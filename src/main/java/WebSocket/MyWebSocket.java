package WebSocket;

import Daojiao.Msg;
import Tool.Tool;
import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint("/websocket/{roomName}/{userName}")
@Component
public class MyWebSocket {

    static Log log= LogFactory.getLog(MyWebSocket.class);

    private  static int onlineCount = 0;

    private static Map<String, ConcurrentHashMap<String,MyWebSocket>> rooms = new ConcurrentHashMap();

    private Session session;
    /**
     * 标识当前连接客户端的用户名
     */
    private String roomName;
    private String userName;

    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("roomName") String roomstring, @PathParam("userName") String userstring){
        try{
           this.userName=Tool.unescape(userstring);
           this.roomName=Tool.unescape(roomstring);
            this.session = session;
            // 将session按照房间名来存储，将各个房间的用户隔离
            if (!rooms.containsKey(roomName)) {
                // 对应房间不存在时，创建房间
                ConcurrentHashMap<String,MyWebSocket> room = new ConcurrentHashMap<>();
                // 添加用户
                if ((roomName.isEmpty())){
                    room.put(session.getId(),this);
                }else {
                    room.put(userName, this);
                }
                rooms.put(roomName, room);
            } else {
                // 房间已存在，直接添加用户到相应的房间
                rooms.get(roomName).put(userName,this);
            }
            addOnlineCount();//在线数加1
            Date date=new Date(System.currentTimeMillis());
            Msg Smsg =new Msg("01","系统通知",Msg.STU,userName+":加入了聊天室\n当前在线人数为:"+getOnlineCount()
                    ,Msg.TYPE_RECEIVED, date);
            broadcast(roomName,JSON.toJSONString(Smsg));
            log.info("有新连接加入！当前在线人数为" + getOnlineCount());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 收到客户端消息后调用的方法
     * @param msg 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void receiveMsg(String msg, Session session) throws Exception {
        // 此处应该有html过滤
        // 接收到信息后进行广播
        String roomName = this.roomName;
        broadcast(roomName, msg);
    }

    // 按照房间名进行广播
    public static void broadcast(String roomName, String msg) throws Exception {
        ConcurrentHashMap<String, MyWebSocket> map = rooms.get(roomName);
        for(String key:map.keySet()){//keySet获取map集合key的集合  然后在遍历key即可
            try{
                MyWebSocket myWebSocket = map.get(key);
                myWebSocket.sendMessage(msg);//
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("roomName") String roomString,@PathParam("userName") String userString, Session session) throws Exception {
        roomName=Tool.unescape(roomString);
        userName=Tool.unescape(userString);
        if (userName.isEmpty()){
            rooms.get(roomName).remove(session.getId());
        }else {
            rooms.get(roomName).remove(userName);
        }
        subOnlineCount();
        Date date=new Date(System.currentTimeMillis());
        Msg Smsg =new Msg("01","系统通知",Msg.STU, userName+":离开了聊天室\n当前在线人数为:"+getOnlineCount()
                ,Msg.TYPE_RECEIVED, date);
        broadcast(roomName, JSON.toJSONString(Smsg));
        log.info("用户退出:"+userName+",当前在线人数为:" + getOnlineCount());
    }


    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) throws Exception {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }

    public static  int getOnlineCount() {
        return onlineCount;
    }

    public static  void addOnlineCount() {
        MyWebSocket.onlineCount++;
    }

    public static  void subOnlineCount() {
        MyWebSocket.onlineCount--;
    }
}

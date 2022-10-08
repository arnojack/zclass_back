package Servlet_;

import Entity_.Msg;
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
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint("/websocket/{roomName}/{userName}/{userId}")
@Component
public class MyWebSocket {

    static Log log= LogFactory.getLog(MyWebSocket.class);

    private  static int onlineCount = 0;

    private static Map<String, ConcurrentHashMap<String,Session>> rooms = new ConcurrentHashMap();

    private static Map<String,String> user=new ConcurrentHashMap<>();//用户在哪个课堂

    private final String system="小助手";
    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("roomName")String roomString,@PathParam("userName")String userString,@PathParam("userId")String IdString){
        String roomName=Tool.unescape(roomString);
        String userid=Tool.unescape(IdString);
        try{
            if(user.containsKey(userid)){
                rooms.get(user.get(userid)).remove(userid);
                user.remove(userid);
            }else {
                addOnlineCount();//在线数加1
                log.info("总人数： "+getOnlineCount());
            }
            user.put(userid,roomName);
            add(roomName,userid,session);
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
    public void receiveMsg(String msg, Session session) {
        Msg m= JSON.parseObject(msg,Msg.class);
        String roomName=m.getRoom();
        String type=m.getUser_ty();
        String userid=m.getUserid();
        String username=m.getName();
        switch (type){
            case "onOpen":
                if(!Objects.equals(user.get(userid), roomName)){
                    rooms.get(user.get(userid)).remove(userid);
                    user.remove(userid);

                    user.put(userid,roomName);
                    add(roomName,userid,session);
                }
                log.info(username+"-----进入----"+roomName+" 课堂");
                m.setUserid("sys");
                m.setUser_ty("sys");
                m.setName(system);
                m.setContent(username+" 进入 "+roomName+" 课堂");
                break;
        }
        broadcast(roomName, JSON.toJSONString(m));
    }

    // 按照房间名进行广播
    public static void broadcast(String roomName, String msg) {

        ConcurrentHashMap<String, Session> map = rooms.get(roomName);
        for(String key:map.keySet()){//keySet获取map集合key的集合  然后在遍历key即可
            try{
                Session session = map.get(key);
                sendMessage(session,msg);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session,@PathParam("roomName")String roomString,@PathParam("userName")String userString,@PathParam("userId")String IdString) {
        String username=Tool.unescape(userString);
        String userid=Tool.unescape(IdString);
        String oldRoom=user.get(userid);
        Date date=new Date(System.currentTimeMillis());

        subOnlineCount();
        log.info("总人数： "+getOnlineCount());

        rooms.get(oldRoom).remove(userid);
        user.remove(userid);
        log.info(username+"-----退出----"+oldRoom+" 课堂");
        Msg Message=new Msg(oldRoom,"sys",system,
                "sys",username+" 退出 "+oldRoom+" 课堂",Msg.TYPE_RECEIVED,date);
        broadcast(oldRoom,JSON.toJSONString(Message));
    }


    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        log.error("发生错误");
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public static void sendMessage(Session session, String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }

    public void add(String roomName,String userid,Session session) {

        if (!rooms.containsKey(roomName)) {
            // 对应房间不存在时，创建房间
            ConcurrentHashMap<String,Session> room = new ConcurrentHashMap<>();

            room.put(userid, session);

            rooms.put(roomName, room);
        } else {
            // 房间已存在，直接添加用户到相应的房间
            if(!rooms.get(roomName).containsKey(userid))
            rooms.get(roomName).put(userid,session);
        }
    }

    public  int getOnlineCount() {
        return onlineCount;
    }

    public static  void addOnlineCount() {
        MyWebSocket.onlineCount++;
    }

    public static  void subOnlineCount() {
        MyWebSocket.onlineCount--;
    }
}

package WebSocket;

import Daojiao.Msg;
import com.alibaba.fastjson.JSON;

import java.io.IOException;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.server.*;
import javax.websocket.*;

@ServerEndpoint(value="/chat/{roomName}")

public class ChatEndpoint

{

    private static final String GUEST_PREFIX = "访客";
    private static final AtomicInteger connectionIds = new AtomicInteger(0);
    // 定义一个集合，用于保存所有接入的 WebSocket 客户端
    private static final Map<String, Set<ChatEndpoint>> rooms = new ConcurrentHashMap();
    // 定义一个成员变量，记录 WebSocket 客户端的聊天昵称
    private final String nickname;
    // 定义一个成员变量，记录与 WebSocket 之间的会话
    private Session session;

    public ChatEndpoint()
    {
        nickname = GUEST_PREFIX + connectionIds.getAndIncrement();
    }

    // 当客户端连接进来时自动激发该方法
    @OnOpen
    public void start(@PathParam("roomName") String roomName,Session session)
    {
        this.session = session;
        // 将 WebSocket 客户端会话添加到集合中
        if (!rooms.containsKey(roomName)) {
            // 创建房间不存在时，创建房间
            Set<ChatEndpoint> clientSet = new CopyOnWriteArraySet<>();
            // 添加用户
            clientSet.add(this);
            rooms.put(roomName, clientSet);
        } else {
            // 房间已存在，直接添加用户到相应的房间
            rooms.get(roomName).add(this);
        }
        Date date=new Date(System.currentTimeMillis());
        Msg Smsg =new Msg("01","系统通知",Msg.STU,nickname+":加入了聊天室"
                ,Msg.TYPE_RECEIVED, date);
        // 发送消息
        broadcast(roomName,JSON.toJSONString(Smsg));
    }

    // 当客户端断开连接时自动激发该方法
    @OnClose
    public void end(@PathParam("roomName") String roomName)
    {
        rooms.get(roomName).remove(this);
        Date date=new Date(System.currentTimeMillis());
        Msg Smsg =new Msg("01","系统通知",Msg.STU,nickname+":离开了聊天室"
                ,Msg.TYPE_RECEIVED, date);
        // 发送消息
        broadcast(roomName,JSON.toJSONString(Smsg));
    }

    // 每当收到客户端消息时自动激发该方法
    @OnMessage
    public void incoming(@PathParam("roomName") String roomName,String message)
    {
        //String filteredMessage = String.format("%s: %s", nickname, filter(message));
        // 发送消息
        broadcast(roomName,message);
    }

    // 当客户端通信出现错误时激发该方法
    @OnError
    public void onError(Throwable t) throws Throwable
    {
        System.out.println("WebSocket 服务端错误" + t);
    }

    // 实现广播消息的工具方法
    private void broadcast(@PathParam("roomName") String roomName,String msg)
    {
        // 遍历服务器关联的所有客户端
        for (ChatEndpoint client : rooms.get(roomName))
        {
            try {
                synchronized (client)
                {
                    // 发送消息
                    client.session.getBasicRemote().sendText(msg);
                }
            } catch (IOException e) {
                System.out.println("聊天错误，向客户端" + client + "发送消息出现错误。");
                rooms.get(roomName).remove(client);
                try {
                    client.session.close();
                } catch (IOException el) {}
                Date date=new Date(System.currentTimeMillis());
                Msg Smsg =new Msg("01","系统通知",Msg.STU,nickname+":已经被断开了连接"
                        ,Msg.TYPE_RECEIVED, date);
                broadcast(roomName,JSON.toJSONString(Smsg));
            }
        }
    }

}

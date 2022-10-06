package Daojiao;
import java.io.Serializable;
import java.util.Date;

/*
 我们需要单例保存的数据为：
 1.消息的内容；
 2.消息的类型：发送还是接收；
 3.消息创建时间
 */
public class Msg implements Serializable {
    private String room;
    private String userid;
    private String username;
    private String content;
    private String user_ty;
    private int type;
    private Date time;
    public final static int TYPE_RECEIVED=0;
    public final static int TYPE_SENT=1;

    public static String STU="student";
    public static String TEA="teacher";
    public static String USERTY="user_ty";
    public static String USERID="userid";
    public static String NAME="username";
    public static String CONTENT = "content";
    public static String TYPE = "type";
    public static String TIME = "time";

    public Msg(Msg msg){
        this.userid=msg.userid;
        this.username=msg.username;
        this.user_ty=msg.user_ty;
        this.content =msg.content;
        this.type = msg.type;
        this.time = msg.time;
    }
    public Msg(String room,String userid,String name,String user_ty){
        this.userid=userid;
        this.username=name;
        this.user_ty=user_ty;
        this.room=room;
    }
    public Msg(String room,String userid,String name,String user_ty,String content,int type,Date time){
        this.room=room;
        this.userid=userid;
        this.username=name;
        this.user_ty=user_ty;
        this.content =content;
        this.type = type;
        this.time = time;
    }
    public Msg(String userid,String name,String user_ty,String content,int type,Date time){
        this.userid=userid;
        this.username=name;
        this.user_ty=user_ty;
        this.content =content;
        this.type = type;
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getUser_ty() {
        return user_ty;
    }

    public void setUser_ty(String user_ty) {
        this.user_ty = user_ty;
    }

    public Msg() {

    }


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public Date getTime() {
        return time;
    }

}
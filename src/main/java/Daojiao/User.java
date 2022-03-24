package Daojiao;

import java.io.Serializable;
import java.util.Date;

/**
 * JavaBean ,存储User上的 信息
 */
public class User implements Serializable{
    private String userId;
    private String phoneNumber;
    private String password;
    private String userName;

    //用于URL传参和取参时的key
    public static String PHONENUMBER = "phoneNumber";
    public static String PASSWORD = "user_password";
    public static String USERID = "user_id";
    //构造函数
    public User(String userid,String password,String userName){
        this.password = password;
        this.userName = userName;
        this.userId = userid;
    }

    public User() {

    }

    public String getuserId() {
        return userId;
    }
    public void setuserId(String id) {
        this.userId = id;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

}


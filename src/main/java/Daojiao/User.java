package Daojiao;

import java.io.Serializable;
import java.util.Date;

/**
 * JavaBean ,存储User上的 信息
 */
public class User implements Serializable{
    private String userid;
    private String phonenumber;
    private String password;
    private String username;

    //用于URL传参和取参时的key
    public static String PHONENUMBER = "phoneNumber";
    public static String PASSWORD = "user_password";
    public static String USERID = "user_id";
    //构造函数

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}


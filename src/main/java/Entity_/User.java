package Entity_;

import java.io.Serializable;

/**
 * JavaBean ,存储User上的 信息
 */
public class User implements Serializable{
    private String userid;
    private String phonenumber;
    private String password;
    private String sex;
    private String username;
    private String code;
    private String profess;
    private String school;
    private String type;
    private String way;
    private int    flag_login;
    private String method;

    //用于URL传参和取参时的key
    public static String STU="student";
    public static String TEA="teacher";
    public static String WAY = "way";
    public static String METHOD = "method";
    public static String PHONENUMBER = "phonenumber";
    public static String PASSWORD = "password";
    public static String USERID = "userid";
    public static String USERNAME = "username";
    public static String CODE ="code";
    public static String PROFESS="profess";
    public static String SCHOOL ="school";
    public static String SEX ="sex";
    //构造函数

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFlag_login() {
        return flag_login;
    }

    public void setFlag_login(int flag_login) {
        this.flag_login = flag_login;
    }

    public String getMethod() {
        return method;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUserid() {
        return userid;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProfess() {
        return profess;
    }

    public void setProfess(String profess) {
        this.profess = profess;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
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


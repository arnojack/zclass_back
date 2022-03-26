package Daojiao;

import java.io.Serializable;

public class Teacher implements Serializable {
    private String tea_id;
    private String tea_name;
    private String user_id;
    private String tea_profess;
    private String tea_school;
    private String tea_grade;

    //用于URL传参和取参时的key
    public static String TEANAME = "tea_name";
    public static String USERID = "user_id";
    public static String TEAID = "tea_id";

    public String getTea_id() {
        return tea_id;
    }

    public void setTea_id(String tea_id) {
        this.tea_id = tea_id;
    }

    public String getTea_name() {
        return tea_name;
    }

    public void setTea_name(String tea_name) {
        this.tea_name = tea_name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTea_profess() {
        return tea_profess;
    }

    public void setTea_profess(String tea_profess) {
        this.tea_profess = tea_profess;
    }

    public String getTea_grade() {
        return tea_grade;
    }

    public void setTea_grade(String tea_grade) {
        this.tea_grade = tea_grade;
    }

    public String getTea_school() {
        return tea_school;
    }

    public void setTea_school(String tea_school) {
        this.tea_school = tea_school;
    }
}

package Daojiao;

import java.io.Serializable;

public class Student implements Serializable {
    private String stu_id;
    private String stu_name;
    private String stu_profess;
    private String stu_school;
    private String stu_grade;
    private String stu_class;
    private String user_id;

    //用于URL传参和取参时的key
    public static String STUNAME = "stu_name";
    public static String STUID = "stu_id";
    public static String USERID = "user_id";

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getStu_profess() {
        return stu_profess;
    }

    public void setStu_profess(String stu_profess) {
        this.stu_profess = stu_profess;
    }

    public String getStu_school() {
        return stu_school;
    }

    public void setStu_school(String stu_school) {
        this.stu_school = stu_school;
    }

    public String getStu_grade() {
        return stu_grade;
    }

    public void setStu_grade(String stu_grade) {
        this.stu_grade = stu_grade;
    }

    public String getStu_class() {
        return stu_class;
    }

    public void setStu_class(String stu_class) {
        this.stu_class = stu_class;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}

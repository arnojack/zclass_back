package Daojiao;

import java.io.Serializable;

public class Work implements Serializable {
    private String stu_id;
    private String cou_on_id;//课程编号
    private String cou_work_id;//作业编号
    private String cou_work;

    //用于URL传参和取参时的key
    public static String COUONID = "cou_on_id";
    public static String WORKID = "cou_work_id";
    public static String USERID = "user_id";

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getCou_on_id() {
        return cou_on_id;
    }

    public void setCou_on_id(String cou_on_id) {
        this.cou_on_id = cou_on_id;
    }

    public String getCou_work_id() {
        return cou_work_id;
    }

    public void setCou_work_id(String cou_work_id) {
        this.cou_work_id = cou_work_id;
    }

    public String getCou_work() {
        return cou_work;
    }

    public void setCou_work(String cou_work) {
        this.cou_work = cou_work;
    }
}

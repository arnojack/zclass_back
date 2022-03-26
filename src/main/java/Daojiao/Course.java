package Daojiao;

import java.io.Serializable;

public class Course implements Serializable {
    private String cou_on_id;
    private String cou_on_name;
    private String tea_id;

    //用于URL传参和取参时的key
    public static String COUONNAME = "cou_on_name";
    public static String COUONID = "cou_on_id";
    public static String TEAID = "tea_id";

    public String getCou_on_id() {
        return cou_on_id;
    }

    public void setCou_on_id(String cou_on_id) {
        this.cou_on_id = cou_on_id;
    }

    public String getCou_on_name() {
        return cou_on_name;
    }

    public void setCou_on_name(String cou_on_name) {
        this.cou_on_name = cou_on_name;
    }

    public String getTea_id() {
        return tea_id;
    }

    public void setTea_id(String tea_id) {
        this.tea_id = tea_id;
    }
}

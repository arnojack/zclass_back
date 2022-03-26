package Daojiao;

import java.io.Serializable;

public class Sign implements Serializable {
    private String stu_id;
    private String cou_on_id;//课堂编号
    private String cou_sign_id;//签到编号
    private String cou_leave;
    private String sign;


    //用于URL传参和取参时的key
    public static String COUONID = "cou_on_id";
    public static String COUSIGNID = "cou_sign_id";
    public static String STUD = "stu_id";
    
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }



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

    public String getCou_sign_id() {
        return cou_sign_id;
    }

    public void setCou_sign_id(String cou_sign_id) {
        this.cou_sign_id = cou_sign_id;
    }

    public String getCou_leave() {
        return cou_leave;
    }

    public void setCou_leave(String cou_leave) {
        this.cou_leave = cou_leave;
    }
}

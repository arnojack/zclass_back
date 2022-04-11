package Dao;

import Daojiao.Cou_Stu;
import Daojiao.Course;
import com.alibaba.fastjson.JSON;
import jdbc.JdbcGet_course;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;

import static Tool.Tool.resultSetToJSON;

public class CourseSer {
    public JSON Queue(HttpServletRequest request) {
        //这两个是获得android端传过来的数据
        JSON resultSet =null;
        Cou_Stu cou_stu =new Cou_Stu();
        cou_stu.setCou_on_id(request.getParameter(Course.COUONID));
        cou_stu.setStu_userid(request.getParameter(Cou_Stu.STUID));
        cou_stu.setWay(request.getParameter(Cou_Stu.WAY));

        switch (cou_stu.getWay()){
            case "stuget":
                JdbcGet_course jdbcstu = new JdbcGet_course();
                JSON json= jdbcstu.jdbc_stuidget(cou_stu);
                resultSet=json ;
                break;
            case "teaget":
                JdbcGet_course jdbctea = new JdbcGet_course();
                Course course=new Course();
                course.setTea_userid(request.getParameter(Course.TEAID));
                JSON json1= jdbctea.jdbc_teaidget(course);
                resultSet=json1 ;
                break;
            case "memget":
                JdbcGet_course jdbcmem = new JdbcGet_course();
                JSON json2= jdbcmem.jdbc_idgetstu(cou_stu);
                resultSet=json2 ;
                break;
        }
        return resultSet;
    }
    public String Update(HttpServletRequest request) {
        //这两个是获得android端传过来的数据
        String resultSet =null;
        Cou_Stu cou_stu =new Cou_Stu();
        cou_stu.setCou_on_id(request.getParameter(Course.COUONID));
        cou_stu.setStu_userid(request.getParameter(Cou_Stu.STUID));
        cou_stu.setWay(request.getParameter(Cou_Stu.WAY));


        switch (cou_stu.getWay()){
            case "join":
                JdbcGet_course jdbcjoin = new JdbcGet_course();
                if(jdbcjoin.jdbc_joinjudge(cou_stu))
                    resultSet = jdbcjoin.jdbc_cou_stuin(cou_stu);
                else
                    resultSet = "Wrong";
                break;
            case "create":
                JdbcGet_course jdbcreate = new JdbcGet_course();
                Course course=getcourse(request,cou_stu.getCou_on_id());
                resultSet= jdbcreate.jdbc_couin(course);
                break;
            case "upclname":
                JdbcGet_course jdbcname = new JdbcGet_course();
                Course course1=getcourse(request,cou_stu.getCou_on_id());
                resultSet= jdbcname.jdbc_coup(course1,"upclname");
                break;
            case "upclgrade":
                JdbcGet_course jdbcgrade = new JdbcGet_course();
                Course course2=getcourse(request,cou_stu.getCou_on_id());
                resultSet= jdbcgrade.jdbc_coup(course2,"upclgrade");
                break;
            case "upclc":
                JdbcGet_course jdbclc = new JdbcGet_course();
                Course course3=getcourse(request,cou_stu.getCou_on_id());
                resultSet= jdbclc.jdbc_coup(course3,"upclc");
                break;
            case "delall":
                JdbcGet_course jdbcdelall = new JdbcGet_course();
                resultSet= jdbcdelall.jdbc_deall(cou_stu);
                break;
            case "delstu":
                JdbcGet_course jdbcdelstu = new JdbcGet_course();
                resultSet= jdbcdelstu.jdbc_destu(cou_stu);
                break;
        }
        return resultSet;
    }
    public Course getcourse(HttpServletRequest request ,String id){
        Course course=new Course();
        course.setTea_userid(request.getParameter(Course.TEAID));
        course.setCou_on_id(id);
        course.setCou_on_name(request.getParameter(Course.COUONNAME));
        course.setCou_grade(request.getParameter(Course.COUGRADE));
        course.setCou_class(request.getParameter(Course.COUCLASS));
        return course;
    }
}

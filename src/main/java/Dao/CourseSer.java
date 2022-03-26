package Dao;

import Daojiao.Cou_Stu;
import Daojiao.Course;
import jdbc.JdbcGet_course;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

public class CourseSer {
    public ResultSet Get_all(HttpServletRequest request) {
        //这两个是获得android端传过来的数据
        ResultSet resultSet =null;
        Cou_Stu cou_stu =new Cou_Stu();
        cou_stu.setCou_on_id(request.getParameter(Course.COUONID));
        cou_stu.setStu_userid(request.getParameter(Cou_Stu.STUID));
        cou_stu.setWay(request.getParameter(Cou_Stu.WAY));


        switch (cou_stu.getWay()){
            case "stuget":
                JdbcGet_course jdbcstu = new JdbcGet_course();
                resultSet= jdbcstu.jdbc_stuidget(cou_stu);
                break;
            case "teaget":
                JdbcGet_course jdbctea = new JdbcGet_course();
                resultSet= jdbctea.jdbc_teaidget(cou_stu);
                break;
        }
        return resultSet;
    }

}

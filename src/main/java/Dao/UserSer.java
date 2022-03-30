package Dao;


import Daojiao.User;
import com.alibaba.fastjson.JSON;
import jdbc.JdbcGet_user;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

import static Tool.Tool.resultSetToJSON;

public class UserSer {

    public String isLoginOK(HttpServletRequest request) {
        //这两个是获得android端传过来的数据
        User user=new User();
        user.setUserid(request.getParameter(User.USERID));
        user.setUsername(request.getParameter(User.USERNAME));
        user.setPhonenumber(request.getParameter(User.PHONENUMBER));
        user.setSex(request.getParameter(User.SEX));
        user.setPassword(request.getParameter(User.PASSWORD));
        user.setSchool(request.getParameter(User.SCHOOL));
        user.setProfess(request.getParameter(User.PROFESS));
        user.setWay(request.getParameter(User.WAY));

        JdbcGet_user jdbcGet_user =new JdbcGet_user();
        switch (user.getWay()){
            case "signin":
                if(jdbcGet_user.jdbcget_userSignin(user))
                    return "Ok";
                break;
            case "signup":
                if(jdbcGet_user.jdbcInser_userSignup(user))
                    return "Ok";
                break;
            default:
                if(jdbcGet_user.jdbcUpdate(user))
                    return "Ok";
                break;
        }
        return "Wrong";
    }
    public JSON update(HttpServletRequest request){
        JSON resultSet=null;
        User user=new User();
        user.setUserid(request.getParameter(User.USERID));
        user.setUsername(request.getParameter(User.USERNAME));
        user.setPassword(request.getParameter(User.PASSWORD));
        user.setWay(request.getParameter(User.WAY));
        JdbcGet_user jdbcGet_user =new JdbcGet_user();
        switch (user.getWay()){
            case "getuser":
                JdbcGet_user jdbcin = new JdbcGet_user();
                JSON json= jdbcin.jdbcget_user(user);
                resultSet=json;
                break;
        }
        return resultSet;
    }
}
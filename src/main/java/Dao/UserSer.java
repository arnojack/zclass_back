package Dao;


import Daojiao.User;
import jdbc.JdbcGet_user;

import javax.servlet.http.HttpServletRequest;

public class UserSer {

    public Boolean isLoginOK(HttpServletRequest request) {
        //这两个是获得android端传过来的数据
        Boolean result=false;
        User user=new User();
        user.setUserid(request.getParameter(User.USERID));
        user.setPassword(request.getParameter(User.PASSWORD));
        user.setWay(request.getParameter(User.WAY));

        JdbcGet_user jdbcGet_user =new JdbcGet_user();
        switch (user.getWay()){
            case "signin":
                JdbcGet_user jdbcin = new JdbcGet_user();
                result= jdbcGet_user.jdbcget_userSignin(user);
                break;
            case "signup":
                JdbcGet_user jdbcup = new JdbcGet_user();
                result= jdbcGet_user.jdbcInser_userSignup(user);
                break;
        }
        return result;
    }
}
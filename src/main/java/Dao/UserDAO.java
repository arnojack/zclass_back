package Dao;


import Daojiao.User;
import jdbc.JdbcGet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

/**
 *这个是模拟数据库的DAO层，如果用户和下面的phoneNumber(123456),password(abc123456)如果是一样的，就说明前端
 * 传过来的数据是一样的，这个以后拓展成访问数据库
 */
public class UserDAO {

    public boolean isLoginOK(HttpServletRequest request) {
        //这两个是获得android端传过来的数据
        User user=new User();
        user.setuserId(request.getParameter(User.USERID));
        user.setPassword(request.getParameter(User.PASSWORD));
        return judge_user(user);
    }
    /**
     * 这个是验证登录信息是否正确的方法
     * @param Muser
     * @return
     */
    public boolean judge_user(User Muser) {
        String table="user";
        String userid = Muser.getuserId();
        String password = Muser.getPassword();

        String value1 =userid;
        String value2 =password;


        JdbcGet jdbcget = new JdbcGet();
        ResultSet resultSet = jdbcget.jdbcget_user(value1,value2);
        return JdbcGet.isExistColumn(resultSet);
    }
}
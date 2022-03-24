package Dao;


import Daojiao.User;
import jdbc.JdbcGet;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

/**
 *这个是模拟数据库的DAO层，如果用户和下面的phoneNumber(123456),password(abc123456)如果是一样的，就说明前端
 * 传过来的数据是一样的，这个以后拓展成访问数据库
 */
public class UserSignin {

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

        JdbcGet jdbcget = new JdbcGet();
        ResultSet resultSet = jdbcget.jdbcget_userSignin(Muser);
        return JdbcGet.isExistColumn(resultSet);
    }
}
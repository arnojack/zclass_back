package Dao;

import Daojiao.User;
import jdbc.JdbcGet_user;

import javax.servlet.http.HttpServletRequest;

/**
 *这个是模拟数据库的DAO层，如果用户和下面的phoneNumber(123456),password(abc123456)如果是一样的，就说明前端
 * 传过来的数据是一样的，这个以后拓展成访问数据库
 */
public class UserSignup {

    public String isSignupOK(HttpServletRequest request) {
        //这两个是获得android端传过来的数据
        User user=new User();
        user.setUserid(request.getParameter(User.USERID));
        user.setPassword(request.getParameter(User.PASSWORD));
        return judge_user(user);
    }
    /**
     * 这个是验证登录信息是否正确的方法
     * @param Muser
     * @return
     */
    public String judge_user(User Muser) {

        JdbcGet_user jdbcget = new JdbcGet_user();
        return jdbcget.jdbcInser_userSignup(Muser);
    }
}
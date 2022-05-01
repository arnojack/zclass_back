package jdbc;

import Daojiao.User;
import com.alibaba.fastjson.JSON;

import java.sql.*;

import static Tool.Tool.resultSetToJSON;

public class JdbcGet_user {
    /*
    数据库查询
     */
    public JSON jdbcget_user(User user){
        JSON resultSet = null;
        PreparedStatement preparedStatement= null;
        Connection connection= null;
        try {
            connection= JdbcUtils.getConnection();
            String sql = "select * from user where userid = ?"; //要运行的sql语句,通过?来替换登录账号和密码

            assert connection != null;
            preparedStatement= connection.prepareStatement(sql);

            //第一个? 用username字符串去替换
            preparedStatement.setString(1, user.getUserid());


            resultSet = resultSetToJSON(preparedStatement.executeQuery());

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultSet;
    }

    public Boolean jdbcget_userSignin(User user){

        try {
            Connection connection = JdbcUtils.getConnection();                  //获取
            String sql = "select * from user where userid = ? and password = ?"; //要运行的sql语句,通过?来替换登录账号和密码

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //第一个? 用username字符串去替换
            preparedStatement.setString(1, user.getUserid());

            //第二个? 用password字符串去替换
            preparedStatement.setString(2, user.getPassword());

            return preparedStatement.executeQuery().next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
    数据库插入
     */
    public Boolean jdbcInser_userSignup(User user){

        ResultSet resultSet = null;
        PreparedStatement preparedstatement = null;
        Connection connection = null;

        try {
            connection = JdbcUtils.getConnection();

            String sql = "insert into  user(userid,username,password)  value(?,?,?)";
            preparedstatement = connection.prepareStatement(sql);


            //第一个? 用username字符串去替换
            preparedstatement.setString(1, user.getUserid());
            preparedstatement.setString(2, user.getUsername());
            preparedstatement.setString(3, user.getPassword());


            int result = preparedstatement.executeUpdate();
            //executeUpdate:用来实现INSERT、UPDATE 或 DELETE 语句,返回值表示执行sql语句之后影响到的数据行数

            System.out.println("插入了"+result+"条数据");
            return true;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }finally {

            JdbcUtils.releaseResc(resultSet, preparedstatement, connection);        //释放资源
        }
    }
    /*
    数据库更新
     */
    public static Boolean jdbcUpdate(User user){

        ResultSet resultSet = null;
        PreparedStatement preparedstatement = null;
        Connection connection = null;

        try {
            connection = JdbcUtils.getConnection();
            switch (user.getWay()){
                case "upsex":
                    String sql2 = "update user  SET sex=?  WHERE  userid=?";
                    preparedstatement = connection.prepareStatement(sql2);
                    preparedstatement.setString(1, user.getSex());
                    break;
                case "uppass":
                    String sql3 = "update user  SET password=?  WHERE  userid=?";
                    preparedstatement = connection.prepareStatement(sql3);
                    preparedstatement.setString(1, user.getPassword());
                    break;
                case "upname":
                    String sql4 = "update user  SET username=?  WHERE  userid=?";
                    preparedstatement = connection.prepareStatement(sql4);
                    preparedstatement.setString(1, user.getUsername());
                    break;
                case "upschool":
                    String sql5 = "update user  SET school=?  WHERE  userid=?";
                    preparedstatement = connection.prepareStatement(sql5);
                    preparedstatement.setString(1, user.getSchool());
                    break;
                case "upphone":
                    String sql6 = "update user  SET phonenumber=?  WHERE  userid=?";
                    preparedstatement = connection.prepareStatement(sql6);
                    preparedstatement.setString(1, user.getPhonenumber());
                    break;
                case "upprof":
                    String sql7 = "update user  SET profess=?  WHERE  userid=?";
                    preparedstatement = connection.prepareStatement(sql7);
                    preparedstatement.setString(1, user.getProfess());
                    break;
            }
            preparedstatement.setString(2, user.getUserid());


            int result = preparedstatement.executeUpdate();
            //executeUpdate:用来实现INSERT、UPDATE 或 DELETE 语句,返回值表示执行sql语句之后影响到的数据行数

            System.out.println("更新了"+result+"条数据");
            return true;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }finally {

            JdbcUtils.releaseResc(resultSet, preparedstatement, connection);        //释放资源
        }
    }
}
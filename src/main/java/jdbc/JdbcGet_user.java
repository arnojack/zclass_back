package jdbc;

import Daojiao.User;

import java.sql.*;

public class JdbcGet_user {
    /*
    数据库查询
     */
    public ResultSet jdbcget_all(String table){

        ResultSet resultSet = null;
        Statement statement = null;
        Connection connection = null;
        try {
            connection = JdbcUtils.getConnection();        //通过JdbcUtils获取connection
            statement = connection.createStatement();

            String sql = "select * from "+table;

            resultSet = statement.executeQuery(sql);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {

            JdbcUtils.releaseResc(resultSet, statement, connection);        //释放资源
        }
        return resultSet;
    }

    public Boolean jdbcget_userSignin(User user){

        try {
            Connection connection = JdbcUtils.getConnection();                  //获取
            String sql = "select * from user where user_id = ? and user_password = ?"; //要运行的sql语句,通过?来替换登录账号和密码

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

            String sql = "insert into  user(user_id,user_password)  value(?,?)";
            preparedstatement = connection.prepareStatement(sql);


            //第一个? 用username字符串去替换
            preparedstatement.setString(1, user.getUserid());
            preparedstatement.setString(2, user.getPassword());


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
    public void jdbcUpdate(String table, String value,String condition){

        ResultSet resultSet = null;
        PreparedStatement preparedstatement = null;
        Connection connection = null;

        try {
            connection = JdbcUtils.getConnection();

            String sql = "update ?  SET ?  WHERE  ?";
            preparedstatement = connection.prepareStatement(sql);

            //第一个? 用username字符串去替换
            preparedstatement.setString(1, table);
            preparedstatement.setString(2, value);
            preparedstatement.setString(3, condition);


            int result = preparedstatement.executeUpdate(sql);
            //executeUpdate:用来实现INSERT、UPDATE 或 DELETE 语句,返回值表示执行sql语句之后影响到的数据行数

            System.out.println("更新了"+result+"条数据");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {

            JdbcUtils.releaseResc(resultSet, preparedstatement, connection);        //释放资源
        }
    }
}
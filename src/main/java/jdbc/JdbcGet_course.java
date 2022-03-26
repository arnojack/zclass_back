package jdbc;

import Daojiao.Cou_Stu;

import java.sql.*;

public class JdbcGet_course {
    /*
    数据库查询
     */
    public ResultSet jdbc_stuidget(Cou_Stu cou_stu){

        try {
            Connection connection = JdbcUtils.getConnection();                  //获取
            String sql = "select * from cou_stu,course where stu_userid = ?"; //要运行的sql语句,通过?来替换登录账号和密码

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //第一个? 用username字符串去替换
            preparedStatement.setString(1, cou_stu.getStu_userid());


            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet;            //有值则返回;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public ResultSet jdbc_teaidget(Cou_Stu cou_stu){

        try {
            Connection connection = JdbcUtils.getConnection();                  //获取
            String sql_pre="select * from course where cou_on_id = ?";
            PreparedStatement preparedStatement_pre = connection.prepareStatement(sql_pre);

            //第一个? 用username字符串去替换
            preparedStatement_pre.setString(1, cou_stu.getCou_on_id());

            ResultSet resultSet_pre = preparedStatement_pre.executeQuery();
            String pre=resultSet_pre.getString("tea_userid");

            String sql = "select * from course where tea_userid = ?"; //要运行的sql语句,通过?来替换登录账号和密码

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //第一个? 用username字符串去替换
            preparedStatement.setString(1, pre);


            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet;            //有值则返回;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    数据库插入
     */
    public String jdbc_coursein(Cou_Stu cou_stu){

        ResultSet resultSet = null;
        PreparedStatement preparedstatement = null;
        Connection connection = null;

        try {
            connection = JdbcUtils.getConnection();

            String sql = "insert into  cou_stu(cou_on_id,stu_userid)  value(?,?)";
            preparedstatement = connection.prepareStatement(sql);


            //第一个? 用username字符串去替换
            preparedstatement.setString(1, cou_stu.getCou_on_id());
            preparedstatement.setString(2, cou_stu.getStu_userid());

            int result = preparedstatement.executeUpdate();
            //executeUpdate:用来实现INSERT、UPDATE 或 DELETE 语句,返回值表示执行sql语句之后影响到的数据行数

            System.out.println("插入了"+result+"条数据");
            return "Ok";

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "Wrong";
        }finally {

            JdbcUtils.releaseResc(resultSet, preparedstatement, connection);        //释放资源
        }
    }
    /*
    数据库删除
     */
    public void jdbc_cou_studel(Cou_Stu cou_stu){

        ResultSet resultSet = null;
        PreparedStatement preparedstatement = null;
        Connection connection = null;

        try {
            connection = JdbcUtils.getConnection();

            String sql = "DELETE FROM cou_stu WHERE stu_userid=? and cou_on_id=?";
            preparedstatement = connection.prepareStatement(sql);

            //第一个? 用username字符串去替换
            preparedstatement.setString(1, cou_stu.getStu_userid());
            preparedstatement.setString(2, cou_stu.getCou_on_id());


            int result = preparedstatement.executeUpdate();
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

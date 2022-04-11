package jdbc;

import Daojiao.Cou_Stu;
import Daojiao.Course;
import com.alibaba.fastjson.JSON;

import java.sql.*;

import static Tool.Tool.resultSetToJSON;

public class JdbcGet_course {
    /*
    数据库查询
     */
    public Boolean jdbc_joinjudge(Cou_Stu cou_stu){

        try {
            Connection connection = JdbcUtils.getConnection();                  //获取
            String sql1 = "select * from course where  cou_on_id = ?"; //要运行的sql语句,通过?来替换登录账号和密码
            String sql2 = "select * from cou_stu  where cou_on_id = ? and stu_userid=?";

            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);

            //第一个? 用username字符串去替换
            preparedStatement1.setInt(1, Integer.parseInt(cou_stu.getCou_on_id()));

            preparedStatement2.setInt(1, Integer.parseInt(cou_stu.getCou_on_id()));
            preparedStatement2.setString(2, cou_stu.getStu_userid());

            ResultSet resultSet1 = (preparedStatement1.executeQuery());
            ResultSet resultSet2 = (preparedStatement2.executeQuery());

            return resultSet1.next()&& !resultSet2.next()
                    && !resultSet1.getString("tea_userid").equals(cou_stu.getStu_userid());            //有值则返回;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public JSON jdbc_stuidget(Cou_Stu cou_stu){

        try {
            Connection connection = JdbcUtils.getConnection();                  //获取
            String sql = "select * from cou_stu,course,user " +
                    "where cou_stu.cou_on_id=course.cou_on_id and course.tea_userid=user.userid " +
                    "and stu_userid = ?"; //要运行的sql语句,通过?来替换登录账号和密码

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //第一个? 用username字符串去替换
            preparedStatement.setString(1, cou_stu.getStu_userid());


            JSON resultSet = resultSetToJSON(preparedStatement.executeQuery());

            return resultSet;            //有值则返回;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public JSON jdbc_idgetstu(Cou_Stu cou_stu){

        try {
            Connection connection = JdbcUtils.getConnection();                  //获取
            String sql = "select * from cou_stu,user " +
                    "where cou_stu.stu_userid=user.userid and cou_on_id = ?"; //要运行的sql语句,通过?来替换登录账号和密码

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //第一个? 用username字符串去替换
            preparedStatement.setInt(1, Integer.parseInt(cou_stu.getCou_on_id()));


            JSON resultSet = resultSetToJSON(preparedStatement.executeQuery());

            return resultSet;            //有值则返回;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public JSON jdbc_teaidget(Course cou){

        try {
            Connection connection = JdbcUtils.getConnection();                  //获取

            String sql = "select * from course,user where course.tea_userid=user.userid and tea_userid = ?"; //要运行的sql语句,通过?来替换登录账号和密码

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //第一个? 用username字符串去替换
            preparedStatement.setString(1, cou.getTea_userid());


            JSON resultSet = resultSetToJSON(preparedStatement.executeQuery());

            return resultSet;            //有值则返回;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    数据库插入
     */
    public String jdbc_coup(Course course ,String way){

        PreparedStatement preparedstatement = null;
        Connection connection = null;

        try {
            connection = JdbcUtils.getConnection();
            switch (way){
                case "upclname":
                    String sql1 = "UPDATE course SET cou_on_name=? " +
                            "WHERE cou_on_id=?;";
                    preparedstatement = connection.prepareStatement(sql1);
                    preparedstatement.setString(1, course.getCou_on_name());
                    break;
                case "upclgrade":
                    String sql2 = "UPDATE course SET cou_grade=? " +
                            "WHERE cou_on_id=?;";
                    preparedstatement = connection.prepareStatement(sql2);
                    preparedstatement.setString(1, course.getCou_grade());
                    break;
                case "upclc":
                    String sql3 = "UPDATE course SET cou_class=? " +
                            "WHERE cou_on_id=?;";
                    preparedstatement = connection.prepareStatement(sql3);
                    preparedstatement.setString(1, course.getCou_class());
                    break;
            }
            preparedstatement.setInt(2, Integer.parseInt(course.getCou_on_id()));
            int result = preparedstatement.executeUpdate();
            //executeUpdate:用来实现INSERT、UPDATE 或 DELETE 语句,返回值表示执行sql语句之后影响到的数据行数

            System.out.println("更新了"+result+"条数据");
            return "Ok";

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "Wrong";
        }finally {
            //释放资源
        }
    }
    public String jdbc_cou_stuin(Cou_Stu cou_stu){

        PreparedStatement preparedstatement = null;
        Connection connection = null;

        try {
            connection = JdbcUtils.getConnection();

            String sql = "insert into  cou_stu(cou_on_id,stu_userid)  value(?,?)";
            preparedstatement = connection.prepareStatement(sql);


            //第一个? 用username字符串去替换
            preparedstatement.setInt(1, Integer.parseInt(cou_stu.getCou_on_id()));
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
            //释放资源
        }
    }
    public String jdbc_couin(Course course){

        ResultSet resultSet = null;
        PreparedStatement preparedstatement = null;
        Connection connection = null;

        try {
            connection = JdbcUtils.getConnection();

            String sql = "insert into  course(cou_on_name,tea_userid,cou_grade,cou_class)  value(?,?,?,?)";
            preparedstatement = connection.prepareStatement(sql);


            //第一个? 用username字符串去替换
            preparedstatement.setString(1, course.getCou_on_name());
            preparedstatement.setString(2, course.getTea_userid());
            preparedstatement.setString(3, course.getCou_grade());
            preparedstatement.setString(4, course.getCou_class());

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
    public String jdbc_deall(Cou_Stu cou_stu){

        PreparedStatement preparedstatement1 = null;
        PreparedStatement preparedstatement2 = null;
        Connection connection = null;

        try {
            connection = JdbcUtils.getConnection();

            String sql1 = "DELETE FROM course WHERE  cou_on_id=?";
            String sql2 ="DELETE FROM cou_stu WHERE  cou_on_id=?";
            preparedstatement1 = connection.prepareStatement(sql1);
            preparedstatement2 = connection.prepareStatement(sql2);

            //第一个? 用username字符串去替换
            preparedstatement1.setInt(1, Integer.parseInt(cou_stu.getCou_on_id()));
            preparedstatement2.setInt(1, Integer.parseInt(cou_stu.getCou_on_id()));

            int result1 = preparedstatement1.executeUpdate();
            int result2 = preparedstatement2.executeUpdate();
            //executeUpdate:用来实现INSERT、UPDATE 或 DELETE 语句,返回值表示执行sql语句之后影响到的数据行数

            return "Ok";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "Wrong";
        }
    }
    public String jdbc_destu(Cou_Stu cou_stu){

        PreparedStatement preparedstatement1 = null;
        Connection connection = null;

        try {
            connection = JdbcUtils.getConnection();

            String sql1 = "DELETE FROM cou_stu WHERE  stu_userid=?";
            preparedstatement1 = connection.prepareStatement(sql1);

            //第一个? 用username字符串去替换
            preparedstatement1.setInt(1, Integer.parseInt(cou_stu.getStu_userid()));

            int result1 = preparedstatement1.executeUpdate();
            //executeUpdate:用来实现INSERT、UPDATE 或 DELETE 语句,返回值表示执行sql语句之后影响到的数据行数

            return "Ok";

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "Wrong";
        }
    }
}

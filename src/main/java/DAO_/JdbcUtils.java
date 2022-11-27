package DAO_;

import java.sql.*;

public class JdbcUtils {

    private static String driver;
    private static String url;
    private static String user;
    private static String pwd;

    static{

        driver = "com.mysql.cj.jdbc.Driver";
        url = "jdbc:mysql://127.0.0.1:3306/zclass" +
                "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        user = "root";
        pwd = "123456";

    }


    //获取一个链接mysql的Connection对象
    static public Connection getConnection(){

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url,user,pwd);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 释放Mysql资源(毕竟mysql连入个数是有限的)
     * @param resultSet 结果集
     * @param statement
     * @param connection 链接
     */
    public static void releaseResc(ResultSet resultSet, Statement statement, Connection connection) {

        try {
            if(resultSet!=null){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(statement!=null){
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

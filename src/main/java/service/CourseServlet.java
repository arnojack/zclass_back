package service;

import Dao.CourseSer;
import Dao.UserSer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

public class CourseServlet extends HttpServlet {
    /**
     * Constructor of the object.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        CourseSer myjoinedCourse =new CourseSer();
        ResultSet resultSet =myjoinedCourse.Get_all(request);
        out.print(resultSet);
        out.flush();
        out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //这个是初始化这个UserDao类，会先调用无参构造器
        UserSer userSignin = new UserSer();

        //OK和Wrong会在安卓端被接收到用来判断服务端是否通过了验证
        if (userSignin.isLoginOK(request)) {
            out.print("Ok");
        }else {
            out.print("Wrong");
        }

        out.flush();
        out.close();
    }
}

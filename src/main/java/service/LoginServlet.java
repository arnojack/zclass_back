package service;

import Dao.UserSer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    public LoginServlet() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

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
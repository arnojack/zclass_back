package Servlet_;

import Service_.UserSer;
import Entity_.User;
import com.alibaba.fastjson.JSON;

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
        request.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        //这个是初始化这个UserDao类，会先调用无参构造器
        UserSer userSignin = new UserSer();
        String method =request.getParameter(User.METHOD);
        switch (method){
            case "login":
                out.print(userSignin.isLoginOK(request));
                break;
            case "update":
                JSON json=userSignin.update(request);
                out.print(json);
                break;
        }
        out.flush();
        out.close();
    }
}
package service;

import Dao.CourseSer;
import Daojiao.Course;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

@WebServlet("/courseServlet")
public class CourseServlet extends HttpServlet {
    public CourseServlet() {
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
        ResultSet resultSet=null;

        PrintWriter out = response.getWriter();
        CourseSer myjoinedCourse =new CourseSer();
        String method =request.getParameter(Course.METHOD);
        switch (method){
            case "Queue":
                JSON json=myjoinedCourse.Queue(request);
                out.print(json);
                break;
            case "Update":
                String result = myjoinedCourse.Update(request);
                out.print(result);
                break;
        }
        out.flush();
        out.close();
    }
}

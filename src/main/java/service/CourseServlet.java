package service;

import Dao.CourseSer;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import static Tool.Tool.resultSetToJSON;

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

        PrintWriter out = response.getWriter();
        CourseSer myjoinedCourse =new CourseSer();
        ResultSet resultSet =myjoinedCourse.Get_all(request);
        JSON json=resultSetToJSON(resultSet);

        out.print(json);
        out.flush();
        out.close();
    }
}

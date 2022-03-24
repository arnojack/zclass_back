package service;

import Dao.UserDAO;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 这个是一个Servlet类
 *
 * @WebServlet("/loginServlet")相当于在web.xml里面配置了servlet,这是一个注解
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    /**
     * Constructor of the object.
     */
    public LoginServlet() {
        super();
    }

    /**
     * The doGet method of the servlet. <br>
     *这个方法应该是作者用来测试这个servlet是否可行而编写的业务逻辑
     * This method is called when a form has its tag value method equals to get.
     *
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        out.println("<HTML>");
        out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
        out.println("  <BODY>");
        out.print("    This is ");
        out.print(this.getClass());
        out.println(", using the GET method");
        out.println("  </BODY>");
        out.println("</HTML>");
        out.flush();
        out.close();
    }
    /**
     * The doPost method of the servlet. <br>
     *
     * This method is called when a form has its tag value method equals to post.
     *
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        //这个是初始化这个UserDao类，会先调用无参构造器
        UserDAO userDAO = new UserDAO();

        //OK和Wrong会在安卓端被接收到用来判断服务端是否通过了验证
        if (userDAO.isLoginOK(request)) {
            out.print("Ok");
        }else {
            out.print("Wrong");
        }

        out.flush();
        out.close();
    }
    public Map<String, Object>   activate(HttpServletRequest request) {
        InputStream is = null;
        Map<String, Object> map = null;
        try {
            is = request.getInputStream();//获取输入流
            ArrayList<Byte> arr = new ArrayList<Byte>();
            byte[] buffer = new byte[50];//缓存数组
            int len;
            //读取数据
            while ((len = is.read(buffer)) != -1) {
                for (int i = 0; i < len; i++) {
                    arr.add(buffer[i]);
                }
            }
            byte[] src = new byte[arr.size()];
            for (int i = 0; i < src.length; i++) {
                src[i] = arr.get(i);
            }
            String json = new String(src);
            map = new HashMap<String, Object>();
            map = JSON.parseObject(json);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }
}
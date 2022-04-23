package service;

import Daojiao.Cou_Stu;
import Daojiao.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

@WebServlet("/uploadHandleServlet2")
@MultipartConfig
public class UploadHandleServlet2 extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();


        String realPath = "F:/Zclass_date/upload";
        String method =request.getParameter("method");
        String userid = request.getParameter(User.USERID);
        realPath = DownLoadServlet1.getString(request, realPath, method);

        File folder = new File(realPath);
        if (!folder.exists()){
            folder.mkdirs();
        }
        //3.获取上传的文件对象，参数是上传文件的key
        Part file = request.getPart("file");

        String cd = file.getHeader("Content-Disposition");
        String filename = cd.substring(cd.lastIndexOf("=")+2, cd.length()-1);

        //获取文件名的后缀.jpg .png
        String suffix = filename.substring(filename.lastIndexOf("."));
        //生产新的文件名
        String newName = userid + suffix;
        //5.保存文件，参数是文件路径
        file.write(realPath+"/"+newName);
        out.print("上传成功!");
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

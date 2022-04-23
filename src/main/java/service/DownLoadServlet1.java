package service;

import Daojiao.Cou_Stu;
import Daojiao.Course;
import Daojiao.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@WebServlet("/downLoadServlet1")
public class DownLoadServlet1 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String realPath="F:/Zclass_date/upload";
        response.setCharacterEncoding("UTF-8");
        //得到要下载的文件名
        String method =request.getParameter("method");
        String userid = request.getParameter(User.USERID);
        String cou_on_id = request.getParameter(Course.COUONID);
        String filename=request.getParameter("filename");
        String suffix = filename.substring(filename.lastIndexOf("."));
        realPath = getString(request, realPath, method);
        realPath=realPath+"/"+userid+suffix;

        //得到要下载的文件
        File file = new File(realPath);
        //如果文件不存在
        if(!file.exists()){
            System.out.println("文件不存在");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print("您要下载的资源已被删除！！");
            out.flush();
            out.close();
            return;
        }
        response.setContentType("application/octet-stream");
        //设置响应头，控制浏览器下载该文件
        String newfname= URLEncoder.encode(file.getName(),"utf-8");
        response.setHeader("content-disposition", "attachment;filename=" + newfname);
        response.setHeader("Content-Length", ""+file.length());
        response.addHeader("Accept-Encoding", "application/octet-stream");
        //读取要下载的文件，保存到文件输入流
        FileInputStream in = new FileInputStream(realPath );
        //创建输出流
        OutputStream os = response.getOutputStream();
        //设置缓存区
        byte[] bytes = new byte[in.available()];
        in.read(bytes);
        os.write(bytes);
        //关闭输入流
        in.close();
        //关闭输出流
        os.close();
    }

    static String getString(HttpServletRequest request, String realPath, String method) {
        switch (method){
            case "icon":
                realPath=realPath+"/icon";
                break;
            case "work":
                String cou_on_id= request.getParameter(Cou_Stu.COUONID);
                String id= request.getParameter("id");
                realPath=realPath+"/work"+"/"+cou_on_id+"/"+id;
                break;
        }
        return realPath;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }
}

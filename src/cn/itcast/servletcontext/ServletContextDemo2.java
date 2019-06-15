package cn.itcast.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: day15_response--${PACKAGE_NAME}
 * @author: WaHotDog 2019-05-24 11:43
 **/


@WebServlet( "/servletContextDemo2")
public class ServletContextDemo2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //通过HttpServlet获取
        ServletContext context = this.getServletContext();
        //定义文件名称
        String filename = "a.jpg";
        //获取MIME类型
        String type = context.getMimeType(filename);
        System.out.println(type);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);
    }
}

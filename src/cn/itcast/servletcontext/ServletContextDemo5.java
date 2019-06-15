package cn.itcast.servletcontext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @program: day15_response--${PACKAGE_NAME}
 * @author: WaHotDog 2019-05-24 11:43
 **/


@WebServlet( "/servletContextDemo5")
public class ServletContextDemo5 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        //通过HttpServlet获取
        ServletContext context = this.getServletContext();

        String realPath = context.getRealPath("/b.txt");
        String realPath1 = context.getRealPath("/WEB-INF/c.txt");
        String realPath2 = context.getRealPath("/WEB-INF/classes/a.txt");
        System.out.println(realPath);
//        File file = new File(realPath);
        response.getWriter().write(realPath);
        response.getWriter().write("<br>");
        response.getWriter().write(realPath1);
        response.getWriter().write("<br>");
        response.getWriter().write(realPath2);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);
    }
}

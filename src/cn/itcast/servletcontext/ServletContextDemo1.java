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


@WebServlet( "/servletContextDemo1")
public class ServletContextDemo1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*ServletContext对象获取*/
        ServletContext context1 = request.getServletContext();
        ServletContext context2 = this.getServletContext();
        System.out.println(context1);
        System.out.println(context2);

        System.out.println(context1==context2);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);
    }
}

package cn.itcast.servlet;

import cn.itcast.bean.User;
import cn.itcast.dao.UserDao;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @program: day15_response--${PACKAGE_NAME}
 * @author: WaHotDog 2019-06-14 14:54
 **/


@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        未使用Beanutils时
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        User loginuser = new User();
//        loginuser.setUsername(username);
//        loginuser.setPassword(password);

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/thml;charset=utf-8");

//        接受用户的验证码
        String checkcode = request.getParameter("checkcode");
//        获取服务器验证码
        HttpSession session = request.getSession();
//        接收验证码
        String checkcode_session = (String) session.getAttribute("checkcode_session");
//        防止用户反复使用session实现不需要验证码登录
        session.removeAttribute("checkcode_session");


//        忽略验证码大小写比较字符串
        if (checkcode_session!=null && checkcode_session.equalsIgnoreCase(checkcode)){
            //        使用map将所有数据放到Beanutils中
            Map<String, String[]> map = request.getParameterMap();
            User loginuser = new User();
            try {
                BeanUtils.populate(loginuser,map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            UserDao userDao = new UserDao();
            User user = userDao.login(loginuser);
            if (user != null){
//            System.out.println(userDao.login(loginuser));
//            response.getWriter().write(userDao.login(loginuser).getUsername());
//            response.getWriter().write("你好");
                session.setAttribute("username",loginuser.getUsername());
                session.setAttribute("password",loginuser.getPassword());
//                跳转是2次请求所以要将数据存到session(服务器上)
                response.sendRedirect(request.getContextPath()+"/success.html");
            }else{
//               存储信息到request
                request.setAttribute("msg_pp","账号密码错误！");
//                转发到登陆页面
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }
        }else{
//            存储信息到request
            request.setAttribute("msg_cc","验证码错误！");
//            转发到登陆页面
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

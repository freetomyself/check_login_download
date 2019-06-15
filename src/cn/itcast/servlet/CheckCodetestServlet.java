package cn.itcast.servlet;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @program: day15_response--${PACKAGE_NAME}
 * @author: WaHotDog 2019-06-11 19:55
 **/


@WebServlet("/checkCodetestServlet")
public class CheckCodetestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 100;
        int height = 50;
//      在内存画出一块区域用于画图
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

//      使用画笔
        Graphics gs = bufferedImage.getGraphics();
//        设置颜色
        gs.setColor(Color.PINK);
//        画形状
        gs.fillRect(0, 0, width, height);
//        设置颜色
        gs.setColor(Color.BLUE);
//        画框
        gs.drawRect(0, 0, width - 1, height - 1);
//        设置验证码
        String ck = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random rd = new Random();
//        用于存放验证码
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 4; i++) {
//            通过规则生成随机数范围
            int index = rd.nextInt(ck.length());
//            通过获得的随机数取对应的值
            char c = ck.charAt(index);
//            将验证码添加到内存
            sb.append(c);
//            输出验证码
            gs.drawString(c + "", width / 5 * i, height / 2 + 5);
        }
        String value = sb.toString();
        System.out.println(value);
        request.getSession().setAttribute("checkcode_session",value);
        gs.setColor(Color.GREEN);
//        画干扰线
        for (int i = 1; i < 10; i++) {
            int w1 = rd.nextInt(width);
            int h1 = rd.nextInt(height);
            int w2 = rd.nextInt(width);
            int h2 = rd.nextInt(height);
            gs.drawLine(w1,h1,w2,h2);
        }
//        将干扰线输出
        ImageIO.write(bufferedImage,"jpg", response.getOutputStream());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

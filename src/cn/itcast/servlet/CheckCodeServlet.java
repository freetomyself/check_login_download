package cn.itcast.servlet;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
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
 * @author: WaHotDog 2019-05-23 15:11
 **/


@WebServlet( "/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int width=100;
        int height=50;
        //1创建一个对象在内存中创建一个图片（验证码图片对象）
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //2美化图片
        //2.1填充颜色
        Graphics gs = image.getGraphics();
        gs.setColor(Color.PINK);
        gs.fillRect(0,0,width,height);
        //2.2画边框
        gs.setColor(Color.BLUE);
        gs.drawRect(0,0,width-1,height-1);
        //2.3写验证码
        String yzm="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
            //获取字符
        String value="";
        Random rm = new Random();
        for(int i = 1; i<=4;i++) {
            int index = rm.nextInt(yzm.length());
            char c = yzm.charAt(index);
            value = value+c;
            gs.drawString( c+"" , width/5*i,height/2+5);
        }
        System.out.println(value);
        //2.4画干扰线
        gs.setColor(Color.GREEN);
        for (int i = 1;i<=10;i++) {
            int x1 =  rm.nextInt(width);
            int x2 = rm.nextInt(width);
            int y1 = rm.nextInt(height);
            int y2 = rm.nextInt(height);
            gs.drawLine(x1,y1,x2,y2);
        }
        //3将图片输出到页面展示
        ImageIO.write(image,"jpg",response.getOutputStream());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);
    }
}

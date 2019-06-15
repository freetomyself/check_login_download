package cn.itcast.download;

import cn.itcast.utils.DownLoadUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @program: day15_response--${PACKAGE_NAME}
 * @author: WaHotDog 2019-05-24 15:50
 **/


@WebServlet( "/downloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1获取文件名
        String filename = request.getParameter("filename");
        System.out.println(filename);
        //1.1使用工具类对中文名进行浏览器适配解析
        String header = request.getHeader("User-Agent");
        filename = DownLoadUtils.getFileName(header,filename);
        //2使用字节输入流加载文件进内存
        //2.1找到文件的服务器路径
        ServletContext context = this.getServletContext();
        String realPath = context.getRealPath("/img/" + filename);
        //2.2用字节流关联
        FileInputStream fls = new FileInputStream(realPath);
        //3设置response的响应头
        //3.1MIME类型 content-type
        String type = context.getMimeType(filename);//获取文件的MIME类型
        response.setHeader("content-type",type);
        //3.2设置响应头打开方式content-disposition
        response.setHeader("content-disposition","attachment;filename="+filename);
        // 4将输入流的数据写出到输出流中
        ServletOutputStream sos = response.getOutputStream();
        byte [] buff = new byte[1024*8];
        int len = 0;
        while((len=fls.read(buff))!=-1){
            sos.write(buff,0,len);
        }
        fls.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);
    }
}

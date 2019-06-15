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
import java.io.IOException;

/**
 * @program: day15_response--${PACKAGE_NAME}
 * @author: WaHotDog 2019-06-14 17:16
 **/


@WebServlet("/downloadTestServlet")
public class DownloadTestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        获取文件名
        String filename = request.getParameter("filename");
//        获取要下载的路径，需要通过getServletContext获取
        ServletContext context = this.getServletContext();
//        获取到文件真实路劲
        String realPath = context.getRealPath("/img/" + filename);
//        用字节流关联
        FileInputStream fis = new FileInputStream(realPath);
//        设置响应头文件context-type 为所选择的文件类型
        String type = context.getMimeType(filename);
        response.setHeader("content-type",type);
//        设置响应头以附件形式打开
        String header = request.getHeader("User-Agent");
        response.setHeader("content-disposition","attachment;filename="+DownLoadUtils.getFileName(header,filename));
//        将输入流的数据写出到输出流数据
        ServletOutputStream outputStream = response.getOutputStream();
        byte [] buff = new byte[1024*8];
        int len = 0;
        while ((len = fis.read(buff))!=-1){
            outputStream.write(buff,0,len);
        }
        fis.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}

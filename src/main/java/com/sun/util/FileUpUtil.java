package com.sun.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
public class FileUpUtil {

    /**
     * 上传多个文件或图片上传在项目路径下的img文件夹在
     * ！！！！！！重新部署项目实效,因为文件夹被删除
     *
     * @param files
     * @param request
     * @return
     */
    public static List<String> upFiles(CommonsMultipartFile files[], HttpServletRequest request) {
        List<String> list = new ArrayList<String>();
        // 获得项目的路径
        ServletContext sc = request.getSession().getServletContext();
        // 上传位置
        String path = sc.getRealPath("/img") + File.separatorChar; // 设定文件保存的目录
        File f = new File(path);
        if (!f.exists())
            f.mkdirs();

        for (int i = 0; i < files.length; i++) {
            // 获得原始文件名
            String fileName = files[i].getOriginalFilename();
            // 新文件名
            String newFileName = UUID.randomUUID() + fileName;
            if (!files[i].isEmpty()) {
                try {
                    FileOutputStream fos = new FileOutputStream(path
                            + newFileName);
                    InputStream in = files[i].getInputStream();
                    int b = 0;
                    while ((b = in.read()) != -1) {
                        fos.write(b);
                    }
                    fos.close();
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            log.debug("上传图片到:" + path + newFileName);
            list.add("img/" + newFileName);
        }
        return list;
    }

    /**
     * 上传一个照片到：<项目路径>/<content>/目录下面，返回图片的名称
     *
     * @param file
     * @param relativePath 相对目录
     * @return
     */
    public static String upFile(CommonsMultipartFile file, String relativePath) {

        // 上传位置
        String path = System.getProperty("user.dir") +"/imgs"+ relativePath; // 设定文件保存的目录
        File f = new File(path);
        if (!f.exists())
            f.mkdirs();
        // 获得原始文件名
        String fileName = file.getOriginalFilename();
        log.debug("原始的文件名：{}", fileName);

        // 新文件名
        String newFileName = UUID.randomUUID()+"."+fileName.split("\\.")[1];
        if (!file.isEmpty()) {
            try {
                FileOutputStream fos = new FileOutputStream(path
                        + newFileName);
                InputStream in = file.getInputStream();
                int b = 0;
                while ((b = in.read()) != -1) {
                    fos.write(b);
                }
                fos.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        log.debug("上传图片到{}目录下的{}", path, newFileName);
        return newFileName;
    }


    /**
     * 下载
     *
     * @param request
     * @param response
     * @param filename
     * @return
     */
    public static void downFile(HttpServletRequest request,
                                HttpServletResponse response, String relativeContent, String filename) {
        // 得到要下载的文件名
        try {

            String path = System.getProperty("user.dir")+"/imgs";

            // 得到要下载的文件
            File file = new File(path + relativeContent + filename);

            // 如果文件不存在
            if (!file.exists()) {
                log.error("图片资源定位错误,您要下载的资源已被删除！！");
                return;
            }

            // 设置响应头，控制浏览器下载该文件
            response.setHeader("content-disposition", "attachment;filename="
                    + URLEncoder.encode(filename, "UTF-8"));
            // 读取要下载的文件，保存到文件输入流
            FileInputStream in = new FileInputStream(path + relativeContent + filename);
            log.debug("所下载图片在服务器的位置：{}",path+relativeContent+filename);
            // 创建输出流
            OutputStream out = response.getOutputStream();
            // 创建缓冲区
            byte buffer[] = new byte[1024];
            int len = 0;
            // 循环将输入流中的内容读取到缓冲区当中
            while ((len = in.read(buffer)) > 0) {
                // 输出缓冲区的内容到浏览器，实现文件下载
                out.write(buffer, 0, len);
            }
            // 关闭文件输入流
            in.close();
            // 关闭输出流
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

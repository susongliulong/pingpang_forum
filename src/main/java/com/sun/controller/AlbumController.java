package com.sun.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sun.common.R;
import com.sun.entity.Admin;
import com.sun.entity.Album;
import com.sun.entity.Photograph;
import com.sun.entity.dto.AlbumKeyWord;
import com.sun.entity.vo.AlbumVO;
import com.sun.service.impl.AdminServiceImpl;
import com.sun.service.impl.AlbumServiceImpl;
import com.sun.service.impl.PhotographServiceImpl;
import com.sun.util.FileUpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sun
 * @since 2024-01-11
 */
@RestController
@RequestMapping("/album")
@Slf4j
public class AlbumController {

    @Autowired
    private AlbumServiceImpl albumService;

    @Autowired
    private PhotographServiceImpl photographService;

    @Autowired
    private AdminServiceImpl adminService;

    /**
     * 上传相册的封面照片
     * @param httpServletRequest
     * @param image
     * @return
     */
    @PostMapping("/uploadCover")
    public String uploadCover(HttpServletRequest httpServletRequest,
                              @RequestParam("image") MultipartFile image,
                              long adminId){

        Admin admin = adminService.getById(adminId);
        // 类型转化，参见配置类：MultipartResolverConfig
        CommonsMultipartFile commonsMultipartFile=(CommonsMultipartFile) image;
        // 封面照片保存的相对目录
        String coverContent="\\" +admin.getName() + "\\"+"cover\\";
        String fileName = FileUpUtil.upFile(commonsMultipartFile, coverContent);
        httpServletRequest.getSession().getServletContext().setAttribute("imageName",fileName);
        return fileName;
    }

    /**
     * 下载相册的封面
     * @param httpServletRequest
     * @param httpServletResponse
     * @param fileName
     */
    @GetMapping("/downloadCover")
    public void downloadCover(HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse,
                              @RequestParam("fileName") String fileName,
                              long adminId){
        Admin admin = adminService.getById(adminId);
        String coverContent = "\\" +admin.getName() + "\\"+"cover\\";
        FileUpUtil.downFile(httpServletRequest,httpServletResponse,coverContent,fileName);
    }

    /**
     * 创建一个新的相册同时保存相册的封面照片
     * /<userName>/cover
     * @param album
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/createAlbum")
    public R createAlbum(@RequestBody Album album,
                         HttpServletRequest httpServletRequest,
                         long adminId){

        Admin admin = adminService.getById(adminId);
        album.setAdminId(admin.getId());
        String coverImagePath = httpServletRequest.getSession().getServletContext().getAttribute("imageName").toString();
        album.setCover(coverImagePath);
        albumService.save(album);
        return R.ok(album,"相册创建成功");
    }

    /**
     * 返回用户所有的相册，其中相册里面包含照片
     * @param adminId
     * @return
     */
    @GetMapping("/findAllById")
    public R findAllById(long adminId){
        LambdaQueryWrapper<Album> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Album::getAdminId,adminId);
        // 查询所有的相册
        List<Album> albums = albumService.list(queryWrapper);
        LinkedList<AlbumVO> albumVOS = new LinkedList<>();
        for (Album album : albums) {
            AlbumVO albumVO = new AlbumVO();
            // 设置相册的属性
            albumVO.setId(album.getId());
            albumVO.setAdminId(album.getAdminId());
            albumVO.setCover(album.getCover());
            albumVO.setCreateTime(album.getCreateTime());
            albumVO.setName(album.getName());
            // 查询相册里面的照片
            List<String>images=photographService.findPictures(album.getId());

            // 每一个相册中照片的数量
            albumVO.setCarousels(photographService.countById(album.getId()));
            albumVO.setLens(photographService.generateLens(album.getId()));
            albumVO.setImages(images);

            // 相册中照片的数量
            LambdaQueryWrapper<Photograph> photographLambdaQueryWrapper = new LambdaQueryWrapper<>();
            photographLambdaQueryWrapper.eq(Photograph::getAlbumId,album.getId());
            albumVO.setPictureNumber(photographService.count(photographLambdaQueryWrapper));

            albumVOS.add(albumVO);
        }

        // 返回结果
        return R.ok(albumVOS);
    }
    @GetMapping("/findAlbumsByKeyWord")
    public R findAlbumsByKeyWord(LocalDate startTime,LocalDate endTime,String albumName) {

        LinkedList<AlbumVO> albumVOS = new LinkedList<>();
        List<Album> albums = albumService.listByKeyWord(startTime,endTime,albumName);
        for (Album album : albums) {
            AlbumVO albumVO = new AlbumVO();
            // 设置相册的属性
            albumVO.setId(album.getId());
            albumVO.setAdminId(album.getAdminId());
            albumVO.setCover(album.getCover());
            albumVO.setCreateTime(album.getCreateTime());
            albumVO.setName(album.getName());
            // 查询相册里面的照片
            List<String> images = photographService.findPictures(album.getId());

            // 每一个相册中照片的数量
            albumVO.setCarousels(photographService.countById(album.getId()));
            albumVO.setLens(photographService.generateLens(album.getId()));
            albumVO.setImages(images);

            // 相册中照片的数量
            LambdaQueryWrapper<Photograph> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Photograph::getAlbumId,album.getId());
            albumVO.setPictureNumber(photographService.count(queryWrapper));

            albumVOS.add(albumVO);
        }
        return R.ok(albumVOS);
    }

    @GetMapping("/findAllByAlbumId")
    public R findAllByAlbumId(long albumId){
        Album album = albumService.getById(albumId);

        // 查询该相册下面的照片
        List<String> images = photographService.findPictures(albumId);
        AlbumVO albumVO = new AlbumVO();
        albumVO.setId(albumId);
        albumVO.setName(album.getName());
        albumVO.setCover(album.getCover());
        albumVO.setCreateTime(album.getCreateTime());
        albumVO.setImages(images);

        return R.ok(albumVO);
    }
}

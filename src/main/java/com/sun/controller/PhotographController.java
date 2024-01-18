package com.sun.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sun.common.R;
import com.sun.entity.Photograph;
import com.sun.service.impl.AdminServiceImpl;
import com.sun.service.impl.AlbumServiceImpl;
import com.sun.service.impl.PhotographServiceImpl;
import com.sun.util.FileUpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sun
 * @since 2024-01-11
 */
@RestController
@RequestMapping("/photograph")
public class PhotographController {

    @Autowired
    private PhotographServiceImpl photographService;

    @Autowired
    private AlbumServiceImpl albumService;

    @Autowired
    private AdminServiceImpl adminService;

    @PostMapping("/uploadPhotograph/{adminId}/{albumId}")
    public R uploadPhotograph(HttpServletRequest request,@PathVariable long adminId, @PathVariable long albumId, MultipartFile image){
        // 获取用户的昵称
        String adminName = adminService.getById(adminId).getName();
        // 获取相册的名称
        String albumName = albumService.getById(albumId).getName();
        // 上传照片到服务器
        String realName = FileUpUtil.upFile((CommonsMultipartFile) image, "\\" + adminName + "\\" + albumName + "\\");
        // 插入图片
        photographService.insertPicture(adminId,albumId,realName);
        return R.ok(realName);
    }

    @GetMapping("/downloadPhotograph/{adminId}/{albumId}")
    public void downloadPhotograph(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @PathVariable long adminId, @PathVariable long albumId, String imageName){
        FileUpUtil.downFile(httpServletRequest,httpServletResponse,"\\"
                + adminService.getById(adminId).getName()
                + "\\"
                + albumService.getById(albumId).getName()
                + "\\",imageName);
    }

    /**
     * 删除照片在数据库中的引用，并不直接从服务器上删除照片
     * @param imageName
     * @return
     */
    @DeleteMapping("/deletePhotograph")
    public R deletePhotograph(String imageName){
        LambdaQueryWrapper<Photograph> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Photograph::getName,imageName);
        photographService.remove(queryWrapper);
        return R.ok("删除成功");
    }

    /**
     * 根据照片所属相册的id删除照片
     */
    @DeleteMapping("/deletePhotoByAlbumId")
    public R deletePhotograph(long albumId){
        albumService.removeById(albumId);
        LambdaQueryWrapper<Photograph> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Photograph::getAlbumId,albumId);
        photographService.remove(queryWrapper);
        return R.ok("删除成功");
    }
}

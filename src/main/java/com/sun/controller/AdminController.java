package com.sun.controller;

import com.sun.common.R;
import com.sun.entity.Admin;
import com.sun.entity.vo.AlbumOverview;
import com.sun.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sun
 * @since 2024-01-11
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;


    @PostMapping("/register")
    public R register(@RequestBody Admin admin){
        return adminService.register(admin);
    }

    @GetMapping("/login")
    public R login(String username, String password, HttpServletRequest httpServletRequest){
        Admin admin = adminService.login(username, password);
        if(admin==null){
            return R.warn("账号或者密码错误");
        }else{
            httpServletRequest.getSession().getServletContext().setAttribute("admin",admin);
            return R.ok(admin,"登录成功");
        }
    }

    /**
     * 注销账号，删除用户所有数据
     * @param adminId
     * @return
     */
    @DeleteMapping("/deleteAdmin")
    public R deleteAdmin(long adminId){
        adminService.deleteAdmin(adminId);
        return R.ok(null,"注销成功");
    }

    /**
     * 用户相册空间预览
     * @param adminId
     * @return
     */
    @GetMapping("/albumOverview")
    public R albumOverview(long adminId){
        AlbumOverview albumOverview=adminService.albumOverview(adminId);
        return R.ok(albumOverview);
    }
}

package com.sun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sun.common.R;
import com.sun.entity.Admin;
import com.sun.entity.Album;
import com.sun.entity.vo.AlbumOverview;
import com.sun.mapper.AdminMapper;
import com.sun.mapper.AlbumMapper;
import com.sun.mapper.PhotographMapper;
import com.sun.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sun
 * @since 2024-01-11
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Resource
    private AdminMapper adminMapper;

    @Resource
    private AlbumMapper albumMapper;

    @Resource
    private PhotographMapper photographMapper;

    LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();

    /**
     * 注册账号，首先检查昵称是否重复
     * @param admin
     * @return
     */
    @Override
    public R register(Admin admin) {
        queryWrapper.clear();
        queryWrapper.eq(Admin::getName,admin.getName());
        if(adminMapper.selectOne(queryWrapper)==null){
            // 注册成功
            adminMapper.insert(admin);
            return R.ok(admin,"注册成功");
        }else{
            return R.error(300,"昵称重复");
        }
    }

    /**
     * 根据username进行登录，其中username可能为手机号，昵称和邮箱
     * @param username
     * @param password
     * @return
     */
    @Override
    public Admin login(String username, String password) {
        queryWrapper.clear();
        Admin admin=adminMapper.findByTelOrNameOrEmail(username);

        if(admin!=null&&admin.getPassword().equals(password)){
            // 更新登录时间
            admin.setLastLoginTime(LocalDateTime.now());
            adminMapper.updateById(admin);
            return admin;
        }else{
            return null;
        }
    }

    @Override
    public void deleteAdmin(long adminId) {

        // 删除账号
        adminMapper.deleteById(adminId);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("admin_id",adminId);
        List<Album> albums = albumMapper.selectByMap(hashMap);
        // 删除照片
        albums.forEach(album -> {
            photographMapper.deleteByAlbumId(album.getId());
        });
        // 删除相册
        albumMapper.deleteByMap(hashMap);

    }

    @Override
    public AlbumOverview albumOverview(long adminId) {
        // 目前先实现这两个
        int albumNumber=albumMapper.countByAdminId(adminId);
        int pictureNumber=photographMapper.countByUser(adminId);
        AlbumOverview albumOverview = new AlbumOverview();
        albumOverview.setAlbumNumber(albumNumber);
        albumOverview.setPictureNumber(pictureNumber);
        albumOverview.setDownloads(100);
        albumOverview.setPageViews(100);

        return albumOverview;
    }
}

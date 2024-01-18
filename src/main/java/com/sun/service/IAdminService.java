package com.sun.service;

import com.sun.common.R;
import com.sun.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sun.entity.vo.AlbumOverview;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sun
 * @since 2024-01-11
 */
public interface IAdminService extends IService<Admin> {

    R register(Admin admin);

    Admin login(String username, String password);

    void deleteAdmin(long adminId);

    AlbumOverview albumOverview(long adminId);

}

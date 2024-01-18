package com.sun.mapper;

import com.sun.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sun
 * @since 2024-01-11
 */
public interface AdminMapper extends BaseMapper<Admin> {

    public List<Admin>findAll();

    // 使用mapper配置文件
    Admin findByTelOrNameOrEmail(String username);
}

package com.sun.mapper;

import com.sun.entity.Album;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sun
 * @since 2024-01-11
 */
public interface AlbumMapper extends BaseMapper<Album> {

    int countByAdminId(long adminId);

}

package com.sun.mapper;

import com.sun.entity.Photograph;
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
public interface PhotographMapper extends BaseMapper<Photograph> {

    List<String> findNamesByAlbumId(Long id);

    void deleteByAlbumId(Long id);

    int countByUser(long adminId);
}

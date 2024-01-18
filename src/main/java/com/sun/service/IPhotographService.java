package com.sun.service;

import com.sun.entity.Photograph;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sun
 * @since 2024-01-11
 */
public interface IPhotographService extends IService<Photograph> {

    void insertPicture(long adminId, long albumId, String realName);

    List<String> findPictures(Long id);

    int countById(Long id);

    List<Integer> generateLens(Long id);
}

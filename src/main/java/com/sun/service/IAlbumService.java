package com.sun.service;

import com.sun.entity.Album;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sun.entity.dto.AlbumKeyWord;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sun
 * @since 2024-01-11
 */
public interface IAlbumService extends IService<Album> {

    List<Album> listByKeyWord(AlbumKeyWord keyWord);

    List<Album> listByKeyWord(LocalDate startTime, LocalDate endTime, String albumName);
}

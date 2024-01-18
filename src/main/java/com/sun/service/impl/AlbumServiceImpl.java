package com.sun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sun.entity.Album;
import com.sun.entity.dto.AlbumKeyWord;
import com.sun.mapper.AlbumMapper;
import com.sun.service.IAlbumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jdk.nashorn.internal.ir.CallNode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
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
public class AlbumServiceImpl extends ServiceImpl<AlbumMapper, Album> implements IAlbumService {

    @Resource
    private AlbumMapper albumMapper;

    LambdaQueryWrapper<Album>queryWrapper=new LambdaQueryWrapper<>();

    /**
     * 按照关键词查找指定的相册
     * @param keyWord
     * @return
     */
    @Override
    public List<Album> listByKeyWord(AlbumKeyWord keyWord) {

        queryWrapper.clear();
        if(keyWord.getStartTime()!=null){
            queryWrapper.ge(Album::getCreateTime,keyWord.getStartTime());
        }
        if(keyWord.getEndTime()!=null){
            queryWrapper.le(Album::getCreateTime,keyWord.getEndTime());
        }
        if(keyWord.getAlbumName()!=null){
            queryWrapper.like(Album::getName,keyWord.getAlbumName());
        }
        return albumMapper.selectList(queryWrapper);
    }

    @Override
    public List<Album> listByKeyWord(LocalDate startTime, LocalDate endTime, String albumName) {
        queryWrapper.clear();

        if(startTime!=null){
            queryWrapper.ge(Album::getCreateTime,startTime);
        }
        if(endTime!=null){
            queryWrapper.le(Album::getCreateTime,endTime);
        }
        if(albumName!=null){
            queryWrapper.like(Album::getName,albumName);
        }
        return albumMapper.selectList(queryWrapper);
    }
}

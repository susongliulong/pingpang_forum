package com.sun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sun.entity.Photograph;
import com.sun.mapper.PhotographMapper;
import com.sun.service.IPhotographService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sun
 * @since 2024-01-11
 */
@Service
public class PhotographServiceImpl extends ServiceImpl<PhotographMapper, Photograph> implements IPhotographService {

    @Resource
    private PhotographMapper photographMapper;

    LambdaQueryWrapper<Photograph> queryWrapper = new LambdaQueryWrapper<>();


    @Override
    public void insertPicture(long adminId, long albumId, String realName) {
        Photograph photograph = new Photograph();
        photograph.setAlbumId(albumId);
        photograph.setName(realName);
        // 插入图片
        photographMapper.insert(photograph);
    }

    /**
     * 根据相册的id查询该相册里面所有的照片
     * @param id
     * @return
     */
    @Override
    public List<String> findPictures(Long id) {
        return photographMapper.findNamesByAlbumId(id);
    }

    /**
     * 计算轮播图的数量
     *
     * @param id
     * @return
     */
    @Override
    public int countById(Long id) {

        queryWrapper.clear();
        queryWrapper.eq(Photograph::getAlbumId, id);
        Integer count = photographMapper.selectCount(queryWrapper);
        // 每张轮播图包含9章照片
        return (int) Math.ceil(count / 9.0);
    }

    /**
     * 配合生成每个轮播图里面图片的数量
     *
     * @param id
     * @return
     */
    @Override
    public List<Integer> generateLens(Long id) {
        queryWrapper.clear();
        queryWrapper.eq(Photograph::getAlbumId, id);
        Integer count = photographMapper.selectCount(queryWrapper);
        LinkedList<Integer> integers = new LinkedList<>();
        while (count > 0) {
            integers.add(count > 9 ? 9 : count);
            count=count-9;
        }
        return integers;
    }

}

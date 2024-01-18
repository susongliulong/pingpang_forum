package com.sun.entity.vo;

import com.sun.entity.Album;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class AlbumVO extends Album {
    // 相册里面的照片
    List<String>images;

    // 前端使用轮播图展示照片，记录轮播图的数量
    int carousels;
    List<Integer>lens;

    // 管理界面显示照片的数量
    int pictureNumber;
}

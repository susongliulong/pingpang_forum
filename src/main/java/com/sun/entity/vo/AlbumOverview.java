package com.sun.entity.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户相册空间预览
 */

@Data
@NoArgsConstructor
public class AlbumOverview {

    private Integer albumNumber;
    private Integer pictureNumber;
    private Integer downloads;
    private Integer pageViews;

}

package com.sun.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 相册搜索关键词
 */
@Data
@NoArgsConstructor
public class AlbumKeyWord {

    private LocalDate startTime;
    private LocalDate endTime;
    private String albumName;
}

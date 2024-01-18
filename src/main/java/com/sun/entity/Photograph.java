package com.sun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author sun
 * @since 2024-01-11
 */
public class Photograph implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 照片名称
     */
    private String name;

    /**
     * 拍摄时间
     */
    private LocalDateTime photoTime;

    /**
     * 上次修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 照片上传时间
     */
    private LocalDateTime submitTime;

    /**
     * 照片的大小，单位为KB
     */
    private Object size;

    /**
     * 图片保存位置
     */
    private String location;

    /**
     * 照片浏览量
     */
    private Integer pageView;

    /**
     * 照片批注
     */
    private String comment;

    /**
     * 照片下载的次数
     */
    private Integer downloadTimes;

    /**
     *
     */
    private long albumId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getPhotoTime() {
        return photoTime;
    }

    public void setPhotoTime(LocalDateTime photoTime) {
        this.photoTime = photoTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(LocalDateTime submitTime) {
        this.submitTime = submitTime;
    }

    public Object getSize() {
        return size;
    }

    public void setSize(Object size) {
        this.size = size;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getPageView() {
        return pageView;
    }

    public void setPageView(Integer pageView) {
        this.pageView = pageView;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getDownloadTimes() {
        return downloadTimes;
    }

    public void setDownloadTimes(Integer downloadTimes) {
        this.downloadTimes = downloadTimes;
    }

    @Override
    public String toString() {
        return "Photograph{" +
            "id = " + id +
            ", name = " + name +
            ", photoTime = " + photoTime +
            ", uodateTime = " + updateTime +
            ", submitTime = " + submitTime +
            ", size = " + size +
            ", location = " + location +
            ", pageView = " + pageView +
            ", comment = " + comment +
            ", downloadTimes = " + downloadTimes +
        "}";
    }

    public long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(long albumId) {
        this.albumId = albumId;
    }
}

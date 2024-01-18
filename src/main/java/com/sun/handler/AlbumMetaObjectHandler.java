package com.sun.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
@Slf4j
public class AlbumMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("开启自动插入填充");
        this.strictInsertFill(metaObject,"updateTime", LocalDateTime::now, LocalDateTime.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("开启自动更新填充");
        this.strictUpdateFill(metaObject,"updateTime",LocalDateTime::now,LocalDateTime.class);
    }
}

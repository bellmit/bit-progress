package com.wpx.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 更新或者修改时自动填充通用字段.
 *
 * @author wpx
 */
@Component
@Slf4j
public class CommonMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("/*------- 【新增记录】自动填充字段 -------*/");

        LocalDateTime now = LocalDateTime.now();
        this.setFieldValByName("createTime", now, metaObject);
        this.setFieldValByName("updateTime", now, metaObject);
        this.setFieldValByName("deleted", false, metaObject);

        // 如果实体类有`disabled` 字段 , 则自动填充 , 没有也不会报错
        this.setFieldValByName("disabled", false, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("/*------- 【修改记录】自动填充字段 -------*/");
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}

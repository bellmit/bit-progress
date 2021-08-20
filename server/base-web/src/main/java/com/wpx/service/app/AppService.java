package com.wpx.service.app;

import com.wpx.mapper.app.AppMapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wpx.model.app.app.App;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 应用信息 服务类
 * </p>
 *
 * @author 不会飞的小鹏
 */
@Service
@Slf4j
public class AppService extends ServiceImpl<AppMapper, App> {

}

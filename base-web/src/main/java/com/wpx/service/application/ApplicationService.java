package com.wpx.service.application;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wpx.mapper.application.ApplicationMapper;
import com.wpx.model.application.application.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 应用信息 服务类
 * </p>
 *
 * @Author: 不会飞的小鹏 
 * @since 2021-06-07
 */
@Service
@Slf4j
public class ApplicationService extends ServiceImpl<ApplicationMapper, Application> {
}

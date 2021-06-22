package com.wpx.service.system.application;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wpx.model.system.application.Application;
import com.wpx.model.system.application.ApplicationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 应用信息 服务类
 * </p>
 *
 * @author wupengxiao
 * @since 2021-06-07
 */
@Service
@Slf4j
public class ApplicationService extends ServiceImpl<ApplicationMapper, Application> {
}

package com.wpx.service.system.application;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wpx.model.system.applicationinfo.ApplicationInfo;
import com.wpx.model.system.applicationinfo.ApplicationInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* <p>
    * 应用配置信息 服务类
    * </p>
*
* @author wupengxiao
* @since 2021-06-27
*/
@Service
@Slf4j
public class ApplicationInfoService extends ServiceImpl<ApplicationInfoMapper, ApplicationInfo> {
}

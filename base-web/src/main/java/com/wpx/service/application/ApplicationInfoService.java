package com.wpx.service.application;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wpx.mapper.application.ApplicationInfoMapper;
import com.wpx.model.application.applicationinfo.ApplicationInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* <p>
    * 应用配置信息 服务类
    * </p>
*
* @author 不会飞的小鹏 
* created on 2021-06-27
*/
@Service
@Slf4j
public class ApplicationInfoService extends ServiceImpl<ApplicationInfoMapper, ApplicationInfo> {
}

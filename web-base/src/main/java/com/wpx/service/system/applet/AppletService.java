package com.wpx.service.system.applet;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wpx.model.system.applet.Applet;
import com.wpx.model.system.applet.AppletMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* <p>
    * 小程序信息 服务类
    * </p>
*
* @author wupengxiao
* @since 2021-06-26
*/
@Service
@Slf4j
public class AppletService extends ServiceImpl<AppletMapper, Applet> {
}

package com.wpx.service.app;

import com.wpx.exception.BaseExceptionMessage;
import com.wpx.mapper.app.AppMapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wpx.model.app.app.App;

import com.wpx.util.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.wpx.constant.AppConstants.PHONE_APP;

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

    /**
     * 获取手机登录用户所属应用
     */
    public App getPhoneApp() {
        return getAppByAppSign(PHONE_APP);
    }

    /**
     * 根据应用标识获取应用信息
     *
     * @param appSign
     */
    public App getAppByAppSign(String appSign) {
        App app = getOne(lambdaQuery().eq(App::getAppSign, appSign));
        Assert.notNull(app, BaseExceptionMessage.APP_NOT_EXIST_EXCEPTION);
        return app;
    }

}

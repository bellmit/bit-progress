package com.wpx.processor;

import com.wpx.exception.BaseException;
import com.wpx.exception.BaseExceptionMessage;
import com.wpx.model.DecryptResult;
import com.wpx.model.app.app.envm.AppTypeEnum;
import com.wpx.model.user.login.WechatPhoneDTO;
import com.wpx.model.user.user.User;
import com.wpx.service.WechatLoginService;
import com.wpx.service.user.UserService;
import com.wpx.service.user.WechatAppletUserService;
import com.wpx.service.user.WechatOaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 不会飞的小鹏
 * @desc: 处理微信用户相关逻辑
 */
@Service
public class WechatUserProcessor {

    @Autowired
    private UserService userService;
    @Autowired
    private WechatAppletUserService wechatAppletUserService;
    @Autowired
    private WechatOaUserService wechatOaUserService;

    /**
     * 更新用户的手机号码
     *
     * @param wechatPhoneDTO
     * @param userId
     */
    public void updatePhone(WechatPhoneDTO wechatPhoneDTO, Long userId) {
        AppTypeEnum appType = userService.getUserAppTypeById(userId);
        String encryptedData = wechatPhoneDTO.getEncryptedData();
        String iv = wechatPhoneDTO.getIv();
        switch (appType) {
            case WECHAT_APPLET: {
                wechatAppletUserService.updatePhone(encryptedData, iv, userId);
                break;
            }
            case WECHAT_OA: {
                wechatOaUserService.updatePhone(encryptedData, iv, userId);
                break;
            }
            default: {
                throw new BaseException(BaseExceptionMessage.NOT_WECHATUSER_EXCEPTION);
            }
        }
    }

}

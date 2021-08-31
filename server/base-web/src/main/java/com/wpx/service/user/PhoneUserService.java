package com.wpx.service.user;

import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.wpx.exception.BaseExceptionMessage;
import com.wpx.mapper.user.PhoneUserMapper;
import com.wpx.model.app.app.App;
import com.wpx.model.user.phoneuser.PhoneUser;
import com.wpx.service.app.AppService;
import com.wpx.util.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.wpx.constant.AppConstants.PHONE_APP;

/**
* <p>
    * 手机用户信息 服务类
    * </p>
*
* @author 不会飞的小鹏
* @since 2021-08-31
*/
@Service
@Slf4j
public class PhoneUserService extends ServiceImpl<PhoneUserMapper, PhoneUser> {

    @Autowired
    private UserService userService;
    @Autowired
    private AppService appService;

    /**
     * 更新用户信息
     *
     * @param phone
     * @return  userId
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public Long updateUser(String phone) {
        LambdaQueryChainWrapper<PhoneUser> query = lambdaQuery();
        query.select(PhoneUser::getUserId).eq(PhoneUser::getPhone, phone);
        PhoneUser phoneUser = getOne(query);
        if (Objects.isNull(phoneUser)) {
            App app = appService.getPhoneApp();
            Long userId = userService.addUser(app.getAppId(), app.getAppSign(), app.getAppType());
            phoneUser = new PhoneUser();
            phoneUser.setUserId(userId).setPhone(phone);
            Assert.isTrue(save(phoneUser), BaseExceptionMessage.PHONEUSER_SAVE_EXCEPTION);
            return userId;
        } else {
            return phoneUser.getUserId();
        }
    }


}

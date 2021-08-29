package com.wpx.service.login;

import com.wpx.constant.StringConstants;
import com.wpx.exception.BaseExceptionMessage;
import com.wpx.exception.CommonException;
import com.wpx.exception.ExceptionMessage;
import com.wpx.gatewayweb.auth.AuthFeignService;
import com.wpx.model.BooleanVO;
import com.wpx.model.JsCode2SessionResult;
import com.wpx.model.ResultVO;
import com.wpx.model.app.wechatapp.envm.WechatAppTypeEnum;
import com.wpx.model.app.wechatapp.pojo.WechatAppRO;
import com.wpx.model.login.LoginDTO;
import com.wpx.model.login.LoginVO;
import com.wpx.model.login.TokenDTO;
import com.wpx.model.user.login.WechatLoginDTO;
import com.wpx.service.WechatLoginService;
import com.wpx.service.app.WechatAppService;
import com.wpx.service.user.WechatAppletUserService;
import com.wpx.service.user.WechatOaUserService;
import com.wpx.service.user.WechatUserService;
import com.wpx.util.Assert;
import com.wpx.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 不会飞的小鹏
 * @desc: 微信登录服务
 */
@Service
public class LoginService {

    @Autowired
    private WechatAppService wechatAppService;

    @Autowired
    private WechatAppletUserService wechatAppletUserService;
    @Autowired
    private WechatOaUserService wechatOaUserService;
    @Autowired
    private WechatUserService wechatUserService;

    @Autowired
    private AuthFeignService authFeignService;

    /**
     * 检查token是否合法
     *
     * @param tokenDTO
     */
    public BooleanVO tokenCheck(TokenDTO tokenDTO) {
        ResultVO<BooleanVO> result = authFeignService.checkToken(tokenDTO);
        return ResultUtils.checkResultVO(result);
    }

    /**
     * 微信登录
     *
     * @param wechatLoginDTO
     * @return token信息
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public LoginVO wechatLogin(WechatLoginDTO wechatLoginDTO) {
        String appSign = wechatLoginDTO.getAppSign();
        WechatAppRO wechatApp = wechatAppService.getWechatAppByAppSign(appSign);
        Long appId = wechatApp.getAppId();
        // 解析jsCode
        String jsCode = wechatLoginDTO.getJsCode();
        JsCode2SessionResult result = WechatLoginService.jsCode2Session(jsCode, wechatApp.getWxAppId(), wechatApp.getAppSecret());
        String openId = result.getOpenId();
        String unionId = result.getUnionId();
        String sessionKey = result.getSessionKey();
        Assert.isNotEmpty(unionId, BaseExceptionMessage.UNION_ID_EMPTY_EXCEPTION);
        Assert.isNotEmpty(openId, BaseExceptionMessage.OPENID_ID_EMPTY_EXCEPTION);

        Boolean authorized = wechatLoginDTO.getAuthorized();
        if (!authorized) {
            wechatLoginDTO.setNickname(StringConstants.EMPTY)
                    .setGender(0)
                    .setCountry(StringConstants.EMPTY)
                    .setProvince(StringConstants.EMPTY)
                    .setCity(StringConstants.EMPTY);
        }

        WechatAppTypeEnum wechatAppType = wechatLoginDTO.getWechatAppType();
        Long wechatUserId = wechatUserService.updateUser(unionId, wechatLoginDTO);
        Long userId;
        switch (wechatAppType) {
            case APPLET: {
                userId = wechatAppletUserService.updateUser(wechatLoginDTO, openId, unionId, wechatUserId, appId,
                        appSign, sessionKey);
                break;
            }
            case OA: {
                userId = wechatOaUserService.updateUser(wechatLoginDTO, openId, unionId, wechatUserId, appSign, appId,
                        sessionKey);
                break;
            }
            default: {
                throw new CommonException(ExceptionMessage.TYPE_NOT_APPOINT);
            }
        }

        // 调用gateway登录接口获取token
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserId(String.valueOf(userId));
        ResultVO<LoginVO> loginResult = authFeignService.login(loginDTO);
        return ResultUtils.checkResultVO(loginResult);
    }
}

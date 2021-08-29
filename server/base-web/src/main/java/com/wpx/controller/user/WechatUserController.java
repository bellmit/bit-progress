package com.wpx.controller.user;

import com.wpx.model.BooleanVO;
import com.wpx.model.ResultVO;
import com.wpx.model.user.login.WechatPhoneDTO;
import com.wpx.processor.WechatUserProcessor;
import com.wpx.service.user.WechatUserService;
import com.wpx.util.UserHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author 不会飞的小鹏
 */
@Api(tags = "系统 -- 微信用户模块")
@RestController
@RequestMapping("api/base/user/wechat")
public class WechatUserController {

    @Autowired
    private WechatUserProcessor wechatUserProcessor;

    @PostMapping("phone")
    @ApiOperation("微信端获取用户手机号码")
    public ResultVO<BooleanVO> updatePhone(@RequestBody @Valid WechatPhoneDTO wechatPhoneDTO) {
        wechatUserProcessor.updatePhone(wechatPhoneDTO, UserHelper.getUserId());
        return ResultVO.successData(BooleanVO.result(true));
    }

}

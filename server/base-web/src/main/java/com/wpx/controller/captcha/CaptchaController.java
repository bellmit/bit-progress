package com.wpx.controller.captcha;

import com.wpx.model.BooleanVO;
import com.wpx.model.ResultVO;
import com.wpx.model.captcha.PhoneCaptchaDTO;
import com.wpx.service.captcha.CaptchaService;
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
 * @desc: 获取各类验证码
 */
@Api(tags = "系统 -- 验证码")
@RestController
@RequestMapping("api/base/captcha")
public class CaptchaController {

    @Autowired
    private CaptchaService captchaService;

    @PostMapping("phone")
    @ApiOperation("获取手机验证码")
    public ResultVO<BooleanVO> phoneCaptcha(@RequestBody @Valid PhoneCaptchaDTO phoneCaptchaDTO) {
        captchaService.sendSmsCaptcha(phoneCaptchaDTO.getPhone());
        return ResultVO.successData(BooleanVO.result(true));
    }

}

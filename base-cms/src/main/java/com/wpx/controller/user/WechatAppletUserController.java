package com.wpx.controller.user;

import com.wpx.model.BooleanVO;
import com.wpx.model.user.wechatappletuser.pojo.cms.WechatAppletUserCmsQueryDTO;
import com.wpx.model.user.wechatappletuser.pojo.cms.WechatAppletUserCmsVO;
import com.wpx.service.user.WechatAppletUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wpx.model.ResultVO;

import java.util.Set;
import org.springframework.web.bind.annotation.RestController;

/**
* @author 不会飞的小鹏
* @since 2021-08-14
*/
@Api(tags = {"微信小程序用户信息"})
@RestController
@RequestMapping("/api/base/user/wechatAppletUser")
public class WechatAppletUserController {

    @Autowired
    private WechatAppletUserService wechatAppletUserService;

    @GetMapping
    @ApiOperation("查询详情")
    public ResultVO<WechatAppletUserCmsVO> findById(@RequestParam @ApiParam("wechatAppletUserId") Long wechatAppletUserId) {
        return ResultVO.successData(wechatAppletUserService.findById(wechatAppletUserId));
    }

    @DeleteMapping
    @ApiOperation("删除")
    public ResultVO<BooleanVO> delete(@RequestParam @ApiParam("wechatAppletUserId列表") Set<Long> wechatAppletUserIds) {
        wechatAppletUserService.deleteWechatAppletUsers(wechatAppletUserIds);
        return ResultVO.successData(BooleanVO.result(true));
    }

    @GetMapping("page")
    @ApiOperation("分页")
    public ResultVO<IPage<WechatAppletUserCmsVO>> page(@ModelAttribute WechatAppletUserCmsQueryDTO wechatAppletUserQueryDTO, Page page) {
        return ResultVO.successData(wechatAppletUserService.findWechatAppletUserPage(wechatAppletUserQueryDTO,page));
    }

}


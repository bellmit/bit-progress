package com.wpx.controller.system.applicationtopic;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wpx.model.system.applicationtopic.pojo.cms.ApplicationTopicCmsAddDTO;
import com.wpx.model.system.applicationtopic.pojo.cms.ApplicationTopicCmsQueryDTO;
import com.wpx.model.system.applicationtopic.pojo.cms.ApplicationTopicCmsUpdateDTO;
import com.wpx.model.system.applicationtopic.pojo.cms.ApplicationTopicCmsVO;
import com.wpx.service.system.applicationtopic.ApplicationTopicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

/**
 * @author wupengxiao
 * @since 2021-06-07
 */
@Api(tags = {"系统 -- 应用主题消息"})
@RestController
@RequestMapping("/api/system/applicationTopic")
public class ApplicationTopicController {

    @Autowired
    private ApplicationTopicService applicationTopicService;

    @GetMapping
    @ApiOperation("查询详情")
    public ApplicationTopicCmsVO findById(@RequestParam @ApiParam("applicationTopicId") Integer applicationTopicId) {
        return applicationTopicService.findById(applicationTopicId);
    }

    @PostMapping
    @ApiOperation("添加")
    public ApplicationTopicCmsVO save(@RequestBody @Valid ApplicationTopicCmsAddDTO applicationTopicAddDTO) {
        return applicationTopicService.saveApplicationTopic(applicationTopicAddDTO);
    }

    @DeleteMapping
    @ApiOperation("删除")
    public void delete(@RequestParam @ApiParam("applicationTopicId列表") Set<Integer> applicationTopicIds) {
        applicationTopicService.deleteApplicationTopics(applicationTopicIds);
    }

    @PutMapping
    @ApiOperation("修改")
    public ApplicationTopicCmsVO update(@RequestBody @Valid ApplicationTopicCmsUpdateDTO applicationTopicUpdateDTO) {
        return applicationTopicService.updateApplicationTopic(applicationTopicUpdateDTO);
    }

    @GetMapping("page")
    @ApiOperation("分页")
    public IPage<ApplicationTopicCmsVO> page(@ModelAttribute ApplicationTopicCmsQueryDTO applicationTopicQueryDTO, Page page) {
        return applicationTopicService.findApplicationTopicPage(applicationTopicQueryDTO, page);
    }

}


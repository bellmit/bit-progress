package com.wpx.rest.dubbo;

import com.wpx.model.BooleanVO;
import com.wpx.model.ResultVO;
import com.wpx.model.quartzjob.pojo.QuartzJobAddDTO;
import com.wpx.model.quartzjob.pojo.QuartzJobDeleteDTO;
import com.wpx.model.quartzjob.pojo.QuartzJobExistsCheckDTO;
import com.wpx.model.quartzjob.pojo.QuartzJobVO;
import com.wpx.scheduler.quartzjob.QuartzJobDubboService;
import com.wpx.service.QuartzJobService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 不会飞的小鹏
 *  QuartzJob dubbo
 */
@Service(version = "1.0.0")
public class QuartzJobDubboServiceImpl implements QuartzJobDubboService {

    @Autowired
    private QuartzJobService quartzJobService;

    /**
     * 创建定时任务
     *
     * @param addDTO 需要添加的定时任务信息
     * @return: ResultVO<QuartzJobVO>
     */
    @Override
    public ResultVO<QuartzJobVO> createQuartzJob(QuartzJobAddDTO addDTO) {
        return ResultVO.successData(quartzJobService.createJob(addDTO));
    }

    /**
     * 移除定时任务
     *
     * @param deleteDTO 需要移除的定时任务信息
     * @return: ResultVO<BooleanVO>
     */
    @Override
    public ResultVO<BooleanVO> deleteQuartzJob(QuartzJobDeleteDTO deleteDTO) {
        quartzJobService.deleteJob(deleteDTO);
        return ResultVO.successData(BooleanVO.result(true));
    }

    /**
     * 查询定时任务是否存在同名任务
     *
     * @param existsCheckDTO 需要检查的任务信息
     * @return: ResultVO<BooleanVO>
     */
    @Override
    public ResultVO<BooleanVO> checkExists(QuartzJobExistsCheckDTO existsCheckDTO) {
        return ResultVO.successData(BooleanVO.result(true));
    }

}

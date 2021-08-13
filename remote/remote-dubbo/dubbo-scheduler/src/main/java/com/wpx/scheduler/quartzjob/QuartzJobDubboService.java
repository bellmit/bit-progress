package com.wpx.scheduler.quartzjob;

import com.wpx.model.BooleanVO;
import com.wpx.model.ResultVO;
import com.wpx.model.quartzjob.pojo.QuartzJobAddDTO;
import com.wpx.model.quartzjob.pojo.QuartzJobDeleteDTO;
import com.wpx.model.quartzjob.pojo.QuartzJobExistsCheckDTO;
import com.wpx.model.quartzjob.pojo.QuartzJobVO;

/**
 * @author 不会飞的小鹏
 *  quartzJob dubbo remote服务
 */
public interface QuartzJobDubboService {

    /**
     * 创建定时任务
     *
     * @param addDTO  需要添加的定时任务信息
     * @return: ResultVO<QuartzJobVO>
     */
    ResultVO<QuartzJobVO> createQuartzJob(QuartzJobAddDTO addDTO);

    /**
     * 移除定时任务
     *
     * @param deleteDTO  需要移除的定时任务信息
     * @return: ResultVO<BooleanVO>
     */
    ResultVO<BooleanVO> deleteQuartzJob(QuartzJobDeleteDTO deleteDTO);

    /**
     * 查询定时任务是否存在同名任务
     *
     * @param existsCheckDTO  需要检查的任务信息
     * @return: ResultVO<BooleanVO>
     */
    ResultVO<BooleanVO> checkExists(QuartzJobExistsCheckDTO existsCheckDTO);

}

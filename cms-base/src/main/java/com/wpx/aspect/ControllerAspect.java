package com.wpx.aspect;

import com.alibaba.fastjson.JSON;
import com.wpx.model.system.managerlog.ManagerLog;
import com.wpx.model.system.managerlog.ManagerLogMapper;
import com.wpx.util.UserHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;

@Aspect
@Component
@Slf4j
public class ControllerAspect {

    @Autowired
    private ManagerLogMapper managerLogMapper;

    /**
     * 对所有controller包及子包下的方法切入
     */
    @Pointcut(value = "execution(public * com.wpx.controller..*.*(..))")
    public void controllerPointcut() {
    }

    /**
     * 记录 POST、PUT、DELETE 方法的操作信息（managerId、API、参数）
     *
     * @param joinPoint
     */
    @Before(value = "controllerPointcut()")
    public void controllerBefore(JoinPoint joinPoint) {
        try {
            Long userId = UserHelper.getUserId();
            if (userId == -1) {
                return;
            }
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            String method = request.getMethod();
            HttpMethod httpMethod = HttpMethod.resolve(method);
            switch (Objects.requireNonNull(httpMethod)) {
                case POST:
                case PUT:
                case DELETE: {
                    LocalDateTime now = LocalDateTime.now();
                    ManagerLog managerLog = new ManagerLog();
                    managerLog.setManagerId(userId).setArgs(JSON.toJSONString(joinPoint.getArgs())).setMethod(method)
                            .setUri(request.getRequestURI()).setCreateTime(now).setUpdateTime(now).setDeleted(false);
                    managerLogMapper.insert(managerLog);
                    break;
                }
                default:
                    break;
            }
        } catch (Exception e) {
            log.error("controllerBeforeCut error", e);
        }
    }

}

package com.wpx.service.user.manager;

import com.wpx.exception.MessageCodes;
import com.wpx.exception.ValidationException;
import com.wpx.okhttp.util.BcryptUtils;
import com.wpx.okhttp.util.StringUtils;
import com.wpx.gatewaycms.GatewayCmsRemoteService;
import com.wpx.model.login.LoginDTO;
import com.wpx.model.login.LogoutDTO;
import com.wpx.model.user.manager.Manager;
import com.wpx.model.user.manager.pojo.cms.LoginSuccessVO;
import com.wpx.model.user.manager.pojo.cms.ManagerLoginDTO;
import com.wpx.redis.SystemRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

/**
 * <p>
 *
 * @author wpx
 **/
@Slf4j
@Service
public class LoginService {

    @Value("${wpx.config.salt}")
    private String salt;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private SystemRedisService systemRedisService;

    @Autowired
    private GatewayCmsRemoteService gatewayCmsRemoteService;

    /**
     * 管理员登录
     *
     * @param loginDTO
     * @return LoginSuccessVO
     */
    public LoginSuccessVO login(ManagerLoginDTO loginDTO) {
        //从redis中获得该验证码
        String uuid = loginDTO.getUuid();
        String rightCaptcha = systemRedisService.getCapText(uuid);
        systemRedisService.deleteCaptcha(uuid);
        if (StringUtils.isEmpty(rightCaptcha)) {
            //验证码已失效
            throw new ValidationException(MessageCodes.AUTH_PICCAPTCHA_LOST);
        } else {
            //判断验证码是否正确
            if (!rightCaptcha.equals(loginDTO.getCaptcha())) {
                //验证码错误
                throw new ValidationException(MessageCodes.AUTH_PICCAPTCHA_WRONG);
            } else {
                //判断账号密码是否正确
                String md5Password;
                String decrypt;
                try {
                    log.info("loginVO.getPassword:[{}]", loginDTO.getPassword());
                    decrypt = BcryptUtils.decrypt(loginDTO.getPassword());
                    log.info("decrypt:[{}]===salt:[{}]", decrypt, salt);
                    md5Password = DigestUtils.md5DigestAsHex((decrypt + salt).getBytes());
                    log.info("md5Password:[{}]", md5Password);
                } catch (Exception e) {
                    log.error("RSAUtil.decrypt exception", e);
                    throw new IllegalArgumentException(MessageCodes.RSAUTIL_DECRYPT_ERROR);
                }
                Manager manager = managerService.getByAccountWithPassword(loginDTO.getAccount(), md5Password);
                log.info("manager:[{}]", manager);
                Assert.notNull(manager, MessageCodes.AUTH_ACCOUNT_PASSWORD_WRONG);
                Assert.isTrue(!manager.getDisabled(), MessageCodes.ACCOUNT_HAS_DISABLED);
                Long managerId = manager.getManagerId();
                int role = manager.getRole().ordinal();
                // 登录获取token
                String token = generateToken(String.valueOf(managerId), String.valueOf(role));
                LoginSuccessVO successVO = new LoginSuccessVO();
                successVO.setToken(token);
                successVO.setRole(manager.getRole());
                successVO.setName(manager.getUsername());
                return successVO;
            }
        }
    }

    /**
     * 生成token
     *
     * @param userId
     * @param role
     */
    public String generateToken(String userId, String role) {
        LoginDTO loginDTO = new LoginDTO(userId, role);
        return gatewayCmsRemoteService.login(loginDTO);
    }

    /**
     * 移除token
     *
     * @param userId
     */
    public void removeToken(String userId) {
        LogoutDTO logoutDTO = new LogoutDTO(userId);
        gatewayCmsRemoteService.logout(logoutDTO);
    }

}

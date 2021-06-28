package com.wpx.service.user.manager;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wpx.common.exception.CustomizeException;
import com.wpx.common.exception.ExceptionMessage;
import com.wpx.common.util.BcryptUtils;
import com.wpx.common.util.CollectionUtils;
import com.wpx.common.util.StringUtils;
import com.wpx.constant.ManagerConstant;
import com.wpx.model.user.manager.Manager;
import com.wpx.model.user.manager.ManagerMapper;
import com.wpx.model.user.manager.envm.RoleEnum;
import com.wpx.model.user.manager.pojo.cms.*;
import com.wpx.util.Assert;
import com.wpx.util.BeanUtils;
import com.wpx.util.ConversionBeanUtils;
import com.wpx.util.UserHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Set;

/**
 * @author wpx
 */
@Slf4j
@Service
public class ManagerService extends ServiceImpl<ManagerMapper, Manager> {

    @Value("${wpx.config.salt}")
    private String salt;

    private final String rootManagerName = ManagerConstant.ROOT_MANAGER_NAME;

    @Autowired
    private LoginService loginService;

    /**
     * 检查和添加超管账号
     */
    @PostConstruct
    public void addSupperManager() {
        boolean hasSupperManger = checkSupperManager();
        if (hasSupperManger) {
            return;
        }
        Manager manager = new Manager();
        manager.setAccount(rootManagerName);
        manager.setPassword(DigestUtils.md5DigestAsHex((rootManagerName + salt).getBytes()));
        manager.setUsername("ROOT");
        manager.setRole(RoleEnum.ROOT);
        manager.setDisabled(false);
        save(manager);
    }

    /**
     * 检查是否有超管账号
     */
    public boolean checkSupperManager() {
        LambdaQueryWrapper<Manager> lambda = new QueryWrapper<Manager>().lambda();
        lambda.eq(Manager::getAccount, rootManagerName);
        return count(lambda) > 0;
    }

    /**
     * 获取管理员的信息
     *
     * @param managerId
     * @param userId
     */
    public ManagerCmsVO findById(Integer managerId, Long userId) {
        Manager manager = getById(userId);
        Assert.isTrue(Objects.equals(RoleEnum.ROOT, manager.getRole()) || Objects.equals(managerId, userId),
                ExceptionMessage.NOT_ROOT_ONLY_CAN_CHECK_SELF_INFO);
        Manager checkManager = getById(managerId);
        Assert.notNull(checkManager, ExceptionMessage.MANAGER_NOT_EXIST);
        return toManagerPageVO(manager);
    }

    /**
     * 获得管理员列表
     *
     * @param queryDTO
     * @param page
     * @return Page<ManagerCmsVO>
     */
    public IPage<ManagerCmsVO> findPage(ManagerCmsQueryDTO queryDTO, Page page) {
        String username = queryDTO.getUsername();
        LambdaQueryWrapper<Manager> lambda = new QueryWrapper<Manager>().lambda();
        if (StringUtils.nonEmpty(username)) {
            lambda.like(Manager::getUsername, queryDTO.getUsername());
        }
        return ConversionBeanUtils.conversionBean(page(page, lambda), this::toManagerPageVO);
    }

    /**
     * Manager -> ManagerCmsVO
     *
     * @param manager
     * @return ManagerCmsVO
     */
    private ManagerCmsVO toManagerPageVO(Manager manager) {
        ManagerCmsVO pageVO = new ManagerCmsVO();
        BeanUtils.copyNonNullProperties(manager, pageVO);
        return pageVO;
    }

    /**
     * 添加管理员
     *
     * @param addDTO
     * @return ManagerCmsVO
     */
    @Transactional
    public ManagerCmsVO add(ManagerCmsAddDTO addDTO) {
        String account = addDTO.getAccount();
        if (!Objects.equals(rootManagerName, account) && addDTO.getRole().equals(RoleEnum.ROOT)) {
            throw new CustomizeException(ExceptionMessage.NOT_ALLOW_ADD_WPX_EXCEPT_ROOT);
        }

        String password;
        try {
            password = DigestUtils.md5DigestAsHex((BcryptUtils.decrypt(addDTO.getPassword()) + salt).getBytes());
        } catch (Exception e) {
            log.error("RSAUtil.decrypt exception", e);
            throw new CustomizeException(ExceptionMessage.RSAUtil_DECRYPT_ERROR);
        }
        LambdaQueryWrapper<Manager> lambda = new QueryWrapper<Manager>().lambda();
        lambda.eq(Manager::getAccount, account);
        Assert.isTrue(count(lambda) == 0, ExceptionMessage.ACCOUNT_ALREADY_EXIST);
        Manager manager = BeanUtils.copyNonNullProperties(addDTO, Manager.class);
        manager.setPassword(password);
        Assert.isTrue(save(manager), ExceptionMessage.MANAGER_SAVE_ERROR);
        return toManagerPageVO(manager);
    }

    /**
     * 更新管理员信息，仅包括用户名
     *
     * @param updateDTO
     */
    @Transactional
    public ManagerCmsVO update(ManagerCmsUpdateDTO updateDTO) {
        Long managerId = updateDTO.getManagerId();
        RoleEnum role = updateDTO.getRole();
        Manager manager = getById(managerId);
        Assert.notNull(manager, ExceptionMessage.MANAGER_NOT_EXIST);
        // 非超管只能编辑自己的信息
        if (!Objects.equals(manager.getRole(), RoleEnum.ROOT) && !Objects.equals(managerId, UserHelper.getUserId())) {
            throw new CustomizeException(ExceptionMessage.NOT_ROOT_ONLY_CAN_EDIT_SELF_INFO);
        }
        // 检查角色权限
        if (!Objects.equals(rootManagerName, manager.getAccount()) && Objects.equals(RoleEnum.ROOT, role)) {
            throw new CustomizeException(ExceptionMessage.NOT_ALLOW_ADD_WPX_EXCEPT_ROOT);
        }
        manager.setRole(role);
        manager.setUsername(updateDTO.getUsername());
        return toManagerPageVO(manager);
    }

    /**
     * 开启或禁用管理员，超管才可以操作
     *
     * @param managerStateDTO
     */
    @Transactional
    public ManagerCmsVO handleDisabled(ManagerStateDTO managerStateDTO, Long userId) {
        Manager manager = getById(userId);
        Assert.isTrue(Objects.equals(RoleEnum.ROOT, manager.getRole()), ExceptionMessage.NOT_ROOT_CANNOT_DISABLED);

        Long managerId = managerStateDTO.getManagerId();
        Manager handleManager = getById(managerId);
        Assert.notNull(handleManager, ExceptionMessage.MANAGER_NOT_EXIST);
        Assert.isTrue(!Objects.equals(RoleEnum.ROOT, handleManager.getRole()), ExceptionMessage.ROOT_CANNOT_DISABLED);
        handleManager.setDisabled(!handleManager.getDisabled());
        if (handleManager.getDisabled()) {

        }
        Assert.isTrue(updateById(handleManager), ExceptionMessage.MANAGER_UPDATE_ERROR);
        return toManagerPageVO(handleManager);
    }

    /**
     * 删除管理员，超管才可以进行账号删除
     *
     * @param managerIds
     */
    @Transactional
    public void delete(Set<Long> managerIds, Long userId) {
        if (CollectionUtils.isEmpty(managerIds)) {
            return;
        }
        Manager manager = getById(userId);
        Assert.isTrue(Objects.equals(RoleEnum.ROOT, manager.getRole()), ExceptionMessage.NOT_ROOT_CANNOT_DELETE);
        managerIds.forEach(this::deleteManager);
    }

    /**
     * 删除管理员
     *
     * @param managerId
     */
    @Transactional
    public void deleteManager(Long managerId) {
        Manager manager = getById(managerId);
        if (Objects.nonNull(manager)) {
            Assert.isTrue(!Objects.equals(RoleEnum.ROOT, manager.getRole()), ExceptionMessage.ROOT_CANNOT_DELETE);
            loginService.removeToken(String.valueOf(managerId));
            manager.setDeleted(true);
            Assert.isTrue(updateById(manager), ExceptionMessage.MANAGER_UPDATE_ERROR);
        }
    }

    /**
     * 修改密码，超管可以对所有账号的密码进行更改，非超管只能更改自己的密码
     *
     * @param managerResetPasswordDTO
     */
    @Transactional
    public ManagerCmsVO resetPassword(ManagerResetPasswordDTO managerResetPasswordDTO, Long userId) {
        Long managerId = managerResetPasswordDTO.getManagerId();
        Manager manager = getById(managerId);
        Assert.notNull(manager, ExceptionMessage.MANAGER_NOT_EXIST);
        if (!Objects.equals(manager.getRole(), RoleEnum.ROOT) && !Objects.equals(managerId, userId)) {
            throw new CustomizeException(ExceptionMessage.NOT_ROOT_ONLY_CAN_EDIT_SELF_INFO);
        }
        String unencryptedPassword = managerResetPasswordDTO.getPassword();
        String md5Password;
        try {
            md5Password = DigestUtils.md5DigestAsHex((BcryptUtils.decrypt(unencryptedPassword) + salt).getBytes());
        } catch (Exception e) {
            log.error("RSAUtil.decrypt exception", e);
            throw new CustomizeException(ExceptionMessage.RSAUtil_DECRYPT_ERROR);
        }
        manager.setPassword(md5Password);
        Assert.isTrue(updateById(manager), ExceptionMessage.MANAGER_UPDATE_ERROR);
        return toManagerPageVO(manager);
    }

    /**
     * 更改角色，只要超管可以进行
     *
     * @param managerRoleDTO
     */
    @Transactional
    public ManagerCmsVO handleRole(ManagerRoleDTO managerRoleDTO) {
        Manager manager = getById(managerRoleDTO.getManagerId());
        Assert.notNull(manager, ExceptionMessage.MANAGER_NOT_EXIST);

        Manager currentManager = getById(UserHelper.getUserId());
        Assert.notNull(currentManager, ExceptionMessage.MANAGER_NOT_EXIST);

        Assert.isTrue(currentManager.getRole().ordinal() < manager.getRole().ordinal(), ExceptionMessage.NOT_ALLOW_SUPERIOR_ROLE);
        manager.setRole(managerRoleDTO.getRole());
        Assert.isTrue(updateById(manager), ExceptionMessage.MANAGER_UPDATE_ERROR);

        return toManagerPageVO(manager);
    }

    /**
     * 通过账号和密码获取管理员信息
     *
     * @param account
     * @param md5Password
     */
    public Manager getByAccountWithPassword(String account, String md5Password) {
        LambdaQueryWrapper<Manager> lambda = new QueryWrapper<Manager>().lambda();
        lambda.eq(Manager::getAccount, account).eq(Manager::getPassword, md5Password);
        return getOne(lambda);
    }
}

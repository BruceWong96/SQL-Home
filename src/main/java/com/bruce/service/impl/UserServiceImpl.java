package com.bruce.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bruce.common.ErrorCode;
import com.bruce.exception.BusinessException;
import com.bruce.mapper.UserMapper;
import com.bruce.model.entity.User;
import com.bruce.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.bruce.constant.UserConstant.USER_LOGIN_STATUS;

/**
 * @author Bruce
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 盐值，用来混淆密码
     */
    private static final String SALT = "bruce";


    @Override
    public Long userRegister(String userName, String userAccount, String userPassword, String checkPassword, String userRole) {
        if (StringUtils.isAnyBlank(userName, userAccount, userPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名、账户、密码不能为空");
        }
        if (userName.length() > 16) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名长度不能超过16位");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户长度不能小于4位");
        }
        if (userPassword.length() < 8 || userPassword.length() > 16) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码长度必须在8-16位之间");
        }
        // 校验两次密码是否一致
        if (!userPassword.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次密码不一致");
        }

        synchronized (userAccount.intern()) {
            // 校验账户是否已存在
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("userAccount", userAccount);
            long count = userMapper.selectCount(queryWrapper);
            if (count > 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户已存在");
            }
            // 加密
            String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
            // 插入数据
            User user = new User();
            user.setUserName(userName);
            user.setUserAccount(userAccount);
            user.setUserPassword(encryptPassword);
            user.setUserRole(userRole);
            boolean result = this.save(user);
            if (!result) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败, 数据库错误");
            }
            return user.getId();
        }
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户、密码不能为空");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户错误");
        }
        if (userPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码错误");
        }
        // 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 校验账户密码是否正确
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encryptPassword);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            log.info("user login failed, userAccount cannot match userPassword");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户不存在或密码错误");
        }
        request.getSession().setAttribute(USER_LOGIN_STATUS, user);
        return user;
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        // 判断是否已经登录
        Object user = request.getSession().getAttribute(USER_LOGIN_STATUS);
        User currentUser = (User) user;
        if (currentUser == null || currentUser.getId() == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR, "用户未登录");
        }
        return null;
    }

    @Override
    public boolean isAdmin(HttpServletRequest request) {
        return false;
    }

    @Override
    public boolean userLogout(HttpServletRequest request) {
        return false;
    }
}

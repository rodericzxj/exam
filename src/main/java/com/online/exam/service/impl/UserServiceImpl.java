package com.online.exam.service.impl;


import com.online.exam.common.Constants;
import com.online.exam.common.ServiceResultEnum;
import com.online.exam.controller.vo.UserVO;
import com.online.exam.dao.UserMapper;
import com.online.exam.entity.User;
import com.online.exam.service.UserService;
import com.online.exam.util.BeanUtil;
import com.online.exam.util.MD5Util;
import com.online.exam.util.PageQueryUtil;
import com.online.exam.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mallUserMapper;

    @Override
    public PageResult getNewBeeMallUsersPage(PageQueryUtil pageUtil) {
        List<User> mallUsers = mallUserMapper.findMallUserList(pageUtil);
        int total = mallUserMapper.getTotalMallUsers(pageUtil);
        PageResult pageResult = new PageResult(mallUsers, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public String register(String loginName, String password) {
        if (mallUserMapper.selectByLoginName(loginName) != null) {
            return ServiceResultEnum.SAME_LOGIN_NAME_EXIST.getResult();
        }
        User registerUser = new User();
        registerUser.setLoginName(loginName);
        registerUser.setNickName(loginName);
        String passwordMD5 = MD5Util.MD5Encode(password, "UTF-8");
        registerUser.setPasswordMd5(passwordMD5);
        if (mallUserMapper.insertSelective(registerUser) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String login(String loginName, String passwordMD5, HttpSession httpSession) {
        User user = mallUserMapper.selectByLoginNameAndPasswd(loginName, passwordMD5);
        if (user != null && httpSession != null) {
            if (user.getLockedFlag() == 1) {
                return ServiceResultEnum.LOGIN_USER_LOCKED.getResult();
            }
            //昵称太长 影响页面展示
            if (user.getNickName() != null && user.getNickName().length() > 7) {
                String tempNickName = user.getNickName().substring(0, 7) + "..";
                user.setNickName(tempNickName);
            }
            UserVO userVO = new UserVO();
            BeanUtil.copyProperties(user, userVO);
            //设置购物车中的数量
            httpSession.setAttribute(Constants.MALL_USER_SESSION_KEY, userVO);
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.LOGIN_ERROR.getResult();
    }

    @Override
    public UserVO updateUserInfo(User mallUser, HttpSession httpSession) {
        User user = mallUserMapper.selectByPrimaryKey(mallUser.getUserId());
        if (user != null) {
            user.setNickName(mallUser.getNickName());
            user.setAddress(mallUser.getAddress());
            user.setIntroduceSign(mallUser.getIntroduceSign());
            if (mallUserMapper.updateByPrimaryKeySelective(user) > 0) {
                UserVO userVO = new UserVO();
                user = mallUserMapper.selectByPrimaryKey(mallUser.getUserId());
                BeanUtil.copyProperties(user, userVO);
                httpSession.setAttribute(Constants.MALL_USER_SESSION_KEY, userVO);
                return userVO;
            }
        }
        return null;
    }

    @Override
    public Boolean lockUsers(Integer[] ids, int lockStatus) {
        if (ids.length < 1) {
            return false;
        }
        return mallUserMapper.lockUserBatch(ids, lockStatus) > 0;
    }
}

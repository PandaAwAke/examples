package com.pandaawake.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pandaawake.user.domain.dto.LoginFormDTO;
import com.pandaawake.user.domain.po.User;
import com.pandaawake.user.domain.vo.UserLoginVO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 */
public interface IUserService extends IService<User> {

    UserLoginVO login(LoginFormDTO loginFormDTO);

    void deductMoney(String pw, Integer totalFee);
}

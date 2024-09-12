package org.example.boot.controller;


import org.example.boot.entity.UserEntity;
import org.example.boot.event.EventPublisher;
import org.example.boot.event.LoginSuccessEvent;
import org.example.boot.service.AccountService;
import org.example.boot.service.CouponService;
import org.example.boot.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired AccountService accountService;

    @Autowired CouponService couponService;

    @Autowired SystemService systemService;

    @Autowired EventPublisher eventPublisher;

    @GetMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("passwd") String passwd) {
//        // 账户 Service：签到加积分
//        accountService.addAccountScore(username);
//        // 优惠 Service：随机发放优惠券
//        couponService.sendCoupon(username);
//        // 系统 Service：登记用户登录 log
//        systemService.recordLog(username);

        LoginSuccessEvent event = new LoginSuccessEvent(new UserEntity(username, passwd));
        eventPublisher.sendEvent(event);

        return username + ": 登录成功";
    }

}

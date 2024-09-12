package org.example.boot.service;

import lombok.extern.slf4j.Slf4j;
import org.example.boot.entity.UserEntity;
import org.example.boot.event.LoginSuccessEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CouponService {

    @EventListener
    public void onEvent(LoginSuccessEvent event) {
        log.info("---------- 事件到达：{} ---------", event);

        UserEntity userEntity = (UserEntity) event.getSource();
        sendCoupon(userEntity.getUsername());
    }

    public void sendCoupon(String username) {
        log.info(username + "得到一张优惠券");
    }

}

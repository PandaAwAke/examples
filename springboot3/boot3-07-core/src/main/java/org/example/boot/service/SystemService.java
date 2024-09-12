package org.example.boot.service;

import lombok.extern.slf4j.Slf4j;
import org.example.boot.entity.UserEntity;
import org.example.boot.event.LoginSuccessEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SystemService {

    @EventListener
    public void onEvent(LoginSuccessEvent event) {
        log.info("---------- 事件到达：{} ---------", event);

        UserEntity userEntity = (UserEntity) event.getSource();
        recordLog(userEntity.getUsername());
    }

    public void recordLog(String username) {
        log.info(username + "登录，已记录");
    }

}

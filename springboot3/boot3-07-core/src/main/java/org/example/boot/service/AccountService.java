package org.example.boot.service;


import lombok.extern.slf4j.Slf4j;
import org.example.boot.entity.UserEntity;
import org.example.boot.event.LoginSuccessEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountService implements ApplicationListener<LoginSuccessEvent> {

    public void addAccountScore(String username) {
        log.info(username + "已签到");
    }

    @Override
    public void onApplicationEvent(LoginSuccessEvent event) {
        log.info("---------- 事件到达：{} ---------", event);

        UserEntity userEntity = (UserEntity) event.getSource();
        addAccountScore(userEntity.getUsername());
    }
}

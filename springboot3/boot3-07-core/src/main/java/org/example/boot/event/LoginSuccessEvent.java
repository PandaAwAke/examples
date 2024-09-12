package org.example.boot.event;


import org.example.boot.entity.UserEntity;
import org.springframework.context.ApplicationEvent;

public class LoginSuccessEvent extends ApplicationEvent {

    public LoginSuccessEvent(UserEntity source) {
        super(source);
    }

}

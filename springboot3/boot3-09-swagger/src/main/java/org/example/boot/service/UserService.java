package org.example.boot.service;


import org.example.boot.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

    private final Map<Long, UserEntity> data = new ConcurrentHashMap<>();

    public void deleteUser(Long id) {
        data.remove(id);
    }

    public void addUser(UserEntity user) {
        data.put(user.getId(), user);
    }

    public List<UserEntity> getUsers() {
        return data.values().stream().toList();
    }

    public UserEntity getUser(Long id) {
        return data.get(id);
    }

}

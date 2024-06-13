package com.mpmd.mi.event.consumer.service;

import com.mpmd.mi.event.consumer.model.UserData;
import com.mpmd.mi.event.consumer.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public void addUser(UserData userData) {
        userRepository.save(userData);
    }
}



package com.mpmd.mi.event.consumer.service;

import com.mpmd.mi.event.consumer.exception.NoSuchDataException;
import com.mpmd.mi.event.consumer.entity.UserData;
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

    @Override
    public UserData getUser(String userID) {
        for (UserData user:userRepository.findAll()) {
           if(user.getUserId().equals(userID)){
               return userRepository.findByUserID(userID);
            }
        }
        throw new NoSuchDataException("No user data found with the id " +userID);
    }

    @Override
    public String deleteUser(String userID) {
        for (UserData user:userRepository.findAll()) {
            if(user.getUserId().equals(userID)){
                userRepository.delete(user);
                return userID;
            }
        }
        throw new NoSuchDataException("No such user is exist with UserID "+userID);
    }
}



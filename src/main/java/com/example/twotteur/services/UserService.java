package com.example.twotteur.services;

import com.example.twotteur.models.UserModel;
import com.example.twotteur.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> getallUsers(){
        List<UserModel> userModels = new ArrayList<>();
        userRepository.findAll().forEach(userModels::add);
        return userModels;
    }
    public int getIdByEmail(String email){
        return userRepository.getFirstByEmail(email).getId();
    }
    public int addUser(String email,String nickname, String password){
        if(!userExist(email)){
            if(userRepository.countUserModelByNickname(nickname)==0){
                userRepository.save(new UserModel(email,nickname,password));
                return 0;
            }
            return 2;
        }
        return 1;
    }

    public boolean userExist(String email){
        return (userRepository.countUserModelByEmail(email)>0);
    }
    public boolean correctPassword(String email,String password){
        return (userRepository.countUserModelByEmailAndPassword(email,password)>0);
    }

}

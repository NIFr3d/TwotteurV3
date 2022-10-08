package com.example.twotteur.services;

import com.example.twotteur.models.User;
import com.example.twotteur.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getallUsers(){
        List<User> Users = new ArrayList<>();
        userRepository.findAll().forEach(Users::add);
        return Users;
    }
    public Optional<User> getUserById(int id){
        return(userRepository.findById(id));
    }
    public User getUserByNickname(String nickname){
        if(userRepository.countUserByNickname(nickname)>0){
            return userRepository.getFirstByNickname(nickname);
        }
        return null;
    }
    public int getIdByEmail(String email){
        return userRepository.getFirstByEmail(email).getId();
    }
    public int addUser(String email,String nickname, String password){
        if(!userExist(email)){
            if(userRepository.countUserByNickname(nickname)==0){
                userRepository.save(new User(email,nickname,password));
                return 0;
            }
            return 2;
        }
        return 1;
    }

    public boolean userExist(String email){
        return (userRepository.countUserByEmail(email)>0);
    }
    public boolean correctPassword(String email,String password){
        return (userRepository.countUserByEmailAndPassword(email,password)>0);
    }

}

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
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }
    public Optional<User> getUserById(int id){
        return(userRepository.findById(id));
    }
    public User getUserByNickname(String username){
        if(userRepository.existsByUsername(username)){
            return userRepository.findByUsername(username).get();
        }
        return null;
    }
    public int getIdByEmail(String email){
        return userRepository.getFirstByEmail(email).getId();
    }
    public int addUser(String email,String username, String password){
        if(!userExist(email)){
            if(!userRepository.existsByUsername(username)){
                userRepository.save(new User(email,username,password));
                return 0;
            }
            return 2;
        }
        return 1;
    }

    public boolean userExist(String email){
        return (userRepository.existsByEmail(email));
    }
    public boolean correctPassword(String email,String password){
        return (userRepository.countUserModelByEmailAndPassword(email,password)>0);
    }

}

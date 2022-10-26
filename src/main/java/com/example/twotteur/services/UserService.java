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
    public Optional<User> getUserById(long id){
        return(userRepository.findById(id));
    }
    public Optional<User> getUserByusername(String username){
        return userRepository.findByUsername(username);
    }
    public long getIdByEmail(String email){
        return userRepository.getFirstByEmail(email).getId();
    }
    public int addUser(String email,String username, String password){
        if(!userExist(email)){
            if(userRepository.countUserByUsername(username)==0){
                userRepository.save(new User(email,username,password));
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

    public void update(User user) {
        userRepository.save(user);
    }
}

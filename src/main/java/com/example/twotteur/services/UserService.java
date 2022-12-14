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

    public List<User> getUsersBySearch(String search){
        List<User> byUsername=userRepository.findByUsernameContaining(search);
        List<User> byNickname=userRepository.findByNicknameContaining(search);
        List<User> result=new ArrayList<>();
        result.addAll(byUsername);
        result.addAll(byNickname);
        for(int i=0;i<result.size();i++){
            for(int j=i+1;j<result.size();j++){
                if(result.get(i).getId()==result.get(j).getId()){
                    result.remove(j);
                    j--;
                }
            }
        }
        return result;
    }

    public void update(User user) {
        userRepository.save(user);
    }
}

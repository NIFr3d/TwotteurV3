package com.example.twotteur.services;

import com.example.twotteur.models.Follow;
import com.example.twotteur.models.User;
import com.example.twotteur.repositories.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowService {

    @Autowired
    private FollowRepository followRepository;

    public List<User> getfollowers(User followed){
        List<User> users=new ArrayList<>();
        List<Follow> follows=followRepository.getByFollowed(followed);
        for(int i=0;i<follows.size();i++){
            users.add(follows.get(i).getFollower());
        }
        return users;
    }
    public List<User> getfollowed(User follower){
        List<User> users=new ArrayList<>();
        List<Follow> follows=followRepository.getByFollower(follower);
        for(int i=0;i<follows.size();i++){
            users.add(follows.get(i).getFollowed());
        }
        return users;
    }
}

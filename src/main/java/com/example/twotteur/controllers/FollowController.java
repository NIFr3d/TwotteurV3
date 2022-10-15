package com.example.twotteur.controllers;

import com.example.twotteur.models.User;
import com.example.twotteur.services.FollowService;
import com.example.twotteur.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FollowController {

    @Autowired
    private FollowService followService;
    @Autowired
    private UserService userService;

    @GetMapping("/follow/{id1}")
    private int follow(@PathVariable long id1, HttpSession session){
        boolean isLogged=false;
        if(session.getAttribute("isLogged") != null) isLogged=(boolean)session.getAttribute("isLogged");
        if(isLogged) {
            long id2 = (long) session.getAttribute("userid");
            if(id1==id2) return 0;
            if(userService.getUserById(id2).isPresent()){
                User user2=userService.getUserById(id2).get();
                if(userService.getUserById(id1).isPresent()) {
                    User user1 = userService.getUserById(id1).get();
                    if(followService.doesFollow(user1,user2)){
                        followService.removeFollow(user1,user2);
                        return 2;
                    }else{
                        followService.addFollow(user1,user2);
                        return 1;
                    }
                }
            }
        }
        return 0;
    }
    @GetMapping("doifollow/{id1}")
    private int doifollow(@PathVariable long id1,HttpSession session){
        boolean isLogged=false;
        if(session.getAttribute("isLogged") != null) isLogged=(boolean)session.getAttribute("isLogged");
        if(isLogged) {
            long id2 = (long) session.getAttribute("userid");
            if (id1 == id2) return 0;
            if(userService.getUserById(id2).isPresent()) {
                User user2 = userService.getUserById(id2).get();
                if (userService.getUserById(id1).isPresent()) {
                    User user1 = userService.getUserById(id1).get();
                    if(followService.doesFollow(user1,user2)) return 1;
                }
            }
        }
        return 0;
    }
}

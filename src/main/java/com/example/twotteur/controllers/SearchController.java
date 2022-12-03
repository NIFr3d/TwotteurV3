package com.example.twotteur.controllers;

import com.example.twotteur.models.User;
import com.example.twotteur.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {
    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public String search(@RequestParam("s") String search){
        List<User> users=userService.getUsersBySearch(search);
        String result="";
        //JSON list of users
        for(User user:users){
            result+="{\"id\":"+user.getId()+",\"username\":\""+user.getusername()+"\",\"nickname\":\""+user.getnickname()+"\"},";
        }
        if(result.length()>0){
            result=result.substring(0,result.length()-1);
        }
        return "["+result+"]";
    }
}

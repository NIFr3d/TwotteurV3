package com.example.twotteur.controllers;

import com.example.twotteur.models.Message;
import com.example.twotteur.models.User;
import com.example.twotteur.services.FollowService;
import com.example.twotteur.services.MessageService;
import com.example.twotteur.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MessageController {

    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private FollowService followService;

    @GetMapping("/messages")
    public String messagePage(HttpSession session, Model model){
        boolean isLogged=false;
        List<User> contacts=new ArrayList<>();
        if(session.getAttribute("isLogged") != null) isLogged=(boolean)session.getAttribute("isLogged");
        if(isLogged) {
            long userid=(long)session.getAttribute("userid");
            if(userService.getUserById(userid).isPresent()) {
                User user = userService.getUserById(userid).get();
                List<User> followeds=followService.getfollowed(user);
                for(int i=0;i<followeds.size();i++){
                    if(followService.doesFollow(user,followeds.get(i))) contacts.add(followeds.get(i));
                }
            }
        }
        model.addAttribute("contacts",contacts);
        return "messages";
    }
}

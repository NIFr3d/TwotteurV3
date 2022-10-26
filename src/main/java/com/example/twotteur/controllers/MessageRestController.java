package com.example.twotteur.controllers;

import com.example.twotteur.models.Message;
import com.example.twotteur.models.User;
import com.example.twotteur.services.MessageService;
import com.example.twotteur.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MessageRestController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;

    @GetMapping("/conv/{username}")
    public String getConv(@PathVariable String username, HttpSession session){
        String histo="";
        boolean isLogged=false;
        if(session.getAttribute("isLogged") != null) isLogged=(boolean)session.getAttribute("isLogged");
        if(isLogged) {
            long id1 = (long) session.getAttribute("userid");
            if(userService.getUserByusername(username).isPresent()) {
                User user2 = userService.getUserByusername(username).get();
                if (userService.getUserById(id1).isPresent()) {
                    User user1 = userService.getUserById(id1).get();
                    List<Message> msgs= messageService.getConv(user1,user2);
                    histo+="[";
                    for(int i=0;i<msgs.size();i++){
                        String user="1";
                        String virgule=",";
                        if(msgs.get(i).getsender().equals(user1)) {user="0";}
                        if(i == msgs.size()-1) {virgule="";}
                        histo+="{\"text\":\""+msgs.get(i).gettext()+"\",\"user\":\""+user+"\",\"date\":\""+msgs.get(i).getdate().toString()+"\"}"+virgule;
                    }
                    histo+="]";
                }
            }
        }
        return histo;
    }

}

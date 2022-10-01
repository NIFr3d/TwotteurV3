package com.example.twotteur.controllers;

import com.example.twotteur.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping(value="/register")
    public static String getLogin() {
        return "register";
    }

    @PostMapping(value="register")
    public RedirectView postLogin(@RequestParam("email") String email,@RequestParam("nickname") String nickname,
                                  @RequestParam("password") String password,@RequestParam("password2") String password2,
                                  HttpSession session){
        if(password.equals(password2)){
            switch (userService.addUser(email, nickname, password)){
                case 0:
                    session.setAttribute("isLogged", true);
                    session.setAttribute("userid", userService.getIdByEmail(email));
                    return new RedirectView("/home");
                case 1:
                    return new RedirectView("/register?e=1");
                case 2:
                    return new RedirectView("/register?e=2");
                default:
                    return new RedirectView("/error?e=403");

            }
        }
        return new RedirectView("/register?e=0");
    }
}

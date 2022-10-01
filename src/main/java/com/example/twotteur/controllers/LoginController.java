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
public class LoginController {

    @Autowired private UserService userService;

    @GetMapping(value="/login")
    public static String getLogin() {
        return "login";
    }

    @PostMapping(value="/login")
    public RedirectView postLogin(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session)
    {
        if(userService.userExist(email)){
            if(userService.correctPassword(email, password)){
                session.setAttribute("isLogged", true);
                session.setAttribute("userid", userService.getIdByEmail(email));
                return new RedirectView("/home");
            }
            return new RedirectView("/login?e=0");
        }
        return new RedirectView("/login?e=1");
    }
}

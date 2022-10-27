package com.example.twotteur.controllers;
import com.example.twotteur.services.UserService;
import com.example.twotteur.services.WSTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {
    @Autowired private UserService userService;
    @Autowired private WSTokenService tokenService;
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
                tokenService.settoken(userService.getUserById(userService.getIdByEmail(email)).get());
                session.setAttribute("wstoken",tokenService.gettoken(userService.getUserById(userService.getIdByEmail(email)).get()).get());
                return new RedirectView("/home");
            }
            return new RedirectView("/login?e=0");
        }
        return new RedirectView("/login?e=1");
    }

    @GetMapping(value="/logout")
    public RedirectView logout(HttpSession session){
        session.invalidate();
        return new RedirectView("/home");
    }

    @GetMapping(value="/register")
    public static String getRegister() {
        return "register";
    }

    @PostMapping(value="register")
    public RedirectView postRegister(@RequestParam("email") String email,@RequestParam("username") String username,
                                  @RequestParam("password") String password,@RequestParam("password2") String password2,
                                  HttpSession session){
        if(password.equals(password2)){
            switch (userService.addUser(email, username, password)){
                case 0:
                    session.setAttribute("isLogged", true);
                    session.setAttribute("userid", userService.getIdByEmail(email));
                    tokenService.settoken(userService.getUserById(userService.getIdByEmail(email)).get());
                    session.setAttribute("wstoken",tokenService.gettoken(userService.getUserById(userService.getIdByEmail(email)).get()));
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

package com.example.twotteur.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class HomeController {

    @GetMapping(value="/home")
    public static String home() {
        return "home";
    }

    @GetMapping(value="/")
    public RedirectView redirectWithUsingRedirectView() {
        return new RedirectView("home");
    }
}

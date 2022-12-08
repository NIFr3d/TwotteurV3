package com.example.twotteur.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;


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

    @GetMapping("test")
    public String test(Model model, HttpSession session) {
        model.addAttribute("wstoken",session.getAttribute("wstoken"));
        return "test";
    }
}

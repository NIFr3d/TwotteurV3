package com.example.twotteur.controllers;

import com.example.twotteur.models.User;
import com.example.twotteur.services.FollowService;
import com.example.twotteur.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FollowController {

    @Autowired
    private FollowService followService;
    @Autowired
    private UserService userService;


}

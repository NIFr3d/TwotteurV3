package com.example.twotteur.services;

import com.example.twotteur.repositories.TwotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwotService {

    @Autowired
    private TwotRepository twotRepository;
}

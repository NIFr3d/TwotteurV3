package com.example.twotteur.services;

import com.example.twotteur.models.TwotModel;
import com.example.twotteur.models.UserModel;
import com.example.twotteur.repositories.TwotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TwotService {

    @Autowired
    private TwotRepository twotRepository;

    public List<TwotModel> getTwots(UserModel user){
        List<TwotModel> twots=new ArrayList<>();
        twotRepository.findTwotModelsByUser(user).forEach(twots::add);
        return twots;
    }
    public List<Integer> getTwotsId(UserModel user){
        List<Integer> ids=new ArrayList<>();
        List<TwotModel> twots=twotRepository.findTwotModelsByUser(user);
        for(int i=0;i< twots.size();i++) {
            ids.add(twots.get(i).getId());
        }
        return ids;
    }

    public void newTweet(UserModel user, String text) {
        twotRepository.save(new TwotModel(user,text));
    }

    public TwotModel getTwotById(int id){
        return twotRepository.getReferenceById(id);
    }
}

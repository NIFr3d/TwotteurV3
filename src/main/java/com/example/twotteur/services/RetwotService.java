package com.example.twotteur.services;

import com.example.twotteur.models.Retwot;
import com.example.twotteur.models.Twot;
import com.example.twotteur.models.User;
import com.example.twotteur.repositories.RetwotRepository;
import com.example.twotteur.repositories.TwotRepository;
import com.example.twotteur.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RetwotService {
    @Autowired
    private RetwotRepository retwotRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TwotRepository twotRepository;


    public int getRetwotCount(Twot twot){
        return retwotRepository.countRetwotsByTwot(twot);
    }

    public List<Retwot> getByUser(User user){
        return retwotRepository.findRetwotsByUser(user);
    }
    public Optional<Retwot> getById(long id){
        return retwotRepository.findById(id);
    }

    public boolean userAlreadyRT(long id, long userid) {
        Optional<Retwot> retwot=retwotRepository.findByTwotAndUser(twotRepository.findById(id).get(),userRepository.findById(userid).get());
        if(retwot.isPresent()){
            return retwot.get().getUser().getId()==userid;
        }
        return false;
    }

    public boolean removeRT(long id, long userid) {
        Optional<Retwot> retwot=retwotRepository.findByTwotAndUser(twotRepository.findById(id).get(),userRepository.findById(userid).get());
        if(retwot.isPresent()){
            retwotRepository.delete(retwot.get());
            return true;
        }
        return false;
    }

    public boolean addRT(long id, long userid) {
        Optional<Retwot> retwot=retwotRepository.findByTwotAndUser(twotRepository.findById(id).get(),userRepository.findById(userid).get());
        if(!retwot.isPresent()) {
            retwotRepository.save(new Retwot(userRepository.findById(userid).get(), twotRepository.findById(id).get()));
            return true;
        }
        return false;
    }
}

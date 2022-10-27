package com.example.twotteur.services;

import com.example.twotteur.models.User;
import com.example.twotteur.models.WSToken;
import com.example.twotteur.repositories.WSTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Optional;
import java.util.Random;

@Service
public class WSTokenService {
    @Autowired
    private WSTokenRepository wsTokenRepository;

    public Optional<User> getbytoken(String token){
        Optional<User> result=Optional.empty();
        if(wsTokenRepository.findByToken(token).isPresent()) {
            result=Optional.of(wsTokenRepository.findByToken(token).get().getuser());
        }
        return result;
    }
    public Optional<String> gettoken(User user) {
        Optional<String> result = Optional.empty();
        if (wsTokenRepository.findByUser(user).isPresent()) {
            result = Optional.of(wsTokenRepository.findByUser(user).get().gettoken());
        }
        return result;
    }
    public void settoken(User user){
        String generatedString = randomstring();
        while(wsTokenRepository.findByToken(generatedString).isPresent()){
            generatedString=randomstring();
        }
        if(!wsTokenRepository.findByUser(user).isPresent()){
            wsTokenRepository.save(new WSToken(user,generatedString));
        }else{
            WSToken token=wsTokenRepository.findByUser(user).get();
            token.settoken(generatedString);
            wsTokenRepository.save(token);
        }
    }
    private String randomstring(){
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 30;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

    }
}

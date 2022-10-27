package com.example.twotteur.websocket;

import com.example.twotteur.models.User;
import com.example.twotteur.services.UserService;
import com.example.twotteur.services.WSTokenService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;

public class MessageHandler extends TextWebSocketHandler {

    @Autowired
    private WSTokenService wsTokenService;
    @Autowired
    private UserService userService;

    List<WebSocketSession> webSocketSessions = Collections.synchronizedList(new ArrayList<>());

    public MessageHandler(WSTokenService wsTokenService,UserService userService) {
        this.wsTokenService = wsTokenService;
        this.userService=userService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        webSocketSessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        webSocketSessions.remove(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

        super.handleMessage(session, message);
        String identtoken=getidenttoken(session);
        if(!identtoken.equals("")){
            String msg=message.getPayload().toString();
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(msg);
            if(wsTokenService.getbytoken(identtoken).isPresent()){
                User sender=wsTokenService.getbytoken(identtoken).get();
                if(json.containsKey("message") && json.containsKey("receiver")){
                    if(userService.getUserByusername(json.get("receiver").toString()).isPresent()){
                        User receiver=userService.getUserByusername(json.get("receiver").toString()).get();
                        if(!sender.getusername().equals(receiver.getusername())){
                            for (WebSocketSession webSocketSession : webSocketSessions) {
                                webSocketSession.sendMessage(new TextMessage(json.get("message").toString()+","+json.get("receiver").toString()));
                            }
                        }
                    }
                }
            }
        }
    }

    public String getidenttoken(WebSocketSession session){
        String cookies=session.getHandshakeHeaders().get(HttpHeaders.COOKIE).get(0);
        String[] cookiesarray=cookies.split("; ");
        String identtoken="";
        for(String cookie : cookiesarray){
            if(cookie.startsWith("WEBSOCKET-IDENT=")){
                identtoken=cookie.substring(16,46);
            }
        }
        return identtoken;
    }

}

package com.example.twotteur.websocket;

import com.example.twotteur.services.WSTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageHandler extends TextWebSocketHandler {

    @Autowired private WSTokenService tokenService;

    List<WebSocketSession> webSocketSessions = Collections.synchronizedList(new ArrayList<>());

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
        String cookies=session.getHandshakeHeaders().get(HttpHeaders.COOKIE).get(0);
        String[] cookiesarray=cookies.split("; ");
        String identtoken="0";
        for(String cookie : cookiesarray){
            if(cookie.startsWith("WEBSOCKET-IDENT=")){
                identtoken=cookie.substring(16,46);
            }
        }
        for (WebSocketSession webSocketSession : webSocketSessions) {
            webSocketSession.sendMessage(new TextMessage(identtoken));
        }
    }

}
package com.max.chat;

import com.max.chat.convert.MessageDecoder;
import com.max.chat.convert.MessageEncoder;
import com.max.chat.model.Message;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@ServerEndpoint
        (value = "/chat/{username}",
                encoders = MessageEncoder.class,
                decoders = MessageDecoder.class)
public class ChatServer {

    private static Map<String, String> users = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        System.out.println("Username " + username + " is connected!");
        users.put(session.getId(), username);
    }

    @OnMessage
    public void onMessage(Session session, Message message) {
        String user = users.get(session.getId());
        message.setFromUser(user);
        List<String> activeUsers = session.getOpenSessions().stream()
                .map(Session::getId)
                .map(users::get)
                .collect(Collectors.toList());
        message.setActiveUsers(activeUsers);
        // TODO display active users
        session.getOpenSessions().forEach(s -> broadcast(s, message));
    }

    @OnClose
    public void onClose(Session session) {
        users.remove(session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // TODO handle errors
    }

    private void broadcast(Session session, Message message) {
        try {
            session.getBasicRemote().sendObject(message);
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }
}

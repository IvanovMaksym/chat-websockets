package com.max.chat.convert;

import com.google.gson.Gson;
import com.max.chat.model.Message;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Message> {

    private static final Gson GSON = new Gson();

    @Override
    public String encode(Message message) throws EncodeException {
        return GSON.toJson(message);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}

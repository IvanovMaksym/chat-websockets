package com.max.chat.convert;

import com.google.gson.Gson;
import com.max.chat.model.Message;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageDecoder implements Decoder.Text<Message> {

    private static final Gson GSON = new Gson();

    @Override
    public Message decode(String s) throws DecodeException {
        return GSON.fromJson(s, Message.class);
    }

    @Override
    public boolean willDecode(String s) {
        return s != null;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}

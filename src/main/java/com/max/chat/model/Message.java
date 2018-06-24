package com.max.chat.model;

import java.util.List;
import java.util.Objects;

public class Message {

    private String fromUser;
    private String contentMessage;
    private List<String> activeUsers;

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getContentMessage() {
        return contentMessage;
    }

    public void setContentMessage(String contentMessage) {
        this.contentMessage = contentMessage;
    }

    public List<String> getActiveUsers() {
        return activeUsers;
    }

    public void setActiveUsers(List<String> activeUsers) {
        this.activeUsers = activeUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Message message = (Message) o;
        return Objects.equals(fromUser, message.fromUser) &&
                Objects.equals(contentMessage, message.contentMessage);
    }

    @Override
    public int hashCode() {

        return Objects.hash(fromUser, contentMessage);
    }
}

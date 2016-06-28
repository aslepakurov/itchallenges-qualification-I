package me.itchallenges.pathfinder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

/**
 * Date: 06/08/2016 6:10 PM
 */
@Component
public class MessageService implements IMessageService {

    @Autowired
    private ResourceBundle messages;

    @Override
    public String getMessage(String message, Object... args) {
        return String.format(messages.getString(message), args);
    }
}

package com.example.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public Message postMessage(Message message){
        
        
        if(!message.getMessageText().isEmpty() && message.getMessageText().length() < 255){

            return messageRepository.save(message);

        }
        
        return null;
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message getMessageById(int messageId){

        return messageRepository.findById(messageId).get();

    }

    public int deleteMessage(int messageId){
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        int rowsDeleted = 0;
        
        if(optionalMessage.isPresent()){
            messageRepository.deleteById(messageId);
            rowsDeleted = 1;
            return rowsDeleted;
        }

        return rowsDeleted;
    }

    public int updateMessage(int messageId, Message message){
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        int rowsUpdated = 0;

        if(optionalMessage.isPresent() && !message.getMessageText().isEmpty() && message.getMessageText().length() <= 255){
            Message updatedMessage = optionalMessage.get();
            updatedMessage.setMessageText(message.getMessageText());
            messageRepository.save(updatedMessage);
            rowsUpdated = 1;
            return rowsUpdated;

        } else{
            return rowsUpdated;
        }
    }

    public List<Message> getAllMessagesGivenAccountId(int postedBy){
        ArrayList<Message> allMessages = new ArrayList<>(Arrays.asList(messageRepository.findMessagesByPostedBy(postedBy)));
        return allMessages;
        
    }

}

package com.example.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

public class MessageService {

    MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public Message postMessage(Message message){
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message getMessageById(int messageId){

        Optional<Message> optionalMessage = messageRepository.findById(messageId);

        if(optionalMessage.isPresent()){
            return optionalMessage.get();
        } else{
            return null;
        }


    }

    public void deleteMessage(int messageId){
        messageRepository.deleteById(messageId);
    }

    public void updateMessage(int messageId, Message updatedMessage){
        Optional<Message> optionalMessage = messageRepository.findById(messageId);

        if(optionalMessage.isPresent()){

        } else{

        }
    }

    public List<Message> getAllMessagesGivenAccountId(int postedBy){
        ArrayList<Message> allMessages = new ArrayList<>(Arrays.asList(messageRepository.findMessagesByPostedBy(postedBy)));
        return allMessages;
        
    }

}

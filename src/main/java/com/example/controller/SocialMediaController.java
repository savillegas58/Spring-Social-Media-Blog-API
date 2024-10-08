package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    @Autowired
    AccountService accountService;
    @Autowired
    MessageService messageService;

    @PostMapping("/register")
    public @ResponseBody Account register(@RequestBody Account account){
        accountService.register(account);
        return account;
    }
    @PostMapping("/login")
    public @ResponseBody Account login(@RequestBody Account account){
        accountService.login(account);
        return account;
    }
    @PostMapping("/messages")
    public @ResponseBody Message postMessage(@RequestBody Message message){
        messageService.postMessage(message);
        return message;
    }
    @GetMapping("/messages")
    public @ResponseBody List<Message> getAllMessages(){
        messageService.getAllMessages();
        return null;
    }
    @GetMapping("/messages/{message_id}")
    public @ResponseBody Message getMessageById(@PathVariable int messageId){
        messageService.getMessageById(messageId);
        return null;
    }

    @DeleteMapping("/messages/{message_id}")
    public @ResponseBody int deleteMessage(@PathVariable int messageId){
        messageService.deleteMessage(messageId);
        return 0;
    }

    @PatchMapping("/messages/{message_id}")
    public @ResponseBody int updateMessage(@PathVariable int messageId, @RequestBody Message message){
        messageService.updateMessage(messageId, message);
        return 0;
    }

    @GetMapping("/accounts/{account_id}/messages")
    public @ResponseBody List<Message> getAllMessagesGivenAccountId(@PathVariable int accountId){
        messageService.getAllMessagesGivenAccountId(accountId);
        return null;
    }
}

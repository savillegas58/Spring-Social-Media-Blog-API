package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your
 * controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use
 * the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations.
 * You should
 * refer to prior mini-project labs and lecture materials for guidance on how a
 * controller may be built.
 */
@RestController
public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public @ResponseBody ResponseEntity<Account> register(@RequestBody Account account) {
        Account registeredAccount = accountService.register(account);

        if (registeredAccount != null) {
            return ResponseEntity.status(200).body(account);
        } else if (accountService.getAccountByUsername(account.getUsername()) != null) {
            return ResponseEntity.status(409).body(null);
        } else {
            return ResponseEntity.status(400).body(null);
        }

    }

    @PostMapping("/login")
    public @ResponseBody ResponseEntity<Account> login(@RequestBody Account account) {
        Account loggedInAccount = accountService.login(account);

        if (loggedInAccount != null) {
            return ResponseEntity.status(200).body(loggedInAccount);
        }
        return ResponseEntity.status(401).body(null);
    }

    @PostMapping("/messages")
    public @ResponseBody ResponseEntity<Message> postMessage(@RequestBody Message message) {
        Message postedMessage = messageService.postMessage(message);

        if (postedMessage != null) {
            return ResponseEntity.status(200).body(postedMessage);
        }
        return ResponseEntity.status(400).body(null);
    }

    @GetMapping("/messages")
    public @ResponseBody ResponseEntity<List<Message>> getAllMessages() {
        List<Message> allMessages = messageService.getAllMessages();
        return ResponseEntity.status(200).body(allMessages);
    }

    @GetMapping("/messages/{messageId}")
    public @ResponseBody ResponseEntity<Message> getMessageById(@PathVariable int messageId) {
        Message receivedMessage = messageService.getMessageById(messageId);
        return ResponseEntity.status(200).body(receivedMessage);
    }

    @DeleteMapping("/messages/{messageId}")
    public @ResponseBody ResponseEntity<Integer> deleteMessage(@PathVariable int messageId) {

        int deleted = messageService.deleteMessage(messageId);
        if (deleted == 1) {
            return ResponseEntity.status(200).body(1);
        } else {
            return ResponseEntity.status(200).body(null);
        }

    }

    @PatchMapping("/messages/{messageId}")
    public @ResponseBody ResponseEntity<Integer> updateMessage(@PathVariable int messageId,
            @RequestBody Message message) {
        int updated = messageService.updateMessage(messageId, message);
        if (updated == 1) {
            return ResponseEntity.status(200).body(1);
        } else {
            return ResponseEntity.status(400).body(null);
        }

    }

    @GetMapping("/accounts/{accountId}/messages")
    public @ResponseBody ResponseEntity<List<Message>> getAllMessagesGivenAccountId(@PathVariable int accountId) {
        List<Message> allMessages = messageService.getAllMessagesGivenAccountId(accountId);
        return ResponseEntity.status(200).body(allMessages);
    }
}

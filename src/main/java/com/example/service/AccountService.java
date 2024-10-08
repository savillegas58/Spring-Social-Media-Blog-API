package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account register(Account account){
        Optional<Account> optionalAccount = accountRepository.findAccountByUsername(account.getUsername());

        if(!optionalAccount.isPresent() && !account.getUsername().isEmpty() && account.getPassword().length() >= 4){
            return accountRepository.save(account);
        }
        
        return null;
    }

    public Account login(Account account){
        Optional<Account> optionalAccount = accountRepository.findAccountByUsername(account.getUsername());

        if(optionalAccount.isPresent()){
            Account registeredAccount = optionalAccount.get();

            if(registeredAccount.getUsername().equals(account.getUsername()) && registeredAccount.getPassword().equals(account.getPassword())){
                return registeredAccount;
            }
        }

        return null;
    }

    

}

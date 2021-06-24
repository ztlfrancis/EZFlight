package com.tianlin.booking.controller;

import com.tianlin.booking.Entity.Account;
import com.tianlin.booking.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("account")
public class AccountController {
    @Autowired
    AccountRepository accountRepository;
    @RequestMapping("update")
    @ResponseBody
    public String update(@RequestBody Account a){
        Optional<Account> acc = accountRepository.findById(a.getId());
        if(!acc.isPresent())return "no such account";
        Account account = acc.get();
        account.setUsername(a.getUsername());
        account.setPassword(a.getPassword());
        account.setAddress(a.getAddress());
        account.setUpdateAt(new java.sql.Date(new Date().getTime()));
        accountRepository.save(account);
        return "success";

    }
    @RequestMapping("findone")
    @ResponseBody
    public Optional<Account> selectOne(@RequestBody Account account){
        System.out.println(account);
        return accountRepository.findById(account.getId());
    }
}

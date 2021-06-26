package com.tianlin.booking.controller;

import com.tianlin.booking.entity.Account;
import com.tianlin.booking.repository.AccountRepository;
import com.tianlin.booking.entity.Account;
import com.tianlin.booking.repository.AccountRepository;
import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("account")
public class AccountController {
    @Autowired
    AccountRepository accountRepository;

    @RequestMapping("update")
    @ResponseBody
    public String update(@RequestBody Account a) {
        Optional<Account> acc = accountRepository.findById(a.getId());
        if (!acc.isPresent()||!acc.get().isExist()) return "account not exist";
        Optional<Account> byUsername = accountRepository.findByUsernameAndExist(a.getUsername(),true);
        if(byUsername.isPresent()&&byUsername.get().getId()!=acc.get().getId())return "duplicate username";
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
    public Optional<Account> selectOne(@RequestBody Account account) {
        System.out.println(account);
        return accountRepository.findById(account.getId());
    }

    @RequestMapping("findall")
    @ResponseBody
    public List<Account> selectOne() {
        return accountRepository.findAll();
    }

    @PostMapping("signup")
    @ResponseBody
    public String signup(@RequestBody Account acc) {

        Optional<Account> byUsername = accountRepository.findByUsernameAndExist(acc.getUsername(),true);
        if(byUsername.isPresent()&&byUsername.get().isExist())
        return "username exist";
        acc.setCreateAt(new java.sql.Date(new Date().getTime()));
        acc.setUpdateAt(new java.sql.Date(new Date().getTime()));
        acc.setExist(true);
        accountRepository.save(acc);
        return "success";
    }

    @PostMapping(value = "login")
    @ResponseBody
    public String login(@RequestBody Account acc,HttpServletResponse hsr){
        System.out.println(acc);
        System.out.println("login");
        Optional<Account> byUsername = accountRepository.findByUsernameAndExist(acc.getUsername(),true);
        if(!byUsername.isPresent()||!byUsername.get().isExist())return "wrong username";
        if(!byUsername.get().getPassword().equals(acc.getPassword()))return "worng password";
        Cookie cookie = new Cookie("username",acc.getUsername());
        cookie.setPath("/");
        hsr.addCookie(cookie);
        return "success";
    }
    @RequestMapping("delete")
    @ResponseBody
    public String delete(@RequestBody Account acc){
        Optional<Account> byId = accountRepository.findById(acc.getId());
        if(!byId.isPresent()||!byId.get().isExist())return "account not exist";
        Account act = byId.get();
        act.setExist(false);
        accountRepository.save(act);
        return "success";
    }

    @RequestMapping("test")
    public String test(){
        return "test";
    }

    @RequestMapping("logout")
    @ResponseBody
    public String logout(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        Cookie username= null;
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("username"))
                username = cookie;
        }
        if(username!=null){
            username.setMaxAge(0);
            username.setPath("/");
            response.addCookie(username);
            request.getSession().setAttribute("username","");
            return "logout!!!";
        }else{
            return "already logout";
        }



    }
}

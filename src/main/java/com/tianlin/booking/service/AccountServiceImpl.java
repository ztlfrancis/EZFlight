package com.tianlin.booking.service;
import com.tianlin.booking.entity.Account;

import com.tianlin.booking.repository.AccountRepository;
import com.tianlin.booking.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements  AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TicketRepository ticketRepository;
    @Override
    public String update(Account a) {
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

    @Override
    public Optional<Account> selectOne(Account account) {
        System.out.println(account);
        return accountRepository.findById(account.getId());
    }

    @Override
    public List<Account> selectAll() {
        return accountRepository.findAll();
    }

    @Override
    public String signup(Account acc) {

        Optional<Account> byUsername = accountRepository.findByUsernameAndExist(acc.getUsername(),true);
        if(byUsername.isPresent()&&byUsername.get().isExist())
            return "username exist";
        acc.setCreateAt(new java.sql.Date(new Date().getTime()));
        acc.setUpdateAt(new java.sql.Date(new Date().getTime()));
        acc.setExist(true);
        accountRepository.save(acc);
        return "success";
    }

    @Override
    public String login(Account acc, HttpServletResponse hsr) {
        Optional<Account> byUsername = accountRepository.findByUsernameAndExist(acc.getUsername(),true);
        if(!byUsername.isPresent()||!byUsername.get().isExist())return "wrong username";
        if(!byUsername.get().getPassword().equals(acc.getPassword()))return "worng password";
        Cookie cookie1 = new Cookie("id",byUsername.get().getId()+"");
        cookie1.setPath("/");
        hsr.addCookie(cookie1);
        Cookie cookie = new Cookie("username",acc.getUsername());
        cookie.setPath("/");
        hsr.addCookie(cookie);
        return "success";
    }

    @Override
    public String delete(Account acc) {
        Optional<Account> byId = accountRepository.findById(acc.getId());
        if(!byId.isPresent()||!byId.get().isExist())return "account not exist";
        Account act = byId.get();
        act.setExist(false);
        accountRepository.save(act);
        return "success";
    }


    @Override
    public String logout(HttpServletRequest request, HttpServletResponse response) {
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

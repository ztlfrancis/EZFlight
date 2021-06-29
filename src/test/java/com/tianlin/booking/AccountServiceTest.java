package com.tianlin.booking;

import com.tianlin.booking.entity.Account;
import com.tianlin.booking.repository.AccountRepository;
import com.tianlin.booking.service.AccountService;
import com.tianlin.booking.service.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;

@SpringBootTest
public class AccountServiceTest {
    @Autowired
    AccountService accountService;

    @Test
    public void selectOne(){
        Account account = new Account();
        account.setId(1);
        account.setExist(true);
        System.out.println(accountService.selectOne(account));
    }
    @Test
    public void selectAll(){
        System.out.println(   accountService.selectAll());

    }

    @Test
    public void signup(){
        Account account = new Account();
        account.setUsername("admin");
        account.setPassword("password");
        System.out.println(   accountService.signup(account));

    }

    @Test
    public void delete(){
        Account account = new Account();
        account.setId(1);
        System.out.println(accountService.delete(account));
    }
}

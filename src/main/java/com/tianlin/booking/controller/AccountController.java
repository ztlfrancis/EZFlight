package com.tianlin.booking.controller;
import com.tianlin.booking.entity.Account;
import com.tianlin.booking.entity.Ticket;
import com.tianlin.booking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("update")
    @ResponseBody
    public String update(@RequestBody Account a) {
        return accountService.update(a);
    }

    @RequestMapping("findone")
    @ResponseBody
    public Optional<Account> selectOne(@RequestBody Account account) {
        return accountService.selectOne(account);
    }

    @RequestMapping("findall")
    @ResponseBody
    public List<Account> selectAll() {
        return accountService.selectAll();
    }

    @PostMapping("signup")
    @ResponseBody
    public String signup(@RequestBody Account acc) {
        return  accountService.signup(acc);
    }

    @PostMapping(value = "login")
    @ResponseBody
    public String login(@RequestBody Account acc,HttpServletResponse hsr){
        return accountService.login(acc,hsr);
    }
    @RequestMapping("delete")
    @ResponseBody
    public String delete(@RequestBody Account acc){
        return accountService.delete(acc);
    }

    @RequestMapping("logout")
    @ResponseBody
    public String logout(HttpServletRequest request,HttpServletResponse response){
        return accountService.logout(request,response);
    }

}

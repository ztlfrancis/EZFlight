package com.tianlin.booking.service;
import com.tianlin.booking.entity.Account;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public interface AccountService {
     String update(Account a);
     Optional<Account> selectOne(Account a);
    List<Account> selectAll();
    String signup(Account acc);
    String login(Account acc, HttpServletResponse hsr);
    String delete( Account acc);
    String logout(HttpServletRequest request, HttpServletResponse response);
}

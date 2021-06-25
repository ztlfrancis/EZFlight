package com.tianlin.booking.repository;

import com.tianlin.booking.entity.Account;
import com.tianlin.booking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Integer> {

    Optional<Account> findByUsernameAndExist(String username, boolean exist);
}

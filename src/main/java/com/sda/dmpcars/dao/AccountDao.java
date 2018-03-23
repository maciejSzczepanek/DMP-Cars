package com.sda.dmpcars.dao;

import com.sda.dmpcars.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

public interface AccountDao extends CrudRepository<Account, Integer> {
    Account findAccountByLoginAndPassword(String login, String password);
}

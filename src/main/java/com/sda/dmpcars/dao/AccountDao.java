package com.sda.dmpcars.dao;

import com.sda.dmpcars.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountDao extends CrudRepository<Account,Integer> {
}

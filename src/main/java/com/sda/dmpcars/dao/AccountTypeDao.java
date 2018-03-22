package com.sda.dmpcars.dao;

import com.sda.dmpcars.model.AccountType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

public interface AccountTypeDao extends CrudRepository <AccountType, Integer> {
}

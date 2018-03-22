package com.sda.dmpcars.dao;

import com.sda.dmpcars.model.AccountDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

public interface AccountDetailDao extends CrudRepository <AccountDetail, Integer> {
}

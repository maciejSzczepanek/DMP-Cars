package com.sda.dmpcars.dao;

import com.sda.dmpcars.model.RegNumber;
import org.springframework.data.repository.CrudRepository;

public interface RegNumberDao extends CrudRepository <RegNumber, Integer> {
}

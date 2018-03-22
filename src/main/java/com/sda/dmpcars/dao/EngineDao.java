package com.sda.dmpcars.dao;

import com.sda.dmpcars.model.Engine;
import org.springframework.data.repository.CrudRepository;

public interface EngineDao extends CrudRepository<Engine, Integer> {
}

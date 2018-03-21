package com.sda.dmpcars.dao;

import com.sda.dmpcars.model.Color;
import org.springframework.data.repository.CrudRepository;

public interface ColorDao extends CrudRepository<Color, Integer> {
}

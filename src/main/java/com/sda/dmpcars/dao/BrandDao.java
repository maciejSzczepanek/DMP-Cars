package com.sda.dmpcars.dao;

import com.sda.dmpcars.model.Brand;
import org.springframework.data.repository.CrudRepository;

public interface BrandDao extends CrudRepository<Brand, Integer> {
}

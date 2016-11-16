package com.gft.dao;

import com.gft.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kamu on 8/23/2016.
 */
@Repository
public interface ProductDao extends PagingAndSortingRepository<Product, Long> {
}

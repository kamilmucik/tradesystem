package com.gft.dao;

import com.gft.dto.model.TransactionType;
import com.gft.model.Product;
import com.gft.model.ProductRate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by kamu on 2016-09-06.
 */
@Repository
public interface ProductRateDao extends CrudRepository<ProductRate, Long> {

    @Query("SELECT pr FROM ProductRate pr")
    Page<ProductRate> findAll(Pageable pageable);

    @Query("SELECT pr FROM ProductRate pr WHERE pr.product = :product")
    Optional<List<ProductRate>> findProductRate(@Param("product")Product product, Sort sort);
}

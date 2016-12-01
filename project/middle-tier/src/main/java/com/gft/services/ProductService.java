package com.gft.services;

import com.gft.dto.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Created by kamu on 2016-08-30.
 */
public interface ProductService {

    CompletableFuture<Page<Product>> findAll(Long page, Long size, List<String> sort, String direction);

    CompletableFuture<Map<Long, String>> getSupported();

    CompletableFuture<Product> getDetails(Long id);
}

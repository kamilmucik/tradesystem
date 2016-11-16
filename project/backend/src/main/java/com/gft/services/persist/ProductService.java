package com.gft.services.persist;

import com.gft.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by kamu on 8/23/2016.
 */
public interface ProductService {

    public Page<Product> findAll(int page, int size, List<String> sort, String direction);
}

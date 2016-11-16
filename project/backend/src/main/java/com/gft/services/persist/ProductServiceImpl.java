package com.gft.services.persist;

import com.gft.dao.ProductDao;
import com.gft.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kamu on 8/23/2016.
 */
@Service("productService")
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Page<Product> findAll(int page, int size, List<String> sort, String direction) {
        return productDao.findAll(
                new PageRequest(
                        page,
                        size,
                        Sort.Direction.fromString(direction),
                        sort.toArray(new String[0])
                )
        );
    }
}

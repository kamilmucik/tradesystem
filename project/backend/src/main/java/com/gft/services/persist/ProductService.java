package com.gft.services.persist;

import com.gft.dto.*;
import com.gft.dto.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by kamu on 8/23/2016.
 */
public interface ProductService {

    Page<com.gft.model.Product> findAll(int page, int size, List<String> sort, String direction);

    List<com.gft.model.Product> findAllToMatch();

    List<Product> findAll();

    Map<Long,String> getSupportedProduct(String envName);

    GetProductDetailsResponse getDetails(GetProductDetailsRequest request);

    GetProductListResponse getAll (GetProductListRequest request);


}

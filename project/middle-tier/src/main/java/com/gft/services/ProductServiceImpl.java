package com.gft.services;

import com.gft.dto.GetProductDetailsRequest;
import com.gft.dto.GetProductListRequest;
import com.gft.dto.model.Product;
import com.gft.messaging.MessageBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * Created by kamu on 2016-08-30.
 */
@Service
public class ProductServiceImpl implements ProductService {

    static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    MessageBase messageBase;

    @Override
    public CompletableFuture<Page<Product>> findAll(Long page, Long size, List<String> sort, String direction) {
        CompletableFuture<Page<Product>> completableFuture = new CompletableFuture<>();

        GetProductListRequest request = new GetProductListRequest(
                page,
                size,
                sort,
                direction,
                110);

        request.setCorrelationId(UUID.randomUUID().toString());
        messageBase.getFutures().put(request.getCorrelationId(), completableFuture);
        messageBase.send(request);

        return completableFuture;
    }

    @Override
    public CompletableFuture<Map<Long, String>> getSupported() {
        CompletableFuture<Map<Long, String>> completableFuture = new CompletableFuture<Map<Long, String>>();
        Map imp = new HashMap<Long, String>();
        messageBase.getJmsCustomers().forEach( customer->{
            imp.putAll(customer.getProducts());
        });
        completableFuture.complete(imp);
        return completableFuture;
    }

    @Override
    public CompletableFuture<Product> getDetails(Long id) {
        CompletableFuture<Product> completableFuture = new CompletableFuture<>();
        GetProductDetailsRequest request = new GetProductDetailsRequest(id);

        request.setCorrelationId(UUID.randomUUID().toString());
        messageBase.getFutures().put(request.getCorrelationId(), completableFuture);
        messageBase.send(request);

        return completableFuture;
    }
}

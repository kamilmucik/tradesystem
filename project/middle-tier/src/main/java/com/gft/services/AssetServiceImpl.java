package com.gft.services;

import com.gft.dto.CreateOrderRequest;
import com.gft.dto.GetActivOrderListRequest;
import com.gft.dto.GetUserAssetsRequest;
import com.gft.dto.model.ActiveOrder;
import com.gft.dto.model.TransactionType;
import com.gft.dto.model.UserAsset;
import com.gft.messaging.MessageBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Created by kamu on 2016-09-06.
 */
@Service
public class AssetServiceImpl implements AssetService {

    static final Logger LOG = LoggerFactory.getLogger(AssetServiceImpl.class);

    @Autowired
    MessageBase messageBase;

    @Override
    public CompletableFuture<Page<ActiveOrder>> findActiveOrders(Long page, Long size, List<String> sort, String direction) {
        CompletableFuture<Page<ActiveOrder>> completableFuture = new CompletableFuture<>();

        GetActivOrderListRequest request = new GetActivOrderListRequest(
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
    public CompletableFuture<Page<UserAsset>> findUserAssets(String login, Long page, Long size, List<String> sort, String direction) {
        CompletableFuture<Page<UserAsset>> completableFuture = new CompletableFuture<>();

        GetUserAssetsRequest request = new GetUserAssetsRequest(
                login,
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
    public CompletableFuture<ResponseEntity<?>> create(Long userId, Long productId, String type, BigDecimal price, Long volume) {
        CompletableFuture<ResponseEntity<?>> completableFuture = new CompletableFuture<>();

        CreateOrderRequest request = new CreateOrderRequest(
                userId,
                productId,
                TransactionType.valueOf(type),
                volume,
                price,
                110);

        request.setCorrelationId(UUID.randomUUID().toString());
        messageBase.getFutures().put(request.getCorrelationId(), completableFuture);
        messageBase.send(request);

        return completableFuture;
    }
}

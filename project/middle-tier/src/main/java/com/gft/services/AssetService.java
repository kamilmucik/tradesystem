package com.gft.services;

import com.gft.dto.model.ActiveOrder;
import com.gft.dto.model.UserAsset;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created by kamu on 2016-09-06.
 */
public interface AssetService {

    CompletableFuture<Page<ActiveOrder>> findActiveOrders(Long page, Long size, List<String> sort, String direction);

    CompletableFuture<Page<UserAsset>> findUserAssets(String login, Long page, Long size, List<String> sort, String direction);

    CompletableFuture<ResponseEntity<?>> create(Long userId, Long productId, String type, BigDecimal price, Long volume);
}

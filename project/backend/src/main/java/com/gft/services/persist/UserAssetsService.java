package com.gft.services.persist;

import com.gft.dto.*;
import com.gft.dto.model.ActiveOrder;
import com.gft.dto.model.UserAsset;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by kamu on 2016-09-06.
 */
public interface UserAssetsService {

    GetUserAssetsResponse getAssetsByUserEmail(GetUserAssetsRequest request);

    CreateOrderResponse createOrder(CreateOrderRequest request);

    GetActivOrderListResponse findAllActiveAssets (GetActivOrderListRequest request);

    List<ActiveOrder> findAllActiveAssets();

    List<com.gft.dto.model.UserAsset> getAssetsByUserEmail(String login);

    Map<Date, BigDecimal> getAssetsChartData(String login);

}

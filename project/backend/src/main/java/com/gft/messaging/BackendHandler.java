package com.gft.messaging;

import com.gft.dto.*;
import com.gft.dto.model.UserComplex;
import com.gft.dto.model.UserDetails;
import com.gft.services.persist.ProductService;
import com.gft.services.persist.UserAssetsService;
import com.gft.services.persist.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Controller;

/**
 * Created by kamu on 2016-08-31.
 */
@Controller
public class BackendHandler extends MessageListenerAdapter {

    static final Logger LOG = LoggerFactory.getLogger(BackendHandler.class);

    @Autowired
    UserService userService;

    @Autowired
    UserAssetsService assetService;

    @Autowired
    ProductService productService;

    @Autowired
    BackendSendService backendSendService;

    public void handleMessage(String text) {
        LOG.debug("Received: " + text);
    }

    public void handleMessage(LoginUserRequest request) {
        LoginUserResponse response = userService.login(request);
        response.setCorrelationId(request.getCorrelationId());
        backendSendService.send(response);
    }

    public void handleMessage(GetProductListRequest request) {
        GetProductListResponse response = productService.getAll(request);
        response.setCorrelationId(request.getCorrelationId());
        backendSendService.send(response);
    }

    public void handleMessage(GetUserAssetsRequest request) {
        GetUserAssetsResponse response = assetService.getAssetsByUserEmail(request);
        response.setCorrelationId(request.getCorrelationId());
        backendSendService.send(response);
    }

    public void handleMessage(GetUserDetailsRequest request) {
        GetUserDetailsResponse response = userService.getUserDetails(request);
        response.setCorrelationId(request.getCorrelationId());
        backendSendService.send(response);
    }

    public void handleMessage(GetProductDetailsRequest request) {
        GetProductDetailsResponse response = productService.getDetails(request);
        response.setCorrelationId(request.getCorrelationId());
        backendSendService.send(response);
    }

    public void handleMessage(CreateOrderRequest request) {
        CreateOrderResponse response = assetService.createOrder(request);
        response.setCorrelationId(request.getCorrelationId());
        backendSendService.send(response);
    }

    public void handleMessage(GetActivOrderListRequest request) {
        GetActivOrderListResponse response = assetService.findAllActiveAssets(request);
        response.setCorrelationId(request.getCorrelationId());
        backendSendService.send(response);
    }

    public void handleMessage(GetSocketPackageRequest request) {
        GetSocketPackageResponse response = new GetSocketPackageResponse();
        response.setCorrelationId(request.getCorrelationId());
        response.getProductList().addAll(productService.findAll());
        response.getActiveOrderList().addAll(assetService.findAllActiveAssets());

        request.getUserMap().forEach((k,v) ->{
            UserComplex userComplex = new UserComplex();
            userComplex.setUserDetails(userService.getUserDetails(v));
            userComplex.getAssetList().addAll(assetService.getAssetsByUserEmail(v));
            userComplex.getChart().putAll(assetService.getAssetsChartData(v));
            response.getUsers().put(k,userComplex);
        });

        backendSendService.send(response);
    }
}

package com.gft.services;

import com.gft.dto.model.ActiveOrder;
import com.gft.dto.model.UserAsset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Class represent <b>Zlecenia aktywne</b> table view and all behavior with included records
 * Created by kamu on 2016-09-06.
 */

@Controller
@RequestMapping("/asset")
public class AssetServiceController {

    static final Logger LOG = LoggerFactory.getLogger(AssetServiceController.class);

    @Autowired
    private AssetService assetService;

    @RequestMapping(value = "/active-orders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public DeferredResult<Page<ActiveOrder>> findActiveOrders(
            @RequestParam(value = "page", required = false, defaultValue = "0") Long page,
            @RequestParam(value = "size", required = false, defaultValue = "7") Long size,
            @RequestParam(value = "sort", required = false, defaultValue = "id") List<String> sort,
            @RequestParam(value = "direction", required = false, defaultValue = "desc") String direction) {
        DeferredResult<Page<ActiveOrder>> deferredResult = new DeferredResult<>();
        CompletableFuture<Page<ActiveOrder>> completableFuture = assetService.findActiveOrders(page,  size,  sort, direction);
        completableFuture.whenComplete((res, ex) -> {
            if (ex != null) {
                deferredResult.setErrorResult(ex);
            } else {
                deferredResult.setResult(res);
            }
        });
        return deferredResult;
    }

    @RequestMapping(value = "/user-held", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public DeferredResult<Page<UserAsset>> findUserAssets(
            @RequestParam(value = "login", required = false, defaultValue = "") String login,
            @RequestParam(value = "page", required = false, defaultValue = "0") Long page,
            @RequestParam(value = "size", required = false, defaultValue = "7") Long size,
            @RequestParam(value = "sort", required = false, defaultValue = "id") List<String> sort,
            @RequestParam(value = "direction", required = false, defaultValue = "desc") String direction) {
        DeferredResult<Page<UserAsset>> deferredResult = new DeferredResult<>();
        CompletableFuture<Page<UserAsset>> completableFuture = assetService.findUserAssets(login, page,  size,  sort, direction);

        completableFuture.whenComplete((res, ex) -> {
            if (ex != null) {
                deferredResult.setErrorResult(ex);
            } else {
                deferredResult.setResult(res);
            }
        });
        return deferredResult;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public DeferredResult<ResponseEntity<?>> create(
            @RequestParam(value = "userId", required = true, defaultValue = "0") Long userId,
            @RequestParam(value = "productId", required = false, defaultValue = "0") Long productId,
            @RequestParam(value = "type", required = false, defaultValue = "SALE") String type,
            @RequestParam(value = "price", required = false, defaultValue = "0.00") Double price,
            @RequestParam(value = "volume", required = false, defaultValue = "1") Long volume) {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<ResponseEntity<?>>();

        LOG.debug("create: {},{},{},{},{}" , userId, productId, type, price, volume);
        //TODO: make test of BigDecimal and constructor precision
        CompletableFuture<ResponseEntity<?>> completableFuture = assetService.create(userId, productId,  type, new BigDecimal(price), volume);

        completableFuture.whenComplete((res, ex) -> {
            if (ex != null) {
                deferredResult.setResult(new ResponseEntity<Void>(HttpStatus.BAD_REQUEST));
            } else {
                deferredResult.setResult(res);
            }
        });

        return deferredResult;
    }
}

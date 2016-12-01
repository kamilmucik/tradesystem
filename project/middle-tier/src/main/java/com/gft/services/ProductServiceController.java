package com.gft.services;

import com.gft.dto.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Class represent <b>Notowania</b> table view and all behavior with included records
 * Created by kamu on 2016-08-30.
 */
@Controller
@RequestMapping("/product")
public class ProductServiceController {

    static final Logger LOG = LoggerFactory.getLogger(ProductServiceController.class);

    @Autowired
    ProductService productService;

    /**
     * Method return page of all supported product.
     * @param page
     * @param size
     * @param sort
     * @param direction
     * @return
     */
    @RequestMapping(value = "/findall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public DeferredResult<Page<Product>> findAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") Long page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Long size,
            @RequestParam(value = "sort", required = false, defaultValue = "id") List<String> sort,
            @RequestParam(value = "direction", required = false, defaultValue = "asc") String direction) {
        DeferredResult<Page<Product>> deferredResult = new DeferredResult<>();
        CompletableFuture<Page<Product>> completableFuture = productService.findAll( page,  size,  sort, direction);
        //TODO: filer product with supported list
        completableFuture.whenComplete((res, ex) -> {
            if (ex != null) {
                deferredResult.setErrorResult(ex);
            } else {
                deferredResult.setResult(res);
            }
        });
        return deferredResult;
    }

    /**
     * Method return list of supported product.
     * Supported product is add to middle-tier after backend registered in queue.
     * @return
     */
    @RequestMapping(value = "/supported", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public DeferredResult<Map<Long, String>> getSupportedProduct() {
        DeferredResult<Map<Long, String>> deferredResult = new DeferredResult<Map<Long, String>>();
        CompletableFuture<Map<Long, String>> completableFuture = productService.getSupported();

        completableFuture.whenComplete((res, ex) -> {
            if (ex != null) {
                deferredResult.setErrorResult(ex);
            } else {
                deferredResult.setResult(res);
            }
        });
        return deferredResult;

    }

    /**
     * Method return detail information about product.
     * @param id
     * @return
     */
    @RequestMapping(value = "/details", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public DeferredResult<Product> productDetails(
            @RequestParam(value = "id", required = true, defaultValue = "") Long id) {
        DeferredResult<Product> deferredResult = new DeferredResult<>();
        CompletableFuture<Product> completableFuture = productService.getDetails(id);
        completableFuture.whenComplete((res, ex) -> {
            if (ex != null) {
                deferredResult.setErrorResult(ex);
            } else {
                deferredResult.setResult(res);
            }
        });
        return deferredResult;
    }
}

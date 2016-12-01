package com.gft.services;

import com.gft.dto.model.Asset;
import com.gft.dto.model.User;
import com.gft.dto.model.UserDetails;
import com.gft.messaging.MiddletierException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Class represent <b>Posiadane aktywa</b> table view, all behavior with included records and user information
 * Created by kamu on 8/12/2016.
 */
@Controller
@RequestMapping("/user")
public class UserServiceController {

    static final Logger LOG = LoggerFactory.getLogger(UserServiceController.class);

    @Autowired
    private UserService userService;

    /**
     * Method return page of realized user asset.
     * @param login
     * @param page
     * @param size
     * @param sort
     * @param direction
     * @return
     */
    @RequestMapping(value = "/assets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public DeferredResult<Page<Asset>> findAssets(
            @RequestParam(value = "login", required = true, defaultValue = "") String login,
            @RequestParam(value = "page", required = false, defaultValue = "0") Long page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Long size,
            @RequestParam(value = "sort", required = false, defaultValue = "id") List<String> sort,
            @RequestParam(value = "direction", required = false, defaultValue = "desc") String direction) {
        DeferredResult<Page<Asset>> deferredResult = new DeferredResult<>();
        CompletableFuture<Page<Asset>> completableFuture = userService.findAssets(login, page,  size,  sort, direction);

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
     * Log in user to trade system.
     * Success return 200 http status code.
     * Failure return 401 http status code.
     * @param login
     * @param password
     * @return
     * @throws MiddletierException
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public DeferredResult<ResponseEntity<?>> loginUser(
            @RequestParam(value = "login") String login,
            @RequestParam(value = "password") String password) throws  MiddletierException {
        DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>(500l);

        try {
            CompletableFuture<User> completableFuture = userService.loginUser(login, password);
            completableFuture.whenComplete((res, ex) -> {
                if (ex != null) {
                    MiddletierException me = (MiddletierException) ex;
                    //deferredResult.setErrorResult(me);
                    deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(me));
                } else {
                    //deferredResult.setResult(res);
                    deferredResult.setResult(ResponseEntity.ok(res));
                }
            });
        } catch (Exception e) {
            deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e));
        }

        return deferredResult;
    }

    /**
     * Get user details information.
     * @param login
     * @return
     */
    @RequestMapping(value = "/details", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public DeferredResult<UserDetails> userDetails(
            @RequestParam(value = "login", required = true, defaultValue = "") String login) {
        DeferredResult<UserDetails> deferredResult = new DeferredResult<>();
        CompletableFuture<UserDetails> completableFuture = userService.getUser(login);
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

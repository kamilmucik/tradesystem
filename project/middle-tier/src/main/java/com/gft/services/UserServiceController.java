package com.gft.services;

import com.gft.dto.User;
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

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created by kamu on 8/12/2016.
 */
@Controller
@RequestMapping("/user")
public class UserServiceController {

    static final Logger LOG = LoggerFactory.getLogger(UserServiceController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/findall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public DeferredResult<Page<User>> findAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") Long page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Long size,
            @RequestParam(value = "sort", required = false, defaultValue = "id") List<String> sort,
            @RequestParam(value = "direction", required = false, defaultValue = "asc") String direction) {
        DeferredResult<Page<User>> deferredResult = new DeferredResult<>();
        CompletableFuture<Page<User>> completableFuture = userService.findAll( page,  size,  sort, direction);

        completableFuture.whenComplete((res, ex) -> {
            if (ex != null) {
                deferredResult.setErrorResult(ex);
            } else {
                deferredResult.setResult(res);
            }
        });
        return deferredResult;
    }

    @RequestMapping(value = "/getuser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public DeferredResult<User> getUser(
            @RequestParam(value = "id", required = false, defaultValue = "0") Long id) {
        DeferredResult<User> deferredResult = new DeferredResult<>();
        CompletableFuture<User> completableFuture = userService.getUser(id);
        completableFuture.whenComplete((res, ex) -> {
            if (ex != null) {
                deferredResult.setErrorResult(ex);
            } else {
                deferredResult.setResult(res);
            }
        });
        return deferredResult;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE )
    public DeferredResult<User> createUser(@RequestBody User user) {
        LOG.debug("user: {}", user);
        DeferredResult<User> deferredResult = new DeferredResult<>();
        CompletableFuture<User> completableFuture = userService.updateUser(user);
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

package com.gft.services;

import com.gft.dto.GetUserAssetsRequest;
import com.gft.dto.GetUserDetailsRequest;
import com.gft.dto.LoginUserRequest;
import com.gft.dto.model.Asset;
import com.gft.messaging.MessageBase;
import com.gft.dto.model.User;
import com.gft.dto.model.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Created by kamu on 8/12/2016.
 */
@Service
public class UserServiceImpl implements UserService {

    static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired(required = false)
    private JmsTemplate jmsTemplate;

    @Autowired
    MessageBase messageBase;


    @Override
    public CompletableFuture<Page<Asset>> findAssets(String login, Long page, Long size, List<String> sort, String direction) {
        CompletableFuture<Page<Asset>> completableFuture = new CompletableFuture<>();
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
    public CompletableFuture<User> loginUser(String login, String password) {
        CompletableFuture<User> completableFuture = new CompletableFuture<>();
        LoginUserRequest request = new LoginUserRequest(
                login,
                password);

        request.setCorrelationId(UUID.randomUUID().toString());
        messageBase.getFutures().put(request.getCorrelationId(), completableFuture);
        messageBase.send(request);

        return completableFuture;
    }

    @Override
    public CompletableFuture<UserDetails> getUser(String login) {
        CompletableFuture<UserDetails> completableFuture = new CompletableFuture<>();
        GetUserDetailsRequest request = new GetUserDetailsRequest(
                login,
                110);

        request.setCorrelationId(UUID.randomUUID().toString());
        messageBase.getFutures().put(request.getCorrelationId(), completableFuture);
        messageBase.send(request);

        return completableFuture;
    }



}

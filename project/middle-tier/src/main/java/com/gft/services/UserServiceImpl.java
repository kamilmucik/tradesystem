package com.gft.services;

import com.gft.bean.Inventory;
import com.gft.bean.InventoryBuilder;
import com.gft.dto.Product;
import com.gft.messaging.MessageSender;
import com.gft.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by kamu on 8/12/2016.
 */
@Service
public class UserServiceImpl implements UserService {

    static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    MessageSender messageSender;

    @Override
    public CompletableFuture<Page<User>> findAll(Long page, Long size, List<String> sort, String direction) {
        CompletableFuture<Page<User>> completableFuture = new CompletableFuture<>();
        Inventory inventory = new InventoryBuilder(UUID.randomUUID().toString())
                .setObj(new User())
                .setMethod("findAll")
                .setJavaType("List")
                .build();

        messageSender.sendMessage(completableFuture,inventory);
        return completableFuture;
    }

    @Override
    public CompletableFuture<User> getUser(Long userId) {
        CompletableFuture<User> completableFuture = new CompletableFuture<>();
        Inventory inventory = new InventoryBuilder(UUID.randomUUID().toString())
                .setObj(new User())
                .setMethod("findOne")
                .setJavaType("Object")
                .addCriteria("id", userId)
                .build();

        messageSender.sendMessage(completableFuture,inventory);
        return completableFuture;
    }

    @Override
    public CompletableFuture<User> updateUser(User user) {
        CompletableFuture<User> completableFuture = new CompletableFuture<>();
        Inventory inventory = new InventoryBuilder(UUID.randomUUID().toString())
                .setObj(user)
                .setMethod("update")
                .setJavaType("com.gft.dto.User")
                .build();

        messageSender.sendMessage(completableFuture,inventory);
        return completableFuture;
    }

    @Override
    public CompletableFuture<User> deleteUser(Long userId) {
        return null;
    }

}

package com.gft.services;

import com.gft.dto.Product;
import com.gft.dto.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Interface declare method for user jms
 * Created by kamu on 8/12/2016.
 */
public interface UserService {

    public CompletableFuture<User> getUser(Long userId);

    public CompletableFuture<Page<User>> findAll(Long page, Long size, List<String> sort, String direction);

    public CompletableFuture<User> updateUser(User user);

    public CompletableFuture<User> deleteUser(Long userId);
}

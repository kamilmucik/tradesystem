package com.gft.services;

import com.gft.dto.model.Asset;
import com.gft.dto.model.User;
import com.gft.dto.model.UserDetails;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Interface declare method for user jms
 * Created by kamu on 8/12/2016.
 */
public interface UserService {

    CompletableFuture<Page<Asset>> findAssets(String login, Long page, Long size, List<String> sort, String direction);

    CompletableFuture<User> loginUser(String login, String password);

    CompletableFuture<UserDetails> getUser(String login);
}

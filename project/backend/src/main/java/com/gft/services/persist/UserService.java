package com.gft.services.persist;

import com.gft.dto.User;

import java.util.List;

/**
 * Created by kamu on 8/16/2016.
 */
public interface UserService {

    public List<User> findAll();

    public User findOne(Long id);

    public User update(User u);


}

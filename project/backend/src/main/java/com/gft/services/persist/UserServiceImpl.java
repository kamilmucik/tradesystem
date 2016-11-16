package com.gft.services.persist;

import com.gft.dao.UsersDao;
import com.gft.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamu on 8/16/2016.
 */
@Service("userService")
@Transactional()
public class UserServiceImpl implements UserService {

    static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UsersDao dao;

    @Override
    public User update(User user){
        LOG.debug("update: {}", user);
        com.gft.model.User modelUser = new com.gft.model.User();

        dao.save(modelUser);
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<User>();
        for (com.gft.model.User u : dao.findAll()) {
            users.add(new User(u.getFirstName(),u.getPassword()));
        }
        return users;
    }

    @Override
    public User findOne(Long id) {
        com.gft.model.User u = dao.findOne(id);
        return new User(u.getFirstName(),u.getPassword());
    }
}

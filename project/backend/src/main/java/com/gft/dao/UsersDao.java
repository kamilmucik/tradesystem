package com.gft.dao;

import com.gft.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kamu on 8/17/2016.
 */
@Repository
public interface UsersDao extends CrudRepository<User, Long> {
}

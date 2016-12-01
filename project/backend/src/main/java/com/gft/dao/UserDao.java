package com.gft.dao;

import com.gft.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by kamu on 8/17/2016.
 */
@Repository
public interface UserDao extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:email) AND LOWER(u.password) = LOWER(:password)")
    User find(@Param("email") String email, @Param("password") String password);

    @Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:email)")
    User find(@Param("email") String email);

}

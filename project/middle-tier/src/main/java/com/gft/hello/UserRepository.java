package com.gft.hello;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by e-klbi on 2016-06-28.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}

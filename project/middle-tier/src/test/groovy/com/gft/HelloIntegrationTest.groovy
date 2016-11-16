package com.gft

import com.gft.hello.User
import com.gft.hello.UserController
import com.gft.hello.UserRepository
import org.junit.Ignore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.WebIntegrationTest
import spock.lang.Specification
/**
 * Created by Kamil Bemowski on 2016-06-30.
 */
@SpringApplicationConfiguration(classes = Configuration.class)
@WebIntegrationTest
@Ignore
class HelloIntegrationTest extends Specification {

    @Autowired
    UserController userService
    @Autowired
    UserRepository repository

    def "hello world return"() {
        setup:
        def hello

        when:
        hello = userService.hello()

        then:
        hello == "Hello World!"

    }

    def "insert new user"() {
        setup:
        def user = new User();
        user.firstName = "Jan"
        user.lastName = "Kowalski"

        when:
        userService.createUser(user);

        then:

        def newUser = repository.findOne(1L)
        newUser.firstName == user.firstName
        newUser.lastName == user.lastName

    }

}

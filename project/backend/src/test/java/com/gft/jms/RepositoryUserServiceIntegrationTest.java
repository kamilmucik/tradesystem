package com.gft.jms;

import com.gft.ConfigApp;
import com.gft.dao.UserDao;
import com.gft.dto.LoginUserRequest;
import com.gft.services.persist.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertEquals;

/**
 * Created by kamu on 2016-09-01.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ConfigApp.class)
@WebIntegrationTest

public class RepositoryUserServiceIntegrationTest {

    @Autowired
    UserService userService;

    @Autowired
    UserDao userDaoMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testContextSetup(){
        Assert.assertNotNull("UserService is null", userService);
        Assert.assertNotNull("UserDao is null", userDaoMock);
    }


    private LoginUserRequest createDTO(String login, String password) {
        LoginUserRequest request = new LoginUserRequest(login, password);
        request.setCorrelationId("correlationIdvalue");
        return request;
    }

}

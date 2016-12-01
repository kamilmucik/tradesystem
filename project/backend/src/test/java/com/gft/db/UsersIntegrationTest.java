package com.gft.db;

import com.gft.ConfigApp;
import com.gft.dao.UserDao;
import com.gft.dto.LoginUserRequest;
import com.gft.dto.LoginUserResponse;
import com.gft.model.User;
import com.gft.services.persist.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by kamu on 2016-09-05.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ConfigApp.class)
public class UsersIntegrationTest {

    private static final Logger LOG = LoggerFactory.getLogger(UsersIntegrationTest.class);

    private static final String USER_LOGIN = "k.mucik@gft.com";
    private static final String USER_PASSWORD = "haslo";

    UserServiceImpl userService;

    UserDao usersDao;

    User user;

    LoginUserRequest request;

    LoginUserResponse response;

    @Before
    public void setupMock() {
        userService = new UserServiceImpl();
        user = mock(User.class);
        usersDao = mock(UserDao.class);
        request = mock(LoginUserRequest.class);
        response = mock(LoginUserResponse.class);
        userService.setDao(usersDao);
    }

    @Test
    public void testContextSetup(){
        Assert.assertNotNull("UserService is null", userService);
        Assert.assertNotNull("UserDao is null", usersDao);
    }

    @Test
    public void loginTest(){
        when(usersDao.find(USER_LOGIN,USER_PASSWORD)).thenReturn(user);
        request.setLogin(USER_LOGIN);
        request.setPassword(USER_PASSWORD);

        given(userService.login(request).getNotificationCode()).willReturn(new Integer(2));
    }

}

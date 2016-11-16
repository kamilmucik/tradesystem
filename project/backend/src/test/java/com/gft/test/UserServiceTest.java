package com.gft.test;

import com.gft.BackendApp;
import com.gft.dao.UsersDao;
import com.gft.model.User;
import com.gft.services.persist.UserService;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.doNothing;

/**
 * Created by kamu on 8/17/2016.
 */
//@ActiveProfiles("test")
//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BackendApp.class)

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UsersDao userRepository;

    @Mock
    private User user;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    //@Ignore
    public void createNewAccount() {
        System.out.println("createNewAccount");
        Assert.assertNotNull(userService);

        for (User u :userRepository.findAll()){
            System.out.println(u);
        }

        //doNothing().when(mockAuditService).notifyDelete(accountId);

        /*User tmpUser = new User();

        tmpUser.setFirstName("Jan");
        tmpUser.setLastName("Nowak");
        tmpUser.setEmail("jan.nowak@gft.pl");
        tmpUser.setAmount(BigDecimal.valueOf(1255.50) );
        tmpUser.setPassword("haslo");

        //userDao.insertInto(tmpUser);

        userService.add(tmpUser);

        System.out.println("tmpUser: " + tmpUser);
        */
    }
}

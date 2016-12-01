package com.gft.services.persist;

import com.gft.ConfigApp;
import com.gft.dao.ProductDao;
import com.gft.dao.UserAssetsDao;
import com.gft.dao.UserDao;
import com.gft.dto.*;
import com.gft.dto.model.Product;
import com.gft.dto.model.TransactionType;
import com.gft.model.User;
import com.gft.model.UserAsset;
import junit.framework.Assert;
import org.junit.Assume;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by kamu on 2016-09-14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ConfigApp.class)
public class UserAssetsServiceTest {
    private static final Logger LOG = LoggerFactory.getLogger(UserAssetsServiceTest.class);

    @Autowired
    UserAssetsService service;

    @Autowired
    UserAssetsDao dao;

    @Autowired
    private UserDao userDao;

    @Test
    public void createAssetOrder(){
        Assume.assumeNotNull("Service is null" ,service);
        CreateOrderRequest request = prepare();
        Assume.assumeNotNull("Asset is null" ,request);

        CreateOrderResponse response = service.createOrder(request);
        Assert.assertNotNull(response.getId());
        Assert.assertTrue(response.getId() > 0);
        Assert.assertEquals(request.getCorrelationId(),response.getCorrelationId());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void createAssetOrderDao(){
        UserAsset userAsset = new UserAsset();
        User user = userDao.findOne(1l);
        Assert.assertNotNull(user);
        userAsset.setContractor1(user);
        userAsset.setVolume(2l);
        userAsset.setPrice(new BigDecimal(12));
        userAsset.setTransactionType(TransactionType.BUY);

        dao.save(userAsset);
        Assert.assertNotNull(userAsset.getId());
        Assert.assertTrue(userAsset.getId() > 0);
    }

    @Test
    public void findAllActiveAssetsTest(){
        Assume.assumeNotNull("Service is null" ,service);
        List<String> sort = new ArrayList<String>();
        sort.add("id");
        GetActivOrderListRequest request = new GetActivOrderListRequest(
                2l,
                7l,
                sort,
                "asc",
                110);
        Assert.assertNotNull(request);

        GetActivOrderListResponse response = service.findAllActiveAssets(request);
        //Assert.assertNotNull(response.getAssetList());
        //Assert.assertTrue(response.getAssetList().size() > 0);
    }


    @Test
    public void findUserAssetsTest(){
        Assume.assumeNotNull("Service is null" ,service);
        List<String> sort = new ArrayList<String>();
        sort.add("id");
        GetUserAssetsRequest request = new GetUserAssetsRequest(
                "k.mucik@gft.com",
                0l,
                7l,
                sort,
                "asc",
                110);
        Assert.assertNotNull(request);

        GetUserAssetsResponse response = service.getAssetsByUserEmail(request);
        //Assert.assertNotNull(response.getAssetList());
        //Assert.assertTrue(response.getAssetList().size() > 0);
    }

    public static CreateOrderRequest prepare(){
        CreateOrderRequest request = new CreateOrderRequest(1l,1l, TransactionType.BUY, 10l, new BigDecimal(123.12));
        request.setCorrelationId(UUID.randomUUID().toString());

        return request;
    }


}

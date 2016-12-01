package com.gft.db;

import com.gft.ConfigApp;
import com.gft.dao.ProductDao;
import com.gft.dto.GetProductListRequest;
import com.gft.dto.GetProductListResponse;
import com.gft.model.Product;
import com.gft.services.persist.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by kamu on 2016-09-12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ConfigApp.class)
public class ProductIntegrationTest {

    private static final Logger LOG = LoggerFactory.getLogger(ProductIntegrationTest.class);

    private String[] products =  {"gold","silver","kasza"};

    @Autowired
    ProductService service;

    @Autowired
    ProductDao dao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testContextSetup(){
        Assert.assertNotNull("ProductService is null", service);
        Assert.assertNotNull("ProductDao is null", dao);
    }

    @Test
    public void findByEnvNameInDaoTest(){
        Set<String> params = new HashSet<String>();
        Arrays.stream(products).forEach(v ->{
            params.add(v);
        });
        Optional<List<Product>> possible = dao.findByEnvNameIn(params);

        //return not null instance
        if (possible.isPresent()){
            List<Product> list = possible.get();
            list.forEach( val ->{
                Assert.assertNull( val.getPrice());
            });
            Assert.assertEquals(list.size(), 2);
        }
    }

    @Test
    public void findAllProductServiceTest(){
        String correlationId = UUID.randomUUID().toString();
        List<String> criteria = new ArrayList<String>();
        criteria.add("id");
        GetProductListRequest request = new GetProductListRequest(0l, 10l, criteria, "asc", 110);
        request.setCorrelationId(correlationId);
        GetProductListResponse response = service.getAll(request);
        Assert.assertNotNull(response);
    }


}

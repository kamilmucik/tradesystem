package com.gft.services;

import com.gft.Configuration;
import com.jayway.restassured.RestAssured;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by kamu on 2016-09-16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Configuration.class, loader = SpringApplicationContextLoader.class)
@WebAppConfiguration
//@Ignore
public class OrderServiceControllerTest {

    private static final Logger LOG = LoggerFactory.getLogger(OrderServiceControllerTest.class);


    @Test
    public void exampleTest(){

        RestAssured.baseURI = "http://localhost:8090/order/version";

        given()
                .queryParam("ID", 1)
                .when()
                .get("1")
                .statusCode();


    }




}

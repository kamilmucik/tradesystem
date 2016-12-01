package com.gft;

import com.gft.dto.model.CustomCustomer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by kamu on 8/10/2016.
 */
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@PropertySource({
        "classpath:application.properties",
        "classpath:filter.properties"
})
@ComponentScan("com.gft")
public class ConfigApp {

    @Value("${env.instance.number}")
    private String instanceIumber;

    @Value("${env.instance.products}")
    private String products;


    @Bean
    public CustomCustomer customCustomer() {
        System.out.println("products: " + products);
        return new CustomCustomer("MT.TO.BACK" + instanceIumber + ".QUEUE",products.split(","));
    }

}

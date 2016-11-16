package com.gft.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * Created by kamu on 8/22/2016.
 */
@Configuration
public class RepositoryConfig {

    @Autowired
    private ApplicationContext applicationContext;

    public <T> T lookupBean(String beanName, Class<T> clazz) throws Exception {
        T bean = applicationContext.getBean(beanName, clazz);
        if (bean == null) {
            throw new Exception("Mandatory Spring bean '" + beanName + "' missing! Aborting");
        }
        return bean;
    }
}

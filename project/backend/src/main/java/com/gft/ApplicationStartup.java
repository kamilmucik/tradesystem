package com.gft;

import com.gft.dto.CustomCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by kamu on 8/11/2016.
 */
@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private CustomCustomer customer;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        return;
    }


}

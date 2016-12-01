package com.gft;

import com.gft.messaging.BackendSendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by kamu on 2016-09-07.
 */
@EnableScheduling
@Component
public class RegisterTask {

    static final Logger LOG = LoggerFactory.getLogger(RegisterTask.class);

    private static boolean needToRunStartupMethod = true;

    @Value("${env.instance.products:}")
    String products;

    @Value("${env.instance.number}")
    Long instance;

    @Autowired
    BackendSendService backendSendService;

    @Scheduled(fixedRate = 30000)
    public void keepAlive() {
        //TODO send request after any 30s to refresh information with backend connection
        if (needToRunStartupMethod) {
            runOnceOnlyOnStartup();
            needToRunStartupMethod = false;
        }
    }

    public void runOnceOnlyOnStartup() {
        backendSendService.backendRegister(instance, products);
    }
}

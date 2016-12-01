package com.gft.jms;

import com.gft.ConfigApp;
import com.gft.dto.LoginUserRequest;
import com.gft.dto.LoginUserResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.util.UUID;

/**
 * Created by kamu on 2016-09-01.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ConfigApp.class)
@WebIntegrationTest
public class UserIntegrationTest {

    private static final String USER_LOGIN = "k.mucik@gft.com";
    private static final String USER_PASSWORD = "haslo";

    private static final String QUEUE_INPUT = "BACK.TO.MT.QUEUE";
    private static final String QUEUE_OUTPUT = "MT.TO.BACK1.QUEUE";

    @Autowired
    private JmsTemplate jmsTemplate;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testContextSetup(){
        Assert.assertNotNull("JmsTemplate is null", jmsTemplate);
    }

    @Test
    @Ignore
    public void testUserLoginMessage() throws JMSException {
        String correlationId = UUID.randomUUID().toString();
        LoginUserRequest request = createDTO(USER_LOGIN, USER_PASSWORD);
        request.setCorrelationId(correlationId);

        jmsTemplate.send(QUEUE_OUTPUT, new MessageCreator(){
            @Override
            public Message createMessage(Session session) throws JMSException{
                ObjectMessage objectMessage = session.createObjectMessage(request);
                objectMessage.setJMSCorrelationID(correlationId);
                return objectMessage;
            }
        });
        Message receivedMessage=jmsTemplate.receive(QUEUE_INPUT);
        ObjectMessage msg = (ObjectMessage)receivedMessage;
        System.out.println("Message Received :"+ ((LoginUserResponse)msg.getObject()) );

        Assert.assertNotNull("LoginUserResponse[NotificationCode] is null", ((LoginUserResponse)msg.getObject()).getNotificationCode());
    }


    private LoginUserRequest createDTO(String login, String password) {
        LoginUserRequest request = new LoginUserRequest(login, password);
        return request;
    }
}

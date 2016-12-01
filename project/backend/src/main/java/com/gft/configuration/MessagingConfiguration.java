package com.gft.configuration;


import java.util.Arrays;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;

import com.gft.messaging.BackendHandler;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;
import org.springframework.jms.support.destination.BeanFactoryDestinationResolver;

/**
 * Created by kamu on 8/18/2016.
 */
@Configuration
@EnableJms
public class MessagingConfiguration {

    private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";
    private static final String ORDER_RESPONSE_QUEUE = "BACK.TO.MT.QUEUE";

    @Value("${env.broker.url:}")
    String brokerUrl;

    @Autowired
    BackendHandler backendHandler;

    @Autowired
    BeanFactory springContextBeanFactory;

    @Autowired
    Destination inputQueueDestination;

    @Autowired
    Destination outputQueueDestination;

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
        c.setIgnoreUnresolvablePlaceholders(true);
        c.setNullValue("");
        return c;
    }

    @Bean
    public ConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        String brocker = (brokerUrl == null)?DEFAULT_BROKER_URL:brokerUrl;
        connectionFactory.setBrokerURL(brocker);
        connectionFactory.setTrustedPackages(Arrays.asList("*"));
        return connectionFactory;
    }

    @Bean
    public ConnectionFactory cachingConnectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setTargetConnectionFactory(connectionFactory());
        connectionFactory.setSessionCacheSize(10);
        return connectionFactory;
    }

    @Bean
    public MessageListenerContainer getContainer(){
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setDestination(inputQueueDestination);
        container.setMessageListener(backendHandler);
        return container;
    }

    @Bean(name="jmsTemplate")
    public JmsTemplate jmsTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestinationName(ORDER_RESPONSE_QUEUE);
        return template;
    }

    @Bean
    public DefaultJmsListenerContainerFactory containerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setDestinationResolver(new BeanFactoryDestinationResolver(springContextBeanFactory));
        factory.setConcurrency("3-10");
        return factory;
    }

    @Bean
    public Destination inputQueueDestination(@Value("${spring.queue.mttoback.name}") String queueName, @Value("${env.instance.number}") Integer instance)
            throws JMSException {
        return new ActiveMQQueue(String.format(queueName, instance));
    }

    @Bean
    public Destination outputQueueDestination(@Value("${spring.queue.backtomt.name}") String queueName)
            throws JMSException {
        return new ActiveMQQueue(queueName);
    }

    @Bean
    public MessageConverter converter(){
        return new SimpleMessageConverter();
    }

}

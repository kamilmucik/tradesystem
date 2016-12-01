package com.gft;

//import com.gft.hello.UserWebSocket;
import com.gft.hello.UserWebSocket;
import com.gft.messaging.MessageBase;
import com.gft.socket.rate.RateWebSocketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.PerConnectionWebSocketHandler;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

/**
 * Created by e-klbi on 2016-06-28.
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.gft")
@EnableWebSocket
public class Configuration implements WebSocketConfigurer {

    private static Logger LOG = LoggerFactory.getLogger(Configuration.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Configuration.class, args);
    }

    @Autowired
    MessageBase messageBase;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(userWebSocket(), "/ws/").setAllowedOrigins("*").withSockJS();
        registry.addHandler(rateWebSocketHandler(), "/rate").setAllowedOrigins("*").withSockJS();
        registry.addHandler(ratingWebSocketHandler(), "/rating").setAllowedOrigins("*").withSockJS();
    }

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(8192);
        container.setMaxBinaryMessageBufferSize(8192);
        return container;
    }

    @Bean
    public WebSocketHandler userWebSocket() {
        return new UserWebSocket();
    }

    @Bean
    public RateWebSocketHandler rateWebSocketHandler() {
        return new RateWebSocketHandler();
    }

    @Bean
    public WebSocketHandler ratingWebSocketHandler() {
        return new PerConnectionWebSocketHandler(RateWebSocketHandler.class);
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

}
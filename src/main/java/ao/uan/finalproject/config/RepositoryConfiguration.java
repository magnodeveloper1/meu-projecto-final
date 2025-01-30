package ao.uan.finalproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ao.uan.finalproject.handler.UtilizadorHandler;

@Configuration
public class RepositoryConfiguration {
    
    @Bean
    UtilizadorHandler userEventHandler() {
        return new UtilizadorHandler();
    }
}

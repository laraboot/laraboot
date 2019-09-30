package cn.laraboot.config;

import cn.laraboot.framework.auth.AuthManager;
import cn.laraboot.framework.contracts.auth.GuardProvider;
import cn.laraboot.framework.contracts.kernel.SecretProvider;
import cn.laraboot.framework.encryption.EncrypterManager;
import cn.laraboot.framework.hashing.HashManager;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@Data
@ConfigurationProperties(prefix = "app")
public class AppConfig implements SecretProvider {
    /**
     * app密钥
     */
    private String secret = "apiboot";

    @Override
    public String get() {
        return secret;
    }

    @Bean(name = "applicationEventMulticaster")
    public SimpleApplicationEventMulticaster simpleApplicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return eventMulticaster;
    }

    @Bean
    public AuthManager authManager(GuardProvider provider){
        return new AuthManager(provider);
    }

    @Bean
    public HashManager hashManager(SecretProvider provider){
        return new HashManager(provider);
    }

    @Bean
    public EncrypterManager encrypterManager(SecretProvider provider){
        return new EncrypterManager(provider);
    }

}

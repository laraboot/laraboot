package cn.laraboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import cn.laraboot.app.http.middlewares.cross.*;

@Configuration
public class CrossConfig {

    @Bean
    public CrossMiddleware crossMiddleware(CrossProvider provider){
        return new CrossMiddleware(provider);
    }

    @Bean
    public CrossProvider crossProvider() {
        return () -> new Crosses()
                .add("localhost:8000")
                .add(new Cross("localhost:8080", new HttpMethod[]{
                        HttpMethod.GET,
                        HttpMethod.POST,
                }));
    }
}

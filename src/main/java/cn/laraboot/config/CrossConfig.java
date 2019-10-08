package cn.laraboot.config;

import cn.laraboot.framework.http.middlewares.cross.Cross;
import cn.laraboot.framework.http.middlewares.cross.CrossMiddleware;
import cn.laraboot.framework.http.middlewares.cross.CrossProvider;
import cn.laraboot.framework.http.middlewares.cross.Crosses;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

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

package cn.laraboot.config;

import cn.laraboot.app.models.User;
import cn.laraboot.auth.DatabaseUserProvider;
import cn.laraboot.auth.guard.JwtGuard;
import cn.laraboot.contracts.auth.AuthenticateAble;
import cn.laraboot.contracts.auth.Guard;
import cn.laraboot.contracts.auth.GuardProvider;
import com.auth0.jwt.algorithms.Algorithm;
import cn.laraboot.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "auth")
public class AuthConfig {

    private String defaultGuard = "jwt";

    private String jwtSecret = "apiboot";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DatabaseUserProvider<? extends AuthenticateAble> databaseUserProvider;

    @Bean
    public DatabaseUserProvider<User> userProvider() {
        return new DatabaseUserProvider<>(userRepository);
    }

    @Bean
    public GuardProvider guards() {
        return new GuardProvider() {
            @Override
            public Map<String, Guard> guards() {
                Map<String, Guard> guards = new HashMap<>();

                // 添加guard
                guards.put("jwt", new JwtGuard(databaseUserProvider, Algorithm.HMAC256(jwtSecret)));

                return guards;
            }

            @Override
            public String defaultGuard() {
                return defaultGuard;
            }
        };
    }
}

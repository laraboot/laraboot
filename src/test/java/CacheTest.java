import cn.laraboot.ApiApplication;
import cn.laraboot.app.models.User;
import cn.laraboot.app.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class CacheTest {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testStringRedisTemplate() {
        stringRedisTemplate.opsForValue().set("redisTemplate", "redisTemplate");
        System.out.println(stringRedisTemplate.opsForValue().get("redisTemplate"));
    }

    @Test
    public void testRedisTemplate() {
        User user = userRepository.findUserByName("测试name");
        redisTemplate.opsForValue().set("user.测试name", user);
        System.out.println(redisTemplate.opsForValue().get("user.测试name"));
    }

}
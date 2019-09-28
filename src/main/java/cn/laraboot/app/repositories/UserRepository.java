package cn.laraboot.app.repositories;

import cn.laraboot.app.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    public User findUserByName(String name);
}

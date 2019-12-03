package cn.laraboot.app.repositories;

import cn.laraboot.app.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    public User findUserByName(String name);

    public Optional<User> findById(Long id);
}

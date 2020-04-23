package io.javabrains.springsecurityjpa.repostitories;

import io.javabrains.springsecurityjpa.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByName(String userName);
}

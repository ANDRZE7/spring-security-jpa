package io.javabrains.springsecurityjpa.repostitories;

import io.javabrains.springsecurityjpa.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}

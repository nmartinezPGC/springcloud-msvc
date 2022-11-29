package org.sdn.springcloud.msvc.users.repositories;

import org.sdn.springcloud.msvc.users.models.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoty extends CrudRepository<UserEntity, Long> {
    // Code Of class
    Optional<UserEntity> findByEmail(String email);

    @Query("select u from UserEntity u where u.email = ?1")
    Optional<UserEntity> findByEmailQuery(String email);

    boolean existsByEmail(String email);
}
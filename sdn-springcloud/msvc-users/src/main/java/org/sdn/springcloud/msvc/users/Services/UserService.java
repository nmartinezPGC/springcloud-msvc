package org.sdn.springcloud.msvc.users.Services;

import org.sdn.springcloud.msvc.users.models.entity.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<UserEntity> findAll();
    Optional<UserEntity> getById(Long id);
    UserEntity saveUser(UserEntity user);
    void deleteUser(Long id);

    Optional<UserEntity> findUserByEmail(String email);
    Optional<UserEntity> findUserByEmailQuery(String email);

    boolean existsByEmail(String email);
}

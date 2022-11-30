package org.sdn.springcloud.msvc.users.Services;

import org.sdn.springcloud.msvc.users.dto.UserDto;
import org.sdn.springcloud.msvc.users.models.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> findAll();
    Optional<UserEntity> getById(Long id);
    UserEntity saveUser(UserEntity user);
    void deleteUser(Long id);

    Optional<UserEntity> findUserByEmail(String email);
    Optional<UserEntity> findUserByEmailQuery(String email);

    // Validate fields Count
    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
    boolean existsByUserIdentification(String userIdentification);
}

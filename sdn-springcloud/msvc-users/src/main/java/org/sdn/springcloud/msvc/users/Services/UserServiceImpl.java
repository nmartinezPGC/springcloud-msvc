package org.sdn.springcloud.msvc.users.Services;

import org.sdn.springcloud.msvc.users.models.entity.UserEntity;
import org.sdn.springcloud.msvc.users.repositories.UserRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepositoty userRepositoty;

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> findAll() {
        return (List<UserEntity>) userRepositoty.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> getById(Long id) {
        return userRepositoty.findById(id);
    }

    @Override
    @Transactional
    public UserEntity saveUser(UserEntity user) {
        return userRepositoty.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepositoty.deleteById(id);
    }

    @Override
    public Optional<UserEntity> findUserByEmail(String email) {
        return userRepositoty.findByEmail(email);
    }

    @Override
    public Optional<UserEntity> findUserByEmailQuery(String email) {
        return userRepositoty.findByEmailQuery(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepositoty.existsByEmail(email);
    }
}

package org.sdn.springcloud.msvc.users.Services;

import org.sdn.springcloud.msvc.users.dto.UserDto;
import org.sdn.springcloud.msvc.users.models.entity.UserEntity;
import org.sdn.springcloud.msvc.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        List<UserEntity> userList = (List<UserEntity>) userRepository.findAll();
        return userList.stream()
                .map(this::fromEntityToDto)
                .collect(Collectors.toList());
    }

    public UserDto fromEntityToDto(UserEntity entity){
        return new UserDto(entity.getId(), entity.getUserIdentification(), entity.getEmail(), entity.getUserName(),
                entity.getPassword(), entity.getUserAddress(), entity.getFullUserName(), entity.getIsEnabled(),
                entity.getCode(), entity.getCreateAt(), entity.getUpdateAt(), entity.getIsDeleted() );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserEntity> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<UserEntity> findUserByEmailQuery(String email) {
        return userRepository.findByEmailQuery(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }

    @Override
    public boolean existsByUserIdentification(String userIdentification) {
        return userRepository.existsByUserIdentification(userIdentification);
    }
}

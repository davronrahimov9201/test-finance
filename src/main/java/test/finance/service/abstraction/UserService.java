package test.finance.service.abstraction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import test.finance.user.UserEntity;

import java.util.UUID;

public interface UserService {
    UserEntity create(UserEntity model);
    UserEntity update(UserEntity model);
    UserEntity save(UserEntity model);
    UserEntity getById(UUID id);
    void delete(UUID id);
    Page<UserEntity> findAll(Pageable pageable);
}

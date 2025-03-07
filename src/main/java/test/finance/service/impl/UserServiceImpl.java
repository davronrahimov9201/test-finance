package test.finance.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import test.finance.exception.Conflict;
import test.finance.exception.ErrorItem;
import test.finance.exception.ErrorKey;
import test.finance.exception.NotFound;
import test.finance.service.abstraction.UserService;
import test.finance.user.UserEntity;
import test.finance.user.UserRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public UserEntity create(UserEntity model) {
        checkExistsByUsername(model.getUsername());
        model.setBalance(BigDecimal.valueOf(0));
        return save(model);
    }

    @Override
    public UserEntity update(UserEntity model) {
        checkExistsByUsernameAndIdNot(model);
        return save(model);
    }

    @Override
    public UserEntity save(UserEntity model) {
        return repository.save(model);
    }

    @Override
    public UserEntity getById(UUID id) {
        Optional<UserEntity> optional = repository.findById(id);
        if(optional.isEmpty()) {
            ErrorItem error = ErrorItem.USER_NOT_FOUND_BY_ID;
            throw new NotFound(
                error.getCode(),
                error.getErrorMessage(),
                new HashMap<>() {{ put(ErrorKey.ID, id); }}
            );
        }
        return optional.get();
    }

    @Override
    public void delete(UUID id) {
        repository.delete(getById(id));
    }

    @Override
    public Page<UserEntity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    private void checkExistsByUsername(String username) {
        if(repository.existsByUsername(username)) {
            ErrorItem error = ErrorItem.USERNAME_ALREADY_EXISTS;
            throw new Conflict(
                error.getCode(),
                error.getErrorMessage(),
                new HashMap<>() {{ put(ErrorKey.USERNAME, username); }}
            );
        }
    }

    private void checkExistsByUsernameAndIdNot(UserEntity model) {
        if(repository.existsByUsernameAndIdNot(model.getUsername(), model.getId())) {
            ErrorItem error = ErrorItem.USERNAME_ALREADY_EXISTS;
            throw new Conflict(
                error.getCode(),
                error.getErrorMessage(),
                new HashMap<>() {{ put(ErrorKey.USERNAME, model.getUsername()); }}
            );
        }
    }



}

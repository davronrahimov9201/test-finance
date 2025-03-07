package test.finance.user;

import org.springframework.stereotype.Component;
import test.finance.generic.Converter;

@Component
public class UserConverter extends Converter<UserDto, UserEntity, UserRequest> {

    public UserConverter() {
        super((request, entity) -> {
            entity.setUsername(request.getUsername());
            entity.setFirstName(request.getFirstName());
            entity.setLastName(request.getLastName());
            return entity;
        }, entity -> new UserDto(
            entity.getId(),
            entity.getUsername(),
            entity.getFirstName(),
            entity.getLastName(),
            entity.getBalance(),
            entity.getCreatedDate(),
            entity.getModifiedDate()
        ));
    }

}

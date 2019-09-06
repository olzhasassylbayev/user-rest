package com.user.rest.userrest.converter;

import com.user.rest.userrest.entity.UserEntity;
import com.user.rest.userrest.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToUserConverter implements Converter<UserEntity, User> {
    @Override
    public User convert(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setLogin(userEntity.getLogin());
        user.setName(userEntity.getName());
        user.setSurname(userEntity.getSurname());
        user.setEmail(userEntity.getEmail());
        user.setPassword(userEntity.getPassword());
        return user;
    }
}

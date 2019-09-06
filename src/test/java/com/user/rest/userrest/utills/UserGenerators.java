package com.user.rest.userrest.utills;

import com.user.rest.userrest.entity.UserEntity;
import com.user.rest.userrest.model.User;

public class UserGenerators {

    static public User generateUser() {
        User genUser = new User();
        genUser.setId(1L);
        genUser.setName("Olzhas");
        genUser.setSurname("Assylbayev");
        genUser.setLogin("ola");
        genUser.setEmail("olzhas.asylbayev@gmail.com");
        genUser.setPassword("Aa11Ab");
        return genUser;
    }

    static public User generateUser(UserEntity userEntity){
        User user = new User();
        user.setId(userEntity.getId());
        user.setName(userEntity.getName());
        user.setSurname(userEntity.getSurname());
        user.setLogin(userEntity.getLogin());
        user.setEmail(userEntity.getEmail());
        user.setPassword(userEntity.getPassword());
        return user;
    }

    static public UserEntity generateUserEntity() {
        UserEntity genUserEntity = new UserEntity();
        genUserEntity.setId(1L);
        genUserEntity.setName("Olzhas");
        genUserEntity.setSurname("Assylbayev");
        genUserEntity.setLogin("ola");
        genUserEntity.setEmail("olzhas.asylbayev@gmail.com");
        genUserEntity.setPassword("Aa11Ab");
        return genUserEntity;
    }

    static public UserEntity generateUpdatedUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName("Eldos");
        userEntity.setSurname("Tamayev");
        userEntity.setLogin("ela");
        userEntity.setEmail("eldos.tamayev@gmail.com");
        userEntity.setPassword("Aa11Ab");
        return userEntity;
    }
}

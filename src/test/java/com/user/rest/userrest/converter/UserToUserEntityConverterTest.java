package com.user.rest.userrest.converter;

import com.user.rest.userrest.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.user.rest.userrest.utills.UserGenerators.generateUser;
import static com.user.rest.userrest.utills.UserGenerators.generateUserEntity;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserToUserEntityConverterTest {

    @Autowired
    UserToUserEntityConverter userToUserEntityConverter;

    @Test
    public void fromUserToUserEntityConverterTest(){
        UserEntity expectedUserEntity = generateUserEntity();
        UserEntity resultUserEntity = userToUserEntityConverter.convert(generateUser());

        assertEquals(expectedUserEntity,resultUserEntity);
    }
}

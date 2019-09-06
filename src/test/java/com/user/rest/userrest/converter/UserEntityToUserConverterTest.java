package com.user.rest.userrest.converter;

import com.user.rest.userrest.model.User;
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
public class UserEntityToUserConverterTest{

    @Autowired
    UserEntityToUserConverter userEntityToUserConverter;

    @Test
    public void fromUserEntityToUserConverterTest(){
        User expectedUser = generateUser();
        User resultUser = userEntityToUserConverter.convert(generateUserEntity());

        assertEquals(resultUser,expectedUser);
    }
}

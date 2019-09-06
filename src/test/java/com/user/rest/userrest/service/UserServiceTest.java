package com.user.rest.userrest.service;

import java.util.List;
import com.user.rest.userrest.converter.UserEntityToUserConverter;
import com.user.rest.userrest.converter.UserToUserEntityConverter;
import com.user.rest.userrest.entity.UserEntity;
import com.user.rest.userrest.model.User;
import com.user.rest.userrest.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static com.user.rest.userrest.utills.UserGenerators.generateUpdatedUserEntity;
import static com.user.rest.userrest.utills.UserGenerators.generateUser;
import static com.user.rest.userrest.utills.UserGenerators.generateUserEntity;
import static java.util.Collections.singletonList;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserEntityToUserConverter userEntityToUserConverter;

    @Mock
    UserToUserEntityConverter userToUserEntityConverter;

    @InjectMocks
    UserService userService;

    @Test
    public void getAllUsersTest(){
        UserEntity userEntity = generateUserEntity();
        User user = generateUser(userEntity);

        when(userEntityToUserConverter.convert(userEntity))
                .thenReturn(user);
        when(userRepository.findAll())
                .thenReturn(singletonList(userEntity));

        List<User> users = userService.getUsers();

        assertEquals(singletonList(user),users);

        verify(userEntityToUserConverter).convert(userEntity);
        verify(userRepository).findAll();

        verifyNoMoreInteractions(userEntityToUserConverter, userRepository);
        verifyZeroInteractions(userToUserEntityConverter);
    }

    @Test
    public void getUserTest(){
        UserEntity userEntity = generateUserEntity();
        User user = generateUser(userEntity);

        when(userEntityToUserConverter.convert(userEntity))
                .thenReturn(user);
        when(userRepository.findById(user.getId()))
                .thenReturn(of(userEntity));

        User actualUser = userService.getUser(user.getId());

        assertEquals(user,actualUser);

        verify(userEntityToUserConverter).convert(userEntity);
        verify(userRepository).findById(user.getId());

        verifyNoMoreInteractions(userEntityToUserConverter, userRepository);
        verifyZeroInteractions(userToUserEntityConverter);
    }

    @Test
    public void createUserTest(){
        UserEntity userEntity = generateUserEntity();
        User user = generateUser(userEntity);

        when(userEntityToUserConverter.convert(userEntity))
                .thenReturn(user);

        when(userToUserEntityConverter.convert(user))
                .thenReturn(userEntity);

        when(userRepository.save(userEntity))
                .thenReturn(userEntity);

        User createdUser = userService.createUser(user);

        assertEquals(user,createdUser);

        verify(userEntityToUserConverter).convert(userEntity);
        verify(userToUserEntityConverter).convert(user);
        verify(userRepository).save(userEntity);

        verifyNoMoreInteractions(userEntityToUserConverter, userToUserEntityConverter, userRepository);
    }

    @Test
    public void updateUserTest(){
        long id = 1L;
        UserEntity userEntity = generateUpdatedUserEntity();
        UserEntity oldUserEntity = generateUserEntity();
        User user = generateUser(userEntity);

        when(userRepository.existsById(id))
                .thenReturn(true);
        when(userRepository.findById(id))
                .thenReturn(ofNullable(oldUserEntity));
        when(userRepository.save(userEntity))
                .thenReturn(userEntity);
        when(userEntityToUserConverter.convert(userEntity))
                .thenReturn(user);
        when(userToUserEntityConverter.convert(user))
                .thenReturn(userEntity);

        User updatedUser = userService.updateUser(id, user);

        assertEquals(user,updatedUser);

        verify(userEntityToUserConverter).convert(userEntity);
        verify(userToUserEntityConverter).convert(user);
        verify(userRepository).save(userEntity);
        verify(userRepository).findById(id);
        verify(userRepository).existsById(id);

        verifyNoMoreInteractions(userEntityToUserConverter, userToUserEntityConverter, userRepository);
    }

    @Test
    public void deleteUserTest(){
        long id = 1;
        when(userRepository.existsById(id))
                .thenReturn(true);

        doNothing().when(userRepository).deleteById(id);

        userService.deleteUser(id);

        verify(userRepository).existsById(id);
        verify(userRepository).deleteById(id);

        verifyNoMoreInteractions(userRepository);
        verifyZeroInteractions(userToUserEntityConverter,userEntityToUserConverter);
    }
}

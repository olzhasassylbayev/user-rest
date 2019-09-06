package com.user.rest.userrest.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import com.user.rest.userrest.converter.UserEntityToUserConverter;
import com.user.rest.userrest.converter.UserToUserEntityConverter;
import com.user.rest.userrest.exceptions.UserNotFoundException;
import com.user.rest.userrest.model.User;
import com.user.rest.userrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserEntityToUserConverter userEntityToUserConverter;

    @Autowired
    private UserToUserEntityConverter userToUserEntityConverter;

    public List<User> getUsers(){
        return userRepository.findAll()
                .stream()
                .map(userEntity -> userEntityToUserConverter.convert(userEntity))
                .collect(Collectors.toList());
    }

    public User getUser(long id){
        return userEntityToUserConverter.convert(userRepository.findById(id).orElseThrow(
                ()-> new UserNotFoundException("User with id: " + id + " not found")));
    }

    public User createUser(User user){
        return userEntityToUserConverter.convert(
                userRepository.save(userToUserEntityConverter.convert(user)));
    }

    public User updateUser(long id, User user){
        if (!userRepository.existsById(id)){
            throw new UserNotFoundException("User with id: " + id + " not found");
        }

        return userRepository.findById(id)
                .map(userEntity -> {
                    userEntity = userToUserEntityConverter.convert(user);
                    return userEntityToUserConverter.convert(userRepository.save(userEntity));})
                .orElseThrow(() -> new UserNotFoundException("Cannot update user with id: " + id));
    }

    public void deleteUser(long id){
        if (!userRepository.existsById(id)){
            throw new UserNotFoundException("User with id: " + id + " not found");
        }
        userRepository.deleteById(id);
    }
}

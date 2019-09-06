package com.user.rest.userrest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.rest.userrest.model.User;
import com.user.rest.userrest.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.user.rest.userrest.utills.UserGenerators.generateUser;
import static java.util.Collections.singletonList;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void getUserTest() throws Exception {
        User mockUser = generateUser();

        when(userService.getUser(anyLong()))
                .thenReturn(mockUser);

        this.mockMvc.perform(get("/users/" + mockUser.getId()))
                .andExpect(status().isOk())
                .andExpect(
                        content().json(objectMapper.writeValueAsString(mockUser)));

        verify(userService).getUser(mockUser.getId());
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void getAllUsersTest() throws Exception {
        User mockUser = generateUser();

        when(userService.getUsers())
                .thenReturn(singletonList(mockUser));

        this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(singletonList(mockUser))));

        verify(userService).getUsers();
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void createUserTest() throws Exception {
        User mockUser = generateUser();
        when(userService.createUser(mockUser))
                .thenReturn(mockUser);

        this.mockMvc.perform(post("/users")
                                     .content(objectMapper.writeValueAsString(mockUser))
                                     .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(mockUser)));

        verify(userService).createUser(mockUser);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void updateUserTest() throws Exception {
        User mockUser = generateUser();

        when(userService.updateUser(mockUser.getId(), mockUser))
                .thenReturn(mockUser);

        this.mockMvc
                .perform(put("/users/" + mockUser.getId())
                                 .content(objectMapper.writeValueAsString(mockUser))
                                 .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockUser)));

        verify(userService).updateUser(mockUser.getId(), mockUser);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void deleteUserTest() throws Exception {
        long id = 1;

        doNothing().when(userService).deleteUser(id);

        this.mockMvc.perform(delete("/users/" + id))
                .andExpect(status().isNoContent());

        verify(userService).deleteUser(id);
        verifyNoMoreInteractions(userService);
    }
}


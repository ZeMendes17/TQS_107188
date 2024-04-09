package pt.ua.deti.backend.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.is;

import pt.ua.deti.backend.entity.User;
import pt.ua.deti.backend.service.UserService;

@WebMvcTest(UserController.class)
class UserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    void whenValidUser_thenUserShouldBeSaved() {
        User user = new User("ze", "ze@example.com");
        when(userService.save(user)).thenReturn(user);

        try {
            mockMvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.toJson(user)))
                .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
        verify(userService, times(1)).save(any(User.class));
    }

    @Test
    void whenValidEmail_thenUserShouldBeFound() {
        User user = new User("ze", "ze@example.com");
        when(userService.getUserByEmail("ze@example.com")).thenReturn(user);

        try {
            mockMvc.perform(get("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .param("email", "ze@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("ze")))
                .andExpect(jsonPath("$.email", is("ze@example.com")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        verify(userService, times(1)).getUserByEmail("ze@example.com");
    }
}

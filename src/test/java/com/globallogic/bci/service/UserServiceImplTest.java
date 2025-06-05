package com.globallogic.bci.service;

import com.globallogic.bci.dto.PhoneDto;
import com.globallogic.bci.dto.UserDto;
import com.globallogic.bci.dto.UserGetDto;
import com.globallogic.bci.dto.UserPostDto;
import com.globallogic.bci.exception.CustomException;
import com.globallogic.bci.model.User;
import com.globallogic.bci.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private UserDto validUserDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setNumber(1234567);
        phoneDto.setCityCode(1);
        phoneDto.setCountryCode("57");

        validUserDto = new UserDto();
        validUserDto.setName("Test");
        validUserDto.setEmail("test@email.com");
        validUserDto.setPassword("a2bCdefg1");
        validUserDto.setPhones(Collections.singletonList(phoneDto));
    }

    @Test
    public void testCreateUser_Success() {
        User savedUser = new User();
        savedUser.setId(UUID.randomUUID());
        savedUser.setEmail(validUserDto.getEmail());
        savedUser.setPassword("a2asfGfdf8");
        savedUser.setToken("token");
        savedUser.setPhones(Collections.emptyList());
        savedUser.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        savedUser.setLastLogin(Timestamp.valueOf(LocalDateTime.now()));
        savedUser.setActive(true);

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        UserPostDto result = userService.createUser(validUserDto);
        assertNotNull(result);
    }

    @Test
    public void testCreateUser_InvalidEmail() {
        validUserDto.setEmail("invalid-email");

        CustomException exception = assertThrows(CustomException.class, () -> userService.createUser(validUserDto));

        assertEquals("Not a valid email", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getCode());
    }

    @Test
    public void testCreateUser_EmailNotNull() {
        validUserDto.setEmail(null);

        CustomException exception = assertThrows(CustomException.class, () -> userService.createUser(validUserDto));

        assertEquals("Email field required", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getCode());
    }

    @Test
    public void testCreateUser_InvalidPassword() {
        validUserDto.setPassword("abc");

        CustomException exception = assertThrows(CustomException.class, () -> userService.createUser(validUserDto));

        assertEquals("Not a valid password", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getCode());
    }

    @Test
    public void testCreateUser_PasswordNotNull() {
        validUserDto.setPassword(null);

        CustomException exception = assertThrows(CustomException.class, () -> userService.createUser(validUserDto));

        assertEquals("Password field required", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST.value(), exception.getCode());
    }

    @Test
    public void testGetUser_Success() {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail("test@email.com");
        user.setToken("oldToken");

        when(userRepository.findByToken("token")).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserGetDto result = userService.getUser("token");
        assertNotNull(result);
        assertEquals(user.getEmail(), result.getEmail());
    }

    @Test
    public void testGetUser_NotFound() {
        when(userRepository.findByToken("token")).thenReturn(Optional.empty());

        CustomException exception = assertThrows(CustomException.class, () -> {
            userService.getUser("token");
        });

        assertEquals("User does not exist", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND.value(), exception.getCode());
    }
}

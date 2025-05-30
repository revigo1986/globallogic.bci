package com.globallogic.bci.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globallogic.bci.dto.UserDto;
import com.globallogic.bci.dto.UserGetDto;
import com.globallogic.bci.dto.UserPostDto;
import com.globallogic.bci.exception.CustomException;
import com.globallogic.bci.model.User;
import com.globallogic.bci.repository.IUserRepository;
import com.globallogic.bci.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserPostDto createUser(UserDto userDto) {

        try {
            // TODO: USE JWT TO GENERATE THE TOKEN

            validateFields(userDto);

            // TODO: If viable, try to encrypt password field

            // TODO: Name and phone, optional fields

            // TODO: Error case, follow ErrorDto format

            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            User user = objectMapper.convertValue(userDto, User.class);

            user.setId(UUID.randomUUID());
            user.setCreated(Timestamp.valueOf(LocalDateTime.now()));
            user.setLastLogin(Timestamp.valueOf(LocalDateTime.now()));
            user.setToken(UUID.randomUUID().toString());
            user.setActive(true);

            final User finalUser = user;

            user.getPhones().forEach(phone -> {
                phone.setId(UUID.randomUUID());
                phone.setUser(finalUser);
            });

            user = userRepository.save(user);

            UserPostDto userPostDto = objectMapper.convertValue(user, UserPostDto.class);

            return userPostDto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    @Transactional
    public UserGetDto getUser(String token) {

        User user = userRepository.findByToken(token).orElseThrow(() -> new CustomException("User does not exist", HttpStatus.NOT_FOUND.value()));

        return objectMapper.convertValue(user, UserGetDto.class);

    }

    /**
     * Validate some special fields
     * @param userDto
     * @throws Exception
     */
    private void validateFields(UserDto userDto) throws Exception {

        // Validate email format
        if (!StringUtils.isValidEmail(userDto.getEmail())){
            throw new CustomException("Not a valid email", HttpStatus.BAD_REQUEST.value());
        }

        // Validate password format
        if (!StringUtils.isValidPassword(userDto.getPassword())) {
            throw new CustomException("Not a valid password", HttpStatus.BAD_REQUEST.value());
        }
    }
}

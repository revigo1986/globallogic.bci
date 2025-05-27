package com.globallogic.bci.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.globallogic.bci.dto.UserDto;
import com.globallogic.bci.model.User;
import com.globallogic.bci.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements IUserService {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

        // TODO: Validate email format

        // TODO: Validate password format

        // TODO: If viable, try to encrypt password field

        User user = userRepository.save(objectMapper.convertValue(userDto, User.class));

        userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setCreated(user.getCreated());
        userDto.setLastLogin(user.getLastLogin());
        userDto.setToken(user.getToken());
        userDto.setActive(user.isActive());

        return userDto;
    }

    @Override
    public UserDto getUser(String token) {

        User user = userRepository.findByToken(token);

        return objectMapper.convertValue(user, UserDto.class);
    }
}

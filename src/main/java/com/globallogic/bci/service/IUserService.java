package com.globallogic.bci.service;


import com.globallogic.bci.dto.UserDto;

public interface IUserService {

    UserDto createUser(UserDto userDto);

    UserDto getUser(String token);
}

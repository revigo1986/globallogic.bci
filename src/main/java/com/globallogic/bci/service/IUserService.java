package com.globallogic.bci.service;


import com.globallogic.bci.dto.UserDto;
import com.globallogic.bci.dto.UserGetDto;
import com.globallogic.bci.dto.UserPostDto;

public interface IUserService {

    UserPostDto createUser(UserDto userDto);

    UserGetDto getUser(String token);
}

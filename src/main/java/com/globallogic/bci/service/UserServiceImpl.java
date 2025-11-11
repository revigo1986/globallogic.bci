package com.globallogic.bci.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globallogic.bci.dto.ErrorDto;
import com.globallogic.bci.dto.UserDto;
import com.globallogic.bci.dto.UserGetDto;
import com.globallogic.bci.dto.UserPostDto;
import com.globallogic.bci.exception.ErrorCode;
import com.globallogic.bci.exception.MultipleCustomException;
import com.globallogic.bci.model.User;
import com.globallogic.bci.repository.IUserRepository;
import com.globallogic.bci.security.Encryptor;
import com.globallogic.bci.security.JwtUtil;
import com.globallogic.bci.util.StringUtils;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private IUserRepository userRepository;

    /**
     * Create user
     * @param userDto
     * @return
     */
    @Override
    public UserPostDto createUser(UserDto userDto) {

        log.info("Creating user");
        List<ErrorDto> errors = new ArrayList<>();

        try {

            if (userRepository.findByName(userDto.getName()).isPresent()) {
                buildErrorDetail(ErrorCode.USER_ALREADY_EXISTS.getCode(), ErrorCode.USER_ALREADY_EXISTS.getMessage(), errors);
                throw new MultipleCustomException(errors, ErrorCode.USER_ALREADY_EXISTS.getHttpStatus(), ErrorCode.USER_ALREADY_EXISTS.getMessage());
            }

            validateFields(userDto, errors);

            if (!errors.isEmpty()) {
              StringBuilder messageBuilder = new StringBuilder();
              errors.forEach(error -> messageBuilder.append(error.getDetail()).append("; "));
              throw new MultipleCustomException(errors, HttpStatus.BAD_REQUEST, messageBuilder.toString());
            }

            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            User user = objectMapper.convertValue(userDto, User.class);

            user.setId(UUID.randomUUID());
            user.setCreated(Timestamp.valueOf(LocalDateTime.now()));
            user.setLastLogin(Timestamp.valueOf(LocalDateTime.now()));
            user.setActive(true);

            // Encrypt password field
            user.setPassword(Encryptor.encryptStringSSLRSA(userDto.getPassword()));

            // Generate token from user id and email
            String token = JwtUtil.generateToken(user.getId(), user.getEmail());
            user.setToken(token);

            final User finalUser = user;

            if (Optional.ofNullable(user.getPhones()).isPresent()) {
                user.getPhones().forEach(phone -> {
                    phone.setId(UUID.randomUUID());
                    phone.setUser(finalUser);
                });
            }

            user = userRepository.save(user);

            UserPostDto userPostDto = objectMapper.convertValue(user, UserPostDto.class);

            log.info("User created.");

            return userPostDto;

        } catch (MultipleCustomException mce) {
            log.error("Error while creating user. Message: "+mce.getMessage());
            throw mce;
        } catch (Exception ex) {
          log.error("Error while creating user. Message: "+ex.getMessage());

          buildErrorDetail(ErrorCode.INTERNAL_ERROR.getCode(), ErrorCode.INTERNAL_ERROR.getMessage(), errors);

          throw new MultipleCustomException(errors, ErrorCode.INTERNAL_ERROR.getHttpStatus(), ex.getMessage());
        }

    }

    /**
     * Get user
     * @param token
     * @return
     */
    @Override
    public UserGetDto getUser(String token) {

        log.info("Getting user according to given token");

        List<ErrorDto> errors = new ArrayList<>();

        try {
          Optional<User> userOptional = userRepository.findByToken(token);

          if (userOptional.isEmpty()) {
            buildErrorDetail(ErrorCode.USER_DOES_NOT_EXIST.getCode(), ErrorCode.USER_DOES_NOT_EXIST.getMessage(), errors);
            throw new MultipleCustomException(errors, ErrorCode.USER_DOES_NOT_EXIST.getHttpStatus(), ErrorCode.USER_DOES_NOT_EXIST.getMessage());
          }

          User user = userOptional.get();

          // Regenerate token
          token = JwtUtil.generateToken(user.getId(), user.getEmail());
          user.setToken(token);

          // Update token
          user = userRepository.save(user);

          log.info("User token updated");

          return objectMapper.convertValue(user, UserGetDto.class);

        } catch (MultipleCustomException mce) {
          log.error("Error while getting user. Message: "+mce.getMessage());
          throw mce;
        } catch (Exception ex) {
          log.error("Error while getting user. Message: "+ex.getMessage());

          buildErrorDetail(ErrorCode.INTERNAL_ERROR.getCode(), ErrorCode.INTERNAL_ERROR.getMessage(), errors);

          throw new MultipleCustomException(errors, ErrorCode.INTERNAL_ERROR.getHttpStatus(), ex.getMessage());
        }

    }

    /**
     * Validate some special fields
     * @param userDto
     */
    private void validateFields(UserDto userDto, List<ErrorDto> errors) {

        log.info("Validating email and password");

        // Validate email is coming
        if (Optional.ofNullable(userDto.getEmail()).isEmpty()) {
          buildErrorDetail(ErrorCode.FIELD_REQUIRED.getCode(), ErrorCode.FIELD_REQUIRED.getMessage() + "email", errors);
        }

        // Validate password is coming
        if (Optional.ofNullable(userDto.getPassword()).isEmpty()) {
          buildErrorDetail(ErrorCode.FIELD_REQUIRED.getCode(), ErrorCode.FIELD_REQUIRED.getMessage() + "password", errors);
        }

        // Validate email format
        if (Optional.ofNullable(userDto.getEmail()).isPresent() && !StringUtils.isValidEmail(userDto.getEmail())){
          buildErrorDetail(ErrorCode.INVALID_EMAIL.getCode(), ErrorCode.INVALID_EMAIL.getMessage(), errors);
        }

        // Validate password format
        if (Optional.ofNullable(userDto.getPassword()).isPresent() && !StringUtils.isValidPassword(userDto.getPassword())) {
          buildErrorDetail(ErrorCode.INVALID_PASSWORD.getCode(), ErrorCode.INVALID_PASSWORD.getMessage(), errors);
        }
    }

  /**
   * Builds the error detail dto according to the code and details
   * @param errorCode
   * @param errorDetail
   * @param errors
   */
  private void buildErrorDetail(int errorCode, String errorDetail, List<ErrorDto> errors) {
      ErrorDto errorDto = new ErrorDto();
      errorDto.setCode(errorCode);
      errorDto.setDetail(errorDetail);
      errorDto.setTimestamp(new Timestamp(System.currentTimeMillis()));
      errors.add(errorDto);
    }
}

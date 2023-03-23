package com.rest.api.service.impl;

import com.rest.api.entity.User;
import com.rest.api.mapper.Mapper;
import com.rest.api.repository.UserRepository;
import com.rest.api.service.UserService;
import com.rest.api.utils.request.UserDTO;
import com.rest.api.utils.response.BaseResponse;
import com.rest.api.utils.response.UserResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public BaseResponse<UserResponseDTO> saveUser(UserDTO userDTO) {

        BaseResponse<UserResponseDTO> baseResponse = new BaseResponse<>();

        BaseResponse validateResult = validateUser(userDTO);
        if (validateResult.isSuccess()) {
            User savedUser = userRepository.save(Mapper.toUse(userDTO));
            baseResponse.setData(Mapper.toUserResponseDTO(savedUser));
            baseResponse.setSuccess(true);
            baseResponse.setCode(String.valueOf(HttpStatus.OK));
        } else {
            baseResponse.setCode(validateResult.getCode());
            baseResponse.setSuccess(validateResult.isSuccess());
        }

        return baseResponse;
    }

    private BaseResponse validateUser(UserDTO userDTO) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setSuccess(true);
        Optional<User> users = userRepository.findByEmail(userDTO.getEmail());
        if (users.isPresent()) {
            baseResponse.setSuccess(false);
            baseResponse.setCode(String.valueOf(HttpStatus.BAD_REQUEST));
            return baseResponse;
        }
        return baseResponse;
    }
}

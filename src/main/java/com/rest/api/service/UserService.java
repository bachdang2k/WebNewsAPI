package com.rest.api.service;

import com.rest.api.utils.request.UserDTO;
import com.rest.api.utils.response.BaseResponse;
import com.rest.api.utils.response.UserResponseDTO;

public interface UserService {

    BaseResponse<UserResponseDTO> saveUser(UserDTO userDTO);
}

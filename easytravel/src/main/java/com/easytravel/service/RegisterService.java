package com.easytravel.service;

import javax.validation.Valid;

import com.easytravel.responseDTO.CommonResponseDTO;
import com.easytravel.responseDTO.LoginDTO;
import com.easytravel.responseDTO.RegisterDTO;

public interface RegisterService {

	CommonResponseDTO registerUser(@Valid RegisterDTO request);

	CommonResponseDTO deleteById(@Valid Long id);

	CommonResponseDTO loginFromMobile(LoginDTO data);

	CommonResponseDTO verifyOTP(LoginDTO request);

}

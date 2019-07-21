package com.easytravel.service.impl;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easytravel.entity.LoginEntity;
import com.easytravel.entity.RegisterEntity;
import com.easytravel.repository.LoginRepository;
import com.easytravel.repository.RegisterRepository;
import com.easytravel.responseDTO.CommonResponseDTO;
import com.easytravel.responseDTO.DataResponseDTO;
import com.easytravel.responseDTO.LoginDTO;
import com.easytravel.responseDTO.RegisterDTO;
import com.easytravel.service.RegisterService;
import com.easytravel.service.helper.RegisterServiceImplHelper;
import com.easytravel.utils.Constant;
import com.easytravel.utils.RandomNumberGenerator;
import com.easytravel.utils.ResponseUtil;
import com.easytravel.utils.ValidationUtil;

@Service
public class RegisterServiceImpl implements RegisterService {

	private Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	RegisterRepository registerRepository;

	@Autowired
	LoginRepository loginRepository;

	@Autowired
	RandomNumberGenerator otpGenerator;
	
	@Autowired
	RegisterServiceImplHelper registerServiceImpl;

	@Override
	public CommonResponseDTO registerUser(RegisterDTO request) {
		log.info(" registerUser of RegisterServiceImpl");
		DataResponseDTO response = new DataResponseDTO();
		if (request != null) {
			RegisterEntity registerdata = new RegisterEntity();
			Boolean userExists = registerRepository.existsByEmailId(request.getEmailId());
			if (userExists) {
				log.info("registerUser already exits.");
				ResponseUtil.prepareResponse(response, "User with this emailId already exits.", Constant.SUCCESS,
						Constant.STATUS_SUCCESS);
				return response;
			}
			BeanUtils.copyProperties(request, registerdata);
			registerRepository.save(registerdata);
			ResponseUtil.prepareResponse(response, "User Register Successfully.", Constant.SUCCESS,
					Constant.STATUS_SUCCESS);

		} else {
			log.error("please fill provide fields.");
			ResponseUtil.prepareResponse(response, "please fill provide fields.", Constant.FAILURE,
					Constant.STATUS_FAIL, "");

		}
		return response;
	}

	@Override
	public CommonResponseDTO deleteById(@Valid Long id) {
		log.info(" login of RegisterServiceImpl");
		DataResponseDTO response = new DataResponseDTO();
		if (id != null) {
			registerRepository.deleteById(id);
			ResponseUtil.prepareResponse(response, "User delete Successfully.", Constant.SUCCESS,
					Constant.STATUS_SUCCESS);

		} else {
			log.error("please provide user id.");
			ResponseUtil.prepareResponse(response, "please provide user id.", Constant.FAILURE, Constant.STATUS_FAIL);

		}
		return response;
	}

	@Override
	public CommonResponseDTO loginFromMobile(LoginDTO request) {
		log.info("loginFromMobile of RegisterServiceImpl-" + request.toString());
		DataResponseDTO response = new DataResponseDTO();
		try {
			if (!ValidationUtil.isValidMobileNumber(request.getMobileNo())) {
				ResponseUtil.prepareResponse(response, "Please send a valid phone number", Constant.FAILURE,
						Constant.STATUS_FAIL);
				return response;
			}
			if (loginRepository.existsByMobileNo(request.getMobileNo())) {
				ResponseUtil.prepareResponse(response, "Profile with this Phone already exists..", Constant.FAILURE,
						Constant.STATUS_FAIL);
				return response;
			}
			
			ResponseUtil.prepareResponse(response, "user mobile number verified successfully.", Constant.SUCCESS,
					Constant.STATUS_SUCCESS);
			if (response.getStatus()) {
				registerServiceImpl.OTPGenerationAndSending(request, response);
			}

		} catch (Exception e) {
			log.error("Exception while saving customer profile.." + e.getMessage(), e);
			ResponseUtil.prepareResponse(response, "Something went wrong.", Constant.FAILURE, Constant.STATUS_FAIL,
					e.getMessage());
		}
		return response;
	}

	@Override
	public CommonResponseDTO verifyOTP(LoginDTO request) {
		DataResponseDTO response = new DataResponseDTO();
		try {
			if (request != null && request.getMobileNo() != null && !request.getMobileNo().isEmpty()) {

				LoginEntity loginData = loginRepository.findByMobileNo(request.getMobileNo());
				if (loginData != null) {
					String otpDB = loginData.getOtp();
					if (otpDB.equals(request.getOtp())) {
						ResponseUtil.prepareResponse(response, "Otp verified successfully.", Constant.SUCCESS,
								Constant.STATUS_SUCCESS);
						return response;
					} else {
						ResponseUtil.prepareResponse(response, "Please enter correct OTP.", Constant.FAILURE,
								Constant.STATUS_FAIL);
						return response;
					}
				}

			} else {
				ResponseUtil.prepareResponse(response, "please provide mobile number.", Constant.FAILURE,
						Constant.STATUS_FAIL, "false");
			}
		} catch (Exception e) {
			log.error("notificationSend of PropertyServiceImpl" + e.getMessage());
			ResponseUtil.prepareResponse(response, "Something went wrong.", Constant.FAILURE, Constant.STATUS_FAIL,
					"false");
		}
		return response;
	}
}

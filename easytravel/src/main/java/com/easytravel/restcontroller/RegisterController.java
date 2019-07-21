package com.easytravel.restcontroller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.easytravel.responseDTO.CommonResponseDTO;
import com.easytravel.responseDTO.LoginDTO;
import com.easytravel.responseDTO.RegisterDTO;
import com.easytravel.service.RegisterService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class RegisterController {
	private Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	RegisterService RegisterService;

	@RequestMapping(value = "/register-user", method = RequestMethod.POST)
	public CommonResponseDTO registerUser(@Valid @RequestBody RegisterDTO request) {
		log.info("registerUser of RegisterController");
		return RegisterService.registerUser(request);
	}

	@RequestMapping(value = "/verify-otp", method = RequestMethod.POST)
	public CommonResponseDTO verifyOTP(@Valid @RequestBody LoginDTO request) {
		log.info("verifyOTP of RegisterController");
		return RegisterService.verifyOTP(request);
	}

	@RequestMapping(value = "/delete-by-id", method = RequestMethod.DELETE)
	public CommonResponseDTO deleteById(Long id) {
		log.info("deleteById of RegisterController");
		return RegisterService.deleteById(id);
	}

	@RequestMapping(value = "/login-mobile", method = RequestMethod.POST)
	public CommonResponseDTO loginFromMobile(@Valid @RequestBody LoginDTO loginrequest) {
		log.info("loginFromMobile of RegisterController");
		return RegisterService.loginFromMobile(loginrequest);
	}

}

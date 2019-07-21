package com.easytravel.service.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.easytravel.entity.LoginEntity;
import com.easytravel.repository.LoginRepository;
import com.easytravel.repository.RegisterRepository;
import com.easytravel.responseDTO.DataResponseDTO;
import com.easytravel.responseDTO.LoginDTO;
import com.easytravel.utils.Constant;
import com.easytravel.utils.NotificationUtil;
import com.easytravel.utils.RandomNumberGenerator;
import com.easytravel.utils.ResponseUtil;

@Component
public class RegisterServiceImplHelper {
	private Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	RegisterRepository registerRepository;

	@Autowired
	LoginRepository loginRepository;

	@Autowired
	RandomNumberGenerator otpGenerator;

	@Async("taskExecutor")
	public void OTPGenerationAndSending(LoginDTO request, DataResponseDTO response) {
		try {
			log.info("inside OTPGenerationAndSending()..");
			request.setOtp(otpGenerator.random());
			LoginEntity loginData = new LoginEntity();
			loginData.setMobileNo(request.getMobileNo());
			loginData.setOtp(request.getOtp());

			loginRepository.save(loginData);

			if (response.getStatus()) {
				String token = request.getDeviceToken();
				request.setDeviceToken(token);
				request.setNotificationBody("Your easytravel OTP is : " + request.getOtp());
				log.info("otp generated and set in notification.. " + request.getOtp());
				request.setTitle("Hello,");
				NotificationUtil.send(request);
			}

		} catch (Exception e) {
			log.error("Exception while saving customer profile.." + e.getMessage(), e);
			ResponseUtil.prepareResponse(response, "Something went wrong.", Constant.FAILURE, Constant.STATUS_FAIL,
					e.getMessage());
		}
	}
}

package com.easytravel.utils;

import com.easytravel.responseDTO.CommonResponseDTO;

public class ResponseUtil {

	public static void prepareResponse(CommonResponseDTO response, String message, String success, Boolean status) {
		if (response != null) {
			response.setMessage(message);
			response.setStatus(status);
			response.setSuccess(success);
		}
	}

	public static void prepareResponse(CommonResponseDTO response, String message, String success, Boolean status,
			String info) {
		if (response != null) {
			response.setMessage(message);
			response.setStatus(status);
			response.setSuccess(success);
			response.setInfo(info);

		}
	}

}

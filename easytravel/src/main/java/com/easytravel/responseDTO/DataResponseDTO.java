package com.easytravel.responseDTO;

import java.util.Map;

@SuppressWarnings("rawtypes")
public class DataResponseDTO extends CommonResponseDTO {

	private Map data;

	private Map responseData;

	public Map getResponseData() {
		return responseData;
	}

	public void setResponseData(Map responseData) {
		this.responseData = responseData;
	}

	public Map getData() {
		return data;
	}

	public void setData(Map data) {
		this.data = data;
	}

}

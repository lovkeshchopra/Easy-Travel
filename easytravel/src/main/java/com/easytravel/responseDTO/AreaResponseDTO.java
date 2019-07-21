package com.easytravel.responseDTO;

import java.util.List;

public class AreaResponseDTO extends CommonResponseDTO {

	private List<String> data;

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

}

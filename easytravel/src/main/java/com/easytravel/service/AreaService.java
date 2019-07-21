package com.easytravel.service;

import java.util.Map;

import com.easytravel.responseDTO.AreaInfoResponseDTO;
import com.easytravel.responseDTO.CommonResponseDTO;

public interface AreaService {

	CommonResponseDTO addAreaInfo(AreaInfoResponseDTO requestDTO);

	CommonResponseDTO areaDetail(Map<String, String> request);

}

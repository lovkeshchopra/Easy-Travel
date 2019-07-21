package com.easytravel.service;

import com.easytravel.responseDTO.BusDTO;
import com.easytravel.responseDTO.CommonResponseDTO;

public interface BusService {

	CommonResponseDTO addBusInfo(BusDTO requestDTO);

	CommonResponseDTO getBusInfo(String busNumber);

	CommonResponseDTO getBusByTime(BusDTO request);

}

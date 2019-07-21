package com.easytravel.restcontroller;

import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.easytravel.responseDTO.AreaInfoResponseDTO;
import com.easytravel.responseDTO.CommonResponseDTO;
import com.easytravel.service.AreaService;

@RestController
public class AreaInfoController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	AreaService areaService;

	@RequestMapping(value = "/add-areaInfo", method = RequestMethod.POST)
	public CommonResponseDTO addAreaInfo(@Valid @RequestBody AreaInfoResponseDTO requestDTO) {
		log.info("/add-areaInfo");
		return areaService.addAreaInfo(requestDTO);
	}
	
	@RequestMapping(value = "/get-areas-in-city", method = RequestMethod.POST)
	public CommonResponseDTO getAreaAsPerCity(@Valid @RequestBody Map<String, String> request) {
		log.info("/get-areas-in-city." + request);
		return areaService.areaDetail(request);

	}

}

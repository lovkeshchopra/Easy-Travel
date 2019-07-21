package com.easytravel.restcontroller;

import java.util.Map;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.easytravel.responseDTO.BusDTO;
import com.easytravel.responseDTO.CommonResponseDTO;
import com.easytravel.service.BusService;

@RestController
public class BusController {

	private Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	BusService busService;

	@RequestMapping(value = "/add-busInfo", method = RequestMethod.POST)
	public CommonResponseDTO addBusInfo(@Valid @RequestBody BusDTO requestDTO) {
		log.info("addBusInfo of BusController");
		return busService.addBusInfo(requestDTO);
	}

	@RequestMapping(value = "/get-busInfo", method = RequestMethod.GET)
	public CommonResponseDTO getBusInfo(@RequestParam(required = true) String busNumber) {
		log.info("getBusInfo of BusController");
		return busService.getBusInfo(busNumber);
	}

	@RequestMapping(value = "/get-bus-by-time", method = RequestMethod.POST)
	public CommonResponseDTO getBusByTime(@Valid @RequestBody BusDTO request) {
		log.info("/get-bus-by-time." + request);
		return busService.getBusByTime(request);

	}

}

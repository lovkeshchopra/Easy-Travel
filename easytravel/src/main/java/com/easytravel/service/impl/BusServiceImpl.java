package com.easytravel.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easytravel.entity.BusEntity;
import com.easytravel.repository.BusRepository;
import com.easytravel.responseDTO.BusDTO;
import com.easytravel.responseDTO.CommonResponseDTO;
import com.easytravel.responseDTO.DataResponseDTO;
import com.easytravel.service.BusService;
import com.easytravel.utils.Constant;
import com.easytravel.utils.ResponseUtil;
import com.easytravel.utils.ValidationUtil;

@Service
public class BusServiceImpl implements BusService {

	private final Logger log = LoggerFactory.getLogger(BusServiceImpl.class);

	@Autowired
	BusRepository busRepository;

	@Override
	public CommonResponseDTO addBusInfo(BusDTO requestDTO) {
		log.info("addBusInfo in BusServiceImpl");
		CommonResponseDTO response = new CommonResponseDTO();
		try {
			if (requestDTO.getDestination() == null || requestDTO.getDestination().isEmpty()) {
				log.info("please enter a destination.");
				ResponseUtil.prepareResponse(response, "please enter a destination.", Constant.FAILURE,
						Constant.STATUS_FAIL);
				return response;
			}
			if (requestDTO.getSource() == null || requestDTO.getSource().isEmpty()) {
				log.info("please enter a source.");
				ResponseUtil.prepareResponse(response, "please enter a source.", Constant.FAILURE,
						Constant.STATUS_FAIL);
				return response;
			}
			if (!ValidationUtil.isValidAadhaarNumber(requestDTO.getAadharNumber())) {
				ResponseUtil.prepareResponse(response, "Please enter a valid aadhar number", Constant.FAILURE,
						Constant.STATUS_FAIL);
				return response;
			}
			if (requestDTO.getBusNumber() == null || requestDTO.getBusNumber().isEmpty()) {
				log.info("please enter an busNumber.");
				ResponseUtil.prepareResponse(response, "please enter an busNumber.", Constant.FAILURE,
						Constant.STATUS_FAIL);
				return response;
			}

			BusEntity busRecord = new BusEntity();
			BeanUtils.copyProperties(requestDTO, busRecord);
			String busNumber = requestDTO.getBusNumber();

			BusEntity busInfoRecord = busRepository.findBybusNumber(busNumber);
			if (busInfoRecord != null) {
				log.info("busInfoRecord already exist.");
				ResponseUtil.prepareResponse(response, "Bus with this number already exist.", Constant.SUCCESS,
						Constant.STATUS_SUCCESS, "data already exist.");
				return response;
			}

			BusEntity record = busRepository.save(busRecord);
			if (record != null) {
				log.info("BusInfo saved successfully");
				ResponseUtil.prepareResponse(response, "BusInfo saved successfully.", Constant.SUCCESS,
						Constant.STATUS_SUCCESS, "data added successfully.");
			} else {
				log.info("BusInfo doesn't saved");
				ResponseUtil.prepareResponse(response, "BusInfo doesn't saved.", Constant.FAILURE, Constant.STATUS_FAIL,
						"data doesn't saved.");
			}
		} catch (Exception e) {
			log.error("Exception while adding BusInfo " + e.getMessage(), e);
			ResponseUtil.prepareResponse(response, "Something went wrong.", Constant.FAILURE, Constant.STATUS_FAIL,
					e.getMessage());
		}

		return response;
	}

	@Override
	public CommonResponseDTO getBusInfo(String busNumber) {
		log.info("getBusInfo of BusServiceImpl--id=" + busNumber);
		DataResponseDTO response = new DataResponseDTO();
		try {
			if (busNumber == null) {
				ResponseUtil.prepareResponse(response, "Please send busNumber.", Constant.FAILURE,
						Constant.STATUS_FAIL);
				return response;
			}
			BusEntity profile = busRepository.findBybusNumber(busNumber);
			if (profile == null) {
				ResponseUtil.prepareResponse(response, "No bus with this busNumber.", Constant.FAILURE,
						Constant.STATUS_FAIL);
				return response;
			}
			BusDTO cpd = new BusDTO();
			cpd.setDataFromEntity(profile);
			HashMap<String, BusDTO> hm = new HashMap<String, BusDTO>();
			hm.put("busInformation", cpd);
			response.setData(hm);
			ResponseUtil.prepareResponse(response, "BusInformation.", Constant.SUCCESS, Constant.STATUS_SUCCESS,
					"BusInformation.");
		} catch (Exception e) {
			log.error("Exception while finding bus information...." + busNumber + " " + e.getMessage(), e);
			ResponseUtil.prepareResponse(response, "Something went wrong.", Constant.FAILURE, Constant.STATUS_FAIL,
					e.getMessage());
		}
		return response;
	}

	@Override
	public CommonResponseDTO getBusByTime(BusDTO request) {

		log.info("findByBookingId of PropertyServiceImpl--id=" + request);
		DataResponseDTO response = new DataResponseDTO();
		if (request == null) {
			ResponseUtil.prepareResponse(response, "Please send booking-id.", Constant.FAILURE, Constant.STATUS_FAIL);
			return response;
		}
		List<BusDTO> busResponseList = new ArrayList<>();
		List<BusEntity> booking = busRepository.findBySourceAndDestination(request.getSource(),
				request.getDestination());
		try {
			Map<String, Object> data = new HashMap<String, Object>();
			if (booking != null) {
				for (BusEntity busEntity : booking) {
					if (booking != null) {
						BusDTO bookingResponse = new BusDTO();
						bookingResponse.setBusNumber(busEntity.getBusNumber());
						bookingResponse.setAmount(busEntity.getAmount());
						bookingResponse.setAvailableSeats(busEntity.getAvailableSeats());
						bookingResponse.setDriverName(busEntity.getDriverName());
						bookingResponse.setDriverMobileNumber(busEntity.getDriverMobileNumber());
						bookingResponse.setBusCategory(busEntity.getBusCategory());
						bookingResponse.setMorningTime(busEntity.getMorningTime());
						bookingResponse.setEveningTime(busEntity.getEveningTime());
						busResponseList.add(bookingResponse);
					}
				}
				data.put("Bus List.", busResponseList);
				response.setData(data);
				ResponseUtil.prepareResponse(response, "prefered bus details..", Constant.SUCCESS,
						Constant.STATUS_SUCCESS);

			} else {
				log.error("No data found.");
				ResponseUtil.prepareResponse(response, "No data found.", Constant.FAILURE, Constant.STATUS_FAIL,
						"No data found.");
			}

		} catch (Exception e) {
			log.error("Exception while finding Booking details by id.." + request + " " + e.getMessage(), e);
			ResponseUtil.prepareResponse(response, "Something went wrong.", Constant.FAILURE, Constant.STATUS_FAIL,
					e.getMessage());
		}
		return response;

	}

}

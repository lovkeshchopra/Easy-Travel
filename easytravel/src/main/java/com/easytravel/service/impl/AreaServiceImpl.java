package com.easytravel.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easytravel.entity.AreaInfoEntity;
import com.easytravel.repository.AreaInfoRepository;
import com.easytravel.responseDTO.AreaInfoResponseDTO;
import com.easytravel.responseDTO.AreaResponseDTO;
import com.easytravel.responseDTO.CommonResponseDTO;
import com.easytravel.service.AreaService;
import com.easytravel.utils.Constant;
import com.easytravel.utils.ResponseUtil;

@Service
public class AreaServiceImpl implements AreaService {

	private final Logger log = LoggerFactory.getLogger(AreaServiceImpl.class);

	@Autowired
	private AreaInfoRepository areaInfoRepository;

	@Override
	public CommonResponseDTO addAreaInfo(AreaInfoResponseDTO requestDTO) {
		log.info("addAreaInfo in AreaInfoServiceImpl");
		CommonResponseDTO response = new CommonResponseDTO();
		try {
			if (requestDTO.getState() == null || requestDTO.getState().isEmpty()) {
				log.info("please enter a state.");
				ResponseUtil.prepareResponse(response, "please enter a state.", Constant.FAILURE, Constant.STATUS_FAIL);
				return response;
			}
			if (requestDTO.getCity() == null || requestDTO.getCity().isEmpty()) {
				log.info("please enter a city.");
				ResponseUtil.prepareResponse(response, "please enter a city.", Constant.FAILURE, Constant.STATUS_FAIL);
				return response;
			}
			if (requestDTO.getArea() == null || requestDTO.getArea().isEmpty()) {
				log.info("please enter an area.");
				ResponseUtil.prepareResponse(response, "please enter an area.", Constant.FAILURE, Constant.STATUS_FAIL);
				return response;
			}
			AreaInfoEntity areaRecord = new AreaInfoEntity();
			BeanUtils.copyProperties(requestDTO, areaRecord);
			String state = requestDTO.getState();
			String city = requestDTO.getCity();
			String area = requestDTO.getArea();
			String pincode = requestDTO.getPincode();
			AreaInfoEntity areaInfoRecord = areaInfoRepository.findByStateAndCityAndArea(state, city, area);
			if (areaInfoRecord != null) {
				log.info("AreaInfoRecord already exist.");
				ResponseUtil.prepareResponse(response, "AreaInfoRecord already exist.", Constant.SUCCESS,
						Constant.STATUS_SUCCESS, "data already exist.");
				return response;
			}
			areaRecord.setState(state);
			areaRecord.setCity(city);
			areaRecord.setArea(area);
			areaRecord.setPincode(pincode);

			AreaInfoEntity record = areaInfoRepository.save(areaRecord);
			if (record != null) {
				log.info("AreaInfo saved successfully");
				ResponseUtil.prepareResponse(response, "AreaInfo saved successfully.", Constant.SUCCESS,
						Constant.STATUS_SUCCESS, "data added successfully.");
			} else {
				log.info("AreaInfo doesn't saved");
				ResponseUtil.prepareResponse(response, "AreaInfo doesn't saved.", Constant.FAILURE,
						Constant.STATUS_FAIL, "data doesn't saved.");
			}
		} catch (Exception e) {
			log.error("Exception while adding areaInfo " + e.getMessage(), e);
			ResponseUtil.prepareResponse(response, "Something went wrong.", Constant.FAILURE, Constant.STATUS_FAIL,
					e.getMessage());
		}

		return response;
	}

	@Override
	public CommonResponseDTO areaDetail(Map<String, String> request) {
		AreaResponseDTO response = new AreaResponseDTO();
		if (!request.containsKey("city")) {
			ResponseUtil.prepareResponse(response, "please provide key as city.", Constant.FAILURE,
					Constant.STATUS_FAIL, "please provide key as city.");
			return response;
		}
		
		String city = request.get("city");
		if (city == null || city.isEmpty()) {
			log.info("please enter a city");
			ResponseUtil.prepareResponse(response, "please enter a city.", Constant.FAILURE, Constant.STATUS_FAIL);
			return response;
		}
		try {
			log.info("areaDetail of AreaInfoServiceImpl :" + city);
			List<String> areaInfos = areaInfoRepository.getAreas(city);
			if (areaInfos.isEmpty()) {
				log.info("No Data found, for given city and state.");
				ResponseUtil.prepareResponse(response, "No data found, for given city and state.", Constant.FAILURE,
						Constant.STATUS_FAIL, "No data found, for given city and state.");
				return response;
			}
			response.setData(areaInfos);

			ResponseUtil.prepareResponse(response, "area details", Constant.SUCCESS, Constant.STATUS_SUCCESS);
		} catch (Exception e) {
			log.error("Exception while getting areas for following city :" + city);
			ResponseUtil.prepareResponse(response, "Something went wrong.", Constant.FAILURE, Constant.STATUS_FAIL,
					e.getMessage());

		}
		return response;
	}

}

package com.kingdee.inte.teamwork.web.controller;

import com.kingdee.inte.teamwork.common.HttpStatusEnum;
import com.kingdee.inte.teamwork.common.ViewResult;
import com.kingdee.inte.teamwork.service.AddrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description: 地址服务
 *
 * @author Administrator
 * @date 2020/7/28 10:12
 */
@RestController
@RequestMapping(value = "/addr-service")
public class AddrServiceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddrServiceController.class);

	@Autowired
	private AddrService service;

	/**
	 * Method Description: Created by whx
	 * 〈 地理编码 〉
	 *
	 * @param address  地址
	 * @param provider 服务提供商
	 * @return ViewResult
	 * @date 2020/7/28 10:23
	 */
	@GetMapping("/geocoder")
	public ViewResult<String> getGeoCoder(String address, String provider) {
		ViewResult<String> vr = ViewResult.instance();
		try {
			vr.code(HttpStatusEnum.OK.code()).msg(HttpStatusEnum.INTERNAL_SERVER_ERROR.reasonPhraseCN())
					.data(service.getGeoCoder(address, provider));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return vr;
	}

	@GetMapping("/reverse-geocoder")
	public ViewResult<String> getReverseGeoCoder(String location, String provider) {
		ViewResult<String> vr = ViewResult.instance();
		try {
			vr.code(HttpStatusEnum.OK.code()).msg(HttpStatusEnum.INTERNAL_SERVER_ERROR.reasonPhraseCN())
					.data(service.getReverseGeoCoder(location, provider));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return vr;
	}

	@GetMapping("/timezone")
	public ViewResult<String> getTimezone(String addrOrLocation, String provider) {
		ViewResult<String> vr = ViewResult.instance();
		try {
			vr.code(HttpStatusEnum.OK.code()).msg(HttpStatusEnum.INTERNAL_SERVER_ERROR.reasonPhraseCN())
					.data(service.getTimezone(addrOrLocation, provider));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return vr;
	}

	@GetMapping("/position")
	public ViewResult<String> getPosition(String ip, String provider) {
		ViewResult<String> vr = ViewResult.instance();
		try {
			vr.code(HttpStatusEnum.OK.code()).msg(HttpStatusEnum.INTERNAL_SERVER_ERROR.reasonPhraseCN())
					.data(service.getPosition(ip, provider));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return vr;
	}

	@GetMapping("/check-address")
	public ViewResult<String> getCheckAddress(String checkAddress, String provider) {
		ViewResult<String> vr = ViewResult.instance();
		try {
			vr.code(HttpStatusEnum.OK.code()).msg(HttpStatusEnum.INTERNAL_SERVER_ERROR.reasonPhraseCN())
					.data(service.getPosition(checkAddress, provider));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return vr;
	}

	@GetMapping("/google-js")
	public String getGoogleJS() {
		String js = null;
		try {
			js = service.getGoogleJS();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return js;

	}
}

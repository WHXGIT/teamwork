package com.kingdee.inte.teamwork.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Andy
 * @version 1.0
 * @date 02/28/2019 10:46
 * @description 用于分发页面跳转请求
 */

@Controller
public class IndexController {
	@GetMapping(value = {"/demo"})
	public String index() {
		return "modules/demo/demo";
	}

	@GetMapping(value = {"/code-submit-list", "/code-submit-list/{id}"})
	public String codeSubmitList() {
		return "modules/code-submit-list/index";
	}

	@GetMapping(value = {"/config-mgt"})
	public String configMgt() {
		return "modules/config-mgt/index";
	}

	@GetMapping(value = {"/addr-service"})
	public String addrService() {
		return "modules/addr-service/index2";
	}
}

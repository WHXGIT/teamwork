package com.kingdee.inte.teamwork.web.controller;

import com.kingdee.inte.teamwork.common.HttpStatusEnum;
import com.kingdee.inte.teamwork.common.ViewResult;
import com.kingdee.inte.teamwork.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description: 用户
 *
 * @author Administrator
 * @date 2020/8/14 9:58
 */
@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService service;

	@GetMapping("/users")
	public ViewResult list() {
		ViewResult vr = ViewResult.instance();
		try {
			List<Map<String, String>> list = new ArrayList<>();
			service.list().forEach(item -> {
				Map<String, String> map = new HashMap<>();
				map.put("value", item.getUsernameCn());
				map.put("label", item.getUsernameCn());
				list.add(map);
			});
			vr.code(HttpStatusEnum.OK.code()).msg(HttpStatusEnum.OK.reasonPhraseCN()).data(list);
		} catch (Exception e) {
			vr.code(HttpStatusEnum.INTERNAL_SERVER_ERROR.code()).msg(HttpStatusEnum.INTERNAL_SERVER_ERROR.reasonPhraseCN()).data(null);
			LOGGER.error(e.getMessage(), e);
		}
		return vr;
	}
}

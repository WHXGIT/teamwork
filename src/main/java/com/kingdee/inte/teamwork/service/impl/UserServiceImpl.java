package com.kingdee.inte.teamwork.service.impl;

import com.kingdee.inte.teamwork.mapper.UserMapper;
import com.kingdee.inte.teamwork.pojo.User;
import com.kingdee.inte.teamwork.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * description:
 *
 * @author Administrator
 * @date 2020/8/14 9:56
 */
@Service
public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserMapper mapper;

	@Override
	public List<User> list() {
		return mapper.list();
	}
}

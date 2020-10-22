package com.kingdee.inte.teamwork.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingdee.inte.teamwork.mapper.DemoMapper;
import com.kingdee.inte.teamwork.pojo.Demo;
import com.kingdee.inte.teamwork.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Andy
 * @version 1.0
 * @date 02/28/2019 16:32
 * @description
 */
@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	private DemoMapper demoMapper;

	private static Integer uuid = 0;


	@Override
	public int create(Demo demo) {
		return demoMapper.create(demo);
	}

	@Override
	public Demo readById(String id) {
		return demoMapper.readById(id);
	}

	@Override
	public int update(Demo demo) {
		return demoMapper.update(demo);
	}

	@Override
	public int deleteById(String id) {
		return demoMapper.deleteById(id);
	}

	@Override
	public PageInfo<Demo> list(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Demo> list = demoMapper.list();
		return new PageInfo<Demo>(list);
	}
}

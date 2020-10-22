package com.kingdee.inte.teamwork.service.impl;

import com.kingdee.inte.teamwork.mapper.ProjectMapper;
import com.kingdee.inte.teamwork.pojo.Project;
import com.kingdee.inte.teamwork.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:
 *
 * @author Administrator
 * @date 2020/10/22 10:49
 */
@Service
public class ProjectServiceImpl implements ProjectService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceImpl.class);

	@Autowired
	private ProjectMapper projectMapper;

	@Cacheable(value = "projects", key = "#number" + "#groupId")
	public List<Project> list(String number, long groupId) {
		List<Project>
		return projectMapper.list(number, groupId);
	}
}

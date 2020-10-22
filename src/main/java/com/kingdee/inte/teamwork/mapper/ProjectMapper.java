package com.kingdee.inte.teamwork.mapper;

import com.kingdee.inte.teamwork.pojo.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * description:
 *
 * @author Administrator
 * @date 2020/8/14 9:48
 */
@Mapper
public interface ProjectMapper {
	List<Project> list(String number, long groupId);
}

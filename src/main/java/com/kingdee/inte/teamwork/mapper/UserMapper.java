package com.kingdee.inte.teamwork.mapper;

import com.kingdee.inte.teamwork.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * description:
 *
 * @author Administrator
 * @date 2020/8/14 9:48
 */
@Mapper
public interface UserMapper {
	List<User> list();
}

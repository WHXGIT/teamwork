package com.kingdee.inte.teamwork.mapper;

import com.kingdee.inte.teamwork.pojo.CodeSubmitList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * description:
 *
 * @author Andy
 * @version 1.0
 * @date 06/26/2020 16:45
 */
@Mapper
public interface CodeSubmitListMapper {
	int create(@Param("codeSubmitList") CodeSubmitList codeSubmitList);

	List<CodeSubmitList> listCodeSubmitList(@Param("creator") String creator,
	                                        @Param("bugNo") String bugNo,
	                                        @Param("keyword") String keyword,
	                                        @Param("startTime") String startTime,
	                                        @Param("endTime") String endTime,
	                                        @Param("projectId") Long projectId,
	                                        @Param("submitTarget") Integer submitTarget);

	List<CodeSubmitList> downloadCodeSubmitList(@Param("startTime") String startTime,
	                                            @Param("endTime") String endTime,
	                                            @Param("creator") String creator,
	                                            @Param("bugNo") String bugNo,
	                                            @Param("keyword") String keyword);

	int deleteCodeSubmitList(@Param("id") String id);

	int finishList(@Param("id") String id);

	int giveUpList(@Param("id") String id);

	int recoveryList(String id);

	int update(@Param("codeSubmitList") CodeSubmitList codeSubmitList);

	CodeSubmitList codeSubmitList(@Param("id") String id);
}

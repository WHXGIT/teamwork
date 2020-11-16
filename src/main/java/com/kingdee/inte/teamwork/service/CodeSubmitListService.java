package com.kingdee.inte.teamwork.service;

import com.github.pagehelper.PageInfo;
import com.kingdee.inte.teamwork.pojo.CodeSubmitList;

import java.util.List;

/**
 * description:
 *
 * @author Andy
 * @version 1.0
 * @date 06/26/2020 16:44
 */
public interface CodeSubmitListService {
	int create(CodeSubmitList codeSubmitList);

	PageInfo<CodeSubmitList> listCodeSubmitList(int pageNum, int pageSize, String creator, String bugNo, String keyword,
	                                            String startTime, String endTime, Long projectId);

	List<CodeSubmitList> downloadCodeSubmitList(String startTime, String endTime, String creator, String bugNo, String keyword);

	int deleteCodeSubmitList(String id);

	int finishList(String id);

	int giveUpList(String id);

	int recoveryList(String id);

	int updateCodeSubmitList(CodeSubmitList codeSubmitList);
}

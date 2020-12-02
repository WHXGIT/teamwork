package com.kingdee.inte.teamwork.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kingdee.inte.teamwork.mapper.CodeSubmitListMapper;
import com.kingdee.inte.teamwork.pojo.CodeSubmitList;
import com.kingdee.inte.teamwork.service.CodeSubmitListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description:
 *
 * @author Andy
 * @version 1.0
 * @date 06/26/2020 16:45
 */
@Service
public class CodeSubmitListServiceImpl implements CodeSubmitListService {

	@Autowired
	private CodeSubmitListMapper mapper;

	@Override
	public int create(CodeSubmitList codeSubmitList) {
		return mapper.create(codeSubmitList);
	}

	@Override
	public PageInfo<CodeSubmitList> listCodeSubmitList(int pageNum, int pageSize, String creator, String bugNo,
	                                                   String keyword, String startTime, String endTime, Long projectId,
	                                                   Integer submitTarget) {
		PageHelper.startPage(pageNum, pageSize);
		List<CodeSubmitList> codeSubmitLists = mapper.listCodeSubmitList(creator, bugNo, keyword, startTime, endTime, projectId, submitTarget);
		PageInfo<CodeSubmitList> pageInfo = new PageInfo<>(codeSubmitLists);
		return pageInfo;
	}

	@Override
	public List<CodeSubmitList> downloadCodeSubmitList(String startTime, String endTime, String creator, String bugNo, String keyword) {
		List<CodeSubmitList> codeSubmitLists = mapper.downloadCodeSubmitList(startTime,  endTime, creator, bugNo, keyword);
		return codeSubmitLists;
	}

	@Override
	public int deleteCodeSubmitList(String id) {
		return mapper.deleteCodeSubmitList(id);
	}

	@Override
	public int finishList(String id) {
		return mapper.finishList(id);
	}

	@Override
	public int giveUpList(String id) {
		return mapper.giveUpList(id);
	}

	@Override
	public int recoveryList(String id) {
		return mapper.recoveryList(id);
	}

	@Override
	public int updateCodeSubmitList(CodeSubmitList codeSubmitList) {
		return mapper.update(codeSubmitList);
	}
}

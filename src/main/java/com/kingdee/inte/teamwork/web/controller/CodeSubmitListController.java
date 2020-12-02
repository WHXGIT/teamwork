package com.kingdee.inte.teamwork.web.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.kingdee.inte.teamwork.common.HttpStatusEnum;
import com.kingdee.inte.teamwork.common.ViewResult;
import com.kingdee.inte.teamwork.pojo.CodeSubmitList;
import com.kingdee.inte.teamwork.service.CodeSubmitListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * description:
 *
 * @author Andy
 * @version 1.0
 * @date 06/25/2020 23:25
 */
@RequestMapping("/tw-csl")
@RestController
public class CodeSubmitListController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CodeSubmitListController.class);

	@Autowired
	private CodeSubmitListService service;

	@GetMapping({"/list", "/list/{id}"})
	public ViewResult<PageInfo<CodeSubmitList>> listCodeSubmitList(@PathVariable(name = "id", required = false) Long projectId,
	                                                               int pageNum,
	                                                               int pageSize,
	                                                               @RequestParam(required = false, name = "creator") String creator,
	                                                               @RequestParam(required = false, name = "bugNo") String bugNo,
	                                                               @RequestParam(required = false, name = "keyword") String keyword,
	                                                               @RequestParam(required = false, name = "startTime") String startTime,
	                                                               @RequestParam(required = false, name = "endTime") String endTime,
	                                                               @RequestParam(required = false, name = "submitTarge") Integer submitTarget) {
		ViewResult vr = ViewResult.instance();
		try {
			PageInfo<CodeSubmitList> data = service.listCodeSubmitList(pageNum, pageSize, creator, bugNo, keyword, startTime, endTime, projectId, submitTarget);
			vr.code(HttpStatusEnum.OK.code()).msg(HttpStatusEnum.OK.reasonPhraseCN()).data(data);
		} catch (Exception e) {
			vr.code(HttpStatusEnum.INTERNAL_SERVER_ERROR.code()).msg(HttpStatusEnum.INTERNAL_SERVER_ERROR.reasonPhraseCN()).data(null);
			LOGGER.error(e.getMessage(), e);
		}
		return vr;
	}

	@GetMapping("/download")
	public void download(@RequestParam(required = false, name = "creator") String creator,
	                     @RequestParam(required = false, name = "startTime") String startTime,
	                     @RequestParam(required = false, name = "endTime") String endTime,
	                     @RequestParam(required = false, name = "bugNo") String bugNo,
	                     @RequestParam(required = false, name = "keyword") String keyword,
	                     HttpServletResponse response) throws IOException {
		try {
			List<CodeSubmitList> data = service.downloadCodeSubmitList(startTime, endTime, creator, bugNo, keyword);
			response.setContentType("application/vnd.ms-excel");
			response.setCharacterEncoding("utf-8");
			// 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
			String fileName = URLEncoder.encode("代码提交申请单", "UTF-8");
			response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
			EasyExcel.write(response.getOutputStream(), CodeSubmitList.class).sheet("代码提交申请单").doWrite(data);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage(), e);
			// 重置response
			response.reset();
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			Map<String, String> map = new HashMap<String, String>();
			map.put("status", "failure");
			map.put("message", "下载文件失败" + e.getMessage());
			response.getWriter().println(JSON.toJSONString(map));
		}
	}

	@GetMapping("/code-submit-list/{id}")
	public ViewResult<CodeSubmitList> getCodeSubmitList(@PathVariable String id) {
		return null;
	}

	@PostMapping("/code-submit-list")
	public ViewResult<Boolean> createCodeSubmitList(@RequestBody CodeSubmitList codeSubmitList) {
		ViewResult vr = ViewResult.instance();
		try {
			String uuid = UUID.randomUUID().toString();
			codeSubmitList.setId(uuid);
			codeSubmitList.setStatus(0);
			int data = service.create(codeSubmitList);
			vr.code(HttpStatusEnum.OK.code()).msg(HttpStatusEnum.OK.reasonPhraseCN()).data(data);
		} catch (Exception e) {
			vr.code(HttpStatusEnum.INTERNAL_SERVER_ERROR.code()).msg(HttpStatusEnum.INTERNAL_SERVER_ERROR.reasonPhraseCN()).data(null);
			LOGGER.error(e.getMessage(), e);
		}
		return vr;
	}

	private String replaceToHtmlBr(String str) {
		String var1 = str.replace("\n", "<br/>");
		var1 = var1.replace(",", "<br/>");
		var1 = var1.replaceAll(" +", " ").replace(" ", "<br/>");
		var1 = var1.replaceAll(" +", " ").replace(" ", "<br/>");
		return var1;
	}

	@PutMapping("/code-submit-list")
	public ViewResult<Boolean> updateCodeSubmitList(@RequestBody CodeSubmitList codeSubmitList) {
		ViewResult vr = ViewResult.instance();
		try {
			int data = service.updateCodeSubmitList(codeSubmitList);
			vr.code(HttpStatusEnum.OK.code()).msg(HttpStatusEnum.OK.reasonPhraseCN()).data(data);
		} catch (Exception e) {
			vr.code(HttpStatusEnum.INTERNAL_SERVER_ERROR.code()).msg(HttpStatusEnum.INTERNAL_SERVER_ERROR.reasonPhraseCN()).data(null);
			LOGGER.error(e.getMessage(), e);
		}
		return vr;
	}

	@DeleteMapping("/code-submit-list/delete/{id}")
	public ViewResult<Boolean> deleteCodeSubmitList(@PathVariable String id) {
		ViewResult vr = ViewResult.instance();
		try {
			int data = service.deleteCodeSubmitList(id);
			vr.code(HttpStatusEnum.OK.code()).msg(HttpStatusEnum.OK.reasonPhraseCN()).data(data);
		} catch (Exception e) {
			vr.code(HttpStatusEnum.INTERNAL_SERVER_ERROR.code()).msg(HttpStatusEnum.INTERNAL_SERVER_ERROR.reasonPhraseCN()).data(null);
			LOGGER.error(e.getMessage(), e);
		}
		return vr;
	}

	@PutMapping("/code-submit-list/finish/{id}")
	public ViewResult<Boolean> finishList(@PathVariable String id) {
		ViewResult vr = ViewResult.instance();
		try {
			int data = service.finishList(id);
			vr.code(HttpStatusEnum.OK.code()).msg(HttpStatusEnum.OK.reasonPhraseCN()).data(data);
		} catch (Exception e) {
			vr.code(HttpStatusEnum.INTERNAL_SERVER_ERROR.code()).msg(HttpStatusEnum.INTERNAL_SERVER_ERROR.reasonPhraseCN()).data(null);
			LOGGER.error(e.getMessage(), e);
		}
		return vr;
	}

	@PutMapping("/code-submit-list/give-up/{id}")
	public ViewResult<Boolean> giveUpList(@PathVariable String id) {
		ViewResult vr = ViewResult.instance();
		try {
			int data = service.giveUpList(id);
			vr.code(HttpStatusEnum.OK.code()).msg(HttpStatusEnum.OK.reasonPhraseCN()).data(data);
		} catch (Exception e) {
			vr.code(HttpStatusEnum.INTERNAL_SERVER_ERROR.code()).msg(HttpStatusEnum.INTERNAL_SERVER_ERROR.reasonPhraseCN()).data(null);
			LOGGER.error(e.getMessage(), e);
		}
		return vr;
	}

	@PutMapping("/code-submit-list/recovery/{id}")
	public ViewResult<Boolean> recoveryList(@PathVariable String id) {
		ViewResult vr = ViewResult.instance();
		try {
			int data = service.recoveryList(id);
			vr.code(HttpStatusEnum.OK.code()).msg(HttpStatusEnum.OK.reasonPhraseCN()).data(data);
		} catch (Exception e) {
			vr.code(HttpStatusEnum.INTERNAL_SERVER_ERROR.code()).msg(HttpStatusEnum.INTERNAL_SERVER_ERROR.reasonPhraseCN()).data(null);
			LOGGER.error(e.getMessage(), e);
		}
		return vr;
	}

}

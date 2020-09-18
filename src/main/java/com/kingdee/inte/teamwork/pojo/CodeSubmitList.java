package com.kingdee.inte.teamwork.pojo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

/**
 * description: 代码提交申请单
 *
 * @author Andy
 * @version 1.0
 * @date 06/25/2020 23:04
 */

public class CodeSubmitList {
	@ExcelIgnore
	private String id;
	@ExcelProperty(value = "提交时间")
	private String createTime;
	@ExcelIgnore
	private String updateTime;
	@ExcelProperty(value = "提交人")
	private String creator;
	@ExcelProperty(value = "提交目标")
	private String submitTarget;
	@ExcelProperty(value = "bug号")
	private String bugNo;
	@ExcelProperty(value = "需求说明", converter = BrEnterConverter.class)
	private String requireDesc;
	@ExcelProperty(value = "代码修改说明", converter = BrEnterConverter.class)
	private String codeModifyDesc;
	@ExcelProperty(value = "影响范围", converter = BrEnterConverter.class)
	private String scope;
	@ExcelProperty(value = "是否自测", converter = SelfTestConverter.class)
	private int isSelfTest;
	@ExcelProperty(value = "冒烟测试", converter = SmokeTestConverter.class)
	private int smokeTest;
	@ExcelProperty(value = "Java文件名", converter = BrEnterConverter.class)
	private String javaFiles;
	@ExcelProperty(value = "元数据文件", converter = BrEnterConverter.class)
	private String metaFiles;
	@ExcelProperty(value = "数据库脚本", converter = BrEnterConverter.class)
	private String dbScript;
	@ExcelProperty(value = "数据状态", converter = StatusConverter.class)
	private int status;
	@ExcelProperty(value = "备注", converter = BrEnterConverter.class)
	private String remark;

	public CodeSubmitList() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getSubmitTarget() {
		return submitTarget;
	}

	public void setSubmitTarget(String submitTarget) {
		this.submitTarget = submitTarget;
	}

	public String getBugNo() {
		return bugNo;
	}

	public void setBugNo(String bugNo) {
		this.bugNo = bugNo;
	}

	public String getRequireDesc() {
		return requireDesc;
	}

	public void setRequireDesc(String requireDesc) {
		this.requireDesc = requireDesc;
	}

	public String getCodeModifyDesc() {
		return codeModifyDesc;
	}

	public void setCodeModifyDesc(String codeModifyDesc) {
		this.codeModifyDesc = codeModifyDesc;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public int getIsSelfTest() {
		return isSelfTest;
	}

	public void setIsSelfTest(int isSelfTest) {
		this.isSelfTest = isSelfTest;
	}

	public int getSmokeTest() {
		return smokeTest;
	}

	public void setSmokeTest(int smokeTest) {
		this.smokeTest = smokeTest;
	}

	public String getJavaFiles() {
		return javaFiles;
	}

	public void setJavaFiles(String javaFiles) {
		this.javaFiles = javaFiles;
	}

	public String getMetaFiles() {
		return metaFiles;
	}

	public void setMetaFiles(String metaFiles) {
		this.metaFiles = metaFiles;
	}

	public String getDbScript() {
		return dbScript;
	}

	public void setDbScript(String dbScript) {
		this.dbScript = dbScript;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "CodeSubmitList{" +
				"id='" + id + '\'' +
				", createTime='" + createTime + '\'' +
				", updateTime='" + updateTime + '\'' +
				", creator='" + creator + '\'' +
				", submitTarget='" + submitTarget + '\'' +
				", bugNo='" + bugNo + '\'' +
				", requireDesc='" + requireDesc + '\'' +
				", codeModifyDesc='" + codeModifyDesc + '\'' +
				", scope='" + scope + '\'' +
				", isSelfTest=" + isSelfTest +
				", smokeTest=" + smokeTest +
				", javaFiles='" + javaFiles + '\'' +
				", metaFiles='" + metaFiles + '\'' +
				", dbScript='" + dbScript + '\'' +
				", status=" + status +
				", remark='" + remark + '\'' +
				'}';
	}
}

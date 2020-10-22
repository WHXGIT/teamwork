package com.kingdee.inte.teamwork.pojo;

/**
 * description: 分组
 *
 * @author Administrator
 * @date 2020/10/22 10:43
 */
public class Group {
	private long id;
	private String number;
	private String name;
	private String desc;

	public Group() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}

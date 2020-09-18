package com.kingdee.inte.teamwork.pojo;

/**
 * description: 国际化用户
 *
 * @author Administrator
 * @date 2020/8/14 9:39
 */
public class User {
	private String id;
	private String username;
	private String usernameCn;
	private String passwd;
	private String type;
	private String createTime;
	private String updateTime;

	public User() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsernameCn() {
		return usernameCn;
	}

	public void setUsernameCn(String usernameCn) {
		this.usernameCn = usernameCn;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "User{" +
				"id='" + id + '\'' +
				", username='" + username + '\'' +
				", usernameCn='" + usernameCn + '\'' +
				", passwd='" + passwd + '\'' +
				", type='" + type + '\'' +
				", createTime='" + createTime + '\'' +
				", updateTime='" + updateTime + '\'' +
				'}';
	}
}

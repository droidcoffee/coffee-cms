package coffee.cms.core.bean;

import coffee.util.database.annotation.Bean;
import coffee.util.database.annotation.Id;

/**
 * 用户
 * 
 * @author coffee
 * 
 *         2012-12-24 下午9:16:17
 */
@Bean(name = "users")
public class UserBean {
	@Id
	private int id;
	private String uname;
	private String pwd;
	private int role;
	private long addTime; // 账号的创建时间
	private long lastUpdateTime; // 账号信息的最后修改时间
	private long lastLoginTime; // 最后登录时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public long getAddTime() {
		return addTime;
	}

	public void setAddTime(long addTime) {
		this.addTime = addTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public long getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
}

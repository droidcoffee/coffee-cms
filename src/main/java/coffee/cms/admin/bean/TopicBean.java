package coffee.cms.admin.bean;

import coffee.util.database.annotation.Bean;
import coffee.util.database.annotation.Id;

/**
 * 主题
 * 
 * @author coffee<br>
 *         2013-4-13上午8:20:21
 */
@Bean(name = "topic")
public class TopicBean {
	@Id
	private int id;
	private String title;// 标题
	private String content;// 内容
	private long addTime;
	private long updateTime;
	private int uid; // 管理员ID
	private String uname;// 管理员用户名
	private int expire = 0;// 是否过期 ,0未过期,1过期

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getAddTime() {
		return addTime;
	}

	public void setAddTime(long addTime) {
		this.addTime = addTime;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}
}

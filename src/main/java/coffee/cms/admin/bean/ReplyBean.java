package coffee.cms.admin.bean;

import coffee.util.database.annotation.Bean;
import coffee.util.database.annotation.Id;

/**
 * 评论
 * 
 * @author coffee<br>
 *         2013-4-13上午8:27:48
 */
@Bean(name = "reply")
public class ReplyBean {
	@Id
	private int id;
	private int tid; // topic_ID
	private String content;// 内容
	private long addTime;// 评论时间
	private int uid; // 用户ID
	private String uname;// 用户名
	private int energy;// 能量值
	private int score;// 分数值

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
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

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}

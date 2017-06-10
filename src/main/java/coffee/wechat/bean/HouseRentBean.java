package coffee.wechat.bean;

/**
 * 房租求租
 *
 * @author coffee
 * 20122012-12-9下午7:11:39
 */
public class HouseRentBean {
	/**
	 * 
	 */
	private String id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 求租地点
	 */
	private String plcae;
	/**
	 * 最低价
	 */
	private int minRice;
	/**
	 * 最大价
	 */
	private int maxRice;
	/**
	 * 类型：
	 * 一室：二室：三室：均可
	 */
	private String type;
	/**
	 * 联系人姓名
	 */
	private String linkName;
	/**
	 * 描述
	 */
	private String desc;
	/**
	 * 电话(一个超链接)
	 */
	private String phone;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPlcae() {
		return plcae;
	}
	public void setPlcae(String plcae) {
		this.plcae = plcae;
	}
	public int getMinRice() {
		return minRice;
	}
	public void setMinRice(int minRice) {
		this.minRice = minRice;
	}
	public int getMaxRice() {
		return maxRice;
	}
	public void setMaxRice(int maxRice) {
		this.maxRice = maxRice;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}

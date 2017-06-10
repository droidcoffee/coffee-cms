package coffee.cms.admin.bean;

import coffee.util.database.annotation.Bean;
import coffee.util.database.annotation.Id;

/**
 * 合作伙伴
 * 
 * @author coffee
 * 
 *         2012-12-25 下午8:05:19
 */
@Bean(name = "d_partner")
public class PartnerBean {
	@Id
	private int id;
	private String uid;
	private String pname;
	/**
	 * 纬度
	 */
	private double lat;
	/**
	 * 经度
	 */
	private double lon;
	/**
	 * 位置信息
	 */
	private String address;
	/**
	 * 详情
	 */
	private String detailUrl;
	/**
	 * 电话，联系方式
	 */
	private String phone;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 是否可见 0：不可见 1：可见 1以上的该值越大显示的时候越靠前
	 */
	private int visible;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetailUrl() {
		return detailUrl;
	}

	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}

	public String getPhone() {
		if (phone == null) {
			return "";
		}
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}

	@Override
	public String toString() {
		return pname + "\n" + address + "\n" + getPhone() + "\n";
	}
}

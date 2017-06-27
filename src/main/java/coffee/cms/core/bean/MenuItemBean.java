package coffee.cms.core.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单项
 * 
 * @author coffee<br>
 * 		2017年6月23日上午9:59:23
 */
@SuppressWarnings("serial")
public class MenuItemBean implements Serializable {
	private String id;
	private String name;
	private String url;
	private String pid;

	private List<MenuItemBean> children = new ArrayList<MenuItemBean>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public List<MenuItemBean> getChildren() {
		return children;
	}

	public void setChildren(List<MenuItemBean> children) {
		this.children = children;
	}
}

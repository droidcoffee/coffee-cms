package com.cms.record;

/**
 * 菜单管理
 * 
 * @author coffee<br>
 * 		2017年5月19日下午2:10:14
 */
public class MenuRecord {
	private String id;
	private String name;
	private String parent;
	private String action;
	
	////
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
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
}

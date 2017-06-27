package com.cms.controller.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * pc后台显示的菜单项
 * 
 * @author coffee<br>
 *         2017年6月23日下午3:41:25
 */
public class MenuBean {
	private Integer id;
	private String name;
	private String action;
	private Integer parent;
	private List<MenuBean> child = new ArrayList<>();

	//
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	public List<MenuBean> getChild() {
		return child;
	}

	public void setChild(List<MenuBean> child) {
		this.child = child;
	}
}

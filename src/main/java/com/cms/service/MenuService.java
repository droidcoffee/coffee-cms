package com.cms.service;

import java.util.List;

import com.cms.controller.bean.MenuBean;

public interface MenuService {
	public List<MenuBean> queryMenus(Integer userId);
}

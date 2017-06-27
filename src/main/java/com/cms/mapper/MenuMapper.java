package com.cms.mapper;

import java.util.List;

import com.cms.controller.bean.MenuBean;

public interface MenuMapper {
	
	public List<MenuBean> queryMenus(Integer userId);
}

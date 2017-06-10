package com.cms.service;

import java.util.List;

import com.cms.record.MenuRecord;

public interface MenuService {
	public List<MenuRecord> queryMenus(Integer userId);
}

package com.cms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.controller.bean.MenuBean;
import com.cms.mapper.MenuMapper;
import com.cms.service.MenuService;
import com.shouhuobao.service.BaseService;

@Service
public class MenuServiceImpl extends BaseService implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public List<MenuBean> queryMenus(Integer userId) {
		List<MenuBean> items = menuMapper.queryMenus(userId);
		Map<Integer, MenuBean> map = new HashMap<>();
		if (isNotEmpty(items)) {
			for (MenuBean item : items) {
				map.put(item.getId(), item);
			}
			//
			for (MenuBean item : items) {
				if (isNotEmpty(item.getParent())) {
					MenuBean parentItem = map.get(item.getParent());
					parentItem.getChild().add(item);
				}
			}
		}
		return items;
	}

}

package com.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cms.controller.bean.MenuBean;
import com.cms.service.MenuService;
import com.shouhuobao.app.ApiResponse;
import com.shouhuobao.controller.BaseController;

/**
 * 用户管理
 * 
 * @author coffee<br>
 *         2017年5月19日下午1:46:10
 */
@Controller
@RequestMapping("user")
public class UserController extends BaseController {

	@Autowired
	private MenuService menuService;

	@RequestMapping("getMenus")
	public ModelAndView login(@RequestParam(name = "userId") Integer userId) {
		ApiResponse<List<MenuBean>> resp = new ApiResponse<>();
		List<MenuBean> items = menuService.queryMenus(userId);
		resp.setResult(items);
		//return toJson(resp);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject(items);
		mv.setViewName("/admin/index.jsp");
		return mv;
	}

}

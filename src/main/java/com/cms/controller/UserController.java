package com.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	private MenuService menuService;
	
	@ResponseBody
	@RequestMapping("getMenus")
	public String login(@RequestParam(name = "userId") Integer userId) {
		ApiResponse<String> resp = new ApiResponse<>();
		menuService.queryMenus(userId);
		return toJson(resp);
	}

}

package coffee.cms.core.action;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import coffee.cms.core.Constants;
import coffee.cms.core.bean.MenuItemBean;
import coffee.cms.core.bean.UserBean;
import coffee.cms.core.service.MenuItemService;
import coffee.util.database.Pager;
import coffee.util.database.Session;
import coffee.util.database.core.DBUtils;
import coffee.util.log.Log;

/**
 * 用户管理
 * 
 * @author coffee
 * 
 *         2012-12-24 下午9:06:50
 */
public class UserAction extends Action {

	public UserAction() {
		super.modelClass = UserBean.class;
		super.tableName = DBUtils.getTableName(UserBean.class);
	}

	@Override
	protected Object reflectObject(HttpServletRequest request, boolean isUpdate) {
		UserBean user = (UserBean) super.reflectObject(request, isUpdate);
		if (isUpdate == false) {
			user.setAddTime(System.currentTimeMillis());
		}
		user.setLastUpdateTime(System.currentTimeMillis());
		String[] roles = request.getParameterValues("roles");
		if (roles != null) {
			int roleVal = 0;
			for (String role : roles) {
				roleVal += 1 << Integer.valueOf(role);
			}
			user.setRole(roleVal);
		}
		return user;
	}

	@Override
	public void query(HttpServletRequest request) {
		String uname = request.getParameter("uname");
		uname = getEncodedString(uname);
		String sql = "select * from " + super.tableName + " where true";
		if (uname != null) {
			sql += " and uname like '%" + uname + "%' ";
		}
		sql += " order by lastUpdateTime desc";
		Session session = new Session();
		String start = request.getParameter("pager.offset");
		try {
			Pager<UserBean> pager = session.queryForPager(sql, getInt(start, 0), 10, UserBean.class);
			request.setAttribute("pager", pager);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("uname", uname);
	}

	/**
	 * 用户登录
	 * 
	 * @param request
	 */
	public void login(HttpServletRequest request) {
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		String sql = "select * from users where uname = ? and pwd = ?";
		Session session = new Session();
		try {
			UserBean user = session.queryForEntity(UserBean.class, sql, uname, pwd);
			if (user != null) {
				Log.info(this, uname + " Login Success");
				request.getSession().setAttribute(Constants.KEY_SESSION_USER, user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * 初始化用户目录结构
	 */
	public void initUserMenu(HttpSession session) {
		// 初始化CMS后台目录
		MenuItemService menuService = new MenuItemService();
		List<MenuItemBean> menuItems = menuService.getMenuItems(session);
		session.setAttribute("menus", menuItems);
	}
}

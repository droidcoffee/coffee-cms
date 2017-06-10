package coffee.cms.admin.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import coffee.cms.admin.bean.UserJIDBean;
import coffee.cms.core.action.Action;
import coffee.util.database.Pager;
import coffee.util.database.Session;
import coffee.util.database.core.DBUtils;
import coffee.util.servlet.ParameterReflect;

/**
 * XMPP用户JID表
 * 
 * @author coffee 20122012-11-8下午7:56:45
 */
public class UserJIDAction extends Action {

	public UserJIDAction() {
		super.modelClass = UserJIDBean.class;
		super.tableName = DBUtils.getTableName(UserJIDBean.class);
	}

	/**
	 * 新增记录
	 * 
	 * @param request
	 * @param modelClass
	 */
	public Object insert(HttpServletRequest request) {
		UserJIDBean model = (UserJIDBean) new ParameterReflect().invoke(
				request, modelClass);
		Session session = new Session();
		try {
			model.setJid(model.getJid());
			session.insert(model);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return model;
	}

	@Override
	public void query(HttpServletRequest request) {
		String jid = request.getParameter("jid");
		String sql = "select * from " + super.tableName + " where true";
		if (jid != null) {
			sql += " and jid like '%" + jid + "%' ";
		}
		Session session = new Session();
		try {
			Pager<UserJIDBean> pager = session.queryForPager(sql, 0, 10,
					UserJIDBean.class);
			request.setAttribute("pager", pager);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		request.setAttribute("jid", jid);
	}
}

package coffee.cms.admin.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import coffee.cms.admin.bean.PartnerBean;
import coffee.cms.core.action.Action;
import coffee.util.database.Pager;
import coffee.util.database.Session;
import coffee.util.database.core.DBUtils;

/**
 * 合作伙伴
 * 
 * @author coffee 20122012-11-8下午7:56:45
 */
public class PartnerAction extends Action {

	public PartnerAction() {
		super.modelClass = PartnerBean.class;
		super.tableName = DBUtils.getTableName(PartnerBean.class);
	}

	@Override
	public void query(HttpServletRequest request) {
		super.query(request);
		//店铺名称 
		String pname = getParameterByEncode(request, "pname");
		//可见性：0不会被显示，1以上的数越大显示的优先级越高
		String visible = request.getParameter("visible");
		if (isEmpty(visible)) {
			visible = "1";
		}
		String sql = "select * from " + super.tableName + //
				" where visible = " + visible;
		if (pname != null) {
			sql += " and pname like '%" + pname + "%' ";
		}
		sql += " order by id desc";
		Session session = new Session();

		try {
			Pager<PartnerBean> pager = session.queryForPager(sql, pagerOffset,
					pagerSize, PartnerBean.class);
			request.setAttribute("pager", pager);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		request.setAttribute("pname", pname);
		request.setAttribute("visible", visible);
	}
}

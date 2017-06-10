package coffee.cms.admin.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import coffee.cms.admin.bean.ReplyBean;
import coffee.cms.admin.bean.TopicBean;
import coffee.cms.core.action.Action;
import coffee.util.database.Pager;
import coffee.util.database.Session;
import coffee.util.database.core.DBUtils;

/**
 * 评论
 * 
 * @author coffee <br>
 *         2012-11-8下午7:56:45
 */
public class ReplyAction extends Action {

	public ReplyAction() {
		super.modelClass = ReplyBean.class;
		super.tableName = DBUtils.getTableName(ReplyBean.class);
	}

	@Override
	protected Object reflectObject(HttpServletRequest request, boolean isUpdate) {
		TopicBean bean = (TopicBean) super.reflectObject(request, isUpdate);
		// 插入消息
		if (isUpdate == false) {
			bean.setAddTime(System.currentTimeMillis());
		}
		return bean;
	}

	@Override
	public void query(HttpServletRequest request) {
		super.query(request);
		// topic ID
		String tid = getParameterByEncode(request, "tid");
		String sql = "select * from reply" + //
				" where true ";
		if (isEmpty(tid) == false) {
			sql += " and tid = " + tid;
		}
		sql += " order by id desc";
		Session session = new Session();
		try {
			Pager<ReplyBean> pager = session.queryForPager(sql, pagerOffset,
					pagerSize, ReplyBean.class);
			request.setAttribute("pager", pager);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		 request.setAttribute("tid", tid);
	}

	/**
	 * 更新过期的expired sql 这样写竟然报错<br>
	 * update topic set expire=1 where id in (select min(id) from topic where
	 * expire = 0)<br>
	 * 无语了 You can't specify target table 'topic' for update in FROM clause<br>
	 */
	public static void updateExpiredTopic() {
		String sql = "UPDATE topic SET expire=1 WHERE id IN  (SELECT t.a FROM (SELECT MIN(id) a FROM topic WHERE expire = 0) t)";
		Session session = new Session();
		try {
			session.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		session.close();
	}
}

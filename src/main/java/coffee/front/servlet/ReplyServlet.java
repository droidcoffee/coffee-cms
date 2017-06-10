package coffee.front.servlet;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import coffee.cms.admin.bean.ReplyBean;
import coffee.cms.core.action.Action;
import coffee.util.database.Session;
import coffee.util.net.NetUtils;
import coffee.util.servlet.ParameterReflect;

/**
 * 发表主题
 * 
 * @author coffee<br>
 *         2013-4-13上午9:47:57
 */
public class ReplyServlet extends Action {

	/**
	 * 发表回复(每次回复的时候 更新一下排行榜)
	 * 
	 * @param request
	 */
	public void postReply(HttpServletRequest request) {
		String tid = request.getParameter("tid");
		if (isEmpty(tid)) {
			tid = "5";
		}
		ReplyBean bean = new ParameterReflect()
				.invoke(request, ReplyBean.class);
		bean.setAddTime(System.currentTimeMillis());
		bean.setTid(Integer.valueOf(tid.trim()));
		bean.setUname(new NetUtils().getIpAddr(request));
		// 震撼度
		float val = new Random().nextFloat() * 10;
		// 分数
		int score = (int) (val * bean.getEnergy());
		bean.setScore(score);
		Session session = new Session();
		try {
			session.insert(bean);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * 
	 * @param request
	 * @param tid
	 */
	public void queryReply(HttpServletRequest request) {
		Session session = new Session();
		try {
			String sql = "select * from reply where tid = ? order by score desc,energy desc";
			String tid = request.getServletContext().getAttribute("tid") + "";
			// 查下一道题的最高分
			List<ReplyBean> maxScore = session.queryForList(ReplyBean.class,
					sql, Integer.valueOf(tid));
			// 最新鲜 10条
			sql = "select * from reply where tid = ? order by id desc limit 0,10";
			request.getSession().getServletContext()
					.setAttribute("maxScore", maxScore);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * 查询某人的
	 * 
	 * @param request
	 * @param tid
	 * @param uname
	 */
	public void queryMyReply(HttpServletRequest request, String uname) {
		if (uname == null) {
			uname = new NetUtils().getIpAddr(request);
		}
		Session session = new Session();
		try {
			String sql = "select * from reply where tid = ? and uname = ? order by id desc limit 0,1";
			String tid = request.getServletContext().getAttribute("tid") + "";
			List<ReplyBean> myReplys = session.queryForList(ReplyBean.class,
					sql, Integer.valueOf(tid), uname);
			if(myReplys.size() > 0){
				request.setAttribute("myReply", myReplys.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

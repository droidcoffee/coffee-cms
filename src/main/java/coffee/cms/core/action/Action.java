package coffee.cms.core.action;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import coffee.util.database.Session;
import coffee.util.servlet.ParameterReflect;

public class Action {
	protected String tableName;
	protected Class<?> modelClass;

	/**
	 * 分页查询的起始偏移量
	 */
	protected int pagerOffset;
	/**
	 * 分页查询的大小
	 */
	protected int pagerSize;

	/**
	 * 如果新增|更新记录的时候需要手动设置一些变量<br>
	 * 请override该方法<br>
	 * 具体使用见这里{@link UserAction#reflectObject(HttpServletRequest)}
	 * 
	 * @param request
	 * @param isUpdate
	 *            该操作是insert还是update<br>
	 *            目前该参数只有子类override的时候用到<br>
	 * @return
	 */
	protected Object reflectObject(HttpServletRequest request, boolean isUpdate) {
		Object model = new ParameterReflect().invoke(request, modelClass);
		return model;
	}

	public void query(HttpServletRequest request) {
		String offset = request.getParameter("pager.offset");
		pagerOffset = getInt(offset, 0);
		String size = request.getParameter("pager.size");
		pagerSize = getInt(size, 10);
		// set attributes
		request.setAttribute("pager.size", pagerSize);
		request.setAttribute("pagerSize", pagerSize);
		request.setAttribute("pager.offset", pagerOffset);
	}

	/**
	 * 新增记录
	 * 
	 * @param request
	 * @param modelClass
	 */
	public Object insert(HttpServletRequest request) {
		Object model = reflectObject(request, false);
		Session session = new Session();
		try {
			session.insert(model);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return model;
	}

	public void toUpdate(HttpServletRequest request) {
		String sid = request.getParameter("sid");
		Session session = new Session();
		try {
			Object model = session
					.queryForEntity(Long.valueOf(sid), modelClass);
			request.setAttribute("item", model);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void update(HttpServletRequest request) {
		Object model = reflectObject(request, true);
		Session session = new Session();
		try {
			session.update(model);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * @param request
	 * @param modelClass
	 */
	public void delete(HttpServletRequest request) {
		String sid = request.getParameter("sid");
		Session session = new Session();
		try {
			String[] ids = sid.split(",");
			if (ids.length > 1) {
				session.deleteBatch(ids, modelClass);
			} else {
				session.delete(Long.valueOf(sid), modelClass);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/**
	 * 以下是工具类
	 */
	public boolean isEmpty(String str) {
		if (str != null && str.trim().length() > 0) {
			return false;
		}
		return true;
	}

	public int getInt(String str, int defValue) {
		try {
			int intVal = Integer.valueOf(str);
			return intVal;
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return defValue;
	}

	public String getEncodedString(String requestValue) {
		if (requestValue == null) {
			return null;
		}
		try {
			requestValue = new String(requestValue.getBytes("iso_8859_1"),
					"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return requestValue;
	}

	/**
	 * 获取指定param的参数值 该值经过编码
	 */
	public String getParameterByEncode(HttpServletRequest request, String param) {
		String paramValue = request.getParameter(param);
		paramValue = getEncodedString(paramValue);
		return paramValue;
	}
}

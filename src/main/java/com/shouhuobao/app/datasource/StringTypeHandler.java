package com.shouhuobao.app.datasource;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * 覆盖 {@link org.apache.ibatis.type.StringTypeHandler}
 * 
 * @author Clinton Begin
 */
public class StringTypeHandler extends BaseTypeHandler<String> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
		ps.setString(i, parameter);
	}

	@Override
	public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String result = rs.getString(columnName);
		return handlerResult(result);
	}

	@Override
	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String result = rs.getString(columnIndex);
		return handlerResult(result);
	}

	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String result = cs.getString(columnIndex);
		return handlerResult(result);
	}

	/**
	 * 处理mysql timestamp类型的字段, 如果java的字段定义为Strng <br>
	 * rs.getString的结果类似2017-05-25 16:54:50.0的格式
	 * 
	 * @return
	 */
	private String handlerResult(String result) {
		if (result != null && result.length() == 21 && result.trim().endsWith(".0")) {
			return result.substring(0, result.trim().length() - 2);
		} else {
			return result;
		}
	}
}

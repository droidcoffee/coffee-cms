package com.shouhuobao.app.datasource;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * mybatis 将mysql类型为timestamp的字段格式化java类型为String<br>
 * 
 * 重写long类型的转换, 如果JavaBean的字段
 * 
 * @author coffee<br>
 *         2017年2月23日下午1:53:23
 */
// @MappedJdbcTypes(JdbcType.TIMESTAMP)
public class MyBatisDateHandler extends BaseTypeHandler<Long> {
	// private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * {@link DefaultParameterHandler#setParameters(PreparedStatement)}
	 */
	@Override
	public void setNonNullParameter(PreparedStatement ps, int columnIndex, Long parameter, JdbcType jdbcType) throws SQLException {
		// int columnType = ps.getre.getMetaData().getColumnType(columnIndex);
		// if (columnType == java.sql.Types.TIMESTAMP) {
		try {
			ps.setTimestamp(columnIndex, new Timestamp(parameter));
			// ps.ServantObject(columnIndex, new Timestamp(parameter));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// } else {
		// ps.setLong(columnIndex, Long.valueOf(String.valueOf(parameter)));
		// }
	}

	@Override
	public Long getNullableResult(ResultSet rs, String columnName) throws SQLException {
		int columnIndex = rs.findColumn(columnName);
		return getNullableResult(rs, columnIndex);
	}

	/**
	 * {@link ResultSetWrapper}
	 */
	@Override
	public Long getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		int columnType = rs.getMetaData().getColumnType(columnIndex);
		long columnValue = 0;
		if (java.sql.Types.TIMESTAMP == columnType) {
			columnValue = rs.getTimestamp(columnIndex).getTime();
		} else {
			columnValue = rs.getLong(columnIndex);
		}
		return columnValue;
	}

	@Override
	public Long getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return getNullableResult(cs, columnIndex);
	}

	@Override
	public String toString() {
		return this.getClass() + " " + super.toString();
	}
}

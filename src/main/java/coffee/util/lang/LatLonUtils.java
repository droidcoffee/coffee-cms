package coffee.util.lang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Latitude and Longitude utils
 * 
 * @author coffee
 */
public class LatLonUtils {

	// 每经度单位米;
	public static final double lonUnit = 102834.74258026089786013677476285;
	// 每纬度单位米;
	public static final double latUnit = 111712.69150641055729984301412873;

	/**
	 * 获取距中心坐标指定距离长度的最小、最大经纬度
	 * 
	 * @param lat
	 *            纬度
	 * @param lon
	 *            精度
	 * @param raidus
	 *            半径（以米为单位）
	 * @return 一位数组,长度为4; 分别为 最小纬度,最小经度;最大纬度,最大经度
	 */
	public static double[] getAround(double lat, double lon, int raidus) {

		Double latitude = lat;
		Double longitude = lon;

		Double degree = (24901 * 1609) / 360.0;
		double raidusMile = raidus;

		Double dpmLat = 1 / degree;
		Double radiusLat = dpmLat * raidusMile;
		Double minLat = latitude - radiusLat;
		Double maxLat = latitude + radiusLat;

		Double mpdLng = degree * Math.cos(latitude * (Math.PI / 180));
		Double dpmLng = 1 / mpdLng;
		Double radiusLng = dpmLng * raidusMile;
		Double minLng = longitude - radiusLng;
		Double maxLng = longitude + radiusLng;
		return new double[] { minLat, minLng, maxLat, maxLng };
	}

	/**
	 * 获取距中心坐标指定距离长度的最小、最大经纬度
	 * 
	 * @param lat
	 *            纬度
	 * @param lon
	 *            精度
	 * @param raidus
	 *            半径（以米为单位）
	 * @return 一位数组,长度为4; 分别为 最小纬度,最小经度;最大纬度,最大经度
	 */
	public static double[] getMinMaxLatLon(double lat, double lon, int radius) {
		// 最小纬度 Math.abs(minLat - lat) * latUnit = raidus
		double minLat = lat - radius / latUnit;
		double maxLat = lat + radius / latUnit;
		double minLon = lon - radius / lonUnit;
		double maxLon = lon + radius / lonUnit;
		System.out.println("minLat >> " + minLat);
		System.out.println("maxLat >> " + maxLat);
		System.out.println("minLon >> " + minLon);
		System.out.println("maxLon >> " + maxLon);
		return new double[] { minLat, maxLat, minLon, maxLon };
	}

	public static String SQL_PRE_QUERY_BY_RAIDUS = "SELECT * FROM d_partner "
			+ " WHERE lat > ? AND lat < ? AND lon > ? AND lon < ?" // 1-4最小纬度、最大纬度、最小经度、最大经度
			+ " AND SQRT(" //
			+ "POWER(ABS(lat - ?) * " + lonUnit + ", 2) + " // 5-中心纬度 ,单位长度(纬度)
			+ "POWER(ABS(lon - ?) * " + latUnit + ", 2) "  // 6-中心经度,单位长度(经度)
			+ ") < ?"; // 7-半径

	public static PreparedStatement getPreparedStmtByRadius(double centerLat,
			double centerLon, int radius, Connection conn) {
		try {
			PreparedStatement pstmt = conn
					.prepareStatement(SQL_PRE_QUERY_BY_RAIDUS);
			double[] minMaxLatLon = getMinMaxLatLon(centerLat, centerLon,
					radius);
			// 1-4
			for (int i = 0; i < minMaxLatLon.length; i++) {
				pstmt.setDouble(i + 1, minMaxLatLon[i]);
			}
			// 5 : 中心坐标的纬度
			pstmt.setDouble(5, centerLat);
			// 6 : ..经度
			pstmt.setDouble(6, centerLon);
			// 7 : ..半径
			pstmt.setDouble(7, radius);
			return pstmt;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 计算两个经纬度之间的距离
	 * 
	 * @param centerLat
	 *            : 中心坐标的纬度
	 * @param centerLong
	 *            : 中心坐标的经度
	 * @param targetLat
	 *            ： 目标坐标的纬度
	 * @param targetLong
	 *            ：目标坐标的经度
	 * @return
	 */
	public static double getDistence(double centerLat, double centerLong,
			double targetLat, double targetLong) {
		double b = Math.abs((centerLat - targetLat) * latUnit);
		double a = Math.abs((centerLong - targetLong) * lonUnit);
		return Math.sqrt((a * a + b * b));
	}

	public static void main(String[] args) {
		System.out.println(getDistence(40.069957, 116.424617, 40.070189,
				116.425062));

		System.out.println(getDistence(40.069957, 116.424617,
				40.06906184656084, 116.424617));

		getMinMaxLatLon(40.069957, 116.424617, 100);

		double[] arrs = getAround(40.069957, 116.424617, 100);

		for (double arr : arrs) {
			System.out.print(arr + " ");
		}
	}
}

package coffee.util.lang;

public class RoleUtils {
	public static void main(String[] args) {
	
		System.out.println(1 << 3);
//		10110
//		0|2|4|0|16
		System.out.println(22 & 1<<3);
		
		System.out.println(hasPermission(10 , 3));
	}
	
	/**
	 * 是否具有权限
	 * @param roleValue : 用户权限值
	 * @param moduleId : 模块ID
	 */
	public static boolean hasPermission(int roleValue, int moduleId)
	{
		boolean result = false;
		/**
		 * 1011 -- 8021
		 * 如果用户的权限值为8+0+2+1 (十进制值-11) 判断其是否具有模块ID为3的权限
		 * 则  (11 & 1 << 3) --> 1011 & 0100 -> 0000
		 * 所有第三位为0，则哦按段11的权限值不具有模块3的权限
		 * 
		 */
		result = (roleValue & 1 << moduleId) != 0;
		return result;
	}
	
}

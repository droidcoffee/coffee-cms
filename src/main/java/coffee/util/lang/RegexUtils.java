package coffee.util.lang;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

	/**
	 * 获取匹配的字符串 默认匹配整个group
	 */
	public static String match(String content, String regex) {
		String str = "";// content.replaceAll("[^"+regex+"]+", "");
		Matcher matcher = Pattern.compile(regex).matcher(content);
		while (matcher.find()) {
			str = matcher.group();
		}
		return str;
	}

	/**
	 * 获取匹配的字符串 匹配指定的group
	 */
	public static String match(String content, String regex, int group) {
		String str = "";
		Matcher matcher = Pattern.compile(regex).matcher(content);
		if (matcher.find()) {
			str = matcher.group(group);
		}
		return str;
	}

	/**
	 * 匹配所有的结果
	 */
	public static String[] matchAll(String content, String regex, int group) {
		Matcher matcher = Pattern.compile(regex).matcher(content);
		List<String> items = new ArrayList<String>();
		String item;
		while (matcher.find()) {
			item = matcher.group(group);
			items.add(item);
		}
		return items.toArray(new String[0]);
	}

	/**
	 * 获取匹配到的所有的group
	 * 
	 * @param content
	 * @param regex
	 * @return
	 */
	public static List<String[]> matchAll(String content, String regex) {
		Matcher matcher = Pattern.compile(regex).matcher(content);
		List<String[]> items = new ArrayList<String[]>();
		while (matcher.find()) {
			String[] arr = new String[matcher.groupCount()];
			for (int i = 1; i < arr.length + 1; i++) {
				arr[i - 1] = matcher.group(i).trim();
			}
			items.add(arr);
		}
		return items;
	}

	public static void main(String[] args) {
		// String[] str = "三星SGH_i728".split("[^(\\w|\\s)]+");
		String str = RegexUtils.match("货号]XM216620", "货号[\\]:]?([\\w\\d]+)", 1);
		System.out.println(str);
	}

}

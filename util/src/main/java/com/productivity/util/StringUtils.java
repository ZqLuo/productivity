package com.productivity.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	/**
	 * 字符串集合用特定字符连接
	 * @param resource
	 * @param separator
	 * @return
	 */
	public static String join(List<String> resource, String separator) {
		StringBuffer result = new StringBuffer();
		if(resource != null && resource.size() > 0) {
			for(String temp : resource) {
				result.append(separator);
				result.append(temp);
			}
			return result.substring(result.indexOf(separator)+separator.length(), result.length());
		}
		return null;
	}
	
	/**
	 * 返回保留小数点位数后的科学计数法字符
	 * @param str 传入str
	 * @param num 小数点后保留位数
	 * @return
	 */
	public static String getScientificNotation(Double str, int num){
		String res = str.toString();
		String[] es = res.split("E");
		if(es!=null && es.length>0){
			String[] points = es[0].split("\\.");
			if(points!=null && points.length>0){
				String point = points[1];
				if(point.length()>num){
					point = point.substring(0,num);
					res = points[0]+"."+point;
					if(es.length>1){
						res = res+"E"+es[1];
					}
				}
			}
		}
		return res;
	}
	
	public static String convertBigDecimalToPerfect(BigDecimal bd) {
		if (bd == null)
			return "";
		if (bd.compareTo(new BigDecimal("0")) == 0) {
			return "0";
		}
		String s = bd.toString();
		s = s.substring(0, 10);
		bd = new BigDecimal(s);
		DecimalFormat df = new DecimalFormat("##.######%");
		return df.format(bd);
	}
	public static Boolean getEmptyValue(String value){
		if(null!=value&&!"".equals(value)&&value.length()>0){
			return true;
		}else{
		return false;
		}
	}
	
	/**
	 * 对象转整数，对象为空返回0
	 * @param o
	 * @return
	 */
	public static int nvlInt(Object o){
		if(o != null && !o.equals("null") && !"".equals(o)){
			return Integer.parseInt(o+"");
		}
		return 0;
	}
	
	/**
	 * 判断对象是否为空
	 * @author luozeqiang
	 * @date 2017年2月23日 上午9:42:13
	 */
	public static boolean isEmpty(Object obj){
		if(obj == null){
			return true;
		}
		if("".equals(obj)){
			return true;
		}
		return false;
	}
	/**
	 * 判断对象是否非空
	 * @author luozeqiang
	 * @date 2017年2月23日 上午9:42:30
	 */
	public static boolean isNotEmpty(Object obj){
		return isEmpty(obj) ? false : true;
	}
	
	/**
	 * 正在表达式验证字符串，若为空则默认通过验证
	 * @param regEx
	 * @param str
	 * @return
	 */
	public static boolean mattch(String regEx, String str){
		if(isEmpty(str)){
			return true;
		}
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	public static String nvlString(Object object){
		if(object == null){
			return "";
		}
		return object + "";
	}

	/**
	 * 字符串替换
	 * @param index 需要替换的下标
	 * @param source 需要被替换的字符串
	 * @param replaceStr 替换的字符
	 * @return
	 */
	public static String repalceStr(int index, String source, String replaceStr){
		return source.substring(0, index) + replaceStr + source.subSequence(index+1, source.length());
	}

	/**
	 * 生成模糊匹配sql
	 * @param value
	 * @return
	 */
	public static String createFuzzyMatchingSql(String value){
		//中文逗号转英文逗号
		return value.trim().replaceAll("\\*", "%").replaceAll("\\?", "_").replaceAll("，",",");
	}

	/**
	 * 把一个字符串的第一个字母大写、效率是最高的
	 * @param fildeName
	 * @return
	 * @throws Exception
	 */
	public static String getMethodName(String fildeName) throws Exception {
		byte[] items = fildeName.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return "get" + new String(items);
	}

	/**
	 * 把一个字符串的第一个字母大写、效率是最高的
	 * @param fildeName
	 * @return
	 * @throws Exception
	 */
	public static String setMethodName(String fildeName) throws Exception {
		byte[] items = fildeName.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return "set" + new String(items);
	}

	/**
	 * 获取详细的异常链信息--精准定位异常位置
	 *
	 * @param ex
	 * @return
	 */
	public static String getStackTrace(Exception ex) {
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw, true);
			ex.printStackTrace(pw);
			pw.flush();
			sw.flush();
			pw.close();
			sw.close();
			return sw.toString();
		} catch (Exception e){
			return ex.getMessage();
		}
	}

	/**
	 * 下划线转驼峰法(默认小驼峰)
	 *
	 * @param line
	 *            源字符串
	 * @param smallCamel
	 *            大小驼峰,是否为小驼峰(驼峰，第一个字符是大写还是小写)
	 * @return 转换后的字符串
	 */
	public static String underline2Camel(String line, boolean ... smallCamel) {
		if (line == null || "".equals(line)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		Pattern pattern = Pattern.compile("([A-Za-z\\d]+)(_)?");
		Matcher matcher = pattern.matcher(line);
		//匹配正则表达式
		while (matcher.find()) {
			String word = matcher.group();
			//当是true 或则是空的情况
			if((smallCamel.length ==0 || smallCamel[0] ) && matcher.start()==0){
				sb.append(Character.toLowerCase(word.charAt(0)));
			}else{
				sb.append(Character.toUpperCase(word.charAt(0)));
			}

			int index = word.lastIndexOf('_');
			if (index > 0) {
				sb.append(word.substring(1, index).toLowerCase());
			} else {
				sb.append(word.substring(1).toLowerCase());
			}
		}
		return sb.toString();
	}

	/**
	 * 驼峰法转下划线
	 *
	 * @param line
	 *            源字符串
	 * @return 转换后的字符串
	 */
	public static String camel2Underline(String line) {
		if (line == null || "".equals(line)) {
			return "";
		}
		line = String.valueOf(line.charAt(0)).toUpperCase()
				.concat(line.substring(1));
		StringBuffer sb = new StringBuffer();
		Pattern pattern = Pattern.compile("[A-Z]([a-z\\d]+)?");
		Matcher matcher = pattern.matcher(line);
		while (matcher.find()) {
			String word = matcher.group();
			sb.append(word.toUpperCase());
			sb.append(matcher.end() == line.length() ? "" : "_");
		}
		return sb.toString();
	}

}

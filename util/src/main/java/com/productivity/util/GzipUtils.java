package com.productivity.util;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * 解压缩工具类
 */
public class GzipUtils {

    public static byte[] gzip(byte[] data) throws Exception{
    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
    	GZIPOutputStream gzip = new GZIPOutputStream(bos);
    	gzip.write(data);
    	gzip.finish();
    	gzip.close();
    	byte[] ret = bos.toByteArray();
    	bos.close();
    	return ret;
    }
    
    public static byte[] ungzip(byte[] data) throws Exception{
    	ByteArrayInputStream bis = new ByteArrayInputStream(data);
    	GZIPInputStream gzip = new GZIPInputStream(bis);
    	byte[] buf = new byte[1024];
    	int num = -1;
    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
    	while((num = gzip.read(buf, 0 , buf.length)) != -1 ){
    		bos.write(buf, 0, num);
    	}
    	gzip.close();
    	bis.close();
    	byte[] ret = bos.toByteArray();
    	bos.flush();
    	bos.close();
    	return ret;
    }
    
    public static void main(String[] args) throws Exception{

    	//读取文件
    	String readPath = System.getProperty("user.dir") + File.separatorChar + "sources" +  File.separatorChar + "006.jpg";
        File file = new File(readPath);
        FileInputStream in = new FileInputStream(file);
        byte[] data = new byte[in.available()];
        in.read(data);
        in.close();

        System.out.println("文件原始大小:" + data.length);
        //测试压缩

        byte[] ret1 = GzipUtils.gzip(data);
        System.out.println("压缩之后大小:" + ret1.length);

        byte[] ret2 = GzipUtils.ungzip(ret1);
        System.out.println("还原之后大小:" + ret2.length);

        //写出文件
        String writePath = System.getProperty("user.dir") + File.separatorChar + "receive" +  File.separatorChar + "006.jpg";
        FileOutputStream fos = new FileOutputStream(writePath);
        fos.write(ret2);
        fos.close();
    	
    	
	}


	private static final String CHARSET = "ISO-8859-1";

	/**
	 * 字符串的压缩
	 *
	 * @param str
	 *            待压缩的字符串
	 * @return    返回压缩后的字符串
	 * @throws IOException
	 */
	public static String gzipString(String str) throws IOException {
		if (null == str || str.length() <= 0) {
			return str;
		}
		// 创建一个新的 byte 数组输出流
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		// 使用默认缓冲区大小创建新的输出流
		GZIPOutputStream gzip = new GZIPOutputStream(out);
		// 将 b.length 个字节写入此输出流
		gzip.write(str.getBytes());
		gzip.close();
		// 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
		return out.toString("ISO-8859-1");
	}

	/**
	 * 字符串的解压
	 *
	 * @param str
	 *            对字符串解压
	 * @return    返回解压缩后的字符串
	 * @throws IOException
	 */
	public static String ungzipString(String str) throws IOException {
		if (null == str || str.length() <= 0) {
			return str;
		}
		// 创建一个新的 byte 数组输出流
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		// 创建一个 ByteArrayInputStream，使用 buf 作为其缓冲区数组
		ByteArrayInputStream in = new ByteArrayInputStream(str
				.getBytes(CHARSET));
		// 使用默认缓冲区大小创建新的输入流
		GZIPInputStream gzip = new GZIPInputStream(in);
		byte[] buffer = new byte[256];
		int n = 0;
		while ((n = gzip.read(buffer)) >= 0) {// 将未压缩数据读入字节数组
			// 将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此 byte数组输出流
			out.write(buffer, 0, n);
		}
		// 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
		return out.toString();
	}
    
}

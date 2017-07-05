package com.parser.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.parser.model.PropertyInfo;

/**
 * 配置文件工具类
 * 
 * @author Administrator
 *
 */
public class PropertiesUtil {

	// 配置文件路径
	private final static String PROPERTY_URL = "resources/common.properties";

	public static PropertyInfo info; // 配置文件信息

	static {
		try {
			info = loadPropertyInfo(); // 加载配置文件信息
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取配置文件信息，转为Model
	 * 
	 * @return
	 * @throws Exception
	 */
	public static PropertyInfo loadPropertyInfo() throws Exception {
		PropertyInfo info = new PropertyInfo();
		Properties properites = PropertiesUtil.getProperites(PROPERTY_URL);
		info.setAuthor("" + properites.get("author"));
		info.setOutPath("" + properites.get("outPath"));
		info.setPdmPath("" + properites.get("pdmPath"));
		info.setPackagePath("" + properites.get("packagePath"));
		info.setPointTable("" + properites.get("pointTable"));
		return info;
	}

	/**
	 * 读取配置文件
	 * 
	 * @param url
	 *            如：resources/common.properties
	 * @return
	 */
	public static Properties getProperites(String url) {
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(url));
			Properties p = new Properties();
			p.load(in);
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

package com.parser.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerUtil {
	public final static String FPL_PATH = "resources/fpl";

	/**
	 * 文件渲染
	 * 
	 * @param root
	 * @param inFile
	 * @param outFile
	 */
	public static void processToFile(Object root, File inFile, File outFile) {
		FileWriter out = null;
		try {
			// 通过一个文件输出流，就可以写到相应的文件中，此处用的是绝对路径
			out = new FileWriter(outFile.getAbsolutePath());
			// 通过Freemaker的Configuration读取相应的ftl
			Configuration cfg = new Configuration();
			// 设置模板文件目录
			cfg.setDirectoryForTemplateLoading(inFile.getParentFile());
			// 在模板文件目录中找到名称为name的文件
			Template temp = cfg.getTemplate(inFile.getName());
			temp.process(root, out);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 字符串渲染
	 * 
	 * @param root
	 * @param str
	 * @return
	 */
	public static String processToString(Object root, String str) {
		Configuration cfg = new Configuration();
		StringTemplateLoader stringLoader = new StringTemplateLoader();
		stringLoader.putTemplate("myTemplate", str);
		cfg.setTemplateLoader(stringLoader);
		try {
			Template template = cfg.getTemplate("myTemplate", "utf-8");
			StringWriter writer = new StringWriter();
			try {
				template.process(root, writer);
				return writer.toString();
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}

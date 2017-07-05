package com.parser.utils;

import java.io.File;
import java.util.List;

import com.resgain.sparrow.pdm.PDMParser;
import com.resgain.sparrow.pdm.bean.Column;
import com.resgain.sparrow.pdm.bean.Project;
import com.resgain.sparrow.pdm.bean.Table;

public class CodeUtil {


	public static void makeCode() throws Exception {
		// 解析pdm成table集合
		Project proj = PDMParser.parse(new File(PropertiesUtil.info.getPdmPath()));
		List<Table> tables = proj.getTableList();;
		for (Table table : tables) {
			if(!isPointTable(table)) continue;
			fillExtendField(table); // 一些初始化工作，填充table和column的扩展字段
			System.out.println(table);
			scanFilesAndProcess(new File(FreemarkerUtil.FPL_PATH), table);
			// 生成excel模板
			ExcelUtil.makeExcel(new File(FreemarkerUtil.FPL_PATH + "/z_xls/" + table.getComment() +".xls"), table);
		}
		System.out.println("=================================================");
		System.out.println("生成成功！");
		System.out.println(PropertiesUtil.info.getOutPath());

	}

	/**
	 * 返回是否生成该表
	 * 如果配置文件的pointTable不填，则全部生成
	 * 如果填写，则提取逗号分隔的表名，匹配的表则返回true
	 * @param table
	 * @return
	 */
	private static boolean isPointTable(Table table) {
		String pointTable = PropertiesUtil.info.getPointTable();
		if(pointTable.length() == 0) // 如果不填，则默认全部表都生成
			return true;
		String[] arr = pointTable.split(",");
		for(String t : arr) {
			if(table.getCode().equals(t))
				return true;
		}
		return false;
	}

	/**
	 * 一些初始化工作，填充table和column的扩展字段
	 * @param table
	 */
	private static void fillExtendField(Table table) {
		table.setPackagePath(PropertiesUtil.info.getPackagePath());
		table.setAuthor(PropertiesUtil.info.getAuthor());
		List<Column> columns = table.getColumns();
		for(Column c : columns) {
			String dbFieldType = c.getType();
			c.setModelFieldType(FieldTypeUtil.getModelFieldType(dbFieldType));
			c.setMybatisFieldType(FieldTypeUtil.getMybatisFieldType(dbFieldType));
		}
	}

	/**
	 * 扫描该目录的所有文件和文件夹里的文件，把模板文件都提取出来生成代码
	 * 
	 * @param file
	 * @param table
	 */
	private static void scanFilesAndProcess(File fplFile, Table table) {
		if (fplFile == null)
			return;
		if (fplFile.isFile()) { // 如果是个文件
			process(fplFile, table);
		} else { // 如果是个目录
			if(".svn".equals(fplFile.getName())) return; // svn的目录跳过
			File[] files = fplFile.listFiles();
			for (File f : files) {
				if (f.isFile()) { // 如果是个目录
					process(f, table);
				} else {
					scanFilesAndProcess(f, table); // 递归
				}
			}
		}

	}

	/**
	 * 生成代码
	 * 
	 * @param fplFile
	 * @param table
	 */
	private static void process(File fplFile, Table table) {
		if (fplFile == null || fplFile.isDirectory())
			return;
		String filename = FreemarkerUtil.processToString(table, fplFile.getName());
		File outFile = new File(getOutPath(fplFile), filename);
		if (!outFile.getParentFile().exists()) {
			outFile.getParentFile().mkdirs();
		}
		FreemarkerUtil.processToFile(table, fplFile, outFile);

	}

	/**
	 * 输出的路径 + 包名 + 模板文件名 ，拼接成渲染后的文件绝对路径
	 * @param fplFile
	 * @return
	 */
	public static String getOutPath(File fplFile) {
		String fplBaseAbsolutePath = new File(FreemarkerUtil.FPL_PATH).getAbsolutePath();
		String fplAbsolutePath = fplFile.getParentFile().getAbsolutePath();
		String fplRelativePath = fplAbsolutePath.replace(fplBaseAbsolutePath, "");
		String packagePath = PropertiesUtil.info.getPackagePath();
		packagePath = packagePath.replace(".", "/");
		String outPath = PropertiesUtil.info.getOutPath() + "/" + packagePath + fplRelativePath;
		return outPath;
	}
}

package com.parser.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.resgain.sparrow.pdm.bean.Column;
import com.resgain.sparrow.pdm.bean.Table;

public class ExcelUtil {

	public static void makeExcel(File fplFile, Table table) {
		List<Column> columns = table.getColumns();
		// 生成Workbook
		HSSFWorkbook wb = new HSSFWorkbook();
		// 添加Worksheet（不添加sheet时生成的xls文件打开时会报错）
		Sheet sheet1 = wb.createSheet();

		Row row1 = sheet1.createRow(0);

		int counter = 0;
		for(Column column : columns) {
			String comment = column.getComment();
			String modelFieldType = column.getModelFieldType();
			String name = column.getName();
			if("id".equals(name) || "createTime".equals(name) || "updateTime".equals(name) || "isValid".equals(name))
				continue;
			Cell cell1_1 = row1.createCell(counter++);
			if("Timestamp".equals(modelFieldType)) {
				cell1_1.setCellValue(comment + "（格式：yyyy-MM-dd 或 yyyy-MM-dd HH:mm:ss）");
			} else {
				cell1_1.setCellValue(comment);
			}
		}
		// 保存为Excel文件
		FileOutputStream out = null;
		try {
			String outPath = CodeUtil.getOutPath(fplFile);
			File f = new File(outPath);
			if(!f.exists()) 
				f.mkdirs();
			out = new FileOutputStream(outPath + "/" + fplFile.getName());
			wb.write(out);
		} catch (IOException e) {
			System.out.println(e.toString());
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}
	}

}

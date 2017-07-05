package ${packagePath}.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minstone.common.model.MyBatisQuery;
import com.minstone.common.utils.ExcelUtil;
import com.minstone.common.utils.ObjectUtil;
import com.minstone.common.utils.TimestampUtil;
import com.minstone.common.utils.UUIDUtil;
import com.minstone.enet.citizen.services.farmer.model.Farmer;

import ${packagePath}.model.${name};
import ${packagePath}.dao.${name}Dao;
import ${packagePath}.service.I${name}Service;
/**
 * ${comment}持久层
 * 
 * @copyright 广州明动软件有限公司 Copyright (c) ${.now?string("yyyy")}  
 * @since ${.now?string("yyyy-MM-dd")} 
 * @author ${author}
 * @version 1.0
 * 
 */
@Service("${name?uncap_first}Service")
public class ${name}ServiceImpl implements I${name}Service <#noparse>{</#noparse>
	@Autowired
	private ${name}Dao ${name?uncap_first}Dao;
	
	
	@Override
	public int add(${name} param) {
		if(param == null) return 0;
		param.setId(UUIDUtil.getUUID());
		param.setCreateTime(TimestampUtil.getCurrentTimestampWithFormat());
		param.setUpdateTime(TimestampUtil.getCurrentTimestampWithFormat());
		param.setIsValid(1);
		return ${name?uncap_first}Dao.add(param);
	}

	@Override
	public int update(${name} param) {
		if(param == null) return 0;
		param.setUpdateTime(TimestampUtil.getCurrentTimestampWithFormat());
		return ${name?uncap_first}Dao.update(param);
	}

	@Override
	public ${name} get(${name} param) {
		if(param == null) return null;
		return ${name?uncap_first}Dao.get(param.getId());
	}

	@Override
	public List<${name}> getList(MyBatisQuery<${name}> query) {
		return ${name?uncap_first}Dao.getList(query);
	}
	
	@Override
	public int getTotalCount(MyBatisQuery<${name}> query) {
		return ${name?uncap_first}Dao.getTotalCount(query);
	}
	
	/**
	 * excel批量导入功能
	 * @param excel
	 */
	@Override
	public void importExcel(File excel) {
		try {
			// 读取excel文件分析Excel文件中的数据
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(excel));
			// 读取第一页的内容
			HSSFSheet sheet = wb.getSheetAt(0);
			${name} vo = new ${name}();
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				HSSFRow row = sheet.getRow(i);
				int counter = 0;
				<#list columns as column>
					<#if column.name != 'id' && column.name != 'createTime' && column.name != 'updateTime' && column.name != 'isValid' >
						<#if column.modelFieldType == 'Integer'>
				vo.set${column.name?cap_first}(IntegerUtil.parseInt(ExcelUtil.getStringCellValue(row, counter++)));
						<#elseif column.modelFieldType == 'Timestamp'>
				vo.set${column.name?cap_first}(ExcelUtil.getDateCellValue(row, counter++));
						<#else>
				vo.set${column.name?cap_first}(ExcelUtil.getStringCellValue(row, counter++));
						</#if>
					</#if>
				</#list>
				add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* env适用的excel录入 */
	
	/**
	 * excel批量导入功能
	 * @param excel
	 
	@Override
	public void importExcel(InputStream excel) {
		List list = new ArrayList();
		try {
			// 读取excel文件分析Excel文件中的数据
			HSSFWorkbook wb = new HSSFWorkbook(excel);
			// 读取第一页的内容
			HSSFSheet sheet = wb.getSheetAt(0);
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				${name} vo = new ${name}();
				HSSFRow row = sheet.getRow(i);
				int counter = 0;
				<#list columns as column>
					<#if column.name != 'id' && column.name != 'createTime' && column.name != 'updateTime' && column.name != 'isValid' >
						<#if column.modelFieldType == 'Integer'>
				vo.set${column.name?cap_first}(IntegerUtil.parseInt(ExcelUtil.getStringCellValue(row, counter++)));
						<#elseif column.modelFieldType == 'Timestamp'>
				vo.set${column.name?cap_first}(ExcelUtil.getDateCellValue(row, counter++));
						<#else>
				vo.set${column.name?cap_first}(ExcelUtil.getStringCellValue(row, counter++));
						</#if>
					</#if>
				</#list>
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		syncDataBatch(list);
	}
	*/
	
<#noparse>}</#noparse>
package ${packagePath}.action.data;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.minstone.common.cons.PageResponseCodeCons;
import com.minstone.common.enums.ResponseCode;
import com.minstone.common.model.MyBatisQuery;
import com.minstone.enetms.common.action.EnetMSBaseAction;
import com.minstone.common.model.MyBatisQuery;
import ${packagePath}.model.${name};
import ${packagePath}.dao.${name}Dao;
import ${packagePath}.service.I${name}Service;

/**
 * ${comment}控制层
 * 
 * @copyright 广州明动软件有限公司 Copyright (c) ${.now?string("yyyy")}  
 * @since ${.now?string("yyyy-MM-dd")} 
 * @author ${author}
 * @version 1.0
 * 
 */
@Controller
@Scope("prototype")
public class ${name}DataAction extends EnetMSBaseAction <#noparse>{</#noparse>
	
	private ${name} param;
	
	@Autowired
	private I${name}Service ${name?uncap_first}Service;
	
	// 导入Excel文件
	private File excel;
	
	
	public void add() throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		ResponseCode resp = ResponseCode.SUCCESS;
		int result = ${name?uncap_first}Service.add(param);
		if(result == 0)
			resp = ResponseCode.ERROR_PARAM_FMT;
		ret.put("respCode", resp.getCode()); // 响应码
		ret.put("respMsg", resp.getMsg()); // 响应消息
		writeJson(ret);
	}
	
	public void update() throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		ResponseCode resp = ResponseCode.SUCCESS;
		int result = ${name?uncap_first}Service.update(param);
		if(result == 0)
			resp = ResponseCode.NOT_EXIST_DATA;
		ret.put("respCode", resp.getCode()); // 响应码
		ret.put("respMsg", resp.getMsg()); // 响应消息
		writeJson(ret);
	}
	
	public void get() throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		ResponseCode resp = ResponseCode.SUCCESS;
		${name} result = ${name?uncap_first}Service.get(param);
		if(result == null) 
			resp = ResponseCode.NOT_EXIST_DATA;
		ret.put("respCode", resp.getCode()); // 响应码
		ret.put("respMsg", resp.getMsg()); // 响应消息
		ret.put("respData", result); // 响应数据
		writeJson(ret);
	}
	
	public void getList() throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		ResponseCode resp = ResponseCode.SUCCESS;
		MyBatisQuery<${name}> query = new MyBatisQuery<${name}>(param, page, rows, sort, order);
		param.setIsValid(1); // 获取没有被逻辑删除的数据
		List<${name}> list = ${name?uncap_first}Service.getList(query);
		int total = ${name?uncap_first}Service.getTotalCount(query);
		ret.put("rows", list);
		ret.put("total", total);
		ret.put("respCode", resp.getCode()); // 响应码
		ret.put("respMsg", resp.getMsg()); // 响应消息
		writeJson(ret);
	}
	
	/**
	 * excel批量录入
	 * @throws Exception
	 */
	public void importExcel() throws Exception {
		Map<String, Object> ret = new HashMap<String, Object>();
		ResponseCode resp = ResponseCode.SUCCESS;
		if (excel != null) {
			${name?uncap_first}Service.importExcel(excel);
		} else {
			resp = ResponseCode.ERROR_PARAM_FMT;
		}
		ret.put("respCode", resp.getCode()); // 响应码
		ret.put("respMsg", resp.getMsg()); // 响应消息
		writeJson(ret);
	}
	
	public ${name} getParam() {
		return param;
	}

	public void setParam(${name} param) {
		this.param = param;
	}
	
	public File getExcel() {
		return excel;
	}

	public void setExcel(File excel) {
		this.excel = excel;
	}

<#noparse>}</#noparse>
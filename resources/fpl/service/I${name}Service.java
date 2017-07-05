package ${packagePath}.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;

import com.minstone.common.model.MyBatisQuery;
import ${packagePath}.model.${name};
/**
 * ${comment}持久层
 * 
 * @copyright 广州明动软件有限公司 Copyright (c) ${.now?string("yyyy")}  
 * @since ${.now?string("yyyy-MM-dd")} 
 * @author ${author}
 * @version 1.0
 * 
 */

public interface I${name}Service <#noparse>{</#noparse>
	int add(${name} param);
	
	int update(${name} param);
	
	${name} get(${name} param);
	
	List<${name}> getList(MyBatisQuery<${name}> query);
	
	int getTotalCount(MyBatisQuery<${name}> query);
	
	void importExcel(File excel);

<#noparse>}</#noparse>
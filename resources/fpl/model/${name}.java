package ${packagePath}.model;

import java.sql.Timestamp;

/**
 * ${comment}Model类
 * 
 * @copyright 广州明动软件有限公司 Copyright (c) ${.now?string("yyyy")}  
 * @since ${.now?string("yyyy-MM-dd")} 
 * @author ${author}
 * @version 1.0
 * 
 */
public class ${name} <#noparse>{</#noparse>
	<#list columns as column>
	/**
	 * ${column.comment}
	 */
	private ${column.modelFieldType} ${column.name};
	</#list>
	
	<#list columns as column>
	/**
	 * 获取${column.comment}
	 */
	public ${column.modelFieldType} get${column.name?cap_first}() <#noparse>{</#noparse>
		return ${column.name};
	<#noparse>}</#noparse>
	/**
	 * 设置${column.comment}
	 */
	public void set${column.name?cap_first}(${column.modelFieldType} ${column.name}) <#noparse>{</#noparse>
		this.${column.name} = ${column.name};
	<#noparse>}</#noparse>
	</#list>
<#noparse>}</#noparse>
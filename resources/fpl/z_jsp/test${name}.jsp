<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String basePath = request.getContextPath()+"/";
%>

<!DOCTYPE HTML>
<!--[if IE 8]><html id="ie8" lang="zh-cmn-Hans" ><![endif]-->
<!--[if !(IE 6) | !(IE 7) | !(IE 8)  ]><!-->
<html lang="zh-cmn-Hans">
<!--<![endif]-->  
<head>
    <base href="<%=basePath%>">
    
    <title>${comment}-接口测试页</title>
    
      <meta charset="UTF-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" /> <!-- 优先使用 IE 最新版本和 Chrome -->

      <meta name="renderer" content="webkit" />
	<%@ include file="/common/jsp/easyui_include.jsp"%>
	<script>
	function testRequest(data, url) { // 通过json请求参数
		$.ajax({
			data: JSON.stringify(data),
			type: 'post',
			dataType: 'json',
			contentType: 'application/json',
			url: '<%=basePath%>' + url,
			success:function(data){
				console.log(data);
				alert(JSON.stringify(data));
			},
			error: function() {  
	             alert('接口失败');
	       }
		});
	}
	
	</script>


  </head>
  
  <body>
  <input name="add" type="button" value="添加" onclick="add();" >
  <input name="update" type="button" value="更新" onclick="update();" >
  <input name="get" type="button" value="获取详情" onclick="get();" >
  <input name="getList" type="button" value="获取列表" onclick="getList();" >
	 

<script type="text/javascript">
 
 	// 添加
	function add(){
		var data = {
				'param' : {
<#list columns as column>
					<#if column.modelFieldType != 'Timestamp' && column.name != 'id' && column.name != 'createTime' && column.name != 'updateTime' && column.name != 'isValid' >
					'${column.name}' : '${column.name}1', //  ${column.comment}
					</#if>
					</#list>
				}
		}
		testRequest(data, '${name?uncap_first}Data/add');
	}
    
 	// 更新
	function update(){
		var data = {
				'param' : {
					<#list columns as column>
					<#if column.modelFieldType != 'Timestamp' && column.name != 'createTime' && column.name != 'updateTime' && column.name != 'isValid' >
					'${column.name}' : '${column.name}2', //  ${column.comment}
					</#if>
					</#list>
					'isValid' : '1' // 是否有效
				}
		}
		testRequest(data, '${name?uncap_first}Data/update');
	}
    
 	// 获取详情
	function get(){
		var data = {
				'param' : {
					'id' : '' 
				}
		}
		testRequest(data, '${name?uncap_first}Data/get');
	}
    
 	// 获取列表
	function getList(){
		var data = {
				'page' : '1', // 第1页
				'rows' : '10', // 每页10行
				'param' : {
					<#list columns as column>
					<#if column.modelFieldType != 'Timestamp' && column.name != 'id' && column.name != 'createTime' && column.name != 'updateTime' && column.name != 'isValid' >
					'${column.name}' : '${column.name}1', //  ${column.comment}
					</#if>
					</#list>
				}
		}
		testRequest(data, '${name?uncap_first}Data/getList');
	}
    
</script> 
</body>
</html>

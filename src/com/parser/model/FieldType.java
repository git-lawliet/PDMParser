package com.parser.model;

public class FieldType {
	private String dbType; // 数据库类型 ，如： varchar2(256)
	private String modelType; // java类型，如： String
	private String mybatisType; // mybatis类型，如： varchar
	
	public FieldType(String dbType, String modelType, String mybatisType) {
		super();
		this.dbType = dbType;
		this.modelType = modelType;
		this.mybatisType = mybatisType;
	}
	public String getDbType() {
		return dbType;
	}
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	public String getModelType() {
		return modelType;
	}
	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
	public String getMybatisType() {
		return mybatisType;
	}
	public void setMybatisType(String mybatisType) {
		this.mybatisType = mybatisType;
	}
	
	
}

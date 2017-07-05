package com.parser.utils;

import java.util.ArrayList;
import java.util.List;

import com.parser.model.FieldType;

public class FieldTypeUtil {
	public static List<FieldType> fieldTypeList = null;
	  
	static {
		fieldTypeList = initFieldTypeList();
	}
	
	public static List<FieldType> initFieldTypeList() {
		List<FieldType> list = new ArrayList<FieldType>();
		list.add(new FieldType("VARCHAR2", "String", "VARCHAR"));
		list.add(new FieldType("VARCHAR", "String", "VARCHAR"));
		list.add(new FieldType("BLOB", "String", "VARCHAR"));
		list.add(new FieldType("CLOB", "String", "VARCHAR"));
		list.add(new FieldType("NUMBER", "Integer", "NUMERIC"));
		list.add(new FieldType("INT", "Integer", "NUMERIC"));
		list.add(new FieldType("TIMESTAMP", "Timestamp", "TIMESTAMP"));
		list.add(new FieldType("DATETIME", "Timestamp", "TIMESTAMP"));
		list.add(new FieldType("DOUBLE", "Double", "NUMERIC"));
		return list;
	}

	public static String getMybatisFieldType(String dbFieldType) {
		if(dbFieldType == null || dbFieldType.length() == 0) return null;
		for(FieldType f : fieldTypeList) {
			if(dbFieldType.toUpperCase().contains(f.getDbType())) {
				return f.getMybatisType();
			}
		}
		return null;
	}
	
	public static String getModelFieldType(String dbFieldType) {
		if(dbFieldType == null || dbFieldType.length() == 0) return null;
		for(FieldType f : fieldTypeList) {
			if(dbFieldType.toUpperCase().contains(f.getDbType())) {
				return f.getModelType();
			}
		}
		return null;
	}
}

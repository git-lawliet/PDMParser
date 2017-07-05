package com.parser.model;

public class PropertyInfo {
	private String author; // 作者
	private String pdmPath; // pdm路径
	private String outPath; // 代码生成路径
	private String packagePath; // 代码包名
	private String pointTable; // 指定生成的表，如果不指定则全部生成
	

	public String getPackagePath() {
		return packagePath;
	}

	public void setPackagePath(String packagePath) {
		this.packagePath = packagePath;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPdmPath() {
		return pdmPath;
	}

	public void setPdmPath(String pdmPath) {
		this.pdmPath = pdmPath;
	}

	public String getOutPath() {
		return outPath;
	}

	public void setOutPath(String outPath) {
		this.outPath = outPath;
	}

	public String getPointTable() {
		return pointTable;
	}

	public void setPointTable(String pointTable) {
		this.pointTable = pointTable;
	}

	
}

package com.ucap.cloud_web.constant;


public enum HealthyType {

	MONITORING_DATABASEINFO(1,"健康指数","healthindex"),
	MONITORING_DATABASETREEINFO(2,"不连通率","linkerrsiteprop"),
	MONITORING_CONNECTIONALL(3,"更新量","updatenum");
	
	private Integer code;
	private String name;
	private String sqlname;



	private HealthyType(Integer code, String name,String sqlname ) {
		this.code = code;
		this.name = name;
		this.sqlname=sqlname;
	}

	public static HealthyType getNameByCode(int focusType) {
		for (HealthyType c : HealthyType.values()) {
			if (c.getCode()==focusType) {
				return c;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public Integer getCode() {
		return code;
	}
	
	public String getSqlname() {
		return sqlname;
	}

}
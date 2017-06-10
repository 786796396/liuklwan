package com.ucap.cloud_web.constant;

/**
 * <p>Description: 云分析登录接口 枚举类</p>
 * <p>@Package：com.ucap.cloud_web.constant </p>
 * <p>Title: comboType </p>
 * <p>Company: 开普互联 </p>
 * <p>@luocheng </p>
 * <p>@date：2017-3-6 </p>
 */
public enum CloudAnalyzeType {
	
	YFX_API_URL("YFX_API_URL","http://fx.kaipuyun.cn/web/api/kpy_api.php"),  //登入url
	EXPIRYDATE("EXPIRYDATE","1519660800000"),  
	TOKEN("TOKEN","77F8376614F7E69CE00393EBDE2F7E80"); //Token
	
	private String code;
	private String name;
	
	private CloudAnalyzeType(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

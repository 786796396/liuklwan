package com.ucap.cloud_web.entity;


import java.util.Date;

import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.util.CommonUtils;


/**


	//首页连通性表id










	// 1： 1天1次  96:1天96次   
	private String connectionSumType;
	// 1天1次 和1天96次  分别有多少条
	private Integer sumTypeCount;
	/** set 首页连通性表id */
		this.id=id;
	}

		return id;
	}

		this.siteCode=siteCode;
	}

		return siteCode;
	}

		this.scanDate=scanDate;
	}

		return scanDate;
	}

		this.connectionSum=connectionSum;
	}

		return connectionSum;
	}

		this.successNum=successNum;
	}

		return successNum;
	}

		this.successProportion=successProportion;
	}

		return successProportion;
	}

		this.errorNum=errorNum;
	}

		return errorNum;
	}

		this.errorProportion=errorProportion;
	}

		return errorProportion;
	}

		this.name=name;
	}

		return name;
	}

		this.type=type;
	}

		return type;
	}

		this.url=url;
	}

		return url;
	}

		this.createTime=createTime;
	}

		return createTime;
	}

	@Override
	public String toString() {
		return "ConnectionAll [id=" + id + ", siteCode=" + siteCode
				+ ", scanDate=" + scanDate + ", connectionSum=" + connectionSum
				+ ", successNum=" + successNum + ", successProportion="
				+ successProportion + ", errorNum=" + errorNum
				+ ", errorProportion=" + errorProportion + ", name=" + name
				+ ", type=" + type + ", url=" + url + ", createTime="
				+ createTime + "]";
	}

	public String getConnectionSumType() {
		return connectionSumType;
	}

	public void setConnectionSumType(String connectionSumType) {
		this.connectionSumType = connectionSumType;
	}

	public Integer getSumTypeCount() {
		return sumTypeCount;
	}

	public void setSumTypeCount(Integer sumTypeCount) {
		this.sumTypeCount = sumTypeCount;
	}


	

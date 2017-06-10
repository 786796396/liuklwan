<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	 <title>业务系统连不通</title>
	 <%@ include file="/newWeiXin/jsp/common_new.jsp"%>
	 <link rel="stylesheet" type="text/css" href="<%= path %>/newWeiXin/css/hp-UpdateQuantity.css" />
  </head>
<body>
	<input type="hidden" id="siteCode_id" value="${initMap.siteCode}" />
	<input type="hidden" id="checkType_id" value="${initMap.checkType}" />
	  <div class="wrap">
        <div class="result-set">
            <div class="main-title ">
                <div class="title-block">
                    <div class="seven-words-tit">
                        <img src="<%= path %>/newWeiXin/images/dm--businessSystem.png" />
                        <span>业务系统连不通</span>
                    </div>
                </div>
            </div>
            <div class="result-detail">
	            <p class="clearfix">
	                <span class="fl">扫描时间</span>
	                <span class="fr">${initMap.scanDate}</span>
	            </p>
	            <p class="clearfix">
	                <span class="fl">连不通业务系统数</span>
	                <span class="fr">${initMap.connBusiness}个</span>
	            </p>
	            <p class="clearfix last-item">
	                <span class="fl">问题站点数</span>
	                <span class="fr">${initMap.connBusinessSite}个</span>
	            </p>
       		 </div>
        </div>
        <div class="block-style">
            <div class="details-list-part">
                <div class="details-list-tit">
                    业务系统连不通情况
                </div>
                <div class="details-list-content" id="conn_business_list_id">
					<ul id="conn_business_id">
					
					</ul>
                </div>
            </div>
        </div>
    </div>
	<%@ include file="/newWeiXin/jsp/footer.jsp"%>
	<script language="javascript" type="text/javascript" src="<%= path%>/newWeiXin/js/connBusiness/connBusinessOrg.js"></script>
	<script type="text/javascript" src="<%= path %>/newWeiXin/js/common.js"></script> 
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<title>首页连不通</title>
	 <%@ include file="/newWeiXin/jsp/common_new.jsp"%>
	 <link rel="stylesheet" href="<%= path %>/newWeiXin/css/hp-impassability.css"/>
  </head>
<body >
<div class="wrap">
		<input type="hidden" id="siteCode_id" value="${initMap.siteCode}" />
		<input type="hidden" id="checkType_id" value="${initMap.checkType}" />
        <div class="main-title ">
            <div class="hp-impassab-tit">
                <img src="<%= path %>/newWeiXin/images/hp-impassab.png" />
                <span>首页连不通</span>
            </div>
        </div>
        <div class="tab-box every-line">
            <div class="half-part fl active">
                <p class="num">${initMap.connHome}</p>
                <p>今日连不通</p>
                <i class="triangle_border_up"></i>
            </div>
            <div class="half-part fl">
                <p class="num">${initMap.countNum}</p>
                <p>7日连不通率超过3%</p>
                <i class="triangle_border_up"></i>
            </div>
        </div>
        <div class="tab-content active" id="conn_home_list_org_id">
        	<div id="conn_home_org_id">
        	
        	
        	</div>
        </div>
        <div class="tab-content" id="conn_home_per_list_org_id">
			<div id="conn_home_per_org_id">
        	
        	</div>
        </div>
    </div>
   <%@ include file="/newWeiXin/jsp/footer.jsp"%>
   <script language="javascript" type="text/javascript" src="<%= path %>/newWeiXin/js/connHome/connHomeOrg.js"></script>
   <script src="<%= path %>/newWeiXin/js/common.js"></script>
</body>
</html>

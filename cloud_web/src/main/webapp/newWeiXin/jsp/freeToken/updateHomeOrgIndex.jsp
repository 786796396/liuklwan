<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	 <title>首页更新量</title>
	 <%@ include file="/newWeiXin/jsp/common_new.jsp"%>
	 <link rel="stylesheet" type="text/css" href="<%= path %>/newWeiXin/css/hp-UpdateQuantity.css" />
  </head>
<body>
	<div class="wrap">
			<input type="hidden" id="siteCode_id" value="${initMap.siteCode}">
			<input type="hidden" id="checkType_id" value="${initMap.checkType}">
	        <div class="result-set">
	            <div class="main-title ">
	                <div class="title-block">
	                    <div class="five-words-tit">
	                        <img src="<%= path %>/newWeiXin/images/hp-updateq.png" />
	                        <span>首页更新量</span>
	                    </div>
	                </div>
	            </div>
	            <div class="result-detail">
	            <p class="clearfix">
	                <span class="fl">扫描时间</span>
	                <span class="fr" id="scanDate_id">${initMap.scanDate}</span>
	            </p>
	            <p class="clearfix">
	                <span class="fl">首页更新量</span>
	                <span class="fr">${initMap.updateHome}条</span>
	            </p>
	            <p class="clearfix last-item">
	                <span class="fl">分布站点</span>
	                <span class="fr">${initMap.updateHomeSite}个站点</span>
	            </p>
	        </div>
	        </div>
	        <div class="block-style">
	            <div class="details-list-part">
	                <div class="details-list-tit">
	                    首页更新情况
	                </div>
	                <div class="details-list-content" id="updateHome_id">
	                	<ul id="updateHome_list_id">
	                	
	                	</ul>
	                </div>
	            </div>
	        </div>
	    </div>
	    <%@ include file="/newWeiXin/jsp/footer.jsp"%>
	<script language="javascript" type="text/javascript" src="<%= path%>/newWeiXin/js/updateHome/updateHomeOrgIndex.js"></script>
	<script type="text/javascript" src="<%= path %>/newWeiXin/js/common.js"></script> 
</body>
</html>

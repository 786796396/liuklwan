<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<title>首页更新不及时</title>
	 <%@ include file="/newWeiXin/jsp/common_new.jsp"%>
	  <link rel="stylesheet" href="<%= path %>/newWeiXin/css/hp-UpdateNotInTime.css"/>
  </head>
<body >
<div class="wrap">
		<input type="hidden" id="siteCode_id" value="${initMap.siteCode}"/>
		<input type="hidden" id="checkType_id" value="${initMap.checkType}"/>
        <div class="result-set">
            <div class="main-title ">
                <div class="title-block">
                    <div class="seven-words-tit  ">
                        <img src="<%= path %>/newWeiXin/images/hp-updatNotInTime.png" />
                        <span>首页更新不及时</span>
                    </div>
                </div>
            </div>
            <div class="result-detail">
                <p class="clearfix">
                    <span class="fl">扫描时间</span>
                    <span class="fr">${initMap.scanDate}</span>
                </p>
                <p class="clearfix last-item">
                    <span class="fl">首页更新不及时网站数</span>
                    <span class="fr">${initMap.securityHome}个</span>
                </p>
            </div>
        </div>
        <div class="block-style">
            <div class="details-list-part">
                <div class="details-list-tit">
                    首页更新不及时网站
                </div>
                <div class="details-list-content" id="securityHomeDays_id">
					<ul id="securityHomeDays_list_id">
					
					</ul>
                </div>
            </div>
        </div>
    </div>
     <%@ include file="/newWeiXin/jsp/footer.jsp"%>
	<script type="text/javascript" src="<%= path %>/newWeiXin/js/common.js"></script> 
	<script type="text/javascript" src="<%= path %>/newWeiXin/js/securityHome/securityHome.js"></script> 
</body>
</html>

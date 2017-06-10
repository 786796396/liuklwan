<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<title>首页连不通</title>
	 <%@ include file="/newWeiXin/jsp/common_new.jsp"%>
	 <link rel="stylesheet" href="<%= path %>/newWeiXin/css/hp-impassability-TB.css"/>
  </head>
<body >
<div class="wrap">
		<input type="hidden" id="siteCode_id" value="${initMap.siteCode}" />
 <div class="result-set">
            <div class="tb-main-title">
                <p class="font-32" id="siteName_id">${initMap.siteName}</p>
                <p class="font-20 margt-24">${initMap.siteCode}</p>
            </div>
            <div class="result-detail">
                <p class="clearfix">
                    <span class="fl">最近扫描时间</span>
                    <span class="fr">${initMap.scanDate}</span>
                </p>
                <p class="clearfix">
                    <span class="fl">扫描间隔时间</span>
                    <span class="fr">${initMap.queueStr}次</span>
                </p>
                <p class="clearfix last-item">
                    <span class="fl">连不通次数</span>
                    <span class="fr" id="pageTotal_id"></span>
                </p>
            </div>
        </div>
       <div  id="conn_home_tb_id">
        	<div id="conn_home_list_tb_id">

        	</div>
        </div>
        <div class="block-style details-info">
            <p class="clearfix">
                <span class="fl">7日连不通率（截至昨日）：</span>
                <span class="fr">${initMap.notConnPer7}</span>
            </p>
            <p class="clearfix">
                <span class="fl">7日内连不通次数：</span>
                <span class="fr">${initMap.notConnNum7}次</span>
            </p>
<!--             <p class="clearfix last-item">
                <span class="fl">7日内总扫描次数：</span>
                <span class="fr">30次</span>
            </p> -->
        </div>


    </div>
   <%@ include file="/newWeiXin/jsp/footer.jsp"%>
   <script language="javascript" type="text/javascript" src="<%= path %>/newWeiXin/js/connHome/connHomeTb.js"></script>
   <script src="<%= path %>/newWeiXin/js/common.js"></script>
</body>
</html>

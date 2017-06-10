<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<title>互动回应差</title>
	 <%@ include file="/newWeiXin/jsp/common_new.jsp"%>
  </head>
<body >

<div class="wrap">
		<input type="hidden" id="siteCode_id" value="${initMap.siteCode}" />
		<input type="hidden" id="servicePeroidId_id" value="${initMap.servicePeroidId}" />
        <div class="result-set">
            <div class="tb-main-title">
                <p class="font-32">${initMap.siteName}</p>
                <p class="font-20 margt-24">${initMap.siteCode}</p>
            </div>
            <div class="result-detail">
                <p class="clearfix">
                    <span class="fl">截至日期</span>
                    <span class="fr">${initMap.scanDate}</span>
                </p>
                <p class="clearfix last-item">
                    <span class="fl">互动回应差问题</span>
                    <span class="fr">${initMap.securityResponse}个</span>
                </p>
            </div>
        </div>
        <div class="block-style">
            <div class="details-list-part hp-tb">
                <div class="details-list-tit">
                    互动回应差问题
                </div>
                <div class="details-list-content" id="security_response_tb_list_id">
					<ul class='green-icon-part' id="security_response_tb_id">
					
					</ul>
                </div>
            </div>
        </div>

    </div>
   <%@ include file="/newWeiXin/jsp/footer.jsp"%>
   <script language="javascript" type="text/javascript" src="<%= path %>/newWeiXin/js/securityResponse/securityResponseTb.js?autoVersoin=<%= autoVersoin %>"></script>
   <script src="<%= path %>/newWeiXin/js/common.js"></script>
</body>
</html>

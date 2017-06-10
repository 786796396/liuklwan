<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<title>空白栏目</title>
	 <%@ include file="/newWeiXin/jsp/common_new.jsp"%>
  </head>
<body >

<div class="wrap">
		<input type="hidden" id="siteCode_id" value="${initMap.siteCode}" />
		<input type="hidden" id="servicePeroidId_id" value="${initMap.servicePeroidId}" />
		<input type="hidden" id="formYJ_id" value="${initMap.formYJ}" />
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
                    <span class="fl">空白栏目栏目数</span>
                    <span class="fr">${initMap.securityBlank}个</span>
                </p>
            </div>
        </div>
        <div class="block-style">
            <div class="details-list-part hp-tb">
                <div class="details-list-tit">
                   空白栏目
                </div>
                <div class="details-list-content" id="security_blank_tb_list_id">
					<ul class='green-icon-part' id="security_blank_tb_id">
					
					</ul>
                </div>
            </div>
        </div>
    </div>
   <%@ include file="/newWeiXin/jsp/footer.jsp"%>
   <script language="javascript" type="text/javascript" src="<%= path %>/newWeiXin/js/securityBlank/securityBlankTb.js?autoVersoin=<%= autoVersoin %>"></script>
   <script src="<%= path %>/newWeiXin/js/common.js"></script>
</body>
</html>

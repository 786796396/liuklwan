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
		<input type="hidden" id="checkType_id" value="${initMap.checkType}" />
		<input type="hidden" id="servicePeroidId_id" value="${initMap.servicePeroidId}" />
        <div class="result-set">
            <div class="main-title ">
                <div class="title-block">
                    <div class="five-words-tit">
                        <img src="<%=path %>/newWeiXin/images/dm-interactResponse.png" />
                        <span class="fr">互动回应差</span>
                    </div>
                </div>
            </div>

            <div class="result-detail">
            <p class="clearfix">
                <span class="fl">本次监测周期时间</span>
                <span class="fr">${initMap.beginDate}&nbsp;&nbsp;&nbsp;截至&nbsp;&nbsp;&nbsp;昨日</span>
            </p>
            <p class="clearfix">
                <span class="fl">互动回应差问题数量</span>
                <span class="fr">${initMap.securityResponse}条</span>
            </p>
            <p class="clearfix last-item">
                <span class="fl">问题站点数</span>
                <span class="fr">${initMap.securityResponseSite}个</span>
            </p>

        </div>
        </div>
        <div class="block-style">
            <div class="details-list-part">
                <div class="details-list-tit">
                    互动回应差
                </div>
                <div class="details-list-content" id="security_response_list_id">
					<ul id="security_response_id">
					</ul>
                </div>
            </div>
        </div>
    </div>
   <%@ include file="/newWeiXin/jsp/footer.jsp"%>
   <script language="javascript" type="text/javascript" src="<%= path %>/newWeiXin/js/securityResponse/securityResponseOrg.js?autoVersoin=<%= autoVersoin %>"></script>
   <script src="<%= path %>/newWeiXin/js/common.js"></script>
</body>
</html>

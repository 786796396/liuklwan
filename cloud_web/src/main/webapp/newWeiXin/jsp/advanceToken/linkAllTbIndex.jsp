<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<title>全站链接不可用</title>
	 <%@ include file="/newWeiXin/jsp/common_new.jsp"%>
	  <link rel="stylesheet" href="<%=path %>/newWeiXin/css/two-state.css?autoVersoin=<%= autoVersoin %>"/>
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
                    <span class="fl">截至时间</span>
                    <span class="fr">${initMap.scanDate}&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;今天</span>
                </p>
                <p class="clearfix last-item">
                    <span class="fl">全站不可用链接数</span>
                    <span class="fr">${initMap.websiteSum}个</span>
                </p>
            </div>
        </div>
        <div class="block-style">
            <div class="details-list-part hp-tb">
                <div class="details-list-tit" id="link_all_tb_confirm_id">

                </div>
                <div class="details-list-tit" id="link_all_tb_not_confirm_id">

                </div>
                <div class="details-list-content" id="link_all_tb_no_data_id">
                
                </div>
            </div>
        </div>

    </div>
   <%@ include file="/newWeiXin/jsp/footer.jsp"%>
   <script language="javascript" type="text/javascript" src="<%= path %>/newWeiXin/js/linkAll/linkAllTb.js?autoVersoin=<%= autoVersoin %>"></script>
   <script src="<%= path %>/newWeiXin/js/common.js"></script>
</body>
</html>

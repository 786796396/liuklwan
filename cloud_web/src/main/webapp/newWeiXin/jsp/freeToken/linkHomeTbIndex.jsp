<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<title>首页链接不可用</title>
	 <%@ include file="/newWeiXin/jsp/common_new.jsp"%>
	 <link rel="stylesheet" href="<%= path %>/newWeiXin/css/two-state.css"/>
  </head>
<body >
 <div class="wrap">
 		<input type="hidden" id="siteCode_id" value="${initMap.siteCode}" />
        <div class="result-set">
            <div class="tb-main-title">
                <p class="font-32">${initMap.siteName}</p>
                <p class="font-20 margt-24">${initMap.siteCode}</p>
            </div>
            <div class="result-detail">
                <p class="clearfix">
                    <span class="fl">扫描时间</span>
                    <span class="fr">${initMap.scanDate}</span>
                </p>
                <p class="clearfix last-item">
                    <span class="fl">首页不可用链接数</span>
                    <span class="fr">${initMap.linkHome}个</span>
                </p>
            </div>
        </div>
        <div class="block-style">
            <div class="details-list-part hp-tb">
                <div class="details-list-tit" id="confirm_link_id">
                </div>
				<div class="details-list-tit" id="not_confirm_link_id">
                </div>
                <div class="details-list-content" id="link_list_id">
                </div>
            </div>
        </div>

    </div>
   <%@ include file="/newWeiXin/jsp/footer.jsp"%>
   <script language="javascript" type="text/javascript" src="<%= path %>/newWeiXin/js/linkHome/linkHomeTb.js"></script>
   <script src="<%= path %>/newWeiXin/js/common.js"></script>
</body>
</html>

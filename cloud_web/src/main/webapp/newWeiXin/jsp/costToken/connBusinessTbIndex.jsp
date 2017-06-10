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
	  <div class="wrap">
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
                    <span class="fl">连不通业务系统数</span>
                    <span class="fr">${initMap.connBusiness}个</span>
                </p>
            </div>
        </div>
        <div class="block-style">
            <div class="details-list-part hp-tb">
                <div class="details-list-tit">
                    连不通业务系统
                </div>
                <div class="details-list-content" id="conn_business_list_id">
					<ul class='green-icon-part' id="conn_business_id">
					
					</ul>
                </div>
            </div>
        </div>

    </div>
	<%@ include file="/newWeiXin/jsp/footer.jsp"%>
	<script language="javascript" type="text/javascript" src="<%= path%>/newWeiXin/js/connBusiness/connBusinessTb.js"></script>
	<script type="text/javascript" src="<%= path %>/newWeiXin/js/common.js"></script> 
</body>
</html>

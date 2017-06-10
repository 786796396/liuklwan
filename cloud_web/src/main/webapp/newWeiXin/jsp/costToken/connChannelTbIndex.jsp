<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	 <title>关键栏目连不通</title>
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
                    <span class="fl">最近扫描时间</span>
                    <span class="fr">${initMap.scanDate}</span>
                </p>
                <p class="clearfix last-item">
                    <span class="fl">连不通栏目数</span>
                    <span class="fr">${initMap.connChannel}个</span>
                </p>
            </div>
        </div>
        <div class="block-style">
            <div class="details-list-part hp-tb">
                <div class="details-list-tit">
                    连不通关键栏目
                </div>
                <div class="details-list-content" id="connChannel_tb_id">
					<ul class='green-icon-part' id="connChannel_tb_list_id">
					
					</ul>
                </div>
            </div>
        </div>
    </div>
	<%@ include file="/newWeiXin/jsp/footer.jsp"%>
	<script language="javascript" type="text/javascript" src="<%= path%>/newWeiXin/js/connChannel/connChannelTb.js"></script>
	<script type="text/javascript" src="<%= path %>/newWeiXin/js/common.js"></script> 
</body>
</html>

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
	<input type="hidden" id="checkType_id" value="${initMap.checkType}" />
	 <div class="wrap">
        <div class="result-set">
            <div class="main-title ">
                <div class="title-block">
                    <div class="seven-words-tit">
                        <img src="<%= path %>/newWeiXin/images/dm-keyColumn.png" />
                        <span>关键栏目连不通</span>
                    </div>
                </div>
            </div>
            <div class="result-detail">
	            <p class="clearfix">
	                <span class="fl">扫描时间</span>
	                <span class="fr">${initMap.scanDate}</span>
	            </p>
	            <p class="clearfix">
	                <span class="fl">连不通栏目数</span>
	                <span class="fr">${initMap.connChannel}个</span>
	            </p>
	            <p class="clearfix last-item">
	                <span class="fl">问题站点数</span>
	                <span class="fr">${initMap.connChannelSite}个</span>
	            </p>
        	</div>
        </div>
        <div class="block-style">
            <div class="details-list-part">
                <div class="details-list-tit">
                   关键栏目连不通情况
                </div>
                <div class="details-list-content" id="connChannel_id">
					<ul id="connChannel_list_id">
					
					</ul>
                </div>

            </div>
        </div>
    </div>
	<%@ include file="/newWeiXin/jsp/footer.jsp"%>
	<script language="javascript" type="text/javascript" src="<%= path%>/newWeiXin/js/connChannel/connChannelOrg.js"></script>
	<script type="text/javascript" src="<%= path %>/newWeiXin/js/common.js"></script> 
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	 <title>栏目内容更新量</title>
	 <%@ include file="/newWeiXin/jsp/common_new.jsp"%>
  </head>
<body>
	<div class="wrap">
			<input type="hidden" id="siteCode_id" value="${initMap.siteCode}">
			<input type="hidden" id="checkType_id" value="${initMap.checkType}">
	        <div class="result-set">
            <div class="main-title ">
                <div class="title-block">
                    <div class="seven-words-tit">
                        <img src="<%= path %>/newWeiXin/images/dm-columnContent.png" />
                        <span>栏目内容更新量</span>
                    </div>
                </div>
            </div>

            <div class="result-detail">
            <p class="clearfix">
                <span class="fl">扫描时间</span>
                <span class="fr">${initMap.scanDate}</span>
            </p>
            <p class="clearfix">
                <span class="fl">栏目内容更新量</span>
                <span class="fr">${initMap.updateChannel}条</span>
            </p>
            <p class="clearfix last-item">
                <span class="fl">分布站点</span>
                <span class="fr">${initMap.updateChannelSite}个</span>
            </p>
        </div>
        </div>
        <div class="block-style">
            <div class="details-list-part">
                <div class="details-list-tit">
                    栏目内容更新情况
                </div>
                <div class="details-list-content" id="update_channel_org_list_id">
					<ul id="update_channel_org_id">
					
					</ul>
                </div>

            </div>
        </div>
    </div>
	 <%@ include file="/newWeiXin/jsp/footer.jsp"%>
	<script language="javascript" type="text/javascript" src="<%= path%>/newWeiXin/js/updateChannel/updateChannelOrgIndex.js"></script>
	<script type="text/javascript" src="<%= path %>/newWeiXin/js/common.js"></script> 
</body>
</html>

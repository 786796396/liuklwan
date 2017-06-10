<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	 <title>栏目更新不及时</title>
	 <%@ include file="/newWeiXin/jsp/common_new.jsp"%>
	 <link rel="stylesheet" type="text/css" href="<%= path %>/newWeiXin/css/dm-ColumnUpdate.css" />
  </head>
<body>
	<input type="hidden" id="siteCode_id" value="${initMap.siteCode}" />
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
                    <span class="fl">不及时更新栏目数</span>
                    <span class="fr">${initMap.notUpdateNum}条</span>
                </p>
            </div>
        </div>
        <div class="block-style">
            <div class="details-list-part hp-tb">
                <div class="details-list-tit">
                    <div class="select-box-cu">
                    	<div id="select_id">
	                        <span class="show-item">全部逾期未更新</span>
	                        <i></i>
                        </div>
                        <ul class="show-ul">
                            <li value="4">全部逾期未更新</li>
                            <li value="1">动态、要闻类</li>
                            <li value="2">通知公告、政策文件类</li>
                            <li value="3">人事、规划计划类</li>
                        </ul>
                    </div>
                </div>
                <div class="details-list-content" id="security_channel_tb_list_id">
                   <ul class='green-icon-part' id="security_channel_tb_id">
                   
                   </ul>
                </div>
            </div>
        </div>
    </div>
	<%@ include file="/newWeiXin/jsp/footer.jsp"%>
	<script language="javascript" type="text/javascript" src="<%= path%>/newWeiXin/js/securityChannel/securityChannelTb.js"></script>
	<script type="text/javascript" src="<%= path %>/newWeiXin/js/common.js"></script> 
</body>
</html>

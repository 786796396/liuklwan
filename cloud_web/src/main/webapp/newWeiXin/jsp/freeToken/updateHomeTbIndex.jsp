<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	 <title>首页更新量</title>
	 <%@ include file="/newWeiXin/jsp/common_new.jsp"%>
	 <link rel="stylesheet" type="text/css" href="<%= path %>/newWeiXin/css/hp-UpdateQuantity.css" />
  </head>
<body>
 <div class="wrap">
        <div class="result-set">
            <div class="tb-main-title">
                <p class="font-32">${initMap.siteName}</p>
                <p class="font-20 margt-24" id="siteCode_id">${initMap.siteCode}</p>
            </div>
            <div class="result-detail">
                <p class="clearfix">
                    <span class="fl">扫描时间</span>
                    <span class="fr" id="scanDate_id">${initMap.scanDate}</span>
                </p>
                <p class="clearfix last-item">
                    <span class="fl">首页更新</span>
                    <span class="fr">${initMap.updateHome}条</span>
                </p>
            </div>
        </div>
        <div class="block-style">
            <div class="details-list-part">
                <div class="details-list-tit">
                    首页更新情况
                </div>
                <div class="details-list-content" id="tb_update_home_id">
					<ul class='artical-list' id="tb_update_home_list_id">
					
					</ul>
                </div>
            </div>
        </div>
    </div>
	    <%@ include file="/newWeiXin/jsp/footer.jsp"%>
	<script language="javascript" type="text/javascript" src="<%= path%>/newWeiXin/js/updateHome/updateHomeTbIndex.js"></script>
	<script type="text/javascript" src="<%= path %>/newWeiXin/js/common.js"></script> 
	
</body>
</html>

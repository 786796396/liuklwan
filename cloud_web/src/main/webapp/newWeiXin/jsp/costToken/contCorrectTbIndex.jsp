<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	 <title>疑似错别字</title>
	 <%@ include file="/newWeiXin/jsp/common_new.jsp"%>
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
                    <span class="fl">疑似错别字</span>
                    <span class="fr">${initMap.contCorrectNum}个</span>
                </p>
            </div>
        </div>
        <div class="block-style">
            <div class="details-list-part hp-tb">
                <div class="details-list-tit">
                    疑似错别字
                </div>
                <div class="details-list-content" id="contCorrectTb_list_id">
					<ul class='green-icon-part' id="contCorrectTb_id">
					
					</ul>
                </div>
            </div>
        </div>
    </div>
	<%@ include file="/newWeiXin/jsp/footer.jsp"%>
	<script language="javascript" type="text/javascript" src="<%= path%>/newWeiXin/js/contCorrect/contCorrectTb.js"></script>
	<script type="text/javascript" src="<%= path %>/newWeiXin/js/common.js"></script> 
</body>
</html>

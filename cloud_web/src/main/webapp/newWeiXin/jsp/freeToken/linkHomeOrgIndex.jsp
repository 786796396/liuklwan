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
		<input type="hidden" id="checkType_id" value="${initMap.checkType}" />
        <div class="result-set">
            <div class="main-title ">
                <div class="title-block">
                    <div class="seven-words-tit hp-link-unab-tb">
                        <img src="<%= path %>/newWeiXin/images/hp-LinkUnavailable.png" />
                        <span>首页链接不可用</span>
                    </div>
                </div>
            </div>

            <div class="result-detail">
            <p class="clearfix">
                <span class="fl">扫描时间 </span>
                <span class="fr">${initMap.scanDate}</span>
            </p>
            <p class="clearfix">
                <span class="fl">首页不可用的链接数</span>
                <span class="fr">${initMap.linkHome}个</span>
            </p>
            <p class="clearfix last-item">
                <span class="fl">问题站点数</span>
                <span class="fr">${initMap.linkHomeSite}个</span>
            </p>
        </div>
        </div>
        <div class="block-style">
            <div class="details-list-part">
                <div class="details-list-tit">
                 首页链接不可用情况
                </div>
                <div class="details-list-content" id="link_home_org_list_id">
               		<ul id="link_home_org_id">
               	
               		</ul>

                </div>

            </div>
        </div>
    </div>
   <%@ include file="/newWeiXin/jsp/footer.jsp"%>
   <script language="javascript" type="text/javascript" src="<%= path %>/newWeiXin/js/linkHome/linkHomeOrg.js"></script>
   <script src="<%= path %>/newWeiXin/js/common.js"></script>
</body>
</html>

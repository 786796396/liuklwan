<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<title>严重问题</title>
	 <%@ include file="/newWeiXin/jsp/common_new.jsp"%>
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
                <p class="clearfix last-item">
                    <span class="fl">严重问题数</span>
                    <span class="fr">${initMap.fatalErrorNum}个</span>
                </p>
            </div>
        </div>
        <div class="block-style">
            <div class="details-list-part hp-tb">
                <div class="details-list-tit">
                    虚假或伪造内容（<span id="fatal_error_num_id1"></span>个）
                </div>
                <div class="details-list-content" id="fatal_error_tb_id1">

                </div>
            </div>
        </div>
        <div class="block-style">
            <div class="details-list-part hp-tb">
                <div class="details-list-tit">
                    反动、暴力或色情内容（<span id="fatal_error_num_id2"></span>个）
                </div>
                <div class="details-list-content" id="fatal_error_tb_id2">
       
                </div>
            </div>
        </div>
        <div class="block-style">
            <div class="details-list-part hp-tb">
                <div class="details-list-tit">
                    其他问题（<span id="fatal_error_num_id3"></span>个）
                </div>
                <div class="details-list-content" id="fatal_error_tb_id3">

                </div>
            </div>
        </div>
    </div>
   <%@ include file="/newWeiXin/jsp/footer.jsp"%>
   <script language="javascript" type="text/javascript" src="<%= path %>/newWeiXin/js/securityFatalError/securityFatalErrorTb.js?autoVersoin=<%= autoVersoin %>"></script>
   <script src="<%= path %>/newWeiXin/js/common.js"></script>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<title id="title_id">${initMap.siteName}</title>
	 <%@ include file="/newWeiXin/jsp/common_new.jsp"%>
	 <link rel="stylesheet" type="text/css" href="<%= path %>/newWeiXin/css/home-page_tb.css" />
  </head>
<body style="background:#fbfbfb;">
	<div class="wrap">
        <div class="hp-head">
        	<input type="hidden" id="is_cost_id" value="${initMap.isCost}">
        	<input type="hidden" id="is_advance_id" value="${initMap.isAdvance}">
        	<input type="hidden" id="siteCode_id" value="${initMap.siteCode}">
        	<input type="hidden" id="open_id" value="${initMap.openId}">
        	<input type="hidden" id="servicePeroidId_id" value="${initMap.servicePeroidId}">
            <div class="top-block">
            </div>
            <div class="health-index-part">
                <div class="chart-body">
                    <canvas width="372" height="348" id="chart-gauge" class="chart-gauge"></canvas>
                    <div class="chart-info">
                        <h4 class="health-index-tit">健康指数</h4>
                        <h3 class="health-index-numb"><span id="indexCount_id"></span></h3>
                    </div>
                </div>

            </div>
            <div class="des-tips">
                <p>健康指数领先全国 <span id="leadAvgRate_id"></span> 的政府网站</p>
            </div>
        </div>
        <div class="detect block-style">
            <h4 class="title">日常监测</h4>
            <div class="every-line clearfix margb-58">
                <div class="fl half-part">
                    <i class="fl daily-first hp-item-icon"></i>
                    <div class="fl every-item" onclick="connHomeTb()">
                        <h5>首页连不通</h5>
                        <p>今日连不通<span id="connHome_id"><span></p>
                    </div>
                </div>
                <div class="fr half-part">
                    <i class="fl daily-second hp-item-icon"></i>
                    <div class="fl every-item" onclick="updateHomeTb()">
                        <h5>首页更新量</h5>
                        <p>有<span id="updateHome_id"><span>条更新</p>
                    </div>
                </div>
            </div>
            <div class="every-line clearfix">
                <div class="fl half-part">
                    <i class="fl daily-third hp-item-icon"></i>
                    <div class="fl every-item" onclick="securityHomeTb()">
                        <h5>首页更新不及时</h5>
                        <p id="securityHome_id">最近扫描</p>
                    </div>
                </div>
                <div class="fr half-part">
                    <i class="fl daily-fourth hp-item-icon"></i>
                    <div class="fl every-item" onclick="linkHomeTb()">
                        <h5>首页链接不可用</h5>
                        <p>有<span id="linkHome_id"><span>个问题</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="detect block-style" id="shendu_id">
            <h4 class="title">深度检测</h4>
            <div class="every-line clearfix margb-58">
                <div class="fl half-part">
                    <i class="fl depth-first hp-item-icon"></i>
                    <div class="fl every-item" onclick="connChannelTb()">
                        <h5>关键栏目连不通</h5>
                        <p>有<span id="connChannel_id"><span>个问题</p>
                    </div>
                </div>
                <div class="fr half-part">
                    <i class="fl depth-second hp-item-icon"></i>
                    <div class="fl every-item" onclick="connBusinessTb()">
                        <h5>业务系统连不通</h5>
                        <p>有<span id="connBusiness_id"><span>个问题</p>
                    </div>
                </div>
            </div>
            <div class="every-line clearfix margb-58">
                <div class="fl half-part">
                    <i class="fl depth-third hp-item-icon"></i>
                    <div class="fl every-item" onclick="updateChannelTb()">
                        <h5>栏目内容更新量</h5>
                        <p>有<span id="updateChannel_id"><span>个更新</p>
                    </div>
                </div>
                <div class="fr half-part">
                    <i class="fl depth-fourth hp-item-icon"></i>
                    <div class="fl every-item" onclick="securityChannelTb()">
                        <h5>栏目更新不及时</h5>
                        <p>有<span id="securityChannel_id"><span>个问题</p>
                    </div>
                </div>
            </div>
            <div class="every-line clearfix margb-58">
            	 <div class="fl half-part">
                     <i class="fl depth-eighth hp-item-icon"></i>
                    <div class="fl every-item" onclick="contCorrectTb()">
                        <h5>疑似错别字</h5>
                        <p>有<span id="contCorrectNum_id"><span>个问题</p>
                    </div>
                </div>
                <div class="fr half-part" id="isAdvance_id1">
                    <i class="fl depth-sixth hp-item-icon"></i>
                    <div class="fl every-item" onclick="securityResponseTb()">
                        <h5>互动回应差</h5>
                        <p>有<span id="securityResponse_id"><span>个问题</p>
                    </div>
                </div>
            </div>
            <div class="every-line clearfix margb-58" id="isAdvance_id2">
                <div class="fl half-part">
                    <i class="fl depth-seventh hp-item-icon"></i>
                    <div class="fl every-item" onclick="securityServiceTb()">
                        <h5>服务不实用</h5>
                        <p>有<span id="securityService_id"><span>个问题</p>
                    </div>
                </div>
                <div class="fl half-part">
                   <i class="fl depth-fifth hp-item-icon"></i>
                    <div class="fl every-item" onclick="securityBlankTb()">
                        <h5>空白栏目</h5>
                        <p>有<span id="securityBlank_id"><span>个问题</p>
                    </div>
                </div>
            </div>
            <div class="every-line clearfix margb-58" id="isAdvance_id3">
                <div class="fl half-part">
                    <i class="fl depth-eleventh hp-item-icon"></i>
                    <div class="fl every-item" onclick="securityFatalErrorTb()">
                        <h5>严重问题</h5>
                        <p>有<span id="securityFatalError_id"><span>个问题</p>
                    </div>
                </div>
                <div class="fl half-part">
                    <i class="fl depth-ninth hp-item-icon"></i>
                    <div class="fl every-item" onclick="linkAllTb()">
                        <h5>全站链接不可用</h5>
                        <p>有<span id="linkAll_id"><span>个问题</p>
                    </div>
                </div>
            </div>
<!--             <div class="every-line clearfix">
            	<div class="fl half-part">
                    <i class="fl depth-tenth hp-item-icon"></i>
                    <div class="fl every-item">
                        <h5>网站安全</h5>
                        <p>有89个问题</p>
                    </div>
                </div>
            </div> -->
        </div>
    </div>
    <%@ include file="/newWeiXin/jsp/footer.jsp"%>
	<script language="javascript" type="text/javascript" src="<%= path%>/newWeiXin/js/checkResult/checkResultTb.js"></script>
	 <script type="text/javascript" src="<%= path %>/newWeiXin/js/common.js"></script> 
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<title>${initMap.siteName}</title>
	 <%@ include file="/newWeiXin/jsp/common_new.jsp"%>
	 <link rel="stylesheet" type="text/css" href="<%= path %>/newWeiXin/css/home-page.css" />
  </head>
<body>
    <div class="wrap">
        <input type="hidden" id="isOrgCost_id" value="${initMap.isOrgCost}">
       	<input type="hidden" id="isOrgAdvance_id" value="${initMap.isOrgAdvance}">
       	<input type="hidden" id="siteCode_id" value="${initMap.siteCode}">
       	<input type="hidden" id="open_id" value="${initMap.openId}">
       	<input type="hidden" id="isHaveNext_id" value="${initMap.isHaveNext}">
       	<input type="hidden" id="servicePeroidId_id" value="${initMap.servicePeroidId}">
        <div class="hp-head">
            <div class="top-block">
                <div class="select-box">
                    <span class="show-item">全部站点</span>
                    <i></i> 
                    <ul class="show-ul">
						<li value='0'>全部站点</li>
						<li value='1'>本级门户</li>
						<li value='2'>本级部门</li>
						<li class='last-item' value='3'>下属站点</li>
                    </ul>
                </div>
            </div>
            <div class="health-index-part">
                <div class="chart-body">
                    <canvas width="372" height="348" id="chart-gauge" class="chart-gauge"></canvas>
                    <div class="chart-info">
                        <h4 class="health-index-tit">健康指数</h4>
                        <h3 class="health-index-numb"><span id="indexCount_id"></span></h3>
                        <p class="site-count">涵盖站点数量：<span id="typeSite_id"></span></p>
                        <div class="index-sort" onclick="indexCountSort()">指数排名</div>
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
                    <div class="fl every-item" onclick="connHomeOrg()">
                        <h5>首页连不通</h5>
                        <p>今日扫描</p>
                        <p>有<span id="connHome_id"></span></p>
                    </div>
                </div>
                <div class="fr half-part">
                    <i class="fl daily-second hp-item-icon"></i>
                    <div class="fl every-item" onclick="updateHomeOrg()">
                        <h5>首页更新量</h5>
                        <p><span id="updateHomeSite_id"></span></p>
                        <p>有<span id="updateHome_id"></span></p>
                    </div>
                </div>
            </div>
            <div class="every-line clearfix">
                <div class="fl half-part">
                    <i class="fl daily-third hp-item-icon"></i>
                    <div class="fl every-item" onclick="securityHomeOrg()">
                        <h5>首页更新不及时</h5>
                        <p>有<span id="securityHome_id"></span></p>
                        <p>首页更新不及时</p>
                    </div>
                </div>
                <div class="fr half-part">
                    <i class="fl daily-fourth hp-item-icon"></i>
                    <div class="fl every-item" onclick="linkHomeOrg()">
                        <h5>首页链接不可用</h5>
                        <p><span id="linkHomeSite_id"></span></p>
                        <p>有<span id="linkHome_id"></span></p>
                    </div>
                </div>
            </div>
        </div>
        <div class="detect block-style" id="shendu_id">
            <h4 class="title">深度检测</h4>
            <div class="every-line clearfix margb-58">
                <div class="fl half-part">
                    <i class="fl depth-first hp-item-icon"></i>
                    <div class="fl every-item"  onclick="connChannelOrg()">
                        <h5>关键栏目连不通</h5>
                        <p><span id="connChannelSite_id"></span></p>
                        <p>有<span id="connChannel_id"></span></p>
                    </div>
                </div>
                <div class="fr half-part">
                    <i class="fl depth-second hp-item-icon"></i>
                    <div class="fl every-item" onclick="connBusinessOrg()">
                        <h5>业务系统连不通</h5>
                        <p><span id="connBusinessSite_id"></span></p>
                        <p>有<span id="connBusiness_id"></span></p>
                    </div>
                </div>
            </div>
            <div class="every-line clearfix margb-58">
                <div class="fl half-part">
                    <i class="fl depth-third hp-item-icon"></i>
                    <div class="fl every-item" onclick="updateChannelOrg()">
                        <h5>栏目内容更新量</h5>
                        <p><span id="updateChannelSite_id"></span></p>
                        <p>有<span id="updateChannel_id"></span></p>
                    </div>
                </div>
                <div class="fr half-part">
                    <i class="fl depth-fourth hp-item-icon"></i>
                    <div class="fl every-item" onclick="securityChannelOrg()">
                        <h5>栏目更新不及时</h5>
                        <p><span id="securityChannelSite_id"></span></p>
                        <p>有<span id="securityChannel_id"></span></p>
                    </div>
                </div>
            </div>
            <div class="every-line clearfix margb-58">
                <div class="fl half-part">
                    <i class="fl depth-eighth hp-item-icon"></i>
                    <div class="fl every-item" onclick="contCorrectOrg()">
                        <h5>疑似错别字</h5>
                        <p><span id="contCorrectSite_id"></span></p>
                        <p>有<span id="contCorrectNum_id"></span></p>
                    </div>
                </div>
                <div class="fr half-part" id="isAdvance_id1">
                    <i class="fl depth-sixth hp-item-icon"></i>
                    <div class="fl every-item" onclick="securityResponseOrg()">
                        <h5>互动回应差</h5>
                        <p><span id="securityResponseSite_id"></span></p>
                        <p>有<span id="securityResponse_id"></span></p>
                    </div>
                </div>
            </div>
            <div class="every-line clearfix margb-58" id="isAdvance_id2">
                <div class="fl half-part">
                    <i class="fl depth-seventh hp-item-icon"></i>
                    <div class="fl every-item" onclick="securityServiceOrg()">
                        <h5>服务不实用</h5>
                        <p><span id="securityServiceSite_id"></span></p>
                        <p>有<span id="securityService_id"></span></p>
                    </div>
                </div>
                <div class="fr half-part">
                    <i class="fl depth-fifth hp-item-icon"></i>
                    <div class="fl every-item" onclick="securityBlankOrg()">
                        <h5>空白栏目</h5>
                        <p><span id="securityBlankSite_id"></span></p>
                        <p>有<span id="securityBlank_id"></span></p>
                    </div>
                </div>
            </div>
            <div class="every-line clearfix margb-58" id="isAdvance_id3">
                <div class="fl half-part">
                    <i class="fl depth-ninth hp-item-icon"></i>
                    <div class="fl every-item" onclick="securityFatalErrorOrg()">
                        <h5>严重问题</h5>
                        <p><span id="securityFatalSite_id"></span></p>
                        <p>有<span id="securityFatalError_id"></span></p>
                    </div>
                </div>
                <div class="fr half-part">
                    <i class="fl depth-tenth hp-item-icon"></i>
                    <div class="fl every-item" onclick="linkAllOrg()">
                        <h5>全站链接不可用</h5>
                        <p><span id="linkAllSite_id"></span></p>
                        <p>有<span id="linkAll_id"></span></p>
                    </div>
                </div>
            </div>
<!--             <div class="every-line clearfix">
                <div class="fl half-part">
                    <i class="fl depth-eleventh hp-item-icon"></i>
                    <div class="fl every-item">
                        <h5>网站安全</h5>
                        <p>46个网站</p>
                        <p>有89个问题</p>
                    </div>
                </div>
            </div> -->
        </div>
    </div>
    <%@ include file="/newWeiXin/jsp/footer.jsp"%>
	<script language="javascript" type="text/javascript" src="<%= path%>/newWeiXin/js/checkResult/checkResultOrg.js"></script>
	<script type="text/javascript" src="<%= path %>/newWeiXin/js/common.js"></script> 
</body>
</html>

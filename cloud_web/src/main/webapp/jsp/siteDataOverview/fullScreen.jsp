<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=path%>/css/OverviewOfTheSiteData_FullScreen_version2.css"/>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/roll/liMarquee.css"/>
<title>政府网站云监管平台大数据分析</title>
<style>
 .chart-info1{position: absolute;
    top: 25px;
    width: 100%;
    left: 36px;color: #fff;}
.z_box_header1 {
    margin:0 auto;
    min-height:208px;    z-index: 94;
    width:180px;
    position: relative;
    width:200px;
}
.chart-body1{width:200px; height:200px; margin-top:8px;}
.num1_msg{line-height: 19px; text-align:center; } 
</style>
</head>
<body class="OV_FullScreen" style="background:#060608;">
<input type="hidden" id="loginSiteCode" value="${sessionScope.shiroUser.siteCode}">
<input type="hidden" id="loginName" value="${sessionScope.shiroUser.userName}">
<p id="backToTop" style="display:none;">
    <a title="回到顶部" href="javascript:void(0);">
        <img src="<%=path%>/images/common/top-hover.png"/>
        <img class="top-img" src="<%=path%>/images/common/top.png"/>
    </a>
</p>
<!--<iframe class="main_iframe" src="../common/header.html" marginheight="0" marginwidth="0" frameborder="0" scrolling="no" width="100%" height="60" style="position:relative; z-index:10;"></iframe>-->
<!--<div class="page_tit nav">
    <i class="z_h_icon pull-left"></i>
    <h3 class="pull-left">bm54130001- 广州市政府</h3>
    <div class="z_h_time pull-right">数据更新时间：2015-08-16  24：00：00</div>
</div>-->
<div class="ov-page_nav">
    <div class="ov-page_nav_inner clearfix">
        <a href="" class="kp_logo fl">
            <img src="<%=path%>/images/common/logo.png" alt=""/>
        </a>
        <div class="fl">
            <div class="ov-title fl">政府网站云监管大数据分析</div>
           <!--  <div class="ov-time_date fl">2016年10月08日数据</div> -->
        </div>
        <div class="fr">
            <div class="exit-fs fr" onclick="subExit()"><i></i>退出全屏</div>
            <div class="full_screen-line fr"></div>
            <!-- <div class="ov-time fr">2016-10-08 14:37:00</div> -->
        </div>
    </div>

</div>
<div class="main-container container" style="background: #060608;">
	<div class="row-fluid">
        <!--<iframe class="main_iframe" src="../common/leftmenu.html" marginheight="0" marginwidth="0" frameborder="0" scrolling="no" width="225" style="position:absolute; height:100%; z-index:10;"></iframe>-->
        <div class="page-content ovfs clearfix">
            <!--需要填充的内容开始-->
            <div class="left_parts fl">
                <div class="first_part every-small_part clearfix">
                    <div class="fl left">
                        <div class="z_box_header1" >
                            <div class="chart-body1">
                                <canvas width="180" height="180" id="chart-gauge" style="position:relative; left:50%; margin-left:-90px; margin-top:10px;"></canvas>
                                <div class="chart-info1">
                                    <h3 class="zhi_s"><span id="centerId"></span>政府网站</br>健康指数</h3>
                                    <!-- &lt;!&ndash;/z_box_header&ndash;&gt; -->
                                    <div class="num1" id="health_index" style="text-align:center; font:bold 22px/56px 'Microsoft YaHei','微软雅黑';"></div>
                                    <div class="num1_msg" style="font:normal 19px 'Microsoft YaHei','微软雅黑';"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="fl right">
                       <div class="row">
                           <!--  <ul class="qus_nav pull-right">
                                <li class="qus_icon1"><i></i>全国政府网站</li>
                                <li class="qus_icon2"><i></i>广州市政府网站群</li>
                            </ul> -->
                        </div>
                        <div class="line_chart" id="index_line" style="height: 100%;width: 100%;margin-top: 4px;min-width:50px;"></div>
                    </div>
                </div>
                <div class="second_part every-small_part">
					<div id="map-wrap" style="height:90%;width:85%;background-attachment:fixed; position:relative; margin-left:10%;;margin-top:2%;margin-bottom: 2%;">
                    
                    </div>
                </div>
            </div>
            <div class="center_parts fl">
                <div class="first_part every-small_part clearfix">
                    <h4 class="tit ques_report-tit">问题实时播报</h4>
                    <div class="ProblemReport">
                        <ul class="PR-content">
                            <li class="underline head-tit">
                                <ul class="clearfix"><li class="fl websiteName">网站名称</li><li class="fl findQues">发现问题</li><li class="fl">时间</li></ul>
                            </li>
                        </ul>
                        <div class="active_report">
                            <ul class="PR-content" id="rollDatas">
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="second_part">
                    <div class="top every-small_part">
                        <div class="top_f clearfix">
                            <div class="show_data_part fl">
                                <h4>监测站点数量</h4>
                                <p class="green" id="count1"></p>
                            </div>
                            <div class="show_img_part fl" style=" height:90px;" id="">
                                <div id="fenbu" style="width:90%; height:90%; margin:5px auto;"></div>
                            </div>
                            <!-- <div class="show_tab_part fr"> -->
                            <div class="table_box fr">
                                <table>
                                    <tr>
                                        <th class="w10p"></th>
                                        <th>状态</th>
                                        <!-- <th>数量</th> -->
                                        <th>比例</th>
                                    </tr>
                                    <tr>
                                        <td class="w10p">
                                            <i class="yuan_1"></i>
                                        </td>
                                        <td>正常</td>
                                        <!-- <td><span id="zhengChang"></span></td> -->
                                        <td><span id="zhengChangPer"></span></td>
                                    </tr>
                                    <tr>
                                        <td class="w10p">
                                            <i class="yuan_2"></i>
                                        </td>
                                        <td>例外</td>
                                        <!--  <td><span id="liWai"></span></td> -->
                                        <td><span id="liWaiPer"></span></td>
                                    </tr>
                                    <tr>
                                        <td class="w10p">
                                            <i class="yuan_3"></i>
                                        </td>
                                        <td>关停</td>
                                        <!--  <td><span id="guanTing"></span></td> -->
                                        <td><span id="guanTingPer"></td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <div class="top_s clearfix">
                            <div class="show_data_part fl">
                                <h4 class="" style="">监测页面数量</h4>
                                <p class=blue id="Pages"></p>
                            </div>
                            <div class="show_img_part fr">
                                <h4 class="" style="color:#0f171c;"></h4>
                                <img class="" src="<%=path%>/images/zanyong/blue_lines.png" alt=""/>
                            </div>
                        </div>
                        <div class="top_t clearfix">
                            <div class="show_data_part fl">
                                <h4>发现问题数量</h4>
                                <p class="red" id="Ques"></p>
                            </div>
                            <div class="show_img_part fr">
                                <h4 class="" style="color:#0f171c;">qqqq</h4>
                                <img class="" src="<%=path%>/images/zanyong/white_lines.png" alt=""/>
                            </div>
                        </div>
                    </div>
                    <div class="bottom every-small_part" id="visitData"></div>
                </div>
            </div>

<div class="right_parts fl">
                <div class="first_part clearfix">
                    <div class="top">
                        <div class="tit">首页连通率  ( 一周 )</div>
                        <div class="content"   onclick="toBigDateJsp();">
                            <!-- <img src="../../images/zanyong/show_8.jpg" alt=""/> -->
                            <h4 class="colo-5e6a78" id="linkErrSiteProp_h" style="cursor:pointer"  data-container="body" data-toggle="popover" data-placement="top" data-content="正常连通的次数/连接总次数">
                                
                                <span>%</span>
                                <!-- <div style="position:relative; width:100%; line-height:20px;">
                                     <div style="position:absolute; top:0; left:0; opacity:0.6; background:#404a59; z-index:5;"></div>
                                     <p style="position:absolute; top:0;left:0; color:#fff; z-index:5;"></p>
                                 </div>-->

                            </h4>
                            <div class="pro">
                                <span id="linkErrSiteProp_span" class="bg_oobcd5"></span>
                            </div>
                        </div>
                    </div>
                    <div class="bottom">
                        <div class="tit">首页正常更新率  ( 两周 )</div>
                        <div class="content"   onclick="toBigDateJsp();">
                            <!-- <img src="../../images/zanyong/show_8.jpg" alt=""/> -->
                            <h4 class="colo-5e6a78" id="noUpdateSiteProp_h" style="cursor:pointer" data-container="body" data-toggle="popover" data-placement="top" data-content="首页在14天内有更新的网站占比">
                                
                                <span>%</span>
                            </h4>
                            <div class="pro">
                                <span id="noUpdateSiteProp_span"></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="second_part">
                        <div style="margin-top:10px;">
                            <div class="tit">首页不可用链接数( 昨天 )  </div>
                            <div class="content" style="cursor:pointer" onclick="toBigDateJsp();">
                                <h4 class="colo-5e6a78" id="indexdeadnum" data-container="body" data-toggle="popover" data-placement="top" data-content="首页不可用链接的个数">
                                    <span>个</span>
                                </h4>
                                <i class="first"></i>
                            </div>
                        </div>
                        <div>
                            <div class="tit">内容更新总量 ( 昨天 )  </div>
                            <div class="content" style="cursor:pointer" onclick="toBigDateJsp();">
                                <h4 class="colo-5e6a78" id="aveupdatenum" data-container="body" data-toggle="popover" data-placement="top" data-content="网站总更新量">
                                   <span>个</span>

                                </h4>
                                <i class="second" style="height:46px;"></i>
                            </div>
                        </div>
                        <div>
                            <div class="tit">首页更新总量 ( 昨天 ) </div>
                            <div class="content" style="cursor:pointer" onclick="toBigDateJsp();">
                                <h4 class="colo-5e6a78" id="updatenum" data-container="body" data-toggle="popover" data-placement="top" data-content="首页总更新量">
                                    <span>个</span>

                                </h4>
                                <i class="third" style="height:46px;"></i>
                            </div>
                        </div>
                </div>
            </div>

            <!--需要填充的内容结束-->
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->
<script language="javascript" type="text/javascript">
$(function(){
    /*该页面用到的js*/
    
    $(".baobei_table_part table tr:odd").css("background","#f7faff");
    $(".baobei_table_part table tbody tr:odd").hover(function(){
        $(this).css("background","#b5f0dd");
    },function(){
        $(this).css("background","");
    });

    $(".baobei_table_part table tbody tr:even").hover(function(){
        $(this).css("background","#b5f0dd");
    },function(){
        $(this).css("background","#f7faff");
    });

	$(window).scroll(function () {
		if ($(window).scrollTop() > 100) {
			$("#backToTop").fadeIn(500);
		} else {
			$("#backToTop").fadeOut(500);
		}
	});
	$("#backToTop").click(function () {
		$('body,html').animate({scrollTop: 0}, 600);
		return false;
	});

});
	</script>
	<script type="text/javascript" src="<%=path%>/js/echarts.min.js"></script> 
	<script language="javascript"  src="<%=path%>/js/map/jsCitys/citysData/geoData.js"></script>
	<%--
	<script language="javascript"  src="<%=path%>/js/map/jsCitys/jsCitys/${sessionScope.shiroUser.siteCode}.js"></script>
	 <script type="text/javascript" src="<%=path%>/js/bigDataAnalysis/bigData.min.js"></script> --%>

	<script language="javascript"  src="<%=path%>/js/gauge.js"></script> 
	<script language="javascript"  src="<%=path%>/js/jquery.liMarquee.js"></script>
	
	<script language="javascript"  src="<%=path%>/js/siteDataOverview/siteDataOverviewChart_FullScreen_new.js"></script>
		<script type="text/javascript" src="<%=path%>/js/common/bigDataCommon.js"></script>
</body>
</html>
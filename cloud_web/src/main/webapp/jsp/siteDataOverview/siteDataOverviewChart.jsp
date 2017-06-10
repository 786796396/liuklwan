<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=path%>/css/OverviewOfTheSiteData.css"/>
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
<body class="OV">
 <%@ include file="/common/top.jsp"%>
<input type="hidden" id="loginSiteCode" value="${sessionScope.shiroUser.siteCode}">
<input type="hidden" id="loginName" value="${sessionScope.shiroUser.userName}">
	<div class="page_wrap">
		<div class="main-container container ">
			<div class="row-fluid">
				<c:set var="ba_index" value="501" scope="request" />
				<c:set var="menu" value="#menuBg" scope="request" />
				<%@ include file="/common/leftmenu.jsp"%>
				<div class="page-content overview">
				<div class="pg_common-title title">政府网站云监管平台大数据分析 <span>${scanDate }数据</span><i class="full_screen"  onclick="fullScreen()" title="全屏显示"></i></div>
            <div class="first_part clearfix">
                <div class="left_part fl">
                    <div class="fl left">
			        	<!-- <div class="row"> -->
			                	<div class="z_box_header1" >
			                        <div class="chart-body1">
			                            <canvas width="180" height="180" id="chart-gauge" style="position:relative; left:50%; margin-left:-90px; margin-top:10px;"></canvas>
			                            <div class="chart-info1">
			                                <h3 class="zhi_s"><span id="centerId"></span>政府网站</br>健康指数</h3>
			                                <!--/z_box_header-->
			                                <div class="num1" id="health_index" style="text-align:center; font:bold 22px/56px 'Microsoft YaHei','微软雅黑';"></div>
			                                <div class="num1_msg" style="font:normal 19px 'Microsoft YaHei','微软雅黑';"></div>
			                            </div>
			                        </div>
                            </div>
                        <!-- </div> -->
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
                <div class="right_part fr" style="cursor:pointer" onclick="toHomeJsp()">
                    <%-- <div class="top clearfix">
                        <div class="show_data_part fl">
                            <h4>监测站点数量</h4>
                            <p class="green" id="count1"></p>
                        </div>
                        <div class="show_img_part fr">
                            <h4 class="" style="color:#323c48;"></h4>
                            <img class="" src="<%=path%>/images/zanyong/green_lines.png" alt=""/>
                        </div>
                    </div> --%>
                    <%-- <div class="bottom clearfix">
                        <div class="show_data_part fl">
                            <h4 class="" style="">监测页面数量</h4>
                            <p class=blue id="Pages"></p>
                        </div>
                        <div class="show_img_part fr">
                            <h4 class="" style="color:#323c48;"></h4>
                            <img class="" src="<%=path%>/images/zanyong/blue_lines.png" alt=""/>
                        </div>
                    </div> --%>
                    <div class="right_part_content">
                    	<h4 class="tit">问题实时播报</h4>
                    	<div class="ProblemReport">
                    		<ul class="PR-content">
                    			<li class="underline head-tit">
                    				<ul class="clearfix"><li class="fl"><!-- <i class="i-block"></i> --></li><li class="fl websiteName">网站名称</li><li class="fl findQues">发现问题</li><li class="fl">时间</li></ul>
                    			</li>
                    		</ul>
                    		<div class="active_report">
	                    		<ul class="PR-content" id="rollDatas">
<!-- 	                    			<li>
	                    				<ul class="clearfix"><li class="fl"><i class="i-block"></i></li><li class="fl websiteName">广东省政府网站</li><li class="fl findQues">首页更新不及时</li><li class="fl">2016-9-26 15:59:03</li></ul>
	                    			</li>
	                    			<li>
	                    				<ul class="clearfix"><li class="fl"><i class="i-block"></i></li><li class="fl websiteName">广东省政府网站</li><li class="fl findQues">首页更新不及时</li><li class="fl">2016-9-26 15:59:03</li></ul>
	                    			</li>
	                    			<li>
	                    				<ul class="clearfix"><li class="fl"><i class="i-block"></i></li><li class="fl websiteName">广东省政府网站</li><li class="fl findQues">首页更新不及时</li><li class="fl">2016-9-26 15:59:03</li></ul>
	                    			</li>
	                    			<li>
	                    				<ul class="clearfix"><li class="fl"><i class="i-block"></i></li><li class="fl websiteName">广东省政府网站</li><li class="fl findQues">首页更新不及时</li><li class="fl">2016-9-26 15:59:03</li></ul>
	                    			</li>
	                    			<li>
	                    				<ul class="clearfix"><li class="fl"><i class="i-block"></i></li><li class="fl websiteName">广东省政府网站</li><li class="fl findQues">首页更新不及时</li><li class="fl">2016-9-26 15:59:03</li></ul>
	                    			</li>
	                    			<li>
	                    				<ul class="clearfix"><li class="fl"><i class="i-block"></i></li><li class="fl websiteName">广东省政府网站</li><li class="fl findQues">首页更新不及时</li><li class="fl">2016-9-26 15:59:03</li></ul>
	                    			</li> -->
	                    		</ul>
                    		</div>
                    	</div>
                    </div>
                </div>
            </div>
            <div class="second_part clearfix">
                <div class="left_part fl" style="position:relative;">
                	<!-- <div style="position:absolute; padding-left:20px; z-index:100; color:#fff;">
                		<h4 style="font: normal 16px 'Microsoft YaHei', '微软雅黑';">全国政府网站连通率状态图 </h4>
                	</div> -->
                    <!-- <img src="../../images/zanyong/show_4.jpg" alt=""/> -->
                    <div id="map-wrap" style="height:90%;width:85%;background-attachment:fixed; position:relative; margin-left:10%;margin-top:2%;margin-bottom: 2%;">
                    
                    </div>
                </div>
                <div class="right_part fr">
                    <%-- <div class="top clearfix" style="cursor:pointer" onclick="toHomeJsp()">
                        <div class="show_data_part fl">
                            <h4>发现问题数量</h4>
                            <p class="red" id="Ques"></p>
                        </div>
                        <div class="show_img_part fr">
                            <h4 class="" style="color:#323c48;">qqqq</h4>
                            <img class="" src="<%=path%>/images/zanyong/white_lines.png" alt=""/>
                        </div>
                    </div> --%>
                  <!--   <div class="bottom">
                        <div class="clearfix part1">
                            <div class="fl website_total">
                                <h4>全国政府网站总数</h4>
                                <p><span id="count2"></span></p>
                            </div>
                            <div class="fl website_p">
                               <div class="every_progress">
                                   <h5>国务院部门网站 <span id="bmCount"></span></h5>
                                   <div>
                                       <span id="line_one"  class="line_one"></span>
                                   </div>
                               </div>
                                <div class="every_progress">
                                    <h5>部门垂直管理单位网站 <span id="chui"></span></h5>
                                    <div>
                                        <span id="line_two" class="line_two"></span>
                                    </div>
                                </div>
                                <div class="every_progress">
                                    <h5>省级政府网站 <span id="sheng"></span></h5>
                                    <div>
                                        <span id="line_three" class="line_three"></span>
                                    </div>
                                </div>
                                <div class="every_progress">
                                    <h5>市级（含省直管局）政府网站 <span id="shi"></span></h5>
                                    <div>
                                        <span id="line_four" class="line_four"></span>
                                    </div>
                                </div>
                                <div class="every_progress">
                                    <h5>县级（含乡镇街道）政府网站 <span id="xian"></span></h5>
                                    <div>
                                        <span id="line_five" class="line_five"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="single_line"></div>
                        <div class="clearfix">
                            <div class="roud_img fl" >
                                <img src="../../images/zanyong/show_9.jpg" alt=""/>
                               <div id="fenbu" style="width:170px;height:170px; left:50%; margin-left:-85px;"></div>
                            </div>
                            <div class="table_box fl">
                                <table>
                                    <tr>
                                        <th class="w10p"></th>
                                        <th>状态</th>
                                        <th>数量</th>
                                        <th>比例</th>
                                    </tr>
                                    <tr>
                                        <td class="w10p">
                                            <i class="yuan_1"></i>
                                        </td>
                                        <td>正常</td>
                                        <td><span id="zhengChang"></span></td>
                                        <td><span id="zhengChangPer"></span></td>
                                    </tr>
                                    <tr>
                                        <td class="w10p">
                                            <i class="yuan_2"></i>
                                        </td>
                                        <td>例外</td>
                                        <td><span id="liWai"></span></td>
                                        <td><span id="liWaiPer"></span></td>
                                    </tr>
                                    <tr>
                                        <td class="w10p">
                                            <i class="yuan_3"></i>
                                        </td>
                                        <td>关停</td>
                                        <td><span id="guanTing"></span></td>
                                        <td><span id="guanTingPer"></td>
                                    </tr>
                                </table>
                            </div>
                            <img src="../../images/zanyong/show_7.jpg" alt=""/>
                        </div>
                    </div> -->
                    <div class="tops">
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
	                       <!--  </div> -->
                    	</div>
                    	<div class="top_s">
                    		<div class="show_data_part fl">
	                            <h4 class="" style="">全国监测页面数量</h4>
	                           <p class=blue id="Pages" ></p> 
                        	</div>
	                        <div class="show_img_part fr">
	                            <h4 class="" style="color:#0f171c;"></h4>
	                            <img class="" src="<%=path%>/images/zanyong/blue_lines.png" alt=""/>
	                        </div>
                    	</div>
                    	<div class="top_t">
                    		<div class="show_data_part fl">
	                            <h4>全国发现问题数量</h4>
	                            <p class="red" id="Ques"></p>
                        	</div>
	                        <div class="show_img_part fr">
	                            <h4 class="" style="color:#0f171c;">qqqq</h4>
	                            <img class="" src="<%=path%>/images/zanyong/white_lines.png" alt=""/>
	                        </div>
                    	</div>
                    </div>
                    <div class="bottom" id="visitData"></div>
                </div>
            </div>
            
            <!-- <div class="third_part clearfix" >
                <div class="fl">
                    <div class="tit">首页连通率  ( 一周 )</div>
                    <div class="content"   onclick="toBigDateJsp();">
                        <img src="../../images/zanyong/show_8.jpg" alt=""/>
                         <h4 class="colo-5e6a78" id="linkErrSiteProp_h" style="cursor:pointer">
                         	
                         	<div style="position:relative; width:100%; line-height:20px;">
                         		<div style="position:absolute; top:0; left:0; opacity:0.6; background:#404a59; z-index:5;"></div>
                         		<p style="position:absolute; top:0;left:0; color:#fff; z-index:5;"></p>
                         	</div>
                         	<span>%</span>
                         </h4>
                         <div class="pro">
                         	<span id="linkErrSiteProp_span"></span>
                         </div>
                    </div>
                </div>
                <div class="fl">
                    <div class="tit">首页正常更新率  ( 两周 )</div>
                    <div class="content" style="cursor:pointer"  onclick="toBigDateJsp();">
                        <img src="../../images/zanyong/show_8.jpg" alt=""/>
                         <h4 class="colo-5e6a78" id="noUpdateSiteProp_h">
                         	<span>%</span>
                         </h4>
                          <div class="pro">
                         	<span id="noUpdateSiteProp_span"></span>
                         </div>
                    </div>
                </div>
                <div class="fl">
                    <div class="tit">首页不可用链接数(2016/10/07)  </div>
                    <div class="content" style="cursor:pointer" onclick="toBigDateJsp();">
                        <h4 class="colo-5e6a78" id="indexdeadnum">
                        	
                        </h4>
                         <i class="first"></i>
                    </div>
                </div>
                <div class="fl" style="width:calc(20% - 10px);">
                    <div class="tit">内容更新总量 ( 2016-10-07 )  </div>
                    <div class="content" style="cursor:pointer" onclick="toBigDateJsp();">
                        <h4 class="colo-5e6a78" id="aveupdatenum">
                        5487<span>个</span>
                        	
                        </h4>
                         <i class="second" style="height:46px;"></i>
                    </div>
                </div>
                <div class="fr" style="margin-right:0; width:calc(20% - 10px);">
                    <div class="tit">首页更新总量 ( 2016-10-07 ) </div>
                    <div class="content" style="cursor:pointer" onclick="toBigDateJsp();">
                        <h4 class="colo-5e6a78" id="updatenum">
                        245789<span>个</span>
                        	
                        </h4>
                         <i class="third" style="height:46px;"></i>
                    </div>
                </div>
            </div> -->
             <div class="third_part clearfix" >
             	<!-- <div class="left"> -->
	                <div class="fl">
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
	                <div class="fl">
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
	                <div class="fl">
	                    <div class="tit">首页不可用链接数( 昨天 )  </div>
	                    <div class="content" style="cursor:pointer" onclick="toBigDateJsp();">
	                        <h4 class="colo-5e6a78" id="indexdeadnum" data-container="body" data-toggle="popover" data-placement="top" data-content="首页不可用链接的个数">
	                        </h4>
	                        <i class="first"></i>
	                    </div>
	                </div>
               <!--  </div> -->
                <div class="fl" style="width:calc(20% - 14px);">
                    <div class="tit">内容更新总量 ( 昨天 )  </div>
                    <div class="content" style="cursor:pointer" onclick="toBigDateJsp();">
                        <h4 class="colo-5e6a78" id="aveupdatenum" data-container="body" data-toggle="popover" data-placement="top" data-content="网站总更新量">
                            <span>个</span>
                        </h4>
                        <i class="second" style="height:46px;"></i>
                    </div>
                </div>
                <div class="fl" style="margin-right:0; width:calc(20% - 14px);">
                    <div class="tit">首页更新总量 ( 昨天 ) </div>
                    <div class="content" style="cursor:pointer" onclick="toBigDateJsp();">
                        <h4 class="colo-5e6a78" id="updatenum" data-container="body" data-toggle="popover" data-placement="top" data-content="首页总更新量">
                            <span>个</span>
                        </h4>
                        <i class="third" style="height:46px;"></i>
                    </div>
                </div>
            </div>
            <!--需要填充的内容结束-->
					<div style="padding-left:30px">
						<%@ include file="/common/footer.jsp"%>
					</div>
					<!--表格部分结束-->
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
			var menuType ="";
	//隐藏广告
	 $(window).load(function(){
// 	$("#advertDiv").show();
	var adDiv=$("#advertDiv").is(":hidden");//true 隐藏
	if(adDiv){
	//隐藏
		$("#page_tit").css("top","59px");
		//$("#sidebar").css("top","114px");
		//$(".page-content").css("marginTop","114px");
		
	}else{
		var img = $("#_span_ad").html();
		if(img.indexOf("img")!=-1){
			$("#page_tit").css("top","120px");
			$("#sidebar").css("top","174px");
			$(".page-content").css("marginTop","174px");
		}else{
			$("#advertDiv").hide();
			$("#page_tit").css("top","59px");
			//$("#sidebar").css("top","114px");
			//$(".page-content").css("marginTop","114px");
		}
	} 
	
	/*  $('.third_part h4').mouseover(function(){
        $(this).popover('show');
    });

    $('.third_part h4').mouseout(function(){
        $(this).popover('hide');
    });
     */
    $('.second_part .right_part .bottom').css("background","#0f171c");
	
});
	</script>
	<script type="text/javascript" src="<%=path%>/js/echarts.min.js"></script> 
	<%-- <script type="text/javascript" src="<%=path%>/js/bigDataAnalysis/bigData.min.js"></script> --%>
	<script type="text/javascript" src="<%=path%>/js/common/bigDataCommon.js"></script>
	<script language="javascript"  src="<%=path%>/js/gauge.js"></script> 
	<script language="javascript"  src="<%=path%>/js/jquery.liMarquee.js"></script>
	
 	<script language="javascript"  src="<%=path%>/js/map/jsCitys/citysData/geoData.js"></script>
<%--	
	<script language="javascript"  src="<%=path%>/js/map/jsCitys/jsCitys/${sessionScope.shiroUser.siteCode}.js"></script>
 --%>
	
	<%--  <script language="javascript"  src="<%=path%>/js/siteDataOverview/siteDataOverviewChart.js"></script>  --%>
	 <script language="javascript"  src="<%=path%>/js/siteDataOverview/siteDataOverviewChart_new.js"></script> 
	
</body>
</html>
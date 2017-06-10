 <%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div id="sidebar" class="page-sidebar">
		
	<%--填报单位左侧菜单 --%>
	<div class="zTreeDemoBackground left">
		<div class="menutop-line"></div>
		<ul id="tianbMenu" class="left-menu panel-group">
			<li class="level0 accordion-group">
				<c:if test="${fn:length(sessionScope.shiroUser.siteCode) == 6 }">
					<a class="level0 cur-tit <c:if test="${tb_index==1}">cur-selected-bg</c:if> " href="<%=path %>/fillUnit_gailan.action?siteCode=${sessionScope.shiroUser.childSiteCode}"> 
						<span class="button"></span> 
						<span>日常监测概览</span>
					</a>
				</c:if>
				<c:if test="${fn:length(sessionScope.shiroUser.siteCode) == 10 }">
					<a class="level0 cur-tit <c:if test="${tb_index==1}">cur-selected-bg</c:if> " href="<%=path %>/fillUnit_gailan.action?siteCode=${sessionScope.shiroUser.siteCode}"> 
						<span class="button"></span> 
						<span>日常监测概览</span>
					</a>
				</c:if>
			</li>
			 <c:if test='${sessionScope.shiroUser.siteCode=="3700000071" }'>
	        <li class="level0 accordion-group">
				<a class="level0 cur-tit <c:if test="${tb_index==22}">cur-selected-bg</c:if>"  href="<%=path %>/securityStatus_toSecurityStatus.action?type=1"> 
					<span class="button"></span> 
					<span>自定义扫描</span> 
				</a>
			</li>
			</c:if> 
			
			<li class="level0 accordion-group">
				<a class="level0 cur-tit <c:if test="${tb_index==2}">cur-selected-bg</c:if>"  href="javascript:void(0);"> 
					<span class="button"></span> 
					<span>网站连通性</span> 
					<span class="tree_icon"></span> 
				</a>
				<ul id="menuWzltx" class="level0">
					<li class="left-menu-line"></li>
					<li class="level1 level1-li <c:if test="${tb_index==2}">level1-li-selected</c:if>">
						<a class="level1" href="<%=path %>/connectionHomeDetail_index.action">
							<span class="button ico_docu"></span> 
							<span>首页连通性</span> 
						</a>
					</li>
					<li class="level1 level1-li <c:if test="${tb_index==3}">level1-li-selected</c:if>">
						<a class="level1" href="<%=path %>/connectionBusinessDetail_index.action">
							<span class="button ico_docu"></span> 
							<span>业务系统连通性</span>
							<c:if test="${sessionScope.shiroUser.iscost==0}">
								<i class="lock"></i>
							</c:if>
						</a>
					</li>
					<li class="level1 level1-li <c:if test="${tb_index==4}">level1-li-selected</c:if>">
						<a class="level1" href="<%=path %>/connectionChannelDetail_index.action">
							<span class="button ico_docu"></span> 
							<span>关键栏目连通性</span>
							<c:if test="${sessionScope.shiroUser.iscost==0}">
								<i class="lock"></i>
							</c:if>
						</a>
					</li>
					<li class="level1 level1-li <c:if test="${tb_index==3}">level1-li-selected</c:if>">
						<a class="level1" href="<%=path %>/connectionBusinessDetail_connectivity.action">
							<span class="button ico_docu"></span> 
							<span>栏目监测</span>
							<c:if test="${sessionScope.shiroUser.iscost==0}">
								<i class="lock"></i>
							</c:if>
						</a>
					</li>
				</ul>
			</li>
			<li class="level0 accordion-group">
				<a class="level0 cur-tit <c:if test="${tb_index==5}">cur-selected-bg</c:if>"  href="javascript:void(0);"> 
					<span style="" class="button"></span> 
					<span>链接可用性</span> 
					<span class="tree_icon"></span> 
				</a>
				<ul id="menuLjkyx" class="level0">
					<li class="left-menu-line"></li>
					<li class="level1 level1-li <c:if test="${tb_index==5}">level1-li-selected</c:if>">
						<a class="level1" href="<%=path %>/linkHome_linkHomeIndex.action">
							<span class="button ico_docu"></span> 
							<span>首页链接可用性</span> 
						</a>
					</li>
					<li class="level1 level1-li <c:if test="${tb_index==6}">level1-li-selected</c:if>">
						<a class="level1" href="<%=path %>/linkAll_linkAllDetailIndex.action">
							<span class="button ico_docu"></span> 
							<span>全站链接可用性</span>
							<c:if test="${sessionScope.shiroUser.iscost==0}">
								<i class="lock"></i>
							</c:if>
						</a>
					</li>
				</ul>
			</li>
			<li class="level0 accordion-group">
				<a class="level0 cur-tit <c:if test="${tb_index==7}">cur-selected-bg</c:if>"  href="javascript:void(0);"> 
					<span style="" class="button"></span> 
					<span>内容保障问题</span> 
					<span class="tree_icon"></span> 
				</a>
				<ul id="menuBzwt" class="level0">
					<li class="left-menu-line"></li>
					<li class="level1 level1-li <c:if test="${tb_index==7}">level1-li-selected</c:if>">
						<a class="level1" href="<%=path %>/homeInfoUnUpdate_index.action"> 
							<span class="button ico_docu"></span> 
							<span>首页不更新</span> 
						</a>
					</li>
					<li class="level1 level1-li <c:if test="${tb_index==8}">level1-li-selected</c:if>">
						<a class="level1" href="<%=path %>/upChannel_upChannelIndex.action">
							<span class="button ico_docu"></span> 
							<span>基本信息 </span>
							<c:if test="${sessionScope.shiroUser.iscost==0}">
								<i class="lock"></i>
							</c:if>
						</a>
					</li>
					<li class="level1 level1-li <c:if test="${tb_index==9}">level1-li-selected</c:if>">
						<a class="level1" href="<%=path %>/securityBlankDetail_index.action"> 
							<span class="button ico_docu"></span> 
							<span>空白栏目</span> 
							<c:if test="${sessionScope.shiroUser.iscost==0}">
								<i class="lock"></i>
							</c:if>
						</a>
					</li>
					<li class="level1 level1-li <c:if test="${tb_index==10}">level1-li-selected</c:if>">
						<a class="level1" href="<%=path %>/securityResponse_index.action">
							<span class="button ico_docu"></span> 
							<span>互动回应差</span> 
							<c:if test="${sessionScope.shiroUser.iscost==0}">
								<i class="lock"></i>
							</c:if>
						</a>
					</li>
					<li class="level1 level1-li <c:if test="${tb_index==11}">level1-li-selected</c:if>">
						<a class="level1" href="<%=path %>/securityServcie_index.action">
							<span class="button ico_docu"></span> 
							<span>服务不实用</span> 
							<c:if test="${sessionScope.shiroUser.iscost==0}">
								<i class="lock"></i>
							</c:if>
						</a>
					</li>
<!-- 					<li class="level1 level1-li <c:if test="${tb_index==19}">level1-li-selected</c:if>"> -->
<!-- 						<a class="level1" href="<%=path %>/securityBasicInfo_index.action"> -->
<!-- 							<span class="button ico_docu"></span>  -->
<!-- 							<span>基本信息</span>  -->
<!-- 							<c:if test="${sessionScope.shiroUser.iscost==0}"> -->
<!-- 								<i class="lock"></i> -->
<!-- 							</c:if> -->
<!-- 						</a> -->
<!-- 					</li> -->
				</ul>
			</li>
			<li class="level0 accordion-group">
				<a class="level0 cur-tit <c:if test="${tb_index==12}">cur-selected-bg</c:if>"  href="<%=path %>/correctContent_index.action"> 
					<span class="button"></span> 
					<span>错别字</span> 
				</a>
			</li>
			<li class="level0 accordion-group">
				<a class="level0 cur-tit <c:if test="${tb_index==32}">cur-selected-bg</c:if>"  href="<%=path %>/jcVisitSitecode_index.action"> 
					<span class="button"></span> 
					<span>网站访问量</span> 
				</a>
			</li>
<%-- 			<c:if test="${sessionScope.shiroUser.siteCode == '3700000071' }">
			<li class="level0 accordion-group">
				<a class="level0 cur-tit <c:if test="${tb_index==21}">cur-selected-bg</c:if>"  href="<%=path %>/securityQuestion_index.action"> 
					<span class="button"></span> 
					<span>安全问题</span> 
				</a>
			</li>
			</c:if> --%>
			<li class="level0 accordion-group" style="display:none;">
				<a class="level0 cur-tit <c:if test="${tb_index==13}">cur-selected-bg</c:if>"  href="javascript:void(0);"> 
					<span style="" class="button"></span> 
					<span>网站安全</span> 
					<span class="tree_icon"></span>
				</a>
				<ul id="menuWzaq" class="level0">
					<li class="left-menu-line"></li>
					<li class="level1 level1-li <c:if test="${tb_index==13}">level1-li-selected</c:if>">
						<a class="level1">
							<span class="button ico_docu"></span> 
							<span>分类分析</span> 
						</a>
					</li>
					<li style="display:none;" class="level1 level1-li <c:if test="${tb_index==14}">level1-li-selected</c:if>">
						<a class="level1">
							<span class="button ico_docu"></span> 
							<span>热点分析</span> 
						</a>
					</li>
				</ul>
			</li>
			
			<li class="level0 accordion-group">
				<a class="level0 cur-tit <c:if test="${tb_index==15}">cur-selected-bg</c:if>"  href="javascript:void(0);"> 
					<span style="" class="button"></span> 
					<span>更新统计</span>
					<span class="tree_icon"></span> 
				</a>
				<ul id="menuNrgx" class="level0">
					<li class="left-menu-line"></li>
					<li class="level1 level1-li <c:if test="${tb_index==15}">level1-li-selected</c:if>">
						<a class="level1" href="<%=path %>/updateHome_updateHomeIndex.action"> 
						<span class="button ico_docu"></span> 
						<span>首页更新</span> 
						</a>
					</li>
					<li class="level1 level1-li <c:if test="${tb_index==16}">level1-li-selected</c:if>">
						<a class="level1" href="<%=path %>/updateChannelDetail_index.action"> 
							<span class="button ico_docu"></span> 
							<span>栏目更新</span> 
							<c:if test="${sessionScope.shiroUser.iscost==0}">
								<i class="lock"></i>
							</c:if>
						</a>
					</li>
					<li class="level1 level1-li <c:if test="${tb_index==17}">level1-li-selected</c:if>" style="display:none;">
						<a class="level1"  href="<%=path %>/updateContentAnalyze_index.action"> 
							<span class="button ico_docu"></span> 
							<span>聚类分析</span>
							<c:if test="${sessionScope.shiroUser.iscost==0}">
								<i class="lock"></i>
							</c:if>
						</a>
					</li>
				</ul>
			</li>
			
			<li class="level0 accordion-group" id="monitorId" style="display:none;">
				<a class="level0 cur-tit <c:if test="${tb_index==27}">cur-selected-bg</c:if>"  href="<%=path %>/monitorSilentResult_indexTB.action"> 
					<span class="button"></span> 
					<span>安全监测</span> 
				</a>
			</li>
			
			<li class="level0 accordion-group" >
					<a id="btn_webinfo"  class="level0 <c:if test="${ba_index==17}">cur-selected-bg</c:if> cur-tit"  href="javascript:void(0);"> 
						<span style="" class="button"></span> 
						<span>站点信息管理</span> 
						<span class="tree_icon"></span> 
					</a>
					<ul id="menuFx1" class="level0">
						<li class="left-menu-line"></li>
						<li id="webinfo_li1" class="level1 level1-li level1-li-selected">
							<aclass="level1" _href="#khxx" href="javascript:void(0);"> 
								<span class="button ico_docu"></span> 
								<span>${databaseInfo.name}</span> 
							</a>
						</li>
						<li class="level1" style="display: none;">
								<a class="level1" _href="#gxpz" href="javascript:void(0);"> 
									<span class="button ico_docu"></span> 
									<span>个性配置</span> 
								</a>
						</li>
					</ul>
			</li>

			<%-- <li class="level0 accordion-group" id="baoguao_tianbao" >
				<a class="view-modal level0 cur-tit <c:if test="${tb_index==18}">cur-selected-bg</c:if>" 
					data-toggle="modal" href="#preview" onclick="getPdfUrl('<%=path %>${sessionScope.shiroUser.pdfUrl }','${sessionScope.shiroUser.siteName }')">
					<span class="button"></span> 
					<span>监测报告</span> 
				</a>
			</li> --%>
			<li class="level0">
				<c:if test="${sessionScope.shiroUser.canSeeWord==1}">
					<a class="level0 <c:if test="${tb_index==20}">cur-selected-bg</c:if> cur-tit" data-toggle="modal" href="<%= path %>/wordList_index.action"><span class="button"></span><span>上级抽检</span></a>
				</c:if>
			</li>
			<c:if test='${fn:length(sessionScope.shiroUser.siteCode) > 6}'>
        	<li class="level0" >
				<a class="level0 <c:if test="${tb_index==10}">cur-selected-bg</c:if> cur-tit" href="<%=path %>/servicePeriod_servicePeriod.action">
					<span class="button"></span>
					<span>全面监测</span> 
				</a>
			</li>
			</c:if>
			
			<c:if test="${sessionScope.shiroUser.paTargetCount !=0 && sessionScope.shiroUser.orgToInfo !=1}">
			<li class="level0 accordion-group">
				<a class="level0 cur-tit <c:if test="${tb_index==28}">cur-selected-bg</c:if>"  href="javascript:void(0);"> 
					<span style="" class="button"></span> 
					<span>网站绩效考评</span> 
					<span class="tree_icon"></span>
				</a>
				<ul id="menuInfoJi"  class="level0">
					<li class="left-menu-line"></li>
					<li class="level1 level1-li <c:if test="${tb_index==28}">level1-li-selected</c:if>">
						<a class="level1" href="<%=path %>/paTask_paTaskInfo.action">
							<span class="button ico_docu"></span> 
							<span>考评任务</span> 
						</a>
					</li>
					<li  class="level1 level1-li <c:if test="${tb_index==21}">level1-li-selected</c:if>">
						<a class="level1" href="<%=path %>/paAdvice_paAdvice.action">
							<span class="button ico_docu"></span> 
							<span>意见建议</span> 
						</a>
					</li>
				</ul>
			</li>
			</c:if> 
			<c:if test="${fn:length(sessionScope.shiroUser.siteCode) == 10 }">
			 <li class="level0 accordion-group">
				<a class="level0 cur-tit <c:if test="${tb_index==29}">cur-selected-bg</c:if>"  href="<%=path %>/notice_indexReceiver.action"> 
					<span class="button"></span> 
					<span>通知与反馈</span> 
				</a>
			</li> 
			</c:if>
			<li class="level0 accordion-group">
				<a class="level0 cur-tit <c:if test="${tb_index==30}">cur-selected-bg</c:if>"  href="<%=path %>/monitorInclude_index.action"> 
					<span class="button"></span> 
					<span>搜索引擎收录</span> 
				</a>
			</li>
			<c:if test='${(fn:length(requestScope.siteCode))!=6 && sessionScope.shiroUser.orgToInfo != 1}'>
			<li class="level0 accordion-group">
				<a class="level0 cur-tit <c:if test="${tb_index==31}">cur-selected-bg</c:if>"
				href="<%=path %>/tempReport_tempReportTB.action"> 
				<span class="button"></span> 
				<span>网站临时报备</span>
			</a></li>
			</c:if>
		</ul>
		<%-- <div class="yun-search_sy" style="width:200px; height:100px; margin:15px auto; cursor:pointer;">
			<img alt="" src="<%=path %>/images/common/search_ad_sy.jpg" onclick="queryDatabaseInfo();">
		</div> --%>
	</div>

</div>
<!-- Modal -->
<div id="preview" class="page-dialog modal hide" tabindex="-1" role="dialog" aria-hidden="true" style="display:none;">
	<div class="modal-header">
      <button aria-hidden="true" data-dismiss="modal" class="close" type="button"></button>
      <h3 id="myModalLabel3"><i class="view"></i>报告预览</h3>
    </div>
    <div class="modal-body">
    	<div id="flexsliderMore3" class="flexslider-more carousel">
          <ul class="slides">
            <li class="active"><div class="font-size1">第1期</div>2014年5月1日</li>
            <li><div class="font-size1">第2期</div>2014年5月1日</li>
            <li><div class="font-size1">第3期</div>2014年5月1日</li>
            <li><div class="font-size1">第4期</div>2014年5月1日</li>
            <li><div class="font-size1">第5期</div>2014年5月1日</li>
            <li><div class="font-size1">第6期</div>2014年5月1日</li>
            <li><div class="font-size1">第7期</div>2014年5月1日</li>
            <li><div class="font-size1">第8期</div>2014年5月1日</li>
            <li><div class="font-size1">第9期</div>2014年5月1日</li>
            <li><div class="font-size1">第10期</div>2014年5月1日</li>
            <li><div class="font-size1">第11期</div>2014年5月1日</li>
          </ul>
        </div>
        
        <div class="modal-container">
          <div class="modal-con-header row">
            
            <div class="page-green-btn1"><i class="dc"></i>导出报告</div>
            <div class="page-blue-btn1"><i></i>邮件督办</div>
            <div class="zt-font pull-right"><i></i>成功</div>
            <h4 class="pull-right">发送状态：</h4>
          </div><!-- /modal-con-header -->
          <div class="modal-con-row row">
            <div class="modal-con-menu">
                <ul class="nav nav-tabs">
                  <li class="active"><a data-toggle="tab" href="#bgContent">报告</a></li>
    <!--<li><a data-toggle="tab" href="#flContent">附录</a></li>-->
                </ul>
              
            </div><!--/Sidebar content-->
            <div class="modal-con">
            	<div class="tab-content">
                	<div id="bgContent" class="tab-pane active" style="height: 1000px;">
                	 <%@ include file="/jsp/reportManageLog/pdfIndex.jsp"%>
                	</div>
                    <div id="flContent" class="tab-pane">附录内容</div>
                </div>
            </div><!--/Body content-->
          </div>
        </div>
                
    </div>
</div>
<!--周期动态效果控件js引入  -->
<script language="javascript" type="text/javascript" src="<%= path %>/js/flexslider/jquery.flexslider-min.js"></script> 
<script language="javascript" type="text/javascript" src="<%= path %>/js/flexslider/jquery.flexslider.js"></script>
<!--周期动态效果控件css引入  -->
<link rel="stylesheet" media="screen"  href="<%= path %>/css/flexslider.css"/>
<script type="text/javascript">
	var siteCodeLogin='${sessionScope.shiroUser.siteCode}';
// 	if(siteCodeLogin == '3700000071' || siteCodeLogin=='N000000546' || siteCodeLogin=='N000000547' || siteCodeLogin=='N000000548' ){
		$("#monitorId").show();
// 	}
var showId='<c:out value="${menu}"/>';
$(showId).show();
$(showId).siblings(".cur-tit").addClass("cur-selected-bg cur");

$(".cur-tit").on('click', function () {
	if($(this).hasClass("cur-selected-bg")){
		if($(this).hasClass("cur")){
			$(this).removeClass("cur");
			$(this).siblings("ul").slideUp();
		}else{
			$(this).siblings("ul").slideDown();
			$(this).addClass("cur");
		}
	}else{
		try {
			var id = this.id;
			// 打开站点信息管理页面
			if(id!=null && id == 'btn_webinfo'){
				location.href = webPath+'/manageInfo_indexTB.action';
			}
		} catch (e) {}
		$('.accordion-group > ul').slideUp();
		$(this).siblings("ul").slideDown();
		$(".cur-tit").removeClass("cur-selected-bg cur");
		$(this).addClass("cur-selected-bg cur");
		$(".level1-li").removeClass("level1-li-selected");
	}
	
})
$(function(){
 	$("#sidebar").mCustomScrollbar({
		theme:"dark",
		autoDraggerLength:true,
		advanced: {
			updateOnContentResize: true,
			updateOnBrowserResize: true,
			autoHideScrollbar: true
		}
	}); 
	$(".level1-li").on('click', function () {
		$(".level1-li").removeClass("level1-li-selected");
		$(this).addClass("level1-li-selected");
	});	
	$("#baoguao_tianbao").on("click",".view-modal",function(){
		$("#flexsliderMore3").flexslider({
			slideshowSpeed: 50000, // 自动播放速度毫秒
			animation: "slide",
			controlNav: false, //Boolean:  usage是否显示控制菜单//什么是控制菜单？
			itemWidth: 133,
			itemMargin: 1,
			minItems: 2,
			maxItems: 8
		   // pausePlay: true
		});
	});
});
	/**展示pdf*/
function getPdfUrl(path,name){
	if(path.length<=10){
		path = webPath+"/pdf/demo.pdf";
	}
	$("#myModalLabel3").html('<i class="view"></i>'+name+'报告预览');
	webViewerInitialized(path);
	document.addEventListener('DOMContentLoaded', webViewerLoad, true);
}
</script>

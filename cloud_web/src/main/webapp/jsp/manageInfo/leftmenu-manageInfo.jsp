<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<div id="sidebar" class="page-sidebar">
	
	<%--组织机构(省)左侧菜单 --%>
	<div class="zTreeDemoBackground left">
		<div class="menutop-line"></div>
		<ul id="menuAccordion" class="left-menu panel-group">
            <li class="level0 accordion-group">
            <c:if test='${(fn:length(requestScope.siteCode))!=6}'>
				<a class="level0 cur-tit <c:if test="${tb_index==17}">cur-selected-bg</c:if>" href="<%=path%>/manageInfo_indexTB.action?top=8"> 
                    <span class="button pIcon05_ico"></span> 
                    <span>单位基本信息</span>
                   <!--  <span class="tree_icon"></span>  -->
                </a>
             </c:if>
             <c:if test='${(fn:length(requestScope.siteCode))==6}'>
				<a class="level0 cur-tit <c:if test="${ba_index==18}">cur-selected-bg</c:if>" href="<%=path%>/manageInfo_index.action?top=8"> 
                    <span class="button pIcon05_ico"></span> 
                    <span>单位基本信息</span>
                   <!--  <span class="tree_icon"></span>  -->
                </a>
             </c:if>
            <c:if test='${(fn:length(sessionScope.shiroUser.siteCode))==6}'>
				<a class="level0 cur-tit  <c:if test="${ba_index==1}">cur-selected-bg</c:if>"  href="<%=path%>/spSite_getSpSite.action"> 
                    <span class="button pIcon17_ico"></span> 
                    <span>专属页面配置</span>
                   <!--  <span class="tree_icon"></span>  -->
                </a>
              
             </c:if>
       
<%--                 <ul id="menuFx1" class="level0">
                    <li class="left-menu-line"></li>
                    <li class="level1 level1-li level1-li-selected">
                        <a class="level1" _href="#khxx" href="javascript:void(0);" onclick="basicInfo();"> 
                            <span class="button ico_docu"></span> 
                            <span>本站信息</span>
                        </a>
                    </li>
                    
                    <c:if test="${sessionScope.shiroUser.currentCount ne 0}">
                     <li class="level1 level1-li">
                        <a class="level1" _href="#ddxx" href="javascript:void(0);" onclick="getLevelMesg(1);"> 
                            <span class="button ico_docu"></span> 
                            <span>本级部门(${sessionScope.shiroUser.currentCount}个)</span>
                        </a>
                    </li>
                    </c:if>
                    
                    
                    <c:if test="${sessionScope.shiroUser.nextCount ne 0}">
                    <li class="level1 level1-li">
                        <a class="level1" _href="#ddxx" href="javascript:void(0);" onclick="getLevelMesg(2);"> 
                            <span class="button ico_docu"></span> 
                            <span>下属单位(${sessionScope.shiroUser.nextCount }个)</span>
                        </a>
                    </li>
                    </c:if>
                    
                    
                    
                    
                    <c:if test="${sessionScope.shiroUser.exceptionCount !=0 }">
                    	<li class="level1 level1-li">
	                        <a class="level1" _href="#ddxx" href="javascript:void(0);" onclick="getLevelMesg(6);"> 
	                            <span class="button ico_docu"></span> 
	                            <span>例外网站(${sessionScope.shiroUser.exceptionCount }个)</span>
	                        </a>
                   		</li>
					</c:if>
					<c:if test="${sessionScope.shiroUser.closeCount !=0 }">
					
						<li class="level1 level1-li">
	                        <a class="level1" _href="#ddxx" href="javascript:void(0);" onclick="getLevelMesg(7);"> 
	                            <span class="button ico_docu"></span> 
	                            <span>关停网站(${sessionScope.shiroUser.closeCount }个)</span>
	                        </a>
                   		</li>
					</c:if>
					<c:if test="${sessionScope.shiroUser.otherCount !=0 }">
					
						<li class="level1 level1-li">
	                        <a class="level1" _href="#ddxx" href="javascript:void(0);" onclick="getLevelMesg(8);"> 
	                            <span class="button ico_docu"></span> 
	                            <span>其他(${sessionScope.shiroUser.otherCount }个)</span>
	                        </a>
                   		</li>
					
					</c:if> --%>
                    
                    
                    
                    
                    
                    
                    <!-- <li class="level1 level1-li">
                        <a class="level1" _href="#ddxx" href="javascript:void(0);" onclick="getLevelMesg(3);"> 
                            <span class="button ico_docu"></span> 
                            <span>其它单位(N个)</span>
                        </a>
                    </li> -->
                <!-- </ul> -->
            </li>
             <c:if test='${(fn:length(sessionScope.shiroUser.siteCode))!=6 && (sessionScope.shiroUser.iscostOwn == 1)}'>
	        	<li class="level0">
					<a class="level0 cur-tit <c:if test="${tb_index==2}">cur-selected-bg</c:if>" _href="#fwdd" href="<%=path%>/configEarly_configEarlyTB.action"> 
						<span class="button pIcon18_ico"></span> 
						<span>预警设置</span>
					</a>
					<div>
						
					</div>
				</li>
			</c:if>
			<c:if test='${(fn:length(requestScope.siteCode))==6 && (fn:length(sessionScope.shiroUser.siteCode))==6 && (sessionScope.shiroUser.isOrgCost == 1)}'>
	        	<li class="level0">
					<a class="level0 cur-tit <c:if test="${tb_index==2}">cur-selected-bg</c:if>" _href="#fwdd" href="<%=path%>/configEarly_configEarlyOrg.action"> 
						<span class="button pIcon18_ico"></span> 
						<span>预警设置</span>
					</a>
				    
				</li>
			</c:if> 
			
			<%-- <c:if test='${(fn:length(requestScope.siteCode))!=6 && sessionScope.shiroUser.orgToInfo != 1}'>
	        	<li class="level0">
					<a class="level0 cur-tit <c:if test="${tb_index==3}">cur-selected-bg</c:if>" _href="#fwdd" href="<%=path%>/tempReport_tempReportTB.action"> 
						<span class="button pIcon06_ico"></span> 
						<span>网站临时报备</span>
					</a>
				</li>
			</c:if>
			<c:if test='${(fn:length(requestScope.siteCode))==6}'>
	        	<li class="level0">
					<a class="level0 cur-tit <c:if test="${tb_index==3}">cur-selected-bg</c:if>" _href="#fwdd" href="<%=path%>/tempReport_tempReportOrg.action"> 
						<span class="button pIcon06_ico"></span> 
						<span>网站临时报备</span>
					</a>
				</li>
			</c:if>  --%>
		</ul>
	</div>
</div>

<input type ="hidden" value="${sessionScope.shiroUser.siteCode }" id = "guideSiteCode">
<input type="hidden" id="guideNext"  value="${guideNext}">
<!--透明层-->
<c:if test='${(sessionScope.shiroUser.iscost==1) || (sessionScope.shiroUser.isOrgCost == 1)}'>
	 <div id="steps-sec">
	 </div>  
	  <div class="yd-second-steps second-ld" id = "yd-second-steps" style="display:none;">
	     <i class="main"></i> 
	     <i class="nextstep" onclick="nextChange()"></i> 
	     <i class="yjyd-close" onclick="guideClose()"></i> 
	 </div>  
	  <div class="tb-yd-second-steps second-ld" id = "tb-yd-second-steps" style="display:none;">
	     <i class="main"></i> 
	    <i class="nextstep" onclick="nextChange()"></i> 
	     <i class="yjyd-close" onclick="guideClose()"></i> 
	 </div> 
	  <div class="yd-fif-steps fifth-ld" id= "ydfifsteps" style="display:none;"> 
	     <i class="main"></i> 
	     <i class="nextstep" onclick="nextChangeLastLast()"></i> 
	     <i class="yjyd-close" onclick="guideClose()"></i> 
	 </div>  
</c:if>
<script type="text/javascript">
var guiDeState;
$(function(){
$.ajax({
		type : "POST",
		url :webPath+"/databaseInfo_selectGuiteState.action",
		data:{
			siteCode:siteCode
		},
		async : false,
		dataType:"json",
		success : function(data) {
			if(data.guiDeState==1){
			  $("#steps-sec").hide();
	        $("#yd-second-steps").hide();
	        $("#tb-yd-second-steps").hide();
			}
		guiDeState =data.guiDeState;
		}
	});
 if(${(fn:length(requestScope.siteCode))==6 && (fn:length(sessionScope.shiroUser.siteCode))==6 && (sessionScope.shiroUser.isOrgCost == 1)})
		{
			$("#tb-yd-second-steps").hide();
		};
	$(".level1-li").on('click', function () {
		$(".level1-li").removeClass("level1-li-selected");
		$(this).addClass("level1-li-selected");
	});
	
	if(${(fn:length(sessionScope.shiroUser.siteCode))!=6 && (sessionScope.shiroUser.iscostOwn == 1)}){
		if( guiDeState != 1){
			$("#steps-sec").show();
			$("#yd-second-steps").hide();
			$("#tb-yd-second-steps").show();
		}
	}
	else if(${(fn:length(requestScope.siteCode))==6 && (fn:length(sessionScope.shiroUser.siteCode))==6 && (sessionScope.shiroUser.isOrgCost == 1)})
	{
		if( guiDeState != 1 ){
		$("#steps-sec").show();
		$("#yd-second-steps").show();
		$("#ydThirdSteps").hide();
		}
	};
	
});
var siteCode = $("#guideSiteCode").val();
var showId='<c:out value="${menu}"/>';

$(showId).show();
$(showId).siblings(".cur-tit").addClass("cur-selected-bg cur");

$(".cur-tit").on('click', function () {
	if($(this).hasClass("cur-selected-bg")){
		if($(this).hasClass("cur")){
			$(this).removeClass("cur");
			$(this).siblings("ul").slideUp();
		}
		else{
			$(this).siblings("ul").slideDown();
			$(this).addClass("cur");
		}
	}else{
		$('.accordion-group > ul').slideUp();
		$(this).siblings("ul").slideDown();
		$(".cur-tit").removeClass("cur-selected-bg cur");
		$(this).addClass("cur-selected-bg cur");
		$(".level1-li").removeClass("level1-li-selected");
	}
});
//预警引导样式
  window.onload=window.onresize=function() {
   var guideNext = $("#guideNext").val();
        var ostep = document.getElementById('steps-sec');
        oH = document.documentElement.clientHeight || document.body.clientHeight;
        oW = document.documentElement.clientWidth || document.body.clientWidth;
        var ostep_height = oH+59;
        var ostep_width = oW;
        if(ostep != null){
         	ostep.style.height = ostep_height + 'px';
        	ostep.style.width = ostep_width + 'px';
        }
       
        if(guideNext==1){
        	$("#ydfifsteps").show();
        	$("#yd-second-steps").hide();
        	 $("#tb-yd-second-steps").hide();
        }
       if(ostep != null){
	       	if(guiDeState != 1){
			 $('body').css({'width':ostep_width+'px','height':ostep_height+'px','overflow-y':'hidden'});
			}
       } 
	
// 	/隐藏头部预警
	$("#steps").hide(); 
	$("#guideSelection").hide();
    } 
     //预警引导事件
    function guideClose(){
	    $("#steps-sec").hide(); 
	    $("#ydfifsteps").hide();
	    $("#tb-yd-second-steps").hide();
	    $("#steps-thi").hide();
	    $('body').css({'overflow-y':'scroll'});
	 	$.ajax({
				type : "POST",
				url :"<%=path%>/databaseInfo_updateGuiteState.action",
				data:{
					siteCode:siteCode,
				},
				async : false,
				dataType:"json",
				success : function(data) {
				}
					
			});
    }
    function nextChange(){
	    if(${(fn:length(sessionScope.shiroUser.siteCode))!=6 && (sessionScope.shiroUser.iscostOwn == 1)}){
			$("#steps-sec").hide();
			location.href = webPath+'/configEarly_configEarlyTB.action';
		}
		else if(${(fn:length(requestScope.siteCode))==6 && (fn:length(sessionScope.shiroUser.siteCode))==6 && (sessionScope.shiroUser.isOrgCost == 1)})
		{
			$("#steps-sec").hide();
			location.href = webPath+'/configEarly_configEarlyOrg.action';
		};
    }
    function nextChangeLastLast(){
    	 $("#ydfifsteps").hide();
     	$("#ydlaststeps").show();
    }
</script>


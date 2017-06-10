<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>预警</title>
    <%@ include file="/common/meta.jsp"%>
 	<%@ include file="/common/heade.jsp"%>
    <script type="text/javascript" src="<%=path%>/js/early/early_table_fenye.js"></script>
  </head>
  <body style="background:#f5f5f5;">
  <!--头部       satrt  -->
  <c:if test="${fn:length(sessionScope.shiroUser.siteCode) <= 6 }">
	<%@ include file="/common/top.jsp"%>
</c:if>
<c:if test="${fn:length(sessionScope.shiroUser.siteCode) > 6 }">
  <%@ include file="/common/top_tb.jsp"%>
</c:if>
  <!--头部       end  -->
  <input type="hidden" value="${earlyType}" id="earlyType">
<div class="page_tit nav pop-page-tit">
    <h3 class="pull-left">预警</h3>
</div>
<div class="main-container container">
	<div class="row-fluid">
 
        
        <div class="page-content pop-page-content">
        	

            <div class="row">
              <div class="tab-content tab-content-border">
            	<div class="tab_header row tab_header_white">
                	<div class="tab_header_white_con">
                        <h3>${sessionScope.shiroUser.userName}</h3>
                        <!-- 组织单位预警 -->
                          <c:if test="${fn:length(sessionScope.shiroUser.siteCode) <= 6 }">
	                        <c:if test="${sessionScope.shiroUser.isOrgCost == 1}">
	                			<div  class="page-green-btn1" onclick="downExcel();"><i></i>导出列表</div>
	                        	<div  class="page-blue-btn1" onclick="emailSupervision();"><i></i>邮件督办</div>
	            			</c:if>
	            		 </c:if>
	            		 <!-- 填报单位预警 -->
	            		 <c:if test="${fn:length(sessionScope.shiroUser.siteCode) > 6 }">
	                        <c:if test="${sessionScope.shiroUser.iscost == 1}">
	                			<div  class="page-green-btn1" onclick="downExcel();"><i></i>导出列表</div>
	                        	<div  class="page-blue-btn1" onclick="emailSupervision();"><i></i>邮件督办</div>
	            			</c:if>
	            		 </c:if>
                    </div>
                </div><!-- /tab_header -->
                <div class="tab_box1 row">
                    <table id="early_table" cellpadding="0" cellspacing="0" class="table">
                        
                    </table>
                </div><!-- /首页连通性 -->
                
                </div>
            </div>
            <%@ include file="/common/footer.jsp"%>
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->

<!-- Modal -->
<div id="viewModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="dialog-close"></i></button>
    <h3 id="myModalLabel1"></h3>
  </div>
  <div class="modal-body">
    <div class="modal-tab row">
        <table id="yujModalTab" cellpadding="0" cellspacing="0" class="table">
        </table>
        <div id="tabLoading" class="loading"><i></i>正在加载中，请稍候...</div>
    </div><!-- /dialog-table -->
  </div>

</div>
<script language="javascript" type="text/javascript">
$(function(){
	//隐藏分页样式
	$("#early_table_filter").hide();
	$("#early_table_length").hide();
	
	
	$(".tab_box1 table tr:odd td").css("background","#fafbfc"); 
	$("#navPills li").click(function(){
		$("#navPills li").removeClass("active");
		$(this).addClass("active");
		$(".chart-content").hide();
		$(".chart-content-"+$(this).attr("id")).show();
	});
	var dataEnd=0;
	$("#viewModal .close").click(function(){
		var early_sum = $("#early_sum").val();
		var early_id = $("#early_id").val();
		$("#new"+early_id).text("0/"+early_sum);
		$("#"+early_id).next().removeClass("xin-icon");
		dataEnd=0;
	});
	//滚动条
	$(".modal-tab").mCustomScrollbar({
		theme:"dark",
		autoDraggerLength:true,
		advanced: {
			updateOnContentResize: true,
			updateOnBrowserResize: true,
			autoHideScrollbar: true
		},
		callbacks:{
			onTotalScroll:function(){
				if(dataEnd==0){
					DataLazyLoad();
				}
			}
		}
	});
	

    
	/*============================
	@author:flc
	@time:2015-10-08
	============================*/
	$(".select").each(function(){
		var s=$(this);
		var z=parseInt(s.css("z-index"));
		var dt=$(this).children("dt");
		var dd=$(this).children("dd");
		var _show=function(){dd.slideDown(200);dt.addClass("cur");s.css("z-index",z+1);};   //展开效果
		var _hide=function(){dd.slideUp(200);dt.removeClass("cur");s.css("z-index",z);};    //关闭效果
		dt.click(function(){dd.is(":hidden")?_show():_hide();});
		dd.find("li").click(function(){dt.html($(this).html());_hide();});     //选择效果（如需要传值，可自定义参数，在此处返回对应的“value”值 ）
		$("body").click(function(i){ !$(i.target).parents(".select").first().is(s) ? _hide():"";});
	})
	/*============================
	@author:DataLazyLoad
	@time:2015-11-06
	============================*/
/* 	var dataL=20;
	if(dataL>10){
		$("#tabLoading").show();
	} */
	//Call DataLazyLoad
	function DataLazyLoad(){
		var pno = $("#pageNo").val();
		var psize = $("#pageTotal").val();
		//var dataJson=[{"num":"2"},{"num":"3"},{"num":"4"}];
		//var pageMax=10;
		if(pno<psize){
			pno = parseInt(pno)+1;
			var id = $("#early_id").val();
			var earlyType=$("#earlyType").val();
			$.ajax({  
		          type : "POST",  
		          url : webPath+"/early_getEarlyDetail.action",  
		           data : {
                    	 siteCode:id,
                    	 size:10,
                    	 pos:pno,
                    	 earlyType:earlyType
                     },    
		          dataType:"json",
		          async : false,  
		          success : function(data) {
		         	 var body = data.body;
		         	 for (var int = 0; int < body.length; int++) {
		         		 var str=body[int];
		         		var pnos = $("#pageNo").val();
		         		var xuhao = parseInt(str.dataNumber) + ((10) * (parseInt(pnos)+1-1));
		         		 $("#yujModalTab").append("<tr><td>"+xuhao+"</td>" +
		         		 		"<td class='td_left font_701999'>"+str.earlyTypeName+"</td>" +
		         		 		"<td class='td_left'>"+str.queDescribe+"</td>" +
		         		 		"<td>"+str.scanTime+"</td>" +
		         		 				"</tr>");
					}
	          	 //总页数
	          	 pageTotal = data.pageTotal;
	          	 //当前页数
	          	 pageNo = data.PageNo;
	          	 //总记录数
	          	 recordSize = data.recordSize;
	          	var pno = $("#pageNo").val(pageNo);
	    		var psize = $("#pageTotal").val(pageTotal);
	          	/*  $("#yujModalTab").append("<input type='hidden' id='pageNo' value='"+pageNo+"'>" +
	           	 		"<input type='hidden' id='pageTotal' value='"+pageTotal+"'>") */
	
	           }
	        });
		}
		
		if(pno >= psize){
			$("#tabLoading").hide();
			dataEnd=1;
		}
	}
});
//修改头部预警数
function changeEarly(early_count,obj){
	var early_hidden = $("#early_hidden").val();
	$(obj).removeAttr("onclick");
	var num = Number(early_hidden) - Number(early_count);
	if(num>999){
		$("#early_count").text("999+");
		$("#early_hidden").val(num)
	}else if(num==0){
		$("#early_count").parent().parent().removeClass("operater_red");
		$("#early_count").remove();
	}else{
		$("#early_hidden").val(num)
		$("#early_count").text(num);
	}
}
</script>
  </body>
</html>

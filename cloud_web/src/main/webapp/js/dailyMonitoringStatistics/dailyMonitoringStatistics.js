var siteCodes = "";//当前登录人网站标识码
var level="";
var dataJson="";
var dataJsonSite="";
$(function(){
	$("#setting").hide();
	var tabHide=$("#tabHide").val();
	isHideTab(tabHide,'init');
	//初始化点击事件
	initBind();
	//初始化页面内容
	initContent();
});


/**
 * @描述:点击面包屑 和下钻的时候调用  查询level name tabHide
 * @作者:liujc@ucap.com.cn
 * @时间:2017-3-8下午3:03:56 
 * @param siteCodes
 * @param level
 * @param siteName
 */
function databaseInfo(siteCodes,level,siteName){
	
	$("#nextNodeUl"+level).hide();
	$.ajax( {
		type : "POST",
		url : webPath+"/bigDataAnalysis_bigDataAnalysis.action",
		data :{
			orgSiteCode:siteCodes,
			level:level,
			siteName:siteName,
			returnType:"0"
		},
		dataType:"json",
		async : false,
		success : function(data) {
			$("#siteCodes").val(data.orgSiteCode);
			$("#level").val(data.level);
			$("#siteName").val(data.siteName);
			$("#tabHide").val(data.tabHide);
			//初始化tab标签
			isHideTab(data.tabHide);
			//加载内容
			initContent();
		}
	});
}
/**
 * @描述:控制面包屑 调用填充页面数据
 * @作者:liujc@ucap.com.cn
 * @时间:2017-3-8下午3:04:50
 */
function initContent(){
	siteCodes = $("#siteCodes").val();
	level=$("#level").val();
	var siteName=$("#siteName").val();
	var loginLevel=$("#loginLevel").val();
	
	var html='';
		if(loginLevel == level){
			html='<span style="cursor: pointer;" onclick="databaseInfo(\''+siteCodes+'\',\''+level+'\',\''+siteName+'\')">'+siteName+'</span>';
			$("#nextNode"+level).html(html);
		}else{
			var  htmlSpan='<span style="cursor: pointer;" ><i class="xie_line"></i>'+siteName+'<i></i></span>';
			$("#nextNodeSpan"+level).html(htmlSpan);
			if($("#nextNode"+level).is(":hidden")){
			$.each(dataJson.body, function(index, obj) {
				html+='<li  onclick="databaseInfo(\''+obj.taskid+'\',\''+level+'\',\''+obj.name+'\')">'+obj.name+'</li>';
			});
			$("#nextNodeUl"+level).html(html);
			}
		}
		
		$("#nextNode"+level).show();
	
	for(var i=1;i<5;i++){
		if(i>level*1){
			$("#nextNode"+i).hide();
		}
	}
	dataJson="";
	dataJsonSite="";
	//列表检索
	$("#searchInfo_id").keyup(function(){
	    var searchInfo=$("#searchInfo_id").val();
			 var num=0;
			 $(".table").find(".wz-name").each(function(index, element) {
				 if($(this).html().indexOf(searchInfo)<0){
					$(this).parents("tr").hide();
				 }else{
					 num+=1;
					 $(this).parents("tr").show();
					 $(this).parents("tr").find(".font_701999").html(num);
				 }
			});
	});
//	querySiteData('getLocalSites');
	controlShow('clickType');
//	queryTotalData('getNextCityAndCountry')
}

//默认单选框
function defaultRadio(tabid){
	if (tabid == 2) {
		$("#radio2").prop("checked",true);
		$("#radioVal").val('2');
		$("#unitVal").val("百分率");
		$(".input_sel_active-g").removeClass("input_sel_active-g");
		$("#radio2span").addClass("input_sel_active-g");
	}else if(tabid == 3){
		$("#radio2").prop("checked",true);
		$("#radioVal").val('2');
		$("#unitVal").val("百分率");
		$(".input_sel_active-g").removeClass("input_sel_active-g");
		$("#radio2span").addClass("input_sel_active-g");
	}else if (tabid == 1 ) {//下级门户
		$("#radio10").prop("checked",true);
		$("#radioVal").val('10');
		$("#unitVal").val("百分率");
		$(".input_sel_active-g").removeClass("input_sel_active-g");
		$("#radio10span").addClass("input_sel_active-g");
	}else if(tabid == 0){//本级部门
		$("#radio10").prop("checked",true);
		$("#radioVal").val('10');
		$("#unitVal").val("百分率");
		$(".input_sel_active-g").removeClass("input_sel_active-g");
		$("#radio10span").addClass("input_sel_active-g");
	}else if(tabid == 4){//
		$("#radio10").prop("checked",true);
		$("#radioVal").val('10');
		$("#unitVal").val("百分率");
		$(".input_sel_active-g").removeClass("input_sel_active-g");
		$("#radio10span").addClass("input_sel_active-g");
	}
	
}
/**
 * @描述:控制显示什么内容
 * @作者:liujc@ucap.com.cn
 * @时间:2017-3-7下午12:29:18
 */
function controlShow(clickType){
	var tabId=$("#tabId").val();
	var dayVal=$("#dayVal").val();
	var contentType=$("#contentType").val();
	var radioVal=$("#radioVal").val();
	var pageSizeVal=$("#pageSizeVal").val();
	//2 检测数量 3首页不更新
	var siteCodeFlagType=$("#siteCodeFlagType").val();
//	var siteCodeFlag=$("#siteCodeFlag").val();//判断是普通导出excel,还是点击站点数或者首页不更新
//	if(''==siteCodeFlag){
//		
//	}else{
//		
//	}
	//选中图表 切换tab标签的时候才会重置选中项
	if(contentType==0 && clickType=="clickType"){
		//图表
		defaultRadio(tabId);
	}
	//隐藏单选按钮
	$(".every_radio").hide();
	$("#radioli7").show();
	$("#radioli11").show();
	if("tab"+tabId=="tab2" || "tab"+tabId=="tab3"){
		//站群
		$("#radioli1").show();
		$("#radioli2").show();
		$("#radioli3").show();
		$("#radioli4").show();
		$("#radioli5").show();
		$("#radioli6").show();
	}else if("tab"+tabId =="tab1" || "tab"+tabId=="tab0" || "tab"+tabId=="tab4"){
		//站点
		$("#radioli8").show();
		$("#radioli9").show();
		$("#radioli10").show();
	}
	//图表单选按钮绑定点击事件
	radioBindCheck();
	if(tabId==0){
		querySiteData('getLocalSites');
	}else if(tabId==1){
		querySiteData('getNextSitesMH',undefined,'','0');
	}else if(tabId==2){
		queryTotalData('getNextCityAndCountry','0');
	}else if(tabId==3){
		queryTotalData('getNextCityAndCountry','1');
	}else if(tabId==4){
		querySiteData('getNextSitesMH',undefined,'','1');
	}
}
/**
 * @描述:图表单选按钮绑定点击事件
 * @作者:liujc@ucap.com.cn
 * @时间:2017-3-8上午10:12:01
 */
function radioBindCheck(){
	$("input[type='radio']").iCheck({
		checkboxClass : 'icheckbox_square-blue',
		radioClass : 'iradio_square-blue'
	});
	$("[name='chartImg']").on('ifChecked', function(event){
		 $("#radioVal").val( $(this).val());
		 $(".input_sel_active-g").removeClass("input_sel_active-g");
		 $("#"+this.id+"span").addClass("input_sel_active-g");
		 if(this.id=="radio1"){
			 $("#unitVal").val("个数");
		 }else if(this.id=="radio2"){  
			 $("#unitVal").val("百分率");
		 }else if(this.id=="radio3"){
			 $("#unitVal").val("平均数");
		 }else if(this.id=="radio4"){
			 $("#unitVal").val("个数");
		 }else if(this.id=="radio5"){
			 $("#unitVal").val("百分率");
		 }else if(this.id=="radio6"){
			 $("#unitVal").val("条数");
		 }else if(this.id=="radio7"){
			 $("#unitVal").val("条数");
		 }else if(this.id=="radio8"){
			 $("#unitVal").val("天数");
		 }else if(this.id=="radio9"){
			 $("#unitVal").val("个数");
		 }else if(this.id=="radio10"){
			 $("#unitVal").val("百分率");
		 }else if(this.id=="radio11"){
			 $("#unitVal").val("");
		 }
		 controlShow();
		});
}


/**
 * @描述:填充  组织单位html
 * @作者:liujc@ucap.com.cn
 * @时间:2017-3-8下午3:05:29 
 * @param data
 */
function eachData(data){
	var tableTitleClass=$("#tableTitleClass").val();
	var html='';
	var con = $('#content');
	con.html('');
	if(data.errorMsg){
		$("#chartFlag").val(1);// 0:有数据    1:无数据
	}else{
		$("#chartFlag").val(0);// 0:有数据    1:无数据
		html+='<table id="table-SSHXSort" cellpadding="0" cellspacing="0" class="table bigData-tab" style="margin-bottom:0;">';
		html+='<tr style="border-bottom:none;">';
		html+='	<th style="width:6%; text-align:center;  ">序号</th>';
		html+='	<th style="width:15%; text-align:left;  ">组织单位名称/编码</th>';
		html+='	<th style="width:10%; position: relative; text-align:center;" class="numOrder">监测网站数量<div style="color:#999; font-weight:normal;"></div><i class="tab_angle"></i></th>';
		html+='<th style="width: 10%;position: relative;text-align:center;" class="numOrder">健康指数<br><div style="color:#999; font-weight:normal;"></div><i class="tab_angle"></i></i></th>';
		html+='<th style="width: 10%;position: relative;text-align:center;" class="numOrder">监测不连通率<br><div style="color:#999; font-weight:normal;" title="不连通次数/监测次数">（占比）</div><i class="tab_angle"></i></i></th>';
		html+='<th style="width:10%;position: relative;text-align:center;" class="numOrder"> 首页死链平均数<br><div style="color:#999;font-weight:normal;" title="总个数/站点数">（个）</div><i class="tab_angle" style="right:-1px;"></i></th>';
		html+='<th style="width: 10%;position: relative;text-align:center;" class="numOrder">首页不更新<br><div style="color:#999;font-weight:normal;"   title=" 选定周期内首页持续不更新的网站个数">（网站数）</div><i class="tab_angle"></i></i></th>';
		html+='<th style="width:10%;position: relative;text-align:center;" class="numOrder">首页不更新<br><div style="color:#999;font-weight:normal;" title="不更新站点数/站点数">（占比）</div><i class="tab_angle"></i></th>';
		html+='<th style="width: 10%;position: relative;text-align:center;" class="numOrder">平均更新量<br><div style="color:#999;font-weight:normal;" title="总更新条数/站点数">（条/站）</div><i class="tab_angle"></i></th>';
		html+='<th style="width: 10%; position: relative;text-align:center;" class="numOrder">监测更新量<br><div style="color:#999;font-weight:normal;"  title="选定周期内首页或栏目更新的数量">（条）</div><i class="tab_angle"></i></th>';
		html+='</tr>';
		html+='</table>';
		html+='<table id="table-SSHX" value="searchInfo_id" name="tableName" cellpadding="0" cellspacing="0" class="table bigData-tab">';
		html+='	<tbody>';
		$.each(data.body, function(index, obj) {
			var linkerrsitenum;//不连通个数
			var linkerrsiteprop;
			var indexdeadnum;
			var noupdatesitenum;
			var noupdatesiteprop;
			var aveupdatenum;
			var updatenum;
			var healthindex;//健康指数
			var dateSelectval = $("#dayVal").val();
			if(dateSelectval ==1){
				linkerrsitenum=obj.linkerrsitenum;
				linkerrsiteprop=obj.linkerrsiteprop;
				indexdeadnum=obj.indexdeadnum;
				noupdatesitenum=obj.noupdatesitenum;
				noupdatesiteprop=obj.noupdatesiteprop;
				aveupdatenum=obj.aveupdatenum;
				updatenum=obj.updatenum;
				healthindex=obj.healthindex;
			}else if(dateSelectval == 7){
				linkerrsitenum=obj.linkerrsitenum7;
				linkerrsiteprop=obj.linkerrsiteprop7;
				indexdeadnum=obj.indexdeadnum7;
				noupdatesitenum=obj.noupdatesitenum7;
				noupdatesiteprop=obj.noupdatesiteprop7;
				aveupdatenum=obj.aveupdatenum7;
				updatenum=obj.updatenum7;
				healthindex=obj.healthindex7;
			}else if(dateSelectval == 14){
				linkerrsitenum=obj.linkerrsitenum14;
				linkerrsiteprop=obj.linkerrsiteprop14;
				indexdeadnum=obj.indexdeadnum14;
				noupdatesitenum=obj.noupdatesitenum14;
				noupdatesiteprop=obj.noupdatesiteprop14;
				aveupdatenum=obj.aveupdatenum14;
				updatenum=obj.updatenum14;
				healthindex=obj.healthindex14;
			}else if(dateSelectval == 30){
				linkerrsitenum=obj.linkerrsitenum30;
				linkerrsiteprop=obj.linkerrsiteprop30;
				indexdeadnum=obj.indexdeadnum30;
				noupdatesitenum=obj.noupdatesitenum30;
				noupdatesiteprop=obj.noupdatesiteprop30;
				aveupdatenum=obj.aveupdatenum30;
				updatenum=obj.updatenum30;
				healthindex=obj.healthindex30;
			}
			if(obj.taskid !="bm8000"){

			html+='	<tr>';
			html+='	<td  style="width:6%;text-align:center;" class="font_701999">'+(index*1+1)+'</td>';
//			if(obj.taskid.substring(obj.taskid.length-2) !='00'){
//				html+='<td class="wz-name" style="width:200px; text-align:left;"><a >'+obj.name+'<br>'+obj.taskid+'</a></td>';	
//			}else{
//				href="'+webPath+'/bigDataAnalysis_bigDataAnalysis.action?orgSiteCode='+obj.taskid+'&level='+(level*1+1)+'&siteName='+obj.name+'"
//				target="_blank"
			
			html+='<td class="wz-name" style="width:15%; text-align:left;"><a  href="javascript:databaseInfo(\''+obj.taskid+'\',\''+(level*1+1)+'\',\''+obj.name+'\')" >'+obj.name+'<br>'+obj.taskid+'</a></td>';
//			}
			html+='	<td class="sort-num" style="width:10%; text-align:center;"><a class="sort-num" onclick="querySiteData(\'queryAllSite\',\''+obj.taskid+'\',2,0,\''+obj.code+'\')" style="cursor:pointer;">'+obj.sitenum+'</a></td>';
			html+='	<td class="sort-num" style="width:10%; text-align:center;"><span class="sort-num">'+healthindex+'</span></td>';
//			html+='	<td class="sort-num" style="width:128px;text-align:center;"><a class="sort-num" onclick="querySiteData(\'queryAllSite\',\''+obj.taskid+'\',4,100)" style="cursor:pointer;">'+linkerrsitenum+'</a></td>';
//			html+='	<td class="sort-num" style="width:128px;text-align:center;">'+linkerrsitenum+'</td>';
			html+='	<td class="sort-num" style="width:10%;text-align:center;"><span class="sort-num">'+linkerrsiteprop+'</span>%</td>';
			html+='	<td class="sort-num" style="width:10%;text-align:center;"><span class="sort-num">'+indexdeadnum +'</span></td>';
//			html+='	<td class="sort-num" style="width:100px;text-align:center;">'+noupdatesitenum+'</td>';
			html+='	<td class="sort-num" style="width:10%;text-align:center;"><a class="sort-num" onclick="querySiteData(\'queryAllSite\',\''+obj.taskid+'\',3,0,\''+obj.code+'\')" style="cursor:pointer;">'+noupdatesitenum+'</a></td>';
			html+='	<td class="sort-num" style="width:10%;text-align:center;"><span class="sort-num">'+noupdatesiteprop+'</span>%</td>';
			html+='	<td class="sort-num" style="width:10%;text-align:center;"><span class="sort-num">'+aveupdatenum+'</span></td>';
			html+='	<td class="sort-num" style="width:10%;text-align:center;"><span class="sort-num">'+updatenum+'</span></td>';
			html+='	</tr>';
			}
		});
		html+='	</tbody>';
		html+='</table>';
		dataJsonSite="";//加载组织单位数据把填报单位数据清空
	}
	$(html).appendTo(con);

}

/**
 * @描述:组织单位查询数据
 * @作者:liujc@ucap.com.cn
 * @时间:2017-3-8下午3:05:29 
 * @param data
 */
function queryTotalData(methods,isBm){
	//加载数据的时候会清空  点击数字记录的状态
	$("#siteCodeFlag").val('');
	//加载数据的时候会清空 搜索框的数据
	$("#searchInfo_id").val('');
	$("#exportExcelDiv").show();
	$("#chartDiv").show();
	var tableTitleClass=$("#tableTitleClass").val();
	var siteName=$("#siteName").val();
	var tabId=$("#tabId").val();
	
	var dayVal=$("#dayVal").val();
	var contentType=$("#contentType").val();
	var radioVal=$("#radioVal").val();
	var pageSizeVal=$("#pageSizeVal").val();
		$.ajax( {
			type : "POST",
			url : webPath+"/bigDataAnalysis_"+methods+".action",
			data :{
				orgSiteCode:siteCodes,
				level:level,
				siteName:siteName,
				tabId:tabId,
				isBm:isBm,
				contentType:contentType,
				dayNum:dayVal,
				radioVal:radioVal,
				pageSizeVal:pageSizeVal
			},
			dataType:"json",
			async : false,
			success : function(data) {
//				isHideTab(data.tabHide);
				if(contentType==0){
					//图表
					chartImg(data);
				}else if(contentType==1){
					//列表
					if(data.tabHide==3){
						querySiteData('getLocalSites');
					}else{
						dataJson=data;
						eachData(data);
					}
				}
			}
		});
	var chartFlag=$("#chartFlag").val();
	//有数据  并且选中的是展示列表数据
	if(chartFlag==0 && contentType==1){
		//列表排序
		new TableSorter("table-SSHX",2,3,4,5,6,7,8,9);
	$("#table-SSHXSort th").on('click', function(event){
			
			if($(this).find(".tab_angle").hasClass("tab_angle_bottom")){
				
				$("#table-SSHX .tab_angle").attr("class","tab_angle");
				$(this).find(".tab_angle").addClass("tab_angle_top");
				$(this).find(".tab_angle").removeClass("tab_angle_bottom");
			}else{
				$("#table-SSHX .tab_angle").attr("class","tab_angle");
				$(this).find(".tab_angle").addClass("tab_angle_bottom");
				$(this).find(".tab_angle").removeClass("tab_angle_top");
			}
		  });
	$(".numOrder").click(function(){
		 var num=0;
		 $(".table").find(".wz-name").each(function(index, element) {
			 if( $(this).parents("tr").css("display") != "none"){
				 num+=1;
				 $(this).parents("tr").find(".font_701999").html(num);
			 }else if($(this).parents("tr").css("display") == "table-row"){
				 num+=1;
				 $(this).parents("tr").find(".font_701999").html(num);
			 }

		});
		
	});
	//列表
	var tableWidth="";
	var tableTop="";
	if($("#tableWidth").val() ==""){
		tableWidth=$("#table-SSHXSort").parent(".lb_table").width();
	}else{
		tableWidth=$("#tableWidth").val();
	}
	if($("#tableTop").val() ==""){
		tableTop=document.getElementById("table-SSHXSort").offsetTop;
	}else{
		tableTop=$("#tableTop").val();
	}	
	if(tableWidth>0){
		$("#tableWidth").val(tableWidth);
	}
	if(tableTop>0){
		$("#tableTop").val(tableTop);
	}
		$("#table-SSHXSort").width($("#tableWidth").val());
		var tabHeaderTop=$("#tableTop").val()*1-105;
		$(window).scroll(function (e) {
			if(tabHeaderTop < $(window).scrollTop()){
				if(tableTitleClass==0){
					$("#table-SSHXSort").addClass("view-head-fixed");
				}else{
					$("#table-SSHXSort").addClass("view-head-fixed_two");
				}
			}else{
				
				if(tableTitleClass==0){
					$("#table-SSHXSort").removeClass("view-head-fixed");
				}else{
					$("#table-SSHXSort").removeClass("view-head-fixed_two");
				}
				
			}
			return false;
		});
	}

}
/**
 * @描述: 填充 填报单位html
 * @作者:liujc@ucap.com.cn
 * @时间:2017-3-8下午3:07:46 
 * @param data
 */
function eachDataSite(data){
	var tableTitleClass=$("#tableTitleClass").val();
	var html='';
	//清空
	$('#content').html('');
	if(data==0){
		//  等于0  情况 免费用户无法查看 
		html+='<div class="no-data"><i></i>本功能为收费项目，请联系开普云监管服务团队  <span >4000-976-005</span></div>';
	}else{
	if(data.errorMsg){
		html+='<div class="zanwsj">'+data.errorMsg+'</div>';
		$("#chartFlag").val(1);// 0:有数据    1:无数据
	}else{
		$("#chartFlag").val(0);// 0:有数据    1:无数据
		html+='<table id="table-MHSort" cellpadding="0" cellspacing="0" class="table bigData-tab2" style="margin-bottom:0;">'
		    +'<tr style="border-bottom:none;">';
		    html+='	<th style="width:6%; text-align:center;">序号</th>'
			+'<th style="width:15%;text-align:left;">组织单位名称/编码</th>'
			+'<th style="width:15%;text-align:left;padding-right:57px;">网址</th>'
			+'<th style="width:7%;position: relative; text-align:center;" class="numOrder ">健康指数<br><div style="color:#999;font-weight:normal;"></div><i class="tab_angle" style="right:-1px;"></i></th>'
			+'<th style="width:10%;position: relative; text-align:center;" class="numOrder ">监测不连通率<br><div style="color:#999;font-weight:normal;" title="不连通次数／监测次数" >（占比）</div><i class="tab_angle" style="right:-1px;"></i></th>'
			+'<th style="width:10%;position: relative; text-align:center;" class="numOrder ">首页不可用链接<br><div style="color:#999;font-weight:normal;">（个）</div><i class="tab_angle" style="right:-1px;"></i></th>'
			+'<th style="width:10%;position: relative; text-align:center;" class="numOrder ">首页不更新<i class="tab_angle" style="right:-1px;"></i></th>'
			+'<th style="width:10%;position: relative; text-align:center;" class="numOrder ">监测更新量<br><div style="color:#999;font-weight:normal;" title="选定周期内首页或栏目更新的数量">（条）</div><i class="tab_angle" style="right:-1px;"></i></th>'
			+'<th style="width:10%;position: relative; text-align:center;" class="numOrder">最后更新日期</th>'
			+'<th style="width:10%;position: relative; text-align:center;" class="numOrder ">未更新天数<i class="tab_angle" style="right:-7px;"></i></th>'
//		<i class="tab_angle"></i> <i class="tab_angle"></i>
			+'</tr></table>';
		html+='<table id="table-MH" value="searchInfo_id" name="tableName" cellpadding="0" cellspacing="0" class="table bigData-tab"><tbody>';
		$.each(data.body, function(index, obj) {
			var linkerrprop;
			var indexdeadnum;
			var updatestatus;
			var updatenum;
			var healthindex;
			var updatecodesType;
			var arr=new Array(); 
			arr=obj.updatecodes.split(",");
			for(var i=0;i<arr.length;i++){
				if(arr[i] =="200" || arr[i]=="301" || arr[i]=="302"){
					updatecodesType=1;//连通
					break;
				}else{
					updatecodesType=-1;//没有连通
				}
			}
			if(obj.lastupdatedate ==0){
				updatecodesType=-1;//没有连通
			}
			var lastupdatedate;//最后更新时间
			var noupdateday=obj.noupdateday;//未更新天数
			if(noupdateday != -1){
				if(noupdateday>14){
					updatestatus='<span class="sort-num" style="color:red">超过两周</span>';
				}else{
					updatestatus='<span class="sort-num">正常更新</span>';
				}
			}else{
				updatestatus='<span class="sort-num">未知</span>';
				lastupdatedate="未知";
				noupdateday="未知";
			}
			
			var dateSelectval =  $("#dayVal").val();
			if(dateSelectval ==1){
				linkerrprop=obj.linkerrprop;
				indexdeadnum=obj.indexdeadnum;
				updatenum=obj.updatenum;
				healthindex=obj.healthindex;
			}else if(dateSelectval == 7){
				linkerrprop=obj.linkerrprop7;
				indexdeadnum=obj.indexdeadnum7;
				updatenum=obj.updatenum7;
				healthindex=obj.healthindex7;
			}else if(dateSelectval == 14){
				linkerrprop=obj.linkerrprop14;
				indexdeadnum=obj.indexdeadnum14;
				updatenum=obj.updatenum14;
				healthindex=obj.healthindex14;
			}else if(dateSelectval == 30){
				linkerrprop=obj.linkerrprop30;
				indexdeadnum=obj.indexdeadnum30;
				updatenum=obj.updatenum30;
				healthindex=obj.healthindex30;
			}
			//最后更新日期  未知
			if(updatecodesType==-1){
				lastupdatedate="未知";
				noupdateday="未知";
				updatestatus='<span class="sort-num">未知</span>';
			}else{
				lastupdatedate=subDateStr(obj.lastupdatedate);
			}
			html+='<tr>';
			html+='	<td  style="width:6%;text-align:center;" class="font_701999">'+(index*1+1)+'</td>';
			html+='<td class="wz-name" style="width:15%; text-align:left;"><a target="_blank" style="display:block; width:120px; " href="'+webPath+'/fillUnit_gailan.action?siteCode='+obj.sitecode+'">'+ obj.name+'<br>'+obj.sitecode+'</a></td>';
			
			var  siteUrl = checkUrlHTTTP(obj.url);
			html+='<td style="width:15%; text-align:left;"><a target="_blank"  href="'+siteUrl+'" title="'+siteUrl+'">'+ subStr(siteUrl,20)+'</a></td>';
			html+='<td class="sort-num" style="width:7%;text-align:center;"><span class="sort-num">'+healthindex+'</span></td>';
			html+='<td class="sort-num" style="width:10%;text-align:center;"><span class="sort-num">'+linkerrprop+'</span>%</td>'
				+'<td class="sort-num" style="width:10%;text-align:center;"><span class="sort-num">'+indexdeadnum+'</span></td>'
				+'<td class="sort-num" style="width:10%;text-align:center;"><span id="updatestatus'+obj.sitecode+'">'+updatestatus+'</span></td>'
				+'<td class="sort-num" style="width:10%;text-align:center;"><span class="sort-num">'+updatenum+'</span></td>'
				+'<td class="sort-num" style="width:10%;text-align:center;position:relative"><a title="查看最后更新的url" class="addHover" siteCode="'+obj.sitecode+'" href="javascript:lastUpdateUrl(\''+obj.sitecode+'\')">'+lastupdatedate+'</a>'
				+'<div style="display:none;width:320px; padding:8px 5px 8px 5px;background:#FFFFE8;position:absolute;right:-100px;z-index:9999; border:1px solid #ccc;">'
				+'<p style="font-size：14px; line-height:22px;">昨天未获取到网站的最后更新日期  <span class="noDate" style="color:red">1</span></p>'
				+'<p style="font-size：14px; line-height:22px;">系统最后一次正常检测到的更新日期 ：<span class="lastDate">2</span></p></div></td>'
				+'<td class="sort-num" style="width:10%;text-align:center;" ><span id="noupdateday'+obj.sitecode+'" class="sort-num">'+noupdateday+'</span></td>'
				+'</tr>';

		});
		html+='</tbody></table>';
	}
}
	$("#html").val(html);
	$('#content').append(html);
	$(".addHover").hover(function(){
			var content = $(this).html();
			if(content == '未知'){
				$(this).attr('title','');
				var here = $(this).parent().find("div").css('display','block');
				var yuan = $(this).parent().find("div span.noDate");
				var lastDate = $(this).parent().find("div span.lastDate");
				var siteCode = $(this).attr("siteCode");
				$.ajax( {
					type : "POST",
					url : webPath+"/bigDataAnalysis_findLastDate.action",
					dataType:"json",
					async : false,
					data: {
						siteCode:siteCode
					},
					success : function(data) {
						//here.css('display','block');
						yuan.html(data.name);
						lastDate.html(data.upateTime);
						$("#updatestatus"+siteCode).html(data.updatestatus)
						$("#noupdateday"+siteCode).html(data.noupdateday);
					},error: function (data) {//请求失败处理函数
			        }
				});
			}
		},function(){
			var ss=$(this).parent().find("div").css('display','none');
			var siteCode = $(this).attr("siteCode");
			var content = $(this).html();
			if(content == '未知'){
			$("#updatestatus"+siteCode).html('未知');
			$("#noupdateday"+siteCode).html('未知');
			}
	});
}

/**
 * @描述: 10位站点统计数据
 * @作者:liujc@ucap.com.cn
 * @时间:2017-3-3下午4:50:00 
 * @param methods  请求后台的方法名
 * @param id 组织单位sitecode
 * @param type 点击的某列数字
 * @param isBm 是否部委 0：地方  1：部委
 */
function querySiteData(methods,id,type,isBm,code){
	$("#code").val(code);
	//加载数据的时候会清空  点击数字记录的状态
	$("#siteCodeFlag").val('');
	//加载数据的时候会清空 搜索框的数据
	$("#searchInfo_id").val('');
	var loginLevel=$("#loginLevel").val();
	var level=$("#level").val();//级别
	if(isOrgCost==0 && ( id !=undefined || (loginLevel!= level && id ==undefined)) ){
//	if(false){
		//免费站点
		eachDataSite(0);
		$("#exportExcelDiv").hide();//隐藏导出按钮
		$("#chartDiv").hide();//隐藏图标按钮
	}else if(isOrgCost==1 || ( loginLevel== level && id ==undefined)){
//		}else if(true){
		//付费站点
		$("#exportExcelDiv").show();//显示导出按钮
		$("#chartDiv").show();//显示图标按钮
	var tableTitleClass=$("#tableTitleClass").val();
	var orgSiteCode=$("#siteCodes").val();//组织机构编码
	var searchDate =  $("#dayVal").val();//下拉框时间段
	var tabId=$("#tabId").val();//当前选中的tab
	
	var dayVal=$("#dayVal").val();//天数
	var contentType=$("#contentType").val();//图表0 列表1
	var radioVal=$("#radioVal").val();//单选按钮选中值
	var pageSizeVal=$("#pageSizeVal").val();//图表-1全部  1前20条  2后20条
	var linkerrprop ="linkerrprop";
	if(id !=undefined){
		orgSiteCode=id;
//		$("#exportExcel").hide();
		$("#sel").hide();
		$("#siteCodeFlag").val(orgSiteCode);
		$("#siteCodeFlagType").val(type);
		if(searchDate>1){
			linkerrprop="linkerrprop"+searchDate;
		}
		$("#linkerrprop").val(linkerrprop);//网站不连通 查询条件名
		$("#linkerrpropVal").val(linkerrpropVal);//网站不连通 查询条件值
	}
		$.ajax( {
			type : "POST",
			url : webPath+"/bigDataAnalysis_"+methods+".action",
			dataType:"json",
			async : false,
			data: {
				level:level,
				orgSiteCode:orgSiteCode,
				dayNum:searchDate,
				urlType:type,
//				linkerrprop:linkerrprop,
//				linkerrpropVal:linkerrpropVal,
				tabId:tabId,
				isBm:isBm,
				code:code,
				contentType:contentType,
				dayNum:dayVal,
				radioVal:radioVal,
				pageSizeVal:pageSizeVal
			},
			success : function(data) {
//				isHideTab(data.tabHide);
				if(contentType==0){
					
					//图表
					chartImg(data);
				}else if(contentType==1){
					//列表
					eachDataSite(data);
					dataJsonSite=data;
				}
				
			},error: function (data) {//请求失败处理函数
	        }
		});
	var chartFlag=$("#chartFlag").val();
	if(chartFlag==0 && contentType==1){
		//列表排序
		new TableSorter("table-MH",3,4,5,6,7,9); 
	$("#table-MHSort th").on('click', function(event){
			if($(this).find(".tab_angle").hasClass("tab_angle_bottom")){
				
				$("#table-MH .tab_angle").attr("class","tab_angle");
				$(this).find(".tab_angle").addClass("tab_angle_top");
				$(this).find(".tab_angle").removeClass("tab_angle_bottom");
			}else{
				$("#table-MH .tab_angle").attr("class","tab_angle");
				$(this).find(".tab_angle").addClass("tab_angle_bottom");
				$(this).find(".tab_angle").removeClass("tab_angle_top");
			}
			//未知悬浮层
			$(".addHover").hover(function(){
				var content = $(this).html();
				if(content == '未知'){
					$(this).attr('title','');
					var here = $(this).parent().find("div").css('display','block');
					var yuan = $(this).parent().find("div span.noDate");
					var lastDate = $(this).parent().find("div span.lastDate");
					var siteCode = $(this).attr("siteCode");
					$.ajax( {
						type : "POST",
						url : webPath+"/bigDataAnalysis_findLastDate.action",
						dataType:"json",
						async : false,
						data: {
							siteCode:siteCode
						},
						success : function(data) {
							//here.css('display','block');
							yuan.html(data.name);
							lastDate.html(data.upateTime);
							$("#updatestatus"+siteCode).html(data.updatestatus);
							$("#noupdateday"+siteCode).html(data.noupdateday);
							
						},error: function (data) {//请求失败处理函数
				        }
					});
				}
			},function(){
				var ss=$(this).parent().find("div").css('display','none');
				var siteCode = $(this).attr("siteCode");
				var content = $(this).html();
				if(content == '未知'){
				$("#updatestatus"+siteCode).html('未知');
				$("#noupdateday"+siteCode).html('未知');
				}
		});
	});
	$(".numOrder").click(function(){
		 var num=0;
		 $(".table").find(".wz-name").each(function(index, element) {
			 if( $(this).parents("tr").css("display") != "none"){
				 num+=1;
				 $(this).parents("tr").find(".font_701999").html(num);
			 }else if($(this).parents("tr").css("display") == "table-row"){
				 num+=1;
				 $(this).parents("tr").find(".font_701999").html(num);
			 }

		});
		
	});
	var tableWidth="";
	var tableTop="";
	if($("#tableWidth").val() ==""){
		tableWidth=$("#table-MHSort").parent(".lb_table").width();
	}else{
		tableWidth=$("#tableWidth").val();
	}
	if($("#tableTop").val() ==""){
		tableTop=document.getElementById("table-MHSort").offsetTop;
	}else{
		tableTop=$("#tableTop").val();
	}	
	if(tableWidth*1>0){
		$("#tableWidth").val(tableWidth);
	}
	if(tableTop*1>0){
		$("#tableTop").val(tableTop);
	}
	$("#table-MHSort").width($("#tableWidth").val());
	var tabHeaderTop=$("#tableTop").val()*1-105;
		$(window).scroll(function (e) {
			if(tabHeaderTop < $(window).scrollTop()){
				if(tableTitleClass==0){
					$("#table-MHSort").addClass("view-head-fixed2");
				}else{
					$("#table-MHSort").addClass("view-head-fixed2_two");
				}
			}else{
				if(tableTitleClass==0){
					$("#table-MHSort").removeClass("view-head-fixed2");
				}else{
					$("#table-MHSort").removeClass("view-head-fixed2_two");
				}
			}
			return false;
		});

	}
}
}
/**
 * @描述:根据 tabHide 控制显示哪些tab标签
 * @作者:liujc@ucap.com.cn
 * @时间:2017-3-8下午3:06:43 
 * @param tabHide
 */
function isHideTab(tabHide,initType){
	if(tabHide == 0){
		//国办
		var bmType=$("#bmType").val();//0：下级站群  1 ：部门站群
		$("#tab0").show();
		$("#tab1").show();
		$("#tab2").show();
		$("#tab3").show();
		$("#tab4").show();
		if(bmType == 0){
			$("#tabId").val(0);
			$('.every_tip').removeClass('on');
			$("#tab0").addClass('on');
		}else if(bmType == 1){
			$("#tabId").val(3);
			$('.every_tip').removeClass('on');
			$("#tab3").addClass('on');
		}
	}else if(tabHide == 1){
		//下级站群
		//页面初始化 
		if(initType=='init'){
			//赋值为0是要选中第一个tab标签  本级站点
			$("#tabId").val(0);
			$('.every_tip').removeClass('on');
			$("#tab0").addClass('on');
		}else{
			//赋值为2是要选中  下级站群
			$("#tabId").val(2);
			$('.every_tip').removeClass('on');
			$("#tab2").addClass('on');
		}
		$("#tab0").show();
		$("#tab1").show();
		$("#tab2").show();
		$("#tab3").hide();
		$("#tab4").hide();
	}else if(tabHide == 2){
		//下级部委
		$("#tabId").val(3);
		$('.every_tip').removeClass('on');
		$("#tab3").addClass('on');
		$("#tab0").hide();
		$("#tab1").hide();
		$("#tab2").show();
		$("#tab3").show();
		$("#tab4").show();
		
	}else if(tabHide == 3){
		//本级部门
		//赋值为0是要选中第一个tab标签
		$("#tabId").val(0);
		$('.every_tip').removeClass('on');
		$("#tab0").addClass('on');
		
		$("#tab0").show();
		$("#tab1").hide();
		$("#tab2").hide();
		$("#tab3").hide();
		$("#tab4").hide();
	}
}


/**
 * @描述:站点数据--导出excel
 * @作者:liujc@ucap.com.cn
 * @时间:2017-3-8下午3:08:13
 */
function exportDate(){
	var tabid=$("#tabId").val();//当前可用的按钮
	var level=$("#level").val();//组织机构级别
	var orgSiteCode=$("#siteCodes").val();//组织机构编码
	var searchDate =  $("#dayVal").val();//下拉框时间段
	var name=$("#siteName").val();//省、市、县名称
	var tabHide=$("#tabHide").val();//点击按钮展示个数
	var code = $("#code").val();
	var siteCodeFlag=$("#siteCodeFlag").val();//判断是普通导出excel,还是点击站点数或者首页不更新
	var siteCodeFlagType=$("#siteCodeFlagType").val();
	var linkerrprop=$("#linkerrprop").val();//网站不连通 查询条件名
	var linkerrpropVal=$("#linkerrpropVal").val();//网站不连通 查询条件值
	if(''==siteCodeFlag){
		window.location.href=webPath+"/dailyMonitoringStatistics_exportExcelData.action?level="+level+"&orgSiteCode="+orgSiteCode+"&searchDate="+searchDate+"&name="+name+"&tabHide="+tabHide+"&DATA="+new Date();
		}else{
		//调用 网站数量详情   首页不更新详情   网站不连通详情
			window.location.href=webPath+"/dailyMonitoringStatistics_exportSiteExcelDate.action?level="+level+"&orgSiteCode="+siteCodeFlag
			+"&dayNum="+searchDate+"&urlType="+siteCodeFlagType+"&linkerrprop="+linkerrprop+"&linkerrpropVal="+linkerrpropVal+"&code="+code;
		
	}

}

/**
 * 生成柱形图
 */
function chartImg(data){
	var ylist = new Array();
	var xlist = new Array();
	var pageSizeVal=$("#pageSizeVal").val();//显示几条数据
	var radioVal=$("#radioVal").val();//选择单选按钮
	var dayVal=$("#dayVal").val();//天数
	xlist=data.xlist;
	ylist=data.ylist;
	if(xlist=="" || xlist==null || ylist=="" || ylist==null || ylist==undefined || xlist==undefined){
		xlist="";
		ylist="";
	}else{
		if(ylist.length>0){
			 $("#chartContent").css("height",(ylist.length*20+150)+"px");
		}
		if(ylist.length==1){
			 $("#chartContent").css("height",150+"px");
		}
	}
	if(xlist!="" && xlist!=null && ylist!="" && ylist!=null){
		if(pageSizeVal!=2){
			xlist.sort(sortNumberAsc);
		}else{
			xlist.sort(sortNumberDesc);
		}
		ylist.reverse();
	}
	
	var unitVal=$("#unitVal").val();
	var myChart = echarts.init(document.getElementById('chartContent'));
	option = {
			color:['#20C05C'],
		    tooltip : {
		        trigger: 'axis',
		        axisPointer: {
							color: 'rgba(150,150,150,0.3)',
							width: 'auto',
							type: 'default'
						}
		    },
		    grid:{
		    	x:230	
		    },
		    calculable : false,
		    xAxis : [
		        {
		            position:'top',
		            type : 'value',
		            boundaryGap : [0, 0.01],
		            name:unitVal
		        }
		    ],
		    yAxis : [
		        {
		        	scale:true,
		            type : 'category',
		            data : ylist
		      
		        }
		      
		    ],
		    series : [
		        {
		            name:'值',
		            type:'bar',
		            data:xlist
		        }
		    ]
		};
	myChart.setOption(option);	 
	if(xlist!="" && xlist!=null && ylist!="" && ylist!=null){
		ylist.reverse();
	}
}
function sortNumberAsc(a, b)
{
return a - b;
}
function sortNumberDesc(a, b)
{
return b - a;
}

/**
 * @描述:初始化点击事件
 * @作者:liujc@ucap.com.cn
 * @时间:2017-3-8下午2:59:20
 */
function initBind(){
	//图表列表切换；
    $('.change').click(function(){
        var n = $(this).index();
        $('.show_box').removeClass('on');
        $('.change').removeClass('on');
        $(this).addClass('on');
        $('.show_box').eq(n).addClass('on');
        $("#contentType").val(n);
        controlShow('clickType');
    });
	//选择天数事件
	$(".dayLi").click(function(){
		$("#dayVal").val(this.value);
		$("#dayName").html($(this).html());
		$("#dayUl").hide();
		$("#searchInfo_id").val('');
		var tabid=$("#tabId").val();
		level=$("#level").val();
		controlShow();
	});
	//tab切换
    //各大块切换；
    $('.every_tip').click(function(){
        var tabid = $(this).index();
        $('.every_tip').removeClass('on');
        $(this).addClass('on');
		$("#tabId").val(tabid);
		var level=$("#level").val();
		dataJson="";
		dataJsonSite="";
		controlShow('clickType');
    });
    //配置弹框tab页切换
    $('.yiny-every-tab.fl').click(function(){
        var tabid = $(this).index();
        $('.yiny-every-tab.fl').removeClass('on');
        $(this).addClass('on');
        $("#configureTabId").val(tabid);
        $("[name='focusTab']").hide();
        $("#focusTab"+tabid).show();
		 // 执行一些动作...
		dailyCopyUrl();
    });
    
    $('.bread').hover(function(){
    	$(this).children('ul').show();
	},function(){
        $(this).children('ul').hide();
	});
	$('.chart_se').hover(function(){
		$(this).children('ul').show();
	},function(){
	    $(this).children('ul').hide();
	});
	//选择 展示条数
	$('[name="pageSize"]').click(function(){
		 $("#pageSizeVal").val( $(this).val());
		 $("#pageSizeUl").hide();
		 $("#pageSizeName").html($(this).html());
		 controlShow();
	 });
}


//日期转换
function subDateStr(str){
	if(str.length==8){
		return str.substring(0,4)+"-"+str.substring(4,6)+"-"+str.substring(6,8);
	}else{
		return str;
	}
	
}


function subStr(str,fontSize){
    if(str.length > fontSize){
    	return str.substring(0,fontSize)+'...';
    }else{
    	return str;
    }
}
//获取最后更新url
function lastUpdateUrl(siteCode){
	$.ajax( {
		type : "POST",
		url : webPath+"/bigDataAnalysis_lastUpdateUrl.action",
		data :{
			orgSiteCode:siteCode,
		},
		dataType:"json",
		async : false,
		success : function(data) {
			var url=data.lastUpdateUrl;
			if(url!=""){
				if(!url.match("http")){
					url="http://"+url;
				}
				window.open(url);
			}
		}
	});

}
$("table tbody tr:odd td").css("background","#fafbfc");
//修改大数据 tabname
function configTabInfo(type){
//	modify 修改页面
//	setting 付费用户 弹框
//	prompt 免费用户  弹框
	var orgSiteCode = $("#siteCodes").val();
	if(type=='modify'){
		$.ajax( {
			type : "POST",
			url : webPath+"/bigAuthDetail_queryBigAuthDetail.action",
			data :{
			},
			dataType:"json",
			async : false,
			success : function(data) {
				$.each(data, function(index, obj) {
					$("#datepicker_start").val(obj.startDate);
					$("#datepicker_end").val(obj.endDate);
					$("#tabName1").val(obj.tabNameOne);
					$("#tabName2").val(obj.tabNameTwo);
					$("#tabName3").val(obj.tabNameThree);
				});
				
			
			}
		});
		$('#modify').modal({});
		$('#setting').modal('hide');
	}else{
		if(isOrgCost==0){
			//免费站点
			$('#prompt').modal({});
		}else if(isOrgCost==1){
			$.ajax( {
				type : "POST",
				url : webPath+"/bigAuthDetail_addBigAuthDetail.action",
				data :{
				},
				dataType:"json",
				async : false,
				success : function(data) {
					$.each(data, function(index, obj) {
						//详情页url
						$("#detailsUrl").html(obj.url);
						$("#datepicker_start").val(obj.startDate);
						$("#datepicker_end").val(obj.endDate);
						$("#tabName1").val(obj.tabNameOne);
						$("#tabName2").val(obj.tabNameTwo);
						$("#tabName3").val(obj.tabNameThree);
						//推荐焦点url
						$("#recommendUrl").html(obj.indexUrl);
						//显示条数
						$("#showNum").val(obj.showNum);
						$("#focusRadio").val(obj.focusType);
						$("#fir-item"+obj.focusType).prop("checked",true);
						$(".active-input_sel_active").removeClass("active-input_sel_active");
						$("#fir-itemSpan"+obj.focusType).addClass("active-input_sel_active");
						$("input[type='radio']").iCheck({
							checkboxClass : 'icheckbox_square-blue',
							radioClass : 'iradio_square-blue'
						});
						
						$("#focusUrlOne").val(obj.focusUrlOne);
						$("#focusUrlTwo").val(obj.focusUrlTwo);
						$("#focusUrlThree").val(obj.focusUrlThree);
						  //焦点推荐单选按钮点击事件
						  $("[name='show-item']").on('ifChecked', function(event){
							  $("#focusRadio").val( $(this).val());
								$(".active-input_sel_active").removeClass("active-input_sel_active");
								$("#fir-itemSpan"+$(this).val()).addClass("active-input_sel_active");
						  });
					});
					
				
				}
			});
			//付费站点
			$('#setting').modal({});
			$('#setting').on('shown.bs.modal', function () {
				  // 执行一些动作...
				dailyCopyUrl();
			})
		}
	}
	
}
//复制链接
function dailyCopyUrl(){
	var configureTabId=$("#configureTabId").val();
	var copyWrite="";
	if(configureTabId==0){
		//焦点推荐
		copyWrite=$("#recommendUrl").text();
	}else if(configureTabId==1){
		//数据详情
		copyWrite=$("#detailsUrl").text();
	}
	$("[name='copyCon']").zclip({
		path: webPath+"/js/ZeroClipboard.swf",
		copy: function(){
			console.info(copyWrite);
		return copyWrite;
		}
//		,
//		beforeCopy:function(){/* 按住鼠标时的操作 */
//			$(this).css("color","orange");
//		},
//		afterCopy:function(){/* 复制成功后的操作 */
//			$(this).css("color","white");
//        }
	});
}
//预览
function dailyOpenUrl(){
//	var url=$("#copyWrite").text();
	var configureTabId=$("#configureTabId").val();
	var url="";
	if(configureTabId==0){
		//焦点推荐
		url=$("#recommendUrl").text();
	}else if(configureTabId==1){
		//数据详情
		url=$("#detailsUrl").text();
	}
	window.open(url);
}
//修改 时间  tabname
function updateBigAuthDetail(updateType){
	//点击重置按钮  //1:修改tab名字和开始时间结束时间 2：修改焦点推荐内容 3：重置焦点推荐内容
	if(updateType==3){
		if(confirm('恢复开普云监管的默认链接和指标设置，确定吗？'))
		{}else{
		   return; 
		}
	}
	var showNum=$("#showNum").val();
	if(updateType==2){
		var flag=isDigit(showNum);
		if(flag==false){
			alert("展示条数不能少于1条、展示条数不能大于500条、或请输入正整数值");
			return;
		}
	}
	
	 var begin=new Date($("#datepicker_start").val().replace(/-/g,"/"));
     var end=new Date($("#datepicker_end").val().replace(/-/g,"/"));
     if(updateType==1){
         //js判断日期
         if(begin-end>0){
            alert("开始日期要在截止日期之前!");  
            return false;
         }else{
        	 $('#modify').modal('hide');
        	//付费站点
    		$('#setting').modal({});
         }
     }

	var orgSiteCode = $("#siteCodes").val();
	var startDate=$("#datepicker_start").val();
	var endDate=$("#datepicker_end").val();
	var tabName1=$("#tabName1").val();
	var tabName2=$("#tabName2").val();
	var tabName3=$("#tabName3").val();
	
	
	var focusRadio=$("#focusRadio").val();
	var focusUrlOne=$("#focusUrlOne").val();
	var focusUrlTwo=$("#focusUrlTwo").val();
	var focusUrlThree=$("#focusUrlThree").val();
	
	$.ajax( {
		type : "POST",
		url : webPath+"/bigAuthDetail_updateBigAuthDetail.action",
		data :{
			"startDate":startDate,
			"endDate":endDate,
			"tabName1":tabName1,
			"tabName2":tabName2,
			"tabName3":tabName3,
			updateType:updateType,
			"bigAuthDetailBean.focusType":focusRadio,
			"bigAuthDetailBean.showNum":showNum,
			"bigAuthDetailBean.focusUrlOne":focusUrlOne,
			"bigAuthDetailBean.focusUrlTwo":focusUrlTwo,
			"bigAuthDetailBean.focusUrlThree":focusUrlThree,
		},
		dataType:"json",
		async : false,
		success : function(data) {
			$.each(data, function(index, obj) {
				//详情页url
				$("#datepicker_start").val(obj.startDate);
				$("#datepicker_end").val(obj.endDate);
				$("#tabName1").val(obj.tabNameOne);
				$("#tabName2").val(obj.tabNameTwo);
				$("#tabName3").val(obj.tabNameThree);
				//显示条数
				$("#showNum").val(obj.showNum);
				$("#focusRadio").val(obj.focusType);
				$("#fir-item"+obj.focusType).prop("checked",true);
				$(".active-input_sel_active").removeClass("active-input_sel_active");
				$("#fir-itemSpan"+obj.focusType).addClass("active-input_sel_active");
				$("input[type='radio']").iCheck({
					checkboxClass : 'icheckbox_square-blue',
					radioClass : 'iradio_square-blue'
				});
				
				$("#focusUrlOne").val(obj.focusUrlOne);
				$("#focusUrlTwo").val(obj.focusUrlTwo);
				$("#focusUrlThree").val(obj.focusUrlThree);
				  //焦点推荐单选按钮点击事件
				  $("[name='show-item']").on('ifChecked', function(event){
					  $("#focusRadio").val( $(this).val());
						$(".active-input_sel_active").removeClass("active-input_sel_active");
						$("#fir-itemSpan"+$(this).val()).addClass("active-input_sel_active");
				  });
			});
			alert("修改成功");
		}
		
	});
}
//显示付费
function showSetting(){
	$('#setting').modal({});
}


//校验是否全由数字组成 
function isDigit(s){ 
	var flag=false;
	var patrn=/^[0-9]{1,20}$/; 
	if (!patrn.exec(s)){ 
		flag= false; 
	}else{
		flag=true ;
	}
	if(flag){
		if(s>=1 && s<=500){
			flag=true ;
		}else{
			flag=false;
		}
	}
	return flag;
}
function queryAllSite(pageSiteCode,sitenum){
	$("#pageSiteCode").val(pageSiteCode);
	$("#sitenum").val(sitenum);
	var html='<table id="table-MH" cellpadding="0" cellspacing="0" class="table bigData-tab" style="margin-bottom:0;">'
	    +'<tr style="border-bottom:none;">'
		+'<th style="width: 200px;text-align:left;">组织单位名称/编码</th>'
		+'<th style="width: 150px;position: relative; text-align:center;">网站不连通<br>（占比）<i class="tab_angle"></i></th>'
		+'<th style="width:150px;position: relative; text-align:center;">首页不可用链接<br>（个）<i class="tab_angle"></i></th>'
		+'<th style="width: 150px;position: relative; text-align:center;">首页不更新<i class="tab_angle"></i></th>'
		+'<th style="width: 150px;position: relative; text-align:center;">总更新量<br>（条）<i class="tab_angle"></i></th></tr></table>';
	$("#content").html(''); 
	$("#content").html(html); 
	var queryAllSiteTitle = [];//
	queryAllSiteTitle.push({"mDataProp": "sitecode","bSortable": false,"sTitle": "组织单位名称/编码", "sClass": "text-left", "bVisible": true, "tabIndex": -8});
	queryAllSiteTitle.push({"mDataProp": "linkerrprop", "sTitle": "网站不连通<br>（占比）","bSortable": true, "sClass": "text-left", "bVisible": true, "tabIndex": -7});
	queryAllSiteTitle.push({"mDataProp": "indexdeadnum", "sTitle": "首页不可用链接<br>（个）","bSortable": true, "sClass": "text-left", "bVisible": true, "tabIndex": -6});
	queryAllSiteTitle.push({"mDataProp": "updatestatus", "sTitle": "首页不更新","bSortable": true, "sClass": "text-left", "bVisible": true, "tabIndex": -5});
	queryAllSiteTitle.push({"mDataProp": "updatenum", "bSortable": true,"sTitle": "总更新量","text-left": "center", "bVisible": true, "tabIndex": -3});
	var table_queryAllSite;
	table_queryAllSite = $("#table-MH").dataTable({
	        "sDom": '<"top"T<"clear">>frt<"toolbar">lip',        
	        "bDestroy": true,
	        "bAutoWidth": true,
	        "bDeferRender": true,
	        "bJQueryUI": true,
	        "searching":false,
	        "sPaginationType": "full_numbers",
	        "iDisplayLength": 10,
	        "aaSorting": [[ 4, "desc"]],
	        "oTableTools": {
	            "sSwfPath": "/boxpro/lib/tableTools/media/swf/copy_csv_xls_pdf.swf",
	            "sRowSelect": "multi"     //可选中行，single单行。multi多行    
	        },
	        "oLanguage": {
	            "sSearch": "查询:",
	            "sLengthMenu": '本页显示 <select>' +
	                '<option value="50">50</option>' +
	                '<option value="100">100</option>'+
	                '<option value="200">200</option>' +
	                '<option value="500">500</option>'+
	                '<option value="1000">1000</option>'+
	                '</select> ',
	            "oPaginate": {
	                "sFirst": "首页",
	                "sLast": "尾页",
	                "sNext": "下一页",
	                "sPrevious": "上一页"
	            },
	            "sInfo": "共 _TOTAL_ 条记录 (当前 _START_ 至 _END_)",
	            "sLoadingRecords": "数据正在加载...",
	            "sInfoFiltered": "",
	            "sInfoEmpty": "共 0 条记录",
	            "sZeroRecords": "暂无数据！"
	        },
	        "bInfo": true,
	        "bPaginate": true,
	        "bRetrieve": true,
	        "bServerSide": true,
	        "bLengthChange": false,
	        "sAjaxSource": webPath+ "/bigDataAnalysis_queryAllSite.action",
	        "sAjaxDataProp": function (data) {
	        	for (var i = 0; i < data.body.length; i++) {
		        	data.body[i]["sitecode"] = "<td class='td_left' align='left'><div class='wz-name' >" +data.body[i]["sitecode"]+"</div></td>";
		        	data.body[i]["linkerrprop"] = "<td class='sort-num'>" +data.body[i]["linkerrprop"]+"</td>";
	        	}
	        	return data.body;
	        },
	        "bProcessing": false,
	        "fnServerData": fnDataTablesPipeline,
	        "aoColumns": queryAllSiteTitle,
	        "fnInitComplete": function () {
	            $("#table_data").parent().parent().removeAttr("style");
	            $("#table_data_filter").css("margin-right", "75%");
//	            $(".DataTables_sort_icon").hide();
	            $("#table-MH_filter").hide();
	            $("#table-MH_length").hide();
	            $("#table-MH_info").hide();
	            var table_dataManager_wrapper = $("#table_data");
	            table_dataManager_wrapper.find("th").css("cursor", "default");
	        },
	        "fnPreDrawCallback": function(oSettings) {
//	            $("#table_unUpdate_channel").siblings(".dropload-load").hide();
	        }
	    });
	
	//列表排序
	new TableSorter("table-MH",1,2,3,4); 
}
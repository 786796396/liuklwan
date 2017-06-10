//
var level = 2;
var showbox=2 ;//  1 代表图表 2 代表列表 
var showTb=1;// 列表表头 1 组织单位  2 填报单位；
var grade =1;// 便于 斜线处的使用
var loginSiteCode = 0;
var databaseTreeInfoId=0;
var dataList;//图表展示的数据
var status = 0;
var siteOrDomain=1;// 1 站点收录网页数  2 域收录网页数

var commonSiteCode;
var comonnWebName;

var clickSiteCode;

var querySiteDataSiteCode ;

var siteCode_result_table_id;
var infoTitle;
var formatIsExp = {
		"1" : "正常",
		"2" : "例外",
		"3" : "关停"
	};
var formatIsScan = {
		"0" : "无发布信息",
		"1" : "",
		"2" : "网站安全配置（例如：安全狗）",
		"3" : "网页格式不规范",
		"4" : "日期格式不规范",
		"5" : "相同IP的站群",
		"6" : "网页格式不规范",
		"7" : "网页格式不规范",
		"8" : "网页格式不规范",
		"9" : "网页格式不规范",
		"10" : "网页格式不规范",
		"11" : "网页格式不规范",
		"12" : "网站打开失败",
		"13" : "网站通知关停",
		"14" : "域名解析失败",
		"15" : "无发布信息",
		"16" : "网络防火墙屏蔽",
		"17" : "中文域名"
};
$(function () {
	loginSiteCode=$("#siteCode").val();
	commonSiteCode = loginSiteCode;
	comonnWebName=$("#webName").val();
	$('.bread').hover(function(){
  		$(this).children('ul').show();
	},function(){
        $(this).children('ul').hide();
	});
	getDatas(commonSiteCode,comonnWebName);
	
	// 初始化 查询框
	initKey();
	//排序
	sortT();
});

//获取下级战群列表数据
function getDatas(sitecode,siteName){
	var keyWord="";
	if(level == 3){
		keyWord=$("#keyWord_id").val();
	}
	$.ajax( {
		type : "POST",
		url : webPath+"/siteDataOverview_getDatas.action",
		data :{
			siteCode:sitecode,
			keyWord:keyWord
		},
		dataType:"json",
		async : false,
		success : function(data) {
			 if(data){
		        	if(data.success == 'true'){
		        		$("#clickk").hide();
		        		status = 0;
		        		dataList = data.list;
		        		level = data.level;
		        		initXian(data,siteName);
		        		//databaseTreeInfoId=data.databaseTreeInfoId;
		        		if(level<3){
		        			$("#keyWord_id").attr("placeholder","输入网站名称或者标识码");
		        			$(".search_box").width(222);
		        			showTable(1);
			        		getBody(1,dataList);
		        		}else{
		        			$("#keyWord_id").attr("placeholder","输入网站名称或者标识码或状态");
		        			$(".search_box").width(256);
		        	        level=3;
			        		showTable(2);
			        		getBody(2,dataList);
		        			
		        		}
		        	}
			    } else {
			    	alert("获取数据失败");
			    }
		},error: function () {//请求失败处理函数
            alert('请求失败');
        }
	});
}
function initXian(data,siteName){
	var html='';
	if(grade == 1){
		var siteName = $("#webName").val();
		html='<span style="cursor: pointer;" onclick="onclickOrg(\''+loginSiteCode+'\',\''+siteName+'\',\''+grade+'\')">'+siteName+'</span>';
		$("#nextNode"+grade).html(html);
		var htmls='';
		if($("#nextNode"+(grade+1)).is(":hidden")){
			$.each(data.list, function(index, obj) {
				htmls+='<li  onclick="onclickOrg(\''+obj.siteCode+'\',\''+obj.name+'\',\''+(grade+1)+'\')">'+obj.name+'</li>';
			});
			$("#nextNodeUl"+(grade+1)).html(htmls);
		}
	}else{
		var  htmlSpan='<span style="cursor: pointer;" ><i class="xie_line2" ></i>'+siteName+'<i></i></span>';
		$("#nextNodeSpan"+grade).html(htmlSpan);
		if($("#nextNode"+(grade+1)).is(":hidden")){
		$.each(data.list, function(index, obj) {
			html+='<li  onclick="onclickOrg(\''+obj.siteCode+'\',\''+obj.name+'\',\''+(grade+1)+'\')">'+obj.name+'</li>';
		});
		$("#nextNodeUl"+(grade+1)).html(html);
		}
	}
	$("#nextNode"+grade).show();
	for(var i=1;i<5;i++){
		if(i>grade){
//			$("#nextNode"+level).html(html);
			$("#nextNode"+i).hide();
		}
	}
}
//点击
function onclickOrg(siteCode,siteName,onclickGrade){
	$("#keyWord_id").attr("placeholder","输入网站名称或者标识码");
	$(".search_box").width(222);
	$("#keyWord_id").val("");
	$("#bodyB").show();
	$("#clickk_paginate").hide();
	//$("#titleB").parent().parent().find("tbody").hide();
	$("#bodyBSort").show();
	 comonnWebName=siteName;
	 commonSiteCode=siteCode;
	if(onclickGrade>grade){
		grade++;
	}else if(onclickGrade<grade){
		grade = onclickGrade;
	}
	
	getDatas(commonSiteCode,comonnWebName);
	//排序
	sortT();
}


//列表表头 1 组织单位  2 填报单位；
function showTable(showTb){
	var html = '';
	if(showTb == 1){
		html+='<th style="width:10%; padding-left: 10px; padding-right: 10px; text-align:left;">序号</th>'+
        '<th style="width:20%; text-align: left;">组织单位名称/编码</th>'+
        '<th class="numOrder" style="width:10%; margin-right: 7.6%;">上报站点  <i class="tab_angle"></i></th>'+
        '<th class="numOrder" style="width:10%; margin-right: 7.6%;">关停站点  <i class="tab_angle"></i></th>'+
        '<th class="numOrder" style="width:10%; margin-right: 7.6%;">例外站点  <i class="tab_angle"></i></th>'+
        '<th class="numOrder" style="width:10%; margin-right: 10%;">暂不监测站点  <i class="tab_angle" style="right: 0px;"></i></th>'+
        '<th class="numOrder" style="width:10%">全面监测站点  <i class="tab_angle" style="right: 12px;"></i></th>';
		$("#titleB").html(html);
	}else if(showTb == 2){
		html+='<th style="width:10%; text-align:left;">序号</th>'+
        '<th style="width:20%; text-align:left;">网站名称</th>'+
        '<th style="width:10%;text-align:center;">网站标识码</th>'+
        '<th style="width:10%; text-align:center;">省</th>'+
        '<th style="width:10%;text-align:center;">市</th>'+
        '<th style="width:10%;text-align:center;">县</th>'+
        '<th style="width:10%;text-align:center;">报送状态</th>';
		$("#titleB").html(html);
	}else if(showTb == 3){
		$("#bodyBSort").hide();
		$("#bodyB").hide();
		$("#clickk").show();
	}
}



//type 1 组织单位的  表身  2 填报单位的  表身
function getBody(type,dataList){
	var str='';
	if(type == 1){//组织单位的  表身
		for ( var i = 0; i < dataList.length; i++) {
			var obj = dataList[i];
			str += "<tr>" +
			"<td style=\"width:10%; padding-left: 10px; padding-right: 10px;\" class=\"font_701999\">"+(i+1)+"</td>" +
			"<td style=\"width:20%; text-align: left;\" class=\"wz-name\">" +
            "<a href=\"javascript:void(0);\" onclick=\onclickOrg(\""+obj.siteCode+"\",\""+obj.name+"\",\""+(grade+1)+"\") class=\"keykey\" style=\"color:#2899df;\">" +
            "<div style=\"margin-bottom: 6px;\">"+obj.name+"</div><div >"+obj.siteCode+"</div>" +
            "</a>" +
            "</td>" +
            "<td class=\"sort-num\" style=\"width:10%; text-align: center;\">"+obj.sumReport+"</td>" +
            "<td class=\"sort-num\" style=\"width:10%; text-align: center; color:#2899df; cursor: pointer;\">" +
            "<a class=\"sort-num\" href=\"javascript:void(0);\" style=\"color:#2899df;\" onclick=\"getSiteList('1','"+obj.siteCode+"')\">"+obj.sumClose+"</a>" +
            "</td>" +
            "<td class=\"sort-num\" style=\"width:10%; text-align: center; color:#2899df; cursor: pointer;\">" +
            "<a class=\"sort-num\" href=\"javascript:void(0);\" style=\"color:#2899df;\" onclick=\"getSiteList('2','"+obj.siteCode+"')\">"+obj.sumException+"</a>" +
            "</td>" +
            "<td class=\"sort-num\" style=\"width:10%; text-align: center; color:#2899df; cursor: pointer;\">" +
            "<a class=\"sort-num\" href=\"javascript:void(0);\" style=\"color:#2899df;\" onclick=\"getSiteList('3','"+obj.siteCode+"')\">"+obj.sumNo+"</a>" +
            "</td>" +
            "<td class=\"sort-num\" style=\"width:10%; text-align: center;\">" +
            "<a class=\"sort-num\" href=\"javascript:void(0);\" style=\"color:#2899df;\" onclick=\"getSiteList('4','"+obj.siteCode+"')\">"+obj.sumNormal+"</a>" +
            "</td>";
			str += "</tr>";
		}
	}else{//填报单位的  表身
		for ( var i = 0; i < dataList.length; i++) {
			var obj = dataList[i];
			var url = "";
			if(obj.jumpUrl != null && obj.jumpUrl != ""){
				url = obj.jumpUrl;
			}else{
				url = obj.url;
			}
			url = checkUrlHTTTP(url);
			if(i%2==1){
				str+='<tr style="background-color: #EFF6FA">';
			}else{
				str+='<tr>';
			}
			str+='<td style="width:10%; text-align:left;" class=\"font_701999\">'+(i+1)+'</td>';
			if(url!=""){
				 str+='<td style="width:20%; text-align:left;" class="keykey">'+"<a href=\""+url+"\"  style=\"color:#2899df; display:block; width:200px;white-space:nowrap; text-overflow:ellipsis; overflow:hidden;\" target=\"_blank\" title='"+obj.name+"'>"+obj.name+"</a>"+'</td>';   ;
			}else{
				 str+='<td style="width:20%; text-align:left;" class="keykey">'+"<a href=\"javascript:void(0);\"   style=\"color:#2899df;display:block; width:200px;white-space:nowrap; text-overflow:ellipsis; overflow:hidden;\" target=\"_blank\" title='"+obj.name+"'>"+obj.name+"</a>"+'</td>';
			}
	        //'<td style="width:20%; text-align:left;" class="keykey">'+obj.name+'</td>';
	        str+='<td style="width:10%;text-align:center;">'+"<a href=\""+webPath+"/fillUnit_gailan.action?siteCode="+obj.siteCode+"\"   style=\"color:#2899df;\" target=\"_blank\">"+obj.siteCode+"</a>"+'</td>'+
	        '<td style="width:10%;text-align:center;">'+obj.province+'</td>'+
	        '<td style="width:10%;text-align:center;">'+obj.city+'</td>'+
	        '<td style="width:10%;text-align:center;">'+obj.county+'</td>'+
	        '<td style="width:10%;text-align:center;">'+formatIsExp[obj.isexp]+'</td>';
			if(status == 3){
				str+='<td style="width:20%;">'+formatIsScan[obj.isScan]+'</td>';
			}
			if(status == 1){
				str+='<td style="width:20%;">'+obj.per+'</td>';
			}
			str+='</tr>';
		}
	}   
	$("#bodys").html(str);
}

// flag 1 关停   2 例外   3 暂不检测    4 全面检测  //默认 0
function getSiteList(flag,sitecode){
	status = flag;
	clickSiteCode = sitecode;
	showTable(3);
	$("#clickk_paginate").show();
	$("#clickk").find("tbody").html("");
	pageChange();
	if(status == 1){//关停
		$("#clickk").dataTable().fnSetColumnVis(-2,false);
		$("#clickk").dataTable().fnSetColumnVis(-1,true);
	}else if(status == 3){//3 暂不检测
		$("#clickk").dataTable().fnSetColumnVis(-1,false);
		$("#clickk").dataTable().fnSetColumnVis(-2,true);
	}else{
		$("#clickk").dataTable().fnSetColumnVis([-1,-2],false);
	}
}

function getInfoTitle(){
	var infoTitl=[];
	infoTitl.push({"mDataProp": "dataNumber", "bSortable": false,"sClass": "", "bVisible": true, "tabIndex": -9});
	infoTitl.push({"mDataProp": "name","bSortable": false,"sClass": "", "bVisible": true, "tabIndex": -8});
	infoTitl.push({"mDataProp": "siteCode","bSortable": false, "sClass": "center", "bVisible": true, "tabIndex": -7});
	infoTitl.push({"mDataProp": "province","bSortable": false, "sClass": "center", "bVisible": true, "tabIndex": -6});
	infoTitl.push({"mDataProp": "city","bSortable": false,"sClass": "center", "bVisible": true, "tabIndex": -5});
	infoTitl.push({"mDataProp": "county","bSortable": false, "sClass": "center", "bVisible": true, "tabIndex": -4});
	infoTitl.push({"mDataProp": "isexp","bSortable": false,"sClass": "center", "bVisible": true, "tabIndex": -3});
	infoTitl.push({"mDataProp": "isScan","bSortable": false,"sClass": "center isScan", "bVisible": true, "tabIndex": -2});
	infoTitl.push({"mDataProp": "remark","bSortable": false,"sClass": "center", "bVisible": true, "tabIndex": -1});
	return infoTitl;
}
function pageChange(){
	infoTitle=getInfoTitle();
	siteCode_result_table_id = $("#clickk").dataTable({
        "sDom": '<"top"T<"clear">>frt<"toolbar">lip',  
        "bDestroy": true,
        "bAutoWidth": false,
        "bDeferRender": true,
        "bJQueryUI": true,
        "searching":false,
        "sPaginationType": "full_numbers",
        "iDisplayLength": 100,
       // "aaSorting": [[ 1, "desc"]],
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
            "sZeroRecords": "组织下不存在站点数据。"
        },
        "bInfo": true,
        "bPaginate": true,
        "bRetrieve": true,
        "bServerSide": true,
        "bLengthChange": false,
        "sAjaxSource": webPath+ "/siteDataOverview_getOtherDatas.action",
        "sAjaxDataProp": function (data) {
        	var pageNo = data.pageNo-1;
        	var pageSize = data.pageSize;
        	var cou = pageNo*pageSize;
        	for (var i = 0; i < data.list.length; i++) {
        		var dataBase = data.list[i];
        		data.list[i].dataNumber = cou+i+1;
				var url = "";
				if(dataBase.jumpUrl != null && dataBase.jumpUrl != ""){
					url = dataBase.jumpUrl;
				}else{
					url = dataBase.url;
				}
				url = checkUrlHTTTP(url);
				if(url!=""){
					data.list[i].name = "<a href=\""+url+"\"  style=\"color:#2899df; display:block; width:200px;white-space:nowrap; text-overflow:ellipsis; overflow:hidden;\" target=\"_blank\" title='"+dataBase.name+"'>"+dataBase.name+"</a>";
				}else{
					data.list[i].name =  "<a href=\"javascript:void(0);\"   style=\"color:#2899df;display:block; width:200px;white-space:nowrap; text-overflow:ellipsis; overflow:hidden;\" target=\"_blank\" title='"+dataBase.name+"'>"+dataBase.name+"</a>";
				}
        		
				data.list[i].siteCode = "<a href=\""+webPath+"/fillUnit_gailan.action?siteCode="+dataBase.siteCode+"\"   style=\"color:#2899df;\" target=\"_blank\">"+dataBase.siteCode+"</a>";
				
				if(data.list[i].isexp !=null ){
        			data.list[i].isexp ="<span>"+formatIsExp[dataBase.isexp]+"</span>";
        		}
				//data.body[i]["county"] = "<td>"+dataBase["county"]+"</td>";
				if(status == 3){
					if(data.list[i].isScan !=null ){
						data.list[i].isScan = "<span>"+formatIsScan[dataBase.isScan]+"</span>";
					}
				}
            	
    		}
            return data.list;
        },
        "bProcessing": false,
        "fnServerData": fnDataTablesPipeline,
        "aoColumns": infoTitle,
        "fnInitComplete": function () {
        	$("#table_data").parent().parent().removeAttr("style");
            $("#table_data_filter").css("margin-right", "75%");
            $(".DataTables_sort_icon").hide();
            $("#clickk_filter").hide();
            $("#clickk_length").hide();
            $("#clickk_info").hide();
            var table_dataManager_wrapper = $("#table_data");
            table_dataManager_wrapper.find("th").css("cursor", "default");
        },
        "fnPreDrawCallback": function(oSettings) {
        	//alert("1");
        }
    });
}

function initKey(){
	//网站名称/标识码查询
	$("#keyWord_id").keyup(function(){
		var searchInfo = $("#keyWord_id").val();
		if(status!=0){
			 getSiteList(status,clickSiteCode);
		}else{
			if($("#clickk").is(":hidden")){
				if(searchInfo==""){
					$("#bodyB tr").show();
//				var num=0;
//				$(".table").find(".wz-name").each(function(index, element) {
//					num+=1;
//					$(this).parents("tr").find(".font_701999").html(num);
//					//$(this).parent().find(".font_701999").html(num);
//				});
				}else{
					var num=0;
					if(level == 3){
						$("#bodyB").find(".keykey").each(function(index, element) {
							if($(this).text().indexOf(searchInfo)<0 && $(this).next().text().indexOf(searchInfo)<0 && $(this).next().next().next().next().next().text().indexOf(searchInfo)<0){
								$(this).parents("tr").hide();
							}else{
								num+=1;
								if(num%2==0){ 
									$(this).parents("tr").css("background-color","#EFF6FA");
								}else{
									$(this).parents("tr").css("background-color","white");
								}
								$(this).parents("tr").show();
								$(this).parents("tr").find(".font_701999").html(num);
							}
						});
					}else{
						$("#bodyB").find(".keykey").each(function(index, element) {
							if($(this).text().indexOf(searchInfo)<0){
								$(this).parents("tr").hide();
							}else{
								num+=1;
								$(this).parents("tr").show();
								$(this).parents("tr").find(".font_701999").html(num);
							}
						});
					}
					
				}
			}
		}
	});
}
function exportDate(){
	var keyWord = "";
	var site=commonSiteCode;
	var tt=1;
	if(status ==0 && level !=3){//组织单位  显示的是统计的数据
		tt=1;
	}else if(status !=0 && level !=3){// 点击了  关停  例外  等
		tt=2;
		keyWord = $("#keyWord_id").val();
		site=clickSiteCode;
	}else{// level 
		keyWord = $("#keyWord_id").val();
		tt=3;
	}
	window.location.href=webPath+"/siteDataOverview_exportData.action?keyWord="+keyWord+"&siteCode="+site+"&flag="+tt+"&status="+status;
}
function sortT(){
	new TableSorter("bodyB",2,3,4,5,6);
	$("#bodyBSort th").on('click', function(event){
		//alert("2");
		if($(this).find(".tab_angle").hasClass("tab_angle_bottom")){
			
			$("#bodyB .tab_angle").attr("class","tab_angle");
			$(this).find(".tab_angle").addClass("tab_angle_top");
			$(this).find(".tab_angle").removeClass("tab_angle_bottom");
		}else{
			$("#bodyB .tab_angle").attr("class","tab_angle");
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
}

$(function () {
	//列表检索
    $("#keyId").keyup(function(){
        var searchInfo=$("#keyId").val();
		 if(searchInfo==""){
			 $("#linkHomeTbody tr").show();
		 }else{
			 $("#linkHomeTbody").find(".cor-01a5ec").each(function(index, element) {
				 if(($(this).html().indexOf(searchInfo)<0) && ($(this).parent().parent().find(".cor-01a5ec").html().indexOf(searchInfo)<0)){
					 $(this).parents("tr").hide();  
				 }else{
					 $(this).parents("tr").show();
				 }
			});
		 }
    });

$("#startDate").datepicker({
	inline : true,
	showOn: "both",
    buttonImage: webPath+"/images/date.png",
	buttonImageOnly: true,
	numberOfMonths : 1,// 显示几个月
	showButtonPanel : true,// 是否显示按钮面板
	dateFormat : 'yy-mm-dd',// 日期格式
	clearText : "清除",// 清除日期的按钮名称
	closeText : "关闭",// 关闭选择框的按钮名称
	yearSuffix : '年', // 年的后缀
	showMonthAfterYear : true,// 是否把月放在年的后面
	defaultDate : $("#yesterdayId").val(),// 默认日期
	minDate : GetDateStr(-90),//最小日期
	maxDate : GetDateStr(-1),// 最大日期
	monthNames : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月',
			'9月', '10月', '11月', '12月' ],
	dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
	dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
	dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
	onSelect : function(dateText, inst){
		linkHomeTable();
	}
}); 

$("#endDate").datepicker({
	inline : true,
	showOn: "both",
    buttonImage: webPath+"/images/date.png",
	buttonImageOnly: true,
	numberOfMonths : 1,// 显示几个月
	showButtonPanel : true,// 是否显示按钮面板
	dateFormat : 'yy-mm-dd',// 日期格式
	clearText : "清除",// 清除日期的按钮名称
	closeText : "关闭",// 关闭选择框的按钮名称
	yearSuffix : '年', // 年的后缀
	showMonthAfterYear : true,// 是否把月放在年的后面
	defaultDate : $("#yesterdayId").val(),// 默认日期
	minDate : GetDateStr(-90),//最小日期
	maxDate : GetDateStr(-1),// 最大日期
	monthNames : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月',
			'9月', '10月', '11月', '12月' ],
	dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
	dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
	dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
	onSelect : function(dateText, inst){
		linkHomeTable();
	}
});

var yesterday = $("#yesterdayId").val();
$("#startDate").val(yesterday);
$("#endDate").val(yesterday);

linkHomeTable();

 $("#table-linkHomeSort th").on('click', function(event){
		if($(this).find(".tab_angle").hasClass("tab_angle_bottom")){
			$("#table-linkHome .tab_angle").attr("class","tab_angle");
			$(this).find(".tab_angle").addClass("tab_angle_top");
			$(this).find(".tab_angle").removeClass("tab_angle_bottom");
		}else{
			$("#table-linkHome .tab_angle").attr("class","tab_angle");
			$(this).find(".tab_angle").addClass("tab_angle_bottom");
			$(this).find(".tab_angle").removeClass("tab_angle_top");
		}
	  });

});

/**
 * ul
 * @param id
 */
function selectShow(id){
    $("#"+id+"Ul li").click(function(){
        $("#"+id+"Val").val(this.value);
        $("#"+id+"Text").html($(this).html());
        $("#"+id+"Ul").hide();
        linkHomeTable();
    });
}

function linkHomeChange(){
	linkHomeTable();
}

function linkHomeTable(){
	$("#linkHomeTbody").html(copyInformation(2,null));
	var siteType = $("#siteTypeVal").val();
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	if(startDate > endDate){
   		alert("开始时间不能大于结束时间,请重新选择");
   	}else{
   	 $.ajax( {  
         type : "POST",  
         url : webPath+"/linkHome_linkHomeIndexOrgTable.action",  
         data : {
        	 siteType:siteType,
        	 startDate:startDate,
        	 endDate:endDate
         },  
         dataType:"json",
         async : false,  
         success : function(data) {
        	 if(data.success == 'true'){
        		 $("#linkHomeTbody").html("");
        		 $("#table-Sum").html("");
        		 $("#sizeId").html("");
        		 var html = '';
        		 var sumHtml = '';
        		 var list = data.body;
        		 if(list.length > 0 && list != null){
        			 
        			 $("#sizeId").html(data.size);
        			 sumHtml+='<tr>';
        			 sumHtml+='<td class="summary-td padl-18 w15p">汇总<i class="summary-icon"></i></td>';
        			 sumHtml+='<td class="td-center w20p"><i class="td_num td_num_57a1f2">'+data.homeUrlNum+'</i></td>';
        			 sumHtml+='<td class="td-center w20p"><i class="td_num td_num_0ad69f">'+data.linkUrlNum+'</i></td>';
        			 sumHtml+='<td class="td-center w20p"><i class="td_num td_num_0ad69f">'+data.websiteUrlSum+'</i></td>';
        			 sumHtml+='<td class="td-center w20p"><i class="td_num td_num_0ad69f">'+data.percentageThan+'</i></td>';
        			 sumHtml+=' </tr>';
        			 $("#table-Sum").append(sumHtml);
        			 
        			 var uri = webPath + "/fillUnit_gailan.action";
        			 var uriThree = webPath + "/linkHome_linkHomeIndex.action";
        			 
        			 $.each(list, function(index, obj) {
        				 var url = obj.url;
        				 if(url != ""){
        					 if(!url.match("http")){
     	        				 url="http://"+url;
     	        			 }
        				 }
        				
        				 html+='<tr>';
        				 html+='<td class="td-left first-row wz-name w15p"><a onClick="changeCookie(\''+2+'\',\''+uri+'\',\''+null+'\',\''+uriThree+'\');" href="'+webPath+'/linkHome_linkHomeIndex.action?siteCode='+obj.siteCode+'" target="_blank" class="cor-01a5ec">'+obj.siteName+'<br>'+obj.siteCode+'</a></td>';
        				 html+='<td class="td-center w20p"><a href="'+url+'" target="_blank" class="sort-num cor-01a5ec url">'+url+'</a></td>';
        				 html+='<td class="td-center sort-num w20p">'+obj.indexlinknum+'</td>';
        				 html+='<td class="td-center sort-num w20p">'+obj.websiteSum+'</td>';
        				 html+='<td class="td-center sort-num w20p"><span class="sort-num">'+obj.percentage+'</span>%</td>';
        				 html+='</tr>';                 
        			 });
        			 $("#linkHomeTbody").append(html);
        		 }else{
        			 var text = copyInformation(1,"未发现问题");
        			 $("#linkHomeTbody").append(text);
        		 }
        	 }
         },
         error:function(data){
 			alert(data.errorMsg);
 		 }
        });
   	 
   //列表排序
	 new TableSorter("table-linkHome",2,3,4);
   	}

}

function linkHomeTableExcel(){
	var siteType = $("#siteTypeVal").val();
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	if(startDate > endDate){
   		alert("开始时间不能大于结束时间,请重新选择");
   	}else{
   		window.location.href = webPath + "/linkHome_linkHomeIndexOrgTableExcel.action?siteType=" 
   								+ siteType + "&startDate=" + startDate + "&endDate=" + endDate;
   	}
}


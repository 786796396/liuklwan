var ch = 14;
$(function () {
	   $("input[name='checkboxId']").iCheck({
	    	checkboxClass : 'icheckbox_square-blue',
	    	radioClass : 'iradio_square-blue'
	    });	
	   //复选框  
	 	$("[name='checkboxId']").on('ifChanged', function(event){
	 		var checkType = $(this).prop("checked");
	 		if(checkType){
	 			ch = 14;
	 		}else{
	 			ch = "";
	 		}
	 		securityHomeTable();
	 	});
	//列表检索
    $("#keyId").keyup(function(){
        var searchInfo=$("#keyId").val();
		 if(searchInfo==""){
			 $("#securityHomeTbody tr").show();
		 }else{
			 $("#securityHomeTbody").find(".cor-01a5ec").each(function(index, element) {
				 if(($(this).html().indexOf(searchInfo)<0) && ($(this).parent().parent().find(".cor-01a5ec").html().indexOf(searchInfo)<0)){
					 $(this).parents("tr").hide();  
				 }else{
					 $(this).parents("tr").show();
				 }
			});
		 }
    });
    
$("#scanDate").datepicker({
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
		securityHomeTable();
	}
}); 


var yesterday = $("#yesterdayId").val();
$("#scanDate").val(yesterday);

securityHomeTable();

$("#table-securityHomeSort th").on('click', function(event){
	if($(this).find(".tab_angle").hasClass("tab_angle_bottom")){
		$("#table-securityHome .tab_angle").attr("class","tab_angle");
		$(this).find(".tab_angle").addClass("tab_angle_top");
		$(this).find(".tab_angle").removeClass("tab_angle_bottom");
	}else{
		$("#table-securityHome .tab_angle").attr("class","tab_angle");
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
        securityHomeTable();
    });
}

function securityHomeChange(){
	securityHomeTable();
}

function securityHomeTable(){
	$("#securityHomeTbody").html(copyInformation(2,null));
	var siteType = $("#siteTypeVal").val();
	var scanDate = $("#scanDate").val();
   	 $.ajax( {  
         type : "POST",  
         url : webPath+"/securityHomeChannel_securityHomeChannelTable.action",  
         data : {
        	 siteType:siteType,
        	 twoWeeks:ch,
        	 scanDate:scanDate
         },  
         dataType:"json",
         async : false,  
         success : function(data) {
        	 if(data.success == 'true'){
        		 $("#securityHomeTbody").html("");
        		 $("#sizeId").html("");
        		 var html = '';
        		 var list = data.body;
        		 if(list.length > 0 && list != null){
        			 
        			 $("#sizeId").html(data.size);
        			 
        			 var uri = webPath + "/fillUnit_gailan.action";
        			 var uriThree = webPath + "/homeInfoUnUpdate_index.action";
        			 
        			 $.each(list, function(index, obj) {
        				 var url = obj.url;
        				 if(url != ""){
        					 if(!url.match("http")){
     	        				 url="http://"+url;
     	        			 }
        				 }
        				
        				 var ahtml = "";
        				 
        				 if(obj.notDay == -1){ //未知
        					 ahtml += "<td class='td-center w10p'><font color='black'>未知</font></td>";
        				 }else{
        					 if(obj.notDay > 14){ //超过两周
            					 ahtml += "<td class='td-center w10p'><font color='red'>超过两周</font></td>";
            				 }else{ //正常更新
            					 ahtml += "<td class='td-center w10p'><font color='black'>正常更新</font></td>";
            				 }
        				 }
        				
        				 html+='<tr>';
        				 html+='<td class="td-left first-row wz-name w15p"><a onClick="changeCookie(\''+2+'\',\''+uri+'\',\''+null+'\',\''+uriThree+'\');" href="'+webPath+'/homeInfoUnUpdate_index.action?siteCode='+obj.siteCode+'&scanDate='+scanDate+'" target="_blank" class="cor-01a5ec">'+obj.siteName+'<br>'+obj.siteCode+'</a></td>';
        				 html+='<td class="td-center w20p"><a href="'+url+'" target="_blank" class="cor-01a5ec url">'+url+'</a></td>';
        				 html+='<td class="td-center w10p">'+obj.lastUpdateTime+'</td>';
        				 html+='<td class="td-center sort-num w10p">'+obj.unUpdateDays+'</td>';
        				 html+= ahtml;
        				 html+='</tr>';                 
        			 });
        			 $("#securityHomeTbody").append(html);
        		 }else{
        			 var text = copyInformation(1,"未发现问题");
        			 $("#securityHomeTbody").append(html);
        		 }
        	 }
         },
         error:function(data){
 			alert(data.errorMsg);
 		 }
        });
    	//列表排序
 	new TableSorter("table-securityHome",3);

}

function securityHomeTableExcel(){
	var siteType = $("#siteTypeVal").val();
	var scanDate = $("#scanDate").val();
   		window.location.href = webPath + "/securityHomeChannel_securityHomeChannelTableExcel.action?siteType=" 
   								+ siteType + "&scanDate=" + scanDate + "&twoWeeks=" + ch;
}


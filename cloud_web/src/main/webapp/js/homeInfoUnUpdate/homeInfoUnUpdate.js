$(function(){ 
    $(".tab_box1 table tr:odd td").css("background","#fafbfc");
    
    
    
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
	});
	/*============================
	@author:datepicker
	@time:2015-10-20
	============================*/
	
	$("#datepicker").datepicker({//添加日期选择功能
		inline: true,
        showOn: "both",
        buttonImage: webPath+"/images/date.png",
        buttonImageOnly: true,
		onSelect: function(dateText, inst) {
			initPage(dateText);
		},
		numberOfMonths:1,//显示几个月  
		showButtonPanel:true,//是否显示按钮面板  
		dateFormat: 'yy-mm-dd',//日期格式  
		clearText:"清除",//清除日期的按钮名称  
		closeText:"关闭",//关闭选择框的按钮名称  
		yearSuffix: '年', //年的后缀  
		showMonthAfterYear:true,//是否把月放在年的后面  
		defaultDate:GetDateStr(-1),//默认日期
		maxDate:GetDateStr(-1),//最大日期  
		monthNames: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],  
		dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
		dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
		dayNamesMin: ['日','一','二','三','四','五','六']
	});
	
	
	/*============================
	@author:图片弹窗
	@time:2015-10-15
	============================*/
	
	
	var scrollTop=0;
	$('#imgzoomModal').on('show.bs.modal', function () {
	  scrollTop=$(document).scrollTop();
	  $(".page-wrap").addClass("page-fixed");
	  $(document).scrollTop(0);
	});
	$('#imgzoomModal').on('hidden.bs.modal', function () {	
	  $(".page-wrap").removeClass("page-fixed");
	  $(document).scrollTop(scrollTop);
	});
	
});
function initPage(timeDate) {
			var jsonObj = {};
			jsonObj["timeDate"] = timeDate;
			$.ajax({
				async : true,
				type : "GET",
				url : webPath+ "/homeInfoUnUpdate_getTables.action?timeDate=" + timeDate ,
				dataType : "json",
				contentType : "application/text"
			}).done(function(response) {
				$("#towWeekUpNumber").html(response.towWeekUpNumber);
				$("#lastUpdateDate").html(response.lastUpdateDate);
				if(response.unUpdateDay<=14){
					$("#unUpdateDay").html('<div class="alert alert-success"><i></i>恭喜您，首页信息更新很及时 !</div>');
				}else if(response.unUpdateDay>14){
					$("#unUpdateDay").html('<div class="alert alert-warning"><i></i>首页已经超过2星期未更新了，请及时更新！</div>');
				}
				$("#scanDate").html(response.scanDate);
				//var htmlAccesDate=   "<a href='javascript:void(0)' onclick=showConnectionDialog('"+list[i].siteCode+ "','"+ list[i].lastAccessDate+ "')>"+ list[i].lastAccessDate + "</a></td>";
				var htmlAccesDate="<a href='javascript:void(0)' onclick=showConnectionDialog('"+response.siteCodeResult+"','"+response.lastAccessDate+"','"+response.scanDate+"')>"+response.lastAccessDate+"</a>";
				$("#lastAccessDate").html(htmlAccesDate);
				//$("#lastAccessDate").html("<a href='javascript:void(0)' onclick='showConnectionDialog('+response.siteCodeResult,response.lastAccessDate,response.scanDate)">"+response.lastAccessDate"</a>");
				$("#imgzoomMax").attr("src",response.imgMax);
				$("#minImg").attr("src",response.imgUrl);
			}).fail(function(data) {
				alert("当前日期没有监测数据！");
			}).always(function() {

			});
    }
//快照页面
function getShot(imgUrl){
	if(isEmpty(imgUrl)){
		alert("暂时没有快照，无法查看！");
		return;
	}
	window.open(imgUrl);
}
/*
 * 验证是否为空
 */
function isEmpty(str) {
    var isOK = false;
    if (str == undefined || str == 'undefined' || str == "" || str == "null") {
        isOK = true;
    }
    return isOK;
}
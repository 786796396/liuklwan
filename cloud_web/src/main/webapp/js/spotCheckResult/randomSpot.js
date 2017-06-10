//例外省份（广东），针对抽查更改为考评
var site_code_session = $("#site_code_session").val();

/**
 * 统计每个省站点个数--不包含例外和关停网站
 * @param shengCode
 */
function getSiteCount(shengCode,shengName){
	if($("#li_scope_select_id").html().indexOf(shengName)<0){
		$.ajax({
	        async: false,
	        cache: false,
	        type: 'POST',
	        dataType: "json",
	        contentType : "application/text",
	        url: webPath + "/spotCheckResult_getSiteCount.action?shengCode="+shengCode,
	        error: function (data) {// 请求失败处理函数
	            alert(data.errorMsg);
	        },success: function (data){
	        	if(data["errorMsg"]){
	        		alert(data["errorMsg"]);
	        	}else{
	                var siteStr="";
	                siteStr="<div class='close-tip' onclick='deleteSite(this)'>"+shengName+"("+data["siteCount"]+")<i></i><span style='display: none'>"+shengCode+"</span><p style='display: none'>"+data["siteCount"]+"</p></div>";
	                $("#li_scope_select_id").append(siteStr);
	        	}
			}
		});
	}
}
//统计所有每个省站点个数--不包含例外和关停网站
var clickNum = 0;
function getAllSiteCount(){
	var allSite = "11,31,12,50,21,22,23,13,14,15,41,42,43,44,45,46,32,33,34,35,36,37,61,62,63,64,65,51,52,53,54,BT";
	if(clickNum%2==0){
		$.ajax({
	        async: false,
	        cache: false,
	        type: 'POST',
	        dataType: "json",
	        contentType : "application/text",
	        url: webPath + "/spotCheckResult_getAllSiteCount.action?shengCodes="+allSite,
	        error: function (data) {// 请求失败处理函数
	        	alert(data.errorMsg);
	        },success: function (data){
	        	var siteStr="";
	        	for(var i=0;i<data["databaseInfo"].length;i++){
	        		var siteNames = "";
	        		if(data["databaseInfo"][i]["province"].indexOf("内蒙古")>=0){
	        			siteNames = "内蒙古";
	        		}else if(data["databaseInfo"][i]["province"].indexOf("黑龙江")>=0){
	        			siteNames = "黑龙江";
	        		}else if(data["databaseInfo"][i]["province"].indexOf("新疆生产")>=0){
	        			siteNames = "新疆兵团";
	        		}else{
	        			siteNames = data["databaseInfo"][i]["province"].substring(0,2);
	        		}
	        		if($("#li_scope_select_id").html().indexOf(siteNames)<0){
	        			siteStr+="<div class='close-tip' onclick='deleteSite(this)'>"+siteNames+"("+data["databaseInfo"][i]["spotCount"]+")<i></i><span style='display: none'>"+data["databaseInfo"][i]["orgCode"]+"0000</span><p style='display: none'>"+data["databaseInfo"][i]["spotCount"]+"</p></div>";
	        		}
	        	}
	        	$("#li_scope_select_id").append(siteStr);
			}
		});
	}else{
		$("#li_scope_select_id").html("");
	}
	clickNum+=1;
}

/**
 * 删除选中的省份
 */
function deleteSite(obj){
	$(obj).remove();
}




/**
 * 输入随机抽查百分比，获取对应条件的抽查网站个数
 */
function spotRandomCount(){
	//获取选中的省份
	var checkCodeStr="";
	var spanList=$("#li_scope_select_id").find("span");
	$.each(spanList,function(index,sp){
		var shengCode=$(this).html().substring(0,2);
		checkCodeStr+=shengCode+",";
	});
	var checkCodeNumStr="";
	var pList=$("#li_scope_select_id").find("p");
	$.each(pList,function(index,sp){
		var shengCodeNum=$(this).html();
		checkCodeNumStr+=shengCodeNum+",";
	});
	
	//获取页面站点级别
	var level="";
	$("[name='level']").each(function(){
		if($(this).prop("checked")){
			level+=$(this).val()+",";
		}
	});
	//获取门户类型
	var isorg="";
	$("[name='isorg-class']").each(function (){
		if($(this).prop("checked")){
			isorg+=$(this).val()+",";
		}
	});
	
	//抽查百分比
	var spotPer=$("#spot_per_id").val();
	
	/**
	 * 非空校验
	 */
	if(checkCodeStr==null || checkCodeStr=="" || checkCodeStr==","){
		if(site_code_session == "440000"){
			alert("考评范围不能为空");
		}else{
			alert("抽查范围不能为空");
		}
		return;
	}
	if(level==null || level=="" || level==","){
		alert("站点级别不能为空");
		return;
	}
	if(isorg==null || isorg=="" || isorg==","){
		alert("门户类型不能为空");
		return;
	}
	if(spotPer==null || spotPer==""){
		if(site_code_session == "440000"){
			alert("考评比例不能为空");
		}else{
			alert("抽查比例不能为空");
		}
		return;
	}else{
		if(isNaN(spotPer)){
			alert('请输入0到100的数字');
			var spotPer=$("#spot_per_id").val("");
			return false;
		}else{
			if(spotPer<=100){
				if(spotPer.indexOf(".")!=-1){
					var spotPers = spotPer.split(".");
					if(spotPers[1].length>1){
						alert('请输入到小数点后一位');
						var spotPer=$("#spot_per_id").val("");
						return false;
					}
				}
			}else{
				alert('请输入0到100的数字');
				var spotPer=$("#spot_per_id").val("");
				return false;
			}
			
		}/*else{
			var r = /^([1-9]\d?(\.\d{1,2})?|0\.\d{1,2}|100)$/;
			if(!r.test(spotPer)){
				alert('请输入0到100的数字');
				return false;
			} 
		}*/
	}
	
	var jsonObj={
		"level":level,
		"isorg":isorg,
		"spotPer":spotPer,
		"checkCodeStr":checkCodeStr,
		"checkCodeNumStr":checkCodeNumStr
	};
    $.ajax({
        async: false,
        cache: false,
        type: 'POST',
        data: JSON.stringify(jsonObj),
        contentType: "application/json",
        dataType: "json",
        url: webPath + "/spotCheckResult_randomCount.action",
        error: function (data) {// 请求失败处理函数
            alert(data.errorMsg);
        },success: function (data){
        	if(data["errorMsg"]){
        		alert(data["errorMsg"]);
        	}else{
        		$("#spot_per_count_id").html(data["spotCount"]);
        	}
		}
  });
}


/**
 * 随机抽查数据校验
 */
/*function verifyRandom(){*/
$("#spot_RandomCheck_button").click(function(){
    var spotPerCount=$("#spot_per_count_id").html();
    var spotAdvancedSum=$("#spotSum").html();
    var spotAdvancedNum=$("#spotNum").html();
    var taskName=$("#taskName").val();//任务名称
	//获取选中的省份
	var checkCodeStr="";
	var spanList=$("#li_scope_select_id").find("span");
	$.each(spanList,function(index,sp){
		var shengCode=$(this).html().substring(0,2);
		checkCodeStr+=shengCode+",";
	});
	var checkCodeNumStr="";
	var pList=$("#li_scope_select_id").find("p");
	$.each(pList,function(index,sp){
		var shengCodeNum=$(this).html();
		checkCodeNumStr+=shengCodeNum+",";
	});
	//获取页面站点级别
	var level="";
	$("[name='level']").each(function(){
		if($(this).prop("checked")){
			level+=$(this).val()+",";
		}
	});
	
	//获取门户类型
	var isorg="";
	$("[name='isorg-class']").each(function (){
		if($(this).prop("checked")){
			isorg+=$(this).val()+",";
		}
	});
	
	//抽查百分比
	var spotPer=$("#spot_per_id").val();
	
	/**
	 * 非空校验
	 */
	if(spotPerCount > (spotAdvancedSum - spotAdvancedNum)){
		if(site_code_session == "440000"){
			alert("高级版剩余考评数量不足");
		}else{
			alert("高级版剩余抽查数量不足");
		}
		return false;
	}
    
    if (!taskName) {
        alert("请选择输入任务名称");
        return false;
    }
    if (taskName.length>25) {
        alert("任务名称长度超过25");
        return false;
    }
    
	if(checkCodeStr==null || checkCodeStr=="" || checkCodeStr==","){
		if(site_code_session == "440000"){
			alert("考评范围不能为空");
		}else{
			alert("抽查范围不能为空");
		}
		return false;
	}
	if(level==null || level=="" || level==","){
		alert("站点级别不能为空");
		return false;
	}
	if(isorg==null || isorg=="" || isorg==","){
		alert("门户类型不能为空");
		return false;
	}
	if(spotPer==null || spotPer==""){
		if(site_code_session == "440000"){
			alert("考评比例不能为空");
		}else{
			alert("抽查比例不能为空");
		}
		return false;
	}else{
		if(isNaN(spotPer)){
			alert('请输入0到100的数字');
//			aa.value="";
			var spotPer=$("#spot_per_id").val("");
			return false;
		}else{
			/*var r = /^([1-9]\d?(\.\d{1,2})?|0\.\d{1,2}|100)$/;
			if(!r.test(spotPer)){
				alert('请输入0到100的数字');
				return false;
			}*/
			if(spotPer<=100){
				if(spotPer.indexOf(".")!=-1){
					var spotPers = spotPer.split(".");
					if(spotPers[1].length>1){
						alert('请输入到小数点后一位');
						var spotPer=$("#spot_per_id").val("");
						return false;
					}
				}
			}else{
				alert('请输入0到100的数字');
				var spotPer=$("#spot_per_id").val("");
				return false;
			}
		}
	}
});

$("#choucModalRandom").on('show.bs.modal', function () {
    $('#choucModalRandom').css("z-index","1052");
    $(".modal-backdrop").css("z-index","1051");
});
$('#modalHideRandom').click(function(){
    $('#choucModalRandom').modal('hide');
    $(".modal-backdrop").css("z-index","1040");
});
$('#modalHideCopy').click(function(){
	$('#choucModalCopy').modal('hide');
	$(".modal-backdrop").css("z-index","1040");
});
/**
 * 随机抽查页面--生成抽查列表--添加点击事件
 */
//function spotRandom(){
$("#certainIdRandom").click(function(){
	
    $("#main-center").hide();//抽查首页面隐藏
    $("#new_task_div").hide();//新建任务隐藏
    $("#spot_submit_div_id").show();//抽查提交页面显示
    
    //隐藏确认提示框
    $("#choucModalRandom").hide();
    $(".modal-backdrop").hide();
    
    var batchNum=$("#batchNum option:selected").text();//批次
    var groupNum=$("#batchNum option:selected").val();//组次
    var taskName=$("#taskName").val();//任务名称
    var scheduleId = $("#scheduleId").val();//抽查进度表id
	
	//获取选中的省份
	var checkCodeStr="";
	var spanList=$("#li_scope_select_id").find("span");
	$.each(spanList,function(index,sp){
		var shengCode=$(this).html().substring(0,2);
		checkCodeStr+=shengCode+",";
	});
	var checkCodeNumStr="";
	var pList=$("#li_scope_select_id").find("p");
	$.each(pList,function(index,sp){
		var shengCodeNum=$(this).html();
		checkCodeNumStr+=shengCodeNum+",";
	});
	//获取页面站点级别
	var level="";
	$("[name='level']").each(function(){
		if($(this).prop("checked")){
			level+=$(this).val()+",";
		}
	});
	
	//获取门户类型
	var isorg="";
	$("[name='isorg-class']").each(function (){
		if($(this).prop("checked")){
			isorg+=$(this).val()+",";
		}
	});
	
	//抽查百分比
	var spotPer=$("#spot_per_id").val();

	var jsonObj={
        "dateStart": $("#datepickerStart").val(),
        "dateEnd": $("#datepickerEnd").val(),
        "groupNum":groupNum,
        "batchNum":batchNum,
        "scheduleId":scheduleId,
        "taskName":taskName,
        "isorganizational":2,
		"level":level,
		"isorg":isorg,
		"spotPer":spotPer,
		"checkCodeStr":checkCodeStr,
		"checkCodeNumStr":checkCodeNumStr
	};
    $.ajax({
        async: false,
        cache: false,
        type: 'POST',
        data: JSON.stringify(jsonObj),
        contentType: "application/json",
        dataType: "json",
        url: webPath + "/spotCheckResult_randomSite.action",
        error: function (data) {// 请求失败处理函数
            alert(data.errorMsg);
        },success: function (data){
        	if(data.errorMsg){
        		alert(data.errorMsg);
        	}else{
        		
        		//页面数据恢复
        		$("#li_scope_select_id").html("");//清空选择的省份
        		//站点级别清空
        		$(".level").each(function(){
        			if($(this).attr("checked")=="checked"){
        				$(this).removeAttr("checked");
        			}
        		});
        		//门户选择清空
        		$(".isorg-class").each(function (){
        			if($(this).attr("checked")=="checked"){
        				$(this).removeAttr("checked");
        			}
        		});
        		//抽取比例清空
        		$("#spot_per_id").val("");
        		
        		//抽取个数设置为0
        		$("#spot_per_count_id").html("0");
        		
        		//抽查提交页面数据初始化
        		spotSubmitInit(batchNum,groupNum,scheduleId);
        	}
		}
  });
});

	
	
	
	

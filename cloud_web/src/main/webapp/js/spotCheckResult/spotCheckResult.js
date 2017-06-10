$(function () {
	
	 $('.every_tip').click(function(){
	        var tabid = $(this).index();
	        $('.every_tip').removeClass('on');
	        $(this).addClass('on');
			$("#tabId").val(tabid);
			
			$('.every_tabs').removeClass('on');
			$('.every_tabs').eq(tabid).addClass('on');
			changeTab(tabid);
			if(tabid == 2 ){
				$(".msg").show();
			}else{
				$(".msg").hide();
			}
			
			
	    });
	
	$('.list-box').mouseover(function(){
		$(this).find('input[type=text]').addClass('on');
		$(this).find('.small-box').addClass('on');
		$(this).find('.small-box2').addClass('on');
		$(this).find('.small-box3').addClass('on');
		$(this).find('.small-box4').addClass('on');
		$(this).find('.small-box5').addClass('on');
		$(this).find('.small-box6').addClass('on');
		$(this).find('.small-box7').addClass('on');
		
	});
	
	$('.list-box').mouseout(function(){	
		$(this).find('input[type=text]').removeClass('on');
		$(this).find('.small-box').removeClass('on');
		$(this).find('.small-box2').removeClass('on');
		$(this).find('.small-box3').removeClass('on');
		$(this).find('.small-box4').removeClass('on');
		$(this).find('.small-box5').removeClass('on');
		$(this).find('.small-box6').removeClass('on');
		$(this).find('.small-box7').removeClass('on');
	});
	
	
    hasInitDatas = {};
    $("#treeScrollbar").mCustomScrollbar({
        theme: "dark",
        autoDraggerLength: true,
        advanced: {
            updateOnContentResize: true,
            updateOnBrowserResize: true,
            autoHideScrollbar: true
        }
    });
    // 滚动条
    $(".modal-tree-selected").mCustomScrollbar({
        theme: "dark",
        autoDraggerLength: true,
        advanced: {
            updateOnContentResize: true,
            updateOnBrowserResize: true,
            autoHideScrollbar: true
        }
    });
    $(".prependedInput").iePlaceholder();

    Date.prototype.Format = function (fmt) { //author: meizz 
        var o = {
            "M+": this.getMonth() + 1, //月份 
            "d+": this.getDate(), //日 
            "h+": this.getHours(), //小时 
            "m+": this.getMinutes(), //分 
            "s+": this.getSeconds(), //秒 
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
            "S": this.getMilliseconds() //毫秒 
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    };

    /*
     * ============================ @author:日历 @time:2015-12-04
     * ============================
     */
    $("#datepickerStart").datepicker(
        {// 添加日期选择功能
            dateFormat: 'yy-mm-dd',// 日期格式
            showMonthAfterYear: true,// 是否把月放在年的后面
            minDate: 0,
            //maxDate: "+1M",// 最大日期
            monthNames: [ '1月', '2月', '3月', '4月', '5月', '6月',
                '7月', '8月', '9月', '10月', '11月', '12月' ],
            dayNames: [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五',
                '星期六' ],
            dayNamesShort: [ '周日', '周一', '周二', '周三', '周四', '周五',
                '周六' ],
            dayNamesMin: [ '日', '一', '二', '三', '四', '五', '六' ],
            onSelect: function( startDate ) {
                var $startDate = $( "#datepickerStart" );
                var $endDate = $('#datepickerEnd');
                var endDate = $endDate.datepicker( 'getDate' );
                if(endDate < startDate){
                    $endDate.datepicker('setDate', startDate - 3600*1000*24);
                }
                $endDate.datepicker( "option", "minDate", new Date(new Date(startDate).getTime()+24*60*60*1000).Format("yyyy-MM-dd") );
            }
        });
    var sDate = new Date();
    $( "#datepickerStart" ).datepicker( 'setDate' , sDate);
    $("#datepickerEnd").datepicker(
        {// 添加日期选择功能
            dateFormat: 'yy-mm-dd',// 日期格式
            showMonthAfterYear: true,// 是否把月放在年的后面
            minDate: 1,
            //maxDate: "+1M",// 最大日期
            monthNames: [ '1月', '2月', '3月', '4月', '5月', '6月',
                '7月', '8月', '9月', '10月', '11月', '12月' ],
            dayNames: [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五',
                '星期六' ],
            dayNamesShort: [ '周日', '周一', '周二', '周三', '周四', '周五',
                '周六' ],
            dayNamesMin: [ '日', '一', '二', '三', '四', '五', '六' ],
            onSelect: function( endDate ) {
                var $startDate = $( "#datepickerStart" );
                var $endDate = $('#datepickerEnd');
                var startDate = $startDate.datepicker( "getDate" );
                if(endDate < startDate){
                    $startDate.datepicker('setDate', startDate + 3600*1000*24);
                }
                $startDate.datepicker( "option", "maxDate", new Date(new Date(endDate).getTime()-24*60*60*1000).Format("yyyy-MM-dd") );
            }
        });
    $( "#datepickerEnd" ).datepicker( 'setDate' , sDate.getMonth() + 7);
    $(".tab_box1 table tr:odd td").css("background", "#fafbfc");
    $("input[type='radio']").iCheck({
        checkboxClass: 'icheckbox_square-blue',
        radioClass: 'iradio_square-blue'
    });
    $('#accordion').on('click', '.accordion-toggle', function () {
        if ($(this).attr("class") == "accordion-toggle") {
            $(".accordion-toggle").attr("class", "accordion-toggle collapsed");
            $(".accordion-group").attr("class", "accordion-group opacity-bg");
        } else {
            $(".accordion-toggle").attr("class", "accordion-toggle collapsed");
            $(".accordion-group").attr("class", "accordion-group opacity-bg");
            $(this).attr("class", "accordion-toggle");
            $(this).parents(".accordion-group").attr("class", "accordion-group");
        }
    });
    $("#choucInfo ul li .close").click(function () {
        $(this).parent("li").remove();
    });
    /*
     * ============================ @author:弹窗 @time:2015-10-15
     * ============================
     */
    $('#new_task').on('click', function () {
//        $(".page-wrap").addClass("page-fixed");
    	var remainNum = $("#remainNum").html();
    	if(remainNum > 0){
	        $("#main-center").hide();
	        $("#spot_submit_div_id").hide();
	        $("#new_task_div").show();
        	//加载地方 部位树
	        loadingTree();
	        //获取批次信息
	        loadingBatchTask();
	        //获取历史已完成抽查
	        getCloseSchedule();
        }
    });
    key = $("#prependedInput");
    key.bind("focus", focusKey)
        .bind("blur", blurKey)
        .bind("propertychange", searchNode)
        .bind("input", searchNode);
    $("#checkCount").text(0);
    
	/**
	 * flag 标识添加站点的来源  
	 * 		 如果为空，说明是点击左侧菜单进入抽查首页面
	 * 		 如果不为空，说明是从站点详情页面中跳转到抽查首页面
	 */
    var flag=$("#flag_id").val();
    if(flag){//站点详情表中点击增加站点跳转到该页面
    	$("#sjxzzd").hide();//增加站点隐藏随机选择站点
    	$("#fzrwzd").hide();//增加站点隐藏复制任务站点
        $("#main-center").hide();//首页面隐藏
        $("#new_task_div").show();//新建任务显示
        $("#spot_submit_div_id").hide();//抽查提交页面隐藏
        
        var scheduleId=$("#scheduleId2").val();
        initNewTask(scheduleId);
        
    }else{
        $("#main-center").show();//首页面隐藏
        $("#new_task_div").hide();//新建任务显示
        $("#spot_submit_div_id").hide();//抽查提交页面隐藏
    	
    }
    
    
    //抽查首页面--表格数据初始化
    getTables();
    //抽查首页面--搜索框添加点击搜索事件
    $("#search_btn_id").click(function(){
    	getTables();
    });
    
    //抽查首页面--时间段下拉框，添加绑定事件
    $("#select_id").change(function(){
    	
    	getTables();
    	//复选框添加样式
	$("input[type='checkbox']").iCheck({
		checkboxClass : 'icheckbox_square-blue',
		radioClass : 'iradio_square-blue'
	});	
    });
    
    //抽查提交页面，关键字查询
    $("#key_search_id").keyup(function(){
		var searchInfo=$("#key_search_id").val();
		 if(searchInfo==""){
			 $("#rwNextTab tr").show();
		 }else{
			 $("#rwNextTab").find(".key").each(function(index, element) {
				 if($(this).html().indexOf(searchInfo)<0){
					$(this).parents("tr").hide();
				 }else{
					 $(this).parents("tr").show();
				 }
			});
		 }
    });
    

    
    
    //输入随机抽查百分比，获取对应条件的抽查网站个数
    $("#spot_per_id").keyup(function(){
    	spotRandomCount();
    });

//    //复选框添加点击事件
  $("input[type='checkbox']").each(function(){
  	$(this).click(function(){
  		if($(this).attr("checked")=="checked"){//原来为选中状态
  			$(this).removeAttr("checked");
  		}else{
  			$(this).attr("checked","checked");
  		}
  	});
  });
  
	//上传文件名显示
	var file = $('#siteReportup');
	aim = $('#affixName');
	file.on('change', function( e ){
		 //e.currentTarget.files 是一个数组，如果支持多个文件，则需要遍历
		//以下获取上传文件名不兼容IE浏览器
		//var name = e.currentTarget.files[0].name;
		
		var src=e.target || window.event.srcElement;
		var filename=src.value;
		var name = filename.substring( filename.lastIndexOf('\\')+1 );
		aim.val( name );
		aim.removeAttr("disabled");
	});
	
	var fileNo = $('#siteReportupNo');
	aimNo = $('#affixNameNo');
	fileNo.on('change', function( e ){
		 //e.currentTarget.files 是一个数组，如果支持多个文件，则需要遍历
		//以下获取上传文件名不兼容IE浏览器
		//var name = e.currentTarget.files[0].name;
		
		var srcNo=e.target || window.event.srcElement;
		var filenameNo=srcNo.value;
		var nameNo = filenameNo.substring( filenameNo.lastIndexOf('\\')+1 );
		aimNo.val( nameNo );
		aimNo.removeAttr("disabled");
	});
	
	//model内的日期
	$("#startDate").datepicker(
			{// 添加日期选择功能
				inline : true,
				showOn :"both" ,
				buttonImage : webPath + "/images/date.png",
				buttonImageOnly : true,
				numberOfMonths : 1,// 显示几个月
				showButtonPanel : false,// 是否显示按钮面板
				dateFormat : 'yy-mm-dd',// 日期格式
				clearText : "清除",// 清除日期的按钮名称
				closeText : "关闭",// 关闭选择框的按钮名称
				yearSuffix : '年', // 年的后缀
				showMonthAfterYear : true,// 是否把月放在年的后面
				defaultDate : new Date(),// 默认日期
//				minDate : GetDateStr(0),// 最小日期
				maxDate : GetDateStr(0),// 最大日期
				monthNames : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月',
						'9月', '10月', '11月', '12月' ],
				dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
				dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
				dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
				onSelect : function(dateText, inst) {
				}
			});
	$("#endDate").datepicker(
			{// 添加日期选择功能
				inline : true,
				showOn :"both" ,
				buttonImage : webPath + "/images/date.png",
				buttonImageOnly : true,
				numberOfMonths : 1,// 显示几个月
				showButtonPanel : false,// 是否显示按钮面板
				dateFormat : 'yy-mm-dd',// 日期格式
				clearText : "清除",// 清除日期的按钮名称
				closeText : "关闭",// 关闭选择框的按钮名称
				yearSuffix : '年', // 年的后缀
				showMonthAfterYear : true,// 是否把月放在年的后面
				defaultDate : new Date(),// 默认日期
//				minDate : GetDateStr(0),// 最小日期
				 maxDate : GetDateStr(100),// 最大日期
				monthNames : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月',
						'9月', '10月', '11月', '12月' ],
				dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
				dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
				dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
				onSelect : function(dateText, inst) {
				}
			});
//	是否上传附件点击
	$("[name='isUp']").on('ifChecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定 
		isUp =$(this).val();
		   if(isUp == 1){
			   $("#uploadIsUp").css('display','block');
		   }else{
			   $("#uploadIsUp").css('display','none');
		   }
	}); 
});
function reportUp(){
	$("#affixName").val('');
	$("#siteReportup").val('');
	var arrChk = $("input[name='chk_list']:checked");
	var id = "";
	$(arrChk).each(function(index,element) {
		//遍历得到每个checkbox的value值
//			id.push($(this).val());
		if($(arrChk).length-1==index){
			id+=$(this).val();
		}else{
			id+=$(this).val()+",";
		}
	});
	
	
	$("#affixName").val("");
	$("#siteReportup").val("");
	$("#taskNameUp").val("");
	$("#websiteNum").val("");
	$("#affixNameNo").val("");
	$("#siteReportupNo").val("");
	if(id ==""){
		alert("未勾选列表任务，只能进行附件汇报");
		$("#divReportUpNo").css('display','block');
		$("#divReportUp").css('display','none');
		$("#check-report").css("height","424px");
		$("#check-report").css("margin-top","-212px");
	}else{
		$("#divReportUp").css('display','block');
		$("#divReportUpNo").css('display','none');
		$("#check-report").css("height","326px");
		$("#check-report").css("margin-top","-118px");
	}
	$("#scheduleIds").val(id);
}
//抽查汇报

function showReport() {
	$('#reportDiv').show();
}
function hideReport() {
	$('#reportDiv').hide();
}
//传入 event 对象
function showReportOver(objEvent) {
var divObj = document.getElementById("reportDiv");
divObj.style.visibility = "visible";
//使用这一行代码，提示层将出现在鼠标附近
//divObj.style.left = objEvent.clientX + 5;   //clientX 为鼠标在窗体中的 x 坐标值       
divObj.style.left = 60 + 5;
divObj.style.top = objEvent.clientY + 5;     //clientY 为鼠标在窗体中的 y 坐标值
}

$("#formUploadNo").ajaxForm({
	url : "spotCheckResult_getSpotCheckSchedulBatchNum.action",
	type : "post", // 请求方式
	dataType : "json", // 响应的数据类型
	async : false, // 异步
	success : function(data) {
		alert(data.report);
		$("#check-report").modal('hide');
	},
	error : function() {
		alert(data.errorMsg);
	}
});

$("#reportUpNo").bind("click", function() {
	var arrChk = $("input[name='chk_list']:checked");
	var id = "";
	$(arrChk).each(function(index,element) {
		//遍历得到每个checkbox的value值
//			id.push($(this).val());
		if($(arrChk).length-1==index){
			id+=$(this).val();
		}else{
			id+=$(this).val()+",";
		}
	});
	if(id == ""){
		var taskName = $("#taskNameUp").val();
		if(taskName == null ||　taskName　== ""){
			alert("请输入任务名称。");
			return false;
		}else if(taskName.length>20){
			alert("任务名称20字以内。");
			return false;
		}
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		if(startDate>endDate){
			alert("日期结束时间不能小于开始时间。");
			return false;
		}
		var websiteNum = $("#websiteNum").val();
		if(websiteNum == null ||　websiteNum　== ""){
			alert("请输入抽查站点数量。");
			return false;
		}
		var affixNameNo = $("#affixNameNo").val();
		var siteReportupNo = $("#siteReportupNo").val();
		if(affixNameNo == null ||　affixNameNo　== "" || siteReportupNo == null ||　siteReportupNo　== "" ){
			alert("附件不能为空");
			return false;
		}
	}
	
	$("#formUploadNo").submit();
});

//全选/反选
function checkAll(){
	if ($("input[name='chk_all']").prop("checked")) {
		$("input[name='chk_list']").prop("checked", true);
	} else {
		$("input[name='chk_list']").prop("checked", false);
	}
//复选框添加样式
$("input[type='checkbox']").iCheck({
	checkboxClass : 'icheckbox_square-blue',
	radioClass : 'iradio_square-blue'
});	
}
var spotStatus={"0":"未启动","1":"检查中","2":"检查完成"};
/**
 * 抽查首页面---获取批次信息表格数据
 *
 */
function getTables(){
	var jsonObj={};
	jsonObj["key"]=$("#input_key_word_id").val();
	if(jsonObj["key"] == "请输入任务名称"){
		jsonObj["key"]="";
	}
	jsonObj["datePd"]=$("#select_id").val();
	var siteCode = $("#site_code_session").val();
	$.ajax({
   	 	type:"post",
     	async:false,
        url: webPath+"/spotCheckResult_getSchedule.action",
        dataType:"json",
        data: JSON.stringify(jsonObj),
        contentType: "application/json",
        success : function(data){
    		//抽查总数
    		var spotSum=data.spotSum;
    	    //已抽查次数
    		var spotNum=data.spotNum;
    		
    		 //剩余可抽查次数
    		var remainNum=data.remainNum;
            $("#spotSum").html(spotSum);
            $("#spotNum").html(spotNum);
            $("#remainNum").html(remainNum);
            if(null!=data.curredContractCode){
            	 $("#curredContractCode").html("当前服务合同号 : <i>"+data.curredContractCode+"</i>");
            }
           
            if(remainNum==0){
            	$("#new_task").removeClass("new_task");
            	$("#new_task").addClass("new-task-unable");
            }
            if(data.NoContract == 'no'){
            	$("#spot_check_no_data_id").hide();
            	$("#spot_check_schedule_tbody_id").hide();
        		$(".pay").addClass('free-html');
				$(".free-html").html("");
        		var pay = "<i></i><span class='font-bold'>提醒：</span>该功能需要付费升级后才能使用，请&nbsp;<a href='http://jg.kaipuyun.cn/ce/banben/version.shtml' target='_blank'>点击这里</a>&nbsp;提交购买申请。或联系我们销售热线：<span>4000-976-005</span>&nbsp;&nbsp;邮箱： <a href='mailto:jianguan@ucap.com.cn'>jianguan@ucap.com.cn</a>";
        		$(".pay").append(pay);
            }else if(data.NoContract == 'yes'){
        	//页面返回错误
        	if(data.errormsg){
        		var errormsg=data.errormsg;
        		//隐藏表格内容，显示暂无数据
        		$("#spot_check_schedule_table_id").attr("style","display:none");
        		$("#spot_check_no_data_id").attr("style","display:block");
        	}else{
        		//显示表格内容，隐藏暂无数据
        		$("#spot_check_schedule_table_id").attr("style","display:block");
        		$("#spot_check_no_data_id").attr("style","display:none");
        		
        		//所有数据
        		var returnList=data.returnList;
        		//批次数据
        		var batchNumList=data.batchNumList;
 
        		//表数据清空
        		$("#spot_check_schedule_tbody_id").html("");
        		
        		/**
        		 * 页面处理逻辑
        		 * 		1 首先遍历批次集合表，获取每一个批次的所有组数据，拼装该批次数据，将改组数据封添加到表中
        		 */
        		if(batchNumList){
            		//遍历批次数组
        			var str ="启动抽查";
        			if(site_code_session == "440000"){
        				str = "启动考评";
        			}
        			for(var i=0;i<batchNumList.length;i++){
            			var listStr="";
            			var listNum=0;
            			for(var x=0;x<returnList.length;x++){
            				if(batchNumList[i]==returnList[x]["batchNum"]){
            					listNum+=1;
            				}
            			}
            			//遍历所有数据的数据
            			for(var j=0;j<returnList.length;j++){
            				if(batchNumList[i]==returnList[j]["batchNum"]){
                				//判断是否为该批次的第一条记录，如果是第一条记录的话，该tr中会有第几批次的td,其他行没有
                				if(listStr==""){
                					listStr+="<tr><td rowspan="+listNum+"><div class='fuzhi-font1'>第"+returnList[j]["batchNum"]+"批</div><br/><div>"+returnList[j]["contractCode"]+"</div></td>"+
                					"<td class='f_num'>"+returnList[j]["groupNum"]+"</td>"+
                					"<td class='t-left'>"+returnList[j]["taskName"]+"</td>"+
                					"<td><div class='fuzhi-font2'>起："+returnList[j]["dateStart"]+"</div><div class='fuzhi-font2'>止："+returnList[j]["endStart"]+"</div></td>"+
                					"<td>"+spotStatus[returnList[j]["state"]]+"</td>";
                					//检查完成--不显示添加站点按钮  检查中显示添加站点按钮
                					listStr+="<td>";
                					if(returnList[j]["state"]==0){
//                						<i ></i>&nbsp;
                						//添加站点
                						listStr+="<a href='#' onclick='openByBatchGroup("+returnList[j]["scheduleId"]+","+returnList[j]["batchNum"]+","+returnList[j]["groupNum"]+","+returnList[j]["state"]+")'>增加站点</a><br/>";
                						//启动考评
                						listStr+="<a href='#' onclick='openCheckResult("+returnList[j]["scheduleId"]+","+returnList[j]["batchNum"]+","+returnList[j]["groupNum"]+")'>"+str+"</a><br/>";
                						//删除
                						listStr+="<a href='#' onclick='deleteSpotCheckSchedule("+returnList[j]["scheduleId"]+","+returnList[j]["batchNum"]+","+returnList[j]["groupNum"]+","+returnList[j]["state"]+")'>删除</a>";
                					}else{
                						listStr+="<div >增加站点</div>";
                						listStr+="<div  >"+str+"</div>";
                						listStr+="<div  >删除</div>";
                					}
            						
            						listStr+="</td>";
                					listStr+="<td><a onClick='spotJump();' href='"+webPath+"/servicePeriod_websiteList.action?dataType=1&type=0&flagAll="+returnList[j]["scheduleId"]+"&batchNum="+returnList[j]["batchNum"]+"&groupNum="+returnList[j]["groupNum"]+"'>"+returnList[j]["webSum"]+"</a></td>";
                					listStr+="<td><a onClick='spotJump();' href='"+webPath+"/servicePeriod_websiteList.action?finishRe=1&dataType=2&type=0&flagAll="+returnList[j]["scheduleId"]+"&batchNum="+returnList[j]["batchNum"]+"&groupNum="+returnList[j]["groupNum"]+"'>"+returnList[j]["successReportWordNum"]+"</a></td>";
                					listStr+="<td><a onClick='spotJump();' href='"+webPath+"/servicePeriod_websiteList.action?dataType=3&type=0&flagAll="+returnList[j]["scheduleId"]+"&batchNum="+returnList[j]["batchNum"]+"&groupNum="+returnList[j]["groupNum"]+"'>"+returnList[j]["checkReportResultNum"]+"</a></td>";
                					listStr+="<td><a onClick='spotJump();' href='"+webPath+"/servicePeriod_websiteList.action?finishRe=2&dataType=4&type=0&flagAll="+returnList[j]["scheduleId"]+"&batchNum="+returnList[j]["batchNum"]+"&groupNum="+returnList[j]["groupNum"]+"'>"+returnList[j]["IsReadNoticeNum"]+"</a></td>";
                					listStr+="<td><a onClick='spotJump();' href='"+webPath+"/servicePeriod_websiteList.action?dataType=1&type=0&flagAll="+returnList[j]["scheduleId"]+"&batchNum="+returnList[j]["batchNum"]+"&groupNum="+returnList[j]["groupNum"]+"'>详情</a></td>";                					
                					if(siteCode != 'bm0100'){
                						listStr+="<td style='width:20px;'><input type='checkbox' name='chk_list' value = '"+returnList[j]["scheduleId"]+"'/></td>"
                					}
                					listStr+="</tr>";
                					
                					
                				}else{
                					listStr+="<tr><td class='f_num'>"+returnList[j]["groupNum"]+"</td>"+
                					"<td class='t-left'>"+returnList[j]["taskName"]+"</td>"+
                					"<td><div class='fuzhi-font2'>起："+returnList[j]["dateStart"]+"</div><div class='fuzhi-font2'>止："+returnList[j]["endStart"]+"</div></td>"+
                					"<td>"+spotStatus[returnList[j]["state"]]+"</td>";
                					
                					//检查完成--不显示添加站点按钮  检查中显示添加站点按钮
                					listStr+="<td>";
                					if(returnList[j]["state"]==0){
//                						<i ></i>&nbsp;
                						//添加站点
                						listStr+="<a href='#' onclick='openByBatchGroup("+returnList[j]["scheduleId"]+","+returnList[j]["batchNum"]+","+returnList[j]["groupNum"]+","+returnList[j]["state"]+")'>增加站点</a><br/>";
                						//启动考评
                						listStr+="<a href='#' onclick='openCheckResult("+returnList[j]["scheduleId"]+","+returnList[j]["batchNum"]+","+returnList[j]["groupNum"]+")'>"+str+"</a><br/>";
                						//删除
                						listStr+="<a href='#' onclick='deleteSpotCheckSchedule("+returnList[j]["scheduleId"]+","+returnList[j]["batchNum"]+","+returnList[j]["groupNum"]+","+returnList[j]["state"]+")'>删除</a>";
                					}else{
                						listStr+="<div >增加站点</div>";
                						listStr+="<div  >"+str+"</div>";
                						listStr+="<div  >删除</div>";
                					}
            						
            						listStr+="</td>";
                					listStr+="<td><a onClick='spotJump();' href='"+webPath+"/servicePeriod_websiteList.action?dataType=1&type=0&flagAll="+returnList[j]["scheduleId"]+"&batchNum="+returnList[j]["batchNum"]+"&groupNum="+returnList[j]["groupNum"]+"'>"+returnList[j]["webSum"]+"</a></td>";
                					listStr+="<td><a onClick='spotJump();' href='"+webPath+"/servicePeriod_websiteList.action?dataType=2&type=0&flagAll="+returnList[j]["scheduleId"]+"&batchNum="+returnList[j]["batchNum"]+"&groupNum="+returnList[j]["groupNum"]+"'>"+returnList[j]["successReportWordNum"]+"</a></td>";
                					listStr+="<td><a onClick='spotJump();' href='"+webPath+"/servicePeriod_websiteList.action?dataType=3&type=0&flagAll="+returnList[j]["scheduleId"]+"&batchNum="+returnList[j]["batchNum"]+"&groupNum="+returnList[j]["groupNum"]+"'>"+returnList[j]["checkReportResultNum"]+"</a></td>";
                					listStr+="<td><a onClick='spotJump();' href='"+webPath+"/servicePeriod_websiteList.action?dataType=4&type=0&flagAll="+returnList[j]["scheduleId"]+"&batchNum="+returnList[j]["batchNum"]+"&groupNum="+returnList[j]["groupNum"]+"'>"+returnList[j]["IsReadNoticeNum"]+"</a></td>";
                					listStr+="<td><a onClick='spotJump();' href='"+webPath+"/servicePeriod_websiteList.action?dataType=1&type=0&flagAll="+returnList[j]["scheduleId"]+"&batchNum="+returnList[j]["batchNum"]+"&groupNum="+returnList[j]["groupNum"]+"'>详情</a></td>";
                					if(siteCode != 'bm0100'){
                						listStr+="<td style='width:20px;'><input type='checkbox' name='chk_list' value = '"+returnList[j]["scheduleId"]+"'/></td>"
                					}
                					listStr+="</tr>";
                						
                				}
            				}
            			}
            		
            			$("#spot_check_schedule_tbody_id").append(listStr);
            		    //增加站点添加绑定事件
            		    //getSpotOnclick();

            			
            		}
        		}
        	}
        }
			$("input[type='checkbox']").iCheck({
				checkboxClass : 'icheckbox_square-blue',
				radioClass : 'iradio_square-blue'
			});	
		 //全选 复选框  
			$("[name='chk_all']").on('ifChanged', function(event){
			    var checkType = $(this).prop("checked");
			   
			    if ($("input[name='chk_all']").prop("checked")) {
					$("input[name='chk_list']").prop("checked", true);
				} else {
					$("input[name='chk_list']").prop("checked", false);
				}
			  //复选框添加样式
			    $("input[name='chk_list']").iCheck({
			    	checkboxClass : 'icheckbox_square-blue',
			    	radioClass : 'iradio_square-blue'
			    });	
			});
        },error:function(data){
        	//console.log(data.errormsg);
	   }
     });
}
//启动抽查
function openCheckResult(scheduleId,batchNum,groupNum){
    $.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
//        url: webPath + "/spotCheckResult_openAllCheckResult.action?batchNum="+batchNum+"&groupNum="+groupNum,
        url: webPath + "/spotCheckResult_openAllCheckResult.action?batchNum="+batchNum+"&groupNum="+groupNum+"&scheduleId="+scheduleId,
        success:function(data){
        	if(data.errorMsg){
        		alert(data.errorMsg);
        	}else{
        		alert(data.success);
        		$("#add_site_id").attr("style","display:none");//添加站点和全部启动不可点击
            	$("#start_all_id").attr("style","display:none");//全部启动不可点击
        		window.location.reload(true);
        	}
        },error:function(data){
        	alert(data.errorMsg);
        }
    });
	
}

//删除抽查
function deleteSpotCheckSchedule(scheduleId,batchNum,groupNum){
	
	if(confirm('确定删除吗 ？')){
		 $.ajax({
		        async: false,
		        cache: false,
		        type: 'POST',
		        dataType: "json",
		        url: webPath + "/spotCheckResult_deleteSpotCheckSchedule.action?batchNum="+batchNum+"&groupNum="+groupNum+"&scheduleId="+scheduleId,
		        success:function(data){
		        
		        	if(data.errorMsg){
		        		alert(data.errorMsg);
		        	}else{
		        		alert(data.success);
		        		window.location.reload(true);
		        	}
		        },error:function(data){
		        	alert(data.errorMsg);
		        }
		    });
	}
	
   
	
}

//抽查首页面---添加站点-----新加方法
function openByBatchGroup(spotCheckScheduleId,batch,group,state){
	if(state ==0){
		$("#main-center").hide();//抽查首页面model隐藏
		$("#new_task_div").show();//新建抽查任务页面展示
		$("#spot_submit_div_id").hide();//抽查提交页面隐藏
		$.ajax({
	        async: false,
	        cache: false,
	        type: 'POST',
//	        url: webPath + "/spotCheckResult_getSpotCheckSchedule.action?batchNum="+batch+"&groupNum="+group,// 请求的action路径
	        url: webPath + "/spotCheckResult_getSpotCheckSchedule.action?batchNum="+batch+"&groupNum="+group+"&scheduleId="+spotCheckScheduleId,// 请求的action路径
	        dataType: "json",
	        contentType: "application/json",
	        success: function (data) { // 请求成功后处理函数。
	        	if(data.errorMsg){
	        		alert(data.errorMsg);
	        	}else{
	        		$("#sjxzzd").hide();//增加站点隐藏随机选择站点
	        		$("#fzrwzd").hide();//增加站点隐藏复制任务站点
	        		//批次--下拉框
	        		$("#batchNum").html("");//先清空
	        		$("#batchNum").append("<option value="+data["groupNum"]+">"+data["batchNum"]+"</option>");
	        		//组次
	        		$("#groupNum").html("");
	        		$("#groupNum").html(data["groupNum"]);
	        		//任务名称
	        		$("#taskName").val(data["taskName"]);
	        		//开始日期
	        		$("#datepickerStart").val(data["startDate"]);
	        		//结束日期
	        		$("#datepickerEnd").val(data["endDate"]);
	        		//获取抽查进度表id
	        		$("#scheduleId").val(data["scheduleId"]);
	        		
	        	    //加载地方 部位树
	        	    loadingTree(scheduleId);
	        	}
	        },error:function(data){
	        	alert(data.errorMsg);
	        }
	    });
	}else{
		$(li).attr("disabled",true);
	}
}

//抽查首页面---添加站点
function getSpotOnclick(){
	var lis=$("#spot_check_schedule_table_id").find(".add-zd");
	$.each(lis,function(index,li){
		var state=$(li).parent().prev().html();
		if(state =="未启动"){
	  		$(li).on('click',function(){
	  			//批次
	  			var batchNum=$(this).parents().parents().find(".fuzhi-font1").html().replace(/[^0-9]/ig,"");
	  			//组次
	  			var groupNum=$(this).parents().parents().find(".f_num").html();
	  			
	  		    $("#main-center").hide();//抽查首页面model隐藏
	  		    $("#new_task_div").show();//新建抽查任务页面展示
	  		    $("#spot_submit_div_id").hide();//抽查提交页面隐藏
	  		    
	  		    $.ajax({
	  		        async: false,
	  		        cache: false,
	  		        type: 'POST',
	  		        url: webPath + "/spotCheckResult_getSpotCheckSchedule.action?batchNum="+batchNum+"&groupNum="+groupNum,// 请求的action路径
	  		        dataType: "json",
	  		        contentType: "application/json",
	  		        success: function (data) { // 请求成功后处理函数。
	  		        	if(data.errorMsg){
	  		        		alert(data.errorMsg);
	  		        	}else{
	  		        		//批次--下拉框
	  		        		$("#batchNum").html("");//先清空
	  		        		$("#batchNum").append("<option value="+data["groupNum"]+">"+data["batchNum"]+"</option>");
	  		        		//组次
	  		        		$("#groupNum").html("");
	  		        		$("#groupNum").html(data["groupNum"]);
	  		        		//任务名称
	  		        		$("#taskName").val(data["taskName"]);
	  		        		//开始日期
	  		        		$("#datepickerStart").val(data["startDate"]);
	  		        		//结束日期
	  		        		$("#datepickerEnd").val(data["endDate"]);
	  		        		//获取抽查进度表id
	  		        		$("#scheduleId").val(data["scheduleId"]);
	  		        		
	  		        	    //加载地方 部位树
	  		        	    loadingTree(scheduleId);
	  		        	}
	  		        },error:function(data){
	  		        	alert(data.errorMsg);
	  		        }
	  		    });
	  		});
		}else{
			$(li).attr("disabled",true);
		}
	});
}

//站点详情页面--点击添加站点，跳转到抽查页面，对新建任务modal数据初始化
function initNewTask(scheduleId){
  $.ajax({
      async: false,
      cache: false,
      type: 'POST',
      url: webPath + "/spotCheckResult_getSpotCheckSchedule.action?scheduleId="+scheduleId,// 请求的action路径
      dataType: "json",
      contentType: "application/json",
      success: function (data) { // 请求成功后处理函数。
      	if(data.errorMsg){
      		alert(data.errorMsg);
      	}else{
      		//批次--下拉框
      		$("#batchNum").html("");//先清空
      		$("#batchNum").append("<option value="+data["groupNum"]+">"+data["batchNum"]+"</option>");
      		//组次
      		$("#groupNum").html("");
      		$("#groupNum").html(data["groupNum"]);
      		//任务名称
      		$("#taskName").val(data["taskName"]);
      		//开始日期
      		$("#datepickerStart").val(data["startDate"]);
      		//结束日期
      		$("#datepickerEnd").val(data["endDate"]);
      		//获取抽查进度表id
      		$("#scheduleId").val(data["scheduleId"]);
      		
      	    //加载地方 部位树
      	    loadingTree(scheduleId);
      	}
      },error:function(data){
      	alert(data.errorMsg);
      }
  });
}


function changeTab(tabid){
	if(tabid == 0){
	}else if(tabid == 1){
		 randomReport();
	}else if(tabid == 2){
		subordinateRectification();
	}
	
}

//抽查汇报  ---------------======================START
function  randomReport(){
	 //抽查首页面--表格数据初始化
    getTablesReport();
    //抽查首页面--搜索框添加点击搜索事件
    $("#search_btn_id_report").click(function(){
    	getTablesReport();
    });
}
var spotStatus={"0":"未启动","1":"检查中","2":"检查完成"};
//抽查数据
function getTablesReport(){
		var key = $("#input_key_word_id_report").val();
		$.ajax({
	   	 	type:"post",
	     	async:false,
	        url: webPath+"/spotCheckSchedule_getScheduleList.action",
	        data:{
	        	key:key,
	        },
	        dataType : "json",
	        success : function(data){
	        	//表数据清空
	    		//抽查总数
	    		var spotSum=data.spotSum;
	    	    //已抽查次数
	    		var spotNum=data.spotNum;
	    		
	        	//页面返回错误
	        	if(data.errormsg){
	        		var errormsg=data.errormsg;
	        		//隐藏表格内容，显示暂无数据
	        		$("#spot_check_schedule_table_id_report").attr("style","display:none");
	        		$("#spot_check_no_data_id_report").attr("style","display:inline-table");
	        		//表数据清空
	        	}else{
	        		//显示表格内容，隐藏暂无数据
	        		$("#spot_check_schedule_table_id_report").attr("style","display:inline-table");
	        		$("#spot_check_no_data_id_report").attr("style","display:none");
	        		
	        		//所有数据
	        		var returnList=data.returnList;
	        		//批次数据
	        		var batchNumList=data.batchNumList;
	        		//表数据清空
	        		$("#spot_check_schedule_tbody_id_report").html("");
	        		
	        		if(batchNumList){
	            		//遍历批次数组
	        			var str ="启动抽查";
	        			for(var i=0;i<batchNumList.length;i++){
	        				var listStr="";
	            			var listNum=0;
	            			for(var x=0;x<returnList.length;x++){
	            				if(batchNumList[i]==returnList[x]["batchNum"]){
	            					listNum+=1;
	            				}
	            			}
	            			//遍历所有数据的数据
	            			for(var j=0;j<returnList.length;j++){
	            				if(batchNumList[i]==returnList[j]["batchNum"]){
	                				//判断是否为该批次的第一条记录，如果是第一条记录的话，该tr中会有第几批次的td,其他行没有
	                					listStr+="<tr><td class='t-center'>"+returnList[j]["reportName"]+"/"+returnList[j]["reportCode"]+"</td>" +
	                					"<td class='t-left'>"+returnList[j]["taskName"]+"</td>"+
	                					"<td><div class='fuzhi-font2'>起："+returnList[j]["dateStart"]
	                					+"</div><div class='fuzhi-font2'>止："+returnList[j]["endStart"]
	                					+"</div></td>"
	                					+"<td>"+spotStatus[returnList[j]["state"]]+"</td>";
	                					if(returnList[j]["type"] == 1){
	                						listStr+="<td><a href='"+webPath+"/spotCheckSchedule_spotCheckDetails.action?dataType=1&type=0&flagAll="+returnList[j]["scheduleId"]+"&batchNum="+returnList[j]["batchNum"]+"&groupNum="+returnList[j]["groupNum"]+"'>"+returnList[j]["webSum"]+"</a></td>";
	                					}else if(returnList[j]["type"] == 2){
	                						listStr+="<td>"+returnList[j]["webSum"]+"</td>"
	                					}
	                					listStr+="<td class='t-left'>"+returnList[j]["modifyTime"]+"</td>";
	                					if(returnList[j]["type"] == 1){
	                						if(returnList[j]["affixName"] == null || returnList[j]["affixUrl"] == null || returnList[j]["affixName"] == "" || returnList[j]["affixUrl"] == ""){
	                							listStr+="<td colspan='2'><a href='"+webPath+"/spotCheckSchedule_spotCheckDetails.action?dataType=1&type=0&flagAll="+returnList[j]["scheduleId"]+"&batchNum="+returnList[j]["batchNum"]+"&groupNum="+returnList[j]["groupNum"]+"&siteCode="+returnList[j]["siteCode"]+"'>详情</a></td>";                			
	                						}else{
	                							if(returnList[j]["affixName"].length>15){
	                								listStr+="<td class='text-left special_state'  title='"+returnList[j]["affixName"]+"' ><a class='sort-num colo_1ca8dd_tn' href='javascript:void(0);' onclick='downLoadReportFile("+returnList[j]["upId"]+")')' >"+returnList[j]["affixName"].substring(0,15)+'...'+"</a></td>";
	                							}else{
		                							listStr+="<td class='text-left special_state' title='"+returnList[j]["affixName"]+"' ><a class='sort-num colo_1ca8dd_tn' href='javascript:void(0);' onclick='downLoadReportFile("+returnList[j]["upId"]+")')' >"+returnList[j]["affixName"]+"</a></td>";
	                							}
	                							listStr+="<td style='width:6%;'><a href='"+webPath+"/spotCheckSchedule_spotCheckDetails.action?dataType=1&type=0&flagAll="+returnList[j]["scheduleId"]+"&batchNum="+returnList[j]["batchNum"]+"&groupNum="+returnList[j]["groupNum"]+"&siteCode="+returnList[j]["siteCode"]+"'>详情</a></td>";                			
	                						}
	                					}else if(returnList[j]["type"] == 2){
	                						if(returnList[j]["affixName"].length>15){
	                							listStr+="<td class='text-left special_state' colspan='2' title='"+returnList[j]["affixName"]+"' ><a class='sort-num colo_1ca8dd_tn' href='javascript:void(0);' onclick='downLoadReportFile("+returnList[j]["upId"]+")')' >"+returnList[j]["affixName"].substring(0,15)+'...'+"</a></td>";
	                						}else{
	                							listStr+="<td class='text-left special_state' colspan='2' title='"+returnList[j]["affixName"]+"' ><a class='sort-num colo_1ca8dd_tn' href='javascript:void(0);' onclick='downLoadReportFile("+returnList[j]["upId"]+")')' >"+returnList[j]["affixName"]+"</a></td>";

	                						}
	                					}
	                					
	                					listStr+="</tr>";
	            				}
	            			}
	            			$("#spot_check_schedule_tbody_id_report").append(listStr);
	            		} 
	            		
	        		}
	        	}
	        },error:function(data){
       		$("#spot_check_no_data_id_report").attr("style","display:none");
		   }
	     });
	}
//附件
function downLoadReportFile(reportUpId){
	if(reportUpId){
		window.location.href = webPath + "/spotCheckSchedule_downLoadReportFile.action?reportUpId="+reportUpId;
	}
}
//抽查汇报-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=END-=-=-

//下级整改通知 start-=-=-=-=-=-=-=-=-=-=-=-=START

function subordinateRectification(){

	//左侧菜单遮罩层控制高度
	var oSheight=$('#sidebar').height();
	$('#left_opacity_id').css('height',oSheight+20);
	
	var isScanFlag=$("#isScanFlag_id").val();
	console.log("======isScanFlag======="+isScanFlag);
	//点击我的监测报告跳转过来的
	if(isScanFlag && "true"==isScanFlag){
		//头部和左侧菜单天机遮罩层
		$("#top_opacity_id").show();
		$("#left_opacity_id").show();
	}else{
		$("#top_opacity_id").hide();
		$("#left_opacity_id").hide();
	}
	getWordList();

	
	//下载报告
	$("#isDown").bind("click",function(){
		var fid = $("#fid").val();
		downLoadWordFile(fid);
	});
	
	$("#reportsFk").bind("click",function(){
		var fid = $("#fidFk").val();
		downLoadWordFileFk(fid);
    });
	
	$("#formUpload").ajaxForm({
		url :"spotCheckNotice_updateNotice.action",
		type : "post", // 请求方式
		dataType : "json", // 响应的数据类型
		async :false, // 异步
		cache:false,
		success : function(data){
			window.location.href = webPath + "/wordList_indexOrg.action";
		},
		error : function(){
			alert(data.errorMsg);
		}
	});
	
	//整改反馈
	$("#noticeUp").bind("click",function(){
    	//$("#formUpload").attr("action", webPath + "/spotCheckNotice_updateNotice.action");
		var quesNum = $("#questionNum").val();
		var noticeResponse = $("#noticeResponse").val();
		var noticeReport = $("#noticeReport").val();
		if(noticeReport != null && noticeReport != ""){
			var hz = noticeReport.substring(noticeReport.lastIndexOf(".")+1,noticeReport.length);
			if(hz != "pdf" && hz != "doc" && hz!="jpg" && hz!="png"){
				alert("只能上传jpg、png、pdf和word（doc）文件");
				return false;
			}
		}
		if(noticeResponse != ""){
			if(noticeResponse.length>250){
				alert("内容过长，请重新输入反馈内容");
				return false;
			}
		}
		if(quesNum != ""){
			if(isNaN(quesNum)){
				alert('请输入数字');
				var quesNum = $("#questionNum").val("");
				return false;
			}
		}else{
			alert('请输入整改问题数');
			return false;
		}
		$("#formUpload").submit();
    });
	
}
var table;
$('#feedbackClick').on('click', function () {
	getWordList()
});

function getWordList(){
	var table_title = [];
	table_title.push({"mDataProp": "fileName", "bSortable": false,"sTitle": "文件名", "sClass": "t-left p_name", "bVisible": true, "tabIndex": -7});
	table_title.push({"mDataProp": "scnType", "bSortable": false,"sTitle": "检测形式", "sClass": "t-center p_sty", "bVisible": true, "tabIndex": -6});
	table_title.push({"mDataProp": "director","bSortable": false, "sTitle": "检测单位", "sClass": "t-left p_uint", "bVisible": true, "tabIndex": -5});
	table_title.push({"mDataProp": "taskTime", "bSortable": false,"sTitle": "检测周期", "sClass": "t-left p_p", "bVisible": true, "tabIndex": -4});
	table_title.push({"mDataProp": "download","bSortable": false, "sTitle": "检测报告", "sClass": "t-left p_rep", "bVisible": true, "tabIndex": -3});
	table_title.push({"mDataProp": "fk_status","bSortable": false, "sTitle": "反馈状态", "sClass": "t-center p_sta", "bVisible": true, "tabIndex": -2});
	table_title.push({"mDataProp": "notice","bSortable": false, "sTitle": "操作","sClass": "t-center p_op", "bVisible": true, "tabIndex": -1});
	if(undefined != table){
		table.fnClearTable();
		table.fnReloadAjax(webPath+ "/wordList_getOrgWordList.action");
	}else{
		table = $("#wordList").dataTable({
        "sDom": '<"top"T<"clear">>frt<"toolbar">lip',        
        "bDestroy": true,
        "bAutoWidth": true,
        "bDeferRender": true,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "iDisplayLength": 15,
        "aaSorting": [],
        "oTableTools": {
            "sSwfPath": "/boxpro/lib/tableTools/media/swf/copy_csv_xls_pdf.swf",
            "sRowSelect": "multi"     // 可选中行，single单行。multi多行
        },
        "oLanguage": {
            "sSearch": "",
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
            "sZeroRecords": "没有要显示的数据！"
        },
        "bInfo": false,  
        "bLengthChange": false,
        "bFilter": false, // 过滤功能
        "bPaginate": true,
        "bRetrieve": true,
        "bServerSide": true,
        "bDestroy": true,
        "sServerMethod": "POST",
        "sAjaxSource": webPath + "/wordList_getOrgWordList.action",//getList
        "sAjaxDataProp": function (data) {
        	$("#sizeId").html(data.iTotalDisplayRecords);
        	if(data && data.body){	
        		for (var i = 0; i < data.body.length; i++) {
        			var fid = data.body[i]["fid"];
        			var checkReportResult = data.body[i]["checkReportResult"];
        			var siteCode = data.body[i]["siteCode"];
        			var servicePeriodId = data.body[i]["servicePeriodId"];
        			var startDate = data.body[i]["startDate"];
        			var endDate = data.body[i]["endDate"];
        			var scnId=data.body[i]["scnId"];
        			var isRead=data.body[i]["isRead"];
        			data.body[i]["download"] = "<span class='yulan-active fl' title='查看报告'" +
//        			\''+ obj.groupNum+ '\'
        			" onclick=\"searchReport('"+siteCode+"','"+servicePeriodId+"','"+startDate+"','"+endDate+"')\"></span><i class='down_load8' onclick='downLoadWordFile("+fid+");'></i>";
        			if(checkReportResult==2){//已反馈
        				data.body[i]["fk_status"] = "<span data-toggle='modal' data-target='#myModalNoticeShowFk' class='already_fk' onclick=\"getNoticeFk('"+fid+"','"+siteCode+"','"+servicePeriodId+"');\">"+isRead+" 已反馈</span>";
        				data.body[i]["notice"] = "<span data-toggle='modal' data-target='#myModalNoticeShow' class='tzzg-green' onclick=\"getNotice('"+fid+"','"+siteCode+"','"+servicePeriodId+"');\">查看通知</span>"
            			+"<span data-toggle='modal' data-target='#myModalNoticeShowFk' class='ckfk-yellow' onclick=\"getNoticeFk('"+fid+"','"+siteCode+"','"+servicePeriodId+"');\">查看反馈</span>";
        			}else{//1未反馈
        				data.body[i]["fk_status"] = "<span class='not_yet_fk'>"+isRead+" 未反馈</span>";
        				data.body[i]["notice"] = "<span data-toggle='modal' data-target='#myModalNoticeShow' class='tzzg-green' onclick=\"getNotice('"+fid+"','"+siteCode+"','"+servicePeriodId+"');\">查看通知</span>"
            			+"<span  class='ckfk-gray' title='该网站暂时还未提交反馈'>查看反馈</span>";
        			}
        			
        		}
        	}
        	
            return data.body;
        },
        "bProcessing": false,
        "fnServerData": fnDataTablesPipeline,
        "aoColumns": table_title,
        "fnCreatedRow":function (id) {
        	
        },
        "fnInitComplete": function () {
        	
        },
        "fnPreDrawCallback": function(oSettings) {
        	
        }
    });
}
}
/**
 * 查看报告的点击事件
 */
function searchReport(siteCode,servicePeriodId,startDate,endDate){
	requestReport("siteCode="+siteCode+"&servicePeriodId="+servicePeriodId+"&startDate="+startDate+"&endDate="+endDate);
}
//获取整改通知反馈
function getNotice(fid,siteCode,servicePeriodId){
	$.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/spotCheckNotice_getNotice.action?siteCode="+siteCode+"&servicePeriodId="+servicePeriodId,
        success: function (data) {
        	if(data.errorMsg){
        		alert(data.errorMsg);
        	}else{
				$("#siteName").html(data.databaseInfo['name']);
				$("#requireTime").html(data.requireTime);
				$("#director").html(data.spotCheckNotice['director']);
				$("#noticeRequirement").html(data.spotCheckNotice['noticeRequirement']);
				$("#noticeId").val(data.spotCheckNotice['id']);
				
				$("#siteNames").html(data.databaseInfo['name']);
				$("#requireTimes").html(data.requireTime);
				$("#directors").html(data.spotCheckNotice['director']);
				$("#noticeRequirements").html(data.spotCheckNotice['noticeRequirement']);
				if(data.spotCheckNotice['email2'] != null && data.spotCheckNotice['email2'] != ""){
					$("#linkmanName").html(data.databaseInfo['linkmanName']);
					$("#email2").html(data.databaseInfo['email2']);
				}
				if(data.spotCheckNotice['email'] != null && data.spotCheckNotice['email'] != ""){
					$("#principalName").html(data.databaseInfo['principalName']);
					$("#email").html(data.databaseInfo['email']);
				}
				$("#endTime").html(data.endTime);
				$("#fid").val(fid);
				$("#noticeResponse").html(data.spotCheckNotice['noticeResponse']);
				$("#questionNum").html(data.spotCheckNotice['questionNum']);
				$("#noticeReport").html('<a onclick="downLoadWordFile(2580);">'+data.spotCheckNotice['noticeReportName']+'</a>');
        	}
		},error: function (data) {// 请求失败处理函数
            alert(data.errorMsg);
        }
    });
}

//查看整改通知反馈
function getNoticeFk(fid,siteCode,servicePeriodId){
	$.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/spotCheckNotice_getNotice.action?siteCode="+siteCode+"&servicePeriodId="+servicePeriodId,
        success: function (data) {
        	if(data.errorMsg){
        		alert(data.errorMsg);
        	}else{
        		var noticeRequirementsFk = "无";
				if(data.spotCheckNotice['noticeRequirement'] != null && data.spotCheckNotice['noticeRequirement'] != ""){
					noticeRequirementsFk = data.spotCheckNotice['noticeRequirement'];
				}
				var questionNumFk = "未填写";
//				if(data.spotCheckNotice['questionNum'] != null && data.spotCheckNotice['questionNum'] != ""){
					questionNumFk = data.spotCheckNotice['questionNum']+"&nbsp;&nbsp;个";
//				}
				var noticeResponsesFk = "未填写";
				if(data.spotCheckNotice['noticeResponse'] != null && data.spotCheckNotice['noticeResponse'] != ""){
					noticeResponsesFk = data.spotCheckNotice['noticeResponse'];
				}
				var reportsFks = "未上传";
				if(data.spotCheckNotice['noticeReportName'] != null && data.spotCheckNotice['noticeReportName'] != ""){
					reportsFks = data.spotCheckNotice['noticeReportName'];
					$("#reportsFk").html(reportsFks);
				}else{
					$("#reportsFk").hide();
					$("#reportsFks").html(reportsFks);
				}
        		$("#siteNamesFk").html(data.databaseInfo['name']);
				$("#requireTimesFk").html(data.requireTime);
				$("#directorsFk").html(data.spotCheckNotice['director']);
				$("#noticeRequirementsFk").html(noticeRequirementsFk);
				$("#noticeIdsFk").val(data.spotCheckNotice['id']);
				$("#noticeResponsesFk").html(noticeResponsesFk);
				$("#endTimesFk").html(data.endTime);
				if(data.spotCheckNotice['noticeReportUrl'] != null){
					$("#fidFk").val(data.spotCheckNotice['id']);
				}
				$("#questionNumFk").html(questionNumFk);
        	}
		},error: function (data) {// 请求失败处理函数
            alert(data.errorMsg);
        }
    });
}

function downLoadWordFile(fid){
	if(fid){
		window.location.href = webPath + "/reportManageLog_getCanSeeWordFile.action?fid="+fid;
	}
}
function downLoadWordFileFk(fid){
	if(fid){
		window.location.href = webPath + "/spotCheckNotice_getDownNoticeWord.action?fid="+fid;
	}
}

function spotJump(){
	var uri = webPath + "/connectionHomeDetail_indexOrg.action";
	var url = webPath + "/spotCheckResult_list.action";
	changeCookie(1,uri,null,url);
}

//下级整改通知 start-=-=-=-=-=-=-=-=-=-=-=-=END
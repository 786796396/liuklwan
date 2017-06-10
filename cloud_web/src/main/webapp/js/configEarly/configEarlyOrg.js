$(function(){
// 	/隐藏头部预警
//	$("#steps").hide(); 
//	$("#guideSelection").hide();
//     var iscost=$("#iscost").val();
//     if(iscost==0){
//      //0：免费
//      $("#tab2").hide();
//     }else{
//     //1：收费
//      $("#tab2").show();
//     }
	
   //是否接收日报（仅组织单位有）  1接收 2不接收
// 	var isDailyReceive=$(":radio[name='check']:checked").val();
//    if(isDailyReceive == 2){
//    	hideShow('hideDiv1',2);
//    }
//    var isSiteReceive=$(":radio[name='isSiteReceive']:checked").val();
//    if(isSiteReceive == 2){
//    	hideShow('hideDiv2',2);
//    }
//    
//    var isNextAllSite=$(":radio[name='isNextAllSite']:checked").val();
//    if(isNextAllSite ==2){
//    	hideShow('hideDiv3',2);
//    } 
    $("#startHourOO").val($("#startHourO").val());
    $("#endHourOO").val($("#endHourO").val());
    $('.change_tab').click(function(){
        $('.change_tab').removeClass('on');
        $(this).addClass('on');
        $("#earlyType").val($(this).attr("value"));
        var n=$(this).index();
        $('.change_tab_content').hide();
        $('.change_tab_content').eq(n).show();
        if(n==0){
        	$("#startHourOO").val($("#startHourO").val());
        	$("#endHourOO").val($("#endHourO").val());
        }else{
        	$("#startHourTT").val($("#startHourT").val());
        	$("#endHourTT").val($("#endHourT").val());
        }
    });
    
	$("input").iCheck({
		checkboxClass : 'icheckbox_square-blue',
		radioClass : 'iradio_square-blue'
	});
	
	//判断预警设置是否已经初始化过数据
	var errmsgStr=$("#errmsg_id").val();
	if(errmsgStr && "0"==errmsgStr){//未初始化
		alert("尊敬的客户 您好，您还未预警设置进行数据初始化，请联系客服人员进行数据初始化");
	}
}) 
function hideShow(hideId,type){
	if(type==1){
		$("#"+hideId).show();
	}else if(type==2){
		$("#"+hideId).hide();
	}
}


/**
 * 修改站群
 */
function updateInfo(){
	var siteCode=$("#siteCode").val();
	var earlyType=$("#earlyType").val();
	//是否接收日报（仅组织单位有）  1接收 2不接收
	var isDailyReceive=$(":radio[name='check']:checked").val();
	//是否接受微信预警
	var isWxReceive=$("input[name='isWxReceive']").prop("checked")==true?1:2;
	//是否接受邮件预警
	var isEmailReceive=$("input[name='isEmailReceive']").prop("checked")==true?1:2;
	//是否接受短信预警
	var isTelReceive=$("input[name='isTelReceive']").prop("checked")==true?1:2;
//	//组织负责人是否接收预警 1接收 2不接收
	var isOrgPrincipal=$("input[name='isOrgPrincipal']").prop("checked")==true?1:2;
//	//组织联系人是否接收预警 1接收 2不接收
	var isOrgLinkman=$("input[name='isOrgLinkman']").prop("checked")==true?1:2;
	var isOrgLinkmantwo=$("input[name='orgLinkmanTwo']").prop("checked")==true?1:2;
	var isOrgLinkmanthree=$("input[name='orgLinkmanThree']").prop("checked")==true?1:2;
	
	var startHour=$("#startHourOO").val();
	var endHour=$("#endHourOO").val();
	if(isDailyReceive==1 && isTelReceive==1){//接受 并且 接受短信  则开始时间结束时间为非空
		if(startHour==null || startHour=='' || endHour==null || endHour=='' ){
			alert("开始时间或结束时间不可以为空");
			return;
		}
	}
	$.ajax( {
		type : "POST",
		url : webPath+"/configEarly_updateInfo.action",
		data :{
				//网站标识码
				'configEarly.siteCode':siteCode,
				//短信 开始时间 结束时间
				'configEarly.startHour':startHour,
				'configEarly.endHour':endHour,
				//预警设置类型(仅组织单位有)：1站群日报设置，2单位预警设置
				'configEarly.earlyType':earlyType,

				//是否接收微信预警 1接收 2不接收
				'configEarly.isWxReceive':isWxReceive,

				//是否接收邮件预警 1接收 2不接收
				'configEarly.isEmailReceive':isEmailReceive,

				//是否接收短信预警 1接收 2不接收
				'configEarly.isTelReceive':isTelReceive,

				//是否接收日报（仅组织单位有）  1接收 2不接收
				'configEarly.isDailyReceive':isDailyReceive,

				//组织负责人是否接收预警 1接收 2不接收
				'configEarly.isOrgPrincipal':isOrgPrincipal,

				//组织联系人是否接收预警 1接收 2不接收
				'configEarly.isOrgLinkman':isOrgLinkman,
				'configEarly.orgLinkmanTwo':isOrgLinkmantwo,
				'configEarly.orgLinkmanThree':isOrgLinkmanthree

//
//				//用户id
//				configEarly.userId
		},
		dataType:"json",
		async : false,
		success : function(data) {
        	alert(data.errorMsg);
		
		},
		error:function(data){
			alert(data.errorMsg);
		}
	});
}
function updateSiteInfo(){
	var siteCode=$("#siteCode").val();
	var earlyType=$("#earlyType").val();
	
	//是否接受微信预警
	var isWxReceive=$("input[name='isWxReceive_one']").prop("checked")==true?1:2;
	//是否接受邮件预警
	var isEmailReceive=$("input[name='isEmailReceive_one']").prop("checked")==true?1:2;
	//是否接受短信预警
	var isTelReceive=$("input[name='isTelReceive_one']").prop("checked")==true?1:2;
	
	//组织负责人
	var isOrgPrincipal=$("input[name='isOrgPrincipal_one']").prop("checked")==true?1:2;
	//组织联系人
	var isOrgLinkman=$("input[name='isOrgLinkman_one']").prop("checked")==true?1:2;
	var orgLinkmanTwo=$("input[name='isOrgLinkman_two']").prop("checked")==true?1:2;
	var orgLinkmanThree=$("input[name='isOrgLinkman_three']").prop("checked")==true?1:2;
	//填报负责人
	var isPrincipalReceive=$("input[name='isPrincipalReceive']").prop("checked")==true?1:2;
	//填报联系人
	var isLinkmanReceive=$("input[name='isLinkmanReceive']").prop("checked")==true?1:2;
	
	//是否接收    首页连不通实时    预警 1接收 2不接收
	var homeConnection=$("#homeConnection").prop("checked")==true?1:2;
	//是否接收首页连不通超过3%预警 1接收 2不接收
	var homeConnectionPer=$("#homeConnectionPer").prop("checked")==true?1:2;
	//是否接收严重错别字预警 1接收 2不接收
	var correctContent=$("#correctContent").prop("checked")==true?1:2;
	//是否接收网站疑似被挂码或被篡改预警 1接收 2不接收
	var modifySite=$("#modifySite").prop("checked")==true?1:2;
	//是否接收首页超过10天不更新预警 1接收 2不接收
	var notUpdateHome=$("#notUpdateHome").prop("checked")==true?1:2;
	//是否接收空白栏目超过5个预警 1接收 2不接收
	var blankChannel=$("#blankChannel").prop("checked")==true?1:2;
	//是否接收超过10个栏目不更新预警 1接收 2不接收
	var notUpdateChannel=$("#notUpdateChannel").prop("checked")==true?1:2;
	//是否接收互动回应差栏目超过三个月未回应预警 1接收 2不接收
	var securityResponse=$("#securityResponse").prop("checked")==true?1:2;
	
//	
	//网站是否接收预警  1接收 2不接收 3直接收本级门户预警
	var isSiteReceive = $(":radio[name='isSiteReceive']:checked").val();
	//下属所有网站预警时是否通知组织单位 1通知 2不通知
	var isNextAllSite = $(":radio[name='isNextAllSite']:checked").val();
	
	var startHour=$("#startHourTT").val();
	var endHour=$("#endHourTT").val();
	if(isSiteReceive!=2 && isTelReceive==1){//接受 并且 接受短信  则开始时间结束时间为非空
		if(startHour==null || startHour=='' || endHour==null || endHour=='' ){
			alert("开始时间或结束时间不可以为空");
			return;
			}
	}
	$.ajax( {
		type : "POST",
		url : webPath+"/configEarly_updateInfo.action",
		data :{
				//网站标识码
				'configEarly.siteCode':siteCode,
				//短信 开始时间 结束时间
				'configEarly.startHour':startHour,
				'configEarly.endHour':endHour,
				//预警设置类型(仅组织单位有)：1站群日报设置，2单位预警设置
				'configEarly.earlyType':earlyType,

				//是否接收微信预警 1接收 2不接收
				'configEarly.isWxReceive':isWxReceive,

				//是否接收邮件预警 1接收 2不接收
				'configEarly.isEmailReceive':isEmailReceive,

				//是否接收短信预警 1接收 2不接收
				'configEarly.isTelReceive':isTelReceive,
				
				
				//组织单位负责人是否接收预警 1接收 2不接收
				'configEarly.isOrgPrincipal':isOrgPrincipal,
				
				//组织单位联系人是否接收预警 1接收 2不接收
				'configEarly.isOrgLinkman':isOrgLinkman,
				'configEarly.orgLinkmanTwo':orgLinkmanTwo,
				'configEarly.orgLinkmanThree':orgLinkmanThree,
				//填报负责人是否接收预警 1接收 2不接收
				'configEarly.isPrincipalReceive':isPrincipalReceive,

				//填报联系人是否接收预警 1接收 2不接收
				'configEarly.isLinkmanReceive':isLinkmanReceive,
				
				//是否接收首页连不通实时预警 1接收 2不接收
				'configEarly.homeConnection':homeConnection,

//				//是否接收首页连不通超过3%预警 1接收 2不接收
				'configEarly.homeConnectionPer':homeConnectionPer,
//
//				//是否接收严重错别字预警 1接收 2不接收
				'configEarly.correctContent':correctContent,
//
//				//是否接收网站疑似被挂码或被篡改预警 1接收 2不接收
				'configEarly.modifySite':modifySite,
				
//				//是否接收首页超过10天不更新预警 1接收 2不接收
				'configEarly.notUpdateHome':notUpdateHome,
//
//				//是否接收空白栏目超过5个预警 1接收 2不接收
				'configEarly.blankChannel':blankChannel,
//
//				//是否接收超过10个栏目不更新预警 1接收 2不接收
				'configEarly.notUpdateChannel':notUpdateChannel,

//				//是否接收互动回应差栏目超过三个月未回应预警 1接收 2不接收
				'configEarly.securityResponse':securityResponse,
				
				//网站是否接收预警  1接收 2不接收 3直接收本级门户预警
				'configEarly.isSiteReceive':isSiteReceive,

				//下属所有网站预警时是否通知组织单位 1通知 2不通知
				'configEarly.isNextAllSite':isNextAllSite

				//用户id
//				'configEarly.userId':-1
		},
		dataType:"json",
		async : false,
		success : function(data) {
        	alert(data.errorMsg);
		
		},
		error:function(data){
			alert(data.errorMsg);
		}
	});
}

//$("input[type='radio']").iCheck({
//	checkboxClass : 'icheckbox_square-blue',
//	radioClass : 'iradio_square-blue'
//});
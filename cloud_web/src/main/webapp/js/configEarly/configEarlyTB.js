/**
 * 修改站群
 */
$(function(){
//隐藏头部引导
	$("#steps").hide(); 
	$("#guideSelection").hide();
	initData();
	
	$("input").iCheck({
		checkboxClass : 'icheckbox_square-blue',
		radioClass : 'iradio_square-blue'
	});
	
	//判断预警设置是否已经初始化过数据
	var errmsgStr=$("#errmsg_id").val();
	if(errmsgStr && "0"==errmsgStr){//未初始化
		alert("尊敬的客户 您好，您还未预警设置进行数据初始化，请联系客服人员进行数据初始化");
		return;
	}
});
function initData(){

	$("#isPrincipalReceive").attr("checked", $('input[name="isPrincipalReceive"]').val() == 1);
	$("#isLinkmanReceive").attr("checked", $('input[name="isLinkmanReceive"]').val() == 1);
	
	$("#homeConnection").attr("checked", $('input[name="homeConnection"]').val() == 1);
	$("#homeConnectionPer").attr("checked", $('input[name="homeConnectionPer"]').val() == 1);
	$("#correctContent").attr("checked", $('input[name="correctContent"]').val() == 1);
	$("#modifySite").attr("checked", $('input[name="modifySite"]').val() == 1);
	$("#notUpdateHome").attr("checked", $('input[name="notUpdateHome"]').val() == 1);
	$("#blankChannel").attr("checked", $('input[name="blankChannel"]').val() == 1);
	$("#notUpdateChannel").attr("checked", $('input[name="notUpdateChannel"]').val() == 1);
	$("#securityResponse").attr("checked", $('input[name="securityResponse"]').val() == 1);
	
	if($("#isSiteReceive").val() == 1){
		$("input[name='isSiteReceive'][value=1]").attr("checked",true); 
	}else{
		$("input[name='isSiteReceive'][value=2]").attr("checked",true); 
	}
	
	$("#isWxReceive").attr("checked", $('input[name="isWxReceive"]').val() == 1);
	$("#isEmailReceive").attr("checked", $('input[name="isEmailReceive"]').val() == 1);
	$("#isTelReceive").attr("checked", $('input[name="isTelReceive"]').val() == 1);
	$("#startHour").val($("#startHourP").val());
	$("#endHour").val($("#endHourP").val());
}
function updateInfoTb(){
	var siteCode=$("#siteCode").val();
	var earlyType=$("#earlyType").val();
	//负责人是否接收预警 1接收 2不接收
	var isPrincipalReceive=$("input[name='isPrincipalReceive']").prop("checked")==true?1:2;
	//联系人是否接收预警 1接收 2不接收
	var isLinkmanReceive=$("input[name='isLinkmanReceive']").prop("checked")==true?1:2;
	var isLinkmanTwo=$("input[name='isLinkmanTwo']").prop("checked")==true?1:2;
	var isLinkmanThree=$("input[name='isLinkmanThree']").prop("checked")==true?1:2;
	//是否接收首页连不通实时预警 1接收 2不接收
	var homeConnection=$("input[name='homeConnection']").prop("checked")==true?1:2;
	//是否接收首页连不通超过3%预警 1接收 2不接收
	var homeConnectionPer=$("input[name='homeConnectionPer']").prop("checked")==true?1:2;
	//是否接收严重错别字预警 1接收 2不接收
	var correctContent=$("input[name='correctContent']").prop("checked")==true?1:2;
	//是否接收网站疑似被挂码或被篡改预警 1接收 2不接收
	var modifySite=$("input[name='modifySite']").prop("checked")==true?1:2;
	//是否接收首页超过10天不更新预警 1接收 2不接收
	var notUpdateHome=$("input[name='notUpdateHome']").prop("checked")==true?1:2;
	//是否接收空白栏目超过5个预警 1接收 2不接收
	var blankChannel=$("input[name='blankChannel']").prop("checked")==true?1:2;
	//是否接收超过10个栏目不更新预警 1接收 2不接收
	var notUpdateChannel=$("input[name='notUpdateChannel']").prop("checked")==true?1:2;
	//是否接收互动回应差栏目超过三个月未回应预警 1接收 2不接收
	var securityResponse=$("input[name='securityResponse']").prop("checked")==true?1:2;
	
	//网站是否接收预警  1接收 2不接收
	var isSiteReceive=$("input[name='isSiteReceive']:checked").val();
	
	
	var isWxReceive=$("input[name='isWxReceive']").prop("checked")==true?1:2;
	var isEmailReceive=$("input[name='isEmailReceive']").prop("checked")==true?1:2;
	var isTelReceive=$("input[name='isTelReceive']").prop("checked")==true?1:2;
	var startHour=$("#startHour").val();
	var endHour=$("#endHour").val();
	
	if(isSiteReceive==1 && isTelReceive==1){//接受 并且 接受短信  则开始时间结束时间为非空
		if(startHour==null || startHour=='' || endHour==null || endHour=='' ){
			alert("开始时间或结束时间不可以为空");
			return;
		}
	}
	
	$.ajax( {
		type : "POST",
		url : webPath+"/configEarly_updateInfoTb.action",
		data :{
				//网站标识码
				'configEarly.siteCode':siteCode,
				//短信 开始时间 结束时间
				'configEarly.startHour':startHour,
				'configEarly.endHour':endHour,

				//预警设置类型(仅组织单位有)：1站群日报设置，2单位预警设置
				//'configEarly.earlyType':earlyType,

				//是否接收微信预警 1接收 2不接收
				'configEarly.isWxReceive':isWxReceive,

				//是否接收邮件预警 1接收 2不接收
				'configEarly.isEmailReceive':isEmailReceive,

				//是否接收短信预警 1接收 2不接收
				'configEarly.isTelReceive':isTelReceive,
				

				//是否接收日报（仅组织单位有）  1接收 2不接收
				//'configEarly.isDailyReceive':isDailyReceive,

				//负责人是否接收预警 1接收 2不接收
				'configEarly.isPrincipalReceive':isPrincipalReceive,

				//联系人是否接收预警 1接收 2不接收
				'configEarly.isLinkmanReceive':isLinkmanReceive,
				'configEarly.isLinkmanTwo':isLinkmanTwo,
				'configEarly.isLinkmanThree':isLinkmanThree,

				
				
				//是否接收首页连不通实时预警 1接收 2不接收
				'configEarly.homeConnection':homeConnection,

				//是否接收首页连不通超过3%预警 1接收 2不接收
				'configEarly.homeConnectionPer':homeConnectionPer,

				//是否接收严重错别字预警 1接收 2不接收
				'configEarly.correctContent':correctContent,

				//是否接收网站疑似被挂码或被篡改预警 1接收 2不接收
				'configEarly.modifySite':modifySite,

				//是否接收空白栏目超过5个预警 1接收 2不接收
				'configEarly.blankChannel':blankChannel,

				//是否接收超过10个栏目不更新预警 1接收 2不接收
				'configEarly.notUpdateChannel':notUpdateChannel,

				//是否接收首页超过10天不更新预警 1接收 2不接收
				'configEarly.notUpdateHome':notUpdateHome,

				//是否接收互动回应差栏目超过三个月未回应预警 1接收 2不接收
				'configEarly.securityResponse':securityResponse,

				//网站是否接收预警  1接收 2不接收
				'configEarly.isSiteReceive':isSiteReceive

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


/**
 * @描述:关键栏目连通性统计分析 详情
 * @作者:liujc@ucap.com.cn
 * @时间:2017-5-19下午4:06:52
 */
function channelStatisticsInfo(encodeurl,url,index){
	var dateStr=$("#datepickerChannel").val();
	var realTimeType="1";//0：实时   1：非实时
	if($("#datepickerChannel").val() == GetDateStr(0)){
		realTimeType="0";//实时
	}
	$.ajax( {
		type : "POST",
		url : webPath+"/connectionChannelDetail_channelStatisticsInfo.action",
		data :{
			dateStr : dateStr,
       	 	realTimeType : realTimeType,
       	 	encodeurl:encodeurl,
       	 	url:url
		},
		dataType:"json",
		async : false,
		success : function(data) {
			var html="";
			if(data.bodySize>0){
				html+='<thead>';
				html+='	<tr class="info">';
				html+='	<th style="width:42px">序号</th>';
				html+='	<th style="width:180px" class="th_left">发现时间</th>';
				html+='	<th style="width:100px" class="th_left">连通状态</th>';
				html+=' <th style="width:200px" class="th_left">问题描述</th>';
				html+=' </tr>';
				html+='</thead>';
				html+='<tbody>';
				$.each(data.body, function(index, obj) {
					html+='<tr role="row">';
					html+='	<td>'+(index+1)+'</td>';
					html+='<td class="tr_left">'+obj.stime+'</td>';
					html+='<td class="tr_left">'+obj.stateName+'</td>';
					html+='	<td class="tr_left">'+obj.questionDescribe+'</td>';
					html+='</tr>';
					
				});
				html+='</tbody>';
			}else{
				html+='<tr><td> <div class="zanwsj" >关键栏目无不连通状况</div></td></tr>';
			}
			$("#channel_statistics_info").html(html);
			$("#channel_statistics_div").show();
			
		}
	});
}
function closeDiv(){
	$("#channel_statistics_div").hide();
}
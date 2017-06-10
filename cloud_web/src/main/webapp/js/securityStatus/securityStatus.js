$(function(){   
	contentHtml();
$('.change_tab').click(function(){
        $('.change_tab').removeClass('on');
        $(this).addClass('on');
        $("#scanType").val($(this).attr("value"));
        var n=$(this).index();
        $('.change_tab_content').hide();
        $('.change_tab_content').eq(n).show();
        contentHtml();
    });
});

function contentHtml(){
	var type=$("#scanType").val();
	$.ajax( {
		type : "POST",
		url : webPath+"/securityStatus_queryStatus.action",
		data :{
			type:type
		},
		dataType:"json",
		async : false,
		success : function(data) {
//			$.each(data, function(index, obj) {
//				$("#queryId").val(data.id);
				if(type ==1){
					$("#button1").hide();
					$("#button2").hide();
					$("#button3").hide();
					$("#button4").hide();
					if(data.status==1){
						//未检测
						$("#button1").show();
					}else if(data.status==2){
						//监测中
//						$("#button2").show();
						$("#button3").show();
					}else if(data.status==3){
						//已完成
						$("#button1").show();
						$("#button4").show();
					}
				}else if(type ==2){
					$("#sbutton1").hide();
					$("#sbutton2").hide();
					$("#sbutton3").hide();
					$("#sbutton4").hide();
					if(data.status==1){
						//未检测
						$("#sbutton1").show();
					}else if(data.status==2){
						//监测中
//						$("#sbutton2").show();
						$("#sbutton3").show();
					}else if(data.status==3){
						//已完成
						$("#sbutton1").show();
						$("#sbutton4").show();
					}
				}
			
//			})
		}
	});
	
}



function updateStatus(){
	var type=$("#scanType").val();
//	var queryId=$("#queryId").val();
	
	$.ajax( {
		type : "POST",
		url : webPath+"/securityStatus_updateStatus.action",
		data :{
			type:type
		},
		dataType:"json",
		async : false,
		success : function(data) {
		}
	});
	if(type==1){
		$("#button1").hide();
		$("#button2").hide();
		$("#button3").show();
		$("#button4").hide();
	}else if(type==2){
		$("#sbutton1").hide();
		$("#sbutton2").hide();
		$("#sbutton3").show();
		$("#sbutton4").hide();
	}
	
}
var level = null;
var state = null;

$(function (){
	level = $("#level").val();
	state = $("#state").val();
	lowerLevelTable();
});

function lowerLevelTable(){
 	 $.ajax( {  
         type : "POST",  
         url : webPath+"/manageInfo_getlowerLevelList.action",  
         data : {
        	 level:level,
        	 state:state
         },  
         dataType:"json",
         async : false,  
         success : function(data) {
        	 if(data.success == 'true'){
        		 $("#lowerLevelTable").html("");
        		 var html = '';
        		 var list = data.body;
        		 var num = 0;
        		 if(list.length > 0 && list != null){
        			 $.each(list, function(index, obj) {
        				 num++;
        				 var url = obj.url;
        				 if(url != ""){
        					 if(!url.match("http")){
     	        				 url="http://"+url;
     	        			 }
        				 }
        				 
        				 $("#lowerLevelTable").append('<tr>'
        						 + '<td class="td-left" style="width:10%;text-align: left;">'+num+'</td>'
        						 + '<td class="td-left" style="width:10%;text-align: left;"><a onClick="fillJumpTb();" href="'+webPath+'/fillUnit_gailan.action?siteCode='+obj.siteCode+'" target="_blank" class="sort-num cor-01a5ec url">'+obj.siteCode+'</a></td>'
        						 + '<td class="td-left" style="width:10%;text-align: left;" ><a href="'+url+'" target="_blank" class="sort-num cor-01a5ec url">'+obj.name+'</a>'
        						 + '<div class="chouchover-div" ></div><div class="info-con" style="display:none">'
                                 + '<div><label>单位名称：</label>'+obj.siteManageUnit+'</div>'
                                 + '<div><label>办公地址：</label>'+(obj.officeAddress||"无")+'</div>'
                                 + '<h3>负责人信息</h3>'
                                 + '<div><label>姓名：</label>'+obj.relationName+'&nbsp;&nbsp;<label>手机：</label>'+obj.relationCellphone+'&nbsp;&nbsp;<label>办公电话：</label>'+obj.relationPhone+'&nbsp;&nbsp;<label>电子邮箱：</label>'+obj.relationEmail+'</div>'
                                 + '<h3>联系人信息</h3>'
                                 + '<div><label>姓名：</label>'+obj.linkman+'&nbsp;&nbsp;<label>手机：</label>'+obj.linkmanCellphone+'&nbsp;&nbsp;<label>办公电话：</label>'+obj.linkmanPhone+'&nbsp;&nbsp;<label>电子邮箱：</label>'+obj.linkmanEmail+'</div>'
                                 + '</div>'
                                 + '</div></td>'
                                 + '<td class="td-center" style="width:10%;text-align: left;" >'+obj.director+'</td>'
                                 + '<td class="td-center" style="width:10%;text-align: left;">'+obj.channelPointNum+'</td>'
                                 + '<td class="td-center"><a onclick="showChannelInfo(\''+obj.siteCode+'\',\''+obj.name+'\')"><i class="preview-icon"></i></a></td>'
                                 +'</tr>');
        			 });
        		 } 
        	 }
         },
         error:function(data){
 			alert(data.errorMsg);
 		 }
       });
}


/**
 * 弹窗显示站点栏目信息
 */
function showChannelInfo(siteCode,siteName){
	$('#edit').modal();
	$("#column_undefined").hide();
	$("#principalName").html("");
	$("#principalTelephone").html("");
	$("#principalMobile").html("");
	$("#principalEmail").html("");
	$("#linkmanNameChannle").html("");
	$("#telephone2Chanele").html("");
	$("#mobile2Chanele").html("");
	$("#email2Chanele").html("");
	scrollTop = $(document).scrollTop();
    $(".page-wrap").addClass("page-fixed");
    $(document).scrollTop(0);
    $("#channelList1").html("");
    $("#myModalLabel_show").html("");
    $("#myModalLabel_show").html(siteCode+"-"+siteName);
    var tempLate = "";
    $.ajax({
        async: false,
        type: "GET",
        url: webPath + "/manageInfo_getWebChannelInfos.action?siteCode="+siteCode,
        dataType: "json",
        contentType: "application/text"
    }).done(function (response) {
    	if(!response.items || !response.items.length){
    		$("#column_undefined").show();
    		$("#principalName").html(response.principalName);
    		$("#principalTelephone").html(response.telephone);
    		$("#principalMobile").html(response.mobile);
    		$("#principalEmail").html(response.email);
    		$("#linkmanNameChannle").html(response.linkmanName);
    		$("#telephone2Chanele").html(response.telephone2);
    		$("#mobile2Chanele").html(response.mobile2);
    		$("#email2Chanele").html(response.email2);
    	}else{
    		tempLate = "<table id='table_data_channel_columnTab' cellpadding='0' cellspacing='0' class='table dblclick-tab'><tbody>";
    		tempLate = tempLate + "<tr><th>序号</th><th>名称</th><th>URL</th><th>跳转URL</th><th>更新期限</th><th>类别</th><th>子类</th><th>监测状态</th></tr>";
    		_.each(response.items,function(item,i){
    			tempLate = tempLate + "<tr>";
    			tempLate = tempLate + "<td>"+(i+1)+"</td>";
    			tempLate = tempLate + "<td><div class='ellipsis-box ellipsis-w2' title='"+(item.channelName||"无")+"'>"+(item.channelName||"无")+"</div></td>";
    			tempLate = tempLate + "<td><div class='ellipsis-box ellipsis-w1' title='"+(item.channelUrl||"无")+"'>"+(item.channelUrl||"无")+"</div></td>";
    			tempLate = tempLate + "<td><div class='ellipsis-box ellipsis-w2' title='"+(item.jumpUrl||"无")+"'>"+(item.jumpUrl||"无")+"</div></td>";
    			tempLate = tempLate + "<td><div class='ellipsis-box ellipsis-w2' title='"+(item.updateTime||"无")+"'>"+(item.updateTime||"-")+"</div></td>";
    			tempLate = tempLate + "<td>"+(item.dicChannelName||"无")+"</td>";
    			tempLate = tempLate + "<td>"+(item.dicChannelSonIdName||"无")+"</td>";
    			
    			//var linkStatus = item.linkStatus;
    			tempLate = tempLate + "<td><div>";
    			if(item.status || item.status == 1 || item.status == "1"){
    				tempLate = tempLate + "<div class='ellipsis-box ellipsis-w4-c'><span class='status_blue'>正常</span><div>";
    				//tempLate = tempLate + "<span class='status_red'>监测异常</span> <span class='status_normal'>连不通，请检查URL或连通性</span>";
    			}else{
    				tempLate = tempLate + "<div class='ellipsis-box ellipsis-w4-c'><span class='status_red' title='未设置监测该栏目'>未监测</span><div>";
    			}
    			tempLate = tempLate + "</div></td>";
    		});
    		tempLate = tempLate + "</tbody></table>";
    	}
    	$("#channelList1").html(tempLate);    
    }).fail(function (data) {
    	alert("错误！");
    }).always(function () {

    });
}

function closeId(){
	 $(".page-wrap").removeClass("page-fixed");
}
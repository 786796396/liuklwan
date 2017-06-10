$(window).resize(function (){
    $("#tabHeaderfixed").width($("#tabHeaderfixed").parent(".tab-header-fixed-box").width());
});
$(function(){
	qwert();//加载新菜单引导层
	initAdvert();
	$("#cur-p").click(function(){
		$(".second-box").show();
		if($(".second-box").hasClass("on")){
			$(".second-box").removeClass("on");
			$(".second-box").hide();
		}else{
			$(".second-box").addClass("on");
			$(".second-box").show();
		}
		
	});
	$("#icoId").click(function() {
		$(".second-box").show();
		if($(".second-box").hasClass("on")){
			$(".second-box").removeClass("on");
			$(".second-box").hide();
		}else{
			$(".second-box").addClass("on");
			$(".second-box").show();
		}
	});
	
	
    if(iscost==0){
        $("#freeFont").html("昨天");
		//内容保障问题        
        $("#saveQuestion").removeAttr("onclick");
        $("#saveQuestion").attr("onclick","saveQuestionFree()");
    }else{
        $("#freeFont").html("周期");
    }
	$("#tabHeaderfixed").width($("#tabHeaderfixed").parent(".tab-header-fixed-box").width());
	var tabHeaderTop=document.getElementById("tabHeaderfixed").offsetTop-105;
	$(window).scroll(function (e) {
		if(tabHeaderTop < $(window).scrollTop()){
			$("#tabHeaderfixed").addClass("tab-head-fixed");
		}else{
			$("#tabHeaderfixed").removeClass("tab-head-fixed");
		}
		return false;
	});
	
	nameHover("listOrg");
    var sTime;
    $(".no-data-box").click(function(){
        clearTimeout(sTime)
        if($(this).find(".no-data").css("display")=="none"){
            $(this).find(".no-data").fadeIn();
        }
    });
    $(".no-data-box").mouseleave(function(){
        sTime=setTimeout(function(){
            if($(".no-data-box").find(".no-data").css("display")=="block"){
                $(".no-data-box").find(".no-data").fadeOut();
            }
        },2000);
    });
	
    $(".star-msg").hover(function(){
        $(this).find(".star-msg-main").fadeIn();
    },function(){
        $(this).find(".star-msg-main").fadeOut();
    });
	$("input[type='radio']").iCheck({
		checkboxClass : 'icheckbox_square-blue',
		radioClass : 'iradio_square-blue'
	});
	$("#searchInfo").iePlaceholder();
	//列表检索
    $("#searchInfo").keyup(function(){
        var searchInfo=$("#searchInfo").val();
		 if(searchInfo==""){
			 $("#index_org_table tr").show();
		 }else{
			 $("#index_org_table").find(".wz-name").each(function(index, element) {
				 if($(this).html().indexOf(searchInfo)<0){
					$(this).parents("tr").hide();
				 }else{
					 $(this).parents("tr").show();
				 }
			});
		 }
    });
	
	$("#link_All").on('ifClicked', function(event){
      	document.getElementById("link_Home_div").style.display ="none";
 		document.getElementById("link_All_div").style.display ="block";
 		link_all();
    }); 
	$("#link_Home").on('ifClicked', function(event){
      	document.getElementById("link_Home_div").style.display ="block";
 		document.getElementById("link_All_div").style.display ="none";
 		link_keyong();
    });
	$("#index_org_tableSort th").on('click', function(event){
		if($(this).find(".tab_angle").hasClass("tab_angle_bottom")){
			$("#index_org_table .tab_angle").attr("class","tab_angle");
			$(this).find(".tab_angle").addClass("tab_angle_top");
			$(this).find(".tab_angle").removeClass("tab_angle_bottom");
		}else{
			$("#index_org_table .tab_angle").attr("class","tab_angle");
			$(this).find(".tab_angle").addClass("tab_angle_bottom");
			$(this).find(".tab_angle").removeClass("tab_angle_top");
		}
    });
	
//    //健康指数
//    healthIndex();
//   	//健康指数折线图
//    healthLine();
//    //网站连通性
//    websiteConnection();
//	//安全问题
//	getQuesSum();
//	 //组织单位  内容保障 栏目不更新色块数据
//    sSecurityMessage();
//    //首页table
//    indexOrgSum();
//  
//	//列表排序
//	new TableSorter("index_org_table",2,3,4,5,6,7,8);

});

//新菜单引导层
function qwert(){
	var guideState = $("#guideState").val();
	if(guideState != 1){
		$('ul li').removeClass('active');
		 $(document.body).css({
		   "overflow-x":"hidden",
		   "overflow-y":"hidden"
		 });
		 var i = $('.bgzg').parent().parent();
		    $('body').pagewalkthrough({
		        name: 'introduction',
		        steps: [
				{ popup: {content: '#walkthrough-1',type: 'modal', style:'left:60px;bottom:18px;'}
		        }, {wrapper: '#center-part',popup: {content: '#walkthrough-2',type: 'tooltip',position: 'bottom',style:'left:90px;top:120px;'}
		        }, {wrapper: '#ulId',popup: {content: '#walkthrough-3',type: 'tooltip',position: 'bottom',style:'left:90px;bottom:40px;'}
		        }, {wrapper: '#ulTwId',popup: {content: '#walkthrough-4',type: 'tooltip',position: 'bottom',style:'left:90px;bottom:45px;'}
		        }, {wrapper: i,popup: {content: '#walkthrough-5',type: 'tooltip',position: 'right',style:'left:60px;bottom:36px;'}
		        },{wrapper: '#glLiId',popup: {content: '#walkthrough-6',type: 'tooltip',position: 'bottom'}
		        ,stayFocus:true
		        }],onClose:function(){
					 $.ajax({
		 				type : "POST",
		 				url :webPath+"/databaseInfo_updateGuiteState.action",
		 				data:{
		 					siteCode:siteCode,
		 				},
		 				async : false,
		 				dataType:"json",
		 				success : function(data) {
		 				$("#walkthrough-content").hide();
		 				$('ul li').eq(0).addClass('active');
		 				 $(document.body).css({
						   "overflow-x":"auto",
						   "overflow-y":"auto"
						 });
		 				}
		 			});
				}
		    });
		    $('body').pagewalkthrough('show');
		 }
};

// 新菜单引导关闭
function closeYindao(){
	
	$.ajax({
			type : "POST",
			url :webPath+"/databaseInfo_updateGuiteState.action",
			data:{
				siteCode:siteCode
			},
			async : false,
			dataType:"json",
			success : function(data) {
				$(document.body).css({
					   "overflow-x":"auto",
					   "overflow-y":"auto"
				});
				//修改状态成功后隐藏
				$("#walkthrough-content").hide();
				$("#jpWalkthrough").hide();
				$("#jpwOverlay").hide();
			}
		});
}

//获取问题数
function getQuesSum(){
	$.ajax({  
		type : "POST",    
		url : webPath+"/monitorSilentResultOrg_getOrgQuesData.action", 
		data:{orgType:isOrgSafetyService},
		dataType:"json",
		async : false,  
		success : function(data) {
			if(data.success == 'true'){
				$("#quesSum").html(data.quesSum);
				$("#quesSumId").html(data.quesSum);
				$("#riskNum").html(data.riskNum);
			}
		}
	});
}

//首页table
function indexOrgSum(){
 $.ajax({  
        type : "POST",    
        url : webPath+"/connectionHomeDetail_getSumResult.action", 
        dataType:"json",
        async : false,  
        success : function(data) {
        	var connNew = data.connNew;
        	var contguaranteNew = data.contguaranteNew;
        	var contcorrectNew = data.contcorrectNew;
        	
        	var conn = data.conn; // 总不连通次数
        	var coHome = data.coHome; // 首页不连通数
        	var coChannel = data.coChannel; // 栏目不连通次数
        	var coBusiness = data.coBusiness; // 业务系统不连通数
        	$("#connId").html(conn);
        	$("#coHomeId").html(coHome);
        	$("#coChannelId").html(coChannel);
        	$("#coBusinessId").html(coBusiness);
        	
        	var link = data.link; // 总链数
        	var lkHome = data.lkHome; // 首页死链数
        	//$("#linkId").html(link);
        	$("#lkHomeId").html(lkHome);
        	
        	depthLinkAllSum(lkHome);
        	
        	var contguarante = data.contguarante; // 内容保障问题总数
        	var seHome = data.seHome; // 内容保障：首页不更新
        	var seChannel = data.seChannel; // 内容保障：栏目不更新
        	var seChannelId = $("#seChannelId").html();
        	if(seChannelId == '未监测'){
        		seChannelId = "0";
        	}
        	$("#contguaranteId").html(parseInt(seHome) + parseInt(seChannelId));
        	$("#seHomeId").html(seHome);
        	//$("#seChannelId").html(seChannel);
        	
        	var contcorrect = data.contcorrect; // 疑似错别字
        	$("#contcorrect").html(contcorrect);
        	$("#contcorrectId").html(contcorrect);
        	
        	var contenUpdate = data.contenUpdate; // 内容更新总数
        	var upHome = data.upHome; // 首页更新数
        	var upChannel = data.upChannel; // 栏目更新数
        	$("#contenUpdateId").html(contenUpdate);
        	$("#upHomeId").html(upHome);
        	$("#upChannelId").html(upChannel);
        	
        	//列表
        	$("#listOrg").html("");
        	var isorgHtml = "";
        	var levelList = data.levelList;
        	var listOrg = data.listOrg;
        	if(levelList != null && levelList.length > 0 ){
        		$("#indexOrgExcel").html("<i></i>导出列表");
        		$("#indexOrgExcel").addClass("page-btn1");
        		$("#indexOrgExcel").attr("onclick","indexOrgExcel()");
        		
        		var siteList = "";
        		
        		var uriMonitor = webPath + "/monitorSilentResult_indexTB.action";
        		var uriHomeInfo = webPath + "/homeInfoUnUpdate_index.action";
        		var uriHome = webPath + "/connectionHomeDetail_index.action";
        		var urilinkHome = webPath + "/linkHome_linkHomeIndex.action";
        		var uriCorrect = webPath + "/correctContent_index.action";
        		var urijumpPage = webPath + "/updateHome_updateHomeIndex.action";
        		
        		$.each(levelList, function(index, obj){
        			siteList += obj.siteCode+",";
        			var url = obj.url;
            		if(url.substr(0, 4)!='http'){
            			url = "http://"+url;
            		}

            		var isorgHtml = "";
					var isorganizational = obj.isorganizational;
					if(isorganizational == 1){
						isorgHtml = "<td class='td_left'><div class='info-box'><i class='star-icon'></i><a target='_blank' class='wz-name wz-name-w2' href='"+url+"'>"+obj.siteName+"</a>"
            		}else {
            			isorgHtml = "<td class='td_left'><div class='info-box'><a target='_blank' class='wz-name wz-name-w2' href='"+url+"'>"+obj.siteName+"</a>"
            		}

					var aHtml = "";
            		if(obj.isOrgCost == 0){      // 0 未开通   1 已开通
            			aHtml = "<td style='width:80px;'><a href='http://jg.kaipuyun.cn/ce/banben/version.shtml' target='_blank'><font color='#08c'>未开通</font></a></td>";
            		}else if(obj.isOrgCost == 1){
            			if(obj.silentType == 0){  // 0未检测，1检测中，2已结束
                			aHtml = "<td style='width:80px;' title='未开通安全监测服务或服务已到期' ><font color='#08c'>未检测</font></td>";
                		}else if(obj.silentType == 1){
                			aHtml = "<td style='width:80px;'><a class='sort-num' title='已检测' target='_blank' onclick='jumpTBPage(\""+uriMonitor+"\")' href='"+webPath+"/monitorSilentResult_indexTB.action?siteCode="+obj.siteCode+"'><font color='#08c'>"+obj.silentNum+"</font></a></td>";
                		}else if(obj.silentType == 2){
                			aHtml = "<td style='width:80px;' title='安全监测服务已到期，显示最后一天问题数' ><font color='red'>"+obj.silentNum+"</font></td>";
                		}
            		}
            	
            		var uHtml = "";
            		if(obj.securityHome == 1){  // 0  正常更新   1  超过2周
            			uHtml = "<td style='width:110px;'><a class='sort-num' target='_blank' onclick='jumpTBPage(\""+uriHomeInfo+"\")' href='"+webPath+"/homeInfoUnUpdate_index.action?siteCode="+obj.siteCode+"'><font color='red'>超过2周</font></a></td>";
            		}else{
            			uHtml = "<td style='width:115px;'><font color='#000000'>正常更新</font></td>";
            		}
            		
            		$("#listOrg").append("<tr>" +
            				"<td class='td_left font_701999' style='width:80px;'><a target='_blank' onclick='fillJumpTb()' href='"+webPath+"/fillUnit_gailan.action?siteCode="+obj.siteCode+"'>"+obj.siteCode+"</a></td>" 
            				+ isorgHtml
    	            		+ "<div class='chouc-hover-div'></div><div class='info-con'>"
                            + "<div><label>单位名称：</label>"+obj.siteManageUnit+"</div>"
                            + "<div><label>办公地址：</label>"+(obj.officeAddress||"无")+"</div>"
                            + "<h3>负责人信息</h3>"
                            + "<div><label>姓名：</label>"+obj.relationName+"&nbsp;&nbsp;<label>手机：</label>"+obj.relationCellphone+"&nbsp;&nbsp;<label>办公电话：</label>"+obj.relationPhone+"&nbsp;&nbsp;<label>电子邮箱：</label>"+obj.relationEmail+"</div>"
                            + "<h3>联系人信息</h3>"
                            + "<div><label>姓名：</label>"+obj.linkman+"&nbsp;&nbsp;<label>手机：</label>"+obj.linkmanCellphone+"&nbsp;&nbsp;<label>办公电话：</label>"+obj.linkmanPhone+"&nbsp;&nbsp;<label>电子邮箱：</label>"+obj.linkmanEmail+"</div>"
                            + "</div>"
                            + "</div></td>"
                            + "<td style='width:80px;'><a class='sort-num' target='_blank' onclick='fillJumpTb()' href='"+webPath+"/fillUnit_gailan.action?siteCode="+obj.siteCode+"'>"+obj.convertScores+"</a></td>"
	        				+ "<td style='width:105px;'><a class='sort-num' target='_blank' onclick='jumpTBPage(\""+uriHome+"\")'  href='"+webPath+"/connectionHomeDetail_index.action?siteCode="+obj.siteCode+"'>"+obj.connHome+"</a></td>"
	        				+ "<td style='width:135px;'><a class='sort-num' target='_blank' onclick='jumpTBPage(\""+urilinkHome+"\")' href='"+webPath+"/linkHome_linkHomeIndex.action?siteCode="+obj.siteCode+"'>"+obj.linkNum+"</a></td>"
	        				+ uHtml
	        				+ "<td style='width:80px;'><a class='sort-num' target='_blank' onclick='jumpTBPage(\""+uriCorrect+"\")' href='"+webPath+"/correctContent_index.action?siteCode="+obj.siteCode+"'>"+obj.contCorrectNum+"</a></td>"
	        				+ aHtml
	        				+ "<td style='width:80px;'><a class='sort-num' target='_blank' onclick='jumpTBPage(\""+urijumpPage+"\")' href='"+webPath+"/updateHome_updateHomeIndex.action?siteCode="+obj.siteCode+"'>"+obj.contUpdate+"</a></td></tr>");
        		});
        		
        		if(listOrg != null && listOrg.length > 0){
        			$.each(listOrg, function(index, org) {
               			if (siteList.match(org.siteCode)==null) { 
    						var url = org.url;
                    		if(url.substr(0, 4)!='http'){
                    			url = "http://"+url;
                    		}
    						
    						var isorgHtml = "";
        					var isorganizational = org.isorganizational;
        					if(isorganizational == 1){
        						isorgHtml = "<td class='td_left'><div class='info-box'><i class='star-icon'></i><a target='_blank' class='wz-name wz-name-w2' href='"+url+"'>"+org.siteName+"</a>"
                    		}else {
                    			isorgHtml = "<td class='td_left'><div class='info-box'><a target='_blank' class='wz-name wz-name-w2' href='"+url+"'>"+org.siteName+"</a>"
                    		}
                    		
                    		var aHtml = "";
                    		if(org.isOrgCost == 0){      // 0 未开通   1 已开通
                    			aHtml = "<td style='width:80px;'><a href='http://jg.kaipuyun.cn/ce/banben/version.shtml' target='_blank'><font color='#08c'>未开通</font></a></td>";
                    		}else if(org.isOrgCost == 1){
                    			if(org.silentType == 0){  // 0未检测，1检测中，2已结束
                        			aHtml = "<td style='width:80px;' title='未开通安全监测服务或服务已到期' ><font color='#08c'>未检测</font></td>";
                        		}else if(org.silentType == 1){
                        			aHtml = "<td style='width:80px;'><a class='sort-num' title='已检测' target='_blank' onclick='jumpTBPage(\""+uriMonitor+"\")' href='"+webPath+"/monitorSilentResult_indexTB.action?siteCode="+org.siteCode+"'><font color='#08c'>"+org.silentNum+"</font></a></td>";
                        		}else if(org.silentType == 2){
                        			aHtml = "<td style='width:80px;' title='安全监测服务已到期，显示最后一天问题数' ><font color='red'>"+org.silentNum+"</font></td>";
                        		}
                    		}
                    	
                    		var uHtml = "";
                    		if(org.securityHome == 1){  // 0  正常更新   1  超过2周
                    			uHtml = "<td style='width:110px;'><a class='sort-num' target='_blank' onclick='jumpTBPage(\""+uriHomeInfo+"\")' href='"+webPath+"/homeInfoUnUpdate_index.action?siteCode="+org.siteCode+"'><font color='red'>超过2周</font></a></td>";
                    		}else{
                    			uHtml = "<td style='width:115px;'><font color='#000000'>正常更新</font></td>";
                    		}
                    		
                    		$("#listOrg").append("<tr>" +
                    				"<td class='td_left font_701999' style='width:80px;'><a target='_blank' onclick='fillJumpTb()' href='"+webPath+"/fillUnit_gailan.action?siteCode="+org.siteCode+"'>"+org.siteCode+"</a></td>" 
                    				+ isorgHtml
            	            		+ "<div class='chouc-hover-div'></div><div class='info-con'>"
                                    + "<div><label>单位名称：</label>"+org.siteManageUnit+"</div>"
                                    + "<div><label>办公地址：</label>"+(org.officeAddress||"无")+"</div>"
                                    + "<h3>负责人信息</h3>"
                                    + "<div><label>姓名：</label>"+org.relationName+"&nbsp;&nbsp;<label>手机：</label>"+org.relationCellphone+"&nbsp;&nbsp;<label>办公电话：</label>"+org.relationPhone+"&nbsp;&nbsp;<label>电子邮箱：</label>"+org.relationEmail+"</div>"
                                    + "<h3>联系人信息</h3>"
                                    + "<div><label>姓名：</label>"+org.linkman+"&nbsp;&nbsp;<label>手机：</label>"+org.linkmanCellphone+"&nbsp;&nbsp;<label>办公电话：</label>"+org.linkmanPhone+"&nbsp;&nbsp;<label>电子邮箱：</label>"+org.linkmanEmail+"</div>"
                                    + "</div>"
                                    + "</div></td>"
                                    + "<td style='width:80px;'><a class='sort-num' target='_blank' onclick='fillJumpTb()' href='"+webPath+"/fillUnit_gailan.action?siteCode="+org.siteCode+"'>"+org.convertScores+"</a></td>"
        	        				+ "<td style='width:105px;'><a class='sort-num' target='_blank' onclick='jumpTBPage(\""+uriHome+"\")'  href='"+webPath+"/connectionHomeDetail_index.action?siteCode="+org.siteCode+"'>"+org.connHome+"</a></td>"
        	        				+ "<td style='width:135px;'><a class='sort-num' target='_blank' onclick='jumpTBPage(\""+urilinkHome+"\")' href='"+webPath+"/linkHome_linkHomeIndex.action?siteCode="+org.siteCode+"'>"+org.linkNum+"</a></td>"
        	        				+ uHtml
        	        				+ "<td style='width:80px;'><a class='sort-num' target='_blank' onclick='jumpTBPage(\""+uriCorrect+"\")' href='"+webPath+"/correctContent_index.action?siteCode="+org.siteCode+"'>"+org.contCorrectNum+"</a></td>"
        	        				+ aHtml
        	        				+ "<td style='width:80px;'><a class='sort-num' target='_blank' onclick='jumpTBPage(\""+urijumpPage+"\")' href='"+webPath+"/updateHome_updateHomeIndex.action?siteCode="+org.siteCode+"'>"+org.contUpdate+"</a></td></tr>");
               			}	
               		});
        		}
        	}else if(listOrg != null && listOrg.length > 0){
	        		$("#indexOrgExcel").html("<i></i>导出列表");
	        		$("#indexOrgExcel").addClass("page-btn1");
	        		$("#indexOrgExcel").attr("onclick","indexOrgExcel()");
        			$.each(listOrg, function(index, org) {
    						var url = org.url;
                    		if(url.substr(0, 4)!='http'){
                    			url = "http://"+url;
                    		}
    						
    						var isorgHtml = "";
        					var isorganizational = org.isorganizational;
        					if(isorganizational == 1){
        						isorgHtml = "<td class='td_left'><div class='info-box'><i class='star-icon'></i><a target='_blank' class='wz-name wz-name-w2' href='"+url+"'>"+org.siteName+"</a>"
                    		}else {
                    			isorgHtml = "<td class='td_left'><div class='info-box'><a target='_blank' class='wz-name wz-name-w2' href='"+url+"'>"+org.siteName+"</a>"
                    		}
                    		
                    		var aHtml = "";
                    		if(org.isOrgCost == 0){      // 0 未开通   1 已开通
                    			aHtml = "<td style='width:80px;'><a href='http://jg.kaipuyun.cn/ce/banben/version.shtml' target='_blank'><font color='#08c'>未开通</font></a></td>";
                    		}else if(org.isOrgCost == 1){
                    			if(org.silentType == 0){  // 0未检测，1检测中，2已结束
                        			aHtml = "<td style='width:80px;' title='未开通安全监测服务或服务已到期' ><font color='#08c'>未检测</font></td>";
                        		}else if(org.silentType == 1){
                        			aHtml = "<td style='width:80px;'><a class='sort-num' title='已检测' target='_blank' onclick='jumpTBPage(\""+uriMonitor+"\")' href='"+webPath+"/monitorSilentResult_indexTB.action?siteCode="+org.siteCode+"'><font color='#08c'>"+org.silentNum+"</font></a></td>";
                        		}else if(org.silentType == 2){
                        			aHtml = "<td style='width:80px;' title='安全监测服务已到期，显示最后一天问题数' ><font color='red'>"+org.silentNum+"</font></td>";
                        		}
                    		}
                    	
                    		var uHtml = "";
                    		if(org.securityHome == 1){  // 0  正常更新   1  超过2周
                    			uHtml = "<td style='width:110px;'><a class='sort-num' target='_blank' onclick='jumpTBPage(\""+uriHomeInfo+"\")' href='"+webPath+"/homeInfoUnUpdate_index.action?siteCode="+org.siteCode+"'><font color='red'>超过2周</font></a></td>";
                    		}else{
                    			uHtml = "<td style='width:115px;'><font color='#000000'>正常更新</font></td>";
                    		}
                    		
                    		$("#listOrg").append("<tr>" +
                    				"<td class='td_left font_701999' style='width:80px;'><a target='_blank' onclick='fillJumpTb()' href='"+webPath+"/fillUnit_gailan.action?siteCode="+org.siteCode+"'>"+org.siteCode+"</a></td>" 
                    				+ isorgHtml
            	            		+ "<div class='chouc-hover-div'></div><div class='info-con'>"
                                    + "<div><label>单位名称：</label>"+org.siteManageUnit+"</div>"
                                    + "<div><label>办公地址：</label>"+(org.officeAddress||"无")+"</div>"
                                    + "<h3>负责人信息</h3>"
                                    + "<div><label>姓名：</label>"+org.relationName+"&nbsp;&nbsp;<label>手机：</label>"+org.relationCellphone+"&nbsp;&nbsp;<label>办公电话：</label>"+org.relationPhone+"&nbsp;&nbsp;<label>电子邮箱：</label>"+org.relationEmail+"</div>"
                                    + "<h3>联系人信息</h3>"
                                    + "<div><label>姓名：</label>"+org.linkman+"&nbsp;&nbsp;<label>手机：</label>"+org.linkmanCellphone+"&nbsp;&nbsp;<label>办公电话：</label>"+org.linkmanPhone+"&nbsp;&nbsp;<label>电子邮箱：</label>"+org.linkmanEmail+"</div>"
                                    + "</div>"
                                    + "</div></td>"
                                    + "<td style='width:80px;'><a class='sort-num' target='_blank' onclick='fillJumpTb()' href='"+webPath+"/fillUnit_gailan.action?siteCode="+org.siteCode+"'>"+org.convertScores+"</a></td>"
        	        				+ "<td style='width:105px;'><a class='sort-num' target='_blank' onclick='jumpTBPage(\""+uriHome+"\")'  href='"+webPath+"/connectionHomeDetail_index.action?siteCode="+org.siteCode+"'>"+org.connHome+"</a></td>"
        	        				+ "<td style='width:135px;'><a class='sort-num' target='_blank' onclick='jumpTBPage(\""+urilinkHome+"\")' href='"+webPath+"/linkHome_linkHomeIndex.action?siteCode="+org.siteCode+"'>"+org.linkNum+"</a></td>"
        	        				+ uHtml
        	        				+ "<td style='width:80px;'><a class='sort-num' target='_blank' onclick='jumpTBPage(\""+uriCorrect+"\")' href='"+webPath+"/correctContent_index.action?siteCode="+org.siteCode+"'>"+org.contCorrectNum+"</a></td>"
        	        				+ aHtml
        	        				+ "<td style='width:80px;'><a class='sort-num' target='_blank' onclick='jumpTBPage(\""+urijumpPage+"\")' href='"+webPath+"/updateHome_updateHomeIndex.action?siteCode="+org.siteCode+"'>"+org.contUpdate+"</a></td></tr>");
               		});
        	}else{
            	$("#listOrg").html("<tr><td colspan='9'>无数据</td></tr>");
        	}
        	$(".tab_box1 table tr:odd").attr("class","odd-bg");
        	
        }
     });
}
//excel 导出
function indexOrgExcel(){
	window.location.href=webPath+"/connectionHomeDetail_indexOrgExcel.action";
}

/**
 * 昨天健康指数
 * @param {} menuType---》省市县类别
 */
function healthIndex(){
	$.ajax({  
		type : "POST",    
		url : webPath+"/indexCount_getIndexSum.action", 
		dataType:"json",
		async : false,  
		success : function(data) {
			var differential=data.differential;
			var differentialRate = data.differentialRate;
			//折算分数
			var convertScores=data.convertScores;
			//健康分数
			var totalSumNumber=data.totalSumNumber;
			var leadSum=data.leadSum;
			if(leadSum==0){
				leadSum = "1";
			}
			$("#health_index").next().text("领先全国"+leadSum+"%的政府网站");
            //半圆进度
            var gauge = new Donut(document.getElementById("chart-gauge"));
            gauge.lineWidth = 7;
            gauge.options = {
                lineWidth: 0.05,
                colorStart: "#ffffff",
                colorStop: "#ffffff",
                strokeColor: "#25902a",
                shadowColor: "#25902a",
                angle: 0.26
            };
            gauge.maxValue = 100;
            gauge.set(convertScores==0?1:convertScores);
            //半圆进度end
			if(convertScores>=90){
				$("#sum_style").text("优");
                //$("#sum_style").parents(".z_box1").css("background-color","#4cb050");
			}else if(convertScores>=70){
				$("#sum_style").text("良");
               // $("#sum_style").parents(".z_box1").css("background-color","#00bcd5");
			}else if(convertScores>=60){
				$("#sum_style").text("中");
                //$("#sum_style").parents(".z_box1").css("background-color","#ff9700");
			}else{
				$("#sum_style").text("差");
                //$("#sum_style").parents(".z_box1").css("background-color","#f44236");
			}
			$("#health_index").text(totalSumNumber);
//            $("#health_index").text("2105.92");
//            $("#totalSumNumber").text(138.30+" +"+0.56+"%");
			if(differential>0){
                $("#totalSumNumber").html(differential+"&nbsp;&nbsp;(+"+differentialRate+")");
			}else{
				$("#index_ico").addClass("down");
                $("#totalSumNumber").html(differential+"&nbsp;&nbsp;("+differentialRate+")");
			}
		}
	});
}
/**
 * 健康指数折线图
 * @param {} menuType---》省市县类别
 */
function healthLine(){
	var allIndexlist = new Array();
	var datelist = new Array();
	var indexlist = new Array();
	var siteName="";
	var isBm="";
	var name = "";
	$.ajax({  
		type : "POST",    
		url : webPath+"/indexCount_getLineSum.action", 
		dataType:"json",
		async : false,  
		success : function(data) {
			allIndexlist=data.allIndexlist;
			datelist=data.datelist;
			indexlist=data.indexlist;
//			name = data.siteName;
			if(data.siteCode=="bm0100"){
				name = "国务院政府网";
			}else{
				name = $("#session_userName").text();
			}
			isBm = data.isBm;
		}
	});
	
	siteName =name +"政府网站群";
	lineSheng(siteName,indexlist,datelist,allIndexlist);
}
//省市折线图，两条
function lineSheng(siteName,indexlist,datelist,allIndexlist){
	var myChart = echarts.init(document.getElementById('index_line'));
	    option = {
    		  	title : {
			        text: '健康指数趋势',
			        x:'left',
			        y:'10',
			        textStyle:{
			        	align:'right',
			        	fontFamily:'微软雅黑',
			        	color:'#34495e',
			        	fontWeight:'500'
			        }
			    },
	    		color:['#7BB6ED','#94ED83'],
				dataZoom : {
					show : false,
					realtime : true,
					height: 20,
					start : 100,
					end : 0
				},
	    	    legend: {
	    	    	 x: 'right',
	    	    	 y: '15',
	    	    	 padding:3,
	    	    	 itemGap:30,
	    	        data:['全国政府网站',siteName]
	    	    },
	 		    tooltip : {
	 		        trigger: 'axis',
		 		    borderColor: '#48b',
		            axisPointer: {
			            color: 'rgba(150,150,150,0.3)',
			            width: 'auto',
			            type: 'default'
		           	},
			       	formatter:function(a,b,c){
			        	var res = "";
			        	if(a.length == 2){
			        		res = a[0][1]+"<br>"+a[0][0]+"："+a[0][2]+"<br>"+a[1][0]+"：\t"+a[1][2]+"<br>";
			        	} else {
			        		res = a[0][1]+" <br/> "+a[0][0]+"："+a[0][2];
			        	}
			        	return res;
			        },
		    	    padding:15
	 		    },
	 		    calculable : true,
	 		    xAxis : [
	 		        {
	 		        	splitLine:false,
	 		        	axisLine:false,
	 		        	axisTick:false,
	 		            type : 'category',
	 		            data : datelist
	 		        }
	 		    ],
	 		    yAxis : [
	 		        {
	 		        	name:'指数',
	 		        	nameTextStyle:{
	 		        		color:'black'
	 		        	},
	  		            axisLine:{
	  		                lineStyle: {
	  		                    color: '#ffffff',
	  		                    width:1
	  		                },
	  		            type : 'value'
	  		            }
	 		        }
	 		    ],
	 		    grid:{
	 		    	 x:55,
	 		    	 x2:0,
		  	    	borderColor:'#fff'
		  	    },
	 		    series : [
	 		        {
	 		        	symbol:'emptyCircle',
	 		        	symbolSize:4,
	 		            name:'全国政府网站',
	 		            type:'line',
	 		            data:allIndexlist
	 		           
	 		        },
	 		       {
	 		        	symbol:'emptyCircle',
	 		        	symbolSize:4,
	 		            name:siteName,
	 		            type:'line',
	 		            data:indexlist
	 		           
	 		        }
	 		    ]
	 		};
	     myChart.setOption(option);
}

//网站连通性
function websiteConnection(){
	setTimeout(function(){
	var homelist = new Array();
	var channellist = new Array();
	var scanTimelist = new Array();
	var businesslist = new Array();
	$.ajax( {  
		type : "POST",    
		url : webPath+"/connectionHomeDetail_getBusinesLine.action", 
		dataType:"json",
		async : false,  
		success : function(data) {
			homelist=data.homelist;
			channellist=data.channellist;
			scanTimelist=data.scanTimelist;
			businesslist=data.businesslist;
		}
	});
	var myChart = echarts.init(document.getElementById('website_bar'));
	option = {
			color:['#FA524F','#46CDE1','#63D677'],
			dataZoom : {
				show : false,
				realtime : true,
				height: 20,
				start : 100,
				end : 0
			},
			legend: {
				x: 'right',
				y: '30',
				itemGap:60,
				padding:10,
				data:['首页','业务系统','关键栏目']
			},
			tooltip : {
				trigger: 'axis',
				borderColor: '#48b',
				axisPointer: {
					color: 'rgba(150,150,150,0.3)',
					width: 'auto',
					type: 'default'
				},
				formatter: function (params,ticket,callback) {
					var res = params[0].name.replace(params[0].name.charAt(4),'年').replace(params[0].name.charAt(7),'月')+'日';
					for (var i = 0, l = params.length; i < l; i++) {
						res += '<br/>' + params[i].seriesName + ' : ' + params[i].value+'次';
					}
					setTimeout(function (){
						// 仅为了模拟异步回调
						callback(ticket, res);
					}, 0);
				}
			},
			calculable : true,
			xAxis : [
		         {
		        	 type : 'category',
		        	 axisLine:false,  
		        	 splitLine:false,
		        	 axisTick:false,
		        	 data : scanTimelist
		         }
		         ],
		         grid:{
		         	x:60,
		         	x2:20,
		        	borderColor:'#fff'
		         },
		         yAxis : [
		                  {
		                	  name:'不连通数  (次)\n',
		                	  nameTextStyle:{color:'black'},
		                	  axisLine:{
		                		  lineStyle: {
		                			  color: '#ffffff',
		                			  width:1
		                		  },
		                		  type : 'value'
		                	  }
		                  }
		                  ],
		                  series : [
		                            {
		                            	barMaxWidth:'20',
		                            	name:'首页',
		                            	type:'bar',
		                            	data:homelist
		                            	
		                            },
		                            {
		                            	barMaxWidth:'20',
		                            	name:'业务系统',
		                            	type:'bar',
		                            	data:businesslist
		                            	
		                            },
		                            {
		                            	barMaxWidth:'20',
		                            	name:'关键栏目',
		                            	type:'bar',
		                            	data:channellist
		                            	
		                            }
		                            ]
	};
	myChart.setOption(option);
	},100);
}

//全站不可用连接---折线图
 function link_all(){
setTimeout(function(){
 	var scanDateAll = new Array();
 	var errorNumAll = new Array();
 	var scanDateStr = new Array();
 	var oldServicePeriod = new Array();
 	 $.ajax( {  
          type : "POST",
          url : webPath+"/depthLinkAll_linkAllLineOrgRebuild.action",
          dataType:"json",
          async : false,  
          success : function(data) {
               for (var int = 0; int < data.length; int++) {
             	  errorNumAll.push(data[int].websiteSum);
                  scanDateAll.push(data[int].periodNum);
                  scanDateStr.push(data[int].periodStr);
                  oldServicePeriod.push(data[int].oldServicePeriod);
              }
          }
       });
 	var myChart = echarts.init(document.getElementById('link_All_div'));
     option = {
    		color:['#4CD2D1'],
     		dataZoom : {
     	        show : true,
     	        realtime : true,
     	        height: 20,
     	        start : 100,
     	        end : 40
     	    },
  		    tooltip : {
  		        trigger: 'axis',
  		        borderColor: '#48b',
  		        axisPointer: {
  		        color: 'rgba(150,150,150,0.3)',
                width: 'auto',
                type: 'default'
                },
                formatter: function (params,ticket,callback) {
                	  var res = params[0].name;
	                  for (var i = 0, l = params.length; i < l; i++) {
	                	  res += '<br/>全站' + params[i].seriesName + '：' + params[i].value+'个';
	                	  var index = 1;
	                	  if(params[0].name.length == 3){
	                		  index = params[0].name.substring(1,2);
	                	  }else if(params[0].name.length == 4){
	                		  index = params[0].name.substring(1,3);
	                	  }else if(params[0].name.length == 5){
	                		  index = params[0].name.substring(1,4);
	                	  }
	                	  res += "<br/>扫描时间：" + scanDateStr[index-1];
	                	  if(oldServicePeriod[index-1] == 1){  //老统计规则
	                		  res += "<br/>注释： 此数据统计为[所有不可用链接]的总数量，不区分站内外和确定疑似死链";
	                	  }else if(oldServicePeriod[index-1] == 2){
	                		  res += "<br/>注释： 此数据统计为[站内确定不可用链接]的总数量";
	                	  }
	                  }
	                  setTimeout(function (){
	                      // 仅为了模拟异步回调
	                      callback(ticket, res);
	                  }, 0);
	              },
	            padding:15
  		    },
  		    calculable : true,
  		    xAxis : [
  		        {
  		        	splitLine:false,
  		        	axisLine:false,  
  		        	axisTick:false,
  		            type : 'category',
  		            data : scanDateAll
  		        }
  		    ],
	  		  grid:{
	  		  	y:32,
  		    	x2:50,
	  	    	borderColor:'#fff'
	  	    },
  		    yAxis : [
  		        {
  		        	name:'不可用链接数  (个)\n',
  		        	nameTextStyle:{color:'black'},
  		            axisLine:{
  		                lineStyle: {
  		                    color: '#ffffff',
  		                    width:1
  		                },
  		            type : 'value'
  		            }
  		        }
  		    ],
  		    series : [
  		        {
  		        	symbol:'emptyCircle',
  		        	symbolSize:4,
  		            name:'不可用链接数',
  		            type:'line',
  		            data:errorNumAll
  		           
  		        }
  		    ]
  		};
    		 myChart.setOption(option);
},100);
     
 }
//首页不可用连接----折线图
 function link_keyong(){
	setTimeout(function(){
 	var scanDate = new Array();
 	var errorNum = new Array();
 	 $.ajax( {  
          type : "POST",  
          url : webPath+"/linkHome_linkHomeTrendLineOrg.action", 
          dataType:"json",
          async : false,  
          success : function(data) {
               for (var int = 0; int < data.length; int++) {
                   errorNum.push(data[int].errorNum);
                   scanDate.push(data[int].scanDate);
              }
          }
       });
 	var myChart = echarts.init(document.getElementById('link_Home_div'));
     option = {
    		color:['#BAAEE0'],
     		dataZoom : {
     	        show : false,
     	        realtime : true,
     	        height: 20,
     	        start : 100,
     	        end : 0
     	    },
  		    tooltip : {
  		        trigger: 'axis',
	  		    borderColor: '#48b',
	            axisPointer: {
	            color: 'rgba(150,150,150,0.3)',
	            width: 'auto',
	            type: 'default'
	            },
	            formatter: function (params,ticket,callback) {
	                  var res = params[0].name.replace(params[0].name.charAt(4),'年').replace(params[0].name.charAt(7),'月')+'日';
	                  for (var i = 0, l = params.length; i < l; i++) {
	                      res += '<br/>首页' + params[i].seriesName + '：' + params[i].value+'个';
	                  }
	                  setTimeout(function (){
	                      // 仅为了模拟异步回调
	                      callback(ticket, res);
	                  }, 0);
	              },
	            padding:15
  		    },
  		    calculable : true,
  		    xAxis : [
  		        {
  		        	splitLine:false,
  		        	axisLine:false,  
  		        	 axisTick:false,
  		            type : 'category',
  		            data : scanDate
  		        }
  		    ],
  		    grid:{
  		    	y:32,
  		    	x2:50,
	  	    	borderColor:'#fff'
	  	    },
  		    yAxis : [
  		        {
  		        	name:'不可用链接数  (个)\n',
  		        	nameTextStyle:{color:'black'},
  		            axisLine:{
  		                lineStyle: {
  		                    color: '#ffffff',
  		                    width:1
  		                },
  		            type : 'value'
  		            }
  		        }
  		    ],
  		    series : [
  		        {
  		        	symbol:'emptyCircle',
  		        	symbolSize:4,
  		            name:'不可用链接数',
  		            type:'line',
  		            data:errorNum
  		           
  		        }
  		    ]
  		};
      myChart.setOption(option);
},100);
 }
//内容保障问题
 function saveQuestion(){
	 setTimeout(function(){
	var securityList = new Array();
	var homeMonthList = new Array();
	var securityServiceList = new Array();
	var homeNumList = new Array();
	var blackList = new Array();
	var noChannelList = new Array();
	 $.ajax( {  
         type : "POST",  
         url : webPath+"/connectionHomeDetail_getLineList.action",  
         dataType:"json",
         async : false,  
         success : function(data) {
        	 homeMonthList=data.dateList;
        	 homeNumList=data.homeList;
        	 noChannelList=data.channelList;
        	 blackList=data.blankList;
        	 securityList=data.resposeList;
        	 securityServiceList=data.serviceList;
         }
      });
	var myChart = echarts.init(document.getElementById('bzwtContent'));
    option = {
    		dataZoom : {
     	        show : true,
     	        realtime : true,
     	        height: 20,
     	        start : 100,
     	        end : 0
     	    },
    	    legend: {
    	    	x: 'right',
    	    	y: '30',
    	    	itemGap:30,
    	    	padding:25,
    	        data:['首页信息不更新','栏目信息不更新','空白栏目','互动回应差','服务不实用']
    	    },
 		    tooltip : {
 		        trigger: 'axis',
 		        borderColor: '#48b',
                axisPointer: {
                color: 'rgba(150,150,150,0.3)',
                width: 'auto',
                type: 'default'
                },
                formatter: function (params,ticket,callback) {
		                  var res = params[0].name.replace(params[0].name.charAt(4),'年').replace(params[0].name.charAt(7),'月')+'日';
		                  for (var i = 0, l = params.length; i < l; i++) {
		                      res += '<br/>' + params[i].seriesName + '：' + params[i].value+'个';
		                  }
		                  setTimeout(function (){
		                      // 仅为了模拟异步回调
		                      callback(ticket, res);
		                  }, 0);
		              }
 		    },
 		    calculable : true,
 		    xAxis : [
 		        {
 		        	splitLine:false,
 		        	axisLine:false,
 		        	axisTick:false,
 		            type : 'category',
 		            data : homeMonthList
 		        }
 		    ],
 		    yAxis : [
 		        {
 		        	name:'问题数  (个)\n',
 		        	nameTextStyle:{color:'black'},
  		            axisLine:{
  		                lineStyle: {
  		                    color: '#ffffff',
  		                    width:1
  		                },
  		            type : 'value'
  		            }
 		        }
 		    ],
 		    grid:{
 		    	x2:50,
	  	    	borderColor:'#fff'
	  	    },
 		    series : [
 		        {
 		        	symbol:'emptyCircle',
	 		        symbolSize:4,
 		            name:'首页信息不更新',
 		            type:'line',
 		            data:homeNumList
 		           
 		        },
 		       {
 		        	symbol:'emptyCircle',
	 		        symbolSize:4,
 		            name:'栏目信息不更新',
 		            type:'line',
 		            data:noChannelList
 		           
 		        },
 		       {
 		        	symbol:'emptyCircle',
	 		        symbolSize:4,
 		            name:'空白栏目',
 		            type:'line',
 		            data:blackList
 		           
 		        },
 		       {
 		        	symbol:'emptyCircle',
	 		        symbolSize:4,
 		            name:'互动回应差',
 		            type:'line',
 		            data:securityList
 		           
 		        },
 		       {
 		        	symbol:'emptyCircle',
	 		        symbolSize:4,
 		            name:'服务不实用',
 		            type:'line',
 		            data:securityServiceList
 		           
 		        }
 		    ]
 		};
     myChart.setOption(option);
	 },100);
}
//内容保障问题--免费
 function saveQuestionFree(){
	 setTimeout(function(){
	var securityList = new Array();
	var homeMonthList = new Array();
	var noChannelList = new Array();
	 $.ajax( {  
         type : "POST",  
         url : webPath+"/connectionHomeDetail_getLineList.action",  
         dataType:"json",
         async : false,  
         success : function(data) {
        	 homeMonthList=data.dateList;
        	 homeNumList=data.homeList;
        	 noChannelList=data.channelList;
         }
      });
	var myChart = echarts.init(document.getElementById('bzwtContent'));
    option = {
    		dataZoom : {
     	        show : true,
     	        realtime : true,
     	        height: 20,
     	        start : 100,
     	        end : 0
     	    },
    	    legend: {
    	    	x: 'right',
    	    	y: '30',
    	    	itemGap:30,
    	    	padding:25,
    	        data:['首页信息不更新','栏目信息不更新']
    	    },
 		    tooltip : {
 		        trigger: 'axis',
 		        borderColor: '#48b',
                axisPointer: {
                color: 'rgba(150,150,150,0.3)',
                width: 'auto',
                type: 'default'
                },
                formatter: function (params,ticket,callback) {
		                  var res = params[0].name.replace(params[0].name.charAt(4),'年').replace(params[0].name.charAt(7),'月')+'日';
		                  for (var i = 0, l = params.length; i < l; i++) {
		                      res += '<br/>' + params[i].seriesName + '：' + params[i].value+'个';
		                  }
		                  setTimeout(function (){
		                      // 仅为了模拟异步回调
		                      callback(ticket, res);
		                  }, 0);
		              }
 		    },
 		    calculable : true,
 		    xAxis : [
 		        {
 		        	splitLine:false,
 		        	axisLine:false,
 		        	axisTick:false,
 		            type : 'category',
 		            data : homeMonthList
 		        }
 		    ],
 		    yAxis : [
 		        {
 		        	name:'问题数  (个)\n',
 		        	nameTextStyle:{color:'black'},
  		            axisLine:{
  		                lineStyle: {
  		                    color: '#ffffff',
  		                    width:1
  		                },
  		            type : 'value'
  		            }
 		        }
 		    ],
 		    grid:{
 		    	x2:50,
	  	    	borderColor:'#fff'
	  	    },
 		    series : [
 		        {
 		        	symbol:'emptyCircle',
	 		        symbolSize:4,
 		            name:'首页信息不更新',
 		            type:'line',
 		            data:homeNumList
 		           
 		        },
 		       {
 		        	symbol:'emptyCircle',
	 		        symbolSize:4,
 		            name:'栏目信息不更新',
 		            type:'line',
 		            data:noChannelList
 		           
 		        }
 		    ]
 		};
     myChart.setOption(option);
	 },100);
}
//内容正确性
 function content_corrent_line(){
setTimeout(function(){
	var timeList = new Array();
	var correntList = new Array();
	 $.ajax( {  
         type : "POST",  
         url : webPath+"/connectionHomeDetail_contentCorrentLine.action",  
         dataType:"json",
         async : false,  
         success : function(data) {
        	 timeList=data.timeList;
           	 correntList=data.correntList;
         }
      });
	var myChart = echarts.init(document.getElementById('zqxContent'));
    option = {
    		dataZoom : {
    	        show : false,
    	        realtime : true,
    	        height: 20,
    	        start : 100,
    	        end : 0
    	    },
 		    tooltip : {
 		        trigger: 'axis',
 		        borderColor: '#48b',
                axisPointer: {
	                color: 'rgba(150,150,150,0.3)',
	                width: 'auto',
	                type: 'default'
                },
                formatter: function (params,ticket,callback) {
		                  var res = params[0].name.replace(params[0].name.charAt(4),'年').replace(params[0].name.charAt(7),'月')+'日';
		                  for (var i = 0, l = params.length; i < l; i++) {
		                      res += '<br/>' + params[i].seriesName + '：' + params[i].value+'条';
		                  }
		                  setTimeout(function (){
		                      // 仅为了模拟异步回调
		                      callback(ticket, res);
		                  }, 0);
		              },
	    	    padding:15
 		    },
 		    calculable : true,
 		    xAxis : [
 		        {
 		        	splitLine:false,
 		        	axisLine:false,
 		        	axisTick:false, 
 		            type : 'category',
 		            data : timeList
 		        }
 		    ],
 		    yAxis : [
 		        {
 		        	name:'问题数  (个)\n',
 		        	nameTextStyle:{color:'black'},
  		            axisLine:{
  		                lineStyle: {
  		                    color: '#ffffff',
  		                    width:1
  		                },
  		            type : 'value'
  		            }
 		        }
 		    ],
 		    grid:{
 		    	x2:50,
	  	    	borderColor:'#fff'
	  	    },
 		    series : [
 		        {
 		        	name:"内容正确性",
 		        	symbol:'emptyCircle',
 		        	symbolSize:4,
 		            type:'line',
 		            data:correntList
 		           
 		        }
 		    ]
 		};
     myChart.setOption(option);
},100);
}
 
//内容更新
function content_update_bar(){
	setTimeout(function(){
		var dateList = new Array();
		var homeList = new Array();
		var channelList = new Array();
		 $.ajax( {  
	         type : "POST",  
	         url : webPath+"/updateHome_contentUpdateLineOrg.action",  
	         dataType:"json",
	         async : false,  
	         success : function(data) {
	           	  dateList=data.dateList;
	           	  homeList=data.homeList;
	           	  channelList=data.channelList;
	         }
	      });
		var myChart = echarts.init(document.getElementById('content_update_bar'));
	    option = {
	    		color:['#7BB6ED','#94ED83'],
				dataZoom : {
					show : false,
					realtime : true,
					height: 20,
					start : 100,
					end : 0
				},
	    	    legend: {
	    	    	 x: 'right',
	    	    	 y: '30',
	    	    	 padding:25,
	    	    	 itemGap:30,
	    	        data:['首页更新','栏目更新']
	    	    },
	 		    tooltip : {
	 		        trigger: 'axis',
		 		    borderColor: '#48b',
		            axisPointer: {
		            color: 'rgba(150,150,150,0.3)',
		            width: 'auto',
		            type: 'default'
		           },
		           formatter: function (params,ticket,callback) {
		                  var res = params[0].name.replace(params[0].name.charAt(4),'年').replace(params[0].name.charAt(7),'月')+'日';
		                  for (var i = 0, l = params.length; i < l; i++) {
		                      res += '<br/>' + params[i].seriesName + '：' + params[i].value+'条';
		                  }
		                  setTimeout(function (){
		                      // 仅为了模拟异步回调
		                      callback(ticket, res);
		                  }, 0);
		              },
	    	    padding:15
	 		    },
	 		    calculable : true,
	 		    xAxis : [
	 		        {
	 		        	splitLine:false,
	 		        	axisLine:false,
	 		        	axisTick:false,
	 		            type : 'category',
	 		            data : dateList
	 		        }
	 		    ],
	 		    yAxis : [
	 		        {
	 		        	name:'更新数量  (条)\n',
	 		        	nameTextStyle:{color:'black'},
	  		            axisLine:{
	  		                lineStyle: {
	  		                    color: '#ffffff',
	  		                    width:1
	  		                },
	  		            type : 'value'
	  		            }
	 		        }
	 		    ],
	 		    grid:{
	 		    	x:65,
	 		    	x2:50,
		  	    	borderColor:'#fff'
		  	    },
	 		    series : [
	 		        {
	 		        	symbol:'emptyCircle',
	 		        	symbolSize:4,
	 		            name:'首页更新',
	 		            type:'line',
	 		            data:homeList
	 		           
	 		        },
	 		       {
	 		        	symbol:'emptyCircle',
	 		        	symbolSize:4,
	 		            name:'栏目更新',
	 		            type:'line',
	 		            data:channelList
	 		           
	 		        }
	 		    ]
	 		};
	     myChart.setOption(option);
	},100);
}
//获取安全问题  柱状图
function siteQue(){
	//网站安全
	setTimeout(function(){
	var weaklist = new Array();
	var horselist = new Array();
	var updatelist = new Array();
	var darklist = new Array();
	var outlist = new Array();
	var scanTimelist = new Array();
	 $.ajax( {  
	     type : "POST",    
	     url : webPath+"/monitorSilentResultOrg_getSevenDatasOrg.action", 
	 	 data:{orgType:isOrgSafetyService},
	     dataType:"json",
	     async : false,  
	     success : function(data) {
	    	 if(data.success == 'true'){
	    		 weaklist = data.weaklist ;
	    		 horselist = data.horselist ;
	    		 updatelist = data.updatelist ;
	    		 darklist = data.darklist ;
	    		 outlist = data.outlist ;
	    		 scanTimelist = data.scanTimelist ;
	    	 }
	     }
	  });
	var myChart = echarts.init(document.getElementById('siteQue'));
	option = {
			color:['#9c28b1','#ea1e63','#607d8b','#9e9e9e','#795547'],
			dataZoom : {
		        show : false,
		        realtime : true,
		        height: 20,
		        start : 100,
		        end : 0
		    },
		    legend: {
		    	x: 'right',
		    	y: '30',
		    	itemGap:60,
		    	padding:40,
		        data:['网站脆弱性','网站挂马','变更/篡改','网站暗链','网站泄露']
		    },
		    tooltip : {
		    	 trigger: 'axis',
	             borderColor: '#48b',
	             axisPointer: {
	             color: 'rgba(150,150,150,0.3)',
	             width: 'auto',
	             type: 'default'
	              },
	             formatter: function (params,ticket,callback) {
	    	            var res = params[0].name.replace(params[0].name.charAt(4),'年').replace(params[0].name.charAt(7),'月')+'日';
	    	            for (var i = 0, l = params.length; i < l; i++) {
	    	                res += '<br/>' + params[i].seriesName + ' : ' + params[i].value+'个';
	    	            }
	    	            setTimeout(function (){
	    	                // 仅为了模拟异步回调
	    	                callback(ticket, res);
	    	            }, 0);
	    	        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            axisLine:false,  
		            splitLine:false,
		            axisTick:false,
		            data : scanTimelist
		        }
		    ],
		    grid:{
	  	    	borderColor:'#fff',
	  	    	x:38,
	  	    	x2:25
	  	    },
		    yAxis : [
		        {
		        	name:'   安全问题数  (个)\n',
		        	nameTextStyle:{color:'black'},
		            axisLine:{
		                lineStyle: {
		                    color: '#ffffff',
		                    width:1
		                },
		            type : 'value'
		            }
		        }
		    ],
		    series : [
		        {
		        	barMaxWidth:'10',
		            name:'网站脆弱性',
		            type:'bar',
		            data:weaklist
		           
		        },
		       {
		        	barMaxWidth:'10',
		            name:'网站挂马',
		            type:'bar',
		            data:horselist
		           
		        },
		       {
		        	barMaxWidth:'10',
		            name:'变更/篡改',
		            type:'bar',
		            data:updatelist
		           
		        },
		        {
		        	barMaxWidth:'10',
		            name:'网站暗链',
		            type:'bar',
		            data:darklist
		           
		        },
		       {
		        	barMaxWidth:'10',
		            name:'网站泄露',
		            type:'bar',
		            data:outlist
		           
		        }
		    ]
		};
	 myChart.setOption(option);
	},100);
}

/**
 * 统计全站不可用链接   
 * 当前组织单位下所有站点的 全站不可用链接总和+首页死链数总和
 */
function depthLinkAllSum(lkHome){
	 $.ajax( {  
	     type : "POST",    
	     url : webPath+"/depthLinkAll_getDepthLinkAllSum.action", 
	     async : false,  
	 	 dataType : "json",
	     success : function(data) {
	    	 if(data.success == 'true'){
	    		 var linkAllSum = data.linkAllSum;
	    		 $("#linkId").html(linkAllSum + lkHome);
	    	     $("#linkAllSum").html(linkAllSum);
	    	 }else{
	    		  $("#linkAllSum").html("未监测");
	    		  $("#linkId").html(lkHome);
	    	 }
	     }
	  });
}

/**
 * 组织单位  内容保障 栏目不更新色块数据调整
 */
function sSecurityMessage(){
	 $.ajax( {  
	     type : "POST",    
	     url : webPath+"/securityGuarantee_getSecurityAllSum.action", 
	     async : false,  
	 	 dataType : "json",
	     success : function(data) {
	    	 if(data.success == 'true'){
	    		 var sBasic = data.sBasic;
	    		 var sBlank = data.sBlank;
	    		 var sResponse = data.sResponse;
	    		 var sService = data.sService;
	    		 var seChannelId = data.seChannelId;
	    		 var otherPeriod = data.otherPeriod;
	    		 var basicPeriod = data.basicPeriod;
	    		 var allOtherPeriod = data.allOtherPeriod;
	    		 
	    		 if(allOtherPeriod == 'notIn'){  //无合同的情况
	    			 $("#sBasic").html("-");
	    			 $("#basicTitle").attr("title","未监测");
	    			 $("#sBlank").html("-");
	    			 $("#blankTitle").attr("title","未监测");
	    			 $("#sService").html("-");
	    			 $("#serviceTitle").attr("title","未监测");
	    			 $("#sResponse").html("-");
	    			 $("#responseTitle").attr("title","未监测");
	    			 $("#seChannelId").html("未监测");
	    			 return;
	    		 }
	    		 
	    		 $("#sBasic").html(sBasic);
	    		 if(otherPeriod == 'notIn'){
	    			 $("#sBlank").html("-");
	    			 $("#blankTitle").attr("title","未监测");
	    			 $("#sService").html("-");
	    			 $("#serviceTitle").attr("title","未监测");
	    			 $("#sResponse").html("-");
	    			 $("#responseTitle").attr("title","未监测");
	    		 }else{
	    			 $("#sBlank").html(sBlank);
	    			 $("#sService").html(sService);
	    			 $("#sResponse").html(sResponse);
	    		 }
	    		 
	    		 $("#seChannelId").html(seChannelId);
	    		
	    	 }else{
	    		  $("#seChannelId").html("未监测");
	    	 }
	     }
	  });
}
//跳转
function jumpPage(uriThree){
	var uri = webPath + "/connectionHomeDetail_indexOrg.action";
	changeCookie(1,uri,null,uriThree);
}
function jumpTBPage(uriThree){
	var uri = webPath + "/fillUnit_gailan.action";
	changeCookie(2,uri,null,uriThree);
}
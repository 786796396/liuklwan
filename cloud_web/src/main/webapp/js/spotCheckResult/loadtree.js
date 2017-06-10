//例外省份（广东），针对抽查更改为考评
var site_code_session = $("#site_code_session").val();
function loadingTree(scheduleId) {
	
	
	if(typeof(scheduleId) == "undefined"){
		$('#datepickerStart').attr("disabled",false); 
		$('#datepickerEnd').attr("disabled",false); 
		$('#comboInfos').attr("disabled",false); 
	}else{
		$('#datepickerStart').attr("disabled",true); 
		$('#datepickerEnd').attr("disabled",true); 
		$('#comboInfos').attr("disabled",true); 
	}
	
	
	
    // 加载新建任务的地方或者部委
    $.ajax({
        type: "POST",
        url: webPath + "/spotCheckResult_getLocalAndMinistries.action",
        dataType: "json",
        async: false,
        success: function (data) {
            var list = data.list;
            var ministriesList = "";
            var localList = "";
            if(data.CurrentSiteCode == "bm0100"){
            	
            }else{
            	$('#local_radio_id').iCheck('destroy');
            	$("#local_radio_id").hide();
            	$('#ministries_radio_id').iCheck('destroy');
            	$("#ministries_radio_id").hide();
            	$("#ministries_select_id").attr("disabled", true);
            	$("#local_select_id").attr("disabled", true);
            }
            if (data.CurrentSiteCode.match("bm")) {
                localList += "<option value=''>——请选择——</option>";
                initLocalTree();
            } else {
                ministriesList += "<option value=''>——请选择——</option>";
                initLocalTree();
            }
            for (var i = 0; i < list.length; i++) {
                if (list[i].local != null) {
                    localList += "<option value='" + list[i].siteCode + "'>" + list[i].local + "</option>";
                }
                if (list[i].ministries != null) {
                    ministriesList += "<option value='" + list[i].siteCode + "'>" + list[i].ministries + "</option>";
                }
            }
            $("#local_select_id").append(localList);
            $("#ministries_select_id").append(ministriesList);
            $("#scheduleId").val(scheduleId);
            
        }
    });
    // 初始化右侧树的抽查个数
    count();
}

// 初始化地方树
function initLocalTree() {
    $.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/spotCheckResult_getCheckResults.action",// 请求的action路径
        error: function () {// 请求失败处理函数
            alert('请求失败');
        },
        success: function (data) { // 请求成功后处理函数。
            zNodes = data;   // 把后台封装好的简单Json格式赋给treeNodes
        }
    });
    $.fn.zTree.init($("#tree_selected"), setting, zNodes);
    $.fn.zTree.init($("#choucInfo"), setting);
    setEdit();
    $("#remove").bind("change", setEdit);
    $("#removeTitle").bind("propertychange", setEdit)
        .bind("input", setEdit);
}
// 初始化部委树
function initMinistriesTree() {
	
    $.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/spotCheckResult_getMiniCheckResults.action",
        error: function () {// 请求失败处理函数
            alert('请求失败');
        },
        success: function (data) { // 请求成功后处理函数。
            zNodes = data;   // 把后台封装好的简单Json格式赋给treeNodes
        }
    });
    $.fn.zTree.init($("#tree_selected"), setting, zNodes);
    $.fn.zTree.init($("#choucInfo"), setting);
    setEdit();
    $("#remove").bind("change", setEdit);
}
// 以下是ztree的显示
var setting = {
    edit: {
        enable: true,
        showRemoveBtn: false,
        showRenameBtn: false
    },
    data: {
        key: {
            title: "name"
        },
        simpleData: {
            enable: true
        }
    },
    callback: {
        beforeDrag: beforeDrag,
        beforeDrop: beforeDrop,
        beforeClick: zTreeOnClick,
        onRemove: zTreeOnRemove
    },
    view: {
        fontCss: getFontCss,
        showIcon: showIconForTree,	// 是否显示节点图标
// dblClickExpand: dblClickExpand,
        showLine: true,
        fontCss: getFont,
        nameIsHTML: true,
        showIcon: true
    }
};

//修改字体颜色
function getFont(treeId, node) {
	
	if(node.type == 1){ //门户
		if(node.isexp == 3){//门户关停
			return {color:"#f35458"};
		}else{
			return {color:"#45ccdf"};
		}
	}
	if(node.isexp == 2){//列外
		return {color:"#c039b2"};
	}
	if(node.isexp == 3){//关停
		return {color:"#f35458"};
	}
	return {};
}
function showIconForTree(treeId, treeNode) {
    return !treeNode.isParent;
};
var checkWeb = [];
// 点击删除按钮回调函数
function zTreeOnRemove(event, treeId, treeNode) {
    var zTree1 = $.fn.zTree.getZTreeObj("tree_selected");
    var node = zTree1.getNodeByParam("isHidden", true);
    if (node) {
        zTree1.showNode(node);
    }
    count();
    var zTree2 = $.fn.zTree.getZTreeObj("choucInfo");
    var nodesObj = zTree2.getNodeByParam("name", treeNode.name, null);
    if (nodesObj == null && !treeNode.isParent) {
        checkWeb.pop(treeNode.siteCode);
    }
}
// 点击隐藏和重构树
function zTreeOnClick(treeId, treeNode, clickFlag, treeNodes) {
    var zTree1 = $.fn.zTree.getZTreeObj("tree_selected");
	    if (treeNode.pId != null && treeNode.isexp < 2) {
        var zTree = $.fn.zTree.getZTreeObj("choucInfo");
        zTree.setting.view.showLine = false;
        zTree.setting.view.showIcon = false;
        var nodesObj = zTree.getNodeByParam("name", treeNode.name, null);
        if (nodesObj == null && !treeNode.isParent) {
            checkWeb.push(treeNode.siteCode);
            zTree.addNodes(null, treeNode);
            $(".modal-tree-selected ul li:odd").css("background", "#fafbfc");
            count();
        }
    }
};

function beforeDrag(treeId, treeNodes) {
    return false;
}
function beforeDrop(treeId, treeNodes, targetNode, moveType) {
    return targetNode ? targetNode.drop !== false : true;
}
// 动态数据
var zNodes;
function focusKey(e) {
    if (key.hasClass("empty")) {
        key.removeClass("empty");
    }
}
function blurKey(e) {
    if (key.get(0).value === "") {
        key.addClass("empty");
    }
}
var lastValue = "", nodeList = [], fontCss = {};
function searchNode(e) {
    var zTree = $.fn.zTree.getZTreeObj("tree_selected");
    var value = $.trim(key.get(0).value);
    var keyType = "name";
    // 回到默认折叠状态
    if (value == "") {
        zTree.expandAll(true);
    }
    if (lastValue === value) return;
    lastValue = value;
    if (value === "") {
        zTree.showNodes(zTree.transformToArray(zTree.getNodes()));
        return;
    }
    nodeList = zTree.getNodesByParamFuzzy(keyType, value);
    for (var x = 0; x < nodeList.length; x++) {
        if (nodeList[x].isParent) {
            nodeList.splice(x--, 1);
        }
    }
    nodeList = zTree.transformToArray(nodeList);
    updateNodes(true);
}
function updateNodes(highlight) {
    var zTree = $.fn.zTree.getZTreeObj("tree_selected");
    var allNode = zTree.transformToArray(zTree.getNodes());
    zTree.open = false;
    zTree.hideNodes(allNode);
    // 判断是否展示数据
    if (nodeList != "") {
        zTree.hideNodes(allNode);
        for (var n in nodeList) {
            findParent(zTree, nodeList[n]);
        }
        zTree.showNodes(nodeList);
    }
}

function findParent(zTree, node) {
    zTree.expandNode(node, true, false, false);
    var pNode = node.getParentNode();
    if (pNode != null) {
        nodeList.push(pNode);
        findParent(zTree, pNode);
    }

}
// 已选择的抽查数
function count() {
    var zTree = $.fn.zTree.getZTreeObj("choucInfo");
    var checkCount = zTree.getNodes().length;
    $("#checkCount").text(checkCount);
}
function getFontCss(treeId, treeNode) {
    return (!!treeNode.highlight) ? {color: "#A60000", "font-weight": "bold"} : {color: "#333", "font-weight": "normal"};
}
function filter(node) {
    return !node.isParent && node.isFirstNode;
}

function setEdit() {
    var zTree = $.fn.zTree.getZTreeObj("choucInfo");
    zTree.setting.edit.showRemoveBtn = true;
    zTree.setting.view.showLine = false;
    zTree.setting.view.showIcon = false;
    var treeObj = $.fn.zTree.getZTreeObj("tree_selected");
    //获取节点
    var nodes = treeObj.getNodes();
    nodes[0].name = nodes[0].name+"政府网站群";
    treeObj.updateNode(nodes[0]);
}
var key;
// 点击地方下拉框变化树结构
$("#local_select_id").change(function () {
    var cityCode = $(this).val();
    var city = $(this).find("option:selected").text();
    $.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/spotCheckResult_getCheckResults.action?cityCode=" + cityCode + "&city=" + encodeURIComponent(city),// 请求的action路径
        error: function () {// 请求失败处理函数
            alert('请求失败');
        },
        success: function (data) { // 请求成功后处理函数。
            zNodes = data;   // 把后台封装好的简单Json格式赋给treeNodes
        }
    });
    $.fn.zTree.init($("#tree_selected"), setting, zNodes);
//    $.fn.zTree.init($("#choucInfo"), setting);
    setEdit();
    $("#remove").bind("change", setEdit);
    $("#removeTitle").bind("propertychange", setEdit)
        .bind("input", setEdit);
});
// 点击部委下拉框变化树结构
$("#ministries_select_id").change(function () {
    var cityCode = $(this).val();
    var city = $(this).find("option:selected").text();
    $.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/spotCheckResult_getCheckResults.action?cityCode=" + cityCode + "&city=" + encodeURIComponent(city),// 请求的action路径
        error: function () {// 请求失败处理函数
            alert('请求失败');
        },
        success: function (data) { // 请求成功后处理函数。
            zNodes = data;   // 把后台封装好的简单Json格式赋给treeNodes
        }
    });
    $.fn.zTree.init($("#tree_selected"), setting, zNodes);
//    $.fn.zTree.init($("#choucInfo"), setting);
    setEdit();
    $("#remove").bind("change", setEdit);
    $("#removeTitle").bind("propertychange", setEdit)
        .bind("input", setEdit);
});


//套餐类型选择关联获取对应的可抽查数
$("#comboInfos").change(function () {
    var spotAdvancedSum=$("#spotSum").html();
    var spotAdvancedNum=$("#spotNum").html();
    
    var comboId = $(this).val();
    if(comboId == 3 || comboId == "3"){//标准套餐
    	$("#comboNum").html("");
    	$("#comboNum").html(spotStandardNum+"/"+spotStandardSum);
    }else if(comboId == 4 || comboId == "4"){//高级套餐
    	$("#comboNum").html("");
    	$("#comboNum").html(spotAdvancedNum+"/"+spotAdvancedSum);
    }
});


$("#choucModal").on('show.bs.modal', function () {
    $('#choucModal').css("z-index","1052");
    $(".modal-backdrop").css("z-index","1051");
});
$('#modalHide').click(function(){
    $('#choucModal').modal('hide');
    $(".modal-backdrop").css("z-index","1040");
})

// 点击确认model中的确定按钮
$("#certainId2").click(function (){
	
    $("#main-center").hide();//抽查首页面隐藏
    $("#new_task_div").hide();//新建任务隐藏
    $("#spot_submit_div_id").show();//抽查提交页面显示
    
    //隐藏确认提示框
    $("#choucModal").hide();
    $(".modal-backdrop").hide();
	
    var batchNum=$("#batchNum option:selected").text();//批次
    var groupNum=$("#batchNum option:selected").val();//组次
    var taskName=$("#taskName").val();//任务名称
    var count = $("#checkCount").text();//选中个数
    var scheduleId = $("#scheduleId").val();//抽查进度表id
    
    
	if ($("#datepickerStart").val() != "" && $("#datepickerEnd").val() != "") {//开始时间和结束时间不为空
        var data = {
            "dateStart": $("#datepickerStart").val(),
            "dateEnd": $("#datepickerEnd").val(),
            "groupNum":groupNum,
            "batchNum":batchNum,
            "checkWeb":checkWeb.toString(),
            "scheduleId":scheduleId,
            "taskName":taskName,
            "isorganizational":2
        };
        $.ajax({
            async: false,
            cache: false,
            type: 'POST',
            data: data,
            dataType: "json",
            url: webPath + "/spotCheckResult_newCheckResults.action",// 请求的action路径
            error: function (data) {// 请求失败处理函数
                alert(data.errorMsg);
            },success: function (data) { // 请求成功后处理函数。
            	if(data.errorMsg){
            		alert(data.errorMsg);
            	}else{
            		//抽查提交页面数据初始化
            		spotSubmitInit(batchNum,groupNum,scheduleId);
            	}

            }
        });
    } else {
    	if(site_code_session == "440000"){
    		alert("请选择考评起止时间！");
		}else{
			alert("请选择抽查起止时间！");
		}
        return false;
    }
});

//抽查提交页面数据初始化
function spotSubmitInit(batchNum,groupNum,scheduleId){
	var params={};
	params["batchNum"]=batchNum;
	params["groupNum"]=groupNum;
	params["scheduleId"]=scheduleId;

    $.ajax({
        async: false,
        cache: false,
        type: 'POST',
        url: webPath + "/spotCheckResult_spotSubInit.action",// 请求的action路径
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success: function (data) { // 请求成功后处理函数。
        	
        	if(data.errorMsg){
        		alert(data.errorMsg);
        		$("#main-center").show();
        	    $("#new_task_div").hide();
        	}else{
        		
        		//抽查进度表id---隐藏字段赋值
        		$("#scheduleId").val(data["scheduleId"]);
	        	 var topStr="";
	        	 //先清空头部
	        	 $("#spot_submit_top_id").html("");
	        		
	        	 topStr+="<h3 class='pull-left'><span class='rwu-font1'>第"+data["batchNum"]+"批</span>"+
		         "<span class='rwu-font2'>第"+data["groupNum"]+"组</span><span class='rwu-tit'>（&nbsp;共<b id='chou_count'>"+data["spotWebsiteNum"]+"</b>个&nbsp;）</span></h3>"+
		         "<div class='rwu-font3 pull-left'>"+data["taskName"]+"</div>"+
		         "<a class='page-return pull-right page-return-close'><img alt='关闭' onclick='closeTaskDiv()' src='"+webPath+"/images/close_ico.png'/></a>"+
		         "<div class='rwu-next-time'>监测周期："+data["startDate"]+"至"+data["endDate"]+"</div>";
		         
		         $("#spot_submit_top_id").append(topStr);
		         
                //左侧菜单
                $("#provinceResultId").html("");
                var siteNumList=data["siteNumList"];
                var shengStr="";
                if(siteNumList && siteNumList.length>0){
                	for(var i=0;i<siteNumList.length;i++){
                		shengStr+="<li><i></i>"+siteNumList[i]["province"]+"("+siteNumList[i]["siteNum"]+")</li>";
                	}
                	$("#provinceResultId").append(shengStr);
                }
                //抽查提交页面--左侧省菜单 添加添加点击事件
                getMenuOnclick();
	            var tbodyStr="";
	            var returnList=data["returnList"];
	            $("#rwNextTab").html("");
	            
	            if(returnList && returnList.length>0){
	            	for(var j=0;j<returnList.length;j++){
	            		tbodyStr+="<tbody><tr><td style='width:150px;' class='first-td'>"+returnList[j]["city"]+"</td>"+
	            		"<td style='width:150px;'>"+returnList[j]["county"]+"</td>"+
	            		"<td style='width:100px;'><a class='site_code' href='"+webPath+"/fillUnit_gailan.action?siteCode="+returnList[j]["siteCode"]+"'>"+returnList[j]["siteCode"]+"</a></td>"+
	            		"<td><a class='key' href='"+returnList[j]["url"]+"'>"+returnList[j]["siteName"]+"</a></td>"+
	            		"<td style='width:150px;'>"+returnList[j]["director"]+"</td>"+
	            		"<td style='width:100px;'><i class='delete'></i></td></tr></tbody>"
	            	}
	            	$("#rwNextTab").append(tbodyStr);
	            	spotResultDelete();
	            }
        	}
        },error:function(data){
           alert(data.errorMsg);
        }
    });
}

//抽查提交页面--左侧省菜单 添加添加点击事件
function getMenuOnclick(){
	var menus=$("#provinceResultId").find("li");
	$.each(menus,function(index,menu){
		$(menu).on("click",function(){
			var sheng=$(this).html().replace(/[^\u4e00-\u9fa5]/gi,"");
			var scheduleId = $("#scheduleId").val();
		    $.ajax({
		        async: false,
		        cache: false,
		        type: 'POST',
		        url: webPath + "/spotCheckResult_getSpotCheckResult.action?scheduleId="+scheduleId+"&sheng="+sheng,// 请求的action路径
		        dataType: "json",
		        contentType: "application/json",
		        success: function (data) { // 请求成功后处理函数。
		        	if(data.errorMsg){
		        		alert(data.errorMsg);
		        	}else{
			            var tbodyStr="";
			            var returnList=data["resultList"];
			            $("#rwNextTab").html("");
			            
			            if(returnList && returnList.length>0){
			            	for(var j=0;j<returnList.length;j++){
			            		tbodyStr+="<tbody><tr><td style='width:150px;' class='first-td'>"+returnList[j]["city"]+"</td>"+
			            		"<td style='width:150px;'>"+returnList[j]["county"]+"</td>"+
			            		"<td style='width:100px;'><a class='site_code' href='"+webPath+"/fillUnit_gailan.action?siteCode="+returnList[j]["siteCode"]+"'>"+returnList[j]["siteCode"]+"</a></td>"+
			            		"<td><a class='key' href='"+returnList[j]["url"]+"'>"+returnList[j]["siteName"]+"</a></td>"+
			            		"<td>"+returnList[j]["director"]+"</td>"+
			            		"<td style='width:100px;'><i class='delete'></i></td></tr></tbody>"
			            	}
			            	
			            	$("#rwNextTab").append(tbodyStr);
			            	spotResultDelete();
			            }
		        	}
		        },error:function(data){
		        	alert(data.errorMsg);
		        }
		    });
			
			
		})
		
	});
	
}

/**
 * 抽查结果页面--列表数据--点击删除图标删除该条记录
 */
function spotResultDelete(){
	var records=$("#rwNextTab").find(".delete");
	
	$.each(records,function(index,record){
		$(record).on("click",function(){
			var siteCode=$(record).parent().parent().find(".site_code").html();
			var scheduleId = $("#scheduleId").val();
		    $.ajax({
		        async: false,
		        cache: false,
		        type: 'POST',
		        url: webPath + "/spotCheckResult_deleteSpotResult.action?scheduleId="+scheduleId+"&siteCode="+siteCode,
		        dataType: "json",
		        contentType: "application/json",
		        success: function (data) { // 请求成功后处理函数。
		        	if(data.errorMsg){
		        		alert(data.errorMsg);
		        	}else{
		        		alert(data.success);
		        		checkWeb.splice(checkWeb.indexOf(siteCode), 1);//删除数组中已删除的siteCode
		        		$("#checkCount").html("0");
		        		if(data.spotWebsiteNum){
		        			$("#chou_count").html(data.spotWebsiteNum);
		        		}
		        		if(data.spotNum){
		        			$("#spotNum").html(data.spotNum);
		        		}
		                //左侧菜单
		                $("#provinceResultId").html("");
		                var siteNumList=data["siteNumList"];
		                var shengStr="";
		                if(siteNumList && siteNumList.length>0){
		                	for(var i=0;i<siteNumList.length;i++){
		                		shengStr+="<li><i></i>"+siteNumList[i]["province"]+"("+siteNumList[i]["siteNum"]+")</li>";
		                	}
		                	$("#provinceResultId").append(shengStr);
		                    //抽查提交页面--左侧省菜单 添加添加点击事件
		                    getMenuOnclick();
		                }
		        		$(record).parent().parent().remove();
		        		
		        	}
		        },error:function(data){
		        	alert(data.errorMsg);
		        }
		    });
		});
		
	});
	
}
//抽查提交页面---点击添加站点
function addSpotSite(){
	$("#sjxzzd").hide();//增加站点隐藏随机选择站点
	$("#fzrwzd").hide();//增加站点隐藏复制任务站点
	$("#rengong").addClass("active");
	$("#suij").removeClass("active");
	$("#fuzhi").removeClass("active");
	$("#rgxzzd").addClass("active");
	//
    $("#main-center").hide();//抽查首页面model隐藏
    $("#new_task_div").show();//新建抽查任务页面展示
    $("#spot_submit_div_id").hide();//抽查提交页面隐藏
    
    //清空
    $("#choucInfo").html("");
    
    var scheduleId=$("#scheduleId").val();//获取抽查进度表id
    
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
//抽查提交数据页面---点击提交按钮  提交抽查数据
function submitSpotResult(){
	var scheduleId=$("#scheduleId").val();//获取抽查进度表id
    $.ajax({
        async: false,
        cache: false,
        type: 'POST',
        url: webPath + "/spotCheckResult_subSpotResult.action?scheduleId="+scheduleId,// 请求的action路径
        dataType: "json",
        contentType: "application/json",
        success: function (data) { // 请求成功后处理函数。
        	if(data.errorMsg){
        		alert(data.errorMsg);
        	}else{
        		alert(data.success);
        		window.location.href=webPath+"/spotCheckResult_list.action";
        	}
        },error:function(data){
        	alert(data.errorMsg);
        }
    });
}
//定义全局站点字符串
var fzhSiteCode ="";
//复制站点
function fzhzhd (noid,allSiteCode,notAllSiteCode){
	if($("#"+noid).prop("checked")){
		fzhSiteCode = fzhSiteCode.replace(allSiteCode, "");
		if(fzhSiteCode.indexOf(notAllSiteCode)<0){
			fzhSiteCode+=notAllSiteCode;
			alert("本组任务已复制成功");
		}else{
			alert("本组任务已复制成功");
		}
	}else{
		if(fzhSiteCode.indexOf(allSiteCode)<0){
			fzhSiteCode = fzhSiteCode.replace(notAllSiteCode, "");
			fzhSiteCode+=allSiteCode;
			alert("本组任务已复制成功");
		}else{
			alert("本组任务已复制成功");
		}
	}
	$("#fzhSiteCodes").val(fzhSiteCode);
}
//是否选择复制不合格站点
/*function fzhzhdNo (noid,allSiteCode,notAllSiteCode){
	if(fzhSiteCode.indexOf(allSiteCode)>=0){
		if($("#"+noid).prop("checked")){
			fzhSiteCode = fzhSiteCode.replace(allSiteCode, "");
			if(notAllSiteCode!=null && ""!=notAllSiteCode && fzhSiteCode.indexOf(notAllSiteCode)<0){
				fzhSiteCode+=notAllSiteCode;
			}
		}else{
			fzhSiteCode = fzhSiteCode.replace(notAllSiteCode, "");
			if(fzhSiteCode.indexOf(allSiteCode)<0){
				fzhSiteCode+=allSiteCode;
			}
		}
	}else{
		if(notAllSiteCode!=null && ""!=notAllSiteCode && fzhSiteCode.indexOf(notAllSiteCode)>=0){
			if(fzhSiteCode.indexOf(allSiteCode)<0){
				fzhSiteCode+=allSiteCode;
			}
		}
	}
	$("#fzhSiteCodes").val(fzhSiteCode);
}*/
//复制站点任务--验证
$("#spot_choucModalCopy_button").click(function () {
	var classs = $("#spot_choucModalCopy_button").attr("class");
	if(classs.indexOf("btn-success")>=0){
		var siteCodes = $("#fzhSiteCodes").val();
		var taskName=$("#taskName").val();
		if (siteCodes.length==0) {
			alert("请选择要复制的任务！");
			return false;
		}
		if (!taskName) {
			alert("请选择输入任务名称");
			return false;
		}
		$("#fzhSiteCodes").val(siteCodes.replace(/_/g,","));
	}else{
		return false;
	}
});

//点击确认model中的确定按钮-复制站点任务
$("#certainIdCopy").click(function (){
	
    $("#main-center").hide();//抽查首页面隐藏
    $("#new_task_div").hide();//新建任务隐藏
    $("#spot_submit_div_id").show();//抽查提交页面显示
    
    //隐藏确认提示框
    $("#choucModalCopy").hide();
    $(".modal-backdrop").hide();
	
    var batchNum=$("#batchNum option:selected").text();//批次
    var groupNum=$("#batchNum option:selected").val();//组次
    var taskName=$("#taskName").val();//任务名称
    var fzhSiteCodes = $("#fzhSiteCodes").val();//选中站点
    var scheduleId = $("#scheduleId").val();//抽查进度表id
    
    
	if ($("#datepickerStart").val() != "" && $("#datepickerEnd").val() != "") {//开始时间和结束时间不为空
        var data = {
            "dateStart": $("#datepickerStart").val(),
            "dateEnd": $("#datepickerEnd").val(),
            "groupNum":groupNum,
            "batchNum":batchNum,
            "checkWeb":fzhSiteCodes,
            "scheduleId":scheduleId,
            "taskName":taskName,
            "isorganizational":2
        };
        $.ajax({
            async: false,
            cache: false,
            type: 'POST',
            data: data,
            dataType: "json",
            url: webPath + "/spotCheckResult_newCheckResults.action",// 请求的action路径
            error: function (data) {// 请求失败处理函数
                alert(data.errorMsg);
            },success: function (data) { // 请求成功后处理函数。
            	if(data.errorMsg){
            		alert(data.errorMsg);
            	}else{
            		//抽查提交页面数据初始化
            		spotSubmitInit(batchNum,groupNum,scheduleId);
            	}

            }
        });
    } else {
    	if(site_code_session == "440000"){
    		alert("请选择考评起止时间！");
		}else{
			alert("请选择抽查起止时间！");
		}
        return false;
    }
});

$("#certainId").click(function () {
//    $('#choucModal').modal('hide');
//    $('#renwuModal').modal('hide');

    var options = $("#local_select_id option:selected").val();// 获取地方或部委选择下拉框的值
    var optionsText = $("#local_select_id option:selected").text();// 获取地方或部委选择下拉框的文本值
    var comboId = $("#comboInfos option:selected").val();//获取选择的套餐ID
    var count = $("#checkCount").text();
    var restNum = $("#restNum").val();
    var taskName=$("#taskName").val();
    
    var spotAdvancedSum=$("#spotSum").html();
    var spotAdvancedNum=$("#spotNum").html();
    if (count == 0) {
        if(site_code_session == "440000"){
        	 alert("请选择考评网站！");
		}else{
			 alert("请选择抽查网站！");
		}
        return false;
    }
    if (!comboId) {
        alert("请选择套餐类型");
        return false;
    }
    if (!taskName) {
        alert("请选择输入任务名称");
        return false;
    }
    if(comboId == 3 || comboId == "3"){//标准版
    	if(checkWeb.length > (spotStandardSum - spotStandardNum)){
    	if(site_code_session == "440000"){
    		alert("标准版剩余考评数量不足");
   		}else{
   			alert("标准版剩余抽查数量不足");
   		}
    		return false;
    	}
    }else if(comboId == 4 || comboId == "4"){//高级版
    	if(checkWeb.length > (spotAdvancedSum - spotAdvancedNum)){

    		if(site_code_session == "440000"){
    			alert("高级版剩余考评数量不足");
       		}else{
       			alert("高级版剩余抽查数量不足");
       		}
    		return false;
    	}
    }
    if (restNum < 0) {
        if(site_code_session == "440000"){
        	alert("剩余的考评数不足");
   		}else{
   			alert("剩余的抽查数不足");
   		}
        return false;
    }
});
/**
 * 把数字转换成汉字
 */
var N = ["零", "一", "二", "三", "四", "五", "六", "七", "八", "九"];
function convertToChinese(num) {
    var str = num.toString();
    var len = num.toString().length;
    var C_Num = [];
    for (var i = 0; i < len; i++) {
        C_Num.push(N[str.charAt(i)]);
    }
    return C_Num.join('');
}

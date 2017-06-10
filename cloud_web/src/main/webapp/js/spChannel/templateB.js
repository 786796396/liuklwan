var menuType = 0;
var siteId;
var loginConfigId;
var templateId;
var displayModule = new String();
var arr = new Array(); 
var errorInfoTitle = [];//表头
errorInfoTitle.push({"mDataProp": "num", "bSortable": false,"sWidth":"50px","sTitle": "序号", "sClass": "center num", "bVisible": true, "tabIndex": -7});
errorInfoTitle.push({"mDataProp": "siteCode", "sTitle": "网站标识码","bSortable": false, "sClass": "center code", "bVisible": true, "tabIndex": -6});
errorInfoTitle.push({"mDataProp": "name", "sWidth":"200px","sTitle": "网站名称", "bSortable": false,"sClass": "text-left name", "bVisible": true, "tabIndex": -5});
errorInfoTitle.push({"mDataProp": "problemId","sWidth":"200px", "sTitle": "问题类型","bSortable": false,"sClass": "center", "bVisible": true, "tabIndex": -4});
errorInfoTitle.push({"mDataProp": "reviewTime","sWidth":"95px", "sTitle": "曝光日期","bSortable": false, "sClass": "text-left", "bVisible": true, "tabIndex": -3});
errorInfoTitle.push({"mDataProp": "status", "sTitle": "办理状态","bSortable": false,"sClass": "text-left", "bVisible": true, "tabIndex": -1});
errorInfoTitle.push({"mDataProp": "id", "sTitle": "","bSortable": false,"sClass": "text-left", "bVisible": true, "tabIndex": -1});
var table_data_errorInfo;

var databaseInfoTitle = [];//表头
databaseInfoTitle.push({"mDataProp": "num", "bSortable": false,"sWidth":"50px","sTitle": "序号", "sClass": "center", "bVisible": true, "tabIndex": -7});
databaseInfoTitle.push({"mDataProp": "siteCode", "sTitle": "网站标识码","bSortable": false, "sClass": "center", "bVisible": true, "tabIndex": -6});
databaseInfoTitle.push({"mDataProp": "director", "sWidth":"200px","sTitle": "网站主管单位", "bSortable": false,"sClass": "text-left", "bVisible": true, "tabIndex": -5});
databaseInfoTitle.push({"mDataProp": "name","sWidth":"200px", "sTitle": "网站名称","bSortable": false,"sClass": "center", "bVisible": true, "tabIndex": -4});
databaseInfoTitle.push({"mDataProp": "url","sWidth":"95px", "sTitle": "首页网址","bSortable": false, "sClass": "text-left", "bVisible": true, "tabIndex": -3});
databaseInfoTitle.push({"mDataProp": "isexp", "sTitle": "网站状态","bSortable": false,"sClass": "text-left", "bVisible": true, "tabIndex": -1});
databaseInfoTitle.push({"mDataProp": "operation","sWidth":"95px", "sTitle": "","bSortable": false,"sClass": "text-left", "bVisible": true, "tabIndex": -1});
var table_data_databaseInfo;

$(function(){
	$("#colorPiece").hide();
	$("#hiddenMap").hide();
	$("#resultsTopId").hide();
	$("#resultsOfId").hide();
	$("#correntId").hide();
	
    siteId = $("#siteCodeId").val();
    templateId = $("#templateId").val();

    healthIndex();  // 健康指数
    getLoginNumOne();
    getLoginNumTwo();
    setInterval(seconds, 1000);  // 每一秒执行一次
    
    
    displayModule = $("#displayModule").val();
    arr = displayModule.split(',');//注split可以用字符或字符串分割  
    
    if(arr != null && arr != ""){
        for(var i = 0; i < arr.length; i++){
        	var type = arr[i];  
        	if(type == 1){  // 显示模块（1:日常检测,2:网站概况,3:栏目,4:大数据,5:政府网站基础信息数据库,6:政府网站网民找错数据）
        		connectedRate();  // 折线图
        	    automatic();   // 每5秒跳转一次
        		$("#colorPiece").show();
        	}
        	
        	if(type == 2){  
        		queryCode();  // 地图
        		$("#hiddenMap").show();
        	}
        	
        	if(type == 5){  
        		$("#resultsTopId").show();
        		$("#resultsOfId").show();
        	} 
        	
        	if(type == 6){  
	        	errorInfoList();
	        	$("#correntId").show();
        	} 
        	
        }
    }else{
    	$("#colorPiece").hide();
    	$("#hiddenMap").hide();
    	$("#resultsTopId").hide();
		$("#resultsOfId").hide();
		$("#correntId").hide();
    }

    $('#queryInput').bind('keyup', function(event) {
        if (event.keyCode == "13") {
            //回车执行查询
        	infoListAjax();
        }
    });
    
/*	//列表检索
    $("#exposureKeyId").keyup(function(){
        var searchInfo=$("#exposureKeyId").val();
			 var nums=0;
			 $("#errorInfoId").find(".code").each(function(index, element) {
				 if(($(this).html().indexOf(searchInfo)<0) && ($(this).parent().find(".name").html().indexOf(searchInfo)<0)){
					 $(this).parents("tr").hide();  
				 }else{
					 nums+=1;
					 $(this).parents("tr").show();
					 $(this).parents("tr").find(".num").html(nums);
				 }
			});
    });*/

});



/**
 * 昨天健康指数
 * @param {} menuType---》省
 */
function healthIndex(){
	$.ajax({  
		type : "POST",    
		url : webPath+"/spChannel_getIndexSum.action", 
		dataType:"json",
		data:{menuType:0,siteCode:siteId},
		async : false,  
		success : function(data) {
			var differential=data.differential;
			var differentialRate = data.differentialRate;
			//折算分数
			var convertScores=data.convertScores;
			//健康分数
			var totalSumNumber=data.totalSumNumber;
			var leadSum=data.leadSum;
			$("#health_index").text(totalSumNumber);
			if(differential>0){
                $("#totalSumNumber").html(differential+"&nbsp;&nbsp;(+"+differentialRate+")");
			}else{
				$("#index_ico").addClass("dowm-ico");
				$('#textcolor').removeClass("colo-ff1f1f").addClass("blue-text");
                $("#totalSumNumber").html(differential+"&nbsp;&nbsp;("+differentialRate+")");
			}
		}
	});
}


/**
 * ul
 * @param id
 */
function selectShow(id){
    $("#"+id+"Ul li").click(function(){
        $("#"+id+"Val").val(this.value);
        $("#"+id+"Text").html($(this).html());
        $("#"+id+"Ul").hide();
        if(id == "aaaType"){
        	 
        }else{
        	errorInfoListAjax();
        }
    });
}

//每5秒自动切换
function automatic(){
    var oBox=document.getElementById('colorPiece');
    var aBtn=oBox.getElementsByTagName('span');
    var aDiv=document.getElementsByClassName('chart-content');
    var timer=null;
    var now=0;
    for (var i=0; i<aBtn.length; i++)
    {
        aBtn[i].index=i;
      
        aBtn[i].onclick=function (){
            for (var i=0; i<aBtn.length; i++)
            {
                $(aBtn[i]).removeClass('active');
                $(aDiv[i]).removeClass('active');
                aDiv[i].style.display="none";
            }
           
            $(this).addClass('active');
            $(aDiv[this.index]).addClass('active');
            aDiv[this.index].style.display="";
        };
    }
    function tab(){
            now++;
            if (now == aBtn.length)
            {
                now=0;
            }
            if(now == 0){
            	connectedRate();
            }else if(now == 1){
            	deadRate();
            }else if(now == 2){
            	datesiteRate();
            }
            
            for (var i=0; i<aBtn.length; i++)
            {
                $(aBtn[i]).removeClass('active');
                $(aDiv[i]).removeClass('active');
                aDiv[i].style.display="none";
            }

            $(aBtn[now]).addClass('active');
            $(aDiv[now]).addClass('active');
            aDiv[now].style.display="";
    };

    timer=setInterval(tab,5000);

    $('#tab-content').mouseover(function(){
        clearInterval(timer);
    });
    $('#tab-content').mouseout(function(){
        timer=setInterval(tab,5000);
    });
}
 
function seconds(){
    getLoginNumOne();
    getLoginNumTwo();
}

function getLoginNumOne(){
	$.ajax({
		type : "POST",
		url : webPath + "/users_getLoginNum.action",
		data:{
			type:1
		},
		dataType : "json",
		async : false,
		success : function(data) {
			$("#infoCountId").html(data.databaseInfoCount);
			var pageNum = '' + data.pageNum + '';
			$("#pageNumId").html(pageNum.replace(/\B(?=(?:\d{3})+\b)/g, ','));
		},
		error : function(data) {
			// alert(data.errorMsg);
		}
	});
}
function getLoginNumTwo(){
	$.ajax({
		type : "POST",
		url : webPath + "/users_getLoginNum.action",
		data:{
			type:2
		},
		dataType : "json",
		async : false,
		success : function(data) {
			var errorNum = '' + data.errorNum + '';
			$("#errorNumId").html(errorNum.replace(/\B(?=(?:\d{3})+\b)/g, ','));
		},
		error : function(data) {
			// alert(data.errorMsg);
		}
	});
}

function infoListAjax(){
	if(table_data_databaseInfo == undefined){
		databaseInfoList();
	}else{
		table_data_databaseInfo.fnReloadAjax(webPath + "/spChannel_getDatabaseInfoBycode.action");
	}
}

function databaseInfoList() {
	table_data_databaseInfo = $("#databaseInfoId").dataTable({
        "sDom": '<"top"T<"clear">>frt<"toolbar">lip',
        "bDestroy": true,
        "bAutoWidth": true,
        "bDeferRender": true,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "iDisplayLength": 10,
        "oTableTools": {
            "sSwfPath": "/boxpro/lib/tableTools/media/swf/copy_csv_xls_pdf.swf",
            "sRowSelect": "multi"     //可选中行，single单行。multi多行    
        },
        "oLanguage": {
            "sSearch": "查询:",
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
            "sZeroRecords": "抱歉，没有找到相关结果，建议您更换查询条件重新搜索。"
        },
        
        "bInfo": true,
        "bPaginate": true,
        "bRetrieve": true,
        "bServerSide": true,
        "bLengthChange": false,
        "bFilter":false,
        "sAjaxSource": webPath+ "/spChannel_getDatabaseInfoBycode.action",
        "sAjaxDataProp": function (data) {
        	$("#databaseInfoNumId").attr("style","display:block");
        	$("#searchResult").attr("style","display:block");
        	$("#searchId").hide();
        	$("#sizeId").html(data.countNum);
        	
        	if(data && data.body){	
        		for (var i = 0; i < data.body.length; i++) {
        			var url = data.body[i]["url"];
        			if(url != ""){
   					 	if(!url.match("http")){
	        				 url="http://"+url;
	        			}
   				 	}
        			var code = data.body[i]["siteCode"];
        			data.body[i]["operation"] = '<td class="center"><a href="'+url+'" target="_blank">访问网站</a> | <span class="jiuc_font" title="对该网站中存在的“不及时、不准确、不回应、不实用”问题进行举报"><a href="http://114.55.181.28/errorInfo/jcInfo/yun_'+code+'" target="_blank">我要找错</a></span></td>';
        		}
        	}
            return data.body;
        },
        "bProcessing": false,
        "fnServerData": fnDataTablesPipeline,
        "aoColumns": databaseInfoTitle,
        "fnInitComplete": function () {
            $("#table_data").parent().parent().removeAttr("style");
            $("#table_data_filter").css("margin-right", "75%");
            $("#databaseInfoId_length").hide();
           // $("#errorInfoId").hide();
            $("#databaseInfoId_filter").hide();
            var table_dataManager_wrapper = $("#databaseInfoId");
            //table_dataManager_wrapper.find("th").css("cursor", "default");
        },
        "fnPreDrawCallback": function(oSettings) {
           // httpHtmlF();
        }
    });
}

function errorInfoListAjax(){
	table_data_errorInfo.fnReloadAjax(webPath + "/spChannel_getErrorInfoList.action");
}


function errorInfoList() {
	table_data_errorInfo = $("#errorInfoId").dataTable({
        "sDom": '<"top"T<"clear">>frt<"toolbar">lip',
        "bDestroy": true,
        "bAutoWidth": true,
        "bDeferRender": true,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "iDisplayLength": 10,
        "oTableTools": {
            "sSwfPath": "/boxpro/lib/tableTools/media/swf/copy_csv_xls_pdf.swf",
            "sRowSelect": "multi"     //可选中行，single单行。multi多行    
        },
        "oLanguage": {
            "sSearch": "查询:",
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
            "sZeroRecords": "抱歉，没有找到相关结果，建议您更换查询条件重新搜索。"
        },
        
        "bInfo": true,
        "bPaginate": true,
        "bRetrieve": true,
        "bServerSide": true,
        "bLengthChange": false,
        "bFilter":false,
        "sAjaxSource": webPath+ "/spChannel_getErrorInfoList.action",
        "sAjaxDataProp": function (data) {
        	$("#errorInfoNumId").attr("style","display:block");
        	if(data && data.body){	
        		for (var i = 0; i < data.body.length; i++) {
        			var id = data.body[i]["id"];
        			data.body[i]["id"] = "<td class='td_left'><span onclick='getErrorInfoById("+id+")'><i>查看</i></span></td>";
        		}
        	}
            return data.body;
        },
        "bProcessing": false,
        "fnServerData": fnDataTablesPipeline,
        "aoColumns": errorInfoTitle,
        "fnInitComplete": function () {
            $("#table_data").parent().parent().removeAttr("style");
            $("#table_data_filter").css("margin-right", "75%");
            $("#errorInfoId_length").hide();
           // $("#errorInfoId").hide();
            $("#errorInfoId_filter").hide();
            var table_dataManager_wrapper = $("#errorInfoId");
            //table_dataManager_wrapper.find("th").css("cursor", "default");
        },
        "fnPreDrawCallback": function(oSettings) {
           // httpHtmlF();
        }
    });
}

function getErrorInfoById(id){
	$.ajax({
		type : "POST",
		url : webPath + "/spChannel_getErrorInfoById.action",
		data:{
			id:id
		},
		dataType : "json",
		async : false,
		success : function(data) {
			 $("#errName").html(data.errName);
			 $("#problemId").html(data.problemId);
			 $("#errorUrl").html(data.errorUrl);
			 $("#description").html(data.description);
			 $("#transactUnit").html(data.transactUnit);
			 $("#transactResult").html(data.transactResult);
			 $("#status").html(data.status);
			 $("#errorInfoIds").show();
			 
			 // 关闭样式
			var scrollTop=0;
			$('#errorInfoIds').on('show.bs.modal', function () {
			    scrollTop=$(document).scrollTop();
			    $(".container-m").addClass("page-fixed");
			    $(document).scrollTop(0);
			});
			$('#errorInfoIds').on('hidden.bs.modal', function () {
			    $(".container-m").removeClass("page-fixed");
			    $(document).scrollTop(scrollTop);
			});
			
			$('#errorInfoIds').modal();
		},
		error : function(data) {
			// alert(data.errorMsg);
		}
	});
}

//获取各省code值
function queryCode(){
	$.ajax({
		type : "POST",
		url : webPath + "/spChannel_getMapInfoList.action",
		dataType : "json",
		data:{
	    	"orgSiteCode":siteId,
	    	},    
		async : false,
		success : function(data) {
			var html = "";
			 $.each(data.body, function(index, obj) {
				 var i = index+1;
				 if(i%2==0){
			    		html+='<li style="background-color:#f5f9fc;"><ul class="clearfix padd-ul"><li class="fl number">'+i+'</li><li class="fl area">'+obj.name+'</li><li class="fl total">'+obj.normalNum+'</li><li class="fl total">'+obj.shutSiteNum+'</li><li class="fl total">'+obj.exceptSiteNum+'</li></ul></li>';
			        }else{
			    		html+='<li><ul class="clearfix padd-ul"><li class="fl number">'+i+'</li><li class="fl area">'+obj.name+'</li><li class="fl total">'+obj.normalNum+'</li><li class="fl total">'+obj.shutSiteNum+'</li><li class="fl total">'+obj.exceptSiteNum+'</li></ul></li>';
			        }
			 });
			
			 $("#monitoringSiteId").append(html);
	          $.get(webPath +"/js/map/provinceJson/"+ siteId +".json",function(obj){
	        	    echarts.registerMap('henan', obj);//hennanJson名称取自henan.js里的var  henanJson变量名
	 		        var chart = echarts.init(document.getElementById('main'));
	 		        var convertData00 = function (data) {
	 		    	    var res = [];
	 		    	    for (var i = 0; i < data.length; i++) {
	 		    	                    res.push({
	 		    	                        name: data[i].name,
	 		    	                        value: data[i].normalNum
	 		    	                    });
	 		    	    }
	 		    	    return res;
	 		    	};
	 		        
	 		        chart.setOption({
	 				    tooltip: {
	 				    trigger: 'item',
	 				    formatter: function (param){
	 	           			var value = "";
	 	           			var datab = data.body;
	 	                      for(var i=0;i<datab.length;i++){
	 	                          if(param.name == datab[i].name){
	 	                              value = datab[i].normalNum;
	 	                          }
	 	                      }
	 	           			return param.name+"</br>正常网站数量："+param.value;
	 	           		}
	 				    },
	 				    legend: {
	 				        orient: 'vertical',
	 				        left: 'left',
	 				    },
	 				    visualMap: {
	 				    show:false,
	 				    splitList: [
	 		                {start: 301},
	 		                {start: 201, end: 300},
	 		                {start: 151, end: 200},
	 		                {start: 101, end: 150},
	 		                {end: 100}
	 				    ],
	 				    color: ['#ea7997', '#4fc5f7', '#88a2f9', '#f9b961', '#5ed2a5'],
//		 			        min: 0,
//		 			        max: 400,
//		 			        color:['#3e3a1d','#cf13bb','red','green','yellow','blue'],
	 			        left: 'left',
	 			        top: 'bottom',
	 			       // text: ['高','低'],          // 文本，默认为数值文本
	 			        calculable: true
	 		   	 },
	 				    series: [
	 				        {
	 				            type: 'map',
	 				            showLegendSymbol: false,
	 				            mapType: 'henan',
	 				            roam: false,
	 				            scaleLimit:{max:2, min:1.2},
	 				            label: {
	 				            	normal: {
	 				                    show: false
	 				                },
	 				                emphasis: {
	 				                    show: true
	 				                }

	 				            },
	 				             data:convertData00(data.body)
	 				        },
	 				    ]
	 				        });
	          });
	   
		},
		error : function(data) {
			// alert(data.errorMsg);
		}
	});
}

//最近7天的不连通率
function connectedRate(){
	$("#ltxContent").show();
	var timeList = new Array();
	var correntList = new Array();
	var min = 0;
	var max = 0;
	var myChart = echarts.init(document.getElementById('ltxContent'));
	 $.ajax( {  
        type : "POST",  
        url : webPath+"/spChannel_monitoringData.action", 
        data:{siteCode:siteId},
        dataType:"json",
        async : false,  
        success : function(data) {
       	 timeList=data.timelist;
          	 correntList=data.countlist;
          	 
          	min = Math.min.apply(null, correntList);
          	max = Math.max.apply(null, correntList);
        }
     });
	 option = {
			    title: {
			    	text: '连通率(%)\n',
			    	textStyle:{
			    		fontSize: 14,
			    		fontWeight:'normal',
			    		fontFamily : '微软雅黑',
			    		color: '#0f171c'
			    	},
			    	padding:[22,5,5,5]
			    },
			    tooltip: {
			        trigger: 'axis',
					  formatter: function (params,ticket,callback) {
			                  var res = params[0].name.replace(params[0].name.charAt(4),'年').replace(params[0].name.charAt(7),'月')+'日';
			                  for (var i = 0, l = params.length; i < l; i++) {
			                      res += '<br/>' + params[i].seriesName + '：' + params[i].value+'%';
			                  }
			                  setTimeout(function (){
			                      // 仅为了模拟异步回调
			                      callback(ticket, res);
			                  }, 0);
			              },
		    	    padding:15
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    xAxis: {
			        type: 'category',
			        boundaryGap: true,
	                axisLabel: {
	                    show: true,
	                },
	                axisLine:{
	  	              lineStyle:{
	  	                  color:'#0f171c'
	  	              }
	  	            },
			        data: timeList
			    },
			    yAxis: {
			    	 min:min,
		             max:max,
			    	lineStyle:{
	                    color:'#0f171c'
	                },
	                axisLabel: {
	                    show: true,
	                    textStyle: {
	                        color: '#0f171c'
	                    }
	                },
	                //show:false,
		            type : 'value',
		            axisLine:{
		              lineStyle:{
		                  color:'#0f171c'
		              }
		            }
			    },
			    series: [
			        {
			            name:'连通率',
			            type:'line', 
			            itemStyle : {  
	                        normal : {  
	                        	label : {show: true},
	                        	color:'#32bc98',  
	                            lineStyle:{  
	                                color:'#32bc98',
	                            }  
	                        }  
	                    }, 
			            data:correntList,
			        }
			    ]
			};
    myChart.setOption(option);
}

//最近7天的死链率
function deadRate(){
	$("#kyxContent").show();
	var timeList = new Array();
	var deadlist = new Array();
	var myChart = echarts.init(document.getElementById('kyxContent'));
	 $.ajax( {  
        type : "POST",  
        url : webPath+"/spChannel_monitoringData.action",  
        data:{siteCode:siteId},
        dataType:"json",
        async : false,  
        success : function(data) {
       	 timeList=data.timelist;
       	 	deadlist=data.deadlist;
       	 	
       	 min = Math.min.apply(null, deadlist);
       	 max = Math.max.apply(null, deadlist);
        }
     });
	 option = {
			    title: {
			    	text: '可用性(%)\n',
			    	textStyle:{
			    		fontSize: 14,
			    		fontWeight:'normal',
			    		fontFamily : '微软雅黑',
			    		color: '#0f171c'
			    	},
			    	padding:[22,5,5,5]
			    },
			    tooltip: {
			        trigger: 'axis',
					  formatter: function (params,ticket,callback) {
			                  var res = params[0].name.replace(params[0].name.charAt(4),'年').replace(params[0].name.charAt(7),'月')+'日';
			                  for (var i = 0, l = params.length; i < l; i++) {
			                      res += '<br/>' + params[i].seriesName + '：' + params[i].value+'%';
			                  }
			                  setTimeout(function (){
			                      // 仅为了模拟异步回调
			                      callback(ticket, res);
			                  }, 0);
			              },
		    	    padding:15
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    xAxis: {
			        type: 'category',
			        boundaryGap: true,
	                axisLabel: {
	                    show: true,
	                },
	                axisLine:{
	  	              lineStyle:{
	  	                  color:'#0f171c'
	  	              }
	  	            },
			        data: timeList
			    },
			    yAxis: {
			    	 min:min,
		             max:max,
			    	lineStyle:{
	                    color:'#0f171c'
	                },
	                axisLabel: {
	                    show: true,
	                    textStyle: {
	                        color: '#0f171c'
	                    }
	                },
	                //show:false,
		            type : 'value',
		            axisLine:{
		            	show:false,
		              lineStyle:{
		                  color:'#0f171c'
		              }
		            }
			    },
			    series: [
			        {
			            name:'可用性',
			            type:'line', 
			            itemStyle : {  
	                        normal : {  
	                        	label : {show: true},
	                        	color:'#32bc98',  
	                            lineStyle:{  
	                                color:'#32bc98',
	                            }  
	                        }  
	                    }, 
			            data:deadlist
			        }
			    ]
			};
    myChart.setOption(option);
}
//最近7天的更新量
function datesiteRate(){
	$("#gxContent").show();
	var timeList = new Array();
	var datesitelist = new Array();
	var myChart = echarts.init(document.getElementById('gxContent'));
	 $.ajax( {  
        type : "POST",  
        url : webPath+"/spChannel_monitoringData.action",  
        data:{siteCode:siteId},
        dataType:"json",
        async : false,  
        success : function(data) {
       	 timeList=data.timelist;
       	 datesitelist=data.datesitelist;
       	 
      	 min = Math.min.apply(null, datesitelist);
       	 max = Math.max.apply(null, datesitelist);
        }
     });
	 option = {
			    title: {
			    	text: '更新量(条)\n',
			    	textStyle:{
			    		fontSize: 14,
			    		fontWeight:'normal',
			    		fontFamily : '微软雅黑',
			    		color: '#0f171c'
			    	},
			    	padding:[22,5,5,5]
			    },
			    tooltip: {
			        trigger: 'axis',
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
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    xAxis: {
			        type: 'category',
			        boundaryGap: true,
	                axisLabel: {
	                    show: true,
	                },
	                axisLine:{
	  	              lineStyle:{
	  	                  color:'#0f171c'
	  	              }
	  	            },
			        data: timeList
			    },
			    yAxis: {
			    	min:min,
		            max:max,
			    	lineStyle:{
	                    color:'#0f171c'
	                },
	                axisLabel: {
	                    show: true,
	                    textStyle: {
	                        color: '#0f171c'
	                    }
	                },
	                //show:false,
		            type : 'value',
		            axisLine:{
		            	show:false,
		              lineStyle:{
		                  color:'#0f171c'
		              }
		            }
			    },
			    series: [
			        {
			            name:'更新量',
			            type:'bar', 
						barMaxWidth:'30',
			            itemStyle : {  
	                        normal : {  
	                        	label : {show: true},
	                        	color:'#32bc98',  
	                            lineStyle:{  
	                                color:'#32bc98',
	                            }  
	                        }  
	                    }, 
			            data:datesitelist
			        }
			    ]
			};
    myChart.setOption(option);
}

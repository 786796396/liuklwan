//
var showbox=2 ;//  1 代表图表 2 代表列表 
var level;//1 bm0100  2 有下级战群  3 没有下级战群
var showTb=1;// 列表表头 1 组织单位  2 填报单位；
var grade =1;// 便于 斜线处的使用
var loginSiteCode = 0;
var databaseTreeInfoId=0;
var seeOrVisit=2;// 2 浏览   3 访客
var dataList;//图表展示的数据
var cutSeeOrVisit;

var commonSiteCode;
var comonnWebName;
var commonBigTab;//0 下级地方站群 1 下级地方门户 2 本级站点 3 下级部门站群  4 下级部门门户

// 弹出的 图框使用的
var commonDateNum=-30;
var commSiteCode;
var tanSeeOrVisit=3;//2  浏览量走势图  3 访客量走势图
var cList;//弹框使用的 请求成功的数据
$(function(){
	level = $("#level").val();
	loginSiteCode=$("#siteCode").val();
	commonSiteCode = loginSiteCode;
	//初始化  tab切换
	initTab();
	//tab切换 各大块切换；
    $('.every_tip').click(function(){
        var tabid = $(this).index();
        $('.every_tip').removeClass('on');
        $(this).addClass('on');
        //alert(tabid);
        commonBigTab=tabid;
        //tabid 0 下级地方站群 1 下级地方门户 2 本级站点 3 下级部门站群  4 下级部门门户
        if(tabid == 0 || tabid == 3){
        	$(".org").show();
        	$(".tb").hide();
        }else{
        	$(".tb").show();
        	$(".org").hide();
        }
        if(tabid == 0 || tabid == 3 ){
        	getDatas(commonSiteCode,comonnWebName);
        }else if(tabid == 1 || tabid == 4){
        	getOrganizations();
        }else if(tabid == 2){
        	getNatives();
        }
    });
	 $("input[type='radio']").iCheck({
			checkboxClass : 'icheckbox_square-blue',
			radioClass : 'iradio_square-blue'
	 });
	 //图表列表切换；
    $('.change').click(function(){
        var n = $(this).index();
        showbox=(n+1);
        $('.show_box').removeClass('on');
        $('.change').removeClass('on');
        $(this).addClass('on');
        $('.show_box').eq(n).addClass('on');
        if(commonBigTab == 0 || commonBigTab == 3){
        	$(".org").show();
        	$(".tb").hide();
        }else{
        	$(".tb").show();
        	$(".org").hide();
        }
        if(showbox ==1){
        	if(commonBigTab == 0 || commonBigTab == 3){
        		cutSeeOrVisit=2;
        	}else{
        		cutSeeOrVisit=1;
        	}
        	chartImg(cutSeeOrVisit,dataList);
        }else{
        	 if(commonBigTab == 0){
             	getDatas(commonSiteCode,comonnWebName);
             }else if(commonBigTab == 1){
             	getOrganizations();
             }else if(commonBigTab == 2){
             	getNatives();
             }
        }
        
       
    });
    $('.bread').hover(function(){
	  		$(this).children('ul').show();
	  },function(){
	        $(this).children('ul').hide();
	  });
    //单选按钮点击事件
    
    $('[name="chartImg"]').on('ifChecked',function(event) {
    	if(this.id=="radio2"){  //浏览量
			 seeOrVisit = 2;
		 }else if(this.id=="radio3"){ //访客量
			 seeOrVisit = 3;
		 }
		 chartImg(cutSeeOrVisit,dataList);
	});
    $('[name="chartImgg"]').on('ifChecked',function(event) {
    	if(this.id=="radio2"){  //浏览量
			 seeOrVisit = 2;
		 }else if(this.id=="radio3"){ //访客量
			 seeOrVisit = 3;
		 }
		 chartImg(cutSeeOrVisit,dataList);
	});
    //列表表头 1 组织单位  2 填报单位；
    showTable(showTb);
    //获取数据
    comonnWebName=$("#webName").val();
    getDatas(commonSiteCode,comonnWebName);
    
	//列表检索
	$("#searchInfo_id").keyup(function(){
	    var searchInfo=$("#searchInfo_id").val();
	   
		 if(searchInfo==""){
			 $(".table tr").show();
			 var num=0;
			 $(".table").find(".wz-name a").each(function(index, element) {
					 num+=1;
					 $(this).parents("tr").find(".font_701999").html(num);
			});
		 }else{
			 var num=0;
			 $(".table").find(".wz-name a").each(function(index, element) {
				 if($(this).html().indexOf(searchInfo)<0){
					$(this).parents("tr").hide();
				 }else{
					 num+=1;
					 $(this).parents("tr").show();
					 $(this).parents("tr").find(".font_701999").html(num);
				 }
			});
		 }
	});
	initTanChart();
	 $('.siteVisits_sel').hover(function(){
		  $(this).children('ul').show();
	  },function(){
	        $(this).children('ul').hide();
	  });
	$("#siteTypeUl li").click(function(){
	    $("#siteTypeText").html($(this).html());
	    $("#siteTypeUl").hide();
	    tanSeeOrVisit=$(this).val();
	    getTanPic();
	});
});
//获取 下级地方门户
function getOrganizations(){
	$.ajax( {
		type : "POST",
		url : webPath+"/jcVisitOrg_getOrganizations.action",
		data :{
			siteCode:commonSiteCode,
			commonBigTab:commonBigTab
		},
		dataType:"json",
		async : false,
		success : function(data) {
			 if(data){
		        	if(data.success == 'true'){
		        		dataList = data.list;
		        		if(showbox == 2){
		        			showTable(2);
		        			getBody(2,dataList);
		        		}else{
		        			chartImg(1,dataList);
		        		}
	        			
		        	}
			    } else {
			    	alert("获取数据失败");
			    }
		},error: function () {//请求失败处理函数
            alert('请求失败');
        }
	});
}
//获取  本级站点
function getNatives(){
	$.ajax( {
		type : "POST",
		url : webPath+"/jcVisitOrg_getNatives.action",
		data :{
			siteCode:commonSiteCode
		},
		dataType:"json",
		async : false,
		success : function(data) {
			 if(data){
		        	if(data.success == 'true'){
		        		dataList = data.list;
	        			if(showbox == 2){
		        			showTable(2);
		        			getBody(2,dataList);
		        		}else{
		        			chartImg(1,dataList);
		        		}
		        	}
			    } else {
			    	alert("获取数据失败");
			    }
		},error: function () {//请求失败处理函数
            alert('请求失败');
        }
	});
}
//获取下级战群列表数据
function getDatas(sitecode,siteName){
	$.ajax( {
		type : "POST",
		url : webPath+"/jcVisitOrg_getOrgData.action",
		data :{
			siteCode:sitecode,
			commonBigTab:commonBigTab
		},
		dataType:"json",
		async : false,
		success : function(data) {
			 if(data){
		        	if(data.success == 'true'){
		        		dataList = data.list;
		        		level = data.level;
		        		initTab();
		        		initXian(data,siteName);
		        		databaseTreeInfoId=data.databaseTreeInfoId;
		        		if(level<3){
		        			$('.every_tip').removeClass('on');
		        			if(commonBigTab == 3){
		        				$('#tab3').addClass('on');
		        			}else{
		        				$('#tab0').addClass('on');
		        			}
		        			if(showbox == 2){//列表展示
		        				showTable(1);
			        			getBody(1,dataList);
		        			}else{//图标展示
		        				chartImg(2,dataList);
		        			}
		        		}else{
		        			$('.every_tip').removeClass('on');
		        	        $('#tab2').addClass('on');
		        	        level=3;
		        	        commonBigTab=2;
		        			if(showbox == 2){//列表展示
			        			showTable(2);
			        			getBody(2,dataList);
		        			}else{//图标展示
		        				chartImg(1,dataList);
		        			}
		        			
		        		}
		        	}
			    } else {
			    	alert("获取数据失败");
			    }
		},error: function () {//请求失败处理函数
            alert('请求失败');
        }
	});
}
//初始化  tab切换
function initTab(){
	if(level == 1){
		if(commonBigTab != 3){
			commonBigTab=0;
		}
		$("#tab0").show();
		$("#tab1").show();
		$("#tab2").show();
		$("#tab3").show();
		$("#tab4").show();
	}else if(level == 2){
		commonBigTab=0;
		$("#tab0").show();
		$("#tab2").show();
		$("#tab1").show();
		$("#tab3").hide();
		$("#tab4").hide();
	}else if(level == 3){
		commonBigTab=1;
		$("#tab0").hide();
		$("#tab1").hide();
		$("#tab2").show();
		$("#tab3").hide();
		$("#tab4").hide();
	}
	
}

//点击
function onclickOrg(siteCode,siteName,onclickGrade){
	 comonnWebName=siteName;
	 commonSiteCode=siteCode;
	if(onclickGrade == 1){
		$('.every_tip').removeClass('on');
		$('#tab0').addClass('on');
	}
	if(onclickGrade>grade){
		grade++;
	}else if(onclickGrade<grade){
		grade = onclickGrade;
	}
	
//	if(commonBigTab == 0){
//     	getDatas(commonSiteCode,comonnWebName);
//     }else if(commonBigTab == 1){
//     	getOrganizations();
//     }else if(commonBigTab == 2){
//     	getNatives();
//     }
	getDatas(commonSiteCode,comonnWebName);
}
function initXian(data,siteName){
	var html='';
	if(grade == 1){
		var siteName = $("#webName").val();
		html='<span style="cursor: pointer;" onclick="onclickOrg(\''+loginSiteCode+'\',\''+siteName+'\',\''+grade+'\')">'+siteName+'</span>';
		$("#nextNode"+grade).html(html);
		var htmls='';
		if($("#nextNode"+(grade+1)).is(":hidden")){
			$.each(data.list, function(index, obj) {
				htmls+='<li  onclick="onclickOrg(\''+obj.siteCode+'\',\''+obj.name+'\',\''+(grade+1)+'\')">'+obj.name+'</li>';
			});
			$("#nextNodeUl"+(grade+1)).html(htmls);
		}
	}else{
		var  htmlSpan='<span style="cursor: pointer;" ><i class="xie_line"></i>'+siteName+'<i></i></span>';
		$("#nextNodeSpan"+grade).html(htmlSpan);
		if($("#nextNode"+(grade+1)).is(":hidden")){
		$.each(data.list, function(index, obj) {
			html+='<li  onclick="onclickOrg(\''+obj.siteCode+'\',\''+obj.name+'\',\''+(grade+1)+'\')">'+obj.name+'</li>';
		});
		$("#nextNodeUl"+(grade+1)).html(html);
		}
	}
	$("#nextNode"+grade).show();
	for(var i=1;i<5;i++){
		if(i>grade){
//			$("#nextNode"+level).html(html);
			$("#nextNode"+i).hide();
		}
	}
}
function subStr(str,fontSize){
    if(str.length > fontSize){
    	return str.substring(0,fontSize)+'...';
    }else{
    	return str;
    }
}
// type 1 组织单位的  表身  2 填报单位的  表身
function getBody(type,dataList){
	var str='';
	if(type == 1){//组织单位的  表身
		for ( var i = 0; i < dataList.length; i++) {
			var obj = dataList[i];
			str+='<tr>';
			str+='<td  style="width:4%;text-align:center;" class="font_701999">'+(i+1)+'</td>';
			str+='<td class="wz-name"  style="width:10%; text-align:left;"><a style="color:#08c;" href="javascript:onclickOrg(\''+obj.siteCode+'\',\''+obj.name+'\',\''+(grade+1)+'\')" >'+obj.name+'<br>'+obj.siteCode+'</a></td>';
			str+='<td class="sort-num" style="width:7%;text-align:center;"><span class="sort-num">'+obj.pvAvg+'</span></td>'
			+'<td class="sort-num" style="width:7%;text-align:center;"><span class="sort-num">'+obj.pv+'</span></td>'
			+'<td class="sort-num" style="width:7%;text-align:center;"><span class="sort-num">'+obj.uvAvg+'</span></td>'
			+'<td class="sort-num" style="width:7%;text-align:center;"><span class="sort-num">'+obj.uv+'</span></td>'
			+'<td class="sort-num" style="width:7%;text-align:center;"><span class="sort-num" onclick="includeChart(\''+obj.siteCode+'\')"><i class="icon-chart"></i></span></td>';
			str+='</tr>';
		}
	}else{//填报单位的  表身
		for ( var i = 0; i < dataList.length; i++) {
			var obj = dataList[i];
			str+='<tr>';
			str+='<td  style="width:4%;text-align:center;" class="font_701999">'+(i+1)+'</td>';
			str+='<td class="wz-name"  style="width:10%; text-align:left;"><a style="color:#08c;" target="_blank" href="'+webPath+'/fillUnit_gailan.action?siteCode='+obj.siteCode+'" >'+obj.name+'<br>'+obj.siteCode+'</a></td>';
			var webUrl;
			if(obj.jumpUrl.length>0){
				webUrl=checkUrlHTTTP(obj.jumpUrl);
			}else{
				webUrl=checkUrlHTTTP(obj.url);
			}
			str+='<td class="sort-num" style="width:15%;text-align:left;"><a style="color:#08c;word-wrap: break-word;word-break: break-all;" target="_blank"  href="'+webUrl+'" title="'+webUrl+'">'+ subStr(webUrl,20)+'</a></td>';
			
			str+='<td class="sort-num" style="width:7%;text-align:center;"><span class="sort-num">'+obj.urlCnt+'</span></td>'
				 +'<td class="sort-num" style="width:7%;text-align:center;"><span class="sort-num">'+obj.ipCnt+'</span></td>'
				 +'<td class="sort-num" style="width:7%;text-align:center;"><span class="sort-num" onclick="includeChart(\''+obj.siteCode+'\')"><i class="icon-chart"></i></span></td>';
			str+='</tr>';
		}
	}
	$("#bodyB").html(str);
}

//生成柱形图
// type 1 站点  2 组织单位
function chartImg(type ,dataList){
	
	cutSeeOrVisit=type;
	var ylist = new Array();//各个名称
	var xlist = new Array();//
	if(type == 1){//站点  填报单位
		var listt = dataList;
		if(seeOrVisit == 2){
			listt.sort(function(a,b){
	            return a.urlCnt-b.urlCnt});
		}else{
			listt.sort(function(a,b){
	            return a.ipCnt-b.ipCnt});
		}
		for ( var i = 0; i < listt.length; i++) {
			var obj = listt[i];
			ylist.push(obj.name);
			if(seeOrVisit == 2){
				xlist.push(obj.urlCnt);
			}else{
				xlist.push(obj.ipCnt);
			}
		}
	}else{//下级战群数据
		var listt = dataList;
		if(seeOrVisit == 2){
			listt.sort(function(a,b){
	            return a.pvAvg-b.pvAvg});
		}else{
			listt.sort(function(a,b){
	            return a.uvAvg-b.uvAvg});
		}
		for ( var i = 0; i < listt.length; i++) {
			var obj = listt[i];
			ylist.push(obj.name);
			if(seeOrVisit == 2){
				xlist.push(obj.pvAvg);
			}else{
				xlist.push(obj.uvAvg);
			}
		}
	}
	
	if(ylist.length>0){
		 $("#chartContent").css("height",(ylist.length*20+130)+"px");
	}else{
		$("#chartContent").css("height",130+"px");
	}
	/*if(ylist.length==0){
		 
	}*/


	var myChart = echarts.init(document.getElementById('chartContent'));
	option = {
			color:['#20C05C'],
		    tooltip : {
		        trigger: 'axis',
		        axisPointer: {
							color: 'rgba(150,150,150,0.3)',
							width: 'auto',
							type: 'default'
						}
		    },
		    grid:{
		    	x:230	
		    },
		    calculable : false,
		    xAxis : [
		        {
		            position:'top',
		            type : 'value',
		            boundaryGap : [0, 0.01],
		            name:'个'
		        }
		    ],
		    
		    yAxis : [
		        {
		        	scale:true,
		            type : 'category',
		            data : ylist
		      
		        }
		      
		    ],
		    series : [
		        {
		            name:'值',
		            type:'bar',
		            data:xlist
		        }
		    ]
		};
	myChart.setOption(option);	 
	xlist.reverse();
	ylist.reverse();

}



//列表表头 1 组织单位  2 填报单位；
function showTable(showTb){
	var html = '';
	if(showTb == 1){
		html+='<th style="width: 4%;text-align: center;">序号</th>'+ 
        '<th style="width: 10%;text-align: left;">组织单位名称/编码</th>'+ 
/*        '<th style="width:10%;text-align:left;padding-right:57px;">挂标网站数量</th>'+ 
		'<th style="width:7%;position: relative; text-align:center;" class="numOrder tab_angle-right-1">挂标比例</th>'+  */
		'<th style="width:7%;position: relative; text-align:center;" class="numOrder tab_angle-right-1">浏览量平均值</th>'+  
		'<th style="width:7%;position: relative; text-align:center;" class="numOrder tab_angle-right-1">浏览量总数</th>'+  
		'<th style="width:7%;position: relative; text-align:center;" class="numOrder tab_angle-right-1">访客量平均值</th>'+  
		'<th style="width:7%;position: relative; text-align:center;" class="numOrder tab_angle-right-1">访客量总数</th>'+  
		'<th style="width:7%;position: relative; text-align:center;" class="numOrder tab_angle-right-1">图表</th>';
	}else{
		html+='<th style="width: 4%;text-align: center;">序号</th>'+
		'<th style="width: 10%;text-align: left;">填报单位名称/编码</th>'+  
		'<th style="width:15%;text-align:left;">网址</th>'+ 
		/*'<th style="width:7%;position: relative; text-align:center;" class="numOrder tab_angle-right-1">挂标状态</th>'+  */
		'<th style="width:7%;position: relative; text-align:center;" class="numOrder tab_angle-right-1">浏览量</th>'+  
		'<th style="width:7%;position: relative; text-align:center;" class="numOrder tab_angle-right-1">访客量</th>'+  
		'<th style="width:7%;position: relative; text-align:center;" class="numOrder tab_angle-right-1">图表</th>'; 
	}
	$("#titleB").html(html);
}
//倒出功能
function exportDate(){
	window.location.href=webPath+"/jcVisitOrg_exportExcelData.action?siteCode="+commonSiteCode+"&DATA="+new Date();
}

//===========================弹框里面的数据及初始化===============================
function initTanChart(){
    $('[name="a"]').on('ifChecked',function(event) {
    	commonDateNum=this.value;
		includeChart(commSiteCode);
	});
}

function includeChart(sCode){
	commSiteCode=sCode;
    $.ajax( {  
        type : "POST",  
        url : webPath+"/jcVisitOrg_getTrend.action",  
        dataType:"json",
        async : false,
        data:{
        	siteCode:commSiteCode,
        	dateNum:commonDateNum
        },
        success : function(data) {
        	if(data.success=="true"){
        		cList = data.list;
        		getTanPic();
        	}
        }
       });
}	


function getTanPic(){
    var ylist = new Array();
	var xlist = new Array();

    if(commSiteCode.length == 10){//站点  填报单位
		for ( var i = 0; i < cList.length; i++) {
			var obj = cList[i];
			xlist.push(obj.visitDate);
			if(tanSeeOrVisit == 2){
				ylist.push(obj.urlCnt);
			}else{
				ylist.push(obj.ipCnt);
			}
		}
	}else{//下级战群数据
		for ( var i = 0; i < cList.length; i++) {
			var obj = cList[i];
			xlist.push(obj.scanDate);
			if(tanSeeOrVisit == 2){
				ylist.push(obj.pvAvg);
			}else{
				ylist.push(obj.uvAvg);
			}
		}
	}
    
    var myChart_line = echarts.init(document.getElementById("includeChartDiv"));
    var min=0;
    var max = 0;
    if(ylist.length>0){
    	var allIndexListSort = ylist.concat(ylist);
    	allIndexListSort.sort(function(a,b){return a-b});
    	min=parseInt(allIndexListSort[0]*0.06)*10;
    	max = parseInt(allIndexListSort[allIndexListSort.length-1]*0.12)*10;
    }
    option = {
		    color:[
               '#36cd4f'
              ],
    	    tooltip : {
    	        trigger: 'axis',
    	         borderColor: '#48b',
    	         backgroundColor: 'rgba(0,0,0,0.5)',
    	          axisPointer: {
		         color: 'rgba(150,150,150,0.3)',
		        width: 'auto',
		        type: 'default'
		      },
		      //padding:150,
              textStyle : {
		           fontSize: 12
		           }/*,
              formatter: function (params,ticket,callback) {
  	            var res =params[0].name.replace(params[0].name.charAt(4),'年').replace(params[0].name.charAt(7),'月')+'日';
  	            for (var i = 0, l = params.length; i < l; i++) {
  	                res += '<br/>站点收录网页数 : ' + params[i].value+'个';
  	            }
  	            setTimeout(function (){
  	                callback(ticket, res);
  	            }, 0)
  	        } */
    	    },
    	    dataZoom : {
    	        show : true,
    	        realtime : true,
    	        height: 20,
    	        start : 60,
    	        end : 100
    	    },
    	    grid:{
    	    	y:40,
    	    	borderColor:'#fff'
             }, 
    	    xAxis : [
    	        {
    	            axisLine:false,
                    axisTick:false,
    	        	splitLine:false,
    	            type : 'category',
    	            boundaryGap : false,
    	            data : xlist
    	        }
    	    ],
    	    yAxis : [
              {
            	  min:min,
                  max:max,
                  name:'',
                  nameTextStyle:{color:'black'},
                  axisLine:{
                      lineStyle: {
                          color: '#ffffff',
                          width:1
                      }
                  },
                  type : 'value'
              }
              ],
    	    series : [
    	        {
    	            name:'值',
    	            type:'line',
    	            symbol:'emptyCircle',
    	            symbolSize:4,
    	            itemStyle: {
    	            backgroundColor:'rgba(0,0,0,0.5)',
                       normal: {
                         areaStyle: {
                           type: 'default',
                           color:'#e9f9ed'
                              }
                        }},
    	            data: ylist
    	        }
    	    ],
    	    calculable:false
    	};
    myChart_line.setOption(option,true);
    $('#prompt').modal({});
}




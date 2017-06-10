//
var level = 2;
var showbox=2 ;//  1 代表图表 2 代表列表 
var showTb=1;// 列表表头 1 组织单位  2 填报单位；
var grade =1;// 便于 斜线处的使用
var loginSiteCode = 0;
var databaseTreeInfoId=0;
var dataList;//图表展示的数据

var siteOrDomain=1;// 1 站点收录网页数  2 域收录网页数

var commonSiteCode;
var comonnWebName;

var querySiteData = 0;//数据来源 0 非 点击站点数获取   1 点击站点数获取
var querySiteDataSiteCode ;
// 弹出的 图框使用的
var commonDateNum=-14;
var commSiteCode;//点击图标 弹出的弹框 标识码
var tanSeeOrVisit=2;//2  浏览量走势图  3 访问量走势图
var cList;//弹框使用的 请求成功的数据
$(function(){
	loginSiteCode=$("#siteCode").val();
	commonSiteCode = loginSiteCode;
	comonnWebName=$("#webName").val();
	
	 //图表列表切换；
    $('.change').click(function(){
        var n = $(this).index();
        showbox=(n+1);
        $('.show_box').removeClass('on');
        $('.change').removeClass('on');
        $(this).addClass('on');
        $('.show_box').eq(n).addClass('on');
        
        if(showbox ==1){//图表
        	chartImg(1,dataList);
        }else{// 列表
        	if(querySiteData == 1){
        		querySite(querySiteDataSiteCode);
        	}else{
        		getDatas(commonSiteCode,comonnWebName);
        	}
        }
    });
    
    $('.chart_se').hover(function(){
		  $(this).children('ul').show();
	  },function(){
	        $(this).children('ul').hide();
	  });
	  //选择 展示条数
	  $('[name="monitorType"]').click(function(){
			 $("#monitorTypeUl").hide();
			 $("#monitorTypeName").html($(this).html());
			 siteOrDomain=$(this).val();
			 chartImg(siteOrDomain,dataList);
	  });
    $('.bread').hover(function(){
	  		$(this).children('ul').show();
	  },function(){
	        $(this).children('ul').hide();
	  });
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
	$("#bodyBSort").width($("#bodyBSort").parent(".lb_table").width());
	var tabHeaderTop=document.getElementById("bodyBSort").offsetTop-105;
	$(window).scroll(function (e) {
		if(tabHeaderTop < $(window).scrollTop()){
			$("#bodyBSort").addClass("view-head-fixed");
		}else{
			$("#bodyBSort").removeClass("view-head-fixed");
		}
		return false;
	});
});

//获取下级战群列表数据
function getDatas(sitecode,siteName){
	$.ajax( {
		type : "POST",
		url : webPath+"/baiduBigData_getDatas.action",
		data :{
			siteCode:sitecode
		},
		dataType:"json",
		async : false,
		success : function(data) {
			 if(data){
		        	if(data.success == 'true'){
		        		querySiteData = 0;
		        		dataList = data.list;
		        		level = data.level;
		        		initXian(data,siteName);
		        		databaseTreeInfoId=data.databaseTreeInfoId;
		        		if(level<3){
		        			if(showbox == 2){//列表展示
		        				showTable(1);
			        			getBody(1,dataList);
		        			}else{//图标展示
		        				chartImg(siteOrDomain,dataList);
		        			}
		        			new TableSorter("bodyB",2,3,4);
		        			$("#bodyBSort th").on('click', function(event){
		        				if($(this).find(".tab_angle").hasClass("tab_angle_bottom")){
		        					
		        					$("#bodyB .tab_angle").attr("class","tab_angle");
		        					$(this).find(".tab_angle").addClass("tab_angle_top");
		        					$(this).find(".tab_angle").removeClass("tab_angle_bottom");
		        				}else{
		        					$("#bodyB .tab_angle").attr("class","tab_angle");
		        					$(this).find(".tab_angle").addClass("tab_angle_bottom");
		        					$(this).find(".tab_angle").removeClass("tab_angle_top");
		        				}
		        			  });
		        			$(".numOrder").click(function(){
		        				 var num=0;
		        				 $(".table").find(".wz-name").each(function(index, element) {
		        					 if( $(this).parents("tr").css("display") != "none"){
		        						 num+=1;
		        						 $(this).parents("tr").find(".font_701999").html(num);
		        					 }else if($(this).parents("tr").css("display") == "table-row"){
		        						 num+=1;
		        						 $(this).parents("tr").find(".font_701999").html(num);
		        					 }
		        				});
		        			});
		        		}else{
		        	        level=3;
		        			if(showbox == 2){//列表展示
			        			showTable(2);
			        			getBody(2,dataList);
		        			}else{//图标展示
		        				chartImg(siteOrDomain,dataList);
		        			}
		        			new TableSorter("bodyB",3,4,6);
		        			$("#bodyBSort th").on('click', function(event){
		        				//alert("2");
		        				if($(this).find(".tab_angle").hasClass("tab_angle_bottom")){
		        					
		        					$("#bodyB .tab_angle").attr("class","tab_angle");
		        					$(this).find(".tab_angle").addClass("tab_angle_top");
		        					$(this).find(".tab_angle").removeClass("tab_angle_bottom");
		        				}else{
		        					$("#bodyB .tab_angle").attr("class","tab_angle");
		        					$(this).find(".tab_angle").addClass("tab_angle_bottom");
		        					$(this).find(".tab_angle").removeClass("tab_angle_top");
		        				}
		        			  });
		        			$(".numOrder").click(function(){
		        				 var num=0;
		        				 $(".table").find(".wz-name").each(function(index, element) {
		        					 if( $(this).parents("tr").css("display") != "none"){
		        						 num+=1;
		        						 $(this).parents("tr").find(".font_701999").html(num);
		        					 }else if($(this).parents("tr").css("display") == "table-row"){
		        						 num+=1;
		        						 $(this).parents("tr").find(".font_701999").html(num);
		        					 }
		        				});
		        			});
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

//点击
function onclickOrg(siteCode,siteName,onclickGrade){
	 comonnWebName=siteName;
	 commonSiteCode=siteCode;
	if(onclickGrade>grade){
		grade++;
	}else if(onclickGrade<grade){
		grade = onclickGrade;
	}
	
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
			str+='<td class="sort-num" style="width:7%;text-align:center;"><a class="sort-num" href="javascript:querySite(\''+obj.siteCode+'\')" style="cursor:pointer;">'+obj.siteSum+'</a></td>'
			+'<td class="sort-num" style="width:7%;text-align:center;"><span class="sort-num">'+obj.baiduSiteAvg+'</span></td>'
			+'<td class="sort-num" style="width:7%;text-align:center;"><span class="sort-num">'+obj.baiduDomainAvg+'</span></td>'
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
			
			str+='<td class="sort-num" style="width:7%;text-align:center;"><span class="sort-num">'+obj.baiduSlWebsite+'</span></td>'
				 +'<td class="sort-num" style="width:7%;text-align:center;"><span class="sort-num">'+obj.baiduSlDomainsite+'</span></td>'
				 +'<td class="sort-num" style="width:7%;text-align:center;"><span class="sort-num" onclick="includeChart(\''+obj.siteCode+'\')"><i class="icon-chart"></i></span></td>'
				 +'<td class="sort-num" style="width:7%;text-align:center;"><span class="sort-num">'+obj.scanDate+'</span></td>'
			str+='</tr>';
		}
	}   
	$("#bodys").html(str);
}
function querySite(sitecode){
	querySiteDataSiteCode = sitecode;
	$.ajax( {  
        type : "POST",  
        url : webPath+"/baiduBigData_querySiteData.action",  
        dataType:"json",
        async : false,
        data:{
        	siteCode:sitecode
        },
        success : function(data) {
        	if(data.success=="true"){
        		querySiteData =1;
        		dataList = data.list;
        		level=3;
    			if(showbox == 2){//列表展示
        			showTable(2);
        			getBody(2,dataList);
    			}else{//图标展示
    				chartImg(siteOrDomain,dataList);
    			}
    			new TableSorter("bodyB",3,4,6);
    			$("#bodyBSort th").on('click', function(event){
    				//alert("2");
    				if($(this).find(".tab_angle").hasClass("tab_angle_bottom")){
    					
    					$("#bodyB .tab_angle").attr("class","tab_angle");
    					$(this).find(".tab_angle").addClass("tab_angle_top");
    					$(this).find(".tab_angle").removeClass("tab_angle_bottom");
    				}else{
    					$("#bodyB .tab_angle").attr("class","tab_angle");
    					$(this).find(".tab_angle").addClass("tab_angle_bottom");
    					$(this).find(".tab_angle").removeClass("tab_angle_top");
    				}
    			  });
    			$(".numOrder").click(function(){
    				 var num=0;
    				 $(".table").find(".wz-name").each(function(index, element) {
    					 if( $(this).parents("tr").css("display") != "none"){
    						 num+=1;
    						 $(this).parents("tr").find(".font_701999").html(num);
    					 }else if($(this).parents("tr").css("display") == "table-row"){
    						 num+=1;
    						 $(this).parents("tr").find(".font_701999").html(num);
    					 }
    				});
    			});
        		
        	}
        }
       });	
}
//生成柱形图
// type 1 站点收录网页数  2 域收录网页数
function chartImg(type ,dataList){
	
	var ylist = new Array();//各个名称
	var xlist = new Array();//
	if(level == 3){//站点  填报单位
		var listt = dataList;
		if(type == 1){//1 站点收录网页数 
			listt.sort(function(a,b){
	            return a.baiduSlWebsite-b.baiduSlWebsite});
		}else{// 2 域收录网页数
			listt.sort(function(a,b){
	            return a.baiduSlDomainsite-b.baiduSlDomainsite});
		}
		for ( var i = 0; i < listt.length; i++) {
			var obj = listt[i];
			ylist.push(obj.name);
			if(type == 1){
				xlist.push(obj.baiduSlWebsite);
			}else{
				xlist.push(obj.baiduSlDomainsite);
			}
		}
	}else{//组织单位
		var listt = dataList;
		if(type == 1){//1 站点收录网页数 
			listt.sort(function(a,b){
	            return a.baiduSiteAvg-b.baiduSiteAvg});
		}else{
			listt.sort(function(a,b){
	            return a.baiduDomainAvg-b.baiduDomainAvg});
		}
		for ( var i = 0; i < listt.length; i++) {
			var obj = listt[i];
			ylist.push(obj.name);
			if(type == 1){//1 站点收录网页数 
				xlist.push(obj.baiduSiteAvg);
			}else{
				xlist.push(obj.baiduDomainAvg);
			}
		}
	}
	
	if(ylist.length>0){
		 $("#chartContent").css("height",(ylist.length*20+130)+"px");
	}else{
		$("#chartContent").css("height",130+"px");
	}


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
		'<th style="width:7%;position: relative; text-align:center;" class="numOrder tab_angle-right-1">站点收录数量<br><div style="color:#999; font-weight:normal;" title="">（个）</div><i class="tab_angle"></i></th>'+  
		'<th style="width:7%;position: relative; text-align:center;" class="numOrder tab_angle-right-1">站点收录网页数<br><div style="color:#999; font-weight:normal;" title="">（平均）</div><i class="tab_angle"></th>'+  
		'<th style="width:7%;position: relative; text-align:center;" class="numOrder tab_angle-right-1">域收录网页数<br><div style="color:#999;font-weight:normal;" title="">（平均）</div><i class="tab_angle"></th>'+  
		'<th style="width:7%;position: relative; text-align:center;" class="numOrder tab_angle-right-1">图表</th>';
	}else{
		html+='<th style="width: 4%;text-align: center;">序号</th>'+
		'<th style="width: 10%;text-align: left;">填报单位名称/编码</th>'+  
		'<th style="width:15%;text-align:left;">网址</th>'+ 
		/*'<th style="width:7%;position: relative; text-align:center;" class="numOrder tab_angle-right-1">挂标状态</th>'+  */
		'<th style="width:7%;position: relative; text-align:center;" class="numOrder tab_angle-right-1">站点收录网页数<i class="tab_angle"></i></th>'+  
		'<th style="width:7%;position: relative; text-align:center;" class="numOrder tab_angle-right-1">域收录网页数<i class="tab_angle"></i></th>'+  
		'<th style="width:7%;position: relative; text-align:center;" class="numOrder tab_angle-right-1">图表</th>'+
		'<th style="width:7%;position: relative; text-align:center;" class="numOrder tab_angle-right-1">更新时间<i class="tab_angle"></i></th>'; 
	}
	$("#titleB").html(html);
}
//倒出功能
function exportDatas(){
	window.location.href=webPath+"/baiduBigData_exportExcelData.action?siteCode="+commonSiteCode+"&querySiteCode="+querySiteDataSiteCode+"&flag="+querySiteData+"&DATA="+new Date();
}

//===========================弹框里面的数据及初始化===============================

function includeChart(sCode){
	commSiteCode=sCode;
    $.ajax( {  
        type : "POST",  
        url : webPath+"/baiduBigData_getTrend.action",  
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
			xlist.push(obj.scanDate);
			ylist.push(obj.baiduSlWebsite);
		}
	}else{//下级战群数据
		for ( var i = 0; i < cList.length; i++) {
			var obj = cList[i];
			xlist.push(obj.scanDate);
			ylist.push(obj.baiduSiteAvg);
		}
	}
    
   
    var myChart_line = echarts.init(document.getElementById("includeChartDiv"));
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
		           },
              formatter: function (params,ticket,callback) {
  	            var res =params[0].name.replace(params[0].name.charAt(4),'年').replace(params[0].name.charAt(7),'月')+'日';
  	            for (var i = 0, l = params.length; i < l; i++) {
  	                res += '<br/>站点收录网页数 : ' + params[i].value+'个';
  	            }
  	            setTimeout(function (){
  	                callback(ticket, res);
  	            }, 0)
  	        } 
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
            	  //min:min,
                 // max:max+10,
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




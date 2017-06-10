var cList;
var dateNum = -14;
var commSiteCode;
var tanSeeOrVisit=3;//2  浏览量走势图  3 访客量走势图

$(function () {

	//列表检索
    $("#keyId").keyup(function(){
        var searchInfo=$("#keyId").val();
			 var nums=0;
			 $("#siteVisitsTbody").find(".wz-name").each(function(index, element) {
				 if(($(this).html().indexOf(searchInfo)<0) && ($(this).parent().find(".wz-code").html().indexOf(searchInfo)<0)){
					 $(this).parents("tr").hide();  
				 }else{
					 nums+=1;
					 $(this).parents("tr").show();
					 $(this).parents("tr").find(".num").html(nums);
				 }
			});
    });
 
$("#monitoringDate").datepicker({
	inline : true,
	showOn: "both",
    buttonImage: webPath+"/images/date.png",
	buttonImageOnly: true,
	numberOfMonths : 1,// 显示几个月
	showButtonPanel : true,// 是否显示按钮面板
	dateFormat : 'yy-mm-dd',// 日期格式
	clearText : "清除",// 清除日期的按钮名称
	closeText : "关闭",// 关闭选择框的按钮名称
	yearSuffix : '年', // 年的后缀
	showMonthAfterYear : true,// 是否把月放在年的后面
	defaultDate : $("#yesterdayId").val(),// 默认日期
	minDate : GetDateStr(-90),//最小日期
	maxDate : GetDateStr(-1),// 最大日期
	monthNames : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月',
			'9月', '10月', '11月', '12月' ],
	dayNames : [ '星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ],
	dayNamesShort : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
	dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ],
	onSelect : function(dateText, inst){
		siteVisitsTbody();
	}
}); 


var yesterday = $("#yesterdayId").val();
$("#monitoringDate").val(yesterday);

siteVisitsTbody();

$("#table-webSort th").on('click', function(event){
	if($(this).find(".tab_angle").hasClass("tab_angle_bottom")){
		$("#table-web .tab_angle").attr("class","tab_angle");
		$(this).find(".tab_angle").addClass("tab_angle_top");
		$(this).find(".tab_angle").removeClass("tab_angle_bottom");
	}else{
		$("#table-web .tab_angle").attr("class","tab_angle");
		$(this).find(".tab_angle").addClass("tab_angle_bottom");
		$(this).find(".tab_angle").removeClass("tab_angle_top");
	}
  });

	initTanChart();
	$('.siteVisits_sel').hover(function(){
		  $(this).children('ul').show();
	},function(){
	      $(this).children('ul').hide();
	});
	$("#chartTypeUl li").click(function(){
	  $("#chartTypeText").html($(this).html());
	  $("#chartTypeUl").hide();
	  tanSeeOrVisit=$(this).val();
	  getSiteVisitsTanPic();
	});
	
	$("#yunfxId").click(function (){
		var uri = webPath + "/cfgOtherProducts_yunAnalytics.action";
		$.cookie('top_cookie', uri, {path:'/'});
	});
});

/**
 * ul
 * @param id
 */
function selectShow(id){
    $("#"+id+"Ul li").click(function(){
        $("#"+id+"Val").val(this.value);
        $("#"+id+"Text").html($(this).html());
        $("#"+id+"Ul").hide();
        siteVisitsTbody();
    });
}

function siteVisitsTbody(){
	$("#siteVisitsTbody").html(copyInformation(2,null));
	var siteType = $("#siteTypeVal").val();
	var monitoringDate = $("#monitoringDate").val();
	 $.ajax( {  
	     type : "POST",  
	     url : webPath+"/siteVisits_getSiteVisitsList.action",  
	     data : {
	    	 siteType:siteType,
	    	 monitoringDate:monitoringDate
	     },  
	     dataType:"json",
	     async : false,  
	     success : function(data) {
	    	 if(data.success == 'true'){
	    		 $("#siteVisitsTbody").html("");
	    		 $("#table-Sum").html("");
	    		 $("#sizeId").html("");
	    		 var html = '';
	    		 var sumHtml = '';
	    		 var list = data.body;
	    		 if(list.length > 0 && list != null){
	    			 
	    			 $("#sizeId").html(data.size);
	    			 sumHtml+='<tr >';
	    			 sumHtml+=' <td class="summary-td-2 padl-18 td-center" style="width:70px;">汇总</td>';
	    			 sumHtml+=' <td></td>';
	    			 sumHtml+=' <td><i class="summary-icon-2"></i></td>';
	    			 sumHtml+='<td class="td-center"><i class="td_num td_num_57a1f2">'+data.homeUrlNum+'</i></td>';
	    			 sumHtml+='<td class="td-center"><i class="td_num td_num_b233b9">'+data.urlCntNum+'</i></td>';
	    			 sumHtml+='<td class="td-center"><i class="td_num td_num_51d466">'+data.ipCntNum+'</i></td>';
	    			 sumHtml+='<td class="td-center"></td>';
	    			 sumHtml+=' </tr>';
	    			 
	    			 
	    			 $("#table-Sum").append(sumHtml);
	    			 
	    			 var ur = webPath + "/fillUnit_gailan.action";
        			 var uriThree = webPath + "/jcVisitSitecode_index.action";
	    			 
	    			 var num = 0;
	    			 $.each(list, function(index, obj) {
	    				 var url = obj.url;
	    				 if(url != ""){
	    					 if(!url.match("http")){
	 	        				 url="http://"+url;
	 	        			 }
	    				 }
	    				 num++;
	    				 
	    				 var uri = url.substr(0,30);
	    				 html+='<tr>';
	    				 html+='<td class="td-center num" style="width:70px;">'+num+'</td>';
	    				 html+='<td class="td-center wz-code">'+obj.siteCode+'</td>';
	    				 html+='<td class="td-left  wz-name">'+obj.name+'</td>';
	    				 html+='<td class="td-left" title="'+url+'"><a href="'+url+'" target="_blank" class="cor-01a5ec url">'+uri+'</a></td>';
	    				 html+='<td class="td-center cor-01a5ec"><a onClick="changeCookie(\''+2+'\',\''+ur+'\',\''+null+'\',\''+uriThree+'\');" href="'+webPath+'/jcVisitSitecode_index.action?siteCode='+obj.siteCode+'" target="_blank" class="cor-01a5ec">'+obj.urlCnt+'</a></td>';
	    				 html+='<td class="td-center">'+obj.ipCnt+'</td>';
	    				 html+='<td class="td-center cor-85b"><span class="sort-num" onclick="siteVisitsChart(\''+obj.siteCode+'\')"><i class="publi-ico_2 tbzx-ico-c"></i></span></td>'; 
	    				 html+='</tr>';     
	    				 
	    			 });
	    			 $("#siteVisitsTbody").append(html);
	    		 }else{
	    			 var text = copyInformation(1,"未发现问题");
	    			 $("#siteVisitsTbody").append(text);
	    		 }
	    	 }
	     },
	     error:function(data){
			alert(data.errorMsg);
		 }
	    });
}

function siteVisitsListExcel(){
	var siteType = $("#siteTypeVal").val();
	var monitoringDate = $("#monitoringDate").val();
	window.location.href = webPath + "/siteVisits_getSiteVisitsListExcel.action?siteType=" 
		+ siteType + "&monitoringDate=" + monitoringDate ;
}


function initTanChart(){
    $('[name="a"]').on('ifChecked',function(event) {
    	dateNum=this.value;
    	siteVisitsChart(commSiteCode);
	});
}

function siteVisitsChart(siteCode){
	commSiteCode = siteCode;
    $.ajax( {  
        type : "POST",  
        url : webPath+"/siteVisits_getSiteVisitsTrend.action",  
        dataType:"json",
        async : false,
        data:{
        	siteCode:commSiteCode,
        	dateNum:dateNum
        },
        success : function(data) {
        	if(data.success=="true"){
        		cList = data.list;
        		getSiteVisitsTanPic();
        	}
        }
       });
}

function getSiteVisitsTanPic(){
    var ylist = new Array();
	var xlist = new Array();

	for ( var i = 0; i < cList.length; i++) {
		var obj = cList[i];
		xlist.push(obj.visitDate);
		if(tanSeeOrVisit == 2){
			ylist.push(obj.urlCnt);
		}else{
			ylist.push(obj.ipCnt);
		}
	}
    
    var myChart_line = echarts.init(document.getElementById("siteVisitsChartDiv"));
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
    $('#siteVisitsPrompt').modal({});
}



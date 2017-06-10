var sitcodVisit=1;//0  浏览量走势图  1 访问量走势图
var siteList;

$(function(){
	 //单选按钮添加添加事件
	 $("input[type='radio']").iCheck({
			checkboxClass : 'icheckbox_square-blue',
			radioClass : 'iradio_square-blue'
	 });
	$("input[name='days']").on('ifChecked',function(event) {
		var days=$(this).val();
		getJcVisitList(days)
	});
   //列表数据加载
	getJcVisitList(-30);
	
	 $('.siteVisits_sel').hover(function(){
		  $(this).children('ul').show();
	  },function(){
	        $(this).children('ul').hide();
	  });
	$("#siteTypeUl li").click(function(){
	    $("#siteTypeText").html($(this).html());
	    $("#siteTypeUl").hide();
	    sitcodVisit=$(this).val();
	    getSiteData();
	});
});

function getJcVisitList(days){
    $.ajax( {  
    	type: "POST",
        url: webPath+"/jcVisitOrg_getTrend.action",
        data : {dateNum:days,
        		siteCode:$("#siteCode").val()
        	},
        dataType: "json",
        async: false,
        success : function(data) {
        	if(data.success=="true"){
        		siteList = data.list;
        		getSiteData();
        	}
        }
       });
}
function getSiteData(){
	var myChart = echarts.init(document.getElementById("jcVisisChart"));
	var ylist = new Array();
	var xlist = new Array();
	for ( var i = 0; i < siteList.length; i++) {
		var obj = siteList[i];
		xlist.push(obj.visitDate);
		if(sitcodVisit == 1){
			ylist.push(obj.ipCnt);
		}else{
			ylist.push(obj.urlCnt);
		}
	}
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
		       padding:15,
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
	myChart.setOption(option,true);
}

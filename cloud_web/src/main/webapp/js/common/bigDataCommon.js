var quesVals = 0;
var pagesVals = 0;

function getScanProblemNum(){
	
	var max = 30;
	var min = 10;

	
	$.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
       // url: webPath + "/dicConfig_getDicConfig.action?configId=7,8,9,10,11,12",// 请求的action路径
        url: webPath + "/siteDataOverviewChart_getScanProblemNum.action",
        success: function (data) { // 请求成功后处理函数。
        	if (data) {
        		$("#Pages").html(randomNum(data['dicConfig'][0]['value']));
        		$("#Ques").html(randomNum(data['dicConfig'][1]['value']));
        		
        		//更新监测页面随机数
            	 window.setInterval("pages("+data['dicConfig'][0]['value']+","+max+","+min+")",500);
            	//更新发现问题随机数
            	 window.setInterval("ques("+data['dicConfig'][1]['value']+",8,2)",1000);
            } else { 
            	$("#Pages").html("0");
        		$("#Ques").html("0");
            }
        },error: function () {// 请求失败处理函数
        	$("#Pages").html("0");
    		$("#Ques").html("0");
        }
    });
	
}


//更新监测页面随机数
function pages (val,max,min){
	var $img = $('<img src="https://jg.kaipuyun.cn/cloud_web/images/date.png?'+(new Date())+'">');
	$img.appendTo('body').css("display","none").load(function(){
		var ranNum = randomNumPages(val,max,min);
		$("#Pages").html(ranNum);
	}).error(function(){    
       // console.log("断网了！")
	});
	
}
//更新发现问题随机数
function ques (val,max,min){
	var $img = $('<img src="https://jg.kaipuyun.cn/cloud_web/images/date.png?'+(new Date())+'">');
	$img.appendTo('body').css("display","none").load(function(){
		var ranNum = randomNumQues(val,max,min);
		$("#Ques").html(ranNum);
	}).error(function(){    
       // console.log("断网了！")
	 });
}
//根据监测页面数、随机最大值、随机最小值生成千分位形式的随机数
function randomNumPages (val,max,min){
	var maxNum = max;
	var minNum = min;
	pagesVals += Math.floor(minNum+Math.random()*(maxNum-minNum));
	var Num = (val + pagesVals).toString();
	var result = "";
    while (Num.length > 3) {
        result = "," + Num.slice(-3) + result;
        Num = Num.slice(0, Num.length - 3);
    }
    if (Num) { result = Num + result; }
	return result;
}
//根据发现问题数、随机最大值、随机最小值生成千分位形式的随机数
function randomNumQues (val,max,min){
	var maxNum = max;
	var minNum = min;
	quesVals += Math.floor(minNum+Math.random()*(maxNum-minNum));
	var Num = (val + quesVals).toString();
	var result = "";
	while (Num.length > 3) {
		result = "," + Num.slice(-3) + result;
		Num = Num.slice(0, Num.length - 3);
	}
	if (Num) { result = Num + result; }
	return result;
}
//初始化千分位形式的数
function randomNum (val){
	var Num = val.toString();
	var result = "";
	while (Num.length > 3) {
		result = "," + Num.slice(-3) + result;
		Num = Num.slice(0, Num.length - 3);
	}
	if (Num) { result = Num + result; }
	return result;
}
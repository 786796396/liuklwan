<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>问题截图</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/common/heade.jsp"%>
	<link rel="stylesheet" href="<%=path%>/css/base.css" />
	<link rel="stylesheet" href="<%=path%>/css/online_report.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/echarts-all.js"></script>
	<script>
	$(function(){
		var imgUrl = getQuery("imgUrl");
		var litImgUrl = getQuery("litImgUrl");
		var imgs = spliceImgUrls(imgUrl, litImgUrl);
		$("#cutImgs").html(imgs);
	});
	
	/**
	 * 拼接imgUrl.(问题截图有多个，此处需要拼接多个用于显示)
	 */
	function spliceImgUrls(imgUrlStr, litImgUrl){
		var preFix = "";
		var returnStr = "";
		if(!isEmpty(litImgUrl)){
			preFix = litImgUrl;
		}
		
		var imgUrls = imgUrlStr.split("|");
		for(var i in imgUrls){
			if(!isEmpty(imgUrls[i])){
				returnStr += ("<img src='" + preFix +imgUrls[i] + "'><br>");
			}
		}
		
		return returnStr;
	}
	/*
 * 接收地址栏参数  key:参数名称
 * */
function getQuery(parameter) {
    var url = window.location.href; //获取url
    var model = "([?&])" + parameter + "=([^&]*)"; 
    var oModel = new RegExp(model); 
    if (oModel.test(url)) { 
        return RegExp["$2"];             
    } else {
        return null;
    }
}
/*
 * 验证是否为空
 */
function isEmpty(str) {
    var isOK = false;
    if (str == undefined || str == 'undefined' || str == "" || str == "null") {
        isOK = true;
    }
    return isOK;
}
	</script>
</head>
<body>
    <div id="cutImgs">
    </div>
  </body>
</html>

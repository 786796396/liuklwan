<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<title>首页更新及时性</title>
	 <%@ include file="/newWeiXin/jsp/common_new.jsp"%>
	 <link rel="stylesheet" href="<%= path %>/newWeiXin/css/hp-UpdateNotInTime.css"/>
  </head>
<body>
<div class="wrap">
        <div class="result-set">
            <div class="tb-main-title">
                <p class="font-32">${initMap.siteName }</p>
                <p class="font-20 margt-24">${initMap.siteCode}</p>
            </div>
            <div class="result-detail">
                <p class="clearfix">
                    <span class="fl">最近扫描</span>
                    <span class="fr">${initMap.scanDate}</span>
                </p>
                <p class="clearfix">
                    <span class="fl">未更新天数</span>
                    <span class="fr">${initMap.notUpdateNum}天</span>
                </p>
                <p class="clearfix last-item">
                    <span class="fl">最后更新日期</span>
                    <span class="fr">${initMap.modifyDate}</span>
                </p>
            </div>
        </div>
        <div class="block-style-first">
            <div class="hp-snapshot-part">
                <img src="<%= path %>/newWeiXin/images/snapshot.png" alt="" onclick="getShot('${initMap.imageUrl}')" />
            </div>
        </div>
    </div>
	<%@ include file="/newWeiXin/jsp/footer.jsp"%>
	<script src="<%= path %>/newWeiXin/js/common.js"></script>
	<script type="text/javascript">
    //快照页面
	function getShot(imgUrl){
		if(isEmpty(imgUrl)){
			alert("暂时没有快照，无法查看！");
			return;
		}
		window.open(imgUrl);
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
</body>
</html>

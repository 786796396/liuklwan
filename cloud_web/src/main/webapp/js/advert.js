//<script src="http://jg.kaipuyun.cn/cloud_backweb/js/cofigAdvert/advert.js?v=2" encodead="ad_0_0_2" id="_ad_"></script>

//ad_1_0_2
//ad广告头，1类别，0类型，2id

// 在页面增加一个放置图标的区块
if (!document.getElementById("_span_ad"))
	document.write("<span id=\"_span_ad\" class=\"_span_ad\"></span>");

// 获取放置图标的区块
var span_msg = document.getElementById("_span_ad");

// 获取参数:id，类型，类别
var encodead = document.getElementById("_ad_").attributes["encodead"].value;

// 获取id
var id = encodead.substring(encodead.lastIndexOf("_") + 1, encodead.length);
//alert(encodead);
// 获取链接地址\图片地址
advert();
function advert() {
	$.ajax({
		type : "POST",
		url : "advertSearch_advertSearch.action",
		data : {
			id : id
		},
		dataType : "json",
		async : true,
		success : function(result) {
			var linkAddr = result.linkAddr;
			var urlAddr = result.urlAddr;
			var backColor = result.backColor;
			var widthHeight = "";
			if(result.width){
				widthHeight += "width:" + result.width + "px;";
			}
			if(result.height){
				widthHeight += "height:" + result.height + "px;";
			}
			if (urlAddr) {
				// 生成广告图标及点击链接
				if (linkAddr) {
					span_msg.innerHTML = "<img onclick=\"onLink('"
						+ linkAddr
						+ "');_trackData.push(['addaction','概览页', '云分析-head', 'top_button', '点击量'])\" style=\"margin:0;border:0;cursor: pointer;" + widthHeight + "\" src=\""
						+ urlAddr + "?v=" + id + "\"/>";
				} else {
					span_msg.innerHTML = "<img style=\"margin:0;border:0;cursor: pointer;" + widthHeight + "\" src=\""
						+ urlAddr + "?v=" + id + "\"/>";
				}
				$("#advertDiv").append("<input id='advertDivShowType' type='hidden' value='show'/>");
//				$("#advertDiv").show();
				$("#advertDiv").css("background-color",backColor);
//				$('.other-products-part').css("paddingTop","170px");
//				$(".base-info").css("top","110px");
//				$("#sidebar").css("top","150px");
//				$(".page-content").css("paddingTop","175px");
			} else {
				$("#advertDiv").append("<input id='advertDivShowType' type='hidden' value='hide'/>");
				$("#advertDiv").hide();
				$('.other-products-part').css("paddingTop","110px");
				$(".base-info").css("top","50px");
				$("#sidebar").css("top","90px");
				$(".page-content").css("paddingTop","115px");
				
			}
		},
		error : function() {
			console.log("查询错误");
		}
	});
}

// 点击图标
function onLink(linkAddr) {
	// 跳转至广告页面
	window.open(linkAddr);
}

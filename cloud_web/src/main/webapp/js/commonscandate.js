var timer=null;
var html='';
var copyWrite = null;
html="<li class='fl'>121.41.11.226</li><li class='fl'>120.26.93.149</li><li class='fl'>112.124.117.141</li><li class='fl'>120.55.90.78</li><li class='fl'>120.26.42.212</li><li class='fl'>121.40.74.31</li><li class='fl'>120.55.180.10</li><li class='fl'>121.40.123.173</li><li class='fl'>121.41.29.248</li><li class='fl'>120.26.231.148</li><li class='fl'>112.74.90.36</li><li class='fl'>123.57.18.200</li><li class='fl'>121.42.42.141</li><li class='fl'>139.224.104.143</li><li class='fl'>121.40.212.65</li><li class='fl'>121.40.159.176</li><li class='fl'>121.40.128.166</li><li class='fl'>120.55.90.117</li><li class='fl'>120.26.41.235</li><li class='fl'>202.85.216.207</li><li class='fl'>114.215.174.217</li>";

/**
 * @描述:隐藏显示 ip
 * @作者:liujc@ucap.com.cn
 * @时间:2017-5-4上午10:35:58
 */
function ip_address(){
    if($('.ip_address i').hasClass('down')){
        $('.hidden_part').slideDown();
        $("p[name='flag']").slideUp();
        $('.ip_address i').removeClass('down').addClass('up');

    }else{
        $('.hidden_part').slideUp();
        $("p[name='flag']").slideDown();
        $('.ip_address i').removeClass('up').addClass('down');
    }
    copyWrite = html.replace(/<li class='fl'>/g,"");
	copyWrite = copyWrite.replace(/<\/li>/g,";");
	$("#commonscandate_copyAllId").zclip({
		path: webPath+"/js/ZeroClipboard.swf",
		copy: function(){
			console.info(copyWrite);
		return copyWrite;
		}
	});
}

//关闭
function commonscandate_copyCloseId(){
	$("#lud_detail").css('display','none');
}
//最后正常监测时间提示
function commonscandateOver() {  // 打开
			clearInterval(timer);
			timer = setInterval(function() {
				$("#lud_detail").show();
				$("#whiteListIP").html(html);
			}, 800);
			copyWrite = html.replace(/<li class='fl'>/g,"");
			copyWrite = copyWrite.replace(/<\/li>/g,";");
			$("#commonscandate_copyAllId").zclip({
				path: webPath+"/js/ZeroClipboard.swf",
				copy: function(){
					console.info(copyWrite);
				return copyWrite;
				}
			});
		
			//复制
//			$('#commonscandate_copyAllId').click(function(){
//			
//			});
			
//			$('#commonscandate_copyCloseId').click(function(){
//				
//			});
}
function commonscandateOut() {  // 关闭
	clearInterval(timer);
	timer = setInterval(function() {
		$("#lud_detail").hide();
	}, 60);
}

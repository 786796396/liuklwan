<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="http-ms-msg" style="display:none;">
    <div class="ms-wen-con">
        <h3>HTTP状态码定义</h3>
        <div class="ztm-con">
            <ul>
                <li>301 请求的网页已永久移动到新位置</li>
                <li>302 请求的网页暂时移动到新位置</li>
                <li>400 （错误请求） 服务器不理解请求的语法</li>
                <li>401 未授权</li>
                <li>402 要求付费</li>
                <li>403 （禁止） 服务器拒绝请求</li>
                <li>404 请求的网页不存在</li>
                <li>405 不允许的方法</li>
                <li>406 不被采纳</li>
                <li>407 要求代理授权</li>
                <li>408 链接超时</li>
                <li>409 冲突</li>
                <li>410 过期的</li>
                <li>411 要求的长度</li>
                <li>412 前提不成立</li>
                <li>413 请求实例太大</li>
                <li>414 请求URI太大</li>
                <li>415 不支持的媒体类型</li>
                <li>416 无法满足的请求范围</li>
                <li>417 失败的预期</li>
                <li>500 （服务器内部错误） 服务器遇到错误，无法完成请求</li>
                <li>501 未被使用</li>
                <li>502 网关错误</li>
                <li>503 网络不可用</li>
                <li>504 网关超时</li>
                <li>505 HTTP版本未被支持</li>
                <li>521 不接受邮件</li>
            </ul>
        </div>
        <i class="angle3"></i>
    </div>
    <div class="ms-icon-wen">
        <i class="i-wen">?</i>
    </div>
</div>
<script type="text/javascript">
var httpHtml=$(".http-ms-msg").html();
//console.log($(".http-html").length+"====length");
if($(".http-html").length>0){
	httpHtmlF();
}
function httpHtmlF(){
	//console.log(11);
	$(".http-html").html(httpHtml);
	$(".ms-icon-wen").hover(function(){
		$(this).siblings(".ms-wen-con").fadeIn();
	},function(){
		$(this).siblings(".ms-wen-con").fadeOut();
	});
}
</script>
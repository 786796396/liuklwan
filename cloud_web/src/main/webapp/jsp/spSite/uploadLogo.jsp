<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>云监测 管理-个性化设置</title>

<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
<link rel="stylesheet" href="<%=path%>/css/control_info-setting-t.css"/>
</head>
<body class="control_info-config">
	<%@ include file="/common/top.jsp"%>

<div class="main-container container">
	<div class="row-fluid">
		 
	<%@ include file="/jsp/manageInfo/leftmenu-manageInfo.jsp"%>

	<div class="page-content" style="padding-top: 34px;">
            <!--专属页面配置头部开始-->
            <div class="info-setting_top">
                <div class="two_part clearfix">
                    <div class="Station-group fl on change_tab">
                        <span>个性化信息设置</span>
                        <i class="left"></i>
                    </div>
                    <div class="Station-single fl change_tab">
                        <span>文章发布</span>
                        <i class="right"></i>
                    </div>
                </div>
                <div class="line"></div>
            </div>
            <!--专属页面配置头部结束-->
            <!--专属配置内容部分开始-->
            <div class="info-setting_content">
                <div class="group change_tab_content on">
                <form id="formUpload" action="" method="post" enctype="multipart/form-data">
									<input type="hidden" id="siteCode" value="${spSite.siteCode}">
                    <div class="receive_y_or_n clearfix every_part">
                        <div class="receive_y_or_n-left fl every_title cic-sitecode">网站标识码：</div>   <!--cic:control_info-config-->
                        <div class="receive_y_or_n-right fl cic-sitecode">
                          ${spSite.siteCode}
                        </div>
                    </div>
                    <div class="receive_Members  clearfix every_part">
                        <div class="receive_Members_left fl every_title">登录URL地址：</div>
                        <div class="receive_Members_right fl">
                            <p class="instructions fl cic-url">${spSite.domain }</p> <!--cic:control_info-config-->
                            <c:if test="${spSite.domain != null }">
                            <div class="common-btn copy_btn fl" id="copyUrl">复制URL</div>
                            </c:if>
                        </div>
                    </div>
                    <div class="setting_infor_way clearfix every_part">
                        <div class="setting_infor_way_left fl every_title">LOGO：</div>
                        <div class="setting_infor_way_right fl">
                            <div class="common-btn AddLogo_btn fl" id="addLogo">添加LOGO</div>
                            <p class="logo-size fl">最大高度100px；格式为jpg,png,最好为透明背景;大小:1MB以内<br/></p>
                        </div>
                       <div id="uploadLogo"></div>
                    </div>
                    <div class="AddLogo_area">
                    	 <div id="logoDiv">													
							<img  src="${spSite.logo }"  width="310px" height="60px"/>
						</div>
							<img  src="" id="splogo" width="150px" height="50px"/>													
							<input type="hidden" id="logo" name="logo" value="${spSite.logo }">
                    </div>
                    <div class="erweima_part clearfix every_part">
                        <div class="erweima_part_left fl every_title">版权信息：</div>
                        <div class="erweima_part_right fl version_info">                            
      						<textarea naem="bottomText" id="bottomText" class="area">${spSite.bottomText }</textarea>                            
                        </div>
                    </div>
                    <div class="receive_y_or_n-two clearfix every_part">
                        <div class="receive_y_or_n-left fl every_title">显示大数据：</div>
                        <div class="receive_y_or_n-right fl">
	                        <c:if test="${spSite.isBigData==0 }">
	                            <p style="display: inline-block; margin-right: 50px;"> <label><input type="radio" id="isBigData" name="isBigData" value="0"  checked/>不显示</label></p>
	                            <p style="display: inline-block;"> <label><input type="radio" id="isBigData" name="isBigData" value="1" />显示</label></p>							</c:if>
							<c:if test="${spSite.isBigData==1 }">								
	                            <p style="display: inline-block; margin-right: 50px;"> <label><input type="radio" id="isBigData" name="isBigData" value="0"  />不显示</label></p>
	                            <p style="display: inline-block;"> <label><input type="radio" id="isBigData" name="isBigData" value="1" checked/>显示</label></p>
							</c:if>
                        </div>
                    </div>
                    <div class="insure_part clearfix every_part">
                        <div class="insure_part_left fl every_title"></div>
                        <div class="insure_part_right fl preview_btn" >
                            <input type="button" id="submitBaseModify_id" value="预览" onclick="toBaoSong();" />
                        </div>
                        <div class="insure_part_right fl ensure_btn" >
							<input type="button" id="submitSpSite"  value="确定"  onclick="toSpSite();"/>
                        </div>
                        <div class="insure_part_right fl reset_btn">
                            重置
                        </div>

                    </div>
                </div>
                <!-- 文章发布 -->
                <div class="single change_tab_content">
                    <div style="width: 900px; height: 500px; border: 1px solid #ddd; margin:30px;"></div>
                    <div class="insure_part clearfix every_part">
                        <div class="insure_part_left fl every_title"></div>
                        <div class="insure_part_right fl preview_btn">
                            <input type="button" id="submitBaseModify_id" value="预览" onclick="toBaoSong();" />
                        </div>
                        <div class="insure_part_right fl ensure_btn">
							<input type="button" id="submitSpSite"  value="确定"  onclick="toSpSite();"/>
                        </div>
                        <div class="insure_part_right fl reset_btn">
                            重置
                        </div>

                    </div>
                </div>
            </div>
            <!--消息设置内容部分结束-->
            <div class="row page-footer">© Copyright 2015. Ucap Info  All Rights Reserve</div><!-- /page-footer -->
        </div>	
	</div>
</div>
	
	<script type="text/javascript" src="<%=path%>/js/spSite/spSite.js"></script>

<script language="javascript" type="text/javascript">
$(function(){

    $('.change_tab').click(function(){
        $('.change_tab').removeClass('on');
        $(this).addClass('on');
        var n=$(this).index();
        $('.change_tab_content').hide();
        $('.change_tab_content').eq(n).show();
    });

}) 
</script>
</body>
</html>

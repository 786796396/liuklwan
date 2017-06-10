<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
<title>组织单位-信息管理</title>
<style>
	.sm_control .insure_part{margin-bottom:15px;}
	.sm_control .un_op .insure_part_right{ background:#ccc;}
	.look_xq{ margin-left:30px;}
	.look_xq a{display:inline-block; padding:10px 20px; border:1px solid #ddd;}
	.sm_control .single .insure_part_right{ margin-top:0px;}
	.sm_control.info-setting_content .every_part .every_title{ width:158px;}
	.sm_control .alreay_sm{background:#e0e7eb; color:#a1a4bb; cursor:none;}
</style>
<!-- Le styles -->
<link rel="stylesheet" href="<%=path%>/css/control_info-setting-t.css"/>
<script language="javascript" type="text/javascript" src="<%=path%>/js/securityStatus/securityStatus.js"></script>
</head>
<body>
<input type="hidden" id="siteCode" value="${siteCode }">
<input type="hidden" id="scanType" value="1">
<input type="hidden" id="basePath" value="${basePath} ">


<div class="page-wrap bg_fff">
<%@ include file="/common/top_tb.jsp"%>
<div class="main-container container">
	<div class="row-fluid">
	<c:set var="tb_index" value="22" scope="request" />
	<%@ include file="/common/leftmenu_tb.jsp"%>
        <div class="page-content" style="padding-top: 34px;">
            <!--消息设置头部开始-->
            <div class="info-setting_top">
                <div class="two_part clearfix">
                    <div class="Station-group fl on change_tab"  value="1">
                        <span>安全扫描</span>
                        <i class="left"></i>
                    </div>
                    <div class="Station-single fl change_tab" id="tab2" value="2">
                        <span>全站死链扫描</span>
                        <i class="right"></i>
                    </div>
                </div>
                <div class="line"></div>
            </div>
            <!--消息设置头部结束-->
            <!--消息设置内容部分开始-->
            <div class="info-setting_content sm_control">
                <div class="group change_tab_content on">
                     <div class="Index_items  clearfix every_part">
                        <div class="Index_items_left fl every_title">安全扫描指标项：</div>
                        <div class="Index_items_right fl">
                            <p> <label> 网站安全漏洞web扫描</label></p>
                            <p> <label>  网页挂马web扫描 </label></p>
                            <p> <label>全站敏感词扫描</label></p>
                            <p> <label> 全站盗链、黑链扫描</label></p>
                        </div>
                    </div>
                    
                     <div class="insure_part clearfix every_part" style="display:none" id="button1">
                        <div class="insure_part_left fl every_title"></div>
                        <div class="insure_part_right fl" onclick="updateStatus()">
                            		启动
                        </div>
                    </div>
                    <div class="insure_part clearfix every_part un_op" style="display:none" id="button2">
                        <div class="insure_part_left fl every_title"></div>
                        <div class="insure_part_right fl" >
                            		启动
                        </div>
                    </div>
                     <div class="insure_part clearfix every_part" style="display:none" id="button3">
                        <div class="insure_part_left fl every_title"></div>
                        <div class="insure_part_right fl" >
                            		扫描中...
                        </div>
                    </div>
                     <div class="insure_part clearfix every_part" style="display:none" id="button4">
                        <div class="insure_part_left fl every_title"></div>
                        <div class="insure_part_right fl alreay_sm" >
                            		扫描完成
                        </div>
                        <div class="fl look_xq">
                        	<a target="_blank" href="${basePath}/securityQuestion_index.action">查看详情</a>
                        </div>
                        
                    </div>
                    
                </div>
           <div class="single change_tab_content">
                    <div class="Index_items  clearfix every_part">
                        <div class="Index_items_left fl every_title">全站死链指标项：</div>
                        <div class="Index_items_right fl">
                            <p> <label> 考察网站一个检测周期（如一个月或一个季度）内全站页面上的链接（包括图片、附件、外部链接等）能否正常访问（打不开或错误）</label></p>
                            <p> <label> 统计不能正常访问的链接数量 </label></p>
                        </div>
                    </div>

                     <div class="insure_part clearfix every_part" style="display:none" id="sbutton1">
                        <div class="insure_part_left fl every_title"></div>
                        <div class="insure_part_right fl" onclick="updateStatus()">
                            		启动
                        </div>
                    </div>
                    <div class="insure_part clearfix every_part un_op" style="display:none" id="sbutton2">
                        <div class="insure_part_left fl every_title"></div>
                        <div class="insure_part_right fl" >
                            		启动
                        </div>
                    </div>
                     <div class="insure_part clearfix every_part" style="display:none" id="sbutton3">
                        <div class="insure_part_left fl every_title"></div>
                        <div class="insure_part_right fl" >
                            		扫描中...
                        </div>
                    </div>
                     <div class="insure_part clearfix every_part" style="display:none" id="sbutton4">
                        <div class="insure_part_left fl every_title"></div>
                        <div class="insure_part_right fl alreay_sm" >
                            		扫描完成
                        </div>
                        <div class="fl look_xq">
                        	<a target="_blank" href="${basePath}/linkAll_linkAllDetailIndex.action">查看详情</a>
                        </div>
                    </div>
                    
                    
                    
                    
                </div>
            </div>
            <!--消息设置内容部分结束-->
             <%@ include file="/common/footer.jsp"%>	
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->
</div>

</body>
</html>

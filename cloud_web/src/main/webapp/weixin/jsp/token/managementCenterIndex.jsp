<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<title>管理中心</title>
	 <%@ include file="/weixin/jsp/common.jsp"%>
  </head>
<body style="background:#fbfbfb;">
<nav>
    <ul class="nav nav-pills nav-justified">
      <li class="active"><a data-toggle="tab" href="#guanl">管理信息</a></li>
      <li><a data-toggle="tab" href="#jies">接收预警</a></li>
    </ul>
</nav>
<div class="manage tab-content">
	<input type="hidden" id="siteCode_id" value="${resultMap.siteCode}"/>
	<input type="hidden" id="openId_id" value="${resultMap.openId}"/>
    <div class="tab-pane active" id="guanl">
    	<h3 class="tit-h3-2">基本信息</h3>
        <div class="table-box1">
            <table cellpadding="0" cellspacing="0">
                <tbody id="tbody_base_info">
                	<!-- 基本信息数据 -->
    			</tbody>
            </table>
        </div>
<!--         <h3 class="tit-h3-2 bor-none">服务信息</h3>
		<div id="order_msg_id">
			每个订单的统计信息 
		</div> -->
		
        
<!--         <h3 class="tit-h3" id="focus_title_id">谁还绑定了</h3>
        <div class="container-fluid mar-bom1" id="focus_id"> 

        </div>-->
    </div><!--**********************/zuz************************-->
    
    <div class="tab-pane" id="jies">
    	<h3 class="tit-h3-2" id="siteList_title_id"></h3>
        <div class="container-fluid mar-bom2" id="container_org_siteList_id">
            <ul>
<!--                 <li>
                    <span class="con-tit col-xs-8 col-sm-6 col-md-6">站群报告通知</span>
                    <div class="col-xs-4 col-sm-6 col-md-6">
                        <div class="con-cbox">
                            <input class="tgl tgl-light" id="cb1" type="checkbox" checked="checked" value="1">
                            <label class="tgl-btn" for="cb1"></label>
                        </div>
                    </div>
                </li> -->
                <li>
                    <span class="con-tit col-xs-8 col-sm-6 col-md-6">接收&nbsp;&nbsp;站群预警通知</span>
                    <div class="col-xs-4 col-sm-6 col-md-6">
                        <div class="con-cbox">
                            <input class="tgl tgl-light" id="cb2" type="checkbox">
                            <label class="tgl-btn" for="cb2"></label>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
        <h3 class="tit-h3-2" id="local_title_id"></h3>
        <div class="container-fluid" id="container_org_local_id">
            <ul>
<!--                 <li>
                    <span class="con-tit col-xs-8 col-sm-6 col-md-6">本级门户报告通知</span>
                    <div class="col-xs-4 col-sm-6 col-md-6">
                        <div class="con-cbox">
                            <input class="tgl tgl-light" id="cb3" type="checkbox">
                            <label class="tgl-btn" for="cb3"></label>
                        </div>
                    </div>
                </li> -->
                <li>
                    <span class="con-tit col-xs-8 col-sm-6 col-md-6">接收&nbsp;&nbsp;本站预警通知</span>
                    <div class="col-xs-4 col-sm-6 col-md-6">
                        <div class="con-cbox">
                            <input class="tgl tgl-light" id="cb4" type="checkbox" checked="checked">
                            <label class="tgl-btn" for="cb4"></label>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
        
        <h3 class="tit-h3-2" id="tb_title_id">接收设置</h3>
        <div class="container-fluid" id="container_tb_id">
            <ul>
<!--                 <li>
                    <span class="con-tit col-xs-8 col-sm-6 col-md-6">报告通知</span>
                    <div class="col-xs-4 col-sm-6 col-md-6">
                        <div class="con-cbox">
                            <input class="tgl tgl-light" id="cb5" type="checkbox">
                            <label class="tgl-btn" for="cb5"></label>
                        </div>
                    </div>
                </li> -->
                <li>
                    <span class="con-tit col-xs-8 col-sm-6 col-md-6">接收&nbsp;&nbsp;本站预警通知</span>
                    <div class="col-xs-4 col-sm-6 col-md-6">
                        <div class="con-cbox">
                            <input class="tgl tgl-light" id="cb6" type="checkbox" checked="checked">
                            <label class="tgl-btn" for="cb6"></label>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div><!--**********************/tianb************************-->
</div>
<%@ include file="/weixin/jsp/footer.jsp"%>
<!-- 引入管理中的js代码 -->
<script type="text/javascript" language="javascript" src="<%=path%>/weixin/js/managementCenter/managementCenterIndex.js"></script>
</body>
</html>

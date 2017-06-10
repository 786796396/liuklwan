<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<title>管理中心</title>
	 <%@ include file="/newWeiXin/jsp/common.jsp"%>
  </head>
<body style="background:#fbfbfb;">
<nav>
    <ul class="nav nav-pills nav-justified">
      <li class="active"><a data-toggle="tab" href="#guanl">管理信息</a></li>
      <li><a data-toggle="tab" href="#jies">接收预警</a></li>
    </ul>
</nav>
<div class="manage tab-content">
	<input type="hidden" id="siteCode_id" value="${initMap.siteCode}"/>
	<input type="hidden" id="openId_id" value="${initMap.openId}"/>
    <div class="tab-pane active" id="guanl">
    	<h3 class="tit-h3-2">基本信息</h3>
        <div class="table-box1">
            <table cellpadding="0" cellspacing="0">
                <tbody id="tbody_base_info">
                	<!-- 基本信息数据 -->
    			</tbody>
            </table>
        </div>
    </div>
    
    <div class="tab-pane" id="jies">
    	<p style="padding-left: 20px;padding-right: 20px;font-size: 12px;color: #666666;font-family: '微软雅黑';line-height: 31px;margin-top: 10px;">此处修改只对您本人微信有效，不会对该标识码的其他绑定用户产生影响。</p>
    	<h3 class="tit-h3-2" id="siteList_title_id"></h3>
        <div class="container-fluid mar-bom2" id="container_org_siteList_id">
            <ul>
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
        <h3 class="tit-h3-2" id="local_title_id"><i class='i-point bg-3ca8ef'></i>本级门户网站</h3>
        <div class="container-fluid" id="container_org_local_id">
            <ul>
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
    </div>
</div>
<%@ include file="/newWeiXin/jsp/footer.jsp"%>
<!-- 引入管理中的js代码 -->
<script type="text/javascript" language="javascript" src="<%=path%>/newWeiXin/js/managementCenter/managementCenterIndex.js"></script>
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<% 
String path = request.getContextPath();
String siteCodeParam = request.getParameter("siteCode")==null?"":request.getParameter("siteCode");
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<!DOCTYPE html>
<html>
  <head>
    <title id="name">网站云监管</title>
	<%@ include file="/common/meta.jsp"%>
    <link rel="stylesheet"  type="text/css" href="<%=path%>/css/customMade/base.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/customMade/customMade.css" />
    <link rel="stylesheet"  type="text/css" href="<%=path%>/css/jquery.mCustomScrollbar.css" />
    <script type="text/javascript" src="<%= path %>/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="<%= path %>/js/customMade/CustomMade.js"></script>  
    <script type="text/javascript" src="<%=path%>/js/jquery.mCustomScrollbar.js"></script><!--滚动条js-->
	<script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/echarts.js"></script>
	<script language="javascript"  src="<%=path%>/js/gauge.js"></script>
  </head>
<body>
<input id="siteId" type="hidden" >
<input id="loginConfigId" type="hidden">
<div class="container">
<div class="page-head">
	 <div class="head-content clearfix">
	        <table class="tit-part fl">
	        	<tr>
	        		<td>
	            		<img src="" alt="" id="logo" style="display:table-cell; vertical-align: middle;"/>
	               </td>
	            </tr>
	        </table>
	 </div>
</div>
<div class="main-content clearfix">
    <div class="show-data_part fl">
       <div class="doubel_block clearfix">
           <div class="fl jkzs item z_box1">
           		<h4>网站健康指数</h4>
				<div class="z_box_header">
							<div class="star-msg">
							</div>
							<i class="zhi-s-i" style="display: none;"></i> <i id="sum_style"
								class="zhis-i" style="display: none;"></i>
				</div>

				<div class="chart-box">
					<div class="chart-body">
						<canvas width="235" height="210" id="chart-gauge"></canvas>
						<div class="chart-info">
							<div class="num1" id="health_index">2105.92</div>
							<div class="num1_msg"></div>
						</div>
					</div>
				</div>

				<div class="num2" id="index_ico">
					<span class="font1">相比昨天</span><i></i><span id="totalSumNumber">0&nbsp;&nbsp;(0%)</span>
				</div>
				              
           </div>
           <div class="fl tzgg">
               <h4><span id="noticeName"></span></h4>
               <ul id="uLId">
               </ul>
           </div>
       </div>
       <div class="single_block area-map margt-12 clearfix" id="hiddenMap">
           <i class="top-line"></i>
           <div class="fl item">
               <h4 class="cm_bigTit">${name}网站概况数据</h4>
               <div class="cm_echarts_map" id="main" style="height:340px;"></div> 
           </div>
           <div class="fl">
               <div class="table-list" style="position: relative; visibility: visible;">
                    <ul class="th-box clearfix padd-ul">
                        <li class="fl number">序号</li>
                        <li class="fl area">区域</li>
                        <li class="fl total">监测网站数量</li>
                    </ul>
                    <div class="content scroll-box">
                        <ul class="td-box" id="monitoringSiteId">
                        </ul>
                    </div>
               </div>
           </div>
       </div>
        <div class="single_block broken-line margt-12" id="colorPiece">
            <i class="top-line"></i>
            <div class="cm_change-tab">
                <span class="link_p active"><i></i><a onclick="connectedRate()" href="javascript:void(0)"
						data-toggle="tab">连通率</a></span>
                <span class="use_a"><i></i><a onclick="deadRate()" href="javascript:void(0)"
						data-toggle="tab">可用性</a></span>
                <span class="updata_t"><i></i><a onclick="datesiteRate()" href="javascript:void(0)"
						data-toggle="tab">更新量</a></span>
            </div> <!--cm:CustomMade-->
            <h4 class="cm_bigTit">${name}网站日常监测数据</h4>
            <div class="cm_echarts_lines">
                <div class="tab-content" id="tab-content">	
              	  <div id="ltxContent" style="height: 260px; width: 800px; display: none;"
								class="chart-content chart-content-bzwt"  ></div>
              	  <div id="kyxContent" style="height: 260px; width: 800px;display: none;"
								class="chart-content chart-content-bzwt"></div>
              	  <div id="gxContent" style="height: 260px; width: 800px;display: none;"
								class="chart-content chart-content-bzwt"></div>
           	 	</div>
            </div>
        </div>
        <div class="news-list margt-34 clearfix" id="articleId">
        	
        </div>
    </div>
    <div class="retrieve_part fr">
        <div class="login">
        <div class="login-det">
    <div class="change-org clearfix" id="myTab">
      <div class="fl active"><a data-toggle="tab" href="#home" onclick="formSubmitOrg();return false;" aria-expanded="true">组织单位</a> </div>
      <div class="fl"><a class=" text-right" data-toggle="tab" href="#tianb" onclick="formSubmit();return false" aria-expanded="false">填报单位</a></div>
      <i class="line_bottom-l" id="lineBottom"></i>
    </div>
      <div class="change-box" id="myTabContent">
       </div>
        
       </div>
        </div>
        <div class="notification margt-12">
            <i class="top-line"></i>
            <div class="every-tip item">
                <p class="tit">
                    <i class="notice_icon"></i>
                    <a target="_blank" href="http://www.gov.cn/zhengce/content/2015-12/15/content_10421.htm">
                    	 国务院办公厅关于第一次全国政府网站普查情况的通报
                    </a>
                </p>
                <p class="article_source">国办函〔2015〕144号</p>
            </div>
            <div class="every-tip item">
                <p class="tit">
                    <i class="notice_icon"></i>
                    <a target="_blank" href="http://www.gov.cn/zhengce/content/2016-07/25/content_5094407.htm">
                    	  国务院办公厅关于2016年第二次全国政府网站抽查情况的通报
                    </a>
                </p>
                <p class="article_source">国办函〔2015〕68号</p>
            </div>
             <div class="every-tip">
                <p class="tit">
                    <i class="notice_icon"></i>
                    <a target="_blank" href="http://www.gov.cn/zhengce/content/2015-03/24/content_9552.htm">
                    	 国务院办公厅关于开展第一次全国政府网站普查的通知
                    </a>
                </p>
                <p class="article_source">国办发〔2015〕15号</p>
            </div>
            <!-- <div class="every-tip">
                <p class="tit">
                    <i class="notice_icon"></i>
                    2016年第三次全国政府网站抽查情况的通报
                </p>
                <p class="article_source"></p>
            </div> -->
        </div>
        <div class="bg_block margt-12">
            <a target="_blank" href="http://121.43.68.40/boxpro/custom/pucha">
	            <div class="every-bg Manage-system">
	                <i></i>
	                <div class="tip">网站填报系统</div>
	            </div>
            </a>
            <a target="_blank" href="http://114.55.181.28/errorInfo/search">
	            <div class="every-bg baogt">
	                <i></i>
	                <div class="tip">网站纠错系统</div>
	            </div>
            </a>
            <a target="_blank" href="http://114.55.181.28/databaseInfo/index">
	            <div class="every-bg Manage-email">
	                <i></i>
	                <div class="tip">网站基本信息数据库</div>
	            </div>
            </a>
        </div>
        <div class="wcat">
            <i class="top-line"></i>
            <h5>网站管理微信公众号</h5>
            <div>
                <img src="<%= path %>/images/customMade/wexin-img.jpg" alt=""/>
            </div>
        </div>
    </div>
</div>
<div class="page-footer">
   <p><span id="bottomText">
   		版权所有：开普互联提供网站技术支持
   </span></p>
</div>
<script type="text/javascript">
var webPath = "<%=path%>"; 
</script>
<script type="text/javascript" src="<%= path %>/js/icheck/icheck.js"></script>
<script type="text/javascript" src="<%= path %>/js/spChannel/spChannel.js"></script>
<script type="text/javascript" src="<%= path %>/js/placeholder.js"></script>
<script type="text/javascript" src="<%= path %>/js/spChannel/login.js"></script>
</body>
</html>

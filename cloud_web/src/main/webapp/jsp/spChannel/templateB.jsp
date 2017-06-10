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
    <title>${name}网站云监管</title>
	<%@ include file="/common/meta.jsp"%>
    
    
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/base.css"/>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/customMade-new.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=path%>/css/jquery.mCustomScrollbar.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/jquery.dataTables_themeroller.css" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap/bootstrap.css"/>
    <script type="text/javascript" src="<%= path %>/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/jquery-ui-1.10.4.min.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/bootstrap/bootstrap.js" ></script>
    <script type="text/javascript" src="<%= path %>/js/jquery.dataTables.js"></script>
    <script type="text/javascript" src="<%= path %>/js/datepicker/jquery-ui.js"></script>
    <script type="text/javascript" src="<%= path %>/js/fnReloadAjax_lw.js"></script>
    <script type="text/javascript" src="<%= path %>/js/customMade/CustomMade.js"></script>
    <script type="text/javascript" src="<%=path%>/js/jquery.mCustomScrollbar.js"></script><!--滚动条js--> 
	<script language="javascript" type="text/javascript" src="<%= request.getContextPath() %>/js/echarts.js"></script>
	<script language="javascript"  src="<%=path%>/js/gauge.js"></script> 
	<script type="text/javascript" src="<%= path %>/js/icheck/icheck.js"></script>
	<script type="text/javascript" src="<%= path %>/js/placeholder.js"></script>
   <script language="javascript" type="text/javascript" src="<%=path%>/js/detection.js"></script>

  </head>
<body>
<input id="templateId" type="hidden" value="${templateType}">
<input id="siteCodeId" type="hidden" value="${siteCode}">
<input id="loginConfigId" type="hidden" value="${loginConfig}">
<input id="displayModule" type="hidden" value="${displayModule}">
<div class="container-m">
    <!--数据展示&用户登录-->
    <div class="dateShow-login clearfix">
        <div class="dateShow-login-content">
            <div class="dateShow fl">
                <h4>${name}政府网站整体健康指数</h4>
                <div class="top-txt">
                    <p class="colo-ff1f1f clearfix" id="textcolor">
                        <em class="fl" id="health_index"></em>
                        <i id="index_ico" class="up-ico-red fl"></i>
                        <span class="fl" id="totalSumNumber"></span>
                    </p>
                </div>
                <div class="bottom-txt">
                    <div class="everyl clearfix">
                        <p class="fl">
                            全国政府网站
                            <span id="infoCountId"></span>
                        </p>
                    </div>
                   <div class="everyl clearfix">
                       <p class="fl">
                           监测页面
                           <span id="pageNumId">464,128,514</span>
                       </p>
                       <p class="fl">
                           发现问题
                           <span class="colo-f7d90d" id="errorNumId">3,678,643</span>
                       </p>
                   </div>
                </div>
            </div>
            <div class="login fr">
                <i class="login-top-line"></i>
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
        </div>
    </div>
    <div class="search-box" id="resultsTopId">
        <h4>${name}政府网站基础数据查询</h4>
        <div class="xinxikf_search clearfix">
            <div id="" class="search radius6 fl">
                  <div class="fl select-box">
                	 <div class="select_showbox"  style="cursor: pointer;"><span id="aaaTypeText">按网站名称</span></div>
	                <input id="aaaTypeVal" type="hidden" value="1">  
	                <!--移入显示块开始-->
	                <ul id="aaaTypeUl" style="display: none;" class="aaaType">
	                	<li value="1" name="monitorType">按网站名称</li>
	                    <li value="0" name="monitorType">按网址查找</li>
	                    <li value="2" name="monitorType">按网站首页网址</li>
	                    <li value="3" name="monitorType">按网站主管单位</li>
                	</ul>
                </div>  
                <input class="inp_srh" id="queryInput" name="keyboard">
            </div>
            <input onclick="infoListAjax()" class="btn_srh fr" type="button" id="subQuery" name="subQuery" value="搜索一下">
        </div>
    </div>
    <div class="main-content clearfix">
            <div class="searchResultPart single_block search_ico" id="resultsOfId">
                <div class="xinxikf_container" style="">
                    <div class="xinxikf_tab1">
                        <div id="table_data_wrapper" class="dataTables_wrapper no-footer"><div class="top"><div class="clear"></div></div>
                        <table id="databaseInfoId" cellpadding="0" cellspacing="0" class="dataTable no-footer" role="grid" aria-describedby="table_data_info" >
                          </table>
                    </div>
                </div>
                 <div id="databaseInfoNumId" class="num_more_msg" style="display:none">由于检索结果过多，只显示前100条数据。</div>
            </div>
                <div id="searchId" class="info_null_msg" style="display:block;">
                    <div class="info_null_msg_con">
                        <p>1. 支持多关键词模糊搜索，关键词间以“空格”键分割，例如：输入“教育 资源”，可检索到所有同时含有“教育”和“资源”关键词 的网站；</p>
                        <p>2. 如您使用名称搜索但无结果，建议尝试其他简称、全称或别称；</p>
                        <p>3. 如您使用网址搜索但无结果，建议减少网址字段重新搜索<br>&nbsp;( 如您关注的网站网址为“http://site.wzpc.gov.cn/2015/dongtai.htm”,建议您输入“site.wzpc.gov.cn”或“wzpc.gov.cn”)</p>
                    </div>
                </div>
            </div>


			<div class="single_block" id="colorPiece">
				<h5>
					<i class="little-color-block"></i> ${name}政府网站日常监测数据
				</h5>
				<div class="hasBorderPart">
					<div class="cm_change-tab">
						 <span class="link_p active"><i></i><a onclick="connectedRate()" href="javascript:void(0)"
								data-toggle="tab">连通率</a></span>
		                <span class="use_a"><i></i><a onclick="deadRate()" href="javascript:void(0)"
								data-toggle="tab">可用性</a></span>
		                <span class="updata_t"><i></i><a onclick="datesiteRate()" href="javascript:void(0)"
								data-toggle="tab">更新量</a></span>
					</div>
					<!--cm:CustomMade-->
					<div class="cm_echarts_lines">
						<div class="tab-content" id="tab-content">
							<div id="ltxContent" style="height: 260px; width: 1040px; display: none;"
								class="chart-content chart-content-bzwt"  ></div>
			              	<div id="kyxContent" style="height: 260px; width: 1040px;display: none;"
											class="chart-content chart-content-bzwt"></div>
			              	<div id="gxContent" style="height: 260px; width: 1040px;display: none;"
											class="chart-content chart-content-bzwt"></div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="single_block area-map clearfix" id="hiddenMap">
               <h5>
                   <i class="little-color-block"></i>
                   ${name}政府网站概况数据
               </h5>
               <div class="hasBorderPart clearfix">
                   <div class="fl item">
                        <div class="cm_echarts_map" id="main" style="height:360px;"></div> 
                   </div>
                   <div class="fl" style="width: 620px;">
                        <div class="table-list" style="position: relative; visibility: visible;">
                         <ul class="th-box clearfix padd-ul">
                            <li class="fl number">序号</li>
                            <li class="fl area">区域</li>
                            <li class="fl total">正常网站数量</li>
                            <li class="fl total">关停网站数量</li>
                            <li class="fl total">例外网站数量</li>
                        </ul> 
                        
                        <div class="content scroll-box">
                             <ul class="td-box" id="monitoringSiteId">
                        	</ul>
                        </div>
                   </div>
                   </div>
               </div>
           </div>
           
           
           <div class="single_block clearfix" id="correntId">
            <h5>
                <i class="little-color-block"></i>
                ${name}政府网站网民找错数据
            </h5>
            <div class="hasBorderPart clearfix">
                <div class="conditions-box clearfix">
                    <div class="fl">
                        <span class="little-tit fl">搜索：</span>
                        <div class="fl input-box">
                            <input id="exposureKeyId" type="text" class="fl" placeholder="输入网站标识码/名称"/>
                    <span class="fl" onclick="errorInfoListAjax()">
                        <i></i>
                    </span>
                        </div>
                    </div>
                    <div class="fl">
                        <span class="little-tit little-tit-qus fl ">问题类型：</span>
                        <div class=" fl select-box">
	                         <span id="siteTypeText">全部</span>
                            <input id="siteTypeVal" type="hidden" value="-1">
                            <i></i>
                            <!--移入显示块开始-->
                            <ul id="siteTypeUl" style="display: none;">
                                <c:forEach items="${dicList}" var="dic">
									 <li value="${dic.value}" name="monitorType">${dic.name}</li>
								</c:forEach>
                            </ul>
                            <!--移入显示块结束-->
	                        
					</div>
                    </div>
                </div>
                <div class="lighthouse_part_tab">
                   <table id="errorInfoId" cellpadding="0" cellspacing="0" class="dataTable no-footer" role="grid" aria-describedby="table_data_info" >
                    </table>
                </div>
                <div id="errorInfoNumId" class="num_more_msg" style="display:none">由于检索结果过多，只显示前100条数据。</div>
            </div>
        </div>
    </div>
</div>
<!-- Modal s-->
<div id="errorInfoIds" class="custom-page-modal modal hide " tabindex="-1" role="dialog" aria-hidden="true" style="position: relative;">
    <div class="modal-header">
        <button aria-hidden="true" data-dismiss="modal" class="close" type="button"></button>
        <h3 id="myModalLabel"><i class="view"></i><p id="errName"></p></h3>
    </div>
    <div class="custom-page-content">
       <h3>
           <i></i>
           曝光信息
       </h3>

       <div class="ques-box">
           <h4>
               <i></i>
               问题1
           </h4>
           <div class="ques-content">
               <table class="tab" cellpadding="0" cellspacing="0">
                   <tbody>
                   <tr>
                       <td class="t_left">问题类型 ：</td>
                       <td class="t_right" id="problemId"></td>
                   </tr>
                   <tr>
                       <td class="t_left">问题页面网址 ：</td>
                       <td class="t_right" id="errorUrl"></td>
                   </tr>
                   <tr>
                       <td class="t_left" valign="top">问题描述 ：</td>
                       <td class="t_right" id="description"></td>
                   </tr>
                   <tr>
                       <td class="t_left">办理状态：</td>
                       <td class="t_right"><p id="status"></p>受理单位：<span id="transactUnit"></span><br>受理单位回复：<span id="transactResult"></span></td>
                   </tr>
                   </tbody>
               </table>

           </div>
       </div>
    </div>
</div>
	<script type="text/javascript">
		var webPath = "<%=path%>"; 
	</script>
	<script type="text/javascript" src="<%= path %>/js/spChannel/login.js"></script>
	<script type="text/javascript" src="<%=path%>/js/spChannel/templateB.js"></script>

</body>
</html>

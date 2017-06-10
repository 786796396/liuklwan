<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>云监测 管理-客户信息</title>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
<!--module-->
    <link rel="stylesheet"  type="text/css" href="<%= path %>/css/site-info-manage.css" /><!--站点信息-->
    <link rel="stylesheet"  type="text/css" href="<%= path %>/css/bread-crumbs.css" /><!--面包屑-->
    <link rel="stylesheet" href="<%= path %>/css/__yunjc2.css"/>
   
</head>
<body class="bg_f5f5f5">
<p id="backToTop" style="display:none;">
    <a title="回到顶部" href="javascript:void(0);">
        <img src="<%= path %>/images/common/top-hover.png"/>
        <img class="top-img" src="<%= path %>/images/common/top.png"/>
    </a>
</p>
<%@ include file="/common/top.jsp"%>
<div class="main-detail">
    <div class="main-detail-content">
    	<%@ include file="/common/leftmenu.jsp"%>
        <input id="typeId" value="${type}" type="hidden"/>
		<input type="hidden" id="siteCode" value="${databaseInfo.siteCode}">    
        <div class="page-content">
            <div class="bread-crumbs">
                <div class="bread-crumbs-content">
                    <span class="cor-0498e4">站点信息管理</span>
                    <i class="cor-0498e4">></i>
                    <span>本站信息</span>
                </div>
            </div>
            <div> 
                <div> 
                    <div class="modal-container">
                        <div class="modal-nav-box">
                            <ul class="nav nav-pills">
                                <li class="active">
                                    <a href="#basicInfo" data-toggle="tab">基本信息</a>
                                </li>
                                <li>
                                    <a href="#columnInfo" data-toggle="tab">栏目信息</a>
                                </li>
                            </ul>
                        </div>
                        <!--/modal-nav-->
                        <div class="tab-content">
                            <div id="basicInfo" class="modal-tab2  active">
                                <input type="hidden" id="siteCode" value="${databaseInfo.siteCode}"> 
                                <table class="basic-tab" cellpadding="0" cellspacing="0" style="margin-left:0;">
                                    <tr>
                                        <td class="tit-td" style="width:80px;">账号状态：</td>
                                        <td><i class="publi-ico_2 keyong-ico"></i>
											<c:if test="${databaseInfo.state==0}">可用</c:if> <c:if test="${databaseInfo.state==1}">不可用</c:if>
										</td>
										<td></td>
										<td></td>
                                    </tr>
                                    <tr>
                                        <td class="tit-td" style="width:135px;">网站标识码：</td>
                                        <td style="width:200px;"><a onclick="fillJumpTb()" target="_blank" class="wz-name wz-name-w2" href="<%=path%>/fillUnit_gailan.action?siteCode=${databaseInfo.siteCode}">${databaseInfo.siteCode}</a></td>

                                    </tr>
                                    <tr>
                                        <td class="tit-td"><span class="red">*</span>网站名称：</td>
                                        <td colspan="3"><input type="text" style="width:392px;" value="${databaseInfo.name}" name="databaseInfo.name" id="siteName_table" onblur="checkSiteName_table()" /></td>
                                    </tr>
                                    <tr>
                                        <td class="tit-td"><span class="red">*</span>主办单位：</td>
                                        <td colspan="3"><input type="text" style="width:392px;" id="siteManageUnit_table" onblur="checkSiteManageUnit_table()" value="${databaseInfo.director}" name="databaseInfo.director" /></td>
                                    </tr>
                                    <tr>
                                        <td class="tit-td"><span class="red">*</span>办公地址：</td>
                                        <td colspan="3"><input type="text" style="width:392px;" id="officeAddress_table" onblur="checkOfficeAddress_table()" value="${databaseInfo.address}" name="databaseInfo.address" /></td>
                                    </tr>
                                    <tr>
                                        <td class="tit-td" valign="top"><span class="red">*</span>首页URL：</td>
                                        <td colspan="3" class="url-td"><input class="failure-input" type="text" style="width:392px;" value="${databaseInfo.manageInfoUrl}" id="homeUrl_table" onblur="checkHomeUrl_table()" name="databaseInfo.url" />
                                            <div class="btn-07c1f2" id="home_url_conn_test" onclick="onLineCheckHomeUrl();">连通测试</div>
                                            <div class="column-loading-box" id="home_url_loading-box">
                                                <div id="home_loading_id">
                                                    <span>连通测试中，可能需要几十秒钟，请稍候......</span><span class="loading-num">75%</span>
                                                </div>
                                                <div class="column-loading">
                                                    <div class="column-loading-con"></div>
                                                </div>
                                            </div>
                                            <div class="info-tip2">(为保证地址准确，请先在浏览器中访问该地址，可正常浏览后复制到此处，如上述操作仍无法通过校验，可联系客服。)</div></td>
                                    </tr>
                                    <tr class="disabled-tr" id="jumpUrl_tr">
                                        <td class="tit-td" valign="top">
                                            <div class="tz-cbox" id="checkBox">
                                                <input type="checkbox" id="checkbox_tr" />
                                            </div>
                                            <div class="tz-tit">
                                                跳转URL：
                                                <div class="info-tip3">（监测入口）</div>
                                            </div></td>
                                        <td colspan="3" class="url-td"><input type="text" style="width:392px;" disabled="disabled" id="jumpUrl_table" value="${databaseInfo.jumpUrl}" name="databaseInfo.jumpUrl" />
                                            <div class="btn-07c1f2" id="jump_home_url_conn_test" onclick="onLineCheckHomeJumpUrl()">连通测试</div>
                                            <div class="column-loading-box" id="jump_url_loading-box">
                                                <div id="jump_loading_id">
                                                    <span>连通测试中，可能需要几十秒钟，请稍候......</span><span class="loading-num">1%</span>
                                                </div>
                                                <div class="column-loading">
                                                    <div class="column-loading-con"></div>
                                                </div>
                                            </div>
                                            <div class="info-tip2">(为保证地址准确，请先在浏览器中访问该地址，可正常浏览后复制到此处，如上述操作仍无法通过校验，可联系客服。)</div></td>
                                    </tr>
                                    <tr>
                                        <td></td><td><div id="submitBaseModify_id" class="base-info-save" value="保 存" onclick="submitBaseModify();" >保&nbsp;&nbsp;&nbsp;存</div>
                                    </tr>
                                </table>
                            </div>
                            <!--/basicInfo-->
                            <div id="columnInfo" class="modal-tab2">
                                <div class="column-group">
                                    <div class="tab_header row tab_header_white">
                                        <div class="tab_header_white_con">
                                            <h3>重点监测栏目/系统</h3>
                                            <div class="input-prepend input-prepend-black" style="border:1px solid #ccc;">
														<span class="add-on" style="top:0;">
                                                            <img alt="search"
														src="<%= path %>/images/common/search_black.png" /> </span>
													<input class="span2 prependedInput" id="keyId" type="text"
														placeholder="输入关键字..." >
                                            </div>
                                            <div class="btn-2dba62" onclick="channelExportExcel()" id="channelExportExcelId">导出列表</div>
                                            <div class="btn-2dba62" onclick="delChannelList()">删 除</div>
                                            <div class="btn-2dba62" onclick="addColumnModal()">添 加</div>
                                        </div>
                                        <div class="info-tip4">
                                            <span>*</span>免费用户可以免费监测5个栏目的更新情况，收费用户可以监测全部报送栏目；
                                        </div>
                                        <div class="info-tip4">
                                            <span>*</span>本版本暂不提供修改URL，如需修改请先删除后再添加。同时为了栏目监测的准确性，请确保添加栏目的url与文章url相匹配。
                                        </div>
                                    </div>
                                    <div class="columninfo-body">
                                        <div id="channelList">
                                            <div class="dropload-load">
                                                <span class="loading"></span>加载中...
                                            </div>
                                            <div id="table_data_channel_columnTab_wrapper" class="dataTables_wrapper no-footer">
                                            <table cellpadding="0" cellspacing="0" class="dataTable table no-footer">
                                            	 <thead>
									                   <tr>
									                   		<th class="w15p th-center sorting_disabled ui-state-default" style="width:5%;text-align: left; border-top:1px solid #abe0ef;"><input type='checkbox' id='checkChannalAllId'/></th>
									                   		<th class="w15p th-center sorting_disabled ui-state-default" style="width:10%;text-align: left;border-top:1px solid #abe0ef;">序号</th>
									                        <th class="w20p th-center sorting_disabled ui-state-default" style="width:15%;text-align: left;border-top:1px solid #abe0ef;">名称</th>
									                        <th class="w10p th-center sorting_disabled ui-state-default" style="width:15%;text-align: left;border-top:1px solid #abe0ef;">URL</th>
									                        <th class="w10p th-center sorting_disabled ui-state-default" style="width:10%;text-align: left;border-top:1px solid #abe0ef;">跳转URL</th>
									                        <th class="w10p th-center sorting_disabled ui-state-default" style="width:10%;text-align: left;border-top:1px solid #abe0ef;"><span title="栏目的最低更新频率">更新期限</span></th>
									                        <th class="w10p th-center sorting_disabled ui-state-default" style="width:10%;text-align: left;border-top:1px solid #abe0ef;">类别</th>
									                        <th class="w10p th-center sorting_disabled ui-state-default" style="width:10%;text-align: left;border-top:1px solid #abe0ef;">子类</th>
									                        <th class="w10p th-center sorting_disabled ui-state-default" style="width:5%;text-align: left;border-top:1px solid #abe0ef;">监测</th>
									                        <th class="w10p th-center sorting_disabled ui-state-default" style="width:10%;text-align: left;border-top:1px solid #abe0ef;">监测状态</th>
									                   </tr>
									                   
									                   <!--  <tr>
									                   		<th class=" "  style="width:5%;text-align: left;"><input type='checkbox' id='checkChannalAllId'/></th>
									                   		<th class=" " style="width:5%;text-align: left;">序号</th>
									                        <th class=" " style="width:15%;text-align: left;">名称</th>
									                        <th class=" " style="width:15%;text-align: left;">URL</th>
									                        <th class=" " style="width:10%;text-align: left;">跳转URL</th>
									                        <th class=" " style="width:10%;text-align: left;"><span title="栏目的最低更新频率">更新期限</span></th>
									                        <th class=" " style="width:10%;text-align: left;">类别</th>
									                        <th class=" " style="width:10%;text-align: left;">子类</th>
									                        <th class=" " style="width:10%;text-align: left;">监测</th>
									                        <th class=" " style="width:5%;text-align: left;">监测状态</th>
									                   </tr> -->
									               </thead>
                                                <tbody id="table_data_channel_columnTab"></tbody>
                                            </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--/tab-content-->
                    </div>
                </div>
            </div>
            <!--/khxx -->
             <!--底部    start-->
            <%@ include file="/common/footer.jsp"%>
            <!--底部    end-->
        </div>
    </div>
</div>	
	
 <%@ include file="modal.jsp"%>
	<script language="javascript" type="text/javascript" src="<%=path%>/js/manageInfo/department.js"></script>
	<!-- 校验工具类js -->
	<script language="javascript" type="text/javascript" src="<%=path%>/js/validateUtil.js"></script>
	<!-- /container -->
	<script type="text/javascript" src="<%=path%>/js/flexslider/jquery.flexslider-min.js"></script>
	
</body>
</html>
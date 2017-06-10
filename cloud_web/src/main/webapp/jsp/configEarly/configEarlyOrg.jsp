<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
<title>组织单位-信息管理</title>
<!-- Le styles -->
<link rel="stylesheet" href="<%=path%>/css/base.css"/>
<link rel="stylesheet" href="<%=path%>/css/control_info-setting-t.css"/>
</head>
<body>
<input type="hidden" id="siteCode" value="${databaseOrgInfo.siteCode }">
<input type="hidden" id="earlyType" value="1">
<style type="text/css">
    #startHourTT,#endHourTT,#startHourOO,#endHourOO{     
    border-radius: 0;
    width: 169px;
    padding: 0 0 0 10px;
    height: 28px;
    margin-bottom: 0px; 
    }
</style>
<div class="page-wrap bg_fff">
<%@ include file="/common/top.jsp"%>
<div class="main-container container">
	<div class="row-fluid">
	<c:set var="tb_index" value="2" scope="request" />
	<%@ include file="/common/leftmenu.jsp"%>
        <div class="page-content" style="padding-top: 115px;">
            <!--消息设置头部开始-->
            <div class="info-setting_top">
                <div class="two_part clearfix">
                    <div class="Station-group fl on change_tab"  value="1">
                        <span>站群监测日报消息</span>
                        <i class="left"></i>
                        <c:if test="${sessionScope.shiroUser.isOrgCost == 1}">
	                         <div class="yd-third-steps third-ld" id = "ydThirdSteps" style="display:none;">
			                  <i class="main"></i>
			                  <i class="nextstep" onclick="ydThirdSteps()"></i>
			                  <i class="yjyd-close" onclick="ydThirdClose()"></i>
	              			</div> 
                        </c:if>
                    </div>
                    <div class="Station-single fl change_tab" id="tab2" value="2">
                        <span>单站预警消息</span>
                        <i class="right"></i>
                    </div>
                </div>
                <div class="line"></div>
            </div>
            <!--消息设置头部结束-->
            <!--消息设置内容部分开始-->
            <div class="info-setting_content">
                <div class="group change_tab_content on">
                    <div class="receive_y_or_n clearfix every_part">
                    	<input id="errmsg_id" type="hidden" value="${errmsg}">
                        <div class="receive_y_or_n-left fl every_title">接收站群监测日报：</div>
                        <div class="receive_y_or_n-right fl">
                            <p> <label><input type="radio" name="check" value="1" <c:if test="${tabDataOne.isDailyReceive==1 }">checked</c:if> /> 接收</label></p>
                            <p> <label><input type="radio" name="check" value="2" <c:if test="${tabDataOne.isDailyReceive==2 }">checked</c:if>/> 不接收</label></p>
                            <p class="instructions">汇总网站前一日的几大指标项，每日发送<br><br></p>
                        </div>
                    </div>
                    <div class="receive_Members  clearfix every_part" id="hideDiv1">
                        <div class="receive_Members_left fl every_title">设置接收日报的成员：</div>
                        <div class="receive_Members_right fl">
                            <p> <label><input type="checkbox" name="isOrgPrincipal" <c:if test="${tabDataOne.isOrgPrincipal==1 }">checked</c:if>/> 网站负责人</label><span>（ ${databaseOrgInfo.principalName}&nbsp;&nbsp;${databaseOrgInfo.principalPhone }&nbsp;&nbsp;${databaseOrgInfo.principalEmail  }）</span><span style="position:absolute; right:-40px;"><a href="<%=path%>/manageInfo_index.action?top=8" style="color: black;text-decoration:underline;" >修改</a></span></p>
                            <p> <label><input type="checkbox" name="isOrgLinkman" <c:if test="${tabDataOne.isOrgLinkman==1 }">checked</c:if>/> 业务联系人</label><span>（ ${databaseOrgInfo.linkmanName }&nbsp;&nbsp;${databaseOrgInfo.linkmanPhone  }&nbsp;&nbsp;${databaseOrgInfo.linkmanEmail }）</span><span style="position:absolute; right:-40px;"><a href="<%=path%>/manageInfo_index.action?top=8" style="color: black;text-decoration:underline;">修改</a></span></p>
                            <p> <label><input type="checkbox" name="orgLinkmanTwo" <c:if test="${tabDataOne.orgLinkmanTwo==1 }">checked</c:if>/> 业务联系人</label><span>（ ${databaseOrgInfo.linkmanNametwo }&nbsp;&nbsp;${databaseOrgInfo.linkmanPhonetwo  }&nbsp;&nbsp;${databaseOrgInfo.linkmanEmailtwo }）</span><span style="position:absolute; right:-40px;"><a href="<%=path%>/manageInfo_index.action?top=8" style="color: black;text-decoration:underline;">修改</a></span></p>
                            <p> <label><input type="checkbox" name="orgLinkmanThree" <c:if test="${tabDataOne.orgLinkmanThree==1 }">checked</c:if>/> 业务联系人</label><span>（ ${databaseOrgInfo.linkmanNamethree }&nbsp;&nbsp;${databaseOrgInfo.linkmanPhonethree  }&nbsp;&nbsp;${databaseOrgInfo.linkmanEmailthree }）</span><span style="position:absolute; right:-40px;"><a href="<%=path%>/manageInfo_index.action?top=8" style="color: black;text-decoration:underline;" >修改</a></span></p>
                        </div>
                    </div>
                    <div class="setting_infor_way clearfix every_part">
                        <div class="setting_infor_way_left fl every_title">设置消息方式：</div>
                        <div class="setting_infor_way_right fl">
                            <p> <label><input type="checkbox" name="isWxReceive"  <c:if test="${tabDataOne.isWxReceive==1 }">checked</c:if>/>微信消息</label></p>
                            <p> <label><input type="checkbox" name="isEmailReceive" <c:if test="${tabDataOne.isEmailReceive==1 }">checked</c:if>/>邮件消息</label></p>
                            <p> <label><input type="checkbox" name="isTelReceive" <c:if test="${tabDataOne.isTelReceive==1 }">checked</c:if> />短信消息 
                            <input type="hidden" id="startHourO" value="${tabDataOne.startHour }">
							<input type="hidden" id="endHourO" value="${tabDataOne.endHour}">
								<input class="Wdate" type="text" id="startHourOO" onfocus="WdatePicker({dateFmt:'HH:mm:ss',maxDate:'#F{$dp.$D(\'endHourOO\',{H:-1})}'})"> 
                             	<span >至</span>
                        		<input class="Wdate" type="text" id="endHourOO" onClick="WdatePicker({dateFmt:'HH:mm:ss',minDate:'#F{$dp.$D(\'startHourOO\',{H:+1})}'})"> 
                            </label></p>
                            <p class="instructions">请扫描二维码关注“开普云监管”，绑定您的登录账号，即可接收微信预警：</p>
                        </div>
                    </div>
                    <div class="erweima_part clearfix every_part">
                        <div class="erweima_part_left fl every_title"></div>
                        <div class="erweima_part_right fl">
                            <img src="<%=path%>/images/info_setting_erweima_img.jpg" alt="二维码"/>
                        </div>
                    </div>
                    <div class="insure_part clearfix every_part">
                        <div class="insure_part_left fl every_title"></div>
                        <div class="insure_part_right fl" onclick="updateInfo()">
                            确定
                        </div>
                    </div>
                </div>
                             <div class="single change_tab_content">
                    <div class="Index_items  clearfix every_part">
                        <div class="Index_items_left fl every_title">单站预警的指标项：</div>
                        <div class="Index_items_right fl">
                            <p> <label><input type="checkbox" id="homeConnection"  <c:if test="${tabDataTwo.homeConnection==1 }">checked</c:if>/> 首页不连通实时预警</label></p>
                            <p> <label><input type="checkbox" id="homeConnectionPer" <c:if test="${tabDataTwo.homeConnectionPer==1 }">checked</c:if>/> 首页不连通率达到3% </label><span>（十五号文的规定首页不连通率达到5%即单项否决）</span> </p>
                            <p> <label><input type="checkbox" id="correctContent" <c:if test="${tabDataTwo.correctContent==1 }">checked</c:if>/> 网站上出现疑似错别字</label></p>
                            <p> <label><input type="checkbox" id="modifySite" <c:if test="${tabDataTwo.modifySite==1 }">checked</c:if>/> 网站疑似被挂码或被篡改</label></p>
                            <p> <label><input type="checkbox" id="notUpdateHome" <c:if test="${tabDataTwo.notUpdateHome==1 }">checked</c:if>/> 首页超过10天未更新</label><span>（十五号文规定首页超过两周未更新即单项否决）</span></p>
                            <p> <label><input type="checkbox" id="blankChannel" <c:if test="${tabDataTwo.blankChannel==1 }">checked</c:if>/> 空白栏目超过2个</label><span>（人工深度审核，十五号文的规定空白栏目数量超过5个即单项否决）</span></p>
                            <p> <label><input type="checkbox" id="notUpdateChannel" <c:if test="${tabDataTwo.notUpdateChannel==1 }">checked</c:if>/> 超过6个栏目不更新</label><span>（ 人工深度审核，十五号文的规定栏目不更新的数量超过10个即单项否决）</span></p>
                            <p> <label><input type="checkbox" id="securityResponse" <c:if test="${tabDataTwo.securityResponse==1 }">checked</c:if>/> 互动回应栏目超过3个月未回应</label><span>（人工深度审核， 十五号文的规定栏目互动回应栏目超过3个月不回应即单项否决）</span></p>
                        </div>
                    </div>
                    <div class="receive_y_or_n-two clearfix every_part">
                        <div class="receive_y_or_n-left fl every_title">是否通知组织单位：</div>
                        <div class="receive_y_or_n-right fl">
                            <p> <label><input type="radio" name="isSiteReceive" value="1" <c:if test="${tabDataTwo.isSiteReceive==1 }">checked</c:if>/> 接收下属所有网站预警 </label></p>
                            <p> <label><input type="radio" name="isSiteReceive" value="2" <c:if test="${tabDataTwo.isSiteReceive==2 }">checked</c:if>/>不接收下属所有网站预警</label></p>
                            <p> <label><input type="radio" name="isSiteReceive" value="3" <c:if test="${tabDataTwo.isSiteReceive==3 }">checked</c:if>/>只接收本级门户网站预警</label></p>
                            <p class="instructions">网站群中每个网站出现异常情况的预警。实时发送，或问题积累到比较危险的程度发送。</p>
                        </div>
                    </div>
                   <!-- <div class="receive_Members  clearfix every_part">
                        <div class="receive_Members_left fl every_title">组织单位接收成员：</div>
                        <div class="receive_Members_right fl">
                            <p> <label><input type="checkbox"/> 网站负责人</label><span>（ 张三&nbsp;&nbsp;15878954583&nbsp;&nbsp;zhangs@126.com）</span></p>
                            <p> <label><input type="checkbox"/> 业务联系人</label><span>（ 李斯&nbsp;&nbsp;13825485621&nbsp;&nbsp;zlisi@163.com）</span></p>
                        </div>
                    </div>-->
                    <div class="receive_Members  clearfix every_part" id="hideDiv2">
                        <div class="receive_Members_left fl every_title">组织单位接收成员：</div>
                        <div class="receive_Members_right fl">
                            <p> <label><input type="checkbox" name="isOrgPrincipal_one" <c:if test="${tabDataTwo.isOrgPrincipal==1 }">checked</c:if>/>负责人</label><span>（ ${databaseOrgInfo.principalName}&nbsp;&nbsp;${databaseOrgInfo.principalPhone}&nbsp;&nbsp;${databaseOrgInfo.principalEmail }）</span><span style="position:absolute; right:-40px;"><a href="<%=path%>/manageInfo_index.action?top=8" style="color: black;text-decoration:underline;" >修改</a></span></p>
                            <p> <label><input type="checkbox" name="isOrgLinkman_one" <c:if test="${tabDataTwo.isOrgLinkman==1 }">checked</c:if>/>联系人</label><span>（ ${databaseOrgInfo.linkmanName }&nbsp;&nbsp;${databaseOrgInfo.linkmanPhone  }&nbsp;&nbsp;${databaseOrgInfo.linkmanEmail }）</span><span style="position:absolute; right:-40px;"><a href="<%=path%>/manageInfo_index.action?top=8" style="color: black;text-decoration:underline;" >修改</a></span></p>
                            <p> <label><input type="checkbox" name="isOrgLinkman_two" <c:if test="${tabDataTwo.orgLinkmanTwo==1 }">checked</c:if>/>联系人</label><span>（ ${databaseOrgInfo.linkmanNametwo }&nbsp;&nbsp;${databaseOrgInfo.linkmanPhonetwo  }&nbsp;&nbsp;${databaseOrgInfo.linkmanEmailtwo }）</span><span style="position:absolute; right:-40px;"><a href="<%=path%>/manageInfo_index.action?top=8" style="color: black;text-decoration:underline;" >修改</a></span></p>
                            <p> <label><input type="checkbox" name="isOrgLinkman_three" <c:if test="${tabDataTwo.orgLinkmanThree==1 }">checked</c:if>/>联系人</label><span>（ ${databaseOrgInfo.linkmanNamethree }&nbsp;&nbsp;${databaseOrgInfo.linkmanPhonethree  }&nbsp;&nbsp;${databaseOrgInfo.linkmanEmailthree }）</span><span style="position:absolute; right:-40px;"><a href="<%=path%>/manageInfo_index.action?top=8" style="color: black;text-decoration:underline;" >修改</a></span></p>
                        </div>
                    </div>
                    <div class="setting_infor_way clearfix every_part">
                        <div class="setting_infor_way_left fl every_title">设置消息方式：</div>
                        <div class="setting_infor_way_right fl">
                         <p> <label><input type="checkbox" name="isWxReceive_one"  <c:if test="${tabDataTwo.isWxReceive==1 }">checked</c:if>/>微信消息</label></p>
                            <p> <label><input type="checkbox" name="isEmailReceive_one" <c:if test="${tabDataTwo.isEmailReceive==1 }">checked</c:if>/>邮件消息</label></p>
                            <p> <label><input type="checkbox" name="isTelReceive_one" <c:if test="${tabDataTwo.isTelReceive==1 }">checked</c:if>/>短信消息 
                            <input type="hidden" id="startHourT" value="${tabDataTwo.startHour }">
							<input type="hidden" id="endHourT" value="${tabDataTwo.endHour}">
                            <input class="Wdate" type="text" id="startHourTT" onfocus="WdatePicker({dateFmt:'HH:mm:ss',maxDate:'#F{$dp.$D(\'endHourTT\',{H:-1})}'})"> 
                             <span >至</span>
                        	<input class="Wdate" type="text" id="endHourTT" onClick="WdatePicker({dateFmt:'HH:mm:ss',minDate:'#F{$dp.$D(\'startHourTT\',{H:+1})}'})"> 
                            </label></p>
                            <p class="instructions">请扫描二维码关注“开普云监管”，绑定您的登录账号，即可接收微信预警：</p>
                        </div>
                    </div>
                    <div class="receive_y_or_n-two clearfix every_part">
                        <div class="receive_y_or_n-left fl every_title">是否通知下级填报单位：</div>
                        <div class="receive_y_or_n-right fl">
                            <p> <label><input type="radio"  name="isNextAllSite" value="1" <c:if test="${tabDataTwo.isNextAllSite==1 }">checked</c:if>/>通知</label></p>
                            <p> <label><input type="radio"  name="isNextAllSite" value="2" <c:if test="${tabDataTwo.isNextAllSite==2 }">checked</c:if>/>不通知</label></p>
                            <p class="instructions">网站发生预警时，是否通知到对应的填报单位。</p>
                        </div>
                    </div>
                    <div class="receive_Members  clearfix every_part" id="hideDiv2">
                        <div class="receive_Members_left fl every_title">下级填报单位接收成员：</div>
                        <div class="receive_Members_right fl">
                            <p> <label><input type="checkbox" name="isPrincipalReceive" <c:if test="${tabDataTwo.isPrincipalReceive==1 }">checked</c:if>/> 网站负责人</label></p>
                            <p> <label><input type="checkbox" name="isLinkmanReceive" <c:if test="${tabDataTwo.isLinkmanReceive==1 }">checked</c:if>/> 业务联系人</label></p>
                        </div>
                    </div>

                    <!--<div class="erweima_part clearfix every_part">
                        <div class="erweima_part_left fl every_title"></div>
                        <div class="erweima_part_right fl">
                            <img src="../../images/info_setting_erweima_img.jpg" alt="二维码"/>
                        </div>
                    </div>-->
                    <div class="insure_part clearfix every_part">
                        <div class="insure_part_left fl every_title"></div>
                        <div class="insure_part_right fl" onclick="updateSiteInfo()">
                            确定
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
<!-- Modal -->
<!--<div id="imgzoomModal" class="page-modal modal fade hide" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="imgzoom-max">
        <div class="imgzoom-maxImage">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
            <img id="maxImg" alt="image" src="../../images/zanyong/4.jpg" />
        </div>
    </div>
</div>-->


<input type="hidden" value="${nextChangeLast}" id="nextChangeLast">
<script language="javascript" type="text/javascript" src="<%=path%>/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="<%=path%>/js/configEarly/configEarlyOrg.js"></script>

</body>
</html>

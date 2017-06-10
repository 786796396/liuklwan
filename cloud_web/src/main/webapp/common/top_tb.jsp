<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
        body{ margin: 0;}
        .guangg_bg{ width: 100%; height: 60px; background: #380454; margin: 0 auto;}
        .guangg_content{ position:relative; width:996px; height: 60px; <%-- background: url("<%=path%>/images/ad_pic.png") no-repeat center center; --%> margin:0 auto; cursor: pointer;}
         .guangg_close-iocn{ position: absolute;top: 4px; right: 4px; width: 20px; height: 20px; background: url("<%=path%>/images/close-icon1.png") no-repeat center center;}
        .report{position:absolute; right:50px; bottom:27px; font:normal 13px '微软雅黑'; cursor:pointer;}
        .report_span{padding:7px; color:#fff; background:#2dcc70; border-radius:6px;}
  </style>
<title></title>
</head>
<body class="bg_f5f5f5">
 	<!--头部       satrt  -->
	<div class="header">
		<input type="hidden" value="${sessionScope.shiroUser.siteCode}" id="siteCodeId">
		<input type ='hidden' value="${sessionScope.shiroUser.childSiteCode}" id="shirUserCode">	
		<input type ='hidden' value="${sessionScope.shiroUser.siteName}" id="siteName">	
		<input type ='hidden' value="${sessionScope.shiroUser.orgToInfo}" id="orgToInfo">	
		<input type ='hidden' value="${requestScope.siteCode}" id="reqSiteCode">	
		<input type ='hidden' value="${sessionScope.shiroUser.paTargetCount}" id="paTargetCount">	

		<div class="guangg_bg" id="advertDiv" style="display: none">
			<div class="guangg_content">
				<script type="text/javascript" src="<%=path%>/js/advert.js?v=1"
					encodead="ad_1_1_1" id="_ad_"></script>
				<i class="guangg_close-iocn"
					onclick="hideAdvert();event.cancelBubble=true"></i>
			</div>
		</div>

		<div class="header-content clearfix">
			<div class="left-part fl">
				<div class="logo fl" id="logoID"></div>
				<%-- <i class="publi-ico_2 fgx-b fl"></i> <a id="aID" class="fl">
					<img src="<%=path%>/images/common/yjc-ziyang.png" alt="" />
				</a> --%>
			</div>
			
			<!-- top部分  satrt -->
			<div class="center-part fl" id="center-part">
				<ul class="nav-part fl o" id="ulId">
				</ul>
				<ul class="nav-part fl t" id="ulTwId">
				</ul>
				<ul class="nav-part fl" id="ulGdId">
				</ul>
			</div>
			<!-- top部分  end -->
			
			<div class="right-part fr">
				<ul id="glUlId" class="clearfix">
					<li id="logOut" class="indexTop "><span class="tuichu publi-ico_2" title="退出"></span> <em class=""></em>
					</li>
					<li class="publi-ico_2 fgx-s indexTop"></li>
					<li class="indexTop"><a target="_blank"
						href="https://boxpro.cn/boxpro/p/4kLdCq_NZ"><span
							class="bzsc publi-ico_2" title="使用手册"></span> </a><em class="active"></em>
					</li>
 					<li class="publi-ico_2 fgx-s indexTop"></li>
					<li id="glLiId" class="indexTop"></li>
					<li class="publi-ico_2 fgx-s indexTop"></li>
					<!-- <li class="yujing-part indexTop"><span class="yujing publi-ico_2"></span>
						<em class=""></em> <i class="yujing-tip">99+</i></li>
					<li class="publi-ico_2 fgx-s"></li> -->
				</ul>
			</div>
			<div class="wecat-part fr" style="display:  block">
				<div class="wecat-content" id="weChatId">
					<span class="publi-ico_2 ewm-ico-new"> </span> 
					<i class="publi-ico_2 down-ico-2"></i>
				</div>

				<div class="wecat-sh" id="weChatDetailId" style="display:  none">
					<div class="sh-top clearfix">
						<div class="fl">
							<img src="<%=path%>/images/ewm-133.png" alt="" />
						</div>
						<div class="fr">
							<ul>
								<li class=""><i class="ckwz publi-ico_2"></i> <span>查看网站状况</span>
								</li>
								<li class="fgx-l"></li>
								<li class=""><i class="jsyj publi-ico_2"></i> <span>接收预警消息</span>
								</li>
							</ul>
						</div>
					</div>
					<div class="sh-bottom">
						<h4>微信公众号：开普云</h4>
						<span>快快绑定微信吧！更多功能陆续开放中...</span>
					</div>
					<i class="mty-ico publi-ico_2"></i>
				</div>
			</div>
		</div>
	</div>

	<!--头部       end  -->

	<div class="base-info">
		<div class="bi-content clearfix">
			<i class="colo-f publi-ico_2"></i>
			<a href="${sessionScope.shiroUser.url}" target="_blank" title="${sessionScope.shiroUser.url}">
				<h4 class="fl">
					${sessionScope.shiroUser.childSiteCode }- ${sessionScope.shiroUser.siteName }
				</h4>
			</a>
			<div class="sub-success" style="display: none" id="submitSuccess">提交成功</div>
			<div class="fr">
				<ul>
					<li class="fr">数据更新时间：${sessionScope.shiroUser.updateDate }</li>
					<li class="fgx-s publi-ico_2 fr"></li>
					<li class="fr">监测状态：
						<c:if test='${sessionScope.shiroUser.orgToInfo == 1}'>
					    <c:if test='${sessionScope.shiroUser.orgToIsScan == 1}'><span >正常监测</span></c:if>
					    <c:if test='${sessionScope.shiroUser.orgToIsScan != 1}'><span style="color:red;cursor:pointer;" >暂不监测</span></c:if>
				   		</c:if>
				   		<c:if test='${sessionScope.shiroUser.orgToInfo != 1}'>
					    <c:if test='${sessionScope.shiroUser.isScan == 1}'><span >正常监测</span></c:if>
					    <c:if test='${sessionScope.shiroUser.isScan != 1}'><span style="color:red;cursor:pointer;" >暂不监测</span></c:if>
				   		</c:if>
					</li>
					<!-- <li class="fgx-s publi-ico_2 fr"></li> 
 					<li class="fr jies"><a href="javascript:void(0)" class="colo-38ba98" onclick="testYun()"> <i 
 							class="publi-ico_2 zbjs"></i> 试用云专题
					</a></li> 
					 <li class="fr jies"><a href="" class="colo-38ba98"> <i
							class="publi-ico_2 zbjs"></i> 指标解释
					</a></li> -->
				</ul>
			</div>
		</div>
	</div>
	
	  <!--监测异常说明--网页格式不规范开始-->
<div class="modal fade hiddenn" id="myModal_1" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false" style="width:734px; margin-left: -367px; left: 50%;">
    <div class="modal-dialog">
        <div class="modal-content" >
            <div class="modal-header green_head" style=" position: relative;">
                <div type="button" class="close green_head_closeicon"
                     data-dismiss="modal" aria-hidden="true">
                </div>
                <h4 class="modal-title green_head_title" id="myModalLabel">
                    暂不监测说明
                </h4>
            </div>
            <div class="modal-body" >
                <div class="body_top">
                    <p>
                        <span class="body_top_fir">监测网址：</span>
                        <span class="body_top_sec"><a href="${sessionScope.shiroUser.url}">${sessionScope.shiroUser.url }</a></span>
                    </p>
                    <p>
                        <span class="body_top_fir">暂不监测原因：</span>
                        <span class="body_top_sec colo-e74848">网页格式不规范 </span>
                    </p>
                </div>
                <div class="wenxin_tip clearfix">
                    <i class="fl"></i>
                    <span class="wenxin_title fl">温馨提示：</span>
                </div>
                <div class="modal_body_content">
                    <p class="paragraph">开普云监测采用云端引擎布署，网页数据需尽量满足国际通用的W3CHtml格式标准，方便引擎抓取数据，以下情况是比较不规范的格式，请您参考：</p>

                    <p class="every_tips">
                        <i></i>
                        链接使用JS动态链接
                    </p>
                    <p class="every_tips">
                        <i></i>
                        首页跳转到其他链接
                    </p>
                    <p class="every_tips">
                        <i></i>
                        首页没有文字内容，主要是链接导航，引擎监测不到首页更新情况
                    </p>
                    <p class="every_tips">
                        <i></i>
                        详情页跳转
                    </p>
                    <p class="every_tips">
                        <i></i>
                        页面的更新时间使用js加载
                    </p>
                    <p class="every_tips">
                        <i></i>
                        详情页使用iframe
                    </p>
                    <div class="middle_line"></div>
                    <p class="lineH25">
                        如果您需要帮助，请联系我们的客服工作人员
                    </p>
                    <p class="lineH25">
                        客服电话：4000-976-005 <i class="qq_icon"></i><a href="tencent://message/?uin=2355700042&Site=admin5.com&Menu=yes"><span class="qq_num cursor_p">QQ：2355700042</span></a>
                    </p>
                    <div style="position:absolute; right:50px; bottom:40px; font:normal 13px '微软雅黑'; cursor:pointer;">
                    	<span onclick="checkReport();" class="report_span">我的监测报告</span>
                    </div>
                </div>
            </div>
            <!--<div class="modal-footer">
                <button type="button" class="btn btn-default zg-close"
                        data-dismiss="modal">关闭
                </button>
            </div>-->
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!--监测异常说明--网页格式不规范结束-->

<!--监测异常说明--网站打开失败开始-->
<div class="modal fade hiddenn" id="myModal_2" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false" style="width:734px; margin-left: -367px; left: 50%;">
    <div class="modal-dialog">
        <div class="modal-content" >
            <div class="modal-header green_head" style=" position: relative;">
                <div type="button" class="close green_head_closeicon"
                     data-dismiss="modal" aria-hidden="true">
                </div>
                <h4 class="modal-title green_head_title" id="myModalLabel">
                    暂不监测说明
                </h4>
            </div>
            <div class="modal-body" >
                <div class="body_top">
                    <p>
                        <span class="body_top_fir">监测网址：</span>
                        <span class="body_top_sec"><a href="${sessionScope.shiroUser.url}">${sessionScope.shiroUser.url }</a></span>
                    </p>
                    <p>
                        <span class="body_top_fir">暂不监测原因：</span>
                        <span class="body_top_sec colo-e74848">网站打开失败 </span>
                    </p>
                </div>
                <div class="wenxin_tip clearfix">
                    <i class="fl"></i>
                    <span class="wenxin_title fl">温馨提示：</span>
                </div>
                <div class="modal_body_content">
                    <div class="mart-20 marb-43">
                        <p class="lineH25 ">系统连续7天连接您的网站首页时出现404或503错误，请您联系网站工程师检查网站的连通性。</p>
                        <p class="lineH25">或者核对一下您的网站首页地址是否准确，如需修改请联系客服</p>
                    </div>

                    <div class="middle_line"></div>
                    <p class="lineH25">
                        如果您需要帮助，请联系我们的客服工作人员
                    </p>
                    <p class="lineH25">
                        客服电话：4000-976-005 <i class="qq_icon"></i><a href="tencent://message/?uin=2355700042&Site=admin5.com&Menu=yes"><span class="qq_num cursor_p">QQ：2355700042</span></a>
                    </p>
                    <div style="position:absolute; right:50px; bottom:43px; font:normal 13px '微软雅黑'; cursor:pointer;">
                    	<span onclick="checkReport();" class="report_span">我的监测报告</span>
                    </div>
                </div>
            </div>
            <!--<div class="modal-footer">
                <button type="button" class="btn btn-default zg-close"
                        data-dismiss="modal">关闭
                </button>
            </div>-->
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!--监测异常说明--网站打开失败结束-->

<!--监测异常说明--日期格式不规范开始-->
<div class="modal fade hiddenn" id="myModal_3" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false" style="width:734px; margin-left: -367px; left: 50%;">
    <div class="modal-dialog">
        <div class="modal-content" >
            <div class="modal-header green_head" style=" position: relative;">
                <div type="button" class="close green_head_closeicon"
                     data-dismiss="modal" aria-hidden="true">
                </div>
                <h4 class="modal-title green_head_title" id="myModalLabel">
                    暂不监测说明
                </h4>
            </div>
            <div class="modal-body" >
                <div class="body_top">
                    <p>
                        <span class="body_top_fir">监测网址：</span>
                        <span class="body_top_sec"><a href="${sessionScope.shiroUser.url}">${sessionScope.shiroUser.url }</a></span>
                    </p>
                    <p>
                        <span class="body_top_fir">暂不监测原因：</span>
                        <span class="body_top_sec colo-e74848">日期格式不规范 </span>
                    </p>
                </div>
                <div class="wenxin_tip clearfix">
                    <i class="fl"></i>
                    <span class="wenxin_title fl">温馨提示：</span>
                </div>
                <div class="modal_body_content">
                    <p class="paragraph">开普云监测采用云端引擎布署，引擎会自动抓取您网页上的日期便于判断网站更新情况，您的网页上的日期可能存在以下情况，使得引擎无法抓取到您的网站日期。</p>

                    <p class="every_tips">
                        <i></i>
                        年份不是4位数，比如2016年，可能写为16年
                    </p>
                    <p class="every_tips">
                        <i></i>
                        日期是中文格式，比如二零一六年七月一日
                    </p>
                    <p class="every_tips">
                        <i></i>
                        网页中无日期
                    </p>
                    <div class="middle_line"></div>
                    <p class="lineH25">
                        如果您需要帮助，请联系我们的客服工作人员
                    </p>
                    <p class="lineH25">
                        客服电话：4000-976-005 <i class="qq_icon"></i><a href="tencent://message/?uin=2355700042&Site=admin5.com&Menu=yes"><span class="qq_num cursor_p">QQ：2355700042</span></a>
                    </p>
                    <div style="position:absolute; right:50px; bottom:48px; font:normal 13px '微软雅黑'; cursor:pointer;">
                    	<span onclick="checkReport();" class="report_span">我的监测报告</span>
                    </div>
                </div>
            </div>
            <!--<div class="modal-footer">
                <button type="button" class="btn btn-default zg-close"
                        data-dismiss="modal">关闭
                </button>
            </div>-->
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!--监测异常说明--日期格式不规范结束-->

<!--监测异常说明--域名解析失败开始-->
<div class="modal fade hiddenn" id="myModal_4" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false" style="width:734px; margin-left: -367px; left: 50%;">
    <div class="modal-dialog">
        <div class="modal-content" >
            <div class="modal-header green_head" style=" position: relative;">
                <div type="button" class="close green_head_closeicon"
                     data-dismiss="modal" aria-hidden="true">
                </div>
                <h4 class="modal-title green_head_title" id="myModalLabel">
                    暂不监测说明
                </h4>
            </div>
            <div class="modal-body" >
                <div class="body_top">
                    <p>
                        <span class="body_top_fir">监测网址：</span>
                        <span class="body_top_sec"><a href="${sessionScope.shiroUser.url}">${sessionScope.shiroUser.url }</a></span>
                    </p>
                    <p>
                        <span class="body_top_fir">暂不监测原因：</span>
                        <span class="body_top_sec colo-e74848">域名解析失败</span>
                    </p>
                </div>
                <div class="wenxin_tip clearfix">
                    <i class="fl"></i>
                    <span class="wenxin_title fl">温馨提示：</span>
                </div>
                <div class="modal_body_content">
                    <div class="mart-20 marb-30">
                        <p class="lineH25">请联系您的域名解析服务商，恢复域名解析。</p>
                        <p class="lineH25">开普云监管采用云端引擎布署，您的网站访问状态会影响引擎监测情况。</p>
                    </div>
                    <div class="middle_line"></div>
                    <p class="lineH25">
                        如果您需要帮助，请联系我们的客服工作人员
                    </p>
                    <p class="lineH25">
                        客服电话：4000-976-005 <i class="qq_icon"></i><a href="tencent://message/?uin=2355700042&Site=admin5.com&Menu=yes"><span class="qq_num cursor_p">QQ：2355700042</span></a>
                    </p>
                    <div style="position:absolute; right:50px; bottom:40px; font:normal 13px '微软雅黑'; cursor:pointer;">
                    	<span onclick="checkReport();" class="report_span">我的监测报告</span>
                    </div>
                </div>
            </div>
            <!--<div class="modal-footer">
                <button type="button" class="btn btn-default zg-close"
                        data-dismiss="modal">关闭
                </button>
            </div>-->
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!--监测异常说明--域名解析失败结束-->

<!--监测异常说明--网站通知关停开始-->
<div class="modal fade hiddenn" id="myModal_5" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false" style="width:734px; margin-left: -367px; left: 50%;">
    <div class="modal-dialog">
        <div class="modal-content" >
            <div class="modal-header green_head" style=" position: relative;">
                <div type="button" class="close green_head_closeicon"
                     data-dismiss="modal" aria-hidden="true">
                </div>
                <h4 class="modal-title green_head_title" id="myModalLabel">
                    暂不监测说明
                </h4>
            </div>
            <div class="modal-body" >
                <div class="body_top">
                    <p>
                        <span class="body_top_fir">监测网址：</span>
                        <span class="body_top_sec"><a href="${sessionScope.shiroUser.url}">${sessionScope.shiroUser.url }</a></span>
                    </p>
                    <p>
                        <span class="body_top_fir">暂不监测原因：</span>
                        <span class="body_top_sec colo-e74848">网站通知关停</span>
                    </p>
                </div>
                <div class="wenxin_tip clearfix">
                    <i class="fl"></i>
                    <span class="wenxin_title fl">温馨提示：</span>
                </div>
                <div class="modal_body_content" style="position:relative;">
                    <div class="mart-20 marb-30">

                        <p class="lineH25">您的网站上挂了类似关停整改的通知，您可以到 <span class="cursor_p colo-5656d7 under_line"><a href="https://pucha.kaipuyun.cn/boxpro/custom/pucha">报送系统</a></span>  申请关停或临时关停。</p>
                        <p class="lineH25"> </p>
                    </div>
                    <div class="middle_line"></div>
                    <p class="lineH25">
                        如果您需要帮助，请联系我们的客服工作人员
                    </p>
                    <p class="lineH25">
                        客服电话：4000-976-005 <i class="qq_icon"></i><a href="tencent://message/?uin=2355700042&Site=admin5.com&Menu=yes"><span class="qq_num cursor_p">QQ：2355700042</span></a>
                    </p>
                    <div class="report">
                    	<span onclick="checkReport();" class="report_span">我的监测报告</span>
                    </div>
                </div>
            </div>
            <!--<div class="modal-footer">
                <button type="button" class="btn btn-default zg-close"
                        data-dismiss="modal">关闭
                </button>
            </div>-->
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!--监测异常说明--网站通知关停结束-->

<!--监测异常说明--网站安全配置开始-->
<div class="modal fade hiddenn" id="myModal_6" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false" style="width:734px; margin-left: -367px; left: 50%;">
    <div class="modal-dialog">
        <div class="modal-content" >
            <div class="modal-header green_head" style=" position: relative;">
                <div type="button" class="close green_head_closeicon"
                     data-dismiss="modal" aria-hidden="true">
                </div>
                <h4 class="modal-title green_head_title" id="myModalLabel">
                    暂不监测说明
                </h4>
            </div>
            <div class="modal-body" >
                <div class="body_top">
                    <p>
                        <span class="body_top_fir">监测网址：</span>
                        <span class="body_top_sec"><a href="${sessionScope.shiroUser.url}">${sessionScope.shiroUser.url }</a></span>
                    </p>
                    <p>
                        <span class="body_top_fir">暂不监测原因：</span>
                        <span class="body_top_sec colo-e74848">网站安全配置（例如：安全狗）</span>
                    </p>
                </div>
                <div class="wenxin_tip clearfix">
                    <i class="fl"></i>
                    <span class="wenxin_title fl">温馨提示：</span>
                </div>
                <div class="modal_body_content">
                    <div class="mart-20 marb-30 " style="position: relative;">

                        <p class="lineH-25">请联系您的网站管理员，将开普云监测的IP地址加入安全配置白名单。 </p>
                        <p class="lineH25 colo-5656d7 under_line"><a id="clickIp" href="javascript:void(0)">开普云监测的IP地址</a><i class="blue_right-icon under_line"></i></p>
                        <div class="ip_group_box" id="showIPs">
                            <div class="ip_top  clearfix">
                                <div class="ip_top_left fl">
                                    <ul class="copyText">
                                        <li> 121.40.74.31 </li>
                                        <li> 120.55.90.78  </li>
                                        <li> 120.26.210.41</li>
                                        <li> 121.41.11.226 </li>
                                        <li> 120.26.93.149 </li>
                                        <li> 120.26.42.212 </li>
                                        <li> 120.55.180.10 </li>
                                        <li> 121.41.29.248</li>
                                        <li> 121.40.123.173 </li>
                                        <li> 114.215.174.217 </li>
                                        <li class="last_li"> 112.124.117.141</li>
                                    </ul>
                                </div>
                                <div class="ip_top_line fl"></div>
                                <div class="ip_top_right fl">
                                    <ul class="copyText">
                                        <li> 120.26.226.5 </li>
                                        <li> 112.74.90.36 </li>
                                        <li> 121.40.17.13  </li>
                                        <li> 121.41.11.171 </li>
                                        <li> 123.57.18.200 </li>
                                        <li> 121.42.42.141 </li>
                                        <li> 120.26.119.69</li>
                                        <li> 120.55.83.215 </li>
                                        <li> 120.26.231.148</li>
                                        <li> 120.26.218.193 </li>
                                        <li class="last_li">  </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="ip_bottom">
                                <span class="commit" id="copyCon">复制全部</span>
                                <span class="closed" id="closeCon">关闭</span>
                            </div>
                    </div>
                    <div class="middle_line"></div>
                    <p class="lineH25">
                        如果您需要帮助，请联系我们的客服工作人员
                    </p>
                    <p class="lineH25">
                        客服电话：4000-976-005 <i class="qq_icon"></i><a href="tencent://message/?uin=2355700042&Site=admin5.com&Menu=yes"><span class="qq_num cursor_p">QQ：2355700042</span></a>
                    </p>
                    <div style="position:absolute; right:50px; bottom:1px; font:normal 13px '微软雅黑'; cursor:pointer;">
                    	<span onclick="checkReport();" class="report_span">我的监测报告</span>
                    </div>
                </div>
            </div>
            <!--<div class="modal-footer">
                <button type="button" class="btn btn-default zg-close"
                        data-dismiss="modal">关闭
                </button>
            </div>-->
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</div>
<!--监测异常说明--网站安全配置结束-->

<!--监测异常说明--无发布信息开始-->
<div class="modal fade hiddenn" id="myModal_8" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false" style="width:734px; margin-left: -367px; left: 50%;">
    <div class="modal-dialog">
        <div class="modal-content" >
            <div class="modal-header green_head" style=" position: relative;">
                <div type="button" class="close green_head_closeicon"
                     data-dismiss="modal" aria-hidden="true">
                </div>
                <h4 class="modal-title green_head_title" id="myModalLabel">
                    暂不监测说明
                </h4>
            </div>
            <div class="modal-body" >
                <div class="body_top">
                    <p>
                        <span class="body_top_fir">监测网址：</span>
                        <span class="body_top_sec"><a href="${sessionScope.shiroUser.url}">${sessionScope.shiroUser.url }</a></span>
                    </p>
                    <p>
                        <span class="body_top_fir">暂不监测原因：</span>
                        <span class="body_top_sec colo-e74848">无发布信息</span>
                    </p>
                </div>
                <div class="wenxin_tip clearfix">
                    <i class="fl"></i>
                    <span class="wenxin_title fl">温馨提示：</span>
                </div>
                <div class="modal_body_content">
                    <div class="mart-20 marb-30">

                        <p class="lineH25">您的网站可能没有发布信息，云端引擎抓取不到有效信息，不利于分析网站更新情况。</p>
                        <p class="lineH25">开普云监测采用云端引擎布署，您的网站更新状态会影响引擎监测情况。</p>
                    </div>
                    <div class="middle_line"></div>
                    <p class="lineH25">
                        如果您需要帮助，请联系我们的客服工作人员
                    </p>
                    <p class="lineH25">
                        客服电话：4000-976-005 <i class="qq_icon"></i><a href="tencent://message/?uin=2355700042&Site=admin5.com&Menu=yes"><span class="qq_num cursor_p">QQ：2355700042</span></a>
                    </p>
                    <div class="report">
                    	<span onclick="checkReport();" class="report_span">我的监测报告</span>
                    </div>
                </div>
            </div>
            <!--<div class="modal-footer">
                <button type="button" class="btn btn-default zg-close"
                        data-dismiss="modal">关闭
                </button>
            </div>-->
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!--监测异常说明--无发布信息结束-->

<!--监测异常说明--中文域名开始-->
<div class="modal fade hiddenn" id="myModal_9" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false" style="width:734px; margin-left: -367px; left: 50%;">
    <div class="modal-dialog">
        <div class="modal-content" >
            <div class="modal-header green_head" style=" position: relative;">
                <div type="button" class="close green_head_closeicon"
                     data-dismiss="modal" aria-hidden="true">
                </div>
                <h4 class="modal-title green_head_title" id="myModalLabel">
                    暂不监测说明
                </h4>
            </div>
            <div class="modal-body" >
                <div class="body_top">
                    <p>
                        <span class="body_top_fir">监测网址：</span>
                        <span class="body_top_sec"><a href="${sessionScope.shiroUser.url}">${sessionScope.shiroUser.url }</a></span>
                    </p>
                    <p>
                        <span class="body_top_fir">暂不监测原因：</span>
                        <span class="body_top_sec colo-e74848">监测平台暂不支持中文域名类型网站</span>
                    </p>
                </div>
                <div class="wenxin_tip clearfix">
                    <i class="fl"></i>
                    <span class="wenxin_title fl">温馨提示：</span>
                </div>
                <div class="modal_body_content">
                    <div class="mart-20 marb-30">

                        <p class="lineH25">请将您的英文网站地址告诉我们，<span id="checkURL" class="cursor_p colo-5656d7 under_line">请点击这里</span></p>
                        <p class="lineH25"></p>
                    </div>
                    <div class="middle_line"></div>
                    <p class="lineH25">
                        如果您需要帮助，请联系我们的客服工作人员
                    </p>
                    <p class="lineH25">
                        客服电话：4000-976-005 <i class="qq_icon"></i><a href="tencent://message/?uin=2355700042&Site=admin5.com&Menu=yes"><span class="qq_num cursor_p">QQ：2355700042</span></a>
                    </p>
                    <div style="position:absolute; right:50px; bottom:40px; font:normal 13px '微软雅黑'; cursor:pointer;">
                    	<span onclick="checkReport();" class="report_span">我的监测报告</span>
                    </div>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!--监测异常说明--中文域名结束-->
<!--暂不监测说明--网站关停开始-->
<div class="modal fade hiddenn" id="myModal_10" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false" style="width:734px; margin-left: -367px; left: 50%;">
    <div class="modal-dialog">
        <div class="modal-content" >
            <div class="modal-header green_head" style=" position: relative;">
                <div type="button" class="close green_head_closeicon"
                     data-dismiss="modal" aria-hidden="true">
                </div>
                <h4 class="modal-title green_head_title" id="myModalLabel">
                   暂不监测说明
                </h4>
            </div>
            <div class="modal-body" >
                <div class="body_top">
                    <p>
                        <span class="body_top_fir">监测网址：</span>
                        <span class="body_top_sec"><a href="${sessionScope.shiroUser.url}">${sessionScope.shiroUser.url }</a></span>
                    </p>
                    <p>
                        <span class="body_top_fir">暂不监测原因：</span>
                        <span class="body_top_sec colo-e74848">网站状态为“关停”</span>
                    </p>
                </div>
                <div class="wenxin_tip clearfix">
                    <i class="fl"></i>
                    <span class="wenxin_title fl">温馨提示：</span>
                </div>
                <div class="modal_body_content">
                    <div class="mart-20 marb-30">

                        <p class="lineH25">您的网站当前处于“关停”状态，如需监测请到<span class="cursor_p colo-5656d7 under_line"><a  target="_blank" href="https://pucha.kaipuyun.cn/boxpro/custom/pucha">全国政府网站报送系统 </a></span>中恢复</p>
                        <p class="lineH25"> </p>
                    </div>
                    <div class="middle_line"></div>
                    <p class="lineH25">
                        如果您需要帮助，请联系我们的客服工作人员
                    </p>
                    <p class="lineH25">
                        客服电话：4000-976-005 <i class="qq_icon"></i><a href="tencent://message/?uin=2355700042&Site=admin5.com&Menu=yes"><span class="qq_num cursor_p">QQ：2355700042</span></a>
                    </p>
                    <div class="report">
                    	<span onclick="checkReport();" class="report_span">我的监测报告</span>
                    </div>
                </div>
            </div>
            <!--<div class="modal-footer">
                <button type="button" class="btn btn-default zg-close"
                        data-dismiss="modal">关闭
                </button>
            </div>-->
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!--暂不监测说明--网站通知关停结束-->

<!--暂不监测说明--网站例外开始-->
<div class="modal fade hiddenn" id="myModal_11" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false" style="width:734px; margin-left: -367px; left: 50%;">
    <div class="modal-dialog">
        <div class="modal-content" >
            <div class="modal-header green_head" style=" position: relative;">
                <div type="button" class="close green_head_closeicon"
                     data-dismiss="modal" aria-hidden="true">
                </div>
                <h4 class="modal-title green_head_title" id="myModalLabel">
                    暂不监测说明
                </h4>
            </div>
            <div class="modal-body" >
                <div class="body_top">
                    <p>
                        <span class="body_top_fir">监测网址：</span>
                        <span class="body_top_sec"><a href="${sessionScope.shiroUser.url}">${sessionScope.shiroUser.url }</a></span>
                    </p>
                    <p>
                        <span class="body_top_fir">暂不监测原因：</span>
                        <span class="body_top_sec colo-e74848">网站状态为“例外”</span>
                    </p>
                </div>
                <div class="wenxin_tip clearfix">
                    <i class="fl"></i>
                    <span class="wenxin_title fl">温馨提示：</span>
                </div>
                <div class="modal_body_content">
                    <div class="mart-20 marb-30">

                        <p class="lineH25">您的网站当前处于“例外”状态，如需监测请到<span class="cursor_p colo-5656d7 under_line"><a href="https://pucha.kaipuyun.cn/boxpro/custom/pucha"  target="_blank">全国政府网站报送系统 </a></span>中恢复</p>
                        <p class="lineH25"> </p>
                    </div>
                    <div class="middle_line"></div>
                    <p class="lineH25">
                        如果您需要帮助，请联系我们的客服工作人员
                    </p>
                    <p class="lineH25">
                        客服电话：4000-976-005 <i class="qq_icon"></i><a href="tencent://message/?uin=2355700042&Site=admin5.com&Menu=yes"><span class="qq_num cursor_p">QQ：2355700042</span></a>
                    </p>
                    <div class="report">
                    	<span onclick="checkReport();" class="report_span">我的监测报告</span>
                    </div>
                </div>
            </div>
            <!--<div class="modal-footer">
                <button type="button" class="btn btn-default zg-close"
                        data-dismiss="modal">关闭
                </button>
            </div>-->
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
    <!-- 	是否完成引导判断 -->
</div>
	
<form id="yunformUrl" name="yunformUrl" method="post">
<input name="data" type="hidden" value="" id="testYunUrl"/>
</form>
</body>
<%@ include file="/common/exitmodal.jsp"%>
<script type="text/javascript" src="<%=path%>/js/common/header_tb.js?<%= autoVersoin %>"></script>

</html>	
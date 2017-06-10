<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/heade.jsp"%>
<title>网站绩效考评-往期自评任务详情</title>
<!-- Le styles -->
<link rel="stylesheet" href="<%=path%>/css/control_info-setting-t.css"/>
<link rel="stylesheet" href="<%=path%>/css/wpe_AppraisalTask.css"/>
</head>
<body>
<input type="hidden" id="ratingId" value="${ratingId}">
<input type="hidden" id="siteCode" value="${sessionScope.shiroUser.siteCode}">
<input type="hidden" id="taskId" value="${taskId}">
<input type="hidden" id="targetTaskId" value="${targetTaskId}">

<%-- <input type="hidden" id="overallSituation2" value="${overallSituation}"></input>
<input type="hidden" id="problemsSuggestions2" value="${problemsSuggestions}"></input>
<input type="hidden" id="other2" value="${other}"></input>
<input type="hidden" id="plan2" value="${plan}"></input> --%>

<div style="display:none;" id="overallSituation2" >${overallSituation}</div>
<div style="display:none;" id="problemsSuggestions2" >${problemsSuggestions}</div>
<div style="display:none;" id="other2" >${other}</div>
<div style="display:none;" id="plan2" >${plan}</div>

<div class="page-wrap bg_fff">
<%@ include file="/common/top.jsp"%>
<div class="main-container container">
	<div class="row-fluid">
		<c:set var="tb_index" value="28" scope="request" />
	<c:set var="menu" value="#menuInfoJi" scope="request" />
	<%@ include file="/common/leftmenu_tb.jsp"%>
          <div class="page-content appraisal-task tb" style="padding-top: 25px;">
            <!--考评任务详情头部title开始  appraisal-task_details-top-->
            <div class="task-top clearfix">
                <div class="top-title fl">
                    ${taskName}任务
                </div>
                <div class="fr">
                    <i class="return" onclick="goback()"></i>
                </div>
            </div>
            <!--考评任务详情头部title开始  appraisal-task_details-top-->
            <!--考评任务详情 详细列表部分开始-->
            <div class="details-list-part">
            	<i class="head_title five">填报人信息</i>
                <i></i>
                <div class="project">
                    <div>
                        <span><em style="color:red;display: inline-block;margin-right: 5px; position: relative; font-style:normal;">*</em>&nbsp;填报人：</span>
                        <input id="ratingName" class="blurr" value="${ratingName}" type="text"/>
                        
                    </div>
                </div>
                <div class="period_state clearfix">
                    <div class="fl">
                        <span><em style="color:red;display: inline-block;margin-right: 5px; position: relative; font-style:normal;">*</em>单位名称：</span>
                        <input id="companyName"  class="blurr" value="${companyName}" type="text"/>
                        
                    </div>
                    <div class="fl state" style="position:relative;">
                        <span><em style="color:red;display: inline-block;margin-right: 5px; position: relative; font-style:normal;">*</em>联系电话：</span>
                        <input id="ratingPhone" class="blurr" value="${ratingPhone}" type="text"/>
                        <span id="phoneSpan" style="position:absolute; right:13px; bottom:-10px;color: red; font:normal 12px '微软雅黑';">(如:010-82350961或18610070037)</span>
                    </div>
                </div>
            </div>
            <!--考评任务详情 详细列表部分结束-->
            <div class="details-list-part zs">
            	<i class="head_title five open_zs zs_s" style="">自评总述<em></em></i>
            	<i></i>
            	<i class="open_zs"></i>
            	<div class="clearfix zs_everyPart">
            		<div class="fl left">总体情况：</div>
            		<div class="fl right">
            			<textarea rows="" cols="" class="blurr" placeholder="此项为非必填项，可选择性填写。"  id="overallSituation"></textarea>
            		</div>
            	</div>
            	<div class="clearfix zs_everyPart">
            		<div class="fl left">问题及建议：</div>
            		<div class="fl right">
            			<textarea rows="" placeholder="此项为非必填项，可选择性填写。" cols="" class="blurr" id="problemsSuggestions"></textarea>
            		</div>
            	</div>
            	<div class="clearfix zs_everyPart">
            		<div class="fl left">其他说明：</div>
            		<div class="fl right">
            			<textarea rows="" cols="" placeholder="此项为非必填项，可选择性填写。"  class="blurr" id="other"></textarea>
            		</div>
            	</div>
            	<div class="clearfix zs_everyPart">
            		<div class="fl left">计划安排：</div>
            		<div class="fl right">
            			<textarea rows="" cols="" placeholder="此项为非必填项，可选择性填写。"  class="blurr" id="plan"></textarea>
            		</div>
            	</div>
            </div>
            <!--考评任务详情 详细table部分开始-->
            <div class="details-table-part">
                <div class="three-tab-top">
                    <div class="three-part clearfix several-part" id="getName">

                    </div>
                </div>
                <div class="table-box">
 
                    <div class="table-part change_box on">
                        <table >
                            <thead>
                                <tr>
                                    <th class="text-center w10p">二级指标</th>
                                    <th class="text-center w25p" colspan="2">三级指标</th>
                                    <th class="text-center  w10p">自评得分</th>
                                    <th class="text-center  w15p">自评说明</th>
                                    <th class="text-center w10p">栏目个数</th>
                                    <th class="text-center w10p">状态</th>
                                    <th class="text-center w10p">操作</th>
                                </tr>
                            </thead>
                            <tbody id="targets">

 
                            </tbody>
                        </table>
                    </div>

                </div>
				 <div class="tb_btn clearfix" style="padding:20px 35px 22px 0;">
                        <div class="submit-data fl">提交数据</div>
                        <div class="return fl"  onclick="goback()">返回</div>
                 </div>
            </div>
            <!--考评任务详情 详细table部分结束-->
            <!--消息设置内容部分结束-->
             <%@ include file="/common/footer.jsp"%>	
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->
</div>
<!-- Modal (submit-data)-->
<div class="page-modal modal fade hide submit-data-modal" id="submit-data" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 570px; left: 50%; top:10%; margin-left: -285px;">
    <!--sd:submit-data-->
    <div class="sd-head op_w-head clearfix">
        <div type="button" class="close green_head_closeicon"
             data-dismiss="modal" aria-hidden="true">
        </div>
        <h4 class="fl" id="myModalLabel">
            未填报提醒
        </h4>
    </div>
    <div class="sd-content">
        <p>
            <i class="red_icon-t"></i>
            自评内容存在未填写项，请先填写完整!
        </p>
    </div>
    <div class="sd-footer clearfix">
        <div class="ensure fr" id="closeCheckM">确定</div>
    </div>
</div>
<!-- /Modal -->
<!-- Modal (开始自评)-->
<div class="page-modal modal fade hide submit-data-modal zp_box" id="begin-zp"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width: 958px; left: 50%; top:7%; margin-left: -479px;">
    <!--zp:ziping(自评)-->
    <!--bz:begin-zp-->
    <div class="bz-head op_w-head clearfix" style="position:relative;">
        <div type="button" class="close green_head_closeicon"
             data-dismiss="modal" aria-hidden="true" >
        </div>
        <!-- <div style="position:absolute; right:75px; top:0; font-size:12px; font-family：微软雅黑; margin-top:22px;">注:自评打分不得超过权重值</div> -->
        <h4 class="fl" id="myModalLabel">
            网站自评
        </h4>
    </div>
    <div class="bz-content">
    	   <!-- <div style="position:absolute; top:10px; right:10px; width:100px; height:24px; border:1px solid #ccc; border-radius:3px; font-size:16px; line-height:24px; text-align:center;">权重：6</div> -->
           <div class="bz-every-part clearfix">
               <!--bzep:bz-every-part-->
               <!--zb:zhibiao(指标)-->
               <div class="bzep_left fl ">当前指标：</div>
               <div class="bzep_right fl" id="currentIn" style="width: calc(100% -86px)">
                   <!-- <span id="oneClass">健康情况</span>
                   <i></i>
                   <span id="twoClass">可用和更新情况</span>
                   <i></i>
                   <span class="last_leveal" id="threeClass">可用情况</span> -->
               </div>
           </div>
			
            <div class="bz-every-part clearfix margt_20">
                <!--bzep:bz-every-part-->
                <!--zb:zhibiao(指标)-->
                <div class="bzep_left fl ">考评内容：</div>
                <div class="bzep_right fl" style="width: calc(100% - 86px)">
                   <p id="checkContent">
                   </p>
                </div>
            </div>
            <div class="bz-every-part clearfix margt_20">
                <!--bzep:bz-every-part-->
                <!--zb:zhibiao(指标)-->
                <div class="bzep_left fl" style="text-indent:28px;">权重：</div>
                <div class="bzep_right fl" style="width: calc(100% - 86px)">
                   <p id="weight"></p>
                </div>
            </div>
            <div class="bz-every-part clearfix margt_20">
                <!--bzep:bz-every-part-->
                <!--zb:zhibiao(指标)-->
                <div class="bzep_left fl ">自评打分：</div>
                <div class="bzep_right fl">
                   <input id="ratingScore" type="text"    style="border-radius:0">
                   </input>
                </div>
                <div class="fl" style="font: normal 12px 'Microsoft YaHei','微软雅黑'; color: #363636; margin:10px 0 0 30px; color:#f40;">注：自评打分不得超过权重值</div>
            </div>
            <div class="bz-every-part clearfix margt_20">
                <!--bzep:bz-every-part-->
                <!--zb:zhibiao(指标)-->
                <div class="bzep_left fl "><em style="color:red;display: inline-block;margin-right: 5px; position: relative; font-style:normal;">*</em> 自评说明：</div>
                <div class="bzep_right fl">
                    <textarea id="ratingExplain"></textarea>
                </div>
            </div>
            <div class="bz-every-part clearfix margt_20 column-part" >
                <!--bzep:bz-every-part-->
                <!--zb:zhibiao(指标)-->
                <div class="bzep_left fl ">对应栏目：</div>
                <div class="bzep_right fl" id="divdiv">

  
                </div>
                <div class="fl">
                    <div class="add on"></div>
                </div>
            </div>
            <div class="bz-every-part clearfix margt_20 bz-footer">
                <!--bzep:bz-every-part-->
                <!--zb:zhibiao(指标)-->
                <div class="bzep_left fl "></div>
                <div class="bzep_right fl">
                    <div class="fl saveNext">保存</div>
                    <div class="fl cancle" data-dismiss="modal" aria-hidden="true" >取消</div>
                </div>
            </div>

    </div>
   <!-- <div class="unload-box"></div>-->
    <!--modal(添加)-->
    <div id="add_box" style="width:600px; left: 50%; top:7%; margin-left: -300px;">
        <div class="bz-head op_w-head clearfix" style="position:relative;">
            <div type="button" class="close green_head_closeicon" id="lm_close">
            </div>
            <div style="position:absolute; right:75px; top:0; font-size:12px; font-family：微软雅黑; margin-top:22px;">注:若无对应栏则填写网站名称和首页地址</div>
            <h4 class="fl" id="myModalLabel">
                添加栏目
            </h4>
        </div>
        
        
         <form method="post" action="upload_fileUpload.action">
        <div class="bz-content">
            <div class="everyTip clearfix">
                <div class="fl left_title">
                   <i style="color:red;display: inline-block;margin-right: 5px; position: relative;top: 3px;">*</i>  栏目名称：
                </div>
                <div class="fl column-address">
                    <input type="text" id="channelName" placeholder="请输入栏目名称"/>
                </div>
            </div>
            <div class="everyTip clearfix">
                <div class="fl left_title">
                    <i style="color:red;display: inline-block;margin-right: 5px; position: relative;top: 3px;">*</i> 栏目地址：
                </div>
                <div class="fl column-address">
                    <input type="text" id="channelUrl" placeholder="请输入栏目地址"/>
                    <input type="hidden" id="addOrUpdate"/>
                    <input type="hidden" id="paRatingChannelId"/>
                </div>
                 <div class="fl link-test text-box" onclick="onLineUrl()">
                    连通性测试
                </div>
            </div>

            <div>
<!-- 			<INPUT type="hidden" name="content1" id="content1"  style="width: 420px">
			<IFRAME ID="eWebEditor1" src="ewebeditor/ewebeditor.htm?id=content1&style=coolblue" frameborder="0" scrolling="no" width="550" height="300"></IFRAME>
             -->
            
             <textarea name="editor" id="editor" cols="100" rows="20" style="width:550px;height:300px;"></textarea>
             </div>
        </div>
            </form>
        <div class="sd-footer clearfix">
            <div class="ensure fr" onclick="addOrUpdateChannel()">确定</div>
        </div>
    </div>
    <div class="lm_bg"></div>
    <!--/modal(添加)-->
</div>
<!-- /Modal -->
<%-- <script type="text/javascript" src="<%=path%>/uEditor/ueditor.config.js"></script>
<script type="text/javascript" src="<%=path%>/uEditor/ueditor.all.js"> </script>
<script type="text/javascript" src="<%=path%>/uEditor/lang/zh-cn/zh-cn.js"></script> --%>
<script type="text/javascript" src="<%=path%>/kindeditor/kindeditor-all.js"></script>
<script type="text/javascript" src="<%=path%>/js/paRatingDetail/paRatingDetailInfo.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.tips.js"></script>


</body>
</html>

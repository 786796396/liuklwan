<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>安全扫描-2016-09-21</title>
<%@ include file="/common/meta.jsp"%>
    <%@ include file="/common/heade.jsp"%>
<!-- Le styles -->
<link rel="stylesheet" type="text/css" href="<%= path %>/css/SafetyScanning.css"/>
</head>

<body>
<p id="backToTop" style="display:none;">
    <a title="回到顶部" href="javascript:void(0);">
        <img src="<%= path %>/images/common/top-hover.png"/>
        <img class="top-img" src="<%= path %>/images/common/top.png"/>
    </a>
</p>
<%@ include file="/common/top.jsp"%>
<div class="main-container container" >
	<div class="row-fluid" style="background: #fff;">
	<!--左侧导航       satrt  -->
	<c:set var="ba_index" value="30" scope="request"/>
	<c:set var="menu" value="#menuAqwt" scope="request"/>
	<%@ include file="/common/leftmenu.jsp"%>
	
        <div class="page-content safe-scan" style="background: #fff;">
            <!--需要填充的内容开始-->
            <div class="title">
                成都市网站安全扫描
            </div>
            <div class="row color-part_box">
                <div class="databox color-part">
                    <a class="bg-603cba color_box" href="#">
                        <span class="num1"><span>17</span>次</span>
                        <span class="num1_msg">
                            SQL注入漏洞、应用漏洞<br/>
                            XSS跨站脚本漏洞、后门
                        </span>
                        <span class="databox-bom">网站脆弱性监测</span>
                    </a>
                </div>
                <div class="databox color-part">
                    <a class="bg-eb3c00 color_box" href="#">
                        <span class="num1"><span>0</span>次</span>
                        <span class="num1_msg">挂码监测</span>
                        <span class="databox-bom">网站挂码监测</span>
                    </a>
                </div>
                <div class="databox color-part">
                    <a class="bg-fda931 color_box" href="#">
                        <span class="num1"><span>0</span>次</span>
                        <span class="num1_msg">网站篡改</span>
                        <span class="databox-bom">变更/篡改监测</span>
                    </a>
                </div>
                <div class="databox color-part">
                    <a class="bg-009788 color_box" href="#">
                        <span class="num1"><span>0</span>次</span>
                        <span class="num1_msg">被嵌入网游、色情、赌博等暗链地址</span>
                        <span class="databox-bom">网站暗链监测</span>
                    </a>
                </div>
                <div class="databox color-part">
                    <a class="bg-9f00a7 color_box" href="#">
                        <span class="num1"><span>0</span>次</span>
                        <span class="num1_msg">文件泄露、内容泄露</span>
                        <span class="databox-bom">内容泄露监测</span>
                    </a>
                </div>
            </div>
            <!--ss:SafeScan-->
            <div class="ss_table_part">
                <div class="first evert_table on">
                    <div class="nav_part clearfix">
                        <div class="every_tit fl on">SQL注入漏洞 <span>（0）</span></div>
                        <div class="every_tit fl">XSS脚本漏洞<span>（1）</span></div>
                        <div class="every_tit fl">CGI漏洞<span>（0）</span></div>
                        <div class="every_tit fl">CSRF漏洞<span>（0）</span></div>
                        <div class="every_tit fl">应用漏洞<span>（16）</span></div>
                        <div class="every_tit fl">表单破解<span>（0）</span></div>
                    </div>
                    <div class="table_group">
                        <div class="every_table_box active">
                            <table>
                                <thead>
                                    <tr>
                                        <th style="width: 10%;">网站标识码</th>
                                        <th>网站名称</th>
                                        <th>问题URL</th>
                                        <th>LEVEL</th>
                                        <th>TYPE</th>
                                        <th>METHOD</th>
                                        <th>TIME</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td colspan="7">无信息</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="every_table_box">
                            <table>
                                <thead>
                                <tr>
                                    <th style="width: 10%;">网站标识码</th>
                                    <th>网站名称</th>
                                    <th>问题URL</th>
                                    <th>LEVEL</th>
                                    <th>TYPE</th>
                                    <th>METHOD</th>
                                    <th>PARAM</th>
                                    <th>DATE</th>
                                    <th>TIME</th>
                                </tr>
                                </thead>
                                <tbody>
                                <!--<tr>
                                    <td colspan="7">无信息</td>
                                </tr>-->
                                    <tr>
                                        <td style="width: 10%;">5101050004</td>
                                        <td>青羊区公众信息网</td>
                                        <td>http://www.cdqingyang.gov.cn/index.php</td>
                                        <td>-</td>
                                        <td>-</td>
                                        <td>GET</td>
                                        <td>keyword</td>
                                        <td>-</td>
                                        <td>2016-09-18 14:26:00</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="every_table_box">
                            <table>
                                <thead>
                                <tr>
                                    <th style="width: 10%;">网站标识码</th>
                                    <th>网站名称</th>
                                    <th>问题URL</th>
                                    <th>LEVEL</th>
                                    <th>TYPE</th>
                                    <th>METHOD</th>
                                    <th>PARAM</th>
                                    <th>DATE</th>
                                    <th>TIME</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td colspan="7">无信息</td>
                                </tr>
                               <!-- <tr>
                                    <td style="width: 10%;">5101050004</td>
                                    <td>青羊区公众信息网</td>
                                    <td>http://www.cdqingyang.gov.cn/index.php</td>
                                    <td>-</td>
                                    <td>-</td>
                                    <td>GET</td>
                                    <td>keyword</td>
                                    <td>-</td>
                                    <td>2016-09-18 14:26:00</td>
                                </tr>-->
                                </tbody>
                            </table>
                        </div>
                        <div class="every_table_box">
                            <table>
                                <thead>
                                <tr>
                                    <th style="width: 10%;">网站标识码</th>
                                    <th>网站名称</th>
                                    <th>问题URL</th>
                                    <th>LEVEL</th>
                                    <th>TYPE</th>
                                    <th>METHOD</th>
                                    <th>PARAM</th>
                                    <th>DATE</th>
                                    <th>TIME</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td colspan="7">无信息</td>
                                </tr>
                                <!-- <tr>
                                     <td style="width: 10%;">5101050004</td>
                                     <td>青羊区公众信息网</td>
                                     <td>http://www.cdqingyang.gov.cn/index.php</td>
                                     <td>-</td>
                                     <td>-</td>
                                     <td>GET</td>
                                     <td>keyword</td>
                                     <td>-</td>
                                     <td>2016-09-18 14:26:00</td>
                                 </tr>-->
                                </tbody>
                            </table>
                        </div>
                        <div class="every_table_box">
                            <table class="loud">
                                <thead>
                                <tr>
                                    <th style="width: 10%;">网站标识码</th>
                                    <th class="website_name">网站名称</th>
                                    <th class="url">问题URL</th>
                                    <th>LEVEL</th>
                                    <th class="type">TYPE</th>
                                    <th>KEY</th>
                                    <th>TIME</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td style="width: 10%;">
                                            5101040001
                                        </td>
                                        <td class="website_name">锦江区公众信息网</td>
                                        <td class="url">http://www.cdjinjiang.gov.cn/
                                        </td>
                                        <td>high</td>
                                        <td>
                                            配置风险
                                        </td>
                                        <td class="key">会话Cookie中缺少HttpOnly属性</td>
                                        <td>2016-09-20 14:31:49</td>
                                    </tr>
                                    <tr>
                                        <td style="width: 10%;">
                                            5101040001
                                        </td>
                                        <td class="website_name">锦江区公众信息网</td>
                                        <td class="url">http://www.cdjinjiang.gov.cn/
                                        </td>
                                        <td>high</td>
                                        <td>
                                            信息泄露
                                        </td>
                                        <td class="key">Apache httpOnly Cookie泄漏</td>
                                        <td>2016-09-20 14:31:49</td>
                                    </tr>
                                     <tr>
                                         <td style="width: 10%;">5101050004</td>
                                         <td class="website_name">青羊区公众信息网</td>
                                         <td class="url">http://www.cdqingyang.gov.cn/phpmyadmin/</td>
                                         <td>high</td>
                                         <td>
                                             信息泄露
                                         </td>
                                         <td class="key">电子邮件地址泄漏(外域)</td>
                                         <td>2016-09-20 14:58:51</td>
                                     </tr>

                                    <tr>
                                        <td style="width: 10%;">5101050004</td>
                                        <td class="website_name">青羊区公众信息网</td>
                                        <td class="url">http://www.cdqingyang.gov.cn/web.config</td>
                                        <td>high</td>
                                        <td>
                                            信息泄露
                                        </td>
                                        <td class="key">bashrc 信息泄漏</td>
                                        <td>2016-09-20 14:58:51</td>
                                    </tr>

                                    <tr>
                                        <td style="width: 10%;">5101050004</td>
                                        <td class="website_name">青羊区公众信息网</td>
                                        <td class="url">http://www.cdqingyang.gov.cn/Public/front/js/jquery.js</td>
                                        <td>high</td>
                                        <td>
                                            配置风险
                                        </td>
                                        <td class="key">允许Flash文件与任何域HTML页面通信</td>
                                        <td>2016-09-20 14:58:51</td>
                                    </tr>

                                    <tr>
                                        <td style="width: 10%;">5101050004</td>
                                        <td class="website_name">青羊区公众信息网</td>
                                        <td class="url">http://www.cdqingyang.gov.cn/Public/front/js/jquery-1.7.2.js</td>
                                        <td>high</td>
                                        <td>
                                            配置风险
                                        </td>
                                        <td class="key">发现敏感目录</td>
                                        <td>2016-09-20 14:58:51</td>
                                    </tr>

                                    <tr>
                                        <td style="width: 10%;">5101050004</td>
                                        <td class="website_name">青羊区公众信息网</td>
                                        <td class="url">http://www.cdqingyang.gov.cn/Assistants/js/jqu</td>
                                        <td>high</td>
                                        <td>
                                            配置风险
                                        </td>
                                        <td class="key">X-Frame-Options Header未配置</td>
                                        <td>2016-09-20 14:58:51</td>
                                    </tr>
                                   <tr>
                                        <td style="width: 10%;">5101050004</td>
                                        <td class="website_name">青羊区公众信息网</td>
                                        <td class="url">http://www.cdqingyang.gov.cn/Public/front/js/jquery-1.4.1.min.js</td>
                                        <td>high</td>
                                        <td>
                                            配置风险
                                        </td>
                                        <td class="key">发现敏感目录</td>
                                        <td>2016-09-20 14:58:51</td>
                                    </tr>
                                    <tr>
                                        <td style="width: 10%;">5101050004</td>
                                        <td class="website_name">青羊区公众信息网</td>
                                        <td class="url">http://www.cdqingyang.gov.cn/server-status</td>
                                        <td>high</td>
                                        <td>
                                            信息泄露
                                        </td>
                                        <td class="key">电子邮件地址泄漏(外域)</td>
                                        <td>2016-09-20 14:58:51</td>
                                    </tr>

                                    <tr>
                                        <td style="width: 10%;">5101050004</td>
                                        <td class="website_name">青羊区公众信息网</td>
                                        <td class="url">http://www.cdqingyang.gov.cn/icons/</td>
                                        <td>high</td>
                                        <td>
                                            信息泄露
                                        </td>
                                        <td class="key">存在风险的javascript库</td>
                                        <td>2016-09-20 14:58:51</td>
                                    </tr>

                                    <tr>
                                        <td style="width: 10%;">5101050004</td>
                                        <td class="website_name">青羊区公众信息网</td>
                                        <td class="url">http://www.cdqingyang.gov.cn/bMMJrZltpT</td>
                                        <td>high</td>
                                        <td>
                                            信息泄露
                                        </td>
                                        <td class="key">发现敏感目录</td>
                                        <td>2016-09-20 14:58:51</td>
                                    </tr>

                                    <tr>
                                        <td style="width: 10%;">5101050004</td>
                                        <td class="website_name">青羊区公众信息网</td>
                                        <td class="url">http://www.cdqingyang.gov.cn/</td>
                                        <td>high</td>
                                        <td>
                                            配置风险
                                        </td>
                                        <td class="key">电子邮件地址泄漏(外域)</td>
                                        <td>2016-09-20 14:58:51</td>
                                    </tr>

                                    <tr>
                                        <td style="width: 10%;">5101000008</td>
                                        <td class="website_name">成都市国土资源局门户网</td>
                                        <td class="url">http://www.cdlr.gov.cn/Javascript/Jquery/jquery-1.4.1.mis.js</td>
                                        <td>high</td>
                                        <td>
                                            配置风险
                                        </td>
                                        <td class="key">存在风险的javascript库</td>
                                        <td>2016-09-20 14:58:51</td>
                                    </tr>

                                    <tr>
                                        <td style="width: 10%;">5101000009</td>
                                        <td class="website_name">成都市国有资产监督管理委员会网站</td>
                                        <td class="url">http://www.cdgzw.gov.cn/</td>
                                        <td>high</td>
                                        <td>
                                            配置风险
                                        </td>
                                        <td class="key">会话Cookie中缺少HttpOnly属性</td>
                                        <td>2016-09-20 15:04:20</td>
                                    </tr>

                                    <tr>
                                        <td style="width: 10%;">5101000028</td>
                                        <td class="website_name">成都市人民政府门户网站</td>
                                        <td class="url">http://www.chengdu.gov.cn/portals/static/js/specialporting/meeting2013/jquery-1.4.3.min.js</td>
                                        <td>high</td>
                                        <td>
                                            配置风险
                                        </td>
                                        <td class="key">存在风险的javascript库</td>
                                        <td>2016-09-20 15:04:20</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="every_table_box">
                            <table>
                                <thead>
                                <tr>
                                    <th style="width: 10%;">网站标识码</th>
                                    <th>网站名称</th>
                                    <th>问题URL</th>
                                    <th>LEVEL</th>
                                    <th>TYPE</th>
                                    <th>METHOD</th>
                                    <th>PARAM</th>
                                   <!-- <th>DATE</th>-->
                                    <th>TIME</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td colspan="7">无信息</td>
                                </tr>
                                <!-- <tr>
                                     <td style="width: 10%;">5101050004</td>
                                     <td>青羊区公众信息网</td>
                                     <td>http://www.cdqingyang.gov.cn/index.php</td>
                                     <td>-</td>
                                     <td>-</td>
                                     <td>GET</td>
                                     <td>keyword</td>
                                     <td>-</td>
                                     <td>2016-09-18 14:26:00</td>
                                 </tr>-->
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="second evert_table">
                    <div class="nav_part clearfix">
                        <div class="every_tit fl on">网站挂马（0）</div>
                    </div>
                    <div class="table_group">
                        <div class="every_table_box active">
                            <table>
                                <thead>
                                <tr>
                                    <th style="width: 10%;">网站标识码</th>
                                    <th>网站名称</th>
                                    <th>问题URL</th>
                                    <th>LEVEL</th>
                                    <th>TYPE</th>
                                    <th>METHOD</th>
                                    <th>PARAM</th>
                                    <!-- <th>DATE</th>-->
                                    <th>TIME</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td colspan="8">无信息</td>
                                    </tr>
                                   <!-- <tr>
                                        <td style="width: 10%;">
                                            5101040001
                                        </td>
                                        <td class="website_name">锦江区公众信息网</td>
                                        <td class="url">http://www.cdjinjiang.gov.cn/
                                        </td>
                                        <td>high</td>
                                        <td>
                                            配置风险
                                        </td>
                                        <td>GET</td>
                                        <td>keyword</td>
                                        <td>2016-09-20 14:31:49</td>
                                    </tr>-->
                                <!-- <tr>
                                     <td style="width: 10%;">5101050004</td>
                                     <td>青羊区公众信息网</td>
                                     <td>http://www.cdqingyang.gov.cn/index.php</td>
                                     <td>-</td>
                                     <td>-</td>
                                     <td>GET</td>
                                     <td>keyword</td>
                                     <td>-</td>
                                     <td>2016-09-18 14:26:00</td>
                                 </tr>-->
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="third evert_table">
                    <div class="nav_part clearfix">
                        <div class="every_tit fl on">安全篡改（0）</div>
                    </div>
                    <div class="table_group">
                        <div class="every_table_box active">
                            <table>
                                <thead>
                                <tr>
                                    <th style="width: 10%;">网站标识码</th>
                                    <th class="website_name" style="width: 20%;">网站名称</th>
                                    <th class="url" style="width: 20%;">问题URL</th>
                                    <th style="width: 20%;">TIME</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td colspan="4">无信息</td>
                                </tr>
                               <!-- <tr>
                                    <td style="width: 10%;">
                                        5101040001
                                    </td>
                                    <td class="website_name" style="width: 20%;">锦江区公众信息网</td>
                                    <td class="url" style="width: 20%;">http://www.cdjinjiang.gov.cn/
                                    </td>
                                    <td style="width: 20%;">2016-09-20 14:31:49</td>
                                </tr>-->
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="fouth evert_table">
                    <div class="nav_part clearfix">
                        <div class="every_tit fl on">网站暗链（0）</div>
                    </div>
                    <div class="table_group">
                        <div class="every_table_box active">
                            <table>
                                <thead>
                                <tr>
                                    <th style="width: 10%;">网站标识码</th>
                                    <th class="website_name" style="width: 20%;">网站名称</th>
                                    <th class="url" style="width: 20%;">问题URL</th>
                                    <th style="width: 20%;">LEVEL</th>
                                    <th style="width: 20%;">TIME</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td colspan="5">无信息</td>
                                </tr>
                                <!--<tr>
                                    <td style="width: 10%;">
                                        5101040001
                                    </td>
                                    <td class="website_name" style="width: 20%;">锦江区公众信息网</td>
                                    <td class="url" style="width: 20%;">http://www.cdjinjiang.gov.cn/
                                    </td>
                                    <td style="width: 20%;">high
                                    </td>
                                    <td style="width: 20%;">2016-09-20 14:31:49</td>
                                </tr>-->
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="fifth evert_table">
                    <div class="nav_part clearfix">
                        <div class="every_tit fl on">文件泄露（0)</div>
                        <div class="every_tit fl">内容泄露（0）</div>
                    </div>
                    <div class="table_group">
                        <div class="every_table_box active">
                            <table>
                                <thead>
                                <tr>
                                    <th style="width: 10%;">网站标识码</th>
                                    <th class="website_name" style="width: 20%;">网站名称</th>
                                    <th class="url" style="width: 20%;">问题URL</th>
                                    <th style="width: 20%;">LEVEL</th>
                                    <th>MATCH_STR</th>
                                    <th style="width: 10%;">TIME</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td colspan="6">无信息</td>
                                </tr>
                                <!--<tr>
                                    <td style="width: 10%;">
                                        5101040001
                                    </td>
                                    <td class="website_name" style="width: 20%;">锦江区公众信息网</td>
                                    <td class="url" style="width: 20%;">http://www.cdjinjiang.gov.cn/
                                    </td>
                                    <td style="width: 20%;">high
                                    </td>
                                    <td>MATCH_STR</td>
                                    <td style="width: 20%;">2016-09-20 14:31:49</td>
                                </tr>-->
                                </tbody>
                            </table>
                        </div>
                        <div class="every_table_box">
                            <table>
                                <thead>
                                <tr>
                                    <th style="width: 10%;">网站标识码</th>
                                    <th class="website_name" style="width: 20%;">网站名称</th>
                                    <th class="url" style="width: 20%;">问题URL</th>
                                    <th style="width: 20%;">LEVEL</th>
                                    <th>MATCH_STR</th>
                                    <th style="width: 10%;">TIME</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td colspan="6">无信息</td>
                                </tr>
                               <!-- <tr>
                                    <td style="width: 10%;">
                                        51010400088
                                    </td>
                                    <td class="website_name" style="width: 20%;">锦江区公众信息网</td>
                                    <td class="url" style="width: 20%;">http://www.cdjinjiang.gov.cn/
                                    </td>
                                    <td style="width: 20%;">high
                                    </td>
                                    <td>MATCH_STR</td>
                                    <td style="width: 20%;">2016-09-20 14:31:49</td>
                                </tr>-->
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <!--需要填充的内容结束-->
            <%@ include file="/common/footer.jsp"%>
            <!-- /page-footer -->
        </div><!-- /page-content -->
    </div>
</div> <!-- /container -->


<script language="javascript" type="text/javascript">
$(function(){
    /*该页面用到的js*/
    $(".every_table_box table tr:even").css("background","#f7faff");
   /* $(".baobei_table_part table tbody tr:odd").hover(function(){
        $(this).css("background","#b5f0dd");
    },function(){
        $(this).css("background","");
    });

    $(".baobei_table_part table tbody tr:even").hover(function(){
        $(this).css("background","#b5f0dd");
    },function(){
        $(this).css("background","#f7faff");
    });*/

	$(window).scroll(function () {
		if ($(window).scrollTop() > 100) {
			$("#backToTop").fadeIn(500);
		} else {
			$("#backToTop").fadeOut(500);
		}
	});
	$("#backToTop").click(function () {
		$('body,html').animate({scrollTop: 0}, 600);
		return false;
	});

    $('.color-part').click(function(){
        var n=$(this).index();
        $('.evert_table').removeClass('on');
        $('.evert_table').eq(n).addClass('on');
    });

    if($('.evert_table').hasClass('on')==1){
        $('.nav_part .every_tit').click(function(){
            $(this).parent().find('.every_tit').removeClass('on');
            /*$('.nav_part .every_tit').removeClass('on');*/
            $(this).addClass('on');
            var n=$(this).index();
            /*$('.table_group .every_table_box').removeClass('active');*/
            $(this).parent().next().find('.every_table_box').removeClass('active');

            /*$('.table_group .every_table_box').eq(n).addClass('active');*/
            $(this).parent().next().find('.every_table_box').eq(n).addClass('active');
        });
    }


})
</script>
</body>
</html>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
</head>
<body>
	<table cellpadding="0" cellspacing="0" width="100%" style="background:#fff;">
	<tr>
    	<td>
            <table cellpadding="0" cellspacing="0" width="650" style="background:#fff; border:1px solid #e5e7e9; margin:30px auto 0px;">
                <tr>
                	<td>
                    	<table cellpadding="0" cellspacing="0" width="100%" height="71" style=" border-top:5px solid #ff0303; border-bottom:1px solid #d4d6db;">
                        	<tr>
                                <td style="padding-left:26px;"><a href="http://www.boxpro.cn/"><img alt="logo" src="https://www.kaipuyun.cn/cloud_web/images/logo.png"/></a></td>
                                <td style="text-align:right; padding-right:50px;"><img alt="网站告警信息" src="https://www.kaipuyun.cn/cloud_web/images/yuj-wz-header.png"/></td>
                            </tr>
                        </table> 
                    </td>
                </tr>
                <tr>
                	<td>
                    	<table cellpadding="0" cellspacing="0" width="592" style="margin:0px auto 23px;">
                            <tr>
                            	<td style="padding:29px 0px 3px 0px; font-size:18px; color:#383b4d; font-family:'微软雅黑';font-weight:bold;">尊敬的客户，您好：</td>
                            </tr>
                            <tr>
                                <td style="padding:15px 10px 10px 40px; color:#696c7e; FONT-SIZE: 14px; font-family:'宋体'; line-height:28px;">
                                	<p style="margin:0px;color:#383b4d;font-family:'微软雅黑'">${scanTime}, <span style="color:#0cbbef;">${siteCodeName}</span>的网站监测结果概述如下：</p>
                                </td>
                            </tr>
                        </table>
                    </td>

                </tr>
                <tr>
                    <td>
                        <table cellpadding="0" cellspacing="0" width="517" style="border:1px solid #f0f0f0; background:#f8fbfc; margin:0px auto 32px; text-align:left;">
                            <tr>
                                <td style="font-size:14px; color:#383b4d; font-family:'Microsoft YaHei'; padding:15px 0 15px 15px;">
                    <p><span style="color:#000;font-weight: 900">单位名称：</span>${siteCodeName}</p>
                    <p><span style="color:#000;font-weight: 900">组织单位编码：</span>${siteCode}</p>
                    <p><span style="color:#000;font-weight: 900">涵盖站点：</span>${totalNum}个</p>
                    <p><span style="color:#000;font-weight: 900">全面监测站点：</span>${scanNum}个</p>
				    <p><span style="color:#000;font-weight: 900">监测异常站点（持续多日访问异常）：</span>${notScanNum}个</p>
				    <p style="padding-right:16px;line-height: 22px;"><span style="color:#000;font-weight: 900">网站连通性：</span>${notConnectionNum}个站点首页${notConnectionSum}次连不通；${notConnectionPer7}个站点首页7日连不通率超3%；7日总体连不通率为${notConnectionPer}</p>
				    <p style="padding-right:16px;line-height: 22px;"><span style="color:#000;font-weight: 900">首页不可用链接：</span>${linkHomeNum}个站点首页${linkHomeSum}条链接不可用</p>
                    <p><span style="color:#000;font-weight: 900">内容更新量：</span>${updateNum}个站点总更新${updateAvgNum}条</p>
                    <p style="padding-right:16px;line-height: 22px;"><span style="color:#000;font-weight: 900">内容保障问题：</span>${notUpdateHome}个站点首页更新不及时；${noUpdateChannelNum}个站点${noUpdateChannelSum}个栏目更新不及时</p>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table cellpadding="0" cellspacing="0" width="517" style="border-top:1px solid #f0f0f0; margin:7px auto 14px;">
                            <tr>
                                <td width="341" style="font-size:12px; color:#8c8e9b; font-family:'宋体'; padding-top:21px;">
                                    <p>开普云监管平台（https://jg.kaipuyun.cn）</p>
				    <p>扫描右边的二维码关注“开普云”微信公众号，</p>
				    <p>绑定开普云监管账号，即可通过微信接收网站预警</p>
                                    <p>更多网站预警服务，请联系我们的销售团队：4000-976-005</p>
                                </td>
                                <td style="text-align:right; padding-top:24px;">
                                    <img alt="code" src="https://jg.kaipuyun.cn/cloud_web/images/code.png"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<table cellpadding="0" cellspacing="0" height="36" width="100%" style="background:#f3f8fa; border-top:1px solid #dce2e8; text-align:center; font-size:12px; color:#8a8a8a;">
                        	<tr>
                            	<td>
                                    <p style="margin:0px;"><span style="font-family:'微软雅黑';">©</span> Copyright 2016 开普互联版权所有</p>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
    	</td>
    </tr>
    <tr><td height="36"></td></tr>
</table>
</body>
</html>
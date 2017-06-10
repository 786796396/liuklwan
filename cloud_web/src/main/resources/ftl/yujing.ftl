<table cellpadding="0" cellspacing="0" width="100%" style="background:#fff;">
	<tr>
    	<td>
            <table cellpadding="0" cellspacing="0" width="650" style="background:#fff; border:1px solid #e5e7e9; margin:30px auto 0px;">
                <tr>
                	<td>
                    	<table cellpadding="0" cellspacing="0" width="100%" height="71" style=" border-top:5px solid #ff0303; border-bottom:1px solid #d4d6db;">
                        	<tr>
                                <td style="padding-left:26px;"><a href="http://jg.kaipuyun.cn/"><img alt="logo" src="https://www.kaipuyun.cn/images/mail/jg_logo.png"/></a></td>
                                <td style="text-align:right; padding-right:50px;"><img alt="预警信息汇总" src="https://www.kaipuyun.cn/images/mail/yuj-header.png"/></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<table cellpadding="0" cellspacing="0" width="592" style="margin:0px auto 23px;">
                            <tr>
                            	<td style="padding:29px 0px 3px 0px; font-size:18px; color:#383b4d; font-family:'微软雅黑';">尊敬的客户，您好：</td>
                            </tr>
                            <tr>
                                <td style="padding:15px 10px 10px 40px; color:#696c7e; FONT-SIZE: 14px; font-family:'宋体'; line-height:28px;">
                                	<p style="margin:0px; text-indent:26px;">开普云监管平台预警信息已经实时通过短信、微信发送给您，本邮件是预警记录汇总。汇总列表如下：
                                	</p>
                                </td>
                            </tr>
                        </table>
                    </td>

                </tr>
                <tr>
                    <td>
                        <table cellpadding="0" cellspacing="0" width="517" style="border:1px solid #f0f0f0; background:#f8fbfc; margin:0px auto 10px; text-align:left;">
                            <tr>
                                <td style="font-size:14px; color:#485069; font-family:'宋体'; padding:10px 0px 10px 35px;">
                                    <p style="margin-top:0px;">网站名称：<span style="color:#969696">${siteName}</span></p>
                                    <p>网站主办单位：<span style="color:#969696">${director}</span></p>
                                    <p>网站地址：<span style="color:#4F81BD">${url}</span></p>
                                    <p>网站标识码：<span style="color:#969696">${siteCode}</span></p>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
	                <td  style="padding:0px 10px 10px 40px; color:#696c7e; FONT-SIZE: 14px; font-family:'宋体'; line-height:28px;">
	                	<p style="margin:0px; text-indent:26px; font-weight: bold; color: #333;">预警时间：${nowTime}0点-24点
	                </td>
                </tr>
                <tr>
                    <td>
                        <table cellpadding="0" cellspacing="0" width="517" style="margin:0px auto 32px; text-align:left;">
                            <tr>
                                <td style="font-size:14px; color:#485069; font-family:'宋体';">
                                	<table width="517" cellpadding="0" cellspacing="0" width="517" style="border:1px solid #f0f0f0; background:#f8fbfc; margin:0px auto 32px; text-align:left;">
                                		<tr>
	                                		<td style="font-size:14px; font-weight: bold; color:#485069; font-family:'宋体'; padding:10px 0px 10px 35px;border-bottom: 1px solid rgb(221, 221, 221);">预警类别</td>
	                                		<td style="font-size:14px; font-weight: bold; color:#485069; font-family:'宋体'; padding:10px 0px 10px 35px;border-bottom: 1px solid rgb(221, 221, 221);">预警次数</td>
                                		</tr>
	                                    <#list edList as ed>
	                                    	<tr>
	                                    		<td style="font-size:14px; color:red; font-family:'宋体'; padding:10px 0px 10px 35px;border-bottom: 1px solid rgb(221, 221, 221);">${ed.earlyType}</td>
	                                    		<td style="font-size:14px; color:#485069; font-family:'宋体'; padding:10px 0px 10px 35px;border-bottom: 1px solid rgb(221, 221, 221);">${ed.earlyNum}</td>
	                                    	</tr>
	                                    </#list>
	                                    <tr>
	                                    	<td colspan="2" style="font-size:14px; color:#485069; font-family:'宋体'; padding:10px 0px 10px 35px;"><p style="margin-top:20px; text-align:center;">
	                                    		<span style="color:#00B0F0;"><a href="http://jg.kaipuyun.cn">点击登录查看详情</a></span></p>
	                                    	</td>
	                                    </tr>
                                    </table>
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
                                    <p>开普云监管平台（http://jg.kaipuyun.cn）</p>
                                    <p>如需帮助，请联系客服：4000-976-005</p>
                                </td>
                                <td style="text-align:right; padding-top:24px;">
                                    <img alt="code" src="https://www.kaipuyun.cn/images/mail/code.png"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                	<td>
                    	<table cellpadding="0" cellspacing="0" height="36" width="100%" style="text-align:center; font-size:12px; color:#8a8a8a;">
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
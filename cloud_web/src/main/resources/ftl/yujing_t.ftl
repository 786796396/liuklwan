<table cellpadding="0" cellspacing="0" width="100%" style="background:#fff;">
	<tr>
    	<td>
            <table cellpadding="0" cellspacing="0" width="650" style="background:#fff; border:1px solid #e5e7e9; margin:30px auto 0px;">
                <tr>
                	<td>
                    	<table cellpadding="0" cellspacing="0" width="100%" height="71" style=" border-top:5px solid #ff0303; border-bottom:1px solid #d4d6db;">
                        	<tr>
                                <td style="padding-left:26px;"><a href="http://www.boxpro.cn/"><img alt="logo" src="http://120.26.192.225/cloud_web/images/logo.png"/></a></td>
                                <td style="text-align:right; padding-right:50px;"><img alt="网站告警信息" src="http://120.26.192.225/cloud_web/images/yuj-wz-header.png"/></td>
                            </tr>
                        </table> 
                    </td>
                </tr>
                <tr>
                	<td>
                    	<table cellpadding="0" cellspacing="0" width="592" style="margin:0px auto 23px;">
                            <tr>
                            	<td style="padding:29px 0px 3px 0px; font-size:18px; color:#383b4d; font-family:'微软雅黑';">您好：</td>
                            </tr>
                            <tr>
                                <td style="padding:15px 10px 10px 40px; color:#696c7e; FONT-SIZE: 14px; font-family:'宋体'; line-height:28px;">
                                	<p style="margin:0px; text-indent:26px;">截止到${nowTime}，开普互联云监测平台监测到${newEarlNum}个预警信息，请及时处理。详情如下：</p>
                                </td>
                            </tr>
                        </table>
                    </td>

                </tr>
              <#list edList as ed>
                <tr>
                    <td>
                        <table cellpadding="0" cellspacing="0" width="517" style="border:1px solid #f0f0f0; background:#f8fbfc; margin:0px auto 32px; text-align:left;">
                            <tr>
                                <td style="font-size:14px; color:#485069; font-family:'宋体'; padding-left:35px;">
                                    <p>问题类型：<span style="color:#ff0303;">${ed.questionName}</span></p>
                                    <p>问题描述：${ed.queDescribe!'无'}</p>
                                    <p>发现时间：${ed.scanTime!'无'}</p>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
              </#list>
                <tr>
                    <td>
                        <table cellpadding="0" cellspacing="0" width="517" style="border-top:1px solid #f0f0f0; margin:7px auto 14px;">
                            <tr>
                                <td width="341" style="font-size:12px; color:#8c8e9b; font-family:'宋体'; padding-top:21px;">
                                    <p>开普云监测平台（http://ce.kaipuyun.cn）</p>
                                    <p>如需帮助，请联系客服：4000-976-005</p>
                                </td>
                                <td style="text-align:right; padding-top:24px;">
                                    <img alt="code" src="http://120.26.192.225/cloud_web/images/code.png"/>
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
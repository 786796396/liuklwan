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
                            	<td style="padding:29px 0px 3px 0px; font-size:18px; color:#383b4d; font-family:'微软雅黑';">尊敬的客户，您好：</td>
                            </tr>
                            <tr>
                                <td style="padding:15px 10px 10px 40px; color:#696c7e; FONT-SIZE: 14px; font-family:'宋体'; line-height:28px;">
                                	<p style="margin:0px; text-indent:26px;">截止到${scanTime}，开普互联云监测平台监测到${newEarlNum}个预警信息，请及时处理。详情如下：</p>
                                </td>
                            </tr>
                        </table>
                    </td>

                </tr>
		<#list editList as ed>
                <tr>
                    <td>
                        <table cellpadding="0" cellspacing="0" width="517" style="border:1px solid #f0f0f0; background:#f8fbfc; margin:0px auto 32px; text-align:left;">
                            <tr>
                                <td style="font-size:14px; color:#485069; font-family:'宋体'; padding-left:35px;">
				    <p>网站名称：${ed.siteName}</p>
				    <p>网站标识码：${ed.siteCode}</p>
				    <p>网站地址：${ed.url}</p>
                                    <p>问题类型：<span style="color:#ff0303;">${ed.type}</span></p>
				    <p>问题描述：<span style="color:#ff0303;">${ed.updateGradeType}</span></p>
                                    <p>检测时间：${ed.scanDate}</p>
				    <p> 各位领导请注意，凡是临时关停维护，升级，改版的情况，除了向上级主管部门做报备外，</p>
				    <p>请务必在报送系统中提交临时关停申请，并“由组织单位审核通过方可生效”。否则，在各类抽查检查中，均视为正常站点无法访问。</p>
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
				    <p>扫描右边的二维码关注“开普云监管”微信公众号，</p>
				    <p>绑定开普云监管账号，即可通过微信接收网站预警</p>
                                    <p>更多网站预警服务，请联系我们的销售团队：4000-976-005</p>
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
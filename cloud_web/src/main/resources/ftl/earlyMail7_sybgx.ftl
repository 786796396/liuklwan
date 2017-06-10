<table cellpadding="0" cellspacing="0" width="100%" style="background:#fff;">
	<tbody>
		<tr>
			<td>
				<table cellpadding="0" cellspacing="0" width="650" style="background:#fff;border:1px solid #e5e7e9;margin:30px auto 0px;">
					<tbody>
						<tr>
							<td>
								<table cellpadding="0" cellspacing="0" width="100%" height="71" style=" border-top:5px solid #ff0303; border-bottom:1px solid #d4d6db;">
                        	        <tr>
                                        <td style="padding-left:26px;"><a href="http://www.boxpro.cn/"><img alt="logo" src="https://jg.kaipuyun.cn/cloud_web/images/logo.png"/></a></td>
                                        <td style="text-align:right; padding-right:50px;"><img alt="网站告警信息" src="https://jg.kaipuyun.cn/cloud_web/images/yuj-wz-header.png"/></td>
                                    </tr>
                                </table>
							</td>
						</tr>
						<tr>
							<td>
								<table cellpadding="0" cellspacing="0" width="592" style="margin:0px auto 23px;">
									<tbody>
										<tr>
											<td style="padding:29px 0px 3px 0px; font-size:18px; color:#383b4d; font-family:'微软雅黑';">
												尊敬的客户，您好：
											</td>
										</tr>
										<tr>
											<td style="color:#696c7e;font-size:14px;font-family:'宋体';padding:15px 0px 3px 0px;">
												<p style="text-indent:26px;">
													截止到${scanDate}，开普互联云监测平台监测到${newEarlNum}个预警信息，请及时处理。详情如下：
												</p>
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
<#list dataList as dl>
						<tr>
							<td>
								<table cellpadding="0" cellspacing="0" width="517" style="border:1px solid #f0f0f0;background:#f8fbfc;margin:0px auto 32px;text-align:left;">
									<tbody>
										<tr>
											<td style="font-size:14px;color:#485069;font-family:'宋体';padding:15px 0 15px 35px;">
												<p>
													网站名称：${dl.siteCodeName}
												</p>
												<p>
													网站标识码：${dl.siteCode}
												</p>
												<p>
													网站地址：<a href="${dl.url}" target="_blank">${dl.url}</a>
												</p>
												<p>
													问题类型：<span style="color:#ff0303;">${dl.earlyType}</span>
												</p>
												<p>
													问题描述：<span style="color:#ff0303;">${dl.queDescribe}</span>
												</p>
												<p>
													首页最后更新时间：${dl.lastUpdateDate}
												</p>
												<p>
													监测时间：${dl.scanDate}
												</p>
												<p><a href="${dl.loginUrl}" target="_blank">点击此处登录开普云监管服务查看详情</a></p>
												<p style="padding-right:40px;">（如上述文字不能点击，请复制以下超链接至浏览器地址栏打开网页登录查看 https://www.kaipuyun.cn）</p>
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
</#list>
						<tr>
							<td>
								<table cellpadding="0" cellspacing="0" width="517" style="margin:7px auto 14px;">
									<tbody>
										<tr>
											<td width="341" style="font-size:12px;color:#8c8e9b;font-family:'宋体';">
												<p>
													开普云监管平台（https://jg.kaipuyun.cn）
												</p>
												<p>
													扫描右边的二维码关注“开普云”微信公众号，
												</p>
												<p>
													绑定开普云监管账号，即可通过微信接收网站预警
												</p>
												<p>
													更多网站预警服务，请联系我们的销售团队：4000-976-005
												</p>
											</td>
											<td style="text-align:right;">
												<img alt="code" src="https://jg.kaipuyun.cn/cloud_web/images/code.png" /> 
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table cellpadding="0" cellspacing="0" height="36" width="100%" style="background:#f3f8fa;text-align:center;font-size:12px;color:#8a8a8a;">
									<tbody>
										<tr>
											<td>
												<p>
													<span style="font-family:'微软雅黑';">?</span> Copyright 2016 开普互联版权所有
												</p>
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<td height="36">
			</td>
		</tr>
	</tbody>
</table>
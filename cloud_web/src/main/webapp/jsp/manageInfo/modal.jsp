<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!-- Modal -->
<div id="addColumnModal" class="modal fade hide"
    tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
    aria-hidden="true" style="">
    <div class="modal-header modal-header2">
        <button type="button" class="close" data-dismiss="modal"
            aria-hidden="true">
            <i class="dialog-close2"></i>
        </button>
        <h3 id="myModalLabel">
            新增栏目
        </h3>
    </div>
    <div class="modal-body">
    	<input type="hidden" id="channel_url_state_id" value="0">
    	<input type="hidden" id="channel_jump_url_state_id" value="0">
        <table class="add-column-tab" cellpadding="0" cellspacing="0">
        	<tr>
            	<td class="column-label" style="width:100px;">网站标识码：</td>
                <td  colspan="2"><span id="site_code_id"></span></td>
            </tr>
            <tr>
            	<td class="column-label">网站名称：</td>
                <td  colspan="2"><span id="site_name_id"></span></td>
            </tr>
            <tr>
            	<td class="column-label"><span class="red">*</span>栏目名称：</td>
                <td colspan="2"><input type="text" id="channel_name_id"  style="width:295px;" class="basic-input"></td>
            </tr>
            <tr>
            	<td class="column-label"><span class="red">*</span>类别：</td>
                <td colspan="2">
                	<select id="parent_channel_id">
                    	
                    </select>
                </td>
            </tr>
            <tr>
            	<td class="column-label"><span class="red">*</span>子类：</td>
                <td colspan="2">
                	<select id="sun_channel_id">
                    	
                    </select>
                    <span style="color:#6e6e6e; font:normal 14px/32px 'Microsoft Yahei'; margin-left:25px;">更新期限：</span><span id="updateTime" style="color:#6e6e6e; font:normal 14px/32px 'Microsoft Yahei;'"></span>
                </td>
            </tr>
            <tr>
            	<td class="column-label"><span class="red">*</span>栏目URL：</td>
                <td style="width:440px;"><input type="text" placeholder='请保证添加url完整准确，例如："http://www.beijing.gov.cn"。' id="channel_url_id"  onblur="modalChannelUrlTest()" style="width:425px;" class="basic-input"></td>
                <td>
                	<div class="btn-07c1f2 pull-left" id="channel_url_conn_test" onclick="onLineCheckChannelUrl()">连通测试</div>
                </td>
            </tr>
            <tr class="loading-tr">
                <td></td>
                <td colspan="2">
					<div class="column-loading-box" id="model_channel_loading_box">
                    	<div id="loading_channel_url_id"><span>连通测试中，可能需要几十秒钟，请稍候......</span><span class="loading-num">75%</span></div>
                    	<div class="column-loading"><div class="column-loading-con"></div></div>
                    </div>
                </td>
            </tr>
            <tr class="disabled-tr" id="jumpUrl_tr2">
				<td class="tit-td" valign="top">
					<div class="tz-cbox" id="checkBox"><input type="checkbox" id="checkbox_tr2"/></div>
						<div class="tz-tit">
								跳转URL：
						<div class="info-tip3">（监测入口）</div>
					</div>
				</td>            
            	<!-- <td class="column-label">跳转URL：</td> -->
                <td><input type="text" disabled="disabled" id="jump_url_id"   style="width:425px;" class="basic-input" ></td>
                <td>
                	<div class="btn-07c1f2 pull-left" id="jump_url_conn_test" onclick="onLineCheckjumpUrl()">连通测试</div>
                </td>
            </tr>
            <tr class="loading-tr">
                <td></td>
                <td colspan="2">
					<div class="column-loading-box" id="channel_jump_loading_box">
                    	<div id="modal_jump_loading_id"><span>连通测试中，可能需要几十秒钟，请稍候......</span><span class="loading-num">1%</span></div>
                    	<div class="column-loading"><div class="column-loading-con"></div></div>
                    </div>
                </td>
            </tr>
            <tr>
            	<td></td>
                <td colspan="2" class="column-msg-td"><div class="column-msg">为保证地址准确，请先在浏览器中访问该地址，可正常浏览后复制到此处，如上述操作仍无法通过校验，请联系客服：4000-976-005 。</div></td>
            </tr>
        </table>

    </div>
    <div class="modal-footer">
      <a class="btn btn-success green-btn" id="addChannel_btn_id" onclick="addChannel()" href="javascript:void(0)" >确  定</a>
      <button class="btn white-btn" data-dismiss="modal" id="closeExitModal">取  消</button>
    </div>
</div>

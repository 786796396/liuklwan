package com.ucap.cloud_web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ucap.cloud_web.dto.DicItemRequest;
import com.ucap.cloud_web.entity.DicItem;
import com.ucap.cloud_web.service.IDicItemService;

/**
 * 描述： 预警配置action
 * 包：com.ucap.cloud_web.action
 * 文件名称：ConfigEarlyAction
 * 公司名称：开普互联
 * 作者：liujc@ucap.com.cn
 * 时间：2016-7-20下午2:58:39 
 * @version V1.0
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class DicItemAction extends BaseAction{
	
	@Autowired
	private IDicItemService dicItemServiceImpl;
	

	/**
	 * @Title: 组织单位修改预警配置信息
	 * @Description:
	 * @author linhb@ucap.com.cn	2016-9-13下午2:21:54
	 */
	public void getData(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String pid = request.getParameter("pid");
			if(pid!=null){
				DicItemRequest dRequest = new DicItemRequest();
				dRequest.setPid(pid);
				dRequest.setPageSize(Integer.MAX_VALUE);
				dRequest.setDelStatus("0");
				List<DicItem> list = dicItemServiceImpl.queryList(dRequest);
				resultMap.put("list", list);
				resultMap.put("success", "true");
				writerPrint(JSONObject.fromObject(resultMap).toString());
			}
		} catch (Exception e) {
			resultMap.put("success", "false");
			writerPrint(JSONObject.fromObject(resultMap).toString());
			e.printStackTrace();
		}
		
	}

	

	
	
}

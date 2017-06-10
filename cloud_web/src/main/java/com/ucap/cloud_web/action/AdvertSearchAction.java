package com.ucap.cloud_web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.publics.util.utils.DateUtils;
import com.publics.util.utils.StringUtils;
import com.ucap.cloud_web.UrlAdapterVar;
import com.ucap.cloud_web.action.BaseAction;
import com.ucap.cloud_web.dto.ConfigAdvertRequest;
import com.ucap.cloud_web.entity.ConfigAdvert;
import com.ucap.cloud_web.service.IConfigAdvertService;

/**
 * <p>Description: 广告查询</p>
 * <p>@Package：com.ucap.cloud_backweb </p>
 * <p>Title: AdvertSearchAction </p>
 * <p>Company: 开普互联 </p>
 * <p>@author：yangshuai </p>
 * <p>@date：2016-9-5下午7:57:29 </p>
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class AdvertSearchAction extends BaseAction{

	@Autowired
	private IConfigAdvertService configAdvertServiceImpl;

	@Autowired
	private UrlAdapterVar urlAdapterVar;

	/**
	 * @Description: 广告查询
	 * @author: yangshuai --- 2016-9-5下午7:56:30
	 */
	public void advertSearch() {
		try {
			String id = request.getParameter("id");
			ConfigAdvert configAdvert = new ConfigAdvert();
			if(StringUtils.isNotEmpty(id)){
				ConfigAdvertRequest configAdvertRequest = new ConfigAdvertRequest();
				configAdvertRequest.setPageSize(Integer.MAX_VALUE);
				configAdvertRequest.setId(Integer.parseInt(id));
				configAdvertRequest.setAdTime(DateUtils.getTodayStandardStr());
				configAdvertRequest.setStatus(0);
				List<ConfigAdvert> caList = configAdvertServiceImpl.queryList(configAdvertRequest);
				if (caList.size() > 0) {
					configAdvert = caList.get(0);
					//获取上传图片地址
					if (StringUtils.isNotEmpty(configAdvert.getUrlAddr())) {
						String[] png = configAdvert.getUrlAddr().split("<img");
						if (png.length > 0) {
							String urlAddr = "";
							if (configAdvert.getUrlAddr().contains(".png")){
								urlAddr = configAdvert.getUrlAddr().substring(configAdvert.getUrlAddr().indexOf("/ewebeditor"),configAdvert.getUrlAddr().indexOf(".png")+4);
							}
							if (configAdvert.getUrlAddr().contains(".jpg")){
								urlAddr = configAdvert.getUrlAddr().substring(configAdvert.getUrlAddr().indexOf("/ewebeditor"),configAdvert.getUrlAddr().indexOf(".jpg")+4);
							}
							configAdvert.setUrlAddr(urlAdapterVar.getLinkUrl() + urlAddr);
						}
					}
				}
			}
			OutputJson(configAdvert);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

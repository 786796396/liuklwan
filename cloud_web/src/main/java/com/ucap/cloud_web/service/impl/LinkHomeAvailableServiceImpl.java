package com.ucap.cloud_web.service.impl;


import java.util.List;


/**


	@Autowired





		if(request.getDatabaseList()!=null && request.getDatabaseList().size()>0){
			param.put("databaseList", request.getDatabaseList());
		}
		if(request.getQuestionCodeArr()!=null && request.getQuestionCodeArr().length>0){
			param.put("questionCodeArr", request.getQuestionCodeArr());
		}
		if(request.getLinkList()!=null && request.getLinkList().size()>0){
			param.put("linkList", request.getLinkList());
		}

		if(request.getDatabaseList()!=null && request.getDatabaseList().size()>0){
			param.put("databaseList", request.getDatabaseList());
		}
		if(request.getQuestionCodeArr()!=null && request.getQuestionCodeArr().length>0){
			param.put("questionCodeArr", request.getQuestionCodeArr());
		}

	@Override
	public List<LinkHomeAvailableRequest> queryGroupBy(
			Map<String, Object> paramMap) {
		return linkHomeAvailableDao.queryGroupBy(paramMap);
	}


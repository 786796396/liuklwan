(function($){
	$.fn.linkageSelect=function(settings){
		if(this.length<1){return;};

		//定义json和id
		var jsonVal=settings.jsonVal,
		 	selectOne=settings.selectOne,
			selectTwo=settings.selectTwo;
		var selectOneJ=$("#"+selectOne),
			selectTwoJ=$("#"+selectTwo);
			
		//默认给第一个select赋值	
		var selectOneHtml;
		$.each(jsonVal.selectList,function(i,optionName){
			selectOneHtml=selectOneHtml+'<option value='+optionName.pv+'>'+optionName.p+'</option>';
		});
		selectOneJ.html(selectOneHtml);
		//默认给第二个select赋值	
		var selectTwoHtml;
		$.each(jsonVal.selectList[0].c,function(i,optionName){
			selectTwoHtml=selectTwoHtml+'<option value='+optionName.nv+'>'+optionName.n+'</option>';
		});
		selectTwoJ.html(selectTwoHtml);
		
		var selectStart=function(){
			var selectTwoHtml;
			$.each(jsonVal.selectList[selectOneJ.get(0).selectedIndex].c,function(i,optionName,optionValue){
				selectTwoHtml=selectTwoHtml+'<option value='+optionName.nv+'>'+optionName.n+'</option>';
			});
			selectTwoJ.html(selectTwoHtml);
		}
		//选择第一个select时发生事件
		selectOneJ.bind("change",function(i){
			selectStart();
		});
		
	}
 })(jQuery);
function dailyEarly(){
	  $.ajax( {  
	        type : "POST",  
	        url : webPath+"/csEarly_sendDaily.action",  
	        data :{
	   
	        },  
	        dataType:"json",
	        async : false,  
	        success : function(data) {
	        }
	        });

}
function sendOrgSingleEarlyInfo(){
	var type=$("#dailyType  option:selected").val();
	  $.ajax( {  
	        type : "POST",  
	        url : webPath+"/csEarly_sendOrgSingleEarlyInfo.action",  
	        data :{
	        	type:type
	        },  
	        dataType:"json",
	        async : false,  
	        success : function(data) {
	        	
	        	
	        }
	        });

}

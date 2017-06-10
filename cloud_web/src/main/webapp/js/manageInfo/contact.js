$(function (){
	getContractInfo();
})

/**
 * 联系人名录
 */
function getContractInfo(){
	var contractsQuery=$("#contractsQuery").val();
	$("#contractContentId").html("");
	$.ajax( {  
        type : "POST",  
        url : webPath+"/manageInfo_getContractInfos.action?contractsQuery="+contractsQuery,  
        dataType:"json",
        async : false,  
        success : function(data) {
          var html='';
          $.each(data.items, function(index, obj) {
        	  var siteCode=obj.siteCode;
        	  if(null==siteCode||''==siteCode){
        		  siteCode='--';
        	  }
        	  var name=obj.name;
        	  if(null==name||''==name){
        		  name='--';
        	  }
        	  var director=obj.director;
        	  if(null==director||''==director){
        		  director='--';
        	  }
        	  var principalName=obj.principalName;
        	  if(null==principalName||''==principalName){
        		  principalName='--';
        	  }
        	  var principalPost=obj.principalPost;
        	  if(null==principalPost||''==principalPost){
        		  principalPost='--';
        	  }
        	  var mobile=obj.mobile;
        	  if(null==mobile||''==mobile){
        		  mobile='--';
        	  }
        	  var telephone=obj.telephone;
        	  if(null==telephone||''==telephone){
        		  telephone='--';
        	  }
        	  var email=obj.email;
        	  if(null==email||''==email){
        		  email='--';
        	  }
        	  var linkmanName=obj.linkmanName;
        	  if(null==linkmanName||''==linkmanName){
        		  linkmanName='--';
        	  }
        	  var mobile2=obj.mobile2;
        	  if(null==mobile2||''==mobile2){
        		  mobile2='--';
        	  }
        	  var telephone2=obj.telephone2;
        	  if(null==telephone2||''==telephone2){
        		  telephone2='--';
        	  }
        	  var email2=obj.email2;
        	  if(null==email2||''==email2){
        		  email2='--';
        	  }
        	  html+='<tr>';
        	  html+='<td class="text-center" rowspan="2">'+(index+1)+'</td>';
        	  html+='<td class="text-left w26p" rowspan="2">'+siteCode+'</td>';
        	  html+='<td class="text-left w26p" rowspan="2">'+name+'</td>';
        	  html+='<td class="text-left w26p" rowspan="2">'+director+'</td>';
        	  html+='<td class="text-center">负责人</td>';
        	  html+='<td class="text-center">'+principalName+'</td>';
        	  html+='<td class="text-center">'+principalPost+'</td>';
        	  html+='<td class="text-center">'+mobile+'</td>';
        	  html+='<td class="text-center">'+telephone+'</td>';
    		  html+='<td class="text-center">'+email+'</td>';
			  html+='</tr>';
			  html+='<tr>';
		      html+='<td class="text-center">联系人</td>';
		      html+='<td class="text-center">'+linkmanName+'</td>';
        	  html+='<td class="text-center">--</td>';
        	  html+='<td class="text-center">'+mobile2+'</td>';
        	  html+='<td class="text-center">'+telephone2+'</td>';
    		  html+='<td class="text-center">'+email2+'</td>';
			  html+='</tr>';
          })
          
          $("#contractContentId").html(html);
        }
	});
}

//联系人名录导出Excel
function exportContracts(){
  var contractsQuery=$("#contractsQuery").val();
  window.location.href=webPath+"/manageInfo_contractInfosExportExcel.action?contractsQuery="+contractsQuery;
}
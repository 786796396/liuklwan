$(document).ready(function () {
		//菜单显示形式
		$("#mainnavToggle").click(function(){
			var menuPopover=$("#container .mainnav li a.menuPopover"),
				menuLi=$("#container .mainnav li");
			if($("#container").hasClass("mainnav-sm")){
				menuPopover.unbind("mouseenter");
				menuLi.unbind("mouseleave");
				$("#container").attr("class","page-container effect mainnav-lg");
				menuPopover.popover('destroy');
			}else{
				$("#container").attr("class","page-container effect mainnav-sm");
				//自定义popover显示的内容
				menuPopover.mouseenter(function(){
					$(this).popover({
					html : true,  
					content: function() {
					  var conTit=$(this).find(".menu-title").html();
					  var conLock=$(this).find(".lock").attr("class")
					  var conHtml='<a href="javascript:void(0);"><span class="menu-title">'+conTit+'</span><i class="'+conLock+'"></i></a>'
					  return conHtml;
					}  
					
				});
					$(this).popover("show");
					return false;
				});
				menuLi.mouseleave(function(){
					$(this).find(".menuPopover").popover("hide");
					return false;
				});
				
			}
		});
		
		
}); 
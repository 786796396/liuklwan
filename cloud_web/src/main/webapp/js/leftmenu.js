
var showId="#menuBg";

$(showId).show();
$(showId).siblings(".cur-tit").addClass("cur-selected-bg cur");

$(".cur-tit").on('click', function () {
	if($(this).hasClass("cur-selected-bg")){
		if($(this).hasClass("cur")){
			$(this).removeClass("cur");
			$(this).siblings("ul").slideUp();
		}else{
			$(this).siblings("ul").slideDown();
			$(this).addClass("cur");
		}
	}else{
		$('.accordion-group > ul').slideUp();
		$(this).siblings("ul").slideDown();
		$(".cur-tit").removeClass("cur-selected-bg cur");
		$(this).addClass("cur-selected-bg cur");
		$(".level1-li").removeClass("level1-li-selected");
	}
	
})

/**$(function(){ 

	$(".level1-li").on('click', function () {
		$(".level1-li").removeClass("level1-li-selected");
		$(this).addClass("level1-li-selected");
	})
	
}) **/
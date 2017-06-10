/**
 * 检测公用
 */
$(function () {
	$('.select-box').hover(function(){
        $(this).children('ul').show();
    },function(){
        $(this).children('ul').hide();
    });
    selectShow("siteType");
    selectShow("aaaType");
});
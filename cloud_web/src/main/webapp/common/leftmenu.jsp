<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
</head>
<body class="bg_f5f5f5">
	<!--菜单       satrt  -->

	<div class="left-menu-bar" id="sidebar">
		<div class="left-mbox">
			<ul id="twoUlId">
			</ul>
		</div>
	</div>
	
	<!--菜单       end  -->
	<script>
		$("#sidebar").mCustomScrollbar({
			autoHideScrollbar : true,
			autoDraggerLength : true,
			theme : "minimal-dark",
			//theme:"light-thick",
			//theme:"rounded-dots",
			//theme:"dark-thin",
			//theme:"light-3",
			//theme:"3d-thick",
			//theme:"3d",
			//theme:"rounded-dark",
			//theme:"dark",
			//autoDraggerLength:true,
			scrollButtons : {
				//enable:true,
				//scrollType:"continuous",
				//scrollSpeed:30,
				scrollAmount : 40
			},
			advanced : {
				updateOnBrowserResize : true,
				updateOnContentResize : true,
				autoExpandHorizontalScroll : false,
				autoScrollOnFocus : true
			},
		});
		
		$('.multi-line li').mouseover(function(){
        	$(this).addClass('active1');
    	});
	    $('.multi-line li').mouseout(function(){
	        $(this).removeClass('active1');
	    });
	    
	    /* $('.second li').html()
	    console.log($('.second li').html())
	    console.log($('#twoUlId .second>li'))
	    $('#twoUlId .second>li').on('click',function(){
	    	alert(1)
	    }) */
	</script>
 </body>
</html>
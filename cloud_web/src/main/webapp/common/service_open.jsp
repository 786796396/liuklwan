<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!--开通服务modal-->
<div class="modal fade hide modal-open-service" id="open-service"
	tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
	aria-hidden="true"
	style="width: 700px; left: 50%; margin-left: -350px;">

		<div class="top clearfix">
			<div type="button" class="close green_head_closeicon"
				data-dismiss="modal" aria-hidden="true"></div>
			<h4 class="fl" id="myModalLabel">开通服务</h4>
		</div>
		<div class="content">
			<h4>请填写您的联系信息，开普云的服务团队将在第一时间与您联系，办理开通服务线下流程。</h4>
			<div class="infos-box">
				<div class="ervery-inp clearfix">
					<span class="fl">单位名称：</span>
					<div class="inp-box fl">
						<input type="text" id="siteName"/>
					</div>
				</div>
				<div class="ervery-inp clearfix">
					<span class="fl">联系人：</span>
					<div class="inp-box fl">
						<input type="text" id="relationPerson"/>
					</div>
				</div>
				<div class="ervery-inp clearfix">
					<span class="fl">联系电话：</span>
					<div class="inp-box fl">
						<input type="text" id="relationPhone" />
					</div>
				</div>
				<div class="ervery-inp clearfix">
					<span class="fl">邮箱：</span>
					<div class="inp-box fl">
						<input type="text" id="email"/>
					</div>
				</div>
				<div class="ervery-checkbox clearfix">
					<span class="fl">开通服务：</span>
					<div class="checkboxs-box fl">
						<div class="fl">
							<input type="checkbox" id="aaa" name="services" value="1"/> <label for="aaa">全面检测</label>
						</div>
						<div class="fl">
							<input type="checkbox" id="bbb" name="services" value="2"/> <label for="bbb">错别字扫描</label>
						</div>
						<div class="fl">
							<input type="checkbox" id="ccc" name="services" value="3"/> <label for="ccc">全站死链扫描</label>
						</div>
						<div class="fl">
							<input type="checkbox" id="ddd" name="services" value="4"/> <label for="ddd">安全扫描</label>
						</div>
						<div class="fl">
							<input type="checkbox" id="eee" name="services" value="5"/> <label for="eee">栏目深度检测</label>
						</div>
						<c:if test="${fn:length(sessionScope.shiroUser.siteCode) == 6 }"> 
							<div class="fl" id="">
								<input type="checkbox" id="spotStateBox" name="services" value="6" /> <label
									for="spotStateBox">抽查</label>
							</div>
							<div class="fl">
								<input type="checkbox"  id="paTatgetBox"  name="services" value="7" /> <label
									for="paTatgetBox">绩效考评</label>
							</div>
						 </c:if> 
					</div>
				</div>
			</div>
		</div>
		<div class="bottom clearfix">
			<div class="btns fr close" data-dismiss="modal" aria-hidden="true">取&nbsp;&nbsp;消</div>
			<div class="btns fr submit" id="submitId">提&nbsp;&nbsp;交</div>
		</div>
	
</div>
<!--开通服务modal-->
<script type="text/javascript">
	/* var paTatgetState = $("#paTatgetState").html();
	if(paTatgetState.indexOf("已开通") == 0){
		$("#paTatgetBox").hide();
	}
	
	var spotStateId = $("#spotStateId").html();
	if(spotStateId.indexOf("已开通") == 0){
		$("#spotStateBox").hide();
	} */
	
	//校验用户填写信息  是否符合规则 才可以提交	

	function openService(){
		$('#open-service').modal('show');
	}
	
	$("#closeExitModal").click(function(){
		$('#open-service').modal('hide');
	});
	
	//提交按钮
	$("#submitId").bind("click", function() {
		var siteName = $("#siteName").val();
		var relationPerson = $("#relationPerson").val();
		var relationPhone = $("#relationPhone").val();
		var email = $("#email").val();
		var services = "";
		var i = 0;
		$("input:checkbox[name='services']:checked").each(function() { // 遍历name=test的多选框
				 i ++ ;
				 services += $(this).val() + ",";  // 每一个被选中项的值
		});
		if(i == 0){
			alert("请至少选中一项进行提交！");
			return;
		}
		if(siteName == ""){
			alert("单位名称不能为空。");
			return;
		}
		if(relationPerson == ""){
			alert("联系人不能为空。");
			return;
		}
		
		if(!(/^1(3|4|5|7|8)\d{9}$/.test(relationPhone))){ 
	        alert("请输入正确的手机号码。");  
	        return;
   		} 
		
		if(!(/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(email))){
			alert("请输入正确的邮箱地址。");
			return;
		}
		
			$.ajax({
				type : "POST",
				url : "serviceCenterRegulation_submitService.action",
				data : {
					siteName : siteName,
					relationPerson : relationPerson,
					relationPhone : relationPhone,
					email : email,
					services : services
				},
				dataType : "json",
				async : false,
				success : function(data) {
					if(data.success){
						$('#open-service').modal('hide');
						$("#submitSuccess").show();
						hideSubmitSuccess();					
				} else {
					alert(data.error);
				}
			}
		});

	});
	
	function hideSubmitSuccess(){
		var timeout = setTimeout(function() {
											$("#submitSuccess").hide();
										}, 3000);
	}
</script>
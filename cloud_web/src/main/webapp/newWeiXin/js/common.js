'use strict';

//移动端宽度定义
if(window.addEventListener){
  window.addEventListener('resize',set_fn_resize,false);
}else {
  window.attachEvent('onresize',set_fn_resize);
}
set_fn_resize();
function set_fn_resize(){
  var oHtml = document.getElementsByTagName('html')[0];
	var oC_w = document.documentElement.clentWidth||document.body.clientWidth;
	setStyles(oHtml,{fontSize:oC_w*10/64 + 'px'});
}

//原生ajax  参数(json格式)：url,type(默认get方式),data(json格式),成功回调函数,失败回调函数
function json2url(json){
	json.t=Math.random();
	var arr = [];
	for(var i in json){
		arr.push(i+'='+encodeURIComponent(json[i]));
	}
	return arr.join('&');
}
function ajax(json){
	json=json||{};
	if(!json.url)return;
	json.data=json.data||{};
	json.type=json.type||'get';
	
	if(window.XMLHttpRequest){
		var oAjax = new XMLHttpRequest();
	}else{
		var oAjax = new ActiveXObject('Microsoft.XMLHTTP');
	}
	switch(json.type.toLowerCase()){
		case 'get':
			oAjax.open('GET',json.url+'?'+json2url(json.data),true);
			oAjax.send();
		break;
		case 'post':
			oAjax.open('POST',json.url,true);
			oAjax.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
			oAjax.send(json2url(json.data));
		break;
	}
	oAjax.onreadystatechange=function(){
		if(oAjax.readyState==4){
			if(oAjax.status>=200&&oAjax.status<300||oAjax.status==304){
				json.success&&json.success(oAjax.responseText);
			}else{
				json.error&&json.error(oAjax.status);
			}
		}
	};
}

//原生设置cookie		参数： sName:name,sValue:值,iDay:保存时间（number,单位：天）
function addCookie(sName,sValue,iDay){
	if(iDay){
		var oDate = new Date();
		oDate.setDate(oDate.getDate()+iDay);
		document.cookie=sName+'='+sValue+'; PATH=/; EXPIRES='+oDate.toGMTString();
	}else{
		document.cookie=sName+'='+sValue+'; PATH=/';
	}
}

//原生获取cookie		参数：cookie的name
function getCookie(sName){
	var arr = document.cookie.split('; ');
	for(var i=0;i<arr.length;i++){
		var arr2 = arr[i].split('=');
		if(arr2[0]==sName){
			return arr2[1];
		}
	}
}

//原生删除cookie  参数：cookie的name
function removeCookie(sName){
	addCookie(sName,1,-1);
}

//设置随机整数,最小为n，最大为m-1
function rnd(n,m){
  return parseInt(n+Math.random()*(m-n));
}

//获取非行间样式
//参数：obj元素名,sName样式名
function getStyle(obj,sName){
	return (obj.currentStyle||getComputedStyle(obj,false))[sName];
}

//运动框架
//参数： 
//obj元素名
//json(json格式)样式名：样式值（不允许带单位,不允许设置复合样式）
//options(json格式)：time:运动时间（单位：ms，默认700ms），type：运动方式(默认'ease-out'),end:function(){}运动完成后执行的函数，可忽略
function startMove(obj,json,options){
	options = options||{};
	options.time=options.time||700;
	options.type=options.type||'ease-out';
	var start = {};
	var dis = {};
	for(var name in json){
		start[name] = parseFloat(getStyle(obj,name));
		if(isNaN(start[name])){
			switch(name){
				case 'top':
					start[name]=obj.offsetTop;
					break;
				case 'left':
					start[name]=obj.offsetLeft;
					break;
				case 'width':
					start[name]=obj.offsetWidth;
					break;
				case 'height':
					start[name]=obj.offsetHeight;
					break;
				case 'opacity':
					start[name]=1;
					break;
				case 'borderWidth':
					start[name]=0;
					break;
			}
		}
		dis[name]=json[name]-start[name];
	}
	var count = Math.floor(options.time/30);
	var n =0;
	clearInterval(obj.timer);
	obj.timer = setInterval(function(){
		n++;
		for(var name in json){
			switch(options.type){
				case 'linear':
					var cur = start[name]+dis[name]*n/count;
					break;
				case 'ease-in':
					var a = n/count;
					var cur = start[name]+dis[name]*Math.pow(a,3);
					break;
				case 'ease-out':
					var a = 1-n/count;
					var cur = start[name]+dis[name]*(1-Math.pow(a,3));
					break;
			}
			if(name=='opacity'){
				obj.style.opacity=cur;
				obj.style.filter='alpha(opacity:'+cur*100+')';
			}else{
				obj.style[name]=cur+'px';
			}
		}
		if(n==count){
			clearInterval(obj.timer);
			options.end&&options.end();
		}
	},30);
}

//设置CSS3的transition
//调用:trnsition(box,{time:'?s'(默认：1s),style:'?'(默认：all),type:'?'(默认：linear)})
//默认：transition:1s all linear
function transition(obj,json){
	json = json||{};
  json.time = json.time||'1s';
	json.style = json.style||'all';
	json.type = json.type||'linear';
	obj.style.WebkitTransition = json.time +' '+json.style+' '+json.type;
	obj.style.MozTransition = json.time +' '+json.style+' '+json.type;
	obj.style.MsTransition = json.time +' '+json.style+' '+json.type;
	obj.style.OTransition = json.time +' '+json.style+' '+json.type;
}

//取反操作		参数：元素，样式名，样式值1，样式值2		(单个元素)
function opposite(obj,sName,styles,to_styles){
  if(obj.style[sName] == styles){
	  obj.style[sName] = to_styles;
	}else {
	  obj.style[sName] = styles;
	}
}

//批量设置元素样式
//参数：
//aObj要设置的元素
//json（json格式）样式名：'值(必须带单位)'
function setStyles(aObj,json){
	if(aObj.length&&(aObj.length>1)){
		for(var _i=0; _i<aObj.length; _i++){
			for(var _name in json){
				aObj[_i].style[_name] = json[_name];
			}
		}
	}else{
		for(var _name in json){
			aObj.style[_name] = json[_name];
		}
	}
}

//批量控制class  参数：aobj:元素，classname:类名
function setClassName(aobj,classname){
  if(aobj.length > 1){
	  for(var _i = 0, _num = aobj.length; _i < _num; _i++){
		  aobj[_i].className = classname;
		}
	}else {
	  aobj.className = classname;
	}
}

//单选框类名控制反选	参数：元素，类名1，类名2
function selection(obj,class1,class2){
  if(obj.className == class1){
		obj.className = class2;
	}else {
		obj.className = class1;
	}
}

//通过class获取元素
function addEvent(obj,sEv,fn){
	if(obj.addEventListener){
		obj.addEventListener(sEv,fn,false);
	}else{
		obj.attachEvent('on'+sEv,fn);
	}
}
function findInArr(arr,item){
	for(var i=0;i<arr.length;i++){
		if(arr[i]==item){
			return true;
		}
	}
	return false;
}
function getByClass(obj,sClass){
	var aResult = [];
	if(obj.getElementsByClassName){
		aResult = obj.getElementsByClassName(sClass);
	}else{
		var aEle = obj.getElementsByTagName('*');
		for(var i=0;i<aEle.length;i++){
			var aClass = aEle[i].className.split(' ');
			if(findInArr(aClass,sClass)){
				aResult.push(aEle[i]);
			}
		}
	}
	return aResult;
}

//设置指定ID元素的value,并且需要有页面显示内容
//参数: obj指定元素ID,obj2需要显示的内容，obj3设置的内容
function fn_value(obj,obj2,obj3){
  var aaaa = document.getElementById(obj);
  aaaa.value = obj3.innerHTML;
  obj2.style.color = '#4a4a4a';
  obj2.innerHTML = obj3.innerHTML;
}

//发送短信验证
function note_verify(obj){
  if(obj.bOk)return;
	obj.bOk = true;
	var timer = null;
	var num = 60;
	setStyles(obj,{background:'#ccc'});
	obj.innerHTML = num + 's后重新发送';
	timer = setInterval(function(){
		num--;
		if(num == 0){
			obj.bOk = false;
			clearInterval(timer);
			setStyles(obj,{background:'#f39a13'});
			obj.innerHTML = '重新发送';
		}else {
			obj.innerHTML = num + 's后重新发送';
		}
	},1000);
}

//倒计时，参数：(number)年，月，日，时，分，秒
function count_down(obj,oY,oM,oD,oH,oMi,oS){
	var json_ = {};
	var oDate = new Date();
	oDate.setFullYear(oY);
	oDate.setMonth(oM-1);
	oDate.setDate(oD);
	oDate.setHours(oH);
	oDate.setMinutes(oMi);
	oDate.setSeconds(oS);
	var nTimer_ = new Date().getTime();
	var nSetTimer_ = oDate.getTime();
	var nDisTime_ = nSetTimer_ - nTimer_;
	if(nDisTime_ <= 0){
		nDisTime_=0;
		clearInterval(obj.timer);
	}
	json_.nD = parseInt(nDisTime_/86400000);
	nDisTime_ %= 86400000;
	json_.nH = parseInt(nDisTime_/3600000);
	nDisTime_ %= 3600000;
	json_.nM = parseInt(nDisTime_/60000);
	nDisTime_ %= 60000;
	json_.nS = parseInt(nDisTime_/1000);
	return json_;
}
function fn_count_down(obj,Y,M,D,h,m,s){
  var oProduct_time = document.getElementById(obj);
	oProduct_time.timer = setInterval(fn_to_next_time,1000);
	fn_to_next_time();
	function fn_to_next_time(){
	  oProduct_time.innerHTML = count_down(obj,Y,M,D,h,m,s).nD+'天'
		+count_down('product-safety-time',Y,M,D,h,m,s).nH+'小时'
		+count_down('product-safety-time',Y,M,D,h,m,s).nM+'分'
		+count_down('product-safety-time',Y,M,D,h,m,s).nS+'秒';
	}
}

//角度转换成弧度，参数：角度(number,不带单位)
function d2a(n){
  return n*Math.PI/180;
}

//百分比转换
function fn_percent(obj) {
	return parseInt(obj.innerHTML) * 3.6;
}

//圆弧,参数：obj:需要设置参数的ID,dug：获取保存百分比内容的元素ID，即控制svg圆环比例的数字容器
function svg_bow(obj,dug){
	dug = fn_percent(dug)||0;
  var cx = 30,cy = 30;
	var r = 19;
	var ang = dug;
	var x = cx + Math.sin(d2a(ang))*r;
	var y = cy - Math.cos(d2a(ang))*r;
	var _i = 0;
	if(ang == 360){
	  x = 29.99;
	}
	obj.setAttribute(
	  'd',
		[
		  'M', cx, cy-r,
			'A', r, r, 0, (ang>180?1:0), 1, x, y
		].join(' ')
	);
}

//banner滚动，参数：obj2：banner内容的盒子ID，obj3：按钮的ID
function fn_Banner(obj2,obj3){
	var a = document.getElementById(obj2);
	a.innerHTML += a.innerHTML+a.innerHTML;
	var b = a.children;
	var f = document.documentElement.clientWidth||document.body.clientWidth,g = -(b.length/3)*f,k = 0;
	var l = {};
	for(var i=0,num=b.length; i<num; i++){
		b[i].e = i;
	}
	a.e = b[b.length/3].e;
	var c = document.getElementById(obj3);
	var d = c.children;
	setStyles(b,{width:f + 'px'});
	setStyles(a,{width:(b.length*b[0].offsetWidth)+'px',left:-(b.length/3*b[0].offsetWidth) + 'px'});
	//setStyles(c,{width:(Math.ceil(d[0].offsetWidth))*d.length + 'px',marginLeft:-(Math.ceil(d[0].offsetWidth))*d.length/2+'px'}); 
	a.addEventListener('touchstart',function(ev){
		var ev = ev||event;
		if(a.bOk)return;
		l = {X:ev.targetTouches[0].pageX,Y:ev.targetTouches[0].pageY};
		clearInterval(a.q);
	});
	a.addEventListener('touchmove',function(ev){
		var ev = ev||event;
		clearInterval(a.q);
		if(a.bOk)return;
		if(ev.targetTouches.length>1||ev.scale&&ev.scale != 1)return;
		var oMove = {X:ev.targetTouches[0].pageX-l.X,Y:ev.targetTouches[0].pageY-l.Y};
		var h = Math.abs(oMove.X)<Math.abs(oMove.Y)?1:0;
		if(h)return;
		ev.preventDefault();
		k = ev.targetTouches[0].pageX - l.X;
		setStyles(a,{left: g + k + 'px'});
	});
	a.addEventListener('touchend',function(ev){
		var ev = ev||event;
		if(a.bOk)return;
		a.bOk = true;
		if(Math.abs(k) > f/10){
			if(k > 0){
				startMove(a,{left: g + f},{time: 500,type:'linear',end:function(){
					a.e = a.e - 1;
					setClassName(d,'');
					setClassName(d[a.e%(b.length/3)],'active');
					g = g + f;
					if(Math.abs(parseInt(a.style.left)) < f/2){
						setStyles(a,{left:-(b.length/3*b[0].offsetWidth) + 'px'});
						a.e = b[b.length/3].e;
						g = -(b.length/3)*f;
					}
					a.bOk = false;
				}});
			}else {
				next_img();
			}
		}else {
			startMove(a,{left: g},{time: 100,type:'linear',end:function(){
				a.bOk = false;
			}});
		}
		clearInterval(a.q);
		a.q = setInterval(next_img,5000);
	});
	
	function next_img(){
		startMove(a,{left: g - f},{time: 500,type:'linear',end:function(){
			a.e = a.e + 1;
			setClassName(d,'');
					setClassName(d[a.e%(b.length/3)],'active');
			g = g - f;
			if(Math.abs(parseInt(a.style.left)) > f*(b.length*2/3 - 1)){
				setStyles(a,{left:-(b.length/3*b[0].offsetWidth) + 'px'});
				a.e = b[b.length/3].e;
				g = -(b.length/3)*f;
			}
			a.bOk = false;
		}});
	}
	clearInterval(a.q);
	a.q = setInterval(next_img,5000);
}

//进度条  参数：控制进度条的父级id
function Schedule(obj){
  var oIndex_percent = document.getElementById(obj);
	setStyles(oIndex_percent.children[0].children[0],{width:parseInt(oIndex_percent.children[1].innerHTML)+'%'});
}
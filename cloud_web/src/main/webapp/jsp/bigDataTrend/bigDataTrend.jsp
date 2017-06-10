<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/common/heade.jsp"%>
    <meta charset="UTF-8">
    <title>预览引用</title>
    <style>
        .wrap{ }
        .tab-change{ position: relative; top: 0;}
        .tab-every{ position: relative; border: 1px solid #e0e0e0; __width: 123px; height: 30px; padding:0 10px 0 10px; background: #f4f6f8; font: normal 14px/30px 'Microsoft Yahei'; text-align: center;box-sizing: border-box;  border-bottom: none; cursor:pointer;}
        .not-f{ margin-left: -1px;}
        .tab-every.on{ border-top: 2px solid red; background: #fff; border-bottom: 0; z-index: 1; color: #f33;}
        .tab-show{ position: relative; top: -1px; __height: 186px; overflow-y: auto; border: 1px solid #e0e0e0;}
        .ul-content{}
        .tb-head{ font: bold 14px 'Microsoft Yahei'; color: #333;}
        .tb-head ul li{ padding:10px 0 5px 0;}
        .first-row{ width: 40%; text-align:left;}
        .first-row span{ margin-left: 20px;}
        .second-row{ width: 30%; text-align: center;}
        .third-row{ width: 30%; text-align: center;}
        .tb-detail{ border-top: 1px solid #eeeeee; font: normal 12px 'Microsoft Yahei'; color: #757575;}
        .tb-detail ul li{ padding:4px 0 4px 0;}
        .colo-f00{ color: #f00;}
        .colo-9ac{ color: #9ACD32;}
        .colo-07a168{ color: #07a168;}
    </style>
</head>
<body>
    <div class="wrap">
    
        <div class="tab-change clearfix">
        <input type="hidden" id="siteCode" value="${orgSiteCode }">
            <div id="tabNameOne" class="fl tab-every on" onclick="spaceSites()">
                本级站点
            </div>
            <div id="tabNameTwo" class="fl tab-every not-f" onclick="spacePortal()">
                下级地方门户
            </div>
            <div id="tabNameThree" class="fl tab-every not-f" onclick="spaceBalance()">
                 下级地方站群      
            </div>
        </div>
       
        <div id="spaceSites" class="tab-show" >
           <!--  <ul>
                
            </ul> -->
            
            <ul id="sites" class="ul-content">
            	<li class="tb-head">
                    <ul class="clearfix">
                        <li class="fl first-row" >
                            <span> 网站名称</span>
                        </li>
                        <li class="fl second-row"><div  class="tapName"></div></li>
                        <li class="fl third-row">增长趋势</li>
                    </ul>
                    <ul id="sites" class="ul-content"></ul>
                </li>
            </ul>
        </div>
        
         <div id="spacePortal" class="tab-show" style="display: none" >
            <ul>
                <li class="tb-head">
                    <ul class="clearfix">
                        <li class="fl first-row">
                            <span> 网站名称</span>
                        </li>
                        <li class="fl second-row"><div class="tapName"></div></li>
                        <li class="fl third-row">增长趋势</li>
                    </ul>
                </li>
            </ul>
            
            <ul id="portal" class="ul-content"></ul>
        </div>
        
         <div id="spaceBalance" class="tab-show" style="display: none">
            <ul>
                <li class="tb-head">
                    <ul class="clearfix">
                        <li class="fl first-row">
                            <span> 组织单位</span>
                        </li>
                        <li class="fl second-row"><div  class="tapName"></div></li>
                        <li class="fl third-row">增长趋势</li>
                    </ul>
                </li>
            </ul>
            <ul id="balance" class="ul-content"></ul>
        </div>
    </div>

</body>
<script type="text/javascript" src="<%=path %>/js/bigDataTrend/bigDataTrend.js"></script>
</html>
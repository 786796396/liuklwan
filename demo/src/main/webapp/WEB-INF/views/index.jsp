<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/3
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <title>林神的娱乐</title>
    <%@ include file="/WEB-INF/common/common.jsp" %>
    <%--<style>--%>
        <%--.admin-content{--%>
            <%--width: 90%;--%>
            <%--padding-top: 100px;--%>
            <%--margin-left: 66px;--%>
        <%--}--%>
    <%--</style>--%>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/index/index.css">

</head>
<body>
<%@ include file="/WEB-INF/common/top.jsp" %>
<div>
    <%@ include file="/WEB-INF/common/left.jsp" %>
    <div class="admin-content" style="border-left: 2px solid darkgray">
        <div class="admin-content-body">
            <div class="am-g">
                <div class="am-u-sm">
                    <%--<iframe name="maincontent" height="100%" width="100%" allowtransparency="yes" id="mainconten" src="">--%>
                    <%--</iframe>--%>
                    <%--<p>适用浏览器：360、FireFox、Chrome、Safari、Opera、傲游、搜狗、世界之窗. 不支持IE8及以下浏览器。</p>--%>
                    <%--<p>来源：<a href="http://sc.chinaz.com/" target="_blank">站长素材</a></p>--%>



                        <div id="birdsCanvas">
                            <img src="${ctx}/img/index/sun.png" width="36" height="36" alt="" class="sun">
                            <div id="bg"></div>
                        </div>

                        <div id="bottom">
                            <div class="chrome-badge">
                                <%--<a href="http://www.chromeexperiments.com/" onclick="sendStat('/Click-on-Chrome-Experiment')" target="_blank" id="ce-link">--%>
                                    <img src="${ctx}/img/index/chrome.png" width="107" height="55" alt="A Chrome Experiment">
                                <%--</a>--%>
                            </div>
                            <div class="badge-sep"></div>
                            <div class="google-badge">
                                <%--<a href="http://www.google.com/" onclick="sendStat('/Click-on-Made-with-Google')" target="_blank" id="google-link">--%>
                                    <img src="${ctx}/img/index/google.png" width="91" height="60" alt="Made with some friends from Google">
                                <%--</a>--%>
                            </div>
                            <div class="credits">
                                <div class="credits-inside">
                                    <%--<span class="kiosk-hide"><a href="#" onclick="openTheWildernessMachine()">--%>
                                        The Wilderness Machine
                                    <%--</a>--%>
                                        | </span>
                                    <%--<a href="https://www.google.com/policies/">--%>
                                        Terms &amp Privacy
                                    <%--</a> --%>
                                        |
                                        <%--<a href="#" onclick="openCredits()">--%>
                                            Credits
                                        <%--</a>--%>
                                    <span class="kiosk-hide"> | Share &nbsp;
                                        <%--<a href="http://www.facebook.com/sharer.php?u=http://www.thewildernessdowntown.com" onclick="sendStat('/Click-on-Share-on-Facebook')" target="_blank">--%>
                                        <img src="${ctx}/img/index/facebook.png" width="20" height="20" alt="Facebook page">
                                        <%--</a>--%>
                                        <%--<a href="http://twitter.com/share?text=Check out Arcade Fire's new interactive HTML5 music experience, “The Wilderness Downtown” &amp;url=http://www.thewildernessdowntown.com" onclick="sendStat('/Click-on-Share-on-Twitter')" target="_blank">--%>
                                        <img src="${ctx}/img/index/twitter.png" width="20" height="20" alt="Tweet me!">
                                        <%--</a>--%>
                                    </span>
                                </div>
                            </div>
                        </div>

                        <div id="top">
                            <div id="writer">
                                <div class="subtitle">
                                    <div class="subtitle-inside" id="sub-text">An interactive film by Chris Milk <br>Featuring "We Used To Wait" <br>Built in HTML5</div>
                                </div>
                            </div>

                            <div id="buttons-area">

                            </div>
                        </div>

                        <img src="${ctx}/img/index/tree.png" alt="" width="385" height="387" class="topleft">

                        <script type="text/javascript" src="${ctx}/js/index/app.js"></script>
                        <script type="text/javascript" src="${ctx}/js/index/ga.js"></script>
                        <script type="text/javascript">
                            var pageTracker = _gat._getTracker("UA-18017745-1");
                            pageTracker._trackPageview();
                        </script>
                        <!--
                            Start of DoubleClick Floodlight Tag: Please do not remove
                            Activity name of this tag: Chrome - LP - Wilderness
                            URL of the webpage where the tag is expected to be placed: http://thewildernessdowntown.com
                            This tag must be placed between the <body> and </body> tags, as close as possible to the opening tag.
                            Creation Date: 08/19/2010
                           -->
                        <%--<script type="text/javascript">--%>
                            <%--try {--%>
                                <%--var axel = Math.random() + '';--%>
                                <%--var a = axel * 10000000000000;--%>
                                <%--document.write('<iframe src="http://fls.doubleclick.net/activityi;src=2542116;type=clien612;cat=chrom975;ord=' + a + '?" width="1" height="1" frameborder="0"></iframe>');--%>
                            <%--} catch (error) {}--%>
                        <%--</script>--%>
                        <%--<noscript>--%>
                            <%--<iframe src="http://fls.doubleclick.net/activityi;src=2542116;type=clien612;cat=chrom975;ord=1?" width="1" height="1" frameborder="0"></iframe>--%>
                        <%--</noscript>--%>
                        <%--<div id="popup-warning">This film requires pop-ups. Click the <img width="25" height="21" src="${ctc}/img/index/popup.png" style=""> icon in the address bar. Select "Always allow pop-ups from wildernessdowntown.com."</div>--%>



                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

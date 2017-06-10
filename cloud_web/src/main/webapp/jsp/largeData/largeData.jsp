<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>大数据分析</title>
    <%@ include file="/common/meta.jsp"%>
 	<%@ include file="/common/heade.jsp"%>

	<link rel="stylesheet" type="text/css" href="<%= path %>/css/jcdata.css" />
	<link rel="stylesheet" type="text/css" href="<%= path %>/css/zzchouc.css" />
	<script type="text/javascript" src="<%=path%>/js/largeData/largeData.js"></script>
</head>
<body>

<div class="page_wrap">
	<%@ include file="/common/top.jsp"%>
    <div class="main-container container ">
        <div class="row-fluid">
        <c:set var="ba_index" value="26" scope="request"/>
		<c:set var="menu" value="" scope="request"/>
        <%@ include file="/common/leftmenu.jsp"%>
            <div class="page-right">
                <!--全国健康指数部分开始-->
                <div class="jkzs clearfix">
                    <div class="health-index fl">
                        <span>全国政府网站整体健康指数</span>
                        <span class="fred-s">1455.28</span>
                        <!--<span class="icon_red"></span>-->
                        <span class="freds-s">&nbsp;<i class="icon_red"></i>&nbsp;&nbsp;&nbsp;+0.09%(1.26)</span>
                    </div>
                    <div class="bor fl"></div>
                    <div class="site fl">
                        <span>监测站点</span>
                        <span class="fgreen-s">84204</span>
                        <span style="color: #979797;">&nbsp;个</span>
                    </div>
                    <div class="bor fl"></div>
                    <div class="page fl">
                        <span>监测页面</span>
                        <span id="Pages" class="fgreen-s">1,142,114,642</span>
                        <span style="color: #979797;">&nbsp;个</span>
                    </div>
                    <div class="bor fl"></div>
                    <div class="ques fl">
                        <span>发现问题</span>
                        <span id="Ques" class="fgreen-s">&nbsp;6,988,909</span>
                        <span style="color: #979797;">&nbsp;个</span>
                    </div>
                </div>
                <!--全国健康指数部分结束-->
                <!--日常监测数据部分开始-->
                <div class="day-data">
                    <div>日常监测数据</div>
                </div>
                <!--日常监测数据部分结束-->
                <!--我是一条线-->
                <div class="solid">
                </div>
                <!--我是一条线-->
                <!--表上面部分开始-->
                <div class="tab-top clearfix">
                    <div class="tab-change fl">
                        <span class="on">省份</span>
                        <span>部委</span>
                    </div>
                    <div class="fr tab_list_report" style="display: none;">导出列表</div>
                    <div class="fr search_box" style="display: none;">
                        <input type="text"placeholder="输入网站名称或标识码" class="search_text"/>
                        <input type="submit" value="搜索" class="search_btn"/>
                    </div>
                    <div class="fr select_box">
                        <select class="sel on" id="sel">
                            <option value="1" selected>昨天</option>
                            <option value="2">近7天</option>
                            <option value="3">近两周</option>
                            <option value="4">近一个月</option>
                        </select>
                        <select class="sel" id="sel">
                            <option value="5" selected>昨天</option>
                            <option value="6">近7天</option>
                            <option value="7">近两周</option>
                            <option value="8">近一个月</option>
                        </select>
                    </div>
                </div>
                <!--表上面部分结束-->
                <!--表格部分开始-->
                <div class="table-box">
                    <table class="table1 on" id="table-Stwodays">
                        <thead>
                            <tr>
                                <th style="width: 15%;">组织单位名称/编码</th>
                                <th style="width: 10%;">网站个数<i class="jian"></i></th>
                                <th style="width: 10%;">网站不连通<br>（占比）<i class="jian"></i></th>
                                <th style="width: 10%;">首页不可用链接（个）<i class="jian"></i></th>
                                <th style="width: 10%;">首页不更新<br>（网站数）<i class="jian"></i></th>
                                <th style="width: 10%;">首页不更新<br>（占比）<i class="jian"></i></th>
                                <th style="width: 15%;">平均更新量<br>（条/站）<i class="jian"></i></th>
                                <th style="width: 10%;">总更新量<br>（条）<i class="jian"></i></th>
                                <th style="width: 10%;">健康指数<i class="jian"></i></th>
                            </tr>
                        </thead>
                        <tbody>
                        <tr><td>北京市<br><br>110000</td><td class="not-td">1075</td><td>39.80%</td><td>840</td><td>999</td><td>92.93%</td><td>0.09 </td><td>96</td><td>1316.43</td></tr>
                        <tr><td>天津市<br><br>120000</td><td class="not-td">326</td><td>36.93%</td><td>170</td><td>310</td><td>95.09%</td><td>0.20 </td><td>66</td><td>1531.23</td></tr>
                        <tr><td>河北省<br><br>130000</td><td class="not-td">1862</td><td>60.91%</td><td>2384</td><td>1658</td><td>89.04%</td><td>0.10 </td><td>187</td><td>1376.04</td></tr>
                        <tr><td>山西省<br><br>140000</td><td class="not-td">1291</td><td>72.56%</td><td>2193</td><td>1066</td><td>82.57%</td><td>0.10 </td><td>135</td><td>1277.32</td></tr>
                        <tr><td>内蒙古自治区<br><br>150000</td><td class="not-td">2013</td><td>86.61%</td><td>2958</td><td>1731</td><td>85.99%</td><td>0.13 </td><td>256</td><td>1305.12</td></tr>
                        <tr><td>辽宁省<br><br>210000</td><td class="not-td">992</td><td>65.59%</td><td>865</td><td>895</td><td>90.22%</td><td>0.09 </td><td>93</td><td>1368.04</td></tr>
                        <tr><td>吉林省<br><br>220000</td><td class="not-td">873</td><td>70.47%</td><td>1082</td><td>702</td><td>80.41%</td><td>1.31 </td><td>1142</td><td>1222.27</td></tr>
                        <tr><td>黑龙江省<br><br>230000</td><td class="not-td">1258</td><td>75.35%</td><td>2016</td><td>1013</td><td>80.52%</td><td>0.08 </td><td>106</td><td>1228.97</td></tr>
                        <tr><td>上海市<br><br>310000</td><td class="not-td">644</td><td>60.79%</td><td>1223</td><td>615</td><td>95.50%</td><td>0.23 </td><td>150</td><td>1539.28</td></tr>
                        <tr><td>江苏省<br><br>320000</td><td class="not-td">3770</td><td>67.36%</td><td>3654</td><td>3448</td><td>91.46%</td><td>0.22 </td><td>827</td><td>1407.65</td></tr>
                        <tr><td>浙江省<br><br>330000</td><td class="not-td">3420</td><td>71.13%</td><td>4294</td><td>2995</td><td>87.57%</td><td>0.45 </td><td>1528</td><td>1375.05</td></tr>
                        <tr><td>安徽省<br><br>340000</td><td class="not-td">2171</td><td>82.25%</td><td>2613</td><td>1829</td><td>84.25%</td><td>0.31 </td><td>668</td><td>1333.15</td></tr>
                        <tr><td>福建省<br><br>350000</td><td class="not-td">2511</td><td>65.05%</td><td>4600</td><td>2271</td><td>90.44%</td><td>0.21 </td><td>521</td><td>1362.14</td></tr>
                        <tr><td>江西省<br><br>360000</td><td class="not-td">1699</td><td>60.24%</td><td>1864</td><td>1540</td><td>90.64%</td><td>0.14 </td><td>243</td><td>1383.76</td></tr>
                        <tr><td>山东省<br><br>370000</td><td class="not-td">3764</td><td>69.47%</td><td>5975</td><td>3430</td><td>91.13%</td><td>0.09 </td><td>327</td><td>1391.97</td></tr>
                        <tr><td>河南省<br><br>410000</td><td class="not-td">2180</td><td>58.29%</td><td>3953</td><td>1886</td><td>86.51%</td><td>0.12 </td><td>255</td><td>1313.3</td></tr>
                        <tr><td>湖北省<br><br>420000</td><td class="not-td">3100</td><td>54.21%</td><td>4409</td><td>2700</td><td>87.10%</td><td>0.15 </td><td>467</td><td>1349.14</td></tr>
                        <tr><td>湖南省<br><br>430000</td><td class="not-td">3044</td><td>67.64%</td><td>3984</td><td>2622</td><td>86.14%</td><td>0.37 </td><td>1132</td><td>1272.88</td></tr>
                        <tr><td>广东省<br><br>440000</td><td class="not-td">4338</td><td>58.41%</td><td>4325</td><td>3996</td><td>92.12%</td><td>0.09 </td><td>384</td><td>1412.08</td></tr>
                        <tr><td>广西壮族自治区<br><br>450000</td><td class="not-td">1848</td><td>44.66%</td><td>1389</td><td>1662</td><td>89.94%</td><td>0.15 </td><td>285</td><td>1420.04</td></tr>
                        <tr><td>海南省<br><br>460000</td><td class="not-td">270</td><td>77.74%</td><td>590</td><td>236</td><td>87.41%</td><td>0.36 </td><td>98</td><td>1349.81</td></tr>
                        <tr><td>重庆市<br><br>500000</td><td class="not-td">956</td><td>76.41%</td><td>2389</td><td>832</td><td>87.03%</td><td>0.18 </td><td>175</td><td>1366.4</td></tr>
                        <tr><td>四川省<br><br>510000</td><td class="not-td">3700</td><td>49.05%</td><td>3593</td><td>3322</td><td>89.78%</td><td>0.10 </td><td>357</td><td>1361.01</td></tr>
                        <tr><td>贵州省<br><br>520000</td><td class="not-td">1518</td><td>57.93%</td><td>1999</td><td>1279</td><td>84.26%</td><td>0.35 </td><td>535</td><td>1333.72</td></tr>
                        <tr><td>云南省<br><br>530000</td><td class="not-td">3548</td><td>50.74%</td><td>2137</td><td>1143</td><td>32.22%</td><td>0.04 </td><td>126</td><td>733.65</td></tr>
                        <tr><td>西藏自治区<br><br>540000</td><td class="not-td">163</td><td>85.01%</td><td>199</td><td>116</td><td>71.17%</td><td>2.12 </td><td>345</td><td>1384.77</td></tr>
                        <tr><td>陕西省<br><br>610000</td><td class="not-td">2811</td><td>57.22%</td><td>3548</td><td>2505</td><td>89.11%</td><td>0.09 </td><td>243</td><td>1339.6</td></tr>
                        <tr><td>甘肃省<br><br>620000</td><td class="not-td">1584</td><td>57.24%</td><td>3566</td><td>1368</td><td>86.36%</td><td>0.24 </td><td>376</td><td>1312.54</td></tr>
                        <tr><td>青海省<br><br>630000</td><td class="not-td">295</td><td>64.31%</td><td>510</td><td>242</td><td>82.03%</td><td>0.53 </td><td>155</td><td>1279.06</td></tr>
                        <tr><td>宁夏回族自治区<br><br>640000</td><td class="not-td">283</td><td>67.39%</td><td>357</td><td>261</td><td>92.23%</td><td>0.09 </td><td>25</td><td>1421.3</td></tr>
                        <tr><td>新疆维吾尔自治区<br><br>650000</td><td class="not-td">1615</td><td>61.26%</td><td>2102</td><td>1446</td><td>89.54%</td><td>0.10 </td><td>162</td><td>1342.83</td></tr>
                        <tr><td>新疆生产建设兵团<br><br>BT0000</td><td class="not-td">204</td><td>92.34%</td><td>94</td><td>197</td><td>96.57%</td><td>0.26 </td><td>53</td><td>1731.54</td></tr>
                        </tbody>
                    </table>
                    <table class="table1"  id="table-Sweek">
                        <thead>
                            <tr>
                                <th style="width: 15%;">组织单位名称/编码</th>
                                <th style="width: 10%;">网站个数<i class="jian"></i></th>
                                <th style="width: 10%;">网站不连通<br>（占比）<i class="jian"></i></th>
                                <th style="width: 10%;">首页不可用链接（个）<i class="jian"></i></th>
                                <th style="width: 10%;">首页不更新（<br>网站数）<i class="jian"></i></th>
                                <th style="width: 10%;">首页不更新<br>（占比）<i class="jian"></i></th>
                                <th style="width: 15%;">平均更新量<br>（条/站）<i class="jian"></i></th>
                                <th style="width: 10%;">总更新量<br>（条）<i class="jian"></i></th>
                                <th style="width: 10%;">健康指数<i class="jian"></i></th>
                            </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>北京市<br>
                                <br>110000</td>
                            <td class="not-td">1075</td>
                            <td>56.69%</td>
                            <td>6515</td>
                            <td>5453</td>
                            <td>72.47%</td>
                            <td>1.39</td>
                            <td>10472</td>
                            <td>1363.68</td>
                        </tr>
                        <tr>
                            <td>天津市<br>
                                <br>120000</td>
                            <td class="not-td">326</td>
                            <td>44.46%</td>
                            <td>1147</td>
                            <td>1560</td>
                            <td>68.36%</td>
                            <td>4.36</td>
                            <td>9959</td>
                            <td>1537.48</td>
                        </tr>
                        <tr>
                            <td>河北省<br>
                                <br>130000</td>
                            <td class="not-td">1862</td>
                            <td>48.45%</td>
                            <td>16498</td>
                            <td>9916</td>
                            <td>76.08%</td>
                            <td>1.43</td>
                            <td>18615</td>
                            <td>1398.84</td>
                        </tr>
                        <tr>
                            <td>山西省<br>
                                <br>140000</td>
                            <td class="not-td">1291</td>
                            <td>55.75%</td>
                            <td>15014</td>
                            <td>6081</td>
                            <td>67.29%</td>
                            <td>1.52</td>
                            <td>13756</td>
                            <td>1274.84</td>
                        </tr>
                        <tr>
                            <td>内蒙古自治区<br>
                                <br>150000</td>
                            <td class="not-td">2013</td>
                            <td>69.92%</td>
                            <td>20693</td>
                            <td>10390</td>
                            <td>73.74%</td>
                            <td>1.53</td>
                            <td>21514</td>
                            <td>1323.18</td>
                        </tr>
                        <tr>
                            <td>辽宁省<br>
                                <br>210000</td>
                            <td class="not-td">992</td>
                            <td>42.20%</td>
                            <td>6035</td>
                            <td>4549</td>
                            <td>65.51%</td>
                            <td>2.65</td>
                            <td>18436</td>
                            <td>1390.46</td>
                        </tr>
                        <tr>
                            <td>吉林省<br>
                                <br>220000</td>
                            <td class="not-td">873</td>
                            <td>40.40%</td>
                            <td>7838</td>
                            <td>3998</td>
                            <td>65.42%</td>
                            <td>2.80</td>
                            <td>17108</td>
                            <td>1222.38</td>
                        </tr>
                        <tr>
                            <td>黑龙江省<br>
                                <br>230000</td>
                            <td class="not-td">1258</td>
                            <td>59.50%</td>
                            <td>14445</td>
                            <td>6085</td>
                            <td>69.10%</td>
                            <td>1.40</td>
                            <td>12369</td>
                            <td>1240.44</td>
                        </tr>
                        <tr>
                            <td>上海市<br>
                                <br>310000</td>
                            <td class="not-td">644</td>
                            <td>51.56%</td>
                            <td>8197</td>
                            <td>3127</td>
                            <td>69.37%</td>
                            <td>3.12</td>
                            <td>14073</td>
                            <td>1526.25</td>
                        </tr>
                        <tr>
                            <td>江苏省<br>
                                <br>320000</td>
                            <td class="not-td">3770</td>
                            <td>58.19%</td>
                            <td>26328</td>
                            <td>17211</td>
                            <td>65.22%</td>
                            <td>2.18</td>
                            <td>57528</td>
                            <td>1416.27</td>
                        </tr>
                        <tr>
                            <td>浙江省<br>
                                <br>330000</td>
                            <td class="not-td">3420</td>
                            <td>63.29%</td>
                            <td>30242</td>
                            <td>15081</td>
                            <td>62.99%</td>
                            <td>2.72</td>
                            <td>65114</td>
                            <td>1370.93</td>
                        </tr>
                        <tr>
                            <td>安徽省<br>
                                <br>340000</td>
                            <td class="not-td">2171</td>
                            <td>78.70%</td>
                            <td>18320</td>
                            <td>9402</td>
                            <td>61.87%</td>
                            <td>2.96</td>
                            <td>45052</td>
                            <td>1346.75</td>
                        </tr>
                        <tr>
                            <td>福建省<br>
                                <br>350000</td>
                            <td class="not-td">2511</td>
                            <td>64.62%</td>
                            <td>31502</td>
                            <td>13493</td>
                            <td>76.77%</td>
                            <td>1.21</td>
                            <td>21271</td>
                            <td>1362.74</td>
                        </tr>
                        <tr>
                            <td>江西省<br>
                                <br>360000</td>
                            <td class="not-td">1699</td>
                            <td>55.42%</td>
                            <td>13479</td>
                            <td>8648</td>
                            <td>72.72%</td>
                            <td>1.63</td>
                            <td>19425</td>
                            <td>1389.75</td>
                        </tr>
                        <tr>
                            <td>山东省<br>
                                <br>370000</td>
                            <td class="not-td">3764</td>
                            <td>58.69%</td>
                            <td>41435</td>
                            <td>19342</td>
                            <td>73.41%</td>
                            <td>1.48</td>
                            <td>38880</td>
                            <td>1395.52</td>
                        </tr>
                        <tr>
                            <td>河南省<br>
                                <br>410000</td>
                            <td class="not-td">2180</td>
                            <td>48.91%</td>
                            <td>27271</td>
                            <td>10580</td>
                            <td>69.33%</td>
                            <td>1.78</td>
                            <td>27099</td>
                            <td>1319.48</td>
                        </tr>
                        <tr>
                            <td>湖北省<br>
                                <br>420000</td>
                            <td class="not-td">3100</td>
                            <td>48.17%</td>
                            <td>30978</td>
                            <td>14695</td>
                            <td>67.72%</td>
                            <td>1.80</td>
                            <td>39040</td>
                            <td>1354.51</td>
                        </tr>
                        <tr>
                            <td>湖南省<br>
                                <br>430000</td>
                            <td class="not-td">3044</td>
                            <td>63.42%</td>
                            <td>29511</td>
                            <td>15942</td>
                            <td>74.82%</td>
                            <td>1.87</td>
                            <td>39823</td>
                            <td>1378.11</td>
                        </tr>
                        <tr>
                            <td>广东省<br>
                                <br>440000</td>
                            <td class="not-td">4338</td>
                            <td>49.79%</td>
                            <td>30440</td>
                            <td>22204</td>
                            <td>73.12%</td>
                            <td>1.47</td>
                            <td>44500</td>
                            <td>1416.79</td>
                        </tr>
                        <tr>
                            <td>广西壮族自治区<br>
                                <br>450000</td>
                            <td class="not-td">1848</td>
                            <td>41.74%</td>
                            <td>10098</td>
                            <td>9348</td>
                            <td>72.26%</td>
                            <td>1.73</td>
                            <td>22352</td>
                            <td>1425.13</td>
                        </tr>
                        <tr>
                            <td>海南省<br>
                                <br>460000</td>
                            <td class="not-td">270</td>
                            <td>73.08%</td>
                            <td>4016</td>
                            <td>1249</td>
                            <td>66.08%</td>
                            <td>2.40</td>
                            <td>4532</td>
                            <td>1327.24</td>
                        </tr>
                        <tr>
                            <td>重庆市<br>
                                <br>500000</td>
                            <td class="not-td">956</td>
                            <td>58.35%</td>
                            <td>16373</td>
                            <td>4601</td>
                            <td>68.75%</td>
                            <td>1.92</td>
                            <td>12847</td>
                            <td>1367.26</td>
                        </tr>
                        <tr>
                            <td>四川省<br>
                                <br>510000</td>
                            <td class="not-td">3700</td>
                            <td>44.04%</td>
                            <td>25980</td>
                            <td>18659</td>
                            <td>72.04%</td>
                            <td>1.94</td>
                            <td>50333</td>
                            <td>1378.71</td>
                        </tr>
                        <tr>
                            <td>贵州省<br>
                                <br>520000</td>
                            <td class="not-td">1518</td>
                            <td>48.72%</td>
                            <td>14339</td>
                            <td>7525</td>
                            <td>70.82%</td>
                            <td>1.88</td>
                            <td>19946</td>
                            <td>1342.92</td>
                        </tr>
                        <tr>
                            <td>云南省<br>
                                <br>530000</td>
                            <td class="not-td">3548</td>
                            <td>42.35%</td>
                            <td>16173</td>
                            <td>6345</td>
                            <td>25.55%</td>
                            <td>0.98</td>
                            <td>24324</td>
                            <td>743.99</td>
                        </tr>
                        <tr>
                            <td>西藏自治区<br>
                                <br>540000</td>
                            <td class="not-td">163</td>
                            <td>75.27%</td>
                            <td>1332</td>
                            <td>722</td>
                            <td>63.28%</td>
                            <td>3.79</td>
                            <td>4320</td>
                            <td>1391.97</td>
                        </tr>
                        <tr>
                            <td>陕西省<br>
                                <br>610000</td>
                            <td class="not-td">2811</td>
                            <td>39.21%</td>
                            <td>24889</td>
                            <td>14757</td>
                            <td>75.00%</td>
                            <td>1.30</td>
                            <td>25616</td>
                            <td>1350.23</td>
                        </tr>
                        <tr>
                            <td>甘肃省<br>
                                <br>620000</td>
                            <td class="not-td">1584</td>
                            <td>47.66%</td>
                            <td>24774</td>
                            <td>7592</td>
                            <td>68.47%</td>
                            <td>2.07</td>
                            <td>22987</td>
                            <td>1317.29</td>
                        </tr>
                        <tr>
                            <td>青海省<br>
                                <br>630000</td>
                            <td class="not-td">295</td>
                            <td>58.86%</td>
                            <td>3419</td>
                            <td>1142</td>
                            <td>55.30%</td>
                            <td>3.79</td>
                            <td>7820</td>
                            <td>1290.40</td>
                        </tr>
                        <tr>
                            <td>宁夏回族自治区<br>
                                <br>640000</td>
                            <td class="not-td">283</td>
                            <td>61.13%</td>
                            <td>2679</td>
                            <td>1387</td>
                            <td>70.02%</td>
                            <td>4.58</td>
                            <td>9082</td>
                            <td>1429.35</td>
                        </tr>
                        <tr>
                            <td>新疆维吾尔自治区<br>
                                <br>650000</td>
                            <td class="not-td">1615</td>
                            <td>55.28%</td>
                            <td>14410</td>
                            <td>8617</td>
                            <td>76.22%</td>
                            <td>1.99</td>
                            <td>22487</td>
                            <td>1344.43</td>
                        </tr>
                        <tr>
                            <td>新疆生产建设兵团<br>
                                <br>BT0000</td>
                            <td class="not-td">204</td>
                            <td>69.82%</td>
                            <td>688</td>
                            <td>980</td>
                            <td>68.63%</td>
                            <td>8.05</td>
                            <td>11502</td>
                            <td>1715.61</td>
                        </tr>
                        </tbody>
                    </table>
                    <table class="table1"  id="table-Stwoweeks">
                        <thead>
                            <tr>
                                <th style="width: 15%;">组织单位名称/编码</th>
                                <th style="width: 10%;">网站个数<i class="jian"></i></th>
                                <th style="width: 10%;">网站不连通<br>（占比）<i class="jian"></i></th>
                                <th style="width: 10%;">首页不可用链接（个）<i class="jian"></i></th>
                                <th style="width: 10%;">首页不更新（<br>网站数）<i class="jian"></i></th>
                                <th style="width: 10%;">首页不更新<br>（占比）<i class="jian"></i></th>
                                <th style="width: 15%;">平均更新量<br>（条/站）<i class="jian"></i></th>
                                <th style="width: 10%;">总更新量<br>（条）<i class="jian"></i></th>
                                <th style="width: 10%;">健康指数<i class="jian"></i></th>
                            </tr>
                        </thead>
                        <tbody>
                        <tr><td>北京市<br><br>110000<td class="not-td">1075</td><td>65.78%</td><td>13514</td><td>10851</td><td>72.10%</td><td>1.51 </td><td>22657</td><td>1396.16 </td></tr>
                        <tr><td>天津市<br><br>120000<td class="not-td">326</td><td>54.97%</td><td>2168</td><td>3079</td><td>67.46%</td><td>4.72 </td><td>21531</td><td>1564.71 </td></tr>
                        <tr><td>河北省<br><br>130000<td class="not-td">1862</td><td>59.18%</td><td>32910</td><td>20004</td><td>76.74%</td><td>1.50 </td><td>39143</td><td>1417.49 </td></tr>
                        <tr><td>山西省<br><br>140000<td class="not-td">1291</td><td>64.49%</td><td>29769</td><td>12088</td><td>66.88%</td><td>1.58 </td><td>28647</td><td>1294.01 </td></tr>
                        <tr><td>内蒙古自治区<br><br>150000<td class="not-td">2013</td><td>79.79%</td><td>41760</td><td>21431</td><td>76.04%</td><td>1.55 </td><td>43691</td><td>1368.85 </td></tr>
                        <tr><td>辽宁省<br><br>210000<td class="not-td">992</td><td>46.95%</td><td>12364</td><td>9230</td><td>66.46%</td><td>2.62 </td><td>36388</td><td>1422.62 </td></tr>
                        <tr><td>吉林省<br><br>220000<td class="not-td">873</td><td>54.18%</td><td>16022</td><td>7844</td><td>64.18%</td><td>3.06 </td><td>37410</td><td>1232.98 </td></tr>
                        <tr><td>黑龙江省<br><br>230000<td class="not-td">1258</td><td>67.59%</td><td>28834</td><td>12363</td><td>70.20%</td><td>1.41 </td><td>24759</td><td>1266.68 </td></tr>
                        <tr><td>上海市<br><br>310000<td class="not-td">644</td><td>59.72%</td><td>16232</td><td>6249</td><td>69.31%</td><td>3.24 </td><td>29172</td><td>1535.47 </td></tr>
                        <tr><td>江苏省<br><br>320000<td class="not-td">3770</td><td>66.31%</td><td>54459</td><td>34858</td><td>66.04%</td><td>2.15 </td><td>113668</td><td>1429.14 </td></tr>
                        <tr><td>浙江省<br><br>330000<td class="not-td">3420</td><td>71.87%</td><td>61589</td><td>30318</td><td>63.32%</td><td>2.68 </td><td>128144</td><td>1382.07 </td></tr>
                        <tr><td>安徽省<br><br>340000<td class="not-td">2171</td><td>79.09%</td><td>36766</td><td>18401</td><td>60.54%</td><td>2.88 </td><td>87451</td><td>1357.70 </td></tr>
                        <tr><td>福建省<br><br>350000<td class="not-td">2511</td><td>72.77%</td><td>64178</td><td>26934</td><td>76.62%</td><td>1.26 </td><td>44168</td><td>1376.05 </td></tr>
                        <tr><td>江西省<br><br>360000<td class="not-td">1699</td><td>65.97%</td><td>27784</td><td>17310</td><td>72.77%</td><td>1.63 </td><td>38800</td><td>1401.86 </td></tr>
                        <tr><td>山东省<br><br>370000<td class="not-td">3764</td><td>69.24%</td><td>84281</td><td>38725</td><td>73.49%</td><td>1.63 </td><td>85924</td><td>1396.31 </td></tr>
                        <tr><td>河南省<br><br>410000<td class="not-td">2180</td><td>56.29%</td><td>55093</td><td>21316</td><td>69.84%</td><td>1.79 </td><td>54484</td><td>1335.36 </td></tr>
                        <tr><td>湖北省<br><br>420000<td class="not-td">3100</td><td>59.60%</td><td>68333</td><td>29422</td><td>67.79%</td><td>1.83 </td><td>79418</td><td>1363.52 </td></tr>
                        <tr><td>湖南省<br><br>430000<td class="not-td">3044</td><td>78.80%</td><td>61680</td><td>32415</td><td>76.06%</td><td>1.94 </td><td>82863</td><td>1429.55 </td></tr>
                        <tr><td>广东省<br><br>440000<td class="not-td">4338</td><td>60.89%</td><td>61338</td><td>44526</td><td>73.32%</td><td>1.48 </td><td>89731</td><td>1436.48 </td></tr>
                        <tr><td>广西壮族自治区<br><br>450000<td class="not-td">1848</td><td>54.02%</td><td>20029</td><td>18563</td><td>71.75%</td><td>1.73 </td><td>44862</td><td>1435.78 </td></tr>
                        <tr><td>海南省<br><br>460000<td class="not-td">270</td><td>83.26%</td><td>7790</td><td>2419</td><td>63.99%</td><td>2.47 </td><td>9340</td><td>1328.21 </td></tr>
                        <tr><td>重庆市<br><br>500000<td class="not-td">956</td><td>66.33%</td><td>32425</td><td>9192</td><td>68.68%</td><td>2.00 </td><td>26723</td><td>1389.98 </td></tr>
                        <tr><td>四川省<br><br>510000<td class="not-td">3700</td><td>52.72%</td><td>51688</td><td>37447</td><td>72.29%</td><td>2.04 </td><td>105615</td><td>1409.09 </td></tr>
                        <tr><td>贵州省<br><br>520000<td class="not-td">1518</td><td>58.67%</td><td>28704</td><td>14915</td><td>70.18%</td><td>1.98 </td><td>42056</td><td>1359.20 </td></tr>
                        <tr><td>云南省<br><br>530000<td class="not-td">3548</td><td>49.25%</td><td>33142</td><td>12722</td><td>25.61%</td><td>0.97 </td><td>48290</td><td>750.51 </td></tr>
                        <tr><td>西藏自治区<br><br>540000<td class="not-td">163</td><td>81.56%</td><td>2570</td><td>1451</td><td>63.58%</td><td>4.16 </td><td>9495</td><td>1416.46 </td></tr>
                        <tr><td>陕西省<br><br>610000<td class="not-td">2811</td><td>46.60%</td><td>50721</td><td>29801</td><td>75.73%</td><td>1.35 </td><td>52994</td><td>1374.87 </td></tr>
                        <tr><td>甘肃省<br><br>620000<td class="not-td">1584</td><td>56.40%</td><td>50665</td><td>15265</td><td>68.84%</td><td>2.09 </td><td>46392</td><td>1334.72 </td></tr>
                        <tr><td>青海省<br><br>630000<td class="not-td">295</td><td>70.24%</td><td>6616</td><td>2191</td><td>53.05%</td><td>3.79 </td><td>15658</td><td>1294.61 </td></tr>
                        <tr><td>宁夏回族自治区<br><br>640000<td class="not-td">283</td><td>69.74%</td><td>5311</td><td>2738</td><td>69.11%</td><td>4.53 </td><td>17953</td><td>1445.74 </td></tr>
                        <tr><td>新疆维吾尔自治区<br><br>650000<td class="not-td">1615</td><td>63.58%</td><td>29054</td><td>17157</td><td>75.88%</td><td>1.99 </td><td>44983</td><td>1365.77 </td></tr>
                        <tr><td>新疆生产建设兵团<br><br>BT0000<td class="not-td">204</td><td>66.56%</td><td>1391</td><td>2016</td><td>70.59%</td><td>9.15 </td><td>26146</td><td>1767.51 </td></tr>
                        </tbody>
                    </table>
                    <table class="table1"  id="table-Smonth">
                        <thead>
                        <tr>
                            <th style="width: 15%;">组织单位名称/编码</th>
                            <th style="width: 10%;">网站个数<i class="jian"></i></th>
                            <th style="width: 10%;">网站不连通<br>（占比）<i class="jian"></i></th>
                            <th style="width: 10%;">首页不可用链接（个）<i class="jian"></i></th>
                            <th style="width: 10%;">首页不更新（<br>网站数）<i class="jian"></i></th>
                            <th style="width: 10%;">首页不更新<br>（占比）<i class="jian"></i></th>
                            <th style="width: 15%;">平均更新量<br>（条/站）<i class="jian"></i></th>
                            <th style="width: 10%;">总更新量<br>（条）<i class="jian"></i></th>
                            <th style="width: 10%;">健康指数<i class="jian"></i></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr><td>北京市<br></br>110000</td><td class='not-td'>1075</td><td>68.31%</td><td>71498</td><td>25465</td><td>78.96%</td><td>1.40 </td><td>45027</td><td>1458.71 </td></tr>
                        <tr><td>天津市<br></br>120000</td><td class='not-td'>326</td><td>57.83%</td><td>13061</td><td>7279</td><td>74.43%</td><td>4.31 </td><td>42142</td><td>1617.37 </td></tr>
                        <tr><td>河北省<br></br>130000</td><td class='not-td'>1862</td><td>58.11%</td><td>145656</td><td>46907</td><td>83.97%</td><td>1.34 </td><td>74802</td><td>1459.81 </td></tr>
                        <tr><td>山西省<br></br>140000</td><td class='not-td'>1291</td><td>61.02%</td><td>133656</td><td>28170</td><td>72.73%</td><td>1.48 </td><td>57297</td><td>1333.73 </td></tr>
                        <tr><td>内蒙古自治区<br></br>150000</td><td class='not-td'>2013</td><td>76.19%</td><td>171416</td><td>50409</td><td>83.47%</td><td>1.52 </td><td>91959</td><td>1432.26 </td></tr>
                        <tr><td>辽宁省<br></br>210000</td><td class='not-td'>992</td><td>44.60%</td><td>55450</td><td>22158</td><td>74.46%</td><td>2.46 </td><td>73169</td><td>1490.78 </td></tr>
                        <tr><td>吉林省<br></br>220000</td><td class='not-td'>873</td><td>61.60%</td><td>75855</td><td>18060</td><td>68.96%</td><td>3.14 </td><td>82235</td><td>1297.02 </td></tr>
                        <tr><td>黑龙江省<br></br>230000</td><td class='not-td'>1258</td><td>63.89%</td><td>133249</td><td>28754</td><td>76.19%</td><td>1.40 </td><td>52822</td><td>1320.19 </td></tr>
                        <tr><td>上海市<br></br>310000</td><td class='not-td'>644</td><td>59.45%</td><td>66674</td><td>15098</td><td>78.15%</td><td>2.79 </td><td>53981</td><td>1563.66 </td></tr>
                        <tr><td>江苏省<br></br>320000</td><td class='not-td'>3770</td><td>63.42%</td><td>250738</td><td>84103</td><td>74.36%</td><td>1.99 </td><td>224905</td><td>1489.28 </td></tr>
                        <tr><td>浙江省<br></br>330000</td><td class='not-td'>3420</td><td>69.90%</td><td>267222</td><td>72569</td><td>70.73%</td><td>2.54 </td><td>260829</td><td>1430.84 </td></tr>
                        <tr><td>安徽省<br></br>340000</td><td class='not-td'>2171</td><td>71.49%</td><td>151694</td><td>43348</td><td>66.56%</td><td>2.75 </td><td>179002</td><td>1395.63 </td></tr>
                        <tr><td>福建省<br></br>350000</td><td class='not-td'>2511</td><td>69.18%</td><td>235637</td><td>62347</td><td>82.77%</td><td>1.22 </td><td>91977</td><td>1427.93 </td></tr>
                        <tr><td>江西省<br></br>360000</td><td class='not-td'>1699</td><td>66.49%</td><td>125230</td><td>40517</td><td>79.49%</td><td>1.58 </td><td>80501</td><td>1456.03 </td></tr>
                        <tr><td>山东省<br></br>370000</td><td class='not-td'>3764</td><td>69.09%</td><td>374670</td><td>90991</td><td>80.58%</td><td>1.47 </td><td>165488</td><td>1463.52 </td></tr>
                        <tr><td>河南省<br></br>410000</td><td class='not-td'>2180</td><td>54.16%</td><td>247660</td><td>50163</td><td>76.70%</td><td>1.67 </td><td>109293</td><td>1382.96 </td></tr>
                        <tr><td>湖北省<br></br>420000</td><td class='not-td'>3100</td><td>57.99%</td><td>252638</td><td>69854</td><td>75.11%</td><td>1.68 </td><td>156416</td><td>1411.50 </td></tr>
                        <tr><td>湖南省<br></br>430000</td><td class='not-td'>3044</td><td>71.14%</td><td>238279</td><td>77543</td><td>84.91%</td><td>1.83 </td><td>166828</td><td>1524.44 </td></tr>
                        <tr><td>广东省<br></br>440000</td><td class='not-td'>4338</td><td>52.30%</td><td>261134</td><td>105648</td><td>81.18%</td><td>1.36 </td><td>177178</td><td>1494.21 </td></tr>
                        <tr><td>广西壮族自治区<br></br>450000</td><td class='not-td'>1848</td><td>54.67%</td><td>107277</td><td>43240</td><td>77.99%</td><td>1.73 </td><td>95816</td><td>1466.60 </td></tr>
                        <tr><td>海南省<br></br>460000</td><td class='not-td'>270</td><td>76.14%</td><td>34882</td><td>5606</td><td>69.21%</td><td>2.30 </td><td>18657</td><td>1366.81 </td></tr>
                        <tr><td>重庆市<br></br>500000</td><td class='not-td'>956</td><td>60.20%</td><td>132046</td><td>21652</td><td>75.50%</td><td>1.91 </td><td>54839</td><td>1438.08 </td></tr>
                        <tr><td>四川省<br></br>510000</td><td class='not-td'>3700</td><td>57.09%</td><td>222240</td><td>88440</td><td>79.68%</td><td>1.94 </td><td>215779</td><td>1474.59 </td></tr>
                        <tr><td>贵州省<br></br>520000</td><td class='not-td'>1518</td><td>60.58%</td><td>111424</td><td>35013</td><td>76.88%</td><td>1.85 </td><td>84392</td><td>1414.00 </td></tr>
                        <tr><td>云南省<br></br>530000</td><td class='not-td'>3548</td><td>54.74%</td><td>132877</td><td>29756</td><td>27.96%</td><td>0.93 </td><td>99206</td><td>767.58 </td></tr>
                        <tr><td>西藏自治区<br></br>540000</td><td class='not-td'>163</td><td>80.92%</td><td>13041</td><td>3484</td><td>71.25%</td><td>4.07 </td><td>19915</td><td>1448.03 </td></tr>
                        <tr><td>陕西省<br></br>610000</td><td class='not-td'>2811</td><td>41.36%</td><td>231910</td><td>69530</td><td>82.45%</td><td>1.29 </td><td>109148</td><td>1432.48 </td></tr>
                        <tr><td>甘肃省<br></br>620000</td><td class='not-td'>1584</td><td>56.81%</td><td>182908</td><td>35942</td><td>75.64%</td><td>2.01 </td><td>95695</td><td>1385.45 </td></tr>
                        <tr><td>青海省<br></br>630000</td><td class='not-td'>295</td><td>69.00%</td><td>30459</td><td>5213</td><td>58.90%</td><td>3.67 </td><td>32477</td><td>1331.75 </td></tr>
                        <tr><td>宁夏回族自治区<br></br>640000</td><td class='not-td'>283</td><td>67.54%</td><td>23003</td><td>6414</td><td>75.55%</td><td>4.10 </td><td>34792</td><td>1489.13 </td></tr>
                        <tr><td>新疆维吾尔自治区<br></br>650000</td><td class='not-td'>1615</td><td>64.21%</td><td>126061</td><td>39859</td><td>82.27%</td><td>1.89 </td><td>91738</td><td>1423.22 </td></tr>
                        <tr><td>新疆生产建设兵团<br></br>BT0000</td><td class='not-td'>204</td><td>63.35%</td><td>16669</td><td>4942</td><td>80.75%</td><td>8.00 </td><td>48983</td><td>1776.31 </td></tr>
                        </tbody>
                    </table>

                    <table class="table1" id="table-Btwodays">
                        <thead>
                        <tr>
                            <th style="width: 15%;">组织单位名称/编码</th>
                            <th style="width: 10%;">网站个数<i class="jian"></i></th>
                            <th style="width: 10%;">网站不连通<br>（占比）<i class="jian"></i></th>
                            <th style="width: 10%;">首页不可用链接（个）<i class="jian"></i></th>
                            <th style="width: 10%;">首页不更新（<br>网站数）<i class="jian"></i></th>
                            <th style="width: 10%;">首页不更新<br>（占比）<i class="jian"></i></th>
                            <th style="width: 15%;">平均更新量<br>（条/站）<i class="jian"></i></th>
                            <th style="width: 10%;">总更新量<br>（条）<i class="jian"></i></th>
                            <th style="width: 10%;">健康指数<i class="jian"></i></th>
                        </tr>
                        </thead>
                        <tbody>

<!--                         <tr style="display:none;"><td>中华人民共和国国务院办公厅<br><br>bm0100</td><td class="not-td">1</td><td>0</td><td>0</td><td>0</td><td>0.00%</td><td>119.00 </td><td>119</td><td>1963.4</td></tr> -->
                        <tr><td>中华人民共和国外交部<br><br>bm0200</td><td class="not-td">9</td><td>52.08%</td><td>4</td><td>7</td><td>77.78%</td><td>1.78 </td><td>16</td><td>1803.71</td></tr>
                        <tr><td>中华人民共和国国家发展和改革委员会<br><br>bm0400</td><td class="not-td">7</td><td>0</td><td>0</td><td>7</td><td>100.00%</td><td>0.14 </td><td>1</td><td>1498.71</td></tr>
                        <tr><td>中华人民共和国教育部<br><br>bm0500</td><td class="not-td">50</td><td>68.06%</td><td>51</td><td>49</td><td>98.00%</td><td>0.00 </td><td>0</td><td>1440.47</td></tr>
                        <tr><td>中华人民共和国科学技术部<br><br>bm0600</td><td class="not-td">7</td><td>0</td><td>1</td><td>7</td><td>100.00%</td><td>0.00 </td><td>0</td><td>1518.2</td></tr>
                        <tr><td>中华人民共和国工业和信息化部<br><br>bm0700</td><td class="not-td">44</td><td>76.70%</td><td>36</td><td>43</td><td>97.73%</td><td>0.00 </td><td>0</td><td>1616.67</td></tr>
                        <tr><td>中华人民共和国国家民族事务委员会<br><br>bm0800</td><td class="not-td">14</td><td>100.00%</td><td>5</td><td>14</td><td>100.00%</td><td>1.79 </td><td>25</td><td>1436.17</td></tr>
                        <tr><td>中华人民共和国公安部<br><br>bm0900</td><td class="not-td">15</td><td>94.20%</td><td>23</td><td>13</td><td>86.67%</td><td>0.80 </td><td>12</td><td>1631.12</td></tr>
                        <tr><td>中华人民共和国民政部<br><br>bm1200</td><td class="not-td">8</td><td>91.47%</td><td>8</td><td>7</td><td>87.50%</td><td>0.00 </td><td>0</td><td>1411.75</td></tr>
                        <tr><td>中华人民共和国司法部<br><br>bm1300</td><td class="not-td">2</td><td>0</td><td>2</td><td>2</td><td>100.00%</td><td>49.50 </td><td>99</td><td>1665.1</td></tr>
                        <tr><td>中华人民共和国财政部<br><br>bm1400</td><td class="not-td">2</td><td>100.00%</td><td>21</td><td>1</td><td>50.00%</td><td>2.00 </td><td>4</td><td>1483.3</td></tr>
                        <tr><td>中华人民共和国人力资源和社会保障部<br><br>bm1500</td><td class="not-td">7</td><td>100.00%</td><td>12</td><td>6</td><td>85.71%</td><td>0.00 </td><td>0</td><td>1447.2</td></tr>
                        <tr><td>中华人民共和国国土资源部<br><br>bm1600</td><td class="not-td">2</td><td>0</td><td>12</td><td>2</td><td>100.00%</td><td>1.50 </td><td>3</td><td>1572.7</td></tr>
                        <tr><td>中华人民共和国环境保护部<br><br>bm1700</td><td class="not-td">9</td><td>42.50%</td><td>14</td><td>7</td><td>77.78%</td><td>0.00 </td><td>0</td><td>1273.73</td></tr>
                        <tr><td>中华人民共和国住房和城乡建设部<br><br>bm1800</td><td class="not-td">4</td><td>100.00%</td><td>5</td><td>3</td><td>75.00%</td><td>16.00 </td><td>64</td><td>1209.85</td></tr>
                        <tr><td>中华人民共和国交通运输部<br><br>bm1900</td><td class="not-td">104</td><td>75.46%</td><td>245</td><td>70</td><td>67.31%</td><td>0.17 </td><td>18</td><td>1321.99</td></tr>
                        <tr><td>中华人民共和国水利部<br><br>bm2000</td><td class="not-td">36</td><td>63.69%</td><td>35</td><td>34</td><td>94.44%</td><td>0.00 </td><td>0</td><td>1480.68</td></tr>
                        <tr><td>中华人民共和国农业部<br><br>bm2100</td><td class="not-td">11</td><td>17.71%</td><td>10</td><td>11</td><td>100.00%</td><td>0.00 </td><td>0</td><td>1807.58</td></tr>
                        <tr><td>中华人民共和国商务部<br><br>bm2200</td><td class="not-td">9</td><td>99.48%</td><td>1</td><td>8</td><td>88.89%</td><td>0.00 </td><td>0</td><td>1372.09</td></tr>
                        <tr><td>中华人民共和国文化部<br><br>bm2300</td><td class="not-td">3</td><td>94.58%</td><td>1</td><td>3</td><td>100.00%</td><td>0.00 </td><td>0</td><td>1544.47</td></tr>
                        <tr><td>中华人民共和国国家卫生和计划生育委员会<br><br>bm2400</td><td class="not-td">3</td><td>0</td><td>1</td><td>3</td><td>100.00%</td><td>0.00 </td><td>0</td><td>1607.07</td></tr>
                        <tr><td>中国人民银行<br><br>bm2500</td><td class="not-td">3</td><td>0</td><td>0</td><td>2</td><td>66.67%</td><td>0.00 </td><td>0</td><td>1254.53</td></tr>
                        <tr><td>中华人民共和国审计署<br><br>bm2600</td><td class="not-td">1</td><td>0</td><td>0</td><td>1</td><td>100.00%</td><td>0.00 </td><td>0</td><td>1900</td></tr>
                        <tr><td>国务院国有资产监督委员会<br><br>bm2700</td><td class="not-td">3</td><td>0</td><td>0</td><td>3</td><td>100.00%</td><td>0.00 </td><td>0</td><td>1609.13</td></tr>
                        <tr><td>中华人民共和国海关总署<br><br>bm2800</td><td class="not-td">45</td><td>13.03%</td><td>69</td><td>45</td><td>100.00%</td><td>0.87 </td><td>39</td><td>1427.61</td></tr>
                        <tr><td>国家税务总局<br><br>bm2900</td><td class="not-td">487</td><td>99.13%</td><td>436</td><td>452</td><td>92.81%</td><td>0.00 </td><td>0</td><td>1460.74</td></tr>
                        <tr><td>国家工商行政管理总局<br><br>bm3000</td><td class="not-td">12</td><td>0</td><td>1</td><td>8</td><td>66.67%</td><td>0.00 </td><td>0</td><td>1137.65</td></tr>
                        <tr><td>国家质量监督检验检疫总局<br><br>bm3100</td><td class="not-td">366</td><td>77.44%</td><td>292</td><td>353</td><td>96.45%</td><td>0.00 </td><td>0</td><td>1422.01</td></tr>
                        <tr><td>国家新闻出版广电总局<br><br>bm3200</td><td class="not-td">2</td><td>100.00%</td><td>3</td><td>2</td><td>100.00%</td><td>0.00 </td><td>0</td><td>1416.6</td></tr>
                        <tr><td>国家体育总局<br><br>bm3300</td><td class="not-td">3</td><td>100.00%</td><td>0</td><td>3</td><td>100.00%</td><td>1.67 </td><td>5</td><td>1466.67</td></tr>
                        <tr><td>国家安全生产监督管理总局<br><br>bm3400</td><td class="not-td">69</td><td>81.79%</td><td>53</td><td>55</td><td>79.71%</td><td>0.00 </td><td>0</td><td>1261.41</td></tr>
                        <tr><td>国家食品药品监督管理总局<br><br>bm3500</td><td class="not-td">1</td><td>0</td><td>2</td><td>1</td><td>100.00%</td><td>0.00 </td><td>0</td><td>1833.4</td></tr>
                        <tr><td>国家统计局<br><br>bm3600</td><td class="not-td">48</td><td>62.78%</td><td>22</td><td>42</td><td>87.50%</td><td>0.10 </td><td>5</td><td>1302.3</td></tr>
                        <tr><td>国家林业局<br><br>bm3700</td><td class="not-td">2</td><td>16.07%</td><td>0</td><td>0</td><td>0.00%</td><td>0.00 </td><td>0</td><td>1195.5</td></tr>
                        <tr><td>国家知识产权局<br><br>bm3800</td><td class="not-td">8</td><td>100.00%</td><td>0</td><td>6</td><td>75.00%</td><td>6.63 </td><td>53</td><td>1220.85</td></tr>
                        <tr><td>国家旅游局<br><br>bm3900</td><td class="not-td">1</td><td>100.00%</td><td>0</td><td>0</td><td>0.00%</td><td>0.00 </td><td>0</td><td>1710</td></tr>
                        <tr><td>国家宗教事务局<br><br>bm4000</td><td class="not-td">1</td><td>100.00%</td><td>0</td><td>1</td><td>100.00%</td><td>0.00 </td><td>0</td><td>1566.6</td></tr>
                        <tr><td>国务院参事室<br><br>bm4100</td><td class="not-td">1</td><td>0</td><td>3</td><td>1</td><td>100.00%</td><td>0.00 </td><td>0</td><td>1800</td></tr>
                        <tr><td>国家机关事务管理局<br><br>bm4200</td><td class="not-td">3</td><td>0</td><td>3</td><td>3</td><td>100.00%</td><td>0.00 </td><td>0</td><td>1594</td></tr>
                        <tr><td>国务院侨务办公室<br><br>bm4300</td><td class="not-td">1</td><td>0</td><td>0</td><td>1</td><td>100.00%</td><td>0.00 </td><td>0</td><td>1900</td></tr>
                        <tr><td>国务院港澳事务办公室<br><br>bm4400</td><td class="not-td">1</td><td>0</td><td>2</td><td>1</td><td>100.00%</td><td>0.00 </td><td>0</td><td>1800</td></tr>
                        <tr><td>国务院法制办公室<br><br>bm4500</td><td class="not-td">1</td><td>0</td><td>0</td><td>1</td><td>100.00%</td><td>18.00 </td><td>18</td><td>1900</td></tr>
                        <tr><td>中国科学院<br><br>bm4800</td><td class="not-td">33</td><td>89.88%</td><td>9</td><td>27</td><td>81.82%</td><td>2.21 </td><td>73</td><td>1390.92</td></tr>
                        <tr><td>中国社会科学院<br><br>bm4900</td><td class="not-td">2</td><td>0</td><td>3</td><td>1</td><td>50.00%</td><td>0.00 </td><td>0</td><td>1703.8</td></tr>
                        <tr><td>中国工程院<br><br>bm5000</td><td class="not-td">1</td><td>100.00%</td><td>0</td><td>1</td><td>100.00%</td><td>0.00 </td><td>0</td><td>1566.6</td></tr>
                        <tr><td>国务院发展研究中心<br><br>bm5100</td><td class="not-td">1</td><td>1.04%</td><td>0</td><td>1</td><td>100.00%</td><td>0.00 </td><td>0</td><td>1733.4</td></tr>
                        <tr><td>国家行政学院<br><br>bm5200</td><td class="not-td">1</td><td>1.04%</td><td>0</td><td>1</td><td>100.00%</td><td>85.00 </td><td>85</td><td>1883.4</td></tr>
                        <tr><td>中国地震局<br><br>bm5300</td><td class="not-td">31</td><td>95.89%</td><td>54</td><td>18</td><td>58.06%</td><td>4.39 </td><td>136</td><td>1338.68</td></tr>
                        <tr><td>中国气象局<br><br>bm5400</td><td class="not-td">152</td><td>47.37%</td><td>140</td><td>115</td><td>75.66%</td><td>0.00 </td><td>0</td><td>1409.73</td></tr>
                        <tr><td>中国银行业监督管理委员会<br><br>bm5500</td><td class="not-td">1</td><td>0</td><td>0</td><td>1</td><td>100.00%</td><td>1.00 </td><td>1</td><td>1900</td></tr>
                        <tr><td>中国证券监督管理委员会<br><br>bm5600</td><td class="not-td">1</td><td>95.24%</td><td>0</td><td>0</td><td>0.00%</td><td>0.00 </td><td>0</td><td>1566.6</td></tr>
                        <tr><td>中国保险监督管理委员会<br><br>bm5700</td><td class="not-td">42</td><td>0</td><td>2</td><td>42</td><td>100.00%</td><td>0.00 </td><td>0</td><td>1455.22</td></tr>
                        <tr><td>全国社会保障基金理事会<br><br>bm5800</td><td class="not-td">1</td><td>100.00%</td><td>0</td><td>1</td><td>100.00%</td><td>0.00 </td><td>0</td><td>1566.6</td></tr>
                        <tr><td>国家自然科学基金委员会<br><br>bm5900</td><td class="not-td">1</td><td>0</td><td>1</td><td>1</td><td>100.00%</td><td>14.00 </td><td>14</td><td>1866.6</td></tr>
                        <tr><td>国家信访局<br><br>bm6000</td><td class="not-td">1</td><td>0</td><td>1</td><td>0</td><td>0.00%</td><td>0.00 </td><td>0</td><td>1600</td></tr>
                        <tr><td>国家粮食局<br><br>bm6100</td><td class="not-td">1</td><td>22.92%</td><td>0</td><td>1</td><td>100.00%</td><td>4.00 </td><td>4</td><td>1566.6</td></tr>
                        <tr><td>国家能源局<br><br>bm6200</td><td class="not-td">20</td><td>24.09%</td><td>4</td><td>18</td><td>90.00%</td><td>0.00 </td><td>0</td><td>1376.08</td></tr>
                        <tr><td>国家国防科技工业局<br><br>bm6300</td><td class="not-td">3</td><td>1.30%</td><td>1</td><td>3</td><td>100.00%</td><td>0.00 </td><td>0</td><td>1415.13</td></tr>
                        <tr><td>国家烟草专卖局<br><br>bm6400</td><td class="not-td">78</td><td>15.20%</td><td>5</td><td>69</td><td>88.46%</td><td>0.00 </td><td>0</td><td>1446.57</td></tr>
                        <tr><td>国家外国专家局<br><br>bm6500</td><td class="not-td">1</td><td>100.00%</td><td>2</td><td>1</td><td>100.00%</td><td>0.00 </td><td>0</td><td>1500</td></tr>
                        <tr><td>国家公务员局<br><br>bm6600</td><td class="not-td">1</td><td>0</td><td>0</td><td>1</td><td>100.00%</td><td>10.00 </td><td>10</td><td>1900</td></tr>
                        <tr><td>国家海洋局<br><br>bm6700</td><td class="not-td">14</td><td>90.42%</td><td>8</td><td>12</td><td>85.71%</td><td>0.00 </td><td>0</td><td>1358.04</td></tr>
                        <tr><td>国家测绘地理信息局<br><br>bm6800</td><td class="not-td">5</td><td>68.06%</td><td>11</td><td>5</td><td>100.00%</td><td>0.00 </td><td>0</td><td>1409.68</td></tr>
                        <tr><td>国家铁路局<br><br>bm6900</td><td class="not-td">1</td><td>0</td><td>0</td><td>1</td><td>100.00%</td><td>0.00 </td><td>0</td><td>1900</td></tr>
                        <tr><td>中国民用航空局<br><br>bm7000</td><td class="not-td">8</td><td>99.98%</td><td>0</td><td>8</td><td>100.00%</td><td>0.00 </td><td>0</td><td>1203.75</td></tr>
                        <tr><td>国家邮政局<br><br>bm7100</td><td class="not-td">367</td><td>61.48%</td><td>31</td><td>366</td><td>99.73%</td><td>0.00 </td><td>0</td><td>1131.01</td></tr>
                        <tr><td>国家文物局<br><br>bm7200</td><td class="not-td">1</td><td>100.00%</td><td>0</td><td>1</td><td>100.00%</td><td>0.00 </td><td>0</td><td>1566.6</td></tr>
                        <tr><td>国家中医药管理局<br><br>bm7300</td><td class="not-td">1</td><td>92.36%</td><td>0</td><td>1</td><td>100.00%</td><td>0.00 </td><td>0</td><td>900</td></tr>
                        <tr><td>国家外汇管理局<br><br>bm7400</td><td class="not-td">37</td><td>0</td><td>0</td><td>37</td><td>100.00%</td><td>0.00 </td><td>0</td><td>1486.52</td></tr>
                        <tr><td>国务院扶贫开发领导小组办公室<br><br>bm7600</td><td class="not-td">1</td><td>100.00%</td><td>2</td><td>1</td><td>100.00%</td><td>0.00 </td><td>0</td><td>1500</td></tr>
                        <tr><td>国务院三峡工程建设委员会办公室<br><br>bm7700</td><td class="not-td">1</td><td>0</td><td>4</td><td>1</td><td>100.00%</td><td>0.00 </td><td>0</td><td>1766.6</td></tr>
                        <tr><td>国务院南水北调工程建设委员会办公室<br><br>bm7800</td><td class="not-td">1</td><td>2.08%</td><td>3</td><td>1</td><td>100.00%</td><td>0.00 </td><td>0</td><td>1733.4</td></tr>
                        <tr><td>国务院台湾事务办公室<br><br>bm8000</td><td class="not-td">1</td><td>0</td><td>0</td><td>1</td><td>100.00%</td><td>0.00 </td><td>0</td><td>1463.6</td></tr>
                        </tbody>
                    </table>
                    <table class="table1"  id="table-Bweek">
                    <thead>
                    <tr>
                        <th style="width: 15%;">组织单位名称/编码</th>
                        <th style="width: 10%;">网站个数<i class="jian"></i></th>
                        <th style="width: 10%;">网站不连通<br>（占比）<i class="jian"></i></th>
                        <th style="width: 10%;">首页不可用链接（个）<i class="jian"></i></th>
                        <th style="width: 10%;">首页不更新（<br>网站数）<i class="jian"></i></th>
                        <th style="width: 10%;">首页不更新<br>（占比）<i class="jian"></i></th>
                        <th style="width: 15%;">平均更新量<br>（条/站）<i class="jian"></i></th>
                        <th style="width: 10%;">总更新量<br>（条）<i class="jian"></i></th>
                        <th style="width: 10%;">健康指数<i class="jian"></i></th>
                    </tr>
                    </thead>
                    <tbody>
<!--                     <tr style="display:none;"> -->
<!--                         <td >中华人民共和国国务院办公厅<br> -->
<!--                             <br>bm0100</td> -->
<!--                         <td class="not-td">1</td> -->
<!--                         <td>0</td> -->
<!--                         <td>0</td> -->
<!--                         <td>0</td> -->
<!--                         <td>0.00%</td> -->
<!--                         <td>171.71</td> -->
<!--                         <td>1202</td> -->
<!--                         <td>2002.83</td> -->
<!--                     </tr> -->
                    <tr>
                        <td>中华人民共和国外交部<br>
                            <br>bm0200</td>
                        <td class="not-td">9</td>
                        <td>70.83%</td>
                        <td>28</td>
                        <td>40</td>
                        <td>63.49%</td>
                        <td>12.92</td>
                        <td>814</td>
                        <td>1815.57</td>
                    </tr>
                    <tr>
                        <td>中华人民共和国国家发展和改革委员会<br>
                            <br>bm0400</td>
                        <td class="not-td">7</td>
                        <td>1.04%</td>
                        <td>2</td>
                        <td>32</td>
                        <td>65.31%</td>
                        <td>5.51</td>
                        <td>270</td>
                        <td>1472.38</td>
                    </tr>
                    <tr>
                        <td>中华人民共和国教育部<br>
                            <br>bm0500</td>
                        <td class="not-td">50</td>
                        <td>54.96%</td>
                        <td>354</td>
                        <td>277</td>
                        <td>79.14%</td>
                        <td>1.62</td>
                        <td>567</td>
                        <td>1441.43</td>
                    </tr>
                    <tr>
                        <td>中华人民共和国科学技术部<br>
                            <br>bm0600</td>
                        <td class="not-td">7</td>
                        <td>4.17%</td>
                        <td>2</td>
                        <td>36</td>
                        <td>73.47%</td>
                        <td>3.27</td>
                        <td>160</td>
                        <td>1512.32</td>
                    </tr>
                    <tr>
                        <td>中华人民共和国工业和信息化部<br>
                            <br>bm0700</td>
                        <td class="not-td">44</td>
                        <td>60.28%</td>
                        <td>233</td>
                        <td>225</td>
                        <td>73.05%</td>
                        <td>4.88</td>
                        <td>1502</td>
                        <td>1594.30</td>
                    </tr>
                    <tr>
                        <td>中华人民共和国国家民族事务委员会<br>
                            <br>bm0800</td>
                        <td class="not-td">14</td>
                        <td>82.64%</td>
                        <td>35</td>
                        <td>41</td>
                        <td>41.84%</td>
                        <td>5.33</td>
                        <td>522</td>
                        <td>1450.22</td>
                    </tr>
                    <tr>
                        <td>中华人民共和国公安部<br>
                            <br>bm0900</td>
                        <td class="not-td">15</td>
                        <td>73.73%</td>
                        <td>83</td>
                        <td>70</td>
                        <td>66.67%</td>
                        <td>22.75</td>
                        <td>2389</td>
                        <td>1668.32</td>
                    </tr>
                    <tr>
                        <td>中华人民共和国民政部<br>
                            <br>bm1200</td>
                        <td class="not-td">8</td>
                        <td>76.19%</td>
                        <td>60</td>
                        <td>22</td>
                        <td>39.29%</td>
                        <td>9.04</td>
                        <td>506</td>
                        <td>1390.08</td>
                    </tr>
                    <tr>
                        <td>中华人民共和国司法部<br>
                            <br>bm1300</td>
                        <td class="not-td">2</td>
                        <td>1.47%</td>
                        <td>14</td>
                        <td>4</td>
                        <td>28.57%</td>
                        <td>12.79</td>
                        <td>179</td>
                        <td>1553.31</td>
                    </tr>
                    <tr>
                        <td>中华人民共和国财政部<br>
                            <br>bm1400</td>
                        <td class="not-td">2</td>
                        <td>24.93%</td>
                        <td>147</td>
                        <td>2</td>
                        <td>14.29%</td>
                        <td>160.79</td>
                        <td>2251</td>
                        <td>1441.07</td>
                    </tr>
                    <tr>
                        <td>中华人民共和国人力资源和社会保障部<br>
                            <br>bm1500</td>
                        <td class="not-td">7</td>
                        <td>86.84%</td>
                        <td>78</td>
                        <td>22</td>
                        <td>44.90%</td>
                        <td>29.65</td>
                        <td>1453</td>
                        <td>1464.51</td>
                    </tr>
                    <tr>
                        <td>中华人民共和国国土资源部<br>
                            <br>bm1600</td>
                        <td class="not-td">2</td>
                        <td>0</td>
                        <td>70</td>
                        <td>3</td>
                        <td>21.43%</td>
                        <td>39.86</td>
                        <td>558</td>
                        <td>1561.91</td>
                    </tr>
                    <tr>
                        <td>中华人民共和国环境保护部<br>
                            <br>bm1700</td>
                        <td class="not-td">9</td>
                        <td>19.10%</td>
                        <td>98</td>
                        <td>41</td>
                        <td>65.08%</td>
                        <td>1.25</td>
                        <td>79</td>
                        <td>1306.59</td>
                    </tr>
                    <tr>
                        <td>中华人民共和国住房和城乡建设部<br>
                            <br>bm1800</td>
                        <td class="not-td">4</td>
                        <td>100.00%</td>
                        <td>39</td>
                        <td>14</td>
                        <td>50.00%</td>
                        <td>1.79</td>
                        <td>50</td>
                        <td>1196.79</td>
                    </tr>
                    <tr>
                        <td>中华人民共和国交通运输部<br>
                            <br>bm1900</td>
                        <td class="not-td">104</td>
                        <td>64.21%</td>
                        <td>1710</td>
                        <td>365</td>
                        <td>50.14%</td>
                        <td>3.38</td>
                        <td>2460</td>
                        <td>1323.45</td>
                    </tr>
                    <tr>
                        <td>中华人民共和国水利部<br>
                            <br>bm2000</td>
                        <td class="not-td">36</td>
                        <td>25.69%</td>
                        <td>245</td>
                        <td>138</td>
                        <td>54.76%</td>
                        <td>5.95</td>
                        <td>1499</td>
                        <td>1465.49</td>
                    </tr>
                    <tr>
                        <td>中华人民共和国农业部<br>
                            <br>bm2100</td>
                        <td class="not-td">11</td>
                        <td>17.34%</td>
                        <td>71</td>
                        <td>44</td>
                        <td>57.14%</td>
                        <td>40.90</td>
                        <td>3149</td>
                        <td>1792.58</td>
                    </tr>
                    <tr>
                        <td>中华人民共和国商务部<br>
                            <br>bm2200</td>
                        <td class="not-td">9</td>
                        <td>17.33%</td>
                        <td>7</td>
                        <td>42</td>
                        <td>66.67%</td>
                        <td>56.73</td>
                        <td>3574</td>
                        <td>1370.71</td>
                    </tr>
                    <tr>
                        <td>中华人民共和国文化部<br>
                            <br>bm2300</td>
                        <td class="not-td">3</td>
                        <td>48.01%</td>
                        <td>7</td>
                        <td>6</td>
                        <td>28.57%</td>
                        <td>25.38</td>
                        <td>533</td>
                        <td>1516.02</td>
                    </tr>
                    <tr>
                        <td>中华人民共和国国家卫生和计划生育委员会<br>
                            <br>bm2400</td>
                        <td class="not-td">3</td>
                        <td>40.63%</td>
                        <td>7</td>
                        <td>10</td>
                        <td>47.62%</td>
                        <td>4.67</td>
                        <td>98</td>
                        <td>1830.84</td>
                    </tr>
                    <tr>
                        <td>中国人民银行<br>
                            <br>bm2500</td>
                        <td class="not-td">3</td>
                        <td>0</td>
                        <td>0</td>
                        <td>14</td>
                        <td>66.67%</td>
                        <td>0.00</td>
                        <td>0</td>
                        <td>1235.06</td>
                    </tr>
                    <tr>
                        <td>中华人民共和国审计署<br>
                            <br>bm2600</td>
                        <td class="not-td">1</td>
                        <td>0</td>
                        <td>0</td>
                        <td>2</td>
                        <td>28.57%</td>
                        <td>30.57</td>
                        <td>214</td>
                        <td>1890.46</td>
                    </tr>
                    <tr>
                        <td>国务院国有资产监督委员会<br>
                            <br>bm2700</td>
                        <td class="not-td">3</td>
                        <td>0</td>
                        <td>0</td>
                        <td>15</td>
                        <td>71.43%</td>
                        <td>5.57</td>
                        <td>117</td>
                        <td>1601.42</td>
                    </tr>
                    <tr>
                        <td>中华人民共和国海关总署<br>
                            <br>bm2800</td>
                        <td class="not-td">45</td>
                        <td>12.36%</td>
                        <td>395</td>
                        <td>203</td>
                        <td>64.44%</td>
                        <td>1.69</td>
                        <td>532</td>
                        <td>1416.77</td>
                    </tr>
                    <tr>
                        <td>国家税务总局<br>
                            <br>bm2900</td>
                        <td class="not-td">487</td>
                        <td>78.15%</td>
                        <td>3012</td>
                        <td>2204</td>
                        <td>64.65%</td>
                        <td>2.26</td>
                        <td>7702</td>
                        <td>1457.54</td>
                    </tr>
                    <tr>
                        <td>国家工商行政管理总局<br>
                            <br>bm3000</td>
                        <td class="not-td">12</td>
                        <td>1.04%</td>
                        <td>7</td>
                        <td>45</td>
                        <td>53.57%</td>
                        <td>6.75</td>
                        <td>567</td>
                        <td>1132.99</td>
                    </tr>
                    <tr>
                        <td>国家质量监督检验检疫总局<br>
                            <br>bm3100</td>
                        <td class="not-td">366</td>
                        <td>57.91%</td>
                        <td>1897</td>
                        <td>1924</td>
                        <td>75.10%</td>
                        <td>0.94</td>
                        <td>2399</td>
                        <td>1427.38</td>
                    </tr>
                    <tr>
                        <td>国家新闻出版广电总局<br>
                            <br>bm3200</td>
                        <td class="not-td">2</td>
                        <td>87.31%</td>
                        <td>24</td>
                        <td>9</td>
                        <td>64.29%</td>
                        <td>2.50</td>
                        <td>35</td>
                        <td>1386.24</td>
                    </tr>
                    <tr>
                        <td>国家体育总局<br>
                            <br>bm3300</td>
                        <td class="not-td">3</td>
                        <td>86.63%</td>
                        <td>0</td>
                        <td>14</td>
                        <td>66.67%</td>
                        <td>16.05</td>
                        <td>337</td>
                        <td>1502.35</td>
                    </tr>
                    <tr>
                        <td>国家安全生产监督管理总局<br>
                            <br>bm3400</td>
                        <td class="not-td">69</td>
                        <td>51.86%</td>
                        <td>375</td>
                        <td>326</td>
                        <td>67.49%</td>
                        <td>1.05</td>
                        <td>508</td>
                        <td>1264.54</td>
                    </tr>
                    <tr>
                        <td>国家食品药品监督管理总局<br>
                            <br>bm3500</td>
                        <td class="not-td">1</td>
                        <td>2.08%</td>
                        <td>16</td>
                        <td>2</td>
                        <td>28.57%</td>
                        <td>8.29</td>
                        <td>58</td>
                        <td>1813.54</td>
                    </tr>
                    <tr>
                        <td>国家统计局<br>
                            <br>bm3600</td>
                        <td class="not-td">48</td>
                        <td>44.57%</td>
                        <td>145</td>
                        <td>245</td>
                        <td>72.92%</td>
                        <td>0.64</td>
                        <td>214</td>
                        <td>1307.64</td>
                    </tr>
                    <tr>
                        <td>国家林业局<br>
                            <br>bm3700</td>
                        <td class="not-td">2</td>
                        <td>37.72%</td>
                        <td>17</td>
                        <td>0</td>
                        <td>0.00%</td>
                        <td>27.93</td>
                        <td>391</td>
                        <td>1481.34</td>
                    </tr>
                    <tr>
                        <td>国家知识产权局<br>
                            <br>bm3800</td>
                        <td class="not-td">8</td>
                        <td>86.63%</td>
                        <td>0</td>
                        <td>23</td>
                        <td>41.07%</td>
                        <td>6.45</td>
                        <td>361</td>
                        <td>1222.02</td>
                    </tr>
                    <tr>
                        <td>国家旅游局<br>
                            <br>bm3900</td>
                        <td class="not-td">1</td>
                        <td>85.45%</td>
                        <td>6</td>
                        <td>0</td>
                        <td>0.00%</td>
                        <td>87.86</td>
                        <td>615</td>
                        <td>1648.17</td>
                    </tr>
                    <tr>
                        <td>国家宗教事务局<br>
                            <br>bm4000</td>
                        <td class="not-td">1</td>
                        <td>86.63%</td>
                        <td>0</td>
                        <td>2</td>
                        <td>28.57%</td>
                        <td>18.57</td>
                        <td>130</td>
                        <td>1566.57</td>
                    </tr>
                    <tr>
                        <td>国务院参事室<br>
                            <br>bm4100</td>
                        <td class="not-td">1</td>
                        <td>0</td>
                        <td>21</td>
                        <td>2</td>
                        <td>28.57%</td>
                        <td>8.71</td>
                        <td>61</td>
                        <td>1740.26</td>
                    </tr>
                    <tr>
                        <td>国家机关事务管理局<br>
                            <br>bm4200</td>
                        <td class="not-td">3</td>
                        <td>2.08%</td>
                        <td>21</td>
                        <td>15</td>
                        <td>71.43%</td>
                        <td>35.14</td>
                        <td>738</td>
                        <td>1597.80</td>
                    </tr>
                    <tr>
                        <td>国务院侨务办公室<br>
                            <br>bm4300</td>
                        <td class="not-td">1</td>
                        <td>0</td>
                        <td>0</td>
                        <td>2</td>
                        <td>28.57%</td>
                        <td>25.57</td>
                        <td>179</td>
                        <td>1856.97</td>
                    </tr>
                    <tr>
                        <td>国务院港澳事务办公室<br>
                            <br>bm4400</td>
                        <td class="not-td">1</td>
                        <td>0</td>
                        <td>14</td>
                        <td>5</td>
                        <td>71.43%</td>
                        <td>0.43</td>
                        <td>3</td>
                        <td>1748.91</td>
                    </tr>
                    <tr>
                        <td>国务院法制办公室<br>
                            <br>bm4500</td>
                        <td class="not-td">1</td>
                        <td>0</td>
                        <td>0</td>
                        <td>6</td>
                        <td>85.71%</td>
                        <td>1.71</td>
                        <td>12</td>
                        <td>1837.66</td>
                    </tr>
                    <tr>
                        <td>中国科学院<br>
                            <br>bm4800</td>
                        <td class="not-td">33</td>
                        <td>97.39%</td>
                        <td>65</td>
                        <td>145</td>
                        <td>62.77%</td>
                        <td>9.26</td>
                        <td>2138</td>
                        <td>1407.56</td>
                    </tr>
                    <tr>
                        <td>中国社会科学院<br>
                            <br>bm4900</td>
                        <td class="not-td">2</td>
                        <td>0</td>
                        <td>13</td>
                        <td>2</td>
                        <td>14.29%</td>
                        <td>124.07</td>
                        <td>1737</td>
                        <td>1711.91</td>
                    </tr>
                    <tr>
                        <td>中国工程院<br>
                            <br>bm5000</td>
                        <td class="not-td">1</td>
                        <td>33.78%</td>
                        <td>4</td>
                        <td>4</td>
                        <td>57.14%</td>
                        <td>2.29</td>
                        <td>16</td>
                        <td>1536.77</td>
                    </tr>
                    <tr>
                        <td>国务院发展研究中心<br>
                            <br>bm5100</td>
                        <td class="not-td">1</td>
                        <td>5.72%</td>
                        <td>0</td>
                        <td>4</td>
                        <td>57.14%</td>
                        <td>1.71</td>
                        <td>12</td>
                        <td>1296.51</td>
                    </tr>
                    <tr>
                        <td>国家行政学院<br>
                            <br>bm5200</td>
                        <td class="not-td">1</td>
                        <td>2.43%</td>
                        <td>0</td>
                        <td>2</td>
                        <td>28.57%</td>
                        <td>8.14</td>
                        <td>57</td>
                        <td>1808.29</td>
                    </tr>
                    <tr>
                        <td>中国地震局<br>
                            <br>bm5300</td>
                        <td class="not-td">31</td>
                        <td>89.57%</td>
                        <td>385</td>
                        <td>106</td>
                        <td>48.85%</td>
                        <td>4.74</td>
                        <td>1029</td>
                        <td>1330.85</td>
                    </tr>
                    <tr>
                        <td>中国气象局<br>
                            <br>bm5400</td>
                        <td class="not-td">152</td>
                        <td>38.98%</td>
                        <td>1081</td>
                        <td>580</td>
                        <td>54.51%</td>
                        <td>3.42</td>
                        <td>3642</td>
                        <td>1409.76</td>
                    </tr>
                    <tr>
                        <td>中国银行业监督管理委员会<br>
                            <br>bm5500</td>
                        <td class="not-td">1</td>
                        <td>0</td>
                        <td>0</td>
                        <td>2</td>
                        <td>28.57%</td>
                        <td>53.14</td>
                        <td>372</td>
                        <td>1856.34</td>
                    </tr>
                    <tr>
                        <td>中国证券监督管理委员会<br>
                            <br>bm5600</td>
                        <td class="not-td">1</td>
                        <td>80.63%</td>
                        <td>0</td>
                        <td>0</td>
                        <td>0.00%</td>
                        <td>12.86</td>
                        <td>90</td>
                        <td>1555.80</td>
                    </tr>
                    <tr>
                        <td>中国保险监督管理委员会<br>
                            <br>bm5700</td>
                        <td class="not-td">42</td>
                        <td>8.26%</td>
                        <td>17</td>
                        <td>204</td>
                        <td>69.39%</td>
                        <td>1.60</td>
                        <td>469</td>
                        <td>1445.34</td>
                    </tr>
                    <tr>
                        <td>全国社会保障基金理事会<br>
                            <br>bm5800</td>
                        <td class="not-td">1</td>
                        <td>86.16%</td>
                        <td>0</td>
                        <td>7</td>
                        <td>100.00%</td>
                        <td>25.86</td>
                        <td>181</td>
                        <td>1553.83</td>
                    </tr>
                    <tr>
                        <td>国家自然科学基金委员会<br>
                            <br>bm5900</td>
                        <td class="not-td">1</td>
                        <td>0</td>
                        <td>7</td>
                        <td>2</td>
                        <td>28.57%</td>
                        <td>11.86</td>
                        <td>83</td>
                        <td>1816.17</td>
                    </tr>
                    <tr>
                        <td>国家信访局<br>
                            <br>bm6000</td>
                        <td class="not-td">1</td>
                        <td>1.04%</td>
                        <td>7</td>
                        <td>1</td>
                        <td>14.29%</td>
                        <td>10.71</td>
                        <td>75</td>
                        <td>1444.63</td>
                    </tr>
                    <tr>
                        <td>国家粮食局<br>
                            <br>bm6100</td>
                        <td class="not-td">1</td>
                        <td>16.49%</td>
                        <td>0</td>
                        <td>2</td>
                        <td>28.57%</td>
                        <td>21.14</td>
                        <td>148</td>
                        <td>1631.57</td>
                    </tr>
                    <tr>
                        <td>国家能源局<br>
                            <br>bm6200</td>
                        <td class="not-td">20</td>
                        <td>55.92%</td>
                        <td>28</td>
                        <td>99</td>
                        <td>70.71%</td>
                        <td>1.67</td>
                        <td>234</td>
                        <td>1384.50</td>
                    </tr>
                    <tr>
                        <td>国家国防科技工业局<br>
                            <br>bm6300</td>
                        <td class="not-td">3</td>
                        <td>1.39%</td>
                        <td>7</td>
                        <td>7</td>
                        <td>33.33%</td>
                        <td>35.29</td>
                        <td>741</td>
                        <td>1377.58</td>
                    </tr>
                    <tr>
                        <td>国家烟草专卖局<br>
                            <br>bm6400</td>
                        <td class="not-td">78</td>
                        <td>14.71%</td>
                        <td>39</td>
                        <td>332</td>
                        <td>60.81%</td>
                        <td>1.91</td>
                        <td>1041</td>
                        <td>1440.45</td>
                    </tr>
                    <tr>
                        <td>国家外国专家局<br>
                            <br>bm6500</td>
                        <td class="not-td">1</td>
                        <td>79.97%</td>
                        <td>14</td>
                        <td>5</td>
                        <td>71.43%</td>
                        <td>2.43</td>
                        <td>17</td>
                        <td>1490.91</td>
                    </tr>
                    <tr>
                        <td>国家公务员局<br>
                            <br>bm6600</td>
                        <td class="not-td">1</td>
                        <td>0</td>
                        <td>0</td>
                        <td>5</td>
                        <td>71.43%</td>
                        <td>27.43</td>
                        <td>192</td>
                        <td>1841.57</td>
                    </tr>
                    <tr>
                        <td>国家海洋局<br>
                            <br>bm6700</td>
                        <td class="not-td">14</td>
                        <td>67.35%</td>
                        <td>55</td>
                        <td>74</td>
                        <td>75.51%</td>
                        <td>2.97</td>
                        <td>291</td>
                        <td>1354.58</td>
                    </tr>
                    <tr>
                        <td>国家测绘地理信息局<br>
                            <br>bm6800</td>
                        <td class="not-td">5</td>
                        <td>15.06%</td>
                        <td>73</td>
                        <td>16</td>
                        <td>45.71%</td>
                        <td>5.29</td>
                        <td>185</td>
                        <td>1390.57</td>
                    </tr>
                    <tr>
                        <td>国家铁路局<br>
                            <br>bm6900</td>
                        <td class="not-td">1</td>
                        <td>1.04%</td>
                        <td>0</td>
                        <td>4</td>
                        <td>57.14%</td>
                        <td>1.57</td>
                        <td>11</td>
                        <td>1892.86</td>
                    </tr>
                    <tr>
                        <td>中国民用航空局<br>
                            <br>bm7000</td>
                        <td class="not-td">8</td>
                        <td>99.98%</td>
                        <td>1</td>
                        <td>30</td>
                        <td>53.57%</td>
                        <td>10.98</td>
                        <td>615</td>
                        <td>1206.28</td>
                    </tr>
                    <tr>
                        <td>国家邮政局<br>
                            <br>bm7100</td>
                        <td class="not-td">367</td>
                        <td>76.54%</td>
                        <td>181</td>
                        <td>2217</td>
                        <td>86.30%</td>
                        <td>0.41</td>
                        <td>1042</td>
                        <td>1124.00</td>
                    </tr>
                    <tr>
                        <td>国家文物局<br>
                            <br>bm7200</td>
                        <td class="not-td">1</td>
                        <td>77.60%</td>
                        <td>0</td>
                        <td>2</td>
                        <td>28.57%</td>
                        <td>11.86</td>
                        <td>83</td>
                        <td>1581.43</td>
                    </tr>
                    <tr>
                        <td>国家中医药管理局<br>
                            <br>bm7300</td>
                        <td class="not-td">1</td>
                        <td>37.22%</td>
                        <td>33</td>
                        <td>7</td>
                        <td>100.00%</td>
                        <td>0.00</td>
                        <td>0</td>
                        <td>825.97</td>
                    </tr>
                    <tr>
                        <td>国家外汇管理局<br>
                            <br>bm7400</td>
                        <td class="not-td">37</td>
                        <td>0</td>
                        <td>0</td>
                        <td>225</td>
                        <td>86.87%</td>
                        <td>0.68</td>
                        <td>175</td>
                        <td>1483.99</td>
                    </tr>
                    <tr>
                        <td>国务院扶贫开发领导小组办公室<br>
                            <br>bm7600</td>
                        <td class="not-td">1</td>
                        <td>86.03%</td>
                        <td>14</td>
                        <td>2</td>
                        <td>28.57%</td>
                        <td>5.86</td>
                        <td>41</td>
                        <td>1358.14</td>
                    </tr>
                    <tr>
                        <td>国务院三峡工程建设委员会办公室<br>
                            <br>bm7700</td>
                        <td class="not-td">1</td>
                        <td>0</td>
                        <td>28</td>
                        <td>6</td>
                        <td>85.71%</td>
                        <td>2.29</td>
                        <td>16</td>
                        <td>1711.63</td>
                    </tr>
                    <tr>
                        <td>国务院南水北调工程建设委员会办公室<br>
                            <br>bm7800</td>
                        <td class="not-td">1</td>
                        <td>1.39%</td>
                        <td>21</td>
                        <td>2</td>
                        <td>28.57%</td>
                        <td>2.00</td>
                        <td>14</td>
                        <td>1702.14</td>
                    </tr>
                    <tr>
                        <td>国务院台湾事务办公室<br>
                            <br>bm8000</td>
                        <td class="not-td">1</td>
                        <td>0</td>
                        <td>0</td>
                        <td>4</td>
                        <td>57.14%</td>
                        <td>7.86</td>
                        <td>55</td>
                        <td>1469.83</td>
                    </tr>
                    </tbody>
                    </table>
                    <table class="table1"  id="table-Btwoweeks">
                        <thead>
                        <tr>
                            <th style="width: 15%;">组织单位名称/编码</th>
                            <th style="width: 10%;">网站个数<i class="jian"></i></th>
                            <th style="width: 10%;">网站不连通<br>（占比）<i class="jian"></i></th>
                            <th style="width: 10%;">首页不可用链接（个）<i class="jian"></i></th>
                            <th style="width: 10%;">首页不更新<br>（网站数）<i class="jian"></i></th>
                            <th style="width: 10%;">首页不更新<br>（占比）<i class="jian"></i></th>
                            <th style="width: 15%;">平均更新量<br>（条/站）<i class="jian"></i></th>
                            <th style="width: 10%;">总更新量<br>（条）<i class="jian"></i></th>
                            <th style="width: 10%;">健康指数<i class="jian"></i></th>
                        </tr>
                        </thead>
                        <tbody>
<!--                         <tr style="display:none;"><td>中华人民共和国国务院办公厅<br><br>bm0100</td><td class="not-td">1</td><td>1.04%</td><td>0</td><td>0</td><td>0.00%</td><td>111.50 </td><td>1561</td><td>1996.87 </td></tr> -->
                        <tr><td>中华人民共和国外交部<br><br>bm0200</td><td class="not-td">9</td><td>70.83%</td><td>56</td><td>82</td><td>65.08%</td><td>13.20 </td><td>1663</td><td>1833.26 </td></tr>
                        <tr><td>中华人民共和国国家发展和改革委员会<br><br>bm0400</td><td class="not-td">7</td><td>1.04%</td><td>4</td><td>66</td><td>67.35%</td><td>5.31 </td><td>520</td><td>1460.29 </td></tr>
                        <tr><td>中华人民共和国教育部<br><br>bm0500</td><td class="not-td">50</td><td>58.73%</td><td>687</td><td>567</td><td>81.00%</td><td>1.34 </td><td>936</td><td>1440.50 </td></tr>
                        <tr><td>中华人民共和国科学技术部<br><br>bm0600</td><td class="not-td">7</td><td>4.17%</td><td>3</td><td>75</td><td>76.53%</td><td>3.26 </td><td>319</td><td>1486.52 </td></tr>
                        <tr><td>中华人民共和国工业和信息化部<br><br>bm0700</td><td class="not-td">44</td><td>67.57%</td><td>451</td><td>455</td><td>73.86%</td><td>4.94 </td><td>3045</td><td>1678.03 </td></tr>
                        <tr><td>中华人民共和国国家民族事务委员会<br><br>bm0800</td><td class="not-td">14</td><td>82.64%</td><td>75</td><td>109</td><td>55.61%</td><td>4.41 </td><td>864</td><td>1449.49 </td></tr>
                        <tr><td>中华人民共和国公安部<br><br>bm0900</td><td class="not-td">15</td><td>75.69%</td><td>176</td><td>138</td><td>65.71%</td><td>23.93 </td><td>5026</td><td>1737.99 </td></tr>
                        <tr><td>中华人民共和国民政部<br><br>bm1200</td><td class="not-td">8</td><td>77.36%</td><td>139</td><td>44</td><td>39.29%</td><td>8.70 </td><td>974</td><td>1400.41 </td></tr>
                        <tr><td>中华人民共和国司法部<br><br>bm1300</td><td class="not-td">2</td><td>1.47%</td><td>28</td><td>10</td><td>35.71%</td><td>12.11 </td><td>339</td><td>1511.87 </td></tr>
                        <tr><td>中华人民共和国财政部<br><br>bm1400</td><td class="not-td">2</td><td>31.32%</td><td>273</td><td>3</td><td>10.71%</td><td>161.82 </td><td>4531</td><td>1385.73 </td></tr>
                        <tr><td>中华人民共和国人力资源和社会保障部<br><br>bm1500</td><td class="not-td">7</td><td>88.28%</td><td>145</td><td>46</td><td>46.94%</td><td>28.63 </td><td>2806</td><td>1443.88 </td></tr>
                        <tr><td>中华人民共和国国土资源部<br><br>bm1600</td><td class="not-td">2</td><td>0</td><td>138</td><td>6</td><td>21.43%</td><td>30.68 </td><td>859</td><td>1476.54 </td></tr>
                        <tr><td>中华人民共和国环境保护部<br><br>bm1700</td><td class="not-td">9</td><td>16.11%</td><td>198</td><td>82</td><td>65.08%</td><td>1.17 </td><td>147</td><td>1302.01 </td></tr>
                        <tr><td>中华人民共和国住房和城乡建设部<br><br>bm1800</td><td class="not-td">4</td><td>100.00%</td><td>82</td><td>31</td><td>55.36%</td><td>1.36 </td><td>76</td><td>1185.80 </td></tr>
                        <tr><td>中华人民共和国交通运输部<br><br>bm1900</td><td class="not-td">104</td><td>70.36%</td><td>3387</td><td>718</td><td>49.31%</td><td>3.44 </td><td>5003</td><td>1329.22 </td></tr>
                        <tr><td>中华人民共和国水利部<br><br>bm2000</td><td class="not-td">36</td><td>25.69%</td><td>447</td><td>276</td><td>54.76%</td><td>5.06 </td><td>2550</td><td>1467.79 </td></tr>
                        <tr><td>中华人民共和国农业部<br><br>bm2100</td><td class="not-td">11</td><td>55.51%</td><td>133</td><td>89</td><td>57.79%</td><td>37.94 </td><td>5843</td><td>1803.24 </td></tr>
                        <tr><td>中华人民共和国商务部<br><br>bm2200</td><td class="not-td">9</td><td>17.13%</td><td>14</td><td>84</td><td>66.67%</td><td>58.96 </td><td>7429</td><td>1372.35 </td></tr>
                        <tr><td>中华人民共和国文化部<br><br>bm2300</td><td class="not-td">3</td><td>50.34%</td><td>18</td><td>12</td><td>28.57%</td><td>24.48 </td><td>1028</td><td>1646.28 </td></tr>
                        <tr><td>中华人民共和国国家卫生和计划生育委员会<br><br>bm2400</td><td class="not-td">3</td><td>40.63%</td><td>16</td><td>18</td><td>42.86%</td><td>4.95 </td><td>208</td><td>1855.26 </td></tr>
                        <tr><td>中国人民银行<br><br>bm2500</td><td class="not-td">3</td><td>0</td><td>0</td><td>27</td><td>64.29%</td><td>0.14 </td><td>6</td><td>1124.68 </td></tr>
                        <tr><td>中华人民共和国审计署<br><br>bm2600</td><td class="not-td">1</td><td>0</td><td>1</td><td>4</td><td>28.57%</td><td>27.71 </td><td>388</td><td>1710.76 </td></tr>
                        <tr><td>国务院国有资产监督委员会<br><br>bm2700</td><td class="not-td">3</td><td>0</td><td>0</td><td>26</td><td>61.90%</td><td>6.07 </td><td>255</td><td>1488.19 </td></tr>
                        <tr><td>中华人民共和国海关总署<br><br>bm2800</td><td class="not-td">45</td><td>12.35%</td><td>663</td><td>422</td><td>66.98%</td><td>1.60 </td><td>1011</td><td>1426.96 </td></tr>
                        <tr><td>国家税务总局<br><br>bm2900</td><td class="not-td">487</td><td>81.64%</td><td>5936</td><td>4415</td><td>64.76%</td><td>2.25 </td><td>15346</td><td>1462.30 </td></tr>
                        <tr><td>国家工商行政管理总局<br><br>bm3000</td><td class="not-td">12</td><td>1.04%</td><td>20</td><td>85</td><td>50.60%</td><td>3.82 </td><td>642</td><td>1119.68 </td></tr>
                        <tr><td>国家质量监督检验检疫总局<br><br>bm3100</td><td class="not-td">366</td><td>62.22%</td><td>3619</td><td>3867</td><td>75.47%</td><td>0.87 </td><td>4467</td><td>1430.66 </td></tr>
                        <tr><td>国家新闻出版广电总局<br><br>bm3200</td><td class="not-td">2</td><td>88.24%</td><td>51</td><td>18</td><td>64.29%</td><td>2.71 </td><td>76</td><td>1310.13 </td></tr>
                        <tr><td>国家体育总局<br><br>bm3300</td><td class="not-td">3</td><td>86.63%</td><td>2</td><td>29</td><td>69.05%</td><td>13.79 </td><td>579</td><td>1484.42 </td></tr>
                        <tr><td>国家安全生产监督管理总局<br><br>bm3400</td><td class="not-td">69</td><td>53.25%</td><td>749</td><td>637</td><td>65.94%</td><td>1.04 </td><td>1008</td><td>1275.84 </td></tr>
                        <tr><td>国家食品药品监督管理总局<br><br>bm3500</td><td class="not-td">1</td><td>2.08%</td><td>43</td><td>4</td><td>28.57%</td><td>12.57 </td><td>176</td><td>1892.60 </td></tr>
                        <tr><td>国家统计局<br><br>bm3600</td><td class="not-td">48</td><td>44.57%</td><td>279</td><td>479</td><td>71.28%</td><td>0.62 </td><td>416</td><td>1302.45 </td></tr>
                        <tr><td>国家林业局<br><br>bm3700</td><td class="not-td">2</td><td>37.72%</td><td>91</td><td>2</td><td>7.14%</td><td>31.75 </td><td>889</td><td>1312.46 </td></tr>
                        <tr><td>国家知识产权局<br><br>bm3800</td><td class="not-td">8</td><td>86.63%</td><td>4</td><td>45</td><td>40.18%</td><td>6.37 </td><td>713</td><td>1213.63 </td></tr>
                        <tr><td>国家旅游局<br><br>bm3900</td><td class="not-td">1</td><td>86.16%</td><td>13</td><td>0</td><td>0.00%</td><td>85.50 </td><td>1197</td><td>1487.10 </td></tr>
                        <tr><td>国家宗教事务局<br><br>bm4000</td><td class="not-td">1</td><td>86.63%</td><td>0</td><td>5</td><td>35.71%</td><td>19.50 </td><td>273</td><td>1534.60 </td></tr>
                        <tr><td>国务院参事室<br><br>bm4100</td><td class="not-td">1</td><td>0</td><td>42</td><td>4</td><td>28.57%</td><td>5.64 </td><td>79</td><td>1568.80 </td></tr>
                        <tr><td>国家机关事务管理局<br><br>bm4200</td><td class="not-td">3</td><td>2.08%</td><td>49</td><td>30</td><td>71.43%</td><td>34.93 </td><td>1467</td><td>1536.32 </td></tr>
                        <tr><td>国务院侨务办公室<br><br>bm4300</td><td class="not-td">1</td><td>0</td><td>0</td><td>4</td><td>28.57%</td><td>19.36 </td><td>271</td><td>1676.87 </td></tr>
                        <tr><td>国务院港澳事务办公室<br><br>bm4400</td><td class="not-td">1</td><td>0</td><td>28</td><td>11</td><td>78.57%</td><td>0.29 </td><td>4</td><td>1578.96 </td></tr>
                        <tr><td>国务院法制办公室<br><br>bm4500</td><td class="not-td">1</td><td>0</td><td>0</td><td>11</td><td>78.57%</td><td>1.07 </td><td>15</td><td>1650.63 </td></tr>
                        <tr><td>中国科学院<br><br>bm4800</td><td class="not-td">33</td><td>98.53%</td><td>142</td><td>287</td><td>62.12%</td><td>6.87 </td><td>3173</td><td>1402.86 </td></tr>
                        <tr><td>中国社会科学院<br><br>bm4900</td><td class="not-td">2</td><td>0</td><td>24</td><td>5</td><td>17.86%</td><td>122.29 </td><td>3424</td><td>1624.72 </td></tr>
                        <tr><td>中国工程院<br><br>bm5000</td><td class="not-td">1</td><td>33.78%</td><td>4</td><td>8</td><td>57.14%</td><td>1.86 </td><td>26</td><td>1354.13 </td></tr>
                        <tr><td>国务院发展研究中心<br><br>bm5100</td><td class="not-td">1</td><td>5.72%</td><td>0</td><td>10</td><td>71.43%</td><td>1.00 </td><td>14</td><td>1352.76 </td></tr>
                        <tr><td>国家行政学院<br><br>bm5200</td><td class="not-td">1</td><td>2.43%</td><td>0</td><td>4</td><td>28.57%</td><td>5.71 </td><td>80</td><td>1649.64 </td></tr>
                        <tr><td>中国地震局<br><br>bm5300</td><td class="not-td">31</td><td>90.32%</td><td>811</td><td>218</td><td>50.23%</td><td>4.83 </td><td>2098</td><td>1374.87 </td></tr>
                        <tr><td>中国气象局<br><br>bm5400</td><td class="not-td">152</td><td>38.42%</td><td>2473</td><td>1138</td><td>53.48%</td><td>3.54 </td><td>7540</td><td>1417.46 </td></tr>
                        <tr><td>中国银行业监督管理委员会<br><br>bm5500</td><td class="not-td">1</td><td>0</td><td>0</td><td>4</td><td>28.57%</td><td>40.71 </td><td>570</td><td>1512.94 </td></tr>
                        <tr><td>中国证券监督管理委员会<br><br>bm5600</td><td class="not-td">1</td><td>80.63%</td><td>0</td><td>2</td><td>14.29%</td><td>10.00 </td><td>140</td><td>1523.79 </td></tr>
                        <tr><td>中国保险监督管理委员会<br><br>bm5700</td><td class="not-td">42</td><td>8.26%</td><td>31</td><td>417</td><td>70.92%</td><td>1.38 </td><td>813</td><td>1446.63 </td></tr>
                        <tr><td>全国社会保障基金理事会<br><br>bm5800</td><td class="not-td">1</td><td>84.62%</td><td>2</td><td>12</td><td>85.71%</td><td>0.36 </td><td>5</td><td>1487.33 </td></tr>
                        <tr><td>国家自然科学基金委员会<br><br>bm5900</td><td class="not-td">1</td><td>0</td><td>14</td><td>4</td><td>28.57%</td><td>18.64 </td><td>261</td><td>1486.00 </td></tr>
                        <tr><td>国家信访局<br><br>bm6000</td><td class="not-td">1</td><td>1.04%</td><td>14</td><td>3</td><td>21.43%</td><td>10.64 </td><td>149</td><td>1442.61 </td></tr>
                        <tr><td>国家粮食局<br><br>bm6100</td><td class="not-td">1</td><td>16.49%</td><td>0</td><td>4</td><td>28.57%</td><td>10.00 </td><td>140</td><td>1562.06 </td></tr>
                        <tr><td>国家能源局<br><br>bm6200</td><td class="not-td">20</td><td>55.92%</td><td>63</td><td>206</td><td>73.57%</td><td>1.48 </td><td>413</td><td>1390.36 </td></tr>
                        <tr><td>国家国防科技工业局<br><br>bm6300</td><td class="not-td">3</td><td>1.39%</td><td>14</td><td>13</td><td>30.95%</td><td>8.52 </td><td>358</td><td>1410.20 </td></tr>
                        <tr><td>国家烟草专卖局<br><br>bm6400</td><td class="not-td">78</td><td>16.53%</td><td>114</td><td>668</td><td>61.17%</td><td>3.02 </td><td>3298</td><td>1472.81 </td></tr>
                        <tr><td>国家外国专家局<br><br>bm6500</td><td class="not-td">1</td><td>79.97%</td><td>26</td><td>10</td><td>71.43%</td><td>4.00 </td><td>56</td><td>1467.56 </td></tr>
                        <tr><td>国家公务员局<br><br>bm6600</td><td class="not-td">1</td><td>0</td><td>0</td><td>9</td><td>64.29%</td><td>1.43 </td><td>20</td><td>1666.29 </td></tr>
                        <tr><td>国家海洋局<br><br>bm6700</td><td class="not-td">14</td><td>67.35%</td><td>98</td><td>148</td><td>75.51%</td><td>1.88 </td><td>369</td><td>1365.79 </td></tr>
                        <tr><td>国家测绘地理信息局<br><br>bm6800</td><td class="not-td">5</td><td>15.06%</td><td>139</td><td>33</td><td>47.14%</td><td>11.03 </td><td>772</td><td>1402.13 </td></tr>
                        <tr><td>国家铁路局<br><br>bm6900</td><td class="not-td">1</td><td>1.04%</td><td>1</td><td>7</td><td>50.00%</td><td>5.14 </td><td>72</td><td>1891.67 </td></tr>
                        <tr><td>中国民用航空局<br><br>bm7000</td><td class="not-td">8</td><td>99.53%</td><td>2</td><td>53</td><td>47.32%</td><td>2.47 </td><td>277</td><td>1206.09 </td></tr>
                        <tr><td>国家邮政局<br><br>bm7100</td><td class="not-td">367</td><td>76.54%</td><td>431</td><td>4379</td><td>85.23%</td><td>0.55 </td><td>2834</td><td>1303.88 </td></tr>
                        <tr><td>国家文物局<br><br>bm7200</td><td class="not-td">1</td><td>77.60%</td><td>0</td><td>4</td><td>28.57%</td><td>9.57 </td><td>134</td><td>1512.41 </td></tr>
                        <tr><td>国家中医药管理局<br><br>bm7300</td><td class="not-td">1</td><td>37.22%</td><td>149</td><td>11</td><td>78.57%</td><td>1.50 </td><td>21</td><td>1084.41 </td></tr>
                        <tr><td>国家外汇管理局<br><br>bm7400</td><td class="not-td">37</td><td>0</td><td>0</td><td>443</td><td>85.52%</td><td>0.76 </td><td>393</td><td>1480.16 </td></tr>
                        <tr><td>国务院扶贫开发领导小组办公室<br><br>bm7600</td><td class="not-td">1</td><td>86.99%</td><td>29</td><td>4</td><td>28.57%</td><td>6.00 </td><td>84</td><td>1261.46 </td></tr>
                        <tr><td>国务院三峡工程建设委员会办公室<br><br>bm7700</td><td class="not-td">1</td><td>0</td><td>52</td><td>12</td><td>85.71%</td><td>2.07 </td><td>29</td><td>1554.51 </td></tr>
                        <tr><td>国务院南水北调工程建设委员会办公室<br><br>bm7800</td><td class="not-td">1</td><td>1.39%</td><td>42</td><td>5</td><td>35.71%</td><td>1.29 </td><td>18</td><td>1541.97 </td></tr>
                        <tr><td>国务院台湾事务办公室<br><br>bm8000</td><td class="not-td">1</td><td>0</td><td>0</td><td>7</td><td>50.00%</td><td>11.57 </td><td>162</td><td>1472.19 </td></tr>
                        </tbody>
                    </table>
                    <table class="table1" id="table-Bmonth">
                        <thead>
                        <tr>
                            <th style="width: 15%;">组织单位名称/编码</th>
                            <th style="width: 10%;">网站个数<i class="jian"></i></th>
                            <th style="width: 10%;">网站不连通<br>（占比）<i class="jian"></i></th>
                            <th style="width: 10%;">首页不可用链接（个）<i class="jian"></i></th>
                            <th style="width: 10%;">首页不更新<br>（网站数）<i class="jian"></i></th>
                            <th style="width: 10%;">首页不更新<br>（占比）<i class="jian"></i></th>
                            <th style="width: 15%;">平均更新量<br>（条/站）<i class="jian"></i></th>
                            <th style="width: 10%;">总更新量<br>（条）<i class="jian"></i></th>
                            <th style="width: 10%;">健康指数<i class="jian"></i></th>
                        </tr>
                        </thead>
                        <tbody>
<!--                         <tr style="display:none;"><td>中华人民共和国国务院办公厅<br><br>bm0100</td><td class="not-td">1</td><td>1.04%</td><td>0</td><td>0</td><td>0.00%</td><td>79.17 </td><td>2375</td><td>2082.79 </td></tr> -->
                        <tr><td>中华人民共和国外交部<br><br>bm0200</td><td class="not-td">9</td><td>54.35%</td><td>265</td><td>200</td><td>74.07%</td><td>11.79 </td><td>3183</td><td>1901.53 </td></tr>
                        <tr><td>中华人民共和国国家发展和改革委员会<br><br>bm0400</td><td class="not-td">7</td><td>9.57%</td><td>26</td><td>153</td><td>72.86%</td><td>5.56 </td><td>1167</td><td>1524.06 </td></tr>
                        <tr><td>中华人民共和国教育部<br><br>bm0500</td><td class="not-td">50</td><td>74.63%</td><td>2709</td><td>1296</td><td>86.40%</td><td>1.24 </td><td>1867</td><td>1487.72 </td></tr>
                        <tr><td>中华人民共和国科学技术部<br><br>bm0600</td><td class="not-td">7</td><td>6.55%</td><td>93</td><td>174</td><td>82.86%</td><td>4.01 </td><td>842</td><td>1541.71 </td></tr>
                        <tr><td>中华人民共和国工业和信息化部<br><br>bm0700</td><td class="not-td">44</td><td>66.11%</td><td>2507</td><td>1065</td><td>80.68%</td><td>4.66 </td><td>6148</td><td>1707.24 </td></tr>
                        <tr><td>中华人民共和国国家民族事务委员会<br><br>bm0800</td><td class="not-td">14</td><td>82.64%</td><td>551</td><td>295</td><td>70.24%</td><td>3.82 </td><td>1603</td><td>1512.79 </td></tr>
                        <tr><td>中华人民共和国公安部<br><br>bm0900</td><td class="not-td">15</td><td>65.85%</td><td>513</td><td>341</td><td>75.78%</td><td>21.78 </td><td>9803</td><td>1814.21 </td></tr>
                        <tr><td>中华人民共和国民政部<br><br>bm1200</td><td class="not-td">8</td><td>61.63%</td><td>1024</td><td>124</td><td>51.67%</td><td>8.40 </td><td>2016</td><td>1423.41 </td></tr>
                        <tr><td>中华人民共和国司法部<br><br>bm1300</td><td class="not-td">2</td><td>1.47%</td><td>91</td><td>30</td><td>50.00%</td><td>10.22 </td><td>613</td><td>1546.18 </td></tr>
                        <tr><td>中华人民共和国财政部<br><br>bm1400</td><td class="not-td">2</td><td>47.49%</td><td>878</td><td>10</td><td>16.67%</td><td>153.32 </td><td>9199</td><td>1332.55 </td></tr>
                        <tr><td>中华人民共和国人力资源和社会保障部<br><br>bm1500</td><td class="not-td">7</td><td>81.78%</td><td>1133</td><td>114</td><td>54.29%</td><td>28.20 </td><td>5921</td><td>1479.03 </td></tr>
                        <tr><td>中华人民共和国国土资源部<br><br>bm1600</td><td class="not-td">2</td><td>0</td><td>539</td><td>17</td><td>28.33%</td><td>23.92 </td><td>1435</td><td>1457.46 </td></tr>
                        <tr><td>中华人民共和国环境保护部<br><br>bm1700</td><td class="not-td">9</td><td>18.97%</td><td>899</td><td>200</td><td>74.07%</td><td>1.21 </td><td>327</td><td>1328.21 </td></tr>
                        <tr><td>中华人民共和国住房和城乡建设部<br><br>bm1800</td><td class="not-td">4</td><td>100.00%</td><td>498</td><td>74</td><td>61.67%</td><td>0.99 </td><td>119</td><td>1160.35 </td></tr>
                        <tr><td>中华人民共和国交通运输部<br><br>bm1900</td><td class="not-td">104</td><td>67.04%</td><td>11629</td><td>1707</td><td>54.71%</td><td>3.50 </td><td>10933</td><td>1375.22 </td></tr>
                        <tr><td>中华人民共和国水利部<br><br>bm2000</td><td class="not-td">36</td><td>22.25%</td><td>2250</td><td>674</td><td>62.41%</td><td>4.57 </td><td>4940</td><td>1509.02 </td></tr>
                        <tr><td>中华人民共和国农业部<br><br>bm2100</td><td class="not-td">11</td><td>72.36%</td><td>486</td><td>211</td><td>63.94%</td><td>33.88 </td><td>11181</td><td>1807.32 </td></tr>
                        <tr><td>中华人民共和国商务部<br><br>bm2200</td><td class="not-td">9</td><td>17.13%</td><td>67</td><td>191</td><td>70.74%</td><td>61.45 </td><td>16592</td><td>1437.08 </td></tr>
                        <tr><td>中华人民共和国文化部<br><br>bm2300</td><td class="not-td">3</td><td>57.93%</td><td>176</td><td>35</td><td>38.89%</td><td>23.83 </td><td>2145</td><td>1647.82 </td></tr>
                        <tr><td>中华人民共和国国家卫生和计划生育委员会<br><br>bm2400</td><td class="not-td">3</td><td>34.00%</td><td>159</td><td>50</td><td>55.56%</td><td>5.78 </td><td>520</td><td>1968.20 </td></tr>
                        <tr><td>中国人民银行<br><br>bm2500</td><td class="not-td">3</td><td>8.33%</td><td>0</td><td>63</td><td>70.00%</td><td>0.07 </td><td>6</td><td>1115.14 </td></tr>
                        <tr><td>中华人民共和国审计署<br><br>bm2600</td><td class="not-td">1</td><td>37.50%</td><td>51</td><td>11</td><td>36.67%</td><td>24.00 </td><td>720</td><td>1621.60 </td></tr>
                        <tr><td>国务院国有资产监督委员会<br><br>m2700</td><td class="not-td">3</td><td>2.36%</td><td>1</td><td>66</td><td>73.33%</td><td>5.83 </td><td>525</td><td>1490.55 </td></tr>
                        <tr><td>中华人民共和国海关总署<br><br>m2800</td><td class="not-td">45</td><td>15.27%</td><td>4626</td><td>1069</td><td>79.19%</td><td>1.37 </td><td>1844</td><td>1463.94 </td></tr>
                        <tr><td>国家税务总局<br><br>m2900</td><td class="not-td">487</td><td>57.67%</td><td>21937</td><td>9998</td><td>68.43%</td><td>3.52 </td><td>51380</td><td>1516.53 </td></tr>
                        <tr><td>国家工商行政管理总局<br><br>m3000</td><td class="not-td">12</td><td>20.23%</td><td>111</td><td>204</td><td>56.67%</td><td>2.11 </td><td>760</td><td>1123.45 </td></tr>
                        <tr><td>国家质量监督检验检疫总局<br><br>m3100</td><td class="not-td">366</td><td>47.20%</td><td>18532</td><td>8996</td><td>81.93%</td><td>0.86 </td><td>9418</td><td>1490.67 </td></tr>
                        <tr><td>国家新闻出版广电总局<br><br>m3200</td><td class="not-td">2</td><td>90.10%</td><td>495</td><td>43</td><td>71.67%</td><td>2.08 </td><td>125</td><td>1300.65 </td></tr>
                        <tr><td>国家体育总局<br><br>m3300</td><td class="not-td">3</td><td>86.63%</td><td>22</td><td>68</td><td>75.56%</td><td>12.87 </td><td>1158</td><td>1541.55 </td></tr>
                        <tr><td>国家安全生产监督管理总局<br><br>m3400</td><td class="not-td">69</td><td>55.59%</td><td>3063</td><td>1473</td><td>71.16%</td><td>1.09 </td><td>2258</td><td>1319.79 </td></tr>
                        <tr><td>国家食品药品监督管理总局<br><br>m3500</td><td class="not-td">1</td><td>4.90%</td><td>143</td><td>11</td><td>36.67%</td><td>17.90 </td><td>537</td><td>2017.47 </td></tr>
                        <tr><td>国家统计局<br><br>m3600</td><td class="not-td">48</td><td>33.11%</td><td>1696</td><td>1087</td><td>75.49%</td><td>0.60 </td><td>859</td><td>1315.65 </td></tr>
                        <tr><td>国家林业局<br><br>m3700</td><td class="not-td">2</td><td>30.58%</td><td>882</td><td>9</td><td>15.00%</td><td>28.53 </td><td>1712</td><td>1079.70 </td></tr>
                        <tr><td>国家知识产权局<br><br>m3800</td><td class="not-td">8</td><td>77.47%</td><td>31</td><td>116</td><td>48.33%</td><td>6.29 </td><td>1509</td><td>1260.52 </td></tr>
                        <tr><td>国家旅游局<br><br>m3900</td><td class="not-td">1</td><td>87.72%</td><td>96</td><td>0</td><td>0.00%</td><td>89.10 </td><td>2673</td><td>1405.41 </td></tr>
                        <tr><td>国家宗教事务局<br><br>m4000</td><td class="not-td">1</td><td>86.63%</td><td>2</td><td>11</td><td>36.67%</td><td>16.07 </td><td>482</td><td>1585.59 </td></tr>
                        <tr><td>国务院参事室<br><br>m4100</td><td class="not-td">1</td><td>0</td><td>161</td><td>11</td><td>36.67%</td><td>4.67 </td><td>140</td><td>1518.13 </td></tr>
                        <tr><td>国家机关事务管理局<br><br>m4200</td><td class="not-td">3</td><td>2.50%</td><td>412</td><td>68</td><td>75.56%</td><td>33.50 </td><td>3015</td><td>1562.38 </td></tr>
                        <tr><td>国务院侨务办公室<br><br>m4300</td><td class="not-td">1</td><td>0</td><td>1</td><td>13</td><td>43.33%</td><td>15.27 </td><td>458</td><td>1654.53 </td></tr>
                        <tr><td>国务院港澳事务办公室<br><br>m4400</td><td class="not-td">1</td><td>4.86%</td><td>168</td><td>26</td><td>86.67%</td><td>0.27 </td><td>8</td><td>1500.62 </td></tr>
                        <tr><td>国务院法制办公室<br><br>m4500</td><td class="not-td">1</td><td>0</td><td>0</td><td>24</td><td>80.00%</td><td>1.23 </td><td>37</td><td>1621.79 </td></tr>
                        <tr><td>中国科学院<br><br>bm4800</td><td class="not-td">33</td><td>97.35%</td><td>726</td><td>699</td><td>70.61%</td><td>5.83 </td><td>5773</td><td>1463.80 </td></tr>
                        <tr><td>中国社会科学院<br><br>bm4900</td><td class="not-td">2</td><td>0</td><td>45</td><td>18</td><td>30.00%</td><td>124.88 </td><td>7493</td><td>1654.93 </td></tr>
                        <tr><td>中国工程院<br><br>bm5000</td><td class="not-td">1</td><td>40.94%</td><td>34</td><td>20</td><td>66.67%</td><td>2.93 </td><td>88</td><td>1183.04 </td></tr>
                        <tr><td>国务院发展研究中心<br><br>bm5100</td><td class="not-td">1</td><td>5.71%</td><td>2</td><td>21</td><td>70.00%</td><td>1.30 </td><td>39</td><td>1425.05 </td></tr>
                        <tr><td>国家行政学院<br><br>bm5200</td><td class="not-td">1</td><td>5.28%</td><td>79</td><td>10</td><td>33.33%</td><td>5.50 </td><td>165</td><td>1556.08 </td></tr>
                        <tr><td>中国地震局<br><br>bm5300</td><td class="not-td">31</td><td>90.04%</td><td>6014</td><td>561</td><td>60.32%</td><td>4.19 </td><td>3893</td><td>1388.46 </td></tr>
                        <tr><td>中国气象局<br><br>bm5400</td><td class="not-td">152</td><td>34.85%</td><td>12143</td><td>2828</td><td>62.02%</td><td>3.37 </td><td>15353</td><td>1462.53 </td></tr>
                        <tr><td>中国银行业监督管理委员会<br><br>bm5500</td><td class="not-td">1</td><td>1.04%</td><td>0</td><td>13</td><td>43.33%</td><td>31.53 </td><td>946</td><td>1412.93 </td></tr>
                        <tr><td>中国证券监督管理委员会<br><br>bm5600</td><td class="not-td">1</td><td>80.63%</td><td>0</td><td>11</td><td>36.67%</td><td>8.53 </td><td>256</td><td>1579.21 </td></tr>
                        <tr><td>中国保险监督管理委员会<br><br>bm5700</td><td class="not-td">42</td><td>10.38%</td><td>1015</td><td>972</td><td>77.14%</td><td>1.56 </td><td>1970</td><td>1505.90 </td></tr>
                        <tr><td>全国社会保障基金理事会<br><br>bm5800</td><td class="not-td">1</td><td>84.62%</td><td>21</td><td>26</td><td>86.67%</td><td>0.27 </td><td>8</td><td>1544.67 </td></tr>
                        <tr><td>国家自然科学基金委员会<br><br>bm5900</td><td class="not-td">1</td><td>1.04%</td><td>56</td><td>12</td><td>40.00%</td><td>12.00 </td><td>360</td><td>1371.25 </td></tr>
                        <tr><td>国家信访局<br><br>bm6000</td><td class="not-td">1</td><td>1.04%</td><td>90</td><td>10</td><td>33.33%</td><td>9.43 </td><td>283</td><td>1472.73 </td></tr>
                        <tr><td>国家粮食局<br><br>bm6100</td><td class="not-td">1</td><td>16.49%</td><td>42</td><td>12</td><td>40.00%</td><td>7.83 </td><td>235</td><td>1558.10 </td></tr>
                        <tr><td>国家能源局<br><br>bm6200</td><td class="not-td">20</td><td>37.14%</td><td>629</td><td>476</td><td>79.33%</td><td>1.42 </td><td>849</td><td>1434.71 </td></tr>
                        <tr><td>国家国防科技工业局<br><br>bm6300</td><td class="not-td">3</td><td>1.50%</td><td>45</td><td>40</td><td>44.44%</td><td>8.12 </td><td>731</td><td>1494.90 </td></tr>
                        <tr><td>国家烟草专卖局<br><br>bm6400</td><td class="not-td">78</td><td>24.72%</td><td>1104</td><td>1576</td><td>67.35%</td><td>3.11 </td><td>7287</td><td>1520.41 </td></tr>
                        <tr><td>国家外国专家局<br><br>bm6500</td><td class="not-td">1</td><td>59.44%</td><td>148</td><td>23</td><td>76.67%</td><td>2.93 </td><td>88</td><td>1419.55 </td></tr>
                        <tr><td>国家公务员局<br><br>bm6600</td><td class="not-td">1</td><td>0</td><td>64</td><td>23</td><td>76.67%</td><td>2.53 </td><td>76</td><td>1587.31 </td></tr>
                        <tr><td>国家海洋局<br><br>bm6700</td><td class="not-td">14</td><td>73.85%</td><td>417</td><td>346</td><td>82.38%</td><td>1.36 </td><td>573</td><td>1400.69 </td></tr>
                        <tr><td>国家测绘地理信息局<br><br>bm6800</td><td class="not-td">5</td><td>17.17%</td><td>573</td><td>88</td><td>58.67%</td><td>10.62 </td><td>1593</td><td>1427.14 </td></tr>
                        <tr><td>国家铁路局<br><br>bm6900</td><td class="not-td">1</td><td>60.42%</td><td>78</td><td>18</td><td>60.00%</td><td>6.20 </td><td>186</td><td>1889.46 </td></tr>
                        <tr><td>中国民用航空局<br><br>bm7000</td><td class="not-td">8</td><td>98.82%</td><td>164</td><td>142</td><td>59.17%</td><td>2.62 </td><td>628</td><td>1298.21 </td></tr>
                        <tr><td>国家邮政局<br><br>bm7100</td><td class="not-td">367</td><td>76.54%</td><td>2575</td><td>9671</td><td>87.84%</td><td>0.69 </td><td>7577</td><td>1468.46 </td></tr>
                        <tr><td>国家文物局<br><br>bm7200</td><td class="not-td">1</td><td>77.60%</td><td>14</td><td>12</td><td>40.00%</td><td>8.77 </td><td>263</td><td>1534.48 </td></tr>
                        <tr><td>国家中医药管理局<br><br>bm7300</td><td class="not-td">1</td><td>37.22%</td><td>943</td><td>25</td><td>83.33%</td><td>1.53 </td><td>46</td><td>1257.59 </td></tr>
                        <tr><td>国家外汇管理局<br><br>bm7400</td><td class="not-td">37</td><td>5.32%</td><td>1</td><td>1014</td><td>91.35%</td><td>0.73 </td><td>809</td><td>1538.19 </td></tr>
                        <tr><td>国务院扶贫开发领导小组办公室<br><br>bm7600</td><td class="not-td">1</td><td>88.83%</td><td>109</td><td>12</td><td>40.00%</td><td>6.27 </td><td>188</td><td>1221.57 </td></tr>
                        <tr><td>国务院三峡工程建设委员会办公室<br><br>bm7700</td><td class="not-td">1</td><td>99.95%</td><td>65</td><td>29</td><td>96.67%</td><td>0.97 </td><td>29</td><td>1263.32 </td></tr>
                        <tr><td>国务院南水北调工程建设委员会办公室<br><br>bm7800</td><td class="not-td">1</td><td>12.78%</td><td>112</td><td>17</td><td>56.67%</td><td>1.47 </td><td>44</td><td>1497.46 </td></tr>
                        <tr><td>国务院台湾事务办公室<br><br>bm8000</td><td class="not-td">1</td><td>0</td><td>13</td><td>16</td><td>53.33%</td><td>12.67 </td><td>380</td><td>1538.63 </td></tr>
                        </tbody>
                    </table>

                </div>
                <!--表格部分结束-->
            </div>
        </div>
    </div>
</div>

<script>
    $(function(){
        $("table tbody tr:odd td").css("background","#fafbfc");
        $("table tbody tr").hover(function(){
            $("table tbody tr").css("background","#fafbfc");
        });

            $('.sel').change(function(){
                var val_=$(this).val();
                //alert(val_);
                if(val_==1){
                    $('.table1').hide();
                    $('#table-Stwodays').show();
                }
                if(val_==2){
                    $('.table1').hide();
                    $('#table-Sweek').show();
                }
                if(val_==3){
                    $('.table1').hide();
                    $('#table-Stwoweeks').show();
                }
                if(val_==4){
                    $('.table1').hide();
                    $('#table-Smonth').show();
                }
                if(val_==5){
                    $('.table1').hide();
                    $('#table-Btwodays').show();
                }
                if(val_==6){
                    $('.table1').hide();
                    $('#table-Bweek').show();
                }
                if(val_==7){
                    $('.table1').hide();
                    $('#table-Btwoweeks').show();
                }
                if(val_==8){
                    $('.table1').hide();
                    $('#table-Bmonth').show();
                }
            });

        $('.tab-change span').click(function(){
            var n = $(this).index();
            $('.tab-change span').removeClass('on');
            $('.sel').removeClass('on');
            $(this).addClass('on');
            $('.sel').eq(n).addClass('on');
            if(n==0){
                $('.table1').hide();
                $('#table-Stwodays').show();
            }
            if(n==1){
                $('.table1').hide();
                $('#table-Btwodays').show();
            }
        })

    });
    //表格切换；
   /* $('.tab-change span').click(function(){
        var n = $(this).index();
        $('.tab-change span').removeClass('on');
        $('.table1').removeClass('on');
        $(this).addClass('on');
        $('.table1').eq(n).addClass('on');
    });*/
    $('td:not(.not-td)').css("color","#2899df");

</script>
</body>
</html>
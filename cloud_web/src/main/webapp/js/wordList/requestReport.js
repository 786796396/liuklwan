/**
 * @描述:访问后台在线预览报告
 * @作者:liujc@ucap.com.cn
 * @时间:2017-4-10下午2:34:18 
 * @param siteCode
 * @param servicePeriodId
 * @param startDate
 * @param endDate
 */
function requestReport(requestParam){
	$.ajax({
        async: false,
        cache: false,
        type: 'POST',
        dataType: "json",
        url: webPath + "/wordList_queryOnlineReportUrl.action",
        success: function (data) {
        	window.open(data.onlineReportUrl+"?"+requestParam);
        }
    });
}
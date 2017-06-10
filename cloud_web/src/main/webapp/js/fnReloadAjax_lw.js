/**
 * By default DataTables only uses the sAjaxSource variable at initialisation
 * time, however it can be useful to re-read an Ajax source and have the table
 * update. Typically you would need to use the `fnClearTable()` and
 * `fnAddData()` functions, however this wraps it all up in a single function
 * call.
 *
 * DataTables 1.10 provides the `dt-api ajax.url()` and `dt-api ajax.reload()`
 * methods, built-in, to give the same functionality as this plug-in. As such
 * this method is marked deprecated, but is available for use with legacy
 * version of DataTables. Please use the new API if you are used DataTables 1.10
 * or newer.
 *
 *  @name fnReloadAjax
 *  @summary Reload the table's data from the Ajax source
 *  @author [Allan Jardine](http://sprymedia.co.uk)
 *  @deprecated
 *
 *  @param {string} [sNewSource] URL to get the data from. If not give, the
 *    previously used URL is used.
 *  @param {function} [fnCallback] Callback that is executed when the table has
 *    redrawn with the new data
 *  @param {boolean} [bStandingRedraw=false] Standing redraw (don't changing the
 *      paging)
 *
 *  @example
 *    var table = $('#example').dataTable();
 *
 *    // Example call to load a new file
 *    table.fnReloadAjax( 'media/examples_support/json_source2.txt' );
 *
 *    // Example call to reload from original file
 *    table.fnReloadAjax();
 */

jQuery.fn.dataTableExt.oApi.fnReloadAjax = function (oSettings, sNewSource, fnCallback, bStandingRedraw) {
    // DataTables 1.10 compatibility - if 1.10 then `versionCheck` exists.
    // 1.10's API has ajax reloading built in, so we use those abilities
    // directly.
    if (jQuery.fn.dataTable.versionCheck) {
        var api = new jQuery.fn.dataTable.Api(oSettings);

        if (sNewSource) {
            api.ajax.url(sNewSource).load(fnCallback, !bStandingRedraw);
        } else {
            api.ajax.reload(fnCallback, !bStandingRedraw);
        }
        return;
    }

    if (sNewSource !== undefined && sNewSource !== null) {
        oSettings.sAjaxSource = sNewSource;
    }

    // Server-side processing should just call fnDraw
    if (oSettings.oFeatures.bServerSide) {
        this.fnDraw();
        return;
    }

    this.oApi._fnProcessingDisplay(oSettings, true);
    var that = this;
    var iStart = oSettings._iDisplayStart;
    var aData = [];

    this.oApi._fnServerParams(oSettings, aData);

    oSettings.fnServerData.call(oSettings.oInstance, oSettings.sAjaxSource, aData, function (json) {
        /* Clear the old information from the table */
        that.oApi._fnClearTable(oSettings);

        /* Got the data - add it to the table */
        var aData = (oSettings.sAjaxDataProp !== "") ?
            that.oApi._fnGetObjectDataFn(oSettings.sAjaxDataProp)(json) : json;

        for (var i = 0; i < aData.length; i++) {
            that.oApi._fnAddData(oSettings, aData[i]);
        }

        oSettings.aiDisplay = oSettings.aiDisplayMaster.slice();

        that.fnDraw();

        if (bStandingRedraw === true) {
            oSettings._iDisplayStart = iStart;
            that.oApi._fnCalculateEnd(oSettings);
            that.fnDraw(false);
        }

        that.oApi._fnProcessingDisplay(oSettings, false);

        /* Callback user function - for event handlers etc */
        if (typeof fnCallback == 'function' && fnCallback !== null) {
            fnCallback(oSettings);
        }
    }, oSettings);
};
var oCache = {
    iCacheLower: -1
};
function fnDataTablesPipeline(sSource, aoData, fnCallback, oSettings) {

	
	
    var iPipe = 1;
    var bNeedServer = false;
    var sEcho = fnGetKey(aoData, "sEcho");
    var iRequestStart = fnGetKey(aoData, "iDisplayStart");
    var iRequestLength = fnGetKey(aoData, "iDisplayLength");
    var iRequestEnd = iRequestStart + iRequestLength;
    oCache.iDisplayStart = iRequestStart;

    var flag = false,
        $active,
        active = "";
    if(oSettings.sInstance == "table_data_home"){
    	aoData.push({name: "type", value: "1"});
    	aoData.push({name: "term", value: $("#table_data_home_term").val()});
    }
    if(oSettings.sInstance == "table_data_business"){
    	aoData.push({name: "type", value: "2"});
    	aoData.push({name: "term", value: $("#table_data_business_term").val()});
    }
    if(oSettings.sInstance=="table_data_blank_info"){
    	aoData.push({name: "term", value: $("#table_data_blank_info_term").val()});
    }
    if(oSettings.sInstance=="table_data_security_response"){
    	aoData.push({name: "term", value: $("#table_data_security_response_term").val()});
    }
    if(oSettings.sInstance=="table_data_security_service"){
    	aoData.push({name: "term", value: $("#table_data_security_service_term").val()});
    }
    if(oSettings.sInstance == "table_data_channel"){
    	aoData.push({name: "type", value: "3"});
    	aoData.push({name: "term", value: $("#table_data_channel_term").val()});
    }
    if(oSettings.sInstance == "table_data_link_home_available"){
    	aoData.push({name: "type", value: "home"});
    	aoData.push({name: "term", value: $("#table_data_link_home_available_term").val()});
    }
    if(oSettings.sInstance == "table_data_link_all_available"){
    	aoData.push({name: "type", value: "all"});
    	aoData.push({name: "term", value: $("#table_data_link_all_available_term").val()});
    }
    if(oSettings.sInstance == "table_data_home_update"){
    	aoData.push({name: "type", value: "home"});
    	aoData.push({name: "term", value: $("#table_data_home_update_term").val()});
    }
    if(oSettings.sInstance == "table_data_channel_update"){
    	aoData.push({name: "type", value: "channel"});
    	aoData.push({name: "term", value: $("#table_data_channel_update_term").val()});
    }
    if(oSettings.sInstance == "table_data_channelInfo_unUpdate"){
    	aoData.push({name: "type", value: "channel"});
    	aoData.push({name: "term", value: $("#table_data_channelInfo_unUpdate_term").val()});
    }
    if(oSettings.sInstance == "table_data_channel_columnTab"){
    	aoData.push({name: "term", value: $("#channelInfo_term").val()});
    	aoData.push({name: "siteCode", value: $("#siteCode").val()});
    	aoData.push({name: "tt", value: new Date().getTime()});
    }
    //组织单位管理，各个级别网站信息列表
    if(oSettings.sInstance == "table_data_webInfos"){
    	aoData.push({name: "departmentLevel", value: departmentLevel});
    	aoData.push({name: "tt", value: new Date().getTime()});
    }
    // 大数据 关停例外数，点进去的分页
    if(oSettings.sInstance == "siteCode_result_table_id"){
    	aoData.push({name: "level", value: parseInt($("#levelVar").val())});
    	aoData.push({name: "siteCode", value: $("#siteCodeVar").val()});
    	aoData.push({name: "flag", value: $("#flagVar").val()});
    	aoData.push({name: "keyWord", value: $("#keyWord_id").val()});
    	aoData.push({name: "xian", value: $("#xianFlag").val()});
    }
    // 大数据 关停例外数，点进去的分页
    if(oSettings.sInstance == "clickk"){
    	aoData.push({name: "status", value: status});
    	aoData.push({name: "siteCode", value: commonSiteCode});
    	aoData.push({name: "clickSiteCode", value: clickSiteCode});
    	aoData.push({name: "keyWord", value: $("#keyWord_id").val()});
    	aoData.push({name: "tt", value: new Date().getTime()});
    }
    if(oSettings.sInstance == "testTable"){
    	aoData.push({name: "siteCode", value: $("#testTerm").val()});
    }
    if(oSettings.sInstance == "business_table"){
    aoData.push({name: "key", value: $("#keyInput").val()});
    aoData.push({name: "date", value:$("#currentDatekerBusiness").val()});
    aoData.push({name: "siteCode", value: $("#siteCode").val()});
    aoData.push({name: "servicePeriodId", value: $("#servicePeriodId").val()});
    aoData.push({name: "startDate", value: $("#startDate").val()});
    aoData.push({name: "endDate", value: $("#endDate").val()});
    aoData.push({name: "tt", value: new Date().getTime()});
    }
    if(oSettings.sInstance == "channel_table"){
    aoData.push({name: "key", value: $("#keyInput").val()});
    aoData.push({name:"date",value:$("#currentDateker").val()});	
    aoData.push({name: "tt", value: new Date().getTime()});
    }
    if(oSettings.sInstance == "table_link_all_id"){
    	aoData.push({name: "periodNum", value:periodNum});
    	aoData.push({name: "servicePdId", value: $("#scanCycleId option:selected").val()});
    	aoData.push({name: "terms", value: $("#link_all_table_key").val()}); 
    	aoData.push({name: "tt", value: new Date().getTime()});
    }
    if(oSettings.sInstance == "table_unUpdate_channel"){
    	aoData.push({name: "key", value: $("#keyInput").val()});
        aoData.push({name: "date", value:$("#dateText").val()});
        aoData.push({name: "type", value:$("#types").val()});
        aoData.push({name: "tt", value: new Date().getTime()});
        aoData.push({name: "siteCode", value: $("#siteCode").val()});
        aoData.push({name: "servicePeriodId", value: $("#servicePeriodId").val()});
        aoData.push({name: "startDate", value: $("#startDate").val()});
        aoData.push({name: "endDate", value: $("#endDate").val()});
    }
    if(oSettings.sInstance == "table_update_home"){
        aoData.push({name: "days", value: $("input[name='days']:checked").val()});
        aoData.push({name: "key", value: $("#keyInput").val()});
        aoData.push({name: "tt", value: new Date().getTime()});
    }
    if(oSettings.sInstance == "table_up_channel_detail"){
    	aoData.push({name: "days", value: $("input[name='days']:checked").val()});
    	aoData.push({name: "key", value: $("#keyInputColumn").val()});
        aoData.push({name: "type", value: $("#types").val()});
        aoData.push({name: "tt", value: new Date().getTime()});
    }
    if(oSettings.sInstance == "early_table"){
    	
    }
    if(oSettings.sInstance == "table_accuracy_content"){
    	aoData.push({name: "key", value: $("#keyInput").val()});
    	aoData.push({name: "beginScanDate", value: $("#scanDate_id_start").val()});
    	aoData.push({name: "endScanDate", value: $("#scanDate_id_end").val()});
    	aoData.push({name: "selectType", value: $("#select_id").val()});
    	aoData.push({name: "tt", value: new Date().getTime()});
    	aoData.push({name: "siteCode", value: $("#siteCode").val()});
    	aoData.push({name: "servicePeriodId", value: $("#servicePeriodId").val()});
    	aoData.push({name: "startDate", value: $("#startDate").val()});
    	aoData.push({name: "endDate", value: $("#endDate").val()});
    }
    if(oSettings.sInstance == "table_link_Home_Info"){
		aoData.push({name: "siteCode", value: $("#siteCode").val()});
		aoData.push({name: "servicePeriodId", value: $("#servicePeriodId").val()});
		aoData.push({name: "startDate", value: $("#startDate").val()});
		aoData.push({name: "endDate", value: $("#endDate").val()});
    	aoData.push({name: "tt", value: new Date().getTime()});
    }
    if(oSettings.sInstance == "table_link_all_info"){
    	aoData.push({name: "siteCode", value: $("#siteCode").val()});
		aoData.push({name: "servicePeriodId", value: $("#servicePeriodId").val()});
		aoData.push({name: "startDate", value: $("#startDate").val()});
		aoData.push({name: "endDate", value: $("#endDate").val()});
    	aoData.push({name: "tt", value: new Date().getTime()});
    }
    
    if(oSettings.sInstance == "table_noDownload"){
    	aoData.push({name: "siteCode", value: $("#siteCode").val()});
    	aoData.push({name: "servicePeriodId", value: $("#servicePeriodId").val()});
    	aoData.push({name: "startDate", value: $("#startDate").val()});
    	aoData.push({name: "endDate", value: $("#endDate").val()});
    	aoData.push({name: "tt", value: new Date().getTime()});
    }
    if(oSettings.sInstance == "table-MH"){
    	aoData.push({name: "orgSiteCode", value: $("#pageSiteCode").val()});
    	aoData.push({name: "dayNum", value: $("#sel  option:selected").val()});
    	aoData.push({name: "sitenum", value: $("#sitenum").val()});
    	aoData.push({name: "tt", value: new Date().getTime()});
    }
    //网站临时报备 组织单位
    if(oSettings.sInstance == "temporary_result_table_id"){
    	aoData.push({name: "siteCode", value: $('#siteCode').val()});
    	aoData.push({name: "startDate", value: $('#startDate').val()});
    	aoData.push({name: "endDate", value: $('#endDate').val()});
    	aoData.push({name: "reportReason", value: $('#reportReason').val()});
    	aoData.push({name: "siteCodeOrName", value: $('#siteCodeOrName').val()});
    	aoData.push({name: "tt", value: new Date().getTime()});
    }
    //网站临时报备 填报单位
    if(oSettings.sInstance == "temporary_result_table_idTB"){
    	aoData.push({name: "siteCode", value: $('#siteCode').val()});
    	aoData.push({name: "startDate", value: $('#startDate').val()});
    	aoData.push({name: "endDate", value: $('#endDate').val()});
    	aoData.push({name: "reportReason", value: $('#reportReason').val()});
    	aoData.push({name: "siteCodeOrName", value: $('#siteCodeOrName').val()});
    	aoData.push({name: "tt", value: new Date().getTime()});
    }
    
    //组织单位管理，各个级别网站信息列表
    if(oSettings.sInstance == "wordList"){
    	aoData.push({name: "siteTypeIdVal", value: $('#siteTypeIdVal').val()});
    	aoData.push({name: "foldName", value: $('#keyId').val()});
    	aoData.push({name: "reportRead", value: $('#chainSelectId').val()});
    	aoData.push({name: "tt", value: new Date().getTime()});
    }
    if(oSettings.sInstance == "errorInfoId"){
    	aoData.push({name: "siteTypeVal", value: $("#siteTypeVal").val()});
    	aoData.push({name: "exposureKeyId", value: $("#exposureKeyId").val()});
    	aoData.push({name: "siteCode", value: $("#siteCodeId").val()});
    	aoData.push({name: "tt", value: new Date().getTime()});
    }
    if(oSettings.sInstance == "databaseInfoId"){
    	aoData.push({name: "aaaTypeVal", value: $("#aaaTypeVal").val()});
    	aoData.push({name: "queryInput", value: $("#queryInput").val()});
    	aoData.push({name: "siteCode", value: $("#siteCodeId").val()});
    	aoData.push({name: "tt", value: new Date().getTime()});
    }
    /* outside pipeline? */
    if (oCache.iCacheLower < 0 || iRequestStart < oCache.iCacheLower || iRequestEnd > oCache.iCacheUpper || flag) {
        bNeedServer = true;
    }

    /* sorting etc changed? */
    if (oCache.lastRequest && !bNeedServer) {
        for (var i = 0, iLen = aoData.length; i < iLen; i++) {
            if (aoData[i].name != "iDisplayStart" && aoData[i].name != "iDisplayLength" && aoData[i].name != "sEcho") {
                if (aoData[i].value != oCache.lastRequest[i].value) {
                    bNeedServer = true;
                    break;
                }
            }
        }
    }

    /* Store the request for checking next time around */
    oCache.lastRequest = aoData.slice();

    if (bNeedServer) {
        if (iRequestStart < oCache.iCacheLower) {
            iRequestStart = iRequestStart - (iRequestLength * (iPipe - 1));
            if (iRequestStart < 0) {
                iRequestStart = 0;
            }
        }

        oCache.iCacheLower = iRequestStart;
        oCache.iCacheUpper = iRequestStart + (iRequestLength * iPipe);
        oCache.iDisplayLength = fnGetKey(aoData, "iDisplayLength");
        fnSetKey(aoData, "iDisplayStart", iRequestStart);
        fnSetKey(aoData, "iDisplayLength", iRequestLength * iPipe);

        var pos = (iRequestStart / iRequestLength + 1) > 1 ? (iRequestStart / iRequestLength + 1) : 1;
        aoData.push({name: "size", value: (iRequestLength)});
        aoData.push({name: "pos", value: pos});
        aoData.push({name: "pageNo", value: pos});
        aoData.push({name:"_t",value:new Date().getTime()});
        oSettings.jqXHR = $.getJSON(sSource, aoData, function (json) {
            /* Callback processing */
            oCache.lastJson = jQuery.extend(true, {}, json);
            if (json.items) {
                if (oCache.iCacheLower != oCache.iDisplayStart) {
                    json.items.splice(0, oCache.iDisplayStart - oCache.iCacheLower);
                    json.body.splice(0, oCache.iDisplayStart - oCache.iCacheLower);
                }
                json.items.splice(oCache.iDisplayLength, json.items.length);
                json.body.splice(oCache.iDisplayLength, json.body.length);
            }
            fnCallback(json);
        });
    } else {
        var json = jQuery.extend(true, {}, oCache.lastJson);
        json.sEcho = sEcho;
        /* Update the echo for each response */
        /*json.items.splice(0, iRequestStart - oCache.iCacheLower);
        json.items.splice(iRequestLength, json.items.length);*/
        json.body.splice(0, iRequestStart - oCache.iCacheLower);
        json.body.splice(iRequestLength, json.body.length);
        fnCallback(json);
    }
}

function fnSetKey(aoData, sKey, mValue) {
    for (var i = 0, iLen = aoData.length; i < iLen; i++) {
        if (aoData[i].name == sKey) {
            aoData[i].value = mValue;
        }
    }
}

function fnGetKey(aoData, sKey) {
    for (var i = 0, iLen = aoData.length; i < iLen; i++) {
        if (aoData[i].name == sKey) {
            return aoData[i].value;
        }
    }
    return null;
}

function getDate(data) {
    var timearray = data.split("T");
    var nyr = timearray[0];
    var tsfm = timearray[1].substr(0, 5);
    var ts = eval(tsfm.split(":")[0] - 0 + 8) + "";
    var tf = tsfm.split(":")[1];
    if (ts.length < 2) {
        ts = "0" + ts;
    }
    if (tf.length < 2) {
        tf = "0" + tf;
    }
    var sfm = ts + ":" + tf;
    return nyr + " " + sfm;
}
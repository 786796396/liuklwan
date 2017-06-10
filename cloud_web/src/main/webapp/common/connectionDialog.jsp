<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--最后更新日期弹框开始-->
<!-- 连通性结果弹框样式 -->

<div class="modal fade last-update" id="last-update" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="false" style="width:690px; margin-left: -345px; left: 50%;display:none">
    <div class="modal-dialog">
        <div class="modal-content" >
            <div class="modal-header green_head clearfix" style=" position: relative; height: 45px;">
                <div type="button" class="close green_head_closeicon"
                     data-dismiss="modal" aria-hidden="true">
                </div>
                <h4 class="modal-title green_head_title fl" id="myModalLabel">
                    网站连通状态详情
                </h4>
                <div class="lud_calendar fl">
                 <input type="hidden" id="minDateSelect" />
                 <input type="hidden" id="siteCode" />
                 <input type="hidden" id="url" />
                 <input type="hidden" id="type" />
                 <!--    <input type="text"/> -->
                   <input class="datepicker-input" type="text" id="datepickerC"/>
                  
                </div>
            </div>
            <div class="modal-body" >
                <div class="table_part_m">
                    <table>
                        <thead>
                            <tr>
                                <th class="text-center w10p">序号</th>
                                <th class="text-left w25p">
                                    时间
                                    <i class="a"></i>
                                </th>
                                <th class="text-center w20p">连通状态</th>
                                <th class="text-left w20p">
                                    问题描述
                                  <!--   <i class="icon-w"></i> -->
                                    <div class="http-html"></div>
                                     <%@ include file="/common/http.jsp"%>
                                </th>
                            </tr>
                        </thead>
                        <tbody id="connectDialogResultId">
                           
                        </tbody>
                    </table>
                </div>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

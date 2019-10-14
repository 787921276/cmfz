<%@page isELIgnored="false" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<script>
    $(function () {
        $("#albumList").jqGrid({
            url: "${path}/album/findByPage",
            editurl: "${path}/album/edit",
            datatype: "json",
            colNames: ["主键", "标题", "封面路径", "评分", "作者", "播音", "集数", "发布时间", "简介", "状态", "上传时间"],
            colModel: [
                {name: "id"},
                {name: "title", editable: true},
                {
                    name: "cover", editable: true, edittype: "file",
                    formatter: function (a, b, c,) {
                        return "<img style='width:100px;height:50px' src='${path}/img/" + a + "' />"
                    }
                },
                {name: "score", editable: true},
                {name: "author", editable: true},
                {name: "beam", editable: true},
                {name: "count", editable: true},
                {name: "publish_date", editable: true, edittype: "date"},
                {name: "content", editable: true},
                {name: "status", editable: true, edittype: "select", editoptions: {value: "展示:展示;不展示:不展示"}},
                {name: "updatatime", editable: true, edittype: "date"},
            ],
            styleUI: "Bootstrap",
            autowidth: true,
            height: "60%",
            pager: "#albumPager",
            page: 1,
            rowNum: 2,
            multiselect: true,
            rowList: [2, 4, 6],
            viewrecords: true,
            subGrid: true,
            subGridRowExpanded: function (subgrid_id, albumId) {
                addSubGrid(subgrid_id, albumId);
            }
        }).jqGrid("navGrid", "#albumPager",
            {//处理页面几个按钮的样式
            },
            {
                closeAfterEdit: true,
                beforeShowForm: function (obj) {
                    obj.find("#cover").attr("disabled", true);
                    obj.find("#score").attr("readonly", true);
                }
            },
            {//在添加数据 之前或者之后进行额外的操作
                /*beforeShowForm:function () {
                    alert("2")
                }*/
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var bannerId = response.responseText;
                    $.ajaxFileUpload({
                        url: "${path}/album/upload",
                        fileElementId: "cover",
                        data: {bannerId: bannerId},
                        success: function (data) {
                            $("#albumList").trigger("reloadGrid")
                        }
                    });
                    return response
                }

            },
            {//在删除数据之前或者之后进行额外的操作
                /*beforeShowForm:function () {
                    alert("3")
                }*/
            }
        )
    });

    function addSubGrid(subgrid_id, albumId) {
        var tableId = subgrid_id + "table";
        var divId = subgrid_id + "div";
        $("#" + subgrid_id).html(
            "<table id='" + tableId + "' ></table>" + "<div id='" + divId + "' ></div>"
        );
        $("#" + tableId).jqGrid({
            url: "${path}/chapter/findByPage?albumid=" + albumId,
            editurl: "${path}/chapter/edit?albumid=" + albumId,
            datatype: "json",
            colNames: ["主键", "标题", "大小", "时长", "创建时间", "音频文件", "操作"],
            colModel: [
                {name: "id"},
                {name: "title", editable: true},
                {name: "size"},
                {name: "longTime"},
                {name: "createDate"},
                {name: "url", editable: true, edittype: "file"},
                {
                    name: "url",
                    formatter: function (cellValue, options, rowObject) {
                        return "<a onclick=\"playAudio('" + cellValue + "')\" href='#'><span class='glyphicon glyphicon-play-circle'></span></a>" + "                       " +
                            "<a onclick=\"downloadAudio('" + cellValue + "')\" href='#'><span class='glyphicon glyphicon-download'></span></a>"
                    }
                }
            ],
            styleUI: "Bootstrap",
            autowidth: true,
            height: "60%",
            pager: "#" + divId,
            page: 1,
            rowNum: 2,
            multiselect: true,
            rowList: [2, 4, 6],
            viewrecords: true
        }).jqGrid("navGrid", "#" + divId,
            {},
            {},
            {
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var chapterId = response.responseText;
                    $.ajaxFileUpload({
                        url: "${path}/chapter/upload?albumId=" + albumId,
                        fileElementId: "url",
                        data: {chapterId: chapterId},
                        success: function (data) {
                            $("#" + tableId).trigger("reloadGrid");
                            $("#albumList").trigger("reloadGrid");
                        }
                    });
                    return response
                }
            },
            {
                afterSubmit: function () {
                    $("#" + tableId).trigger("reloadGrid");
                    $("#albumList").trigger("reloadGrid");
                    return "adf";
                }
            }
        )

    }

    function playAudio(d) {
        $("#dialogId").modal("show");
        $("#audioId").attr("src", "${path}/audio/" + d);
    }

    function downloadAudio(a) {
        location.href = "${path}/chapter/download?audioName=" + a;
    }
</script>
<div id="dialogId" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <audio id="audioId" controls src=""></audio>
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<table id="albumList"></table>
<div id="albumPager"></div>
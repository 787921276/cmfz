<%@page isELIgnored="false" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>
<script>
    $(function () {
        $("#bannerList").jqGrid({
            url: "${path}/Admin/findByPage",
            editurl: "${path}/banner/edit",
            datatype: "json",
            colNames: ["主键", "图片路径", "标题", "状态", "描述信息", "创建时间"],
            colModel: [
                {
                    name: "id"
                },
                {
                    name: "img_path", editable: true, edittype: "file",
                    formatter: function (a, b, c,) {
                        return "<img style='width:100px;height:50px' src='${path}/img/" + a + "' />"
                    }
                },
                {
                    name: "title", editable: true
                },

                {
                    name: "status", editable: true, edittype: "select", editoptions: {value: "展示:展示;不展示:不展示"}
                },
                {
                    name: "decs", editable: true
                },
                {
                    name: "create_date", editable: true, edittype: "date"
                }
            ],
            styleUI: "Bootstrap",
            autowidth: true,
            height: "10%",
            pager: "#bannerPager",
            page: 1,
            rowNum: 2,
            rowList: [2, 4, 6],
            editurl: "${path}/Admin/edit",
            viewrecords: true,
            multiselect: true
        }).jqGrid("navGrid", "#bannerPager",
            {//处理页面几个按钮的样式
            },
            {//在编辑之前或者之后进行额外的操作
                /*beforeShowForm:function () {
                    alert("1")
                }*/
            },
            {//在添加数据 之前或者之后进行额外的操作
                /*beforeShowForm:function () {
                    alert("2")
                }*/
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var bannerId = response.responseText;
                    $.ajaxFileUpload({
                        url: "${path}/banner/upload",
                        fileElementId: "img_path",
                        data: {bannerId: bannerId},
                        success: function (data) {
                            $("#bannerList").trigger("reloadGrid")
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

    function outBanner() {
        location.href = "${pageContext.request.contextPath}/banner/querall"
    }
</script>
<div>

    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab"
                                                  data-toggle="tab">轮播图列表</a></li>
        <li role="presentation"><a href="#profile" onclick="outBanner()" aria-controls="profile" role="tab"
                                   data-toggle="tab">导出轮播图信息</a></li>
    </ul>

</div>
<table id="bannerList"></table>
<div id="bannerPager"></div>
$(function () {
    $("#jqGrid").jqGrid({
        url: '/admin/news/list',
        datatype: "json",
        colModel: [
            {label: '新闻编号', name: 'newsId', index: 'newsId', width: 60, key: true},
            {label: '新闻类型', name: 'newsType', index: 'newsType', width: 120},
            {label: '新闻标题', name: 'newsTitle', index: 'newsTitle', width: 120},
            {label: '新闻作者', name: 'newsAuthor', index: 'newsAuthor', width: 120},
            {label: '创建时间', name: 'newsTime', index: 'newsTime', width: 60}
        ],
        height: 760,
        rowNum: 20,
        rowList: [20, 50, 80],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        rownumWidth: 20,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data.list",
            page: "data.currPage",
            total: "data.totalPage",
            records: "data.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order",
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });

    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });


});

/**
 * jqGrid重新加载
 */
function reload() {
    initFlatPickr();
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}

/**
 * 添加新闻
 */
function addNews() {
    window.location.href = "/admin/news/edit";
}

/**
 * 修改新闻
 */
function editNews() {
    var id = getSelectedRow();
    if (id == null) {
        return;
    }
    window.location.href = "/admin/news/edit/" + id;
}
/**
 * 删除
 */
function deleteNews() {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    swal({
        title: "确认弹框",
        text: "确认要执行下架操作吗?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((flag) => {
            if (flag) {
                $.ajax({
                    type: "PUT",
                    url: "/admin/news/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.resultCode == 200) {
                            swal("删除成功", {
                                icon: "success",
                            });
                            $("#jqGrid").trigger("reloadGrid");
                        } else {
                            swal(r.message, {
                                icon: "error",
                            });
                        }
                    }
                });
            }
        }
    )
    ;
}
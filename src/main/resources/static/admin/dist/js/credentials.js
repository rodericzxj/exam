$(function () {
    $("#jqGrid").jqGrid({
        url: '/admin/credentials/list',
        datatype: "json",
        colModel: [
            {label: '证书编号', name: 'credentialsId', index: 'credentialsId', width: 60, key: true},
            {label: '证书类型', name: 'credentialsType', index: 'credentialsType', width: 120},
            {label: '证书标题', name: 'credentialsTitle', index: 'credentialsTitle', width: 120},
            {label: '证书作者', name: 'credentialsAuthor', index: 'credentialsAuthor', width: 120},
            {label: '创建时间', name: 'credentialsTime', index: 'credentialsTime', width: 60}
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
 * 添加证书
 */
function addCredentials() {
    window.location.href = "/admin/credentials/edit";
}

/**
 * 修改证书
 */
function editCredentials() {
    var id = getSelectedRow();
    if (id == null) {
        return;
    }
    window.location.href = "/admin/credentials/edit/" + id;
}
/**
 * 删除
 */
function deleteCredentials() {
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
                    url: "/admin/credentials/delete",
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
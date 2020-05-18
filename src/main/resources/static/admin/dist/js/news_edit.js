//KindEditor变量
var editor;

$(function () {

    //详情编辑器
    editor = KindEditor.create('textarea[id="editor"]', {
        items: ['source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
            'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
            'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
            'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
            'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
            'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'multiimage',
            'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
            'anchor', 'link', 'unlink'],
        uploadJson: '/admin/upload/file',
        filePostName: 'file'
    });
});

$('#confirmButton').click(function () {
    var newsType = $('#newsType').val();
    var newsTitle = $('#newsTitle').val();
    var newsAuthor = $('#newsAuthor').val();
    var newsDetailContent = editor.html();
    if (isNull(newsType)) {
        swal("请输入新闻类型", {
            icon: "error",
        });
        return;
    }
    if (!validLength(newsType, 50)) {
        swal("新闻类型过长", {
            icon: "error",
        });
        return;
    }
    if (isNull(newsTitle)) {
        swal("请输入新闻标题", {
            icon: "error",
        });
        return;
    }
    if (!validLength(newsTitle, 100)) {
        swal("新闻标题过长", {
            icon: "error",
        });
        return;
    }
    if (isNull(newsAuthor)) {
        swal("请输入新闻作者", {
            icon: "error",
        });
        return;
    }
    if (!validLength(newsAuthor, 50)) {
        swal("新闻作者过长", {
            icon: "error",
        });
        return;
    }

    if (isNull(newsDetailContent)) {
        swal("请输入新闻内容", {
            icon: "error",
        });
        return;
    }
    if (!validLength(newsDetailContent, 50000)) {
        swal("新闻内容过长", {
            icon: "error",
        });
        return;
    }
    $('#newsModal').modal('show');
});

$('#saveButton').click(function () {
    var newsId = $('#newsId').val();
    var newsType = $('#newsType').val();
    var newsTitle = $('#newsTitle').val();
    var newsAuthor = $('#newsAuthor').val();
    var newsDetailContent = editor.html();
    var url = '/admin/news/save';
    var swlMessage = '保存成功';
    var data = {
        "newsId": newsId,
        "newsType": newsType,
        "newsTitle": newsTitle,
        "newsAuthor": newsAuthor,
        "newsValue": newsDetailContent
    };
    if (newsId > 0) {
        url = '/admin/news/update';
        swlMessage = '修改成功';
        data = {
            "newsId": newsId,
            "newsType": newsType,
            "newsTitle": newsTitle,
            "newsAuthor": newsAuthor,
            "newsValue": newsDetailContent
        };
    }
    console.log(data);
    $.ajax({
        type: 'POST',//方法类型
        url: url,
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (result) {
            if (result.resultCode == 200) {
                $('#newsModal').modal('hide');
                swal({
                    title: swlMessage,
                    type: 'success',
                    showCancelButton: false,
                    confirmButtonColor: '#1baeae',
                    confirmButtonText: '返回新闻列表',
                    confirmButtonClass: 'btn btn-success',
                    buttonsStyling: false
                }).then(function () {
                    window.location.href = "/admin/news";
                })
            } else {
                $('#newsModal').modal('hide');
                swal(result.message, {
                    icon: "error",
                });
            }
            ;
        },
        error: function () {
            swal("操作失败", {
                icon: "error",
            });
        }
    });
});

$('#cancelButton').click(function () {
    window.location.href = "/admin/news";
});

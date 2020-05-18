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
    var credentialsType = $('#credentialsType').val();
    var credentialsTitle = $('#credentialsTitle').val();
    var credentialsAuthor = $('#credentialsAuthor').val();
    var credentialsDetailContent = editor.html();
    if (isNull(credentialsType)) {
        swal("请输入证书类型", {
            icon: "error",
        });
        return;
    }
    if (!validLength(credentialsType, 50)) {
        swal("证书类型过长", {
            icon: "error",
        });
        return;
    }
    if (isNull(credentialsTitle)) {
        swal("请输入证书标题", {
            icon: "error",
        });
        return;
    }
    if (!validLength(credentialsTitle, 100)) {
        swal("证书标题过长", {
            icon: "error",
        });
        return;
    }
    if (isNull(credentialsAuthor)) {
        swal("请输入证书作者", {
            icon: "error",
        });
        return;
    }
    if (!validLength(credentialsAuthor, 50)) {
        swal("证书作者过长", {
            icon: "error",
        });
        return;
    }

    if (isNull(credentialsDetailContent)) {
        swal("请输入证书内容", {
            icon: "error",
        });
        return;
    }
    if (!validLength(credentialsDetailContent, 50000)) {
        swal("证书内容过长", {
            icon: "error",
        });
        return;
    }
    $('#credentialsModal').modal('show');
});

$('#saveButton').click(function () {
    var credentialsId = $('#credentialsId').val();
    var credentialsType = $('#credentialsType').val();
    var credentialsTitle = $('#credentialsTitle').val();
    var credentialsAuthor = $('#credentialsAuthor').val();
    var credentialsDetailContent = editor.html();
    var url = '/admin/credentials/save';
    var swlMessage = '保存成功';
    var data = {
        "credentialsId": credentialsId,
        "credentialsType": credentialsType,
        "credentialsTitle": credentialsTitle,
        "credentialsAuthor": credentialsAuthor,
        "credentialsValue": credentialsDetailContent
    };
    if (credentialsId > 0) {
        url = '/admin/credentials/update';
        swlMessage = '修改成功';
        data = {
            "credentialsId": credentialsId,
            "credentialsType": credentialsType,
            "credentialsTitle": credentialsTitle,
            "credentialsAuthor": credentialsAuthor,
            "credentialsValue": credentialsDetailContent
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
                $('#credentialsModal').modal('hide');
                swal({
                    title: swlMessage,
                    type: 'success',
                    showCancelButton: false,
                    confirmButtonColor: '#1baeae',
                    confirmButtonText: '返回证书列表',
                    confirmButtonClass: 'btn btn-success',
                    buttonsStyling: false
                }).then(function () {
                    window.location.href = "/admin/credentials";
                })
            } else {
                $('#credentialsModal').modal('hide');
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
    window.location.href = "/admin/credentials";
});

/**
 * 详情对话框
 */

var frockInfoDlg = {
    data: {
        deptId: "",
        deptName: ""
    }
};
// 点击部门时

layui.use(['layer', 'form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer; // 让当前iframe弹层高度适应
    admin.iframeAuto();
    $('#deptName').click(function () {
        var formName = encodeURIComponent("parent.frockInfoDlg.data.deptName");
        var formId = encodeURIComponent("parent.frockInfoDlg.data.deptId");
        var treeUrl = encodeURIComponent(Feng.ctxPath + "/dept/tree");

        layer.open({
            type: 2,
            title: '部门选择',
            area: ['300px', '400px'],
            content: Feng.ctxPath + '/system/commonTree?formName=' + formName + "&formId=" + formId + "&treeUrl=" + treeUrl,
            end: function () {
                console.log(frockInfoDlg.data);
                $("#deptId").val(frockInfoDlg.data.deptId);
                $("#deptName").val(frockInfoDlg.data.deptName);
            }
        });
    });

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/frockInfo/add", function (data) {
            Feng.success("添加成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });
});
/**
 * 角色详情对话框
 */
var UserInfoDlg = {
    data: {
        deptId: "",
        deptName: ""
    }
};

layui.use(['layer', 'form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;

    // 让当前iframe弹层高度适应
    admin.iframeAuto();

    // //初始化角色的详情数据
    var ajax = new $ax(Feng.ctxPath + "/group/view/" + Feng.getUrlParam("id"));
    var result = ajax.start();
    form.val('groupForm',result.data);

    // // 点击上级角色时
    // $('#pName').click(function () {
    //     var formName = encodeURIComponent("parent.groupInfoDlg.data.pName");
    //     var formId = encodeURIComponent("parent.groupInfoDlg.data.pid");
    //     var treeUrl = encodeURIComponent(Feng.ctxPath + "/groupTreeList");
    //
    //     layer.open({
    //         type: 2,
    //         title: '父级角色选择',
    //         area: ['300px', '200px'],
    //         content: Feng.ctxPath + '/system/commonTree?formName=' + formName + "&formId=" + formId + "&treeUrl=" + treeUrl,
    //         end: function () {
    //             $("#pid").val(groupInfoDlg.data.pid);
    //             $("#pName").val(groupInfoDlg.data.pName);
    //         }
    //     });
    // });
// 点击部门时
    $('#deptName').click(function () {
        var formName = encodeURIComponent("parent.UserInfoDlg.data.deptName");
        var formId = encodeURIComponent("parent.UserInfoDlg.data.deptId");
        var treeUrl = encodeURIComponent(Feng.ctxPath + "/dept/tree");

        layer.open({
            type: 2,
            title: '部门选择',
            area: ['300px', '400px'],
            content: Feng.ctxPath + '/system/commonTree?formName=' + formName + "&formId=" + formId + "&treeUrl=" + treeUrl,
            end: function () {
                console.log(UserInfoDlg.data);
                $("#deptId").val(UserInfoDlg.data.deptId);
                $("#deptName").val(UserInfoDlg.data.deptName);
            }
        });
    });
    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/group/edit", function (data) {
            Feng.success("修改成功!");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("修改失败!" + data.responseJSON.message + "!");
        });
        ajax.set(data.field);
        ajax.start();
    });
});
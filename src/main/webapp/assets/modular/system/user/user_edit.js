/**
 * 用户详情对话框
 */
var UserInfoDlg = {
    data: {
        deptId: "",
        deptName: ""
    }
};


layui.use(['layer', 'form', 'admin', 'laydate', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var laydate = layui.laydate;
    var layer = layui.layer;
    var layedit = layui.layedit;

    // 让当前iframe弹层高度适应
    admin.iframeAuto();

    //获取用户信息
    var ajax = new $ax(Feng.ctxPath + "/mgr/getUserInfo?userId=" + Feng.getUrlParam("userId"));
    var result = ajax.start();
    form.val('userForm', result.data);

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



    // 添加表单验证方法
    form.verify({
        psw: [/^[\S]{6,12}$/, '密码必须6到12位，且不能出现空格'],
        repsw: function (value) {
            if (value !== $('#userForm input[name=password]').val()) {
                return '两次密码输入不一致';
            }
        }
    });

    // 渲染时间选择框
    laydate.render({
        elem: '#birthday'
    });

    // 渲染入所时间选择框
    laydate.render({
        elem: '#joinDate'
    });



    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/mgr/edit", function (data) {
            Feng.success("修改成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("修改成功！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });


    // form.on('select(groupId)', function(data) {
    //     $.getJSON(Feng.ctxPath + "/system/getGroupName", function (data) {
    //         var optionstring = "";
    //         $.each(data.data, function (i, item) {
    //             optionstring += "<option value=\"" + item.id + "\" >" + item.name + "</option>";
    //         });
    //         $("#groupId").html("<option value=\"" + "" + "\" >"+optionstring);
    //         form.render('select'); //这个很重要
    //     });
    // })

//     $(function () {
//         $.ajax({
// //提交数据的类型 POST GET
//             type: "POST",
// //提交的网址
//         url: Feng.ctxPath+"/system/getGroupName",
// //提交的数据
// //返回数据的格式
//         datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
// //成功返回之后调用的函数
//         success: function (data) {
//             $.each(data, function (index, item) {
//                 $('#groupId').append(new Option(item.name, item.id));// 下拉菜单里添加元素
//             });
//             layui.form.render("select");
//             alert(data.toString())
//         }, error: function () {
//             alert("查询部门失败！！！")
//         }
//     });
//     })
});
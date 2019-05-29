layui.use(['layer', 'form', 'table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 系统管理--角色管理
     */
    var group = {
        tableId: "groupTable",    //表格id
        condition: {
            name: "",
            deptId: "",
            status: ""
        }
    };

    /**
     * 初始化表格的列
     */
    group.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', sort: true, title: '专业组ID'},
            {field: 'name', sort: true, title: '专业组名称'},
            {field: 'deptId', sort: true, title: '部门ID'},
            {field: 'status', sort: true, title: '状态'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    group.search = function () {
        var queryData = {};
        queryData['name'] = $("#name").val();
        queryData['deptId'] = $("#deptId").val();
        queryData['status'] = $("#status").val();
        table.reload(group.tableId, {where: queryData});
    };

    /**
     * 弹出添加角色
     */
    group.openAddgroup = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加专业组',
            content: Feng.ctxPath + '/group/group_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(group.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    group.exportExcel = function () {
        var checkRows = table.checkStatus(group.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击编辑角色
     *
     * @param data 点击按钮时候的行数据
     */
    group.onEditgroup = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改专业组',
            content: Feng.ctxPath + '/group/group_edit?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(group.tableId);
            }
        });
    };

    /**
     * 点击删除角色
     *
     * @param data 点击按钮时候的行数据
     */
    group.onDeletegroup = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/group/remove", function () {
                Feng.success("删除成功!");
                table.reload(group.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除专业组 " + data.name + "?", operation);
    };

    /**
     * 分配菜单
     *
     * @param data 点击按钮时候的行数据
     */
    group.groupAssign = function (data) {
        layer.open({
            type: 2,
            title: '权限配置',
            area: ['300px', '450px'], //宽高
            fix: false,
            maxmin: true,
            content: Feng.ctxPath + '/group/group_assign/' + data.groupId,
            end: function () {
                table.reload(group.tableId);
            }
        });
    };
// alert("fdsfds");
    // 渲染表格
    var tableResult = table.render({
        elem: '#' + group.tableId,
        url: Feng.ctxPath + '/group/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: group.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        group.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        group.openAddgroup();
    });

    // 导出excel
    $('#btnExp').click(function () {
        group.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + group.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            group.onEditgroup(data);
        } else if (layEvent === 'delete') {
            group.onDeletegroup(data);
        } else if (layEvent === 'groupAssign') {
            group.groupAssign(data);
        }
    });
});

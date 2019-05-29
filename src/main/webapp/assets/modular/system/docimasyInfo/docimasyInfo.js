layui.use(['layer', 'form', 'table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 系统管理--管理
     */
    var docimasyInfo = {
        tableId: "docimasyInfoTable",    //表格id
        condition: {
            docimasyInfoName: ""
        }
    };

    /**
     * 初始化表格的列
     */
    docimasyInfo.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, sort: true, title: 'id'},
            {field: 'dcsCode', sort: true, title: '检定编号'},
            {field: 'typeCode', sort: true, title: '设备编号'},
            {field: 'dcsInspection', sort: true, title: '送检方式'},
            {field: 'dcsMonth', sort: true, title: '检定周期'},
            {field: 'dcsUnit', sort: true, title: '承检单位'},
            {field: 'isDcsScene', sort: true, title: '是否现场检定'},
            {field: 'dcsCost', sort: true, title: '检定费用'},
            {field: 'otherCost', sort: true, title: '其他费用'},
            {field: 'dcsCertificateCode', sort: true, title: '证书编号'},
            {field: 'dcsResult', sort: true, title: '检定结论'},
            {field: 'dcsNextTime', sort: true, title: '下次检定日期'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 260}
        ]];
    };

    /**
     * 点击查询按钮
     */
    docimasyInfo.search = function () {
        var queryData = {};
        queryData['condition'] = $("#docimasyInfoName").val();
        table.reload(docimasyInfo.tableId, {where: queryData});
    };

    /**
     * 弹出添加
     */
    docimasyInfo.openAdddocimasyInfo = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加',
            content: Feng.ctxPath + '/docimasyInfo/docimasyInfo_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(docimasyInfo.tableId);
            }
        });
    };



    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    docimasyInfo.onEditdocimasyInfo = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改',
            content: Feng.ctxPath + '/docimasyInfo/docimasyInfo_edit?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(docimasyInfo.tableId);
            }
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    docimasyInfo.onDeletedocimasyInfo = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/docimasyInfo/remove", function () {
                Feng.success("删除成功!");
                table.reload(docimasyInfo.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除该编号"+data.typeCode+"的检定 "  + "?", operation);
    };



    // 渲染表格
    var tableResult = table.render({
        elem: '#' + docimasyInfo.tableId,
        url: Feng.ctxPath + '/docimasyInfo/list?type='+$("#docimasyInfoType").val(),
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: docimasyInfo.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        docimasyInfo.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        docimasyInfo.openAdddocimasyInfo();
    });

    // 导出excel
    $('#btnExp').click(function () {
        docimasyInfo.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + docimasyInfo.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            docimasyInfo.onEditdocimasyInfo(data);
        } else if (layEvent === 'delete') {
            docimasyInfo.onDeletedocimasyInfo(data);
        }
    });
});

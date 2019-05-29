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
    var frockInfo = {
        tableId: "frockInfoTable",    //表格id
        condition: {
            frockInfoName: ""
        }
    };

    /**
     * 初始化表格的列
     */
    frockInfo.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, sort: true, title: 'id'},
            {field: 'froName', sort: true, title: '工装名称'},
            {field: 'froCode', sort: true, title: '编号'},
            {field: 'froOem', sort: true, title: '生产厂家'},
            {field: 'froModel', sort: true, title: '工装型号'},
            {field: 'factoryCode', sort: true, title: '出厂编号'},
            {field: 'froPerson', sort: true, title: '设备负责人'},
            {field: 'openTime', sort: true, title: '启用日期'},
            {field: 'professionalUse', sort: true, title: '使用专业'},
            {field: 'storageLocation', sort: true, title: '存放地点'},
            {field: 'isCheck', sort: true, title: '是否检定'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 300}
        ]];
    };

    /**
     * 点击查询按钮
     */
    frockInfo.search = function () {
        var queryData = {};
        queryData['condition'] = $("#frockInfoName").val();
        table.reload(frockInfo.tableId, {where: queryData});
    };

    /**
     * 弹出添加
     */
    frockInfo.openAddfrockInfo = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加',
            content: Feng.ctxPath + '/frockInfo/frockInfo_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(frockInfo.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    frockInfo.exportExcel = function () {
        var checkRows = table.checkStatus(frockInfo.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };

    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    frockInfo.onEditfrockInfo = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改',
            content: Feng.ctxPath + '/frockInfo/frockInfo_edit?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(frockInfo.tableId);
            }
        });
    };
    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */
    frockInfo.onAddCheckfrockInfo = function (data) {
        var ajax = new $ax(Feng.ctxPath + "/docimasyInfo/typeId?typeId=" + data.id);
        var result = ajax.start();
        if("fail"==result){
            admin.putTempData('formOk', false);
            top.layui.admin.open({
                type: 2,
                title: '增加检定信息',
                content: Feng.ctxPath + '/docimasyInfo/docimasyInfo_add?typeId=' + data.id +"&type=" +2 ,
                end: function () {
                    admin.getTempData('formOk') && table.reload(frockInfo.tableId);
                }
            });
        }else{
            layer.msg('你已经增加检定信息');
        }

    };
    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    frockInfo.onDeletefrockInfo = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/frockInfo/remove", function () {
                Feng.success("删除成功!");
                table.reload(frockInfo.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除设备 " + data.froName + "?", operation);
    };



    // 渲染表格
    var tableResult = table.render({
        elem: '#' + frockInfo.tableId,
        url: Feng.ctxPath + '/frockInfo/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: frockInfo.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        frockInfo.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        frockInfo.openAddfrockInfo();
    });

    // 导出excel
    $('#btnExp').click(function () {
        frockInfo.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + frockInfo.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            frockInfo.onEditfrockInfo(data);
        } else if (layEvent === 'delete') {
            frockInfo.onDeletefrockInfo(data);
        }else if(layEvent === 'addCheck'){
            frockInfo.onAddCheckfrockInfo(data);
        }
    });
});

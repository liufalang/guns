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
    var equipmentInfo = {
        tableId: "equipmentInfoTable",    //表格id
        condition: {
            equipmentInfoName: ""
        }
    };

    /**
     * 初始化表格的列
     */
    equipmentInfo.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, sort: true, title: 'id'},
            {field: 'eptName', sort: true, title: '设备名称'},
            {field: 'eptCode', sort: true, title: '编号'},
            {field: 'eptOem', sort: true, title: '生产厂家'},
            {field: 'eptModel', sort: true, title: '设备型号'},
            {field: 'factoryCode', sort: true, title: '出厂编号'},
            {field: 'measurRang', sort: true, title: '测量范围'},
            {field: 'eptPerson', sort: true, title: '设备负责人'},
            {field: 'openTime', sort: true, title: '启用日期'},
            {field: 'professionalUse', sort: true, title: '使用专业'},
            {field: 'storageLocation', sort: true, title: '存放地点'},
            {field: 'isStandardDevice', sort: true, title: '是否标准器'},
            {field: 'uncertainty', sort: true, title: '最大允许误差'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 300}
        ]];
    };

    /**
     * 点击查询按钮
     */
    equipmentInfo.search = function () {
        var queryData = {};
        queryData['condition'] = $("#equipmentInfoName").val();
        table.reload(equipmentInfo.tableId, {where: queryData});
    };

    /**
     * 弹出添加
     */
    equipmentInfo.openAddequipmentInfo = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加',
            content: Feng.ctxPath + '/equipmentInfo/equipmentInfo_add',
            end: function () {
                admin.getTempData('formOk') && table.reload(equipmentInfo.tableId);
            }
        });
    };

    /**
     * 导出excel按钮
     */
    equipmentInfo.exportExcel = function () {
        var checkRows = table.checkStatus(equipmentInfo.tableId);
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
    equipmentInfo.onEditequipmentInfo = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改',
            content: Feng.ctxPath + '/equipmentInfo/equipmentInfo_edit?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(equipmentInfo.tableId);
            }
        });
    };
    /**
     * 点击编辑
     *
     * @param data 点击按钮时候的行数据
     */

    equipmentInfo.onAddCheckequipmentInfo = function (data) {
        var ajax = new $ax(Feng.ctxPath + "/docimasyInfo/typeId?typeId=" + data.id);
        var result = ajax.start();
        if("fail"==result){
            admin.putTempData('formOk', false);
            top.layui.admin.open({
                type: 2,
                title: '增加检定信息',
                content: Feng.ctxPath + '/docimasyInfo/docimasyInfo_add?typeId=' + data.id +"&type=" +1,
                end: function () {
                    admin.getTempData('formOk') && table.reload(equipmentInfo.tableId);
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
    equipmentInfo.onDeleteequipmentInfo = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/equipmentInfo/remove", function () {
                Feng.success("删除成功!");
                table.reload(equipmentInfo.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除设备 " + data.eptName + "?", operation);
    };



    // 渲染表格
    var tableResult = table.render({
        elem: '#' + equipmentInfo.tableId,
        url: Feng.ctxPath + '/equipmentInfo/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: equipmentInfo.initColumn()
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        equipmentInfo.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        equipmentInfo.openAddequipmentInfo();
    });

    // 导出excel
    $('#btnExp').click(function () {
        equipmentInfo.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + equipmentInfo.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            equipmentInfo.onEditequipmentInfo(data);
        } else if (layEvent === 'delete') {
            equipmentInfo.onDeleteequipmentInfo(data);
        }else if(layEvent === 'addCheck'){
            equipmentInfo.onAddCheckequipmentInfo(data);
        }
    });
});

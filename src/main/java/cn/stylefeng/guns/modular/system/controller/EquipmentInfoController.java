/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.modular.system.controller;
import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.system.entity.EquipmentInfo;
import cn.stylefeng.guns.modular.system.model.EquipmentInfoDto;
import cn.stylefeng.guns.modular.system.service.EquipmentInfoService;
import cn.stylefeng.guns.modular.system.service.UserService;
import cn.stylefeng.guns.modular.system.warpper.EquipmentInfoWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2017年2月12日21:59:14
 */
@Controller
@RequestMapping("/equipmentInfo")
@Api(value = "equipmentInfo")
public class EquipmentInfoController extends BaseController {

    private static String PREFIX = "/modular/system/equipmentInfo";

    @Autowired
    private UserService userService;
    @Autowired
    private EquipmentInfoService equipmentInfoService;


    /**
     * 跳转到列表页面
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:30 PM
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/equipmentInfo.html";
    }

    /**
     * 跳转到添加
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:30 PM
     */
    @RequestMapping(value = "/equipmentInfo_add")
    public String equipmentInfoAdd() {
        return PREFIX + "/equipmentInfo_add.html";
    }

    /**
     * 跳转到修改
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @RequestMapping(value = "/equipmentInfo_edit")
    public String equipmentInfoEdit(@RequestParam String id) {
        if (ToolUtil.isEmpty(id)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        EquipmentInfo EquipmentInfo = this.equipmentInfoService.getById(id);
        LogObjectHolder.me().set(EquipmentInfo);
        return PREFIX + "/EquipmentInfo_edit.html";
    }

    /**
     * 跳转到权限分配
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @Permission
    @RequestMapping(value = "/equipmentInfo_assign/{id}")
    public String EquipmentInfoAssign(@PathVariable("id") String id, Model model) {
        if (ToolUtil.isEmpty(id)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        model.addAttribute("id", id);
        return PREFIX + "/equipmentInfo_assign.html";
    }

    /**
     * 获取列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @ApiOperation(value="获取信息List")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list( String condition) {
        Page<Map<String, Object>> EquipmentInfos = this.equipmentInfoService.selectEquipmentInfos(condition);
        Page<Map<String, Object>> wrap = new EquipmentInfoWrapper(EquipmentInfos).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    /**
     * 新增
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @ApiOperation(value="add")
    @RequestMapping(value = "/add")
    @ResponseBody
    public ResponseData add(EquipmentInfo equipmentInfo) {
        this.equipmentInfoService.addEquipmentInfo(equipmentInfo);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @ApiOperation(value="edit")
    @RequestMapping(value = "/edit")
    @ResponseBody
    public ResponseData edit( EquipmentInfo equipmentInfo) {
        this.equipmentInfoService.editEquipmentInfo(equipmentInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @ApiOperation(value="del")
    @RequestMapping(value = "/remove")
    @ResponseBody
    public ResponseData remove(@RequestParam String id) {
        this.equipmentInfoService.deleteEquipmentInfosById(id);
        return SUCCESS_TIP;
    }

    /**
     * 查看
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @ApiOperation(value="get")
    @RequestMapping(value = "/view/{id}")
    @ResponseBody
    public Object view(@PathVariable String id) {
        if (ToolUtil.isEmpty(id)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        EquipmentInfo equipmentInfo = this.equipmentInfoService.getById(id);
        EquipmentInfoDto edto= new EquipmentInfoDto();
        BeanUtil.copyProperties(equipmentInfo, edto);
        edto.setDeptName( ConstantFactory.me().getDeptName(equipmentInfo.getDeptId()));
        return edto;
    }


}

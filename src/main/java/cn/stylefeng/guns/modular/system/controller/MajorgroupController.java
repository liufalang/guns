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
import cn.hutool.core.collection.CollectionUtil;
import cn.stylefeng.guns.config.properties.GunsProperties;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.core.common.constant.dictmap.DeleteDict;
import cn.stylefeng.guns.core.common.constant.dictmap.MajorgroupDict;
import cn.stylefeng.guns.core.common.constant.dictmap.RoleDict;
import cn.stylefeng.guns.core.common.constant.dictmap.UserDict;
import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.common.constant.state.ManagerStatus;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.modular.system.entity.Majorgroup;
import cn.stylefeng.guns.modular.system.entity.Role;
import cn.stylefeng.guns.modular.system.entity.User;
import cn.stylefeng.guns.modular.system.factory.UserFactory;
import cn.stylefeng.guns.modular.system.model.MajorgroupDto;
import cn.stylefeng.guns.modular.system.model.RoleDto;
import cn.stylefeng.guns.modular.system.model.UserDto;
import cn.stylefeng.guns.modular.system.service.DeptService;
import cn.stylefeng.guns.modular.system.service.MajorgroupService;
import cn.stylefeng.guns.modular.system.service.UserService;
import cn.stylefeng.guns.modular.system.warpper.MajorgroupWrapper;
import cn.stylefeng.guns.modular.system.warpper.RoleWrapper;
import cn.stylefeng.guns.modular.system.warpper.UserWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.datascope.DataScope;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 系统管理员控制器
 *
 * @author fengshuonan
 * @Date 2017年1月11日 下午1:08:17
 */
@Controller
@RequestMapping("/group")
public class MajorgroupController extends BaseController {

    private static String PREFIX = "/modular/system/group";

    @Autowired
    private GunsProperties gunsProperties;

    @Autowired
    MajorgroupService majorgroupService;

    @Autowired
    DeptService deptService;

    @RequestMapping("")
    public String index() {
        return PREFIX + "/group.html";
    }

    @RequestMapping(value = "/group_add")
    public String roleAdd() {
        return PREFIX + "/group_add.html";
    }


    /**
     * 修改专业组
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @Permission
    @RequestMapping(value = "/group_edit")
    public String roleEdit(@RequestParam String id) {
        if (ToolUtil.isEmpty(id)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        Majorgroup role = this.majorgroupService.getById(id);
        LogObjectHolder.me().set(role);
        return PREFIX + "/group_edit.html";
    }


    @RequestMapping("/list")
    @ResponseBody
    public Object getList(@RequestParam(required = false) String name,
                          @RequestParam(required = false) String deptId,
                          @RequestParam(required = false) String status) {
        System.out.println("fdsfasfds");
        Page<Map<String, Object>>  page=majorgroupService.selectGroup(name,deptId,status);
        Page<Map<String, Object>> wrap = new MajorgroupWrapper(page).wrap();
//        System.out.println("2fdsfasfds");
        return LayuiPageFactory.createPageInfo(wrap);
    }

    /**
     * 查看专业组
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @RequestMapping(value = "/view/{id}")
    @ResponseBody
    public ResponseData view(@PathVariable String id) {
        if (ToolUtil.isEmpty(id)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        Majorgroup majorgroup = this.majorgroupService.getById(id);

        Map<String, Object> roleMap = BeanUtil.beanToMap(majorgroup);
        roleMap.put("deptName",(deptService.getById(majorgroup.getDeptId())).getSimpleName());
        System.out.println(roleMap.toString());
        return ResponseData.success(roleMap);
    }

    /**
     * 专业组新增
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @RequestMapping(value = "/add")
    @BussinessLog(value = "添加专业组", key = "name", dict = MajorgroupDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData addMajorGroup(Majorgroup role) {
        this.majorgroupService.addMajorGroup(role);
        return SUCCESS_TIP;
    }
//
    /**
     * 专业组修改
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @RequestMapping(value = "/edit")
    @BussinessLog(value = "修改专业组", key = "id", dict = RoleDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData edit(MajorgroupDto roleDto) {
        this.majorgroupService.editGroup(roleDto);
        return SUCCESS_TIP;
    }

    /**
     * 删除专业组
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @RequestMapping(value = "/remove")
    @BussinessLog(value = "删除专业组", key = "id", dict = DeleteDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData remove(@RequestParam String id) {

        //缓存被删除的专业组id
        LogObjectHolder.me().set(id);

        this.majorgroupService.delGroupById(id);
        return SUCCESS_TIP;
    }

}

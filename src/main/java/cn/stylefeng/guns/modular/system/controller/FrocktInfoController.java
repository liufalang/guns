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
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.system.entity.FrockInfo;
import cn.stylefeng.guns.modular.system.service.FrockInfoService;
import cn.stylefeng.guns.modular.system.service.UserService;
import cn.stylefeng.guns.modular.system.warpper.FrockInfoWrapper;
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

import java.util.Map;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2017年2月12日21:59:14
 */
@Controller
@RequestMapping("/frockInfo")
@Api(value = "frockInfo")
public class FrocktInfoController extends BaseController {

    private static String PREFIX = "/modular/system/frockInfo";

    @Autowired
    private UserService userService;

    @Autowired
    private FrockInfoService frockInfoService;

    /**
     * 跳转到列表页面
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:30 PM
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/frockInfo.html";
    }

    /**
     * 跳转到添加
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:30 PM
     */
    @PostMapping(value = "/frockInfo_add")
    public String frockInfoAdd() {
        return PREFIX + "/frockInfo_add.html";
    }

    /**
     * 跳转到修改
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @Permission
    @RequestMapping(value = "/frockInfo_edit")
    public String frockInfoEdit(@RequestParam Long id) {
        if (ToolUtil.isEmpty(id)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        FrockInfo frockInfo = this.frockInfoService.getById(id);
        LogObjectHolder.me().set(frockInfo);
        return PREFIX + "/frockInfo_edit.html";
    }

    /**
     * 跳转到权限分配
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @Permission
    @RequestMapping(value = "/frockInfo_assign/{id}")
    public String frockInfoAssign(@PathVariable("id") Long id, Model model) {
        if (ToolUtil.isEmpty(id)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        model.addAttribute("id", id);
        return PREFIX + "/frockInfo_assign.html";
    }

    /**
     * 获取列表
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @ApiOperation(value="获取信息List")
    @PostMapping(value = "/list")
    @ResponseBody
    public Object list( String frockInfoName) {
        Page<Map<String, Object>> frockInfos = this.frockInfoService.selectFrockInfos(frockInfoName);
        Page<Map<String, Object>> wrap = new FrockInfoWrapper(frockInfos).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }

    /**
     * 新增
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @ApiOperation(value="add")
    @PostMapping(value = "/add")
    @ResponseBody
    public ResponseData add(FrockInfo frockInfo) {
        this.frockInfoService.addFrockInfo(frockInfo);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @ApiOperation(value="edit")
    @PostMapping(value = "/edit")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public ResponseData edit( FrockInfo frockInfo) {
        this.frockInfoService.editFrockInfo(frockInfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @ApiOperation(value="del")
    @GetMapping(value = "/remove")
    @ResponseBody
    public ResponseData remove(@RequestParam String id) {
        this.frockInfoService.deleteFrockInfosById(id);
        return SUCCESS_TIP;
    }

    /**
     * 查看
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @PostMapping(value = "/view/{id}")
    @ApiOperation(value="get")
    @ResponseBody
    public ResponseData view(@PathVariable String id) {
        if (ToolUtil.isEmpty(id)) {
            throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
        }
        FrockInfo frockInfo = this.frockInfoService.getById(id);

        return ResponseData.success(frockInfo);
    }


}

package cn.stylefeng.guns.modular.system.service;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.core.common.constant.cache.Cache;
import cn.stylefeng.guns.core.common.constant.state.ManagerStatus;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.node.MenuNode;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.shiro.ShiroKit;
import cn.stylefeng.guns.core.shiro.ShiroUser;
import cn.stylefeng.guns.core.shiro.service.UserAuthService;
import cn.stylefeng.guns.core.util.ApiMenuFilter;
import cn.stylefeng.guns.core.util.CacheUtil;
import cn.stylefeng.guns.modular.system.entity.Majorgroup;
import cn.stylefeng.guns.modular.system.entity.Menu;
import cn.stylefeng.guns.modular.system.entity.Role;
import cn.stylefeng.guns.modular.system.entity.User;
import cn.stylefeng.guns.modular.system.factory.UserFactory;
import cn.stylefeng.guns.modular.system.mapper.MajorgroupMapper;
import cn.stylefeng.guns.modular.system.mapper.MenuMapper;
import cn.stylefeng.guns.modular.system.mapper.UserMapper;
import cn.stylefeng.guns.modular.system.model.MajorgroupDto;
import cn.stylefeng.guns.modular.system.model.RoleDto;
import cn.stylefeng.guns.modular.system.model.UserDto;
import cn.stylefeng.roses.core.datascope.DataScope;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-07
 */
@Service
public class MajorgroupService extends ServiceImpl<MajorgroupMapper, Majorgroup>{

    @Resource
    MajorgroupMapper majorgroupMapper;


    /**
     * 根据条件查询用户列表
     *
     * @author fengshuonan
     * @Date 2018/12/24 22:45
     */
     public Page<Map<String, Object>> selectGroup( String name, String deptId, String
            status) {
         Page page = LayuiPageFactory.defaultPage();
         return this.baseMapper.selectGroup(page,status,name,deptId);

    }

    /**
     * 添加角色
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:40 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void addMajorGroup(Majorgroup majorgroup) {

        if (ToolUtil.isOneEmpty(majorgroup, majorgroup.getName(), majorgroup.getStatus(), majorgroup.getDeptId())) {
            throw new RequestEmptyException();
        }

        majorgroup.setId(UUID.randomUUID().toString().replaceAll("-",""));

        this.save(majorgroup);
    }

    public int delGroupById(@Param("id") String id){
        return this.baseMapper.delGroupById(id);
    }

    /**
     * 编辑角色
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:40 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void editGroup(MajorgroupDto groupDto) {

        if (ToolUtil.isOneEmpty(groupDto, groupDto.getId(), groupDto.getName(), groupDto.getDeptId(),groupDto.getStatus())) {
            throw new RequestEmptyException();
        }

        Majorgroup old = this.getById(groupDto.getId());
        BeanUtil.copyProperties(groupDto, old);
        this.updateById(old);

        //删除缓存
        CacheUtil.removeAll(Cache.CONSTANT);
    }
}

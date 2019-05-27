package cn.stylefeng.guns.modular.system.service;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.constant.cache.Cache;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.util.CacheUtil;
import cn.stylefeng.guns.modular.system.entity.EquipmentInfo;
import cn.stylefeng.guns.modular.system.mapper.EquipmentInfoMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 表 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-07
 */
@Service
public class EquipmentInfoService extends ServiceImpl<EquipmentInfoMapper, EquipmentInfo> {
    @Resource
    private EquipmentInfoMapper equipmentInfoMapper;

    @Resource
    private UserService userService;

    /**
     * 添加
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:40 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void addEquipmentInfo(EquipmentInfo equipmentInfo) {
        equipmentInfo.setProcurementTime(new Date());
        equipmentInfo.setOpenTime(new Date());
        equipmentInfo.setId(UUID.randomUUID().toString());
        this.save(equipmentInfo);
    }

    /**
     * 编辑
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:40 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void editEquipmentInfo(EquipmentInfo equipmentInfo) {
        EquipmentInfo old = this.getById(equipmentInfo.getId());
        BeanUtil.copyProperties(equipmentInfo, old);
        this.updateById(old);
        //删除缓存
        CacheUtil.removeAll(Cache.CONSTANT);
    }
    /**
     * 根据条件查询列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    public Page<Map<String, Object>> selectEquipmentInfos(String condition) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.selectEquipmentInfos(page, condition);
    }

    /**
     * 删除某
     *
     * @param id
     * @return
     * @date 2017年2月13日 下午7:57:51
     */
    @Transactional
    public int deleteEquipmentInfosById(String id) {
        return this.baseMapper.deleteEquipmentInfosById(id);
    }



}

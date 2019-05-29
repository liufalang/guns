package cn.stylefeng.guns.modular.system.service;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.constant.cache.Cache;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.util.CacheUtil;
import cn.stylefeng.guns.modular.system.entity.DocimasyInfo;
import cn.stylefeng.guns.modular.system.entity.EquipmentInfo;
import cn.stylefeng.guns.modular.system.entity.FrockInfo;
import cn.stylefeng.guns.modular.system.mapper.DocimasyInfoMapper;
import cn.stylefeng.guns.modular.system.mapper.EquipmentInfoMapper;
import cn.stylefeng.guns.modular.system.mapper.FrockInfoMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.beetl.ext.fn.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 表 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-07
 */
@Service
public class DocimasyInfoService extends ServiceImpl<DocimasyInfoMapper, DocimasyInfo> {
    @Resource
    private DocimasyInfoMapper docimasyInfoMapper;

    @Resource
    private UserService userService;

    @Resource
    private EquipmentInfoMapper equipmentInfoMapper;

    @Resource
    private FrockInfoMapper frockInfoMapper;

    /**
     * 添加
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:40 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void addDocimasyInfo(DocimasyInfo docimasyInfo) {
        docimasyInfo.setId(UUID.randomUUID().toString());
        if("1".equals(docimasyInfo.getType())){
            EquipmentInfo equipmentInfosById = equipmentInfoMapper.findEquipmentInfosById(docimasyInfo.getTypeId());
            docimasyInfo.setTypeCode(equipmentInfosById.getEptCode());
        }else{
            FrockInfo frockInfo = frockInfoMapper.findFrockInfosById(docimasyInfo.getTypeId());
            docimasyInfo.setTypeCode(frockInfo.getFroCode());
        }


        docimasyInfo.setDcsTime(new Date());
        Calendar curr = new GregorianCalendar();
        curr.set(Calendar.DAY_OF_MONTH,curr.get(Calendar.DAY_OF_MONTH)+7);
        Date date=curr.getTime();
        docimasyInfo.setDcsNextTime(date);
        this.save(docimasyInfo);
    }

    /**
     * 编辑
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:40 PM
     */
    @Transactional(rollbackFor = Exception.class)
    public void editDocimasyInfo(DocimasyInfo DocimasyInfo) {
        DocimasyInfo old = this.getById(DocimasyInfo.getId());
        BeanUtil.copyProperties(DocimasyInfo, old);
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
    public Page<Map<String, Object>> selectDocimasyInfos(String condition,String type) {
        Page page = LayuiPageFactory.defaultPage();
        return this.baseMapper.selectDocimasyInfos(page, condition,type);
    }

    /**
     * 删除某
     *
     * @param id
     * @return
     * @date 2017年2月13日 下午7:57:51
     */
    @Transactional
    public int deleteDocimasyInfosById(String id) {
        return this.baseMapper.deleteDocimasyInfosById(id);
    }
    public DocimasyInfo  findDocimasyInfoByTypeId(String typeId){
        DocimasyInfo docimasyInfosByIds = docimasyInfoMapper.findDocimasyInfosByIds(typeId);
        return  docimasyInfosByIds;
    }

}

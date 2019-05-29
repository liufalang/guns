package cn.stylefeng.guns.modular.system.mapper;
import cn.stylefeng.guns.modular.system.entity.DocimasyInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-07
 */
public interface DocimasyInfoMapper extends BaseMapper<DocimasyInfo> {

    /**
     * 根据条件查询列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    Page<Map<String, Object>> selectDocimasyInfos(@Param("page") Page page, @Param("condition") String condition, @Param("type") String type);

    /**
     * 删除某个
     *
     * @param id
     * @return
     * @date 2017年2月13日 下午7:57:51
     */
    int deleteDocimasyInfosById(@Param("id") String id);


    /**
     * 查询某个
     *
     * @param id
     * @return
     * @date 2017年2月13日 下午7:57:51
     */
    DocimasyInfo findDocimasyInfosById(@Param("id") String id);



    /**
     *
     *typeID
     * @param   typeID
     * @return
     * @date 2017年2月13日 下午7:57:51
     */
    DocimasyInfo findDocimasyInfosByIds(@Param("typeID") String typeID  );
}

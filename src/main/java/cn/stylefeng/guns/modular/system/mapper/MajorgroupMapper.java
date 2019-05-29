package cn.stylefeng.guns.modular.system.mapper;

import cn.stylefeng.guns.modular.system.entity.Majorgroup;
import cn.stylefeng.guns.modular.system.entity.User;
import cn.stylefeng.roses.core.datascope.DataScope;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2018-12-07
 */
public interface MajorgroupMapper extends BaseMapper<Majorgroup> {

    /**
     * 修改专业组，名称及状态
     */
    int setStatus(@Param("id") Long userId, @Param("status") String status,@Param("name") String name);

    /**
     * 根据条件查询专业组列表列表
     */
    Page<Map<String, Object>> selectGroup(@Param("page") Page page,
            @Param("status") String status, @Param("name") String name, @Param("deptId") String deptId);

    //增加专业组
//    int addMajorgroup(@Param("name") String name,@Param("deptId") String deptId,@Param("status") String status);

    int delGroupById(@Param("id") String id);


}

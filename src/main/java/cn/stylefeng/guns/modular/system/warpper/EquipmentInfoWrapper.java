package cn.stylefeng.guns.modular.system.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

public class EquipmentInfoWrapper extends BaseControllerWrapper {
    public EquipmentInfoWrapper(Map<String, Object> single) {
        super(single);
    }

    public EquipmentInfoWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public EquipmentInfoWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public EquipmentInfoWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {

    }
}

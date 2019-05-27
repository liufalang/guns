package cn.stylefeng.guns.modular.system.warpper;

import cn.stylefeng.roses.core.base.warpper.BaseControllerWrapper;
import cn.stylefeng.roses.kernel.model.page.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

public class FrockInfoWrapper extends BaseControllerWrapper {
    public FrockInfoWrapper(Map<String, Object> single) {
        super(single);
    }

    public FrockInfoWrapper(List<Map<String, Object>> multi) {
        super(multi);
    }

    public FrockInfoWrapper(Page<Map<String, Object>> page) {
        super(page);
    }

    public FrockInfoWrapper(PageResult<Map<String, Object>> pageResult) {
        super(pageResult);
    }

    @Override
    protected void wrapTheMap(Map<String, Object> map) {

    }
}

package wangzhongqiu.financehelper.samples.pipeline.eastmoney;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import wangzhongqiu.financehelper.samples.dao.eastmoney.IndustryDao;
import wangzhongqiu.financehelper.samples.model.eastmoney.Industry;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangzhongqiu on 2017/9/12.
 */
@Component("IndustryPipeline")
public class IndustryPipeline implements Pipeline {
    @Resource
    private IndustryDao industryDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<Industry> industrys = resultItems.get("industries");
        if (industrys != null && industrys.size() > 0) {
            for (Industry industry : industrys) {
                industryDao.add(industry);
            }
        }
    }
}

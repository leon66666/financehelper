package wangzhongqiu.financehelper.samples.pipeline;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;
import wangzhongqiu.financehelper.samples.dao.eastmoney.IndustryDao;
import wangzhongqiu.financehelper.samples.model.eastmoney.Industry;

import javax.annotation.Resource;

/**
 * Created by wangzhongqiu on 2017/9/11.
 */
@Component("HYInfoDaoPipeline")
public class HYInfoDaoPipeline implements PageModelPipeline<Industry> {
    @Resource
    private IndustryDao industryDao;

    @Override
    public void process(Industry industry, Task task) {
        industryDao.add(industry);
    }
}

package wangzhongqiu.financehelper.samples.pipeline.eastmoney;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import wangzhongqiu.financehelper.samples.dao.eastmoney.IndustryInfoDao;
import wangzhongqiu.financehelper.samples.model.eastmoney.IndustryInfo;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangzhongqiu on 2017/9/13.
 */
@Component("IndustryInfoPipeline")
public class IndustryInfoPipeline implements Pipeline {
    @Resource
    private IndustryInfoDao industryInfoDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<IndustryInfo> industryInfos = resultItems.get("industryInfos");
        if (industryInfos != null && industryInfos.size() > 0) {
            for (IndustryInfo industryInfo : industryInfos) {
                industryInfoDao.add(industryInfo);
            }
        }
    }
}

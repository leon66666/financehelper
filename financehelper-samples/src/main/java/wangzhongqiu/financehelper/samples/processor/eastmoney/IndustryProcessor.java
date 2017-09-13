package wangzhongqiu.financehelper.samples.processor.eastmoney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.processor.PageProcessor;
import wangzhongqiu.financehelper.samples.model.eastmoney.Industry;
import wangzhongqiu.financehelper.samples.pipeline.eastmoney.IndustryPipeline;
import zhongqiu.javautils.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangzhongqiu on 2017/9/12.
 */
@Component
public class IndustryProcessor implements PageProcessor {
    @Qualifier("IndustryPipeline")
    @Autowired
    private IndustryPipeline industryPipeline;

    private static String AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36";
    private static String URL = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?cmd=C._BKHY&type=ct&sr=-1&ps=50&token=894050c76af8597a853f5b408b759f5d&sty=DCFFITABK&rt=50173371";
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setUserAgent(AGENT);

    @Override
    public Site getSite() {
        return site;
    }

    public void crawl() {
        Spider.create(new IndustryProcessor()).addUrl(URL).addPipeline(industryPipeline).thread(5).run();
    }

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        String temp = page.getHtml().toString();
        int beginIndex = temp.lastIndexOf('[');
        int endIndex = temp.lastIndexOf(']');
        temp = temp.substring(beginIndex, endIndex + 1);
        List<Object> items = JsonUtil.jsonToList(temp, Object.class);
        List<Industry> industries = new ArrayList<Industry>();
        for (Object object : items) {
            String[] arr = object.toString().split(",");
            industries.add(new Industry(arr[2]));
        }
        page.putField("industries", industries);
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext*.xml");
        final IndustryProcessor industryProcessor = applicationContext.getBean(IndustryProcessor.class);
        industryProcessor.crawl();
    }
}

package wangzhongqiu.financehelper.samples.processor.eastmoney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import wangzhongqiu.financehelper.samples.model.EastMoneyHYInfoSample;
import wangzhongqiu.financehelper.samples.model.eastmoney.IndustryInfo;
import wangzhongqiu.financehelper.samples.pipeline.eastmoney.IndustryInfoPipeline;
import wangzhongqiu.financehelper.samples.processor.HYInfoProcessorSample;

/**
 * Created by wangzhongqiu on 2017/9/13.
 */
public class IndustryInfoProcessor {
    @Qualifier("IndustryInfoPipeline")
    @Autowired
    private IndustryInfoPipeline industryInfoPipeline;

    public void crawl() {
        String agent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36";
        String url = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?cmd=C._BKHY&type=ct&st=(BalFlowMain)&sr=-1&p=1&ps=50&js=var%20BscAxcAq={pages:(pc),data:[(x)]}&token=894050c76af8597a853f5b408b759f5d&sty=DCFFITABK&rt=50173371";
        OOSpider.create(Site.me().setCharset("gb2312").setUserAgent(agent), industryInfoPipeline, IndustryInfo.class).addUrl(url).thread(5).run();
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext*.xml");
        final IndustryInfoProcessor industryInfoProcessor = applicationContext.getBean(IndustryInfoProcessor.class);
        industryInfoProcessor.crawl();
    }
}

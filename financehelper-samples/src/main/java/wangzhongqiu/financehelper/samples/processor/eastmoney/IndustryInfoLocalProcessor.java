package wangzhongqiu.financehelper.samples.processor.eastmoney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import wangzhongqiu.financehelper.samples.model.eastmoney.IndustryInfo;
import wangzhongqiu.financehelper.samples.pipeline.eastmoney.IndustryInfoPipeline;
import zhongqiu.javautils.DateUtil;
import zhongqiu.javautils.JsonUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangzhongqiu on 2017/9/13.
 */
public class IndustryInfoLocalProcessor implements PageProcessor {
    private static String AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36";
    private static String URL = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?cmd=C._BKHY&type=ct&sr=-1&ps=50&token=894050c76af8597a853f5b408b759f5d&sty=DCFFITABK&rt=50173371";
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setUserAgent(AGENT);

    @Override
    public Site getSite() {
        return site;
    }

    @Qualifier("IndustryInfoPipeline")
    @Autowired
    private IndustryInfoPipeline industryInfoPipeline;

    public void crawl() {
        Spider.create(new IndustryInfoProcessor()).addUrl(URL).addPipeline(industryInfoPipeline).thread(5).run();
    }

    @Override
    public void process(Page page) {
        String now = DateUtil.dateToString(new Date(), DateUtil.DATE_FORMAT_DAY_SHORT);
        String temp = page.getHtml().toString();
        int beginIndex = temp.lastIndexOf('[');
        int endIndex = temp.lastIndexOf(']');
        temp = temp.substring(beginIndex, endIndex + 1);
        List<Object> items = JsonUtil.jsonToList(temp, Object.class);
        List<IndustryInfo> industryInfos = new ArrayList<IndustryInfo>();
        for (Object object : items) {
            String[] arr = object.toString().split(",");
            IndustryInfo industryInfo = new IndustryInfo();
            //// TODO: 2017/9/13 setIndustryId
            industryInfo.setIndustryName(arr[2]);
            industryInfo.setRise(new BigDecimal(arr[3]));
            industryInfo.setMain(Integer.parseInt(arr[4].substring(0, arr[4].lastIndexOf("."))));
            industryInfo.setSuper_(Integer.parseInt(arr[6].substring(0, arr[6].lastIndexOf("."))));
            industryInfo.setBig(Integer.parseInt(arr[8].substring(0, arr[8].lastIndexOf("."))));
            industryInfo.setMedium(Integer.parseInt(arr[10].substring(0, arr[10].lastIndexOf("."))));
            industryInfo.setSmall(Integer.parseInt(arr[12].substring(0, arr[12].lastIndexOf("."))));
            industryInfo.setTotal(industryInfo.getMain() + industryInfo.getSuper_() + industryInfo.getBig() + industryInfo.getMedium() + industryInfo.getSmall());
            industryInfo.setDate(Integer.parseInt(now));
            industryInfos.add(industryInfo);
        }
        page.putField("industryInfos", industryInfos);
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext*.xml");
        final IndustryInfoLocalProcessor industryInfoLocalProcessor = applicationContext.getBean(IndustryInfoLocalProcessor.class);
        industryInfoLocalProcessor.crawl();
    }
}

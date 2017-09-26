package wangzhongqiu.financehelper.samples.processor.eastmoney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
import wangzhongqiu.financehelper.samples.model.eastmoney.IndustryInfo;
import wangzhongqiu.financehelper.samples.pipeline.eastmoney.IndustryInfoPipeline;
import zhongqiu.javautils.DateUtil;
import zhongqiu.javautils.JsonUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangzhongqiu on 2017/9/25.
 */
@Component
public class IndustryInfoHistoryProcessor implements PageProcessor {
    private static String AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36";
    private static String URL = "http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?cmd=C._BKHY&type=ct&sr=-1&ps=50&token=894050c76af8597a853f5b408b759f5d&sty=DCFFITABK&rt=50173371";
    private Site site = Site.me().setRetryTimes(1).setSleepTime(3000).setUserAgent(AGENT).setCharset("gb2312").setTimeOut(1000 * 30);

    @Override
    public Site getSite() {
        return site;
    }

    @Qualifier("IndustryInfoPipeline")
    @Autowired
    private IndustryInfoPipeline industryInfoPipeline;

    public void crawl() {
        Spider.create(new IndustryInfoHistoryProcessor()).addUrl(URL).addPipeline(industryInfoPipeline).thread(1).run();
    }

    @Override
    public void process(Page page) {
        if (page.getUrl().toString().equals(URL)) {
            String now = DateUtil.dateToString(new Date(), DateUtil.DATE_FORMAT_DAY_SHORT);
            String temp = page.getHtml().toString();
            int beginIndex = temp.lastIndexOf('[');
            int endIndex = temp.lastIndexOf(']');
            temp = temp.substring(beginIndex, endIndex + 1);
            List<String> targetUrls = new ArrayList<String>();
            List<Object> items = JsonUtil.jsonToList(temp, Object.class);
            List<IndustryInfo> industryInfos = new ArrayList<IndustryInfo>();
            for (Object object : items) {
                String[] arr = object.toString().split(",");
                IndustryInfo industryInfo = new IndustryInfo();
                targetUrls.add("http://data.eastmoney.com/bkzj/" + arr[1] + ".html");
            }
            page.addTargetRequests(targetUrls);
        } else {
            String url = page.getUrl().toString();
            url = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
            List<String> dateList = page.getHtml().xpath("//table[@id='tb_lishi']/tbody/tr/td/text()").all();
            List<String> contentList = page.getHtml().xpath("//table[@id='tb_lishi']/tbody/tr/td/span/text()").all();
            List<IndustryInfo> industryInfos = new ArrayList<IndustryInfo>();
            int contengListIndex = 0;
            for (int i = 0; i < dateList.size(); i = i + 11) {
                IndustryInfo industryInfo = new IndustryInfo();
                industryInfo.setDate(Integer.parseInt(dateList.get(i).replace("-", "").trim()));
                industryInfo.setIndustryCode(url);
                industryInfo.setMain(parse(contentList.get(contengListIndex)));
                contengListIndex = contengListIndex + 2;
                industryInfo.setSuper_(parse(contentList.get(contengListIndex)));
                contengListIndex = contengListIndex + 2;
                industryInfo.setBig(parse(contentList.get(contengListIndex)));
                contengListIndex = contengListIndex + 2;
                industryInfo.setMedium(parse(contentList.get(contengListIndex)));
                contengListIndex = contengListIndex + 2;
                industryInfo.setSmall(parse(contentList.get(contengListIndex)));
                contengListIndex = contengListIndex + 2;
                industryInfo.setTotal(industryInfo.getMain() + industryInfo.getSuper_() + industryInfo.getBig() + industryInfo.getMedium() + industryInfo.getSmall());
                industryInfos.add(industryInfo);

            }
            page.putField("industryInfos", industryInfos);
            System.out.println(url + "完成");
        }

    }

    public static int parse(String str) {
        String head = str.substring(0, str.length() - 1);
        String tail = str.substring(str.length() - 1, str.length());
        Double result;
        if ("万".equals(tail)) {
            result = Double.parseDouble(head);
            return result.intValue();
        }
        if ("亿".equals(tail)) {
            result = Double.parseDouble(head) * 10000;
            return result.intValue();
        }
        return Integer.parseInt(head);
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext*.xml");
        final IndustryInfoHistoryProcessor industryInfoHistoryProcessor = applicationContext.getBean(IndustryInfoHistoryProcessor.class);
        industryInfoHistoryProcessor.crawl();
    }
}

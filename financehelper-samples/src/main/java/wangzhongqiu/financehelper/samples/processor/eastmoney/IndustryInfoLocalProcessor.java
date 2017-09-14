package wangzhongqiu.financehelper.samples.processor.eastmoney;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import wangzhongqiu.financehelper.samples.dao.eastmoney.IndustryInfoDao;
import wangzhongqiu.financehelper.samples.model.eastmoney.IndustryInfo;
import zhongqiu.javautils.DateUtil;
import zhongqiu.javautils.FileUtil;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.TreeMap;

/**
 * Created by wangzhongqiu on 2017/9/13.
 */
@Component
public class IndustryInfoLocalProcessor {
    @Resource
    private IndustryInfoDao industryInfoDao;

    public void crawl() {
        String file = "E:\\work\\githubcode\\financehelper\\financehelper-samples\\src\\main\\resources\\新建文本文档.txt";
        String charsetName = "GB2312";
        TreeMap<Integer, String> results = FileUtil.readFileByLines(file, charsetName);
        for (int i = 1; i <= results.size(); i = i + 3) {
            String date = results.get(i);
            date = date.replace("【 ", "").replace(" 】", "");
            date = DateUtil.dateToString(new Date(date), DateUtil.DATE_FORMAT_DAY_SHORT);

            /*String temp2 = results.get(i + 1);
            String[] arrTemp2 = temp2.split(",");
            for (String str : arrTemp2) {
                IndustryInfo industryInfo = new IndustryInfo();
                industryInfo.setDate(Integer.parseInt(date));
                industryInfo.setIndustryName(str.substring(str.lastIndexOf("【") + 1, str.lastIndexOf("】")));
                industryInfo.setRise(new BigDecimal(str.substring(str.lastIndexOf("】") + 1, str.length())));
                industryInfoDao.add(industryInfo);
            }
            String temp3 = results.get(i + 2);
            String[] arrTemp3 = temp3.split(",");
            for (String str : arrTemp3) {
                IndustryInfo industryInfo = new IndustryInfo();
                industryInfo.setDate(Integer.parseInt(date));
                industryInfo.setIndustryName(str.substring(str.lastIndexOf("【") + 1, str.lastIndexOf("】")));
                Double d = Double.parseDouble(str.substring(str.lastIndexOf("】") + 1, str.length())) * 10000;
                industryInfo.setTotal(d.intValue());
                industryInfoDao.add(industryInfo);
            }*/
            System.out.println(date);
        }
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext*.xml");
        final IndustryInfoLocalProcessor industryInfoLocalProcessor = applicationContext.getBean(IndustryInfoLocalProcessor.class);
        industryInfoLocalProcessor.crawl();
    }
}

package wangzhongqiu.financehelper.samples.model;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import wangzhongqiu.financehelper.samples.dao.eastmoney.IndustryDao;
import zhongqiu.javautils.JsonUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangzhongqiu on 2017/9/12.
 * 有的时候，注解模式无法满足所有需求，我们可能还需要写代码完成一些事情，这个时候就要用到AfterExtractor接口了。
 */
public class IndustrySample implements AfterExtractor {
    @Resource
    private IndustryDao industryDao;
    private String name;
    private String platform = "data.eastmoney.com";
    @ExtractByUrl
    private String url = "";

    public IndustrySample() {

    }

    public IndustrySample(String name) {
        this.name = name;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public void afterProcess(Page page) {
        String temp = page.getHtml().toString();
        int beginIndex = temp.lastIndexOf('[');
        int endIndex = temp.lastIndexOf(']');
        temp = temp.substring(beginIndex, endIndex + 1);
        List<Object> items = JsonUtil.jsonToList(temp, Object.class);
        for (Object object : items) {
            String[] arr = object.toString().split(",");
//            industryDao.add(new IndustrySample(arr[2]));
        }
        //从页面发现后续的url地址来抓取
        /*List<String> targetRequests = new ArrayList<String>();
        targetRequests.add("http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?cmd=C._BKHY&type=ct&st=(BalFlowMain)&sr=-1&p=2&ps=50&js=var%20BscAxcAq={pages:(pc),data:[(x)]}&token=894050c76af8597a853f5b408b759f5d&sty=DCFFITABK&rt=50173371");
        page.addTargetRequests(targetRequests);*/
    }
}

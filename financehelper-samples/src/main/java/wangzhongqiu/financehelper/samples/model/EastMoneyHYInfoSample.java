package wangzhongqiu.financehelper.samples.model;

import org.apache.commons.codec.digest.DigestUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * Created by wangzhongqiu on 2017/9/11.
 * 有的时候，注解模式无法满足所有需求，我们可能还需要写代码完成一些事情，这个时候就要用到AfterExtractor接口了。
 */
@TargetUrl("http://data.eastmoney.com/bkzj/hy.html")
//@HelpUrl("https://www.liepin.com/sojob/?dqs=020&curPage=\\d+")
public class EastMoneyHYInfoSample implements AfterExtractor {
    @ExtractBy("//table[@id='dt_1']/tbody/tr[1]/td[2]/a/text()")
//    @ExtractBy("/html/head/title[1]/text()")
    private String name = "";
    private String platform = "data.eastmoney.com";
    @ExtractByUrl
    private String url = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    }
}

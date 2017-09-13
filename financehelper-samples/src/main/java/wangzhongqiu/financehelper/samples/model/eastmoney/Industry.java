package wangzhongqiu.financehelper.samples.model.eastmoney;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.ExtractByUrl;
import wangzhongqiu.financehelper.samples.dao.eastmoney.IndustryDao;
import zhongqiu.javautils.JsonUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangzhongqiu on 2017/9/12.
 * 有的时候，注解模式无法满足所有需求，我们可能还需要写代码完成一些事情，这个时候就要用到AfterExtractor接口了。
 */
public class Industry {
    private String name;
    private String platform = "data.eastmoney.com";
    @ExtractByUrl
    private String url = "";

    public Industry() {

    }

    public Industry(String name) {
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
}

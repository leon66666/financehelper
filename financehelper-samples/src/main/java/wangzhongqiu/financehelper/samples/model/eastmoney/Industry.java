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
 */
public class Industry {
    private String name;
    private String platform = "data.eastmoney.com";
    private String url = "";
    private String code;

    public Industry() {

    }

    public Industry(String name, String code) {
        this.name = name;
        this.code = code;
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

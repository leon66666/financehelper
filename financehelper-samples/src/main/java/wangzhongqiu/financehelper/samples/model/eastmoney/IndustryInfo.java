package wangzhongqiu.financehelper.samples.model.eastmoney;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.model.AfterExtractor;
import zhongqiu.javautils.DateUtil;
import zhongqiu.javautils.JsonUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangzhongqiu on 2017/9/13.
 * 有的时候，注解模式无法满足所有需求，我们可能还需要写代码完成一些事情，这个时候就要用到AfterExtractor接口了。
 */
public class IndustryInfo implements AfterExtractor {
    private String platform = "data.eastmoney.com";
    private String industryName;
    private int industryId;
    private BigDecimal rise;
    private int main;
    private int super_;
    private int big;
    private int medium;
    private int small;
    private int total;
    private int date;

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public int getIndustryId() {
        return industryId;
    }

    public void setIndustryId(int industryId) {
        this.industryId = industryId;
    }

    public BigDecimal getRise() {
        return rise;
    }

    public void setRise(BigDecimal rise) {
        this.rise = rise;
    }

    public int getMain() {
        return main;
    }

    public void setMain(int main) {
        this.main = main;
    }

    public int getSuper_() {
        return super_;
    }

    public void setSuper_(int super_) {
        this.super_ = super_;
    }

    public int getBig() {
        return big;
    }

    public void setBig(int big) {
        this.big = big;
    }

    public int getMedium() {
        return medium;
    }

    public void setMedium(int medium) {
        this.medium = medium;
    }

    public int getSmall() {
        return small;
    }

    public void setSmall(int small) {
        this.small = small;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    @Override
    public void afterProcess(Page page) {
        String now = DateUtil.dateToString(new Date(), DateUtil.DATE_PATTERN_DAY);
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
            industryInfo.setMain(Integer.parseInt(arr[4]));
            industryInfo.setSuper_(Integer.parseInt(arr[6]));
            industryInfo.setBig(Integer.parseInt(arr[8]));
            industryInfo.setMedium(Integer.parseInt(arr[10]));
            industryInfo.setSmall(Integer.parseInt(arr[12]));
            industryInfo.setTotal(Integer.parseInt(arr[4]) + Integer.parseInt(arr[4]) + Integer.parseInt(arr[6]) + Integer.parseInt(arr[8]) + Integer.parseInt(arr[12]));
            industryInfo.setDate(Integer.parseInt(now));
            industryInfos.add(industryInfo);
        }
        page.putField("industryInfos", industryInfos);
    }
}

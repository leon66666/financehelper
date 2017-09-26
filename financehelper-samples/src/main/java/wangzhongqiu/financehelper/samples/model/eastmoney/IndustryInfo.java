package wangzhongqiu.financehelper.samples.model.eastmoney;

import java.math.BigDecimal;

/**
 * Created by wangzhongqiu on 2017/9/13.
 * 有的时候，注解模式无法满足所有需求，我们可能还需要写代码完成一些事情，这个时候就要用到AfterExtractor接口了。
 */
public class IndustryInfo {
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
    private String industryCode;

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

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }
}

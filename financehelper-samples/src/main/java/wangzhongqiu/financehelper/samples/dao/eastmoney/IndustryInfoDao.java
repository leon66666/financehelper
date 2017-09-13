package wangzhongqiu.financehelper.samples.dao.eastmoney;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;
import wangzhongqiu.financehelper.samples.model.eastmoney.IndustryInfo;

/**
 * Created by wangzhongqiu on 2017/9/13.
 */
@Repository
public interface IndustryInfoDao {
    @Insert("insert into industry_info (`name`,`platform`) values (#{name},#{platform})")
    public int add(IndustryInfo industryInfo);
}

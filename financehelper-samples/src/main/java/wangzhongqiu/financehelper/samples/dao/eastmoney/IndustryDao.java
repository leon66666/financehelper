package wangzhongqiu.financehelper.samples.dao.eastmoney;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;
import wangzhongqiu.financehelper.samples.model.eastmoney.Industry;

/**
 * Created by wangzhongqiu on 2017/9/12.
 */
@Repository
public interface IndustryDao {
    @Insert("insert into industry (`name`,`platform`) values (#{name},#{platform})")
    public int add(Industry industry);
}

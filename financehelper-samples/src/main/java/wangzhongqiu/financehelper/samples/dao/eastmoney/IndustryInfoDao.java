package wangzhongqiu.financehelper.samples.dao.eastmoney;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import wangzhongqiu.financehelper.samples.model.eastmoney.IndustryInfo;

import java.util.List;

/**
 * Created by wangzhongqiu on 2017/9/13.
 */
@Repository
public interface IndustryInfoDao {
    @Insert("insert into industry_info (`industry_name`,`rise`,`main`,`super`,`big`,`medium`,`small`,`total`,`date`,`industry_code`) values (#{industryName},#{rise},#{main},#{super_},#{big},#{medium},#{small},#{total},#{date},#{industryCode})")
    public int add(IndustryInfo industryInfo);

    @Select("select * from industry_info where industry_name in(${industrys})")
    public List<IndustryInfo> getByIndustrys(List<String> industrys);

    @Select("select * from industry_info where industry_name=#{industry} order by date asc")
    public List<IndustryInfo> get(String industry);
}

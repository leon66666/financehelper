package wangzhongqiu.financehelper.samples.dao.eastmoney;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import wangzhongqiu.financehelper.samples.model.Content;

import java.util.List;

/**
 * Created by wangzhongqiu on 2017/10/16.
 */
@Repository
public interface ContentDao {
    @Insert("insert into content (`user_id`,`content`,`addtime`,`like_num`) values (#{userId},#{content},#{addtime},#{likeNum})")
    public int add(Content content);

    @Select("select * from content where user_id in(${userIds})")
    public List<Content> getByIndustrys(List<Integer> userIds);

    @Select("select * from content where user_id=#{userId} order by addtime asc")
    public List<Content> get(Integer userId);

    @Update("update content set like_num=#{likeNum} where id=#{id}")
    public void update(Content content);
}

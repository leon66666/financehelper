package wangzhongqiu.financehelper.samples.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wangzhongqiu.financehelper.samples.dao.eastmoney.ContentDao;
import wangzhongqiu.financehelper.samples.model.Content;


import javax.annotation.Resource;

/**
 * Created by wangzhongqiu on 2017/10/16.
 */
@Service
//@Transactional
public class ContentService {
    @Resource
    private ContentDao contentDao;

    public void add(Content content) {
        contentDao.add(content);
        throw new RuntimeException("发生异常");
    }
}

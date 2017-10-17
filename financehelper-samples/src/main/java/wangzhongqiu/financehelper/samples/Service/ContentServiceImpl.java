package wangzhongqiu.financehelper.samples.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wangzhongqiu.financehelper.samples.dao.eastmoney.ContentDao;
import wangzhongqiu.financehelper.samples.model.Content;


import javax.annotation.Resource;

/**
 * Created by wangzhongqiu on 2017/10/16.
 */
@Service
@Transactional
public class ContentServiceImpl implements ContentService {
    @Resource
    private ContentDao contentDao;

    @Override
    public void add(Content content) {
        contentDao.add(content);
        throw new RuntimeException("发生异常");
    }
}

package wangzhongqiu.financehelper.samples.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import wangzhongqiu.financehelper.samples.Service.ContentService;

/**
 * Created by wangzhongqiu on 2017/10/16.
 * 数据库事务注解  @Transactional，测试
 */
public class TransactionTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext*.xml");
        final ContentService contentService = applicationContext.getBean(ContentService.class);

    }
}

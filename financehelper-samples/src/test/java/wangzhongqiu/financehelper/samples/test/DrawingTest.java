package wangzhongqiu.financehelper.samples.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import wangzhongqiu.financehelper.samples.drawing.BarChart;
import wangzhongqiu.financehelper.samples.drawing.PieChart;
import wangzhongqiu.financehelper.samples.drawing.TimeSeriesChart;
import wangzhongqiu.financehelper.samples.drawing.eastmoney.IndustryRiseChart;
import wangzhongqiu.financehelper.samples.drawing.eastmoney.IndustryTimeSeriesChart;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by wangzhongqiu on 2017/9/19.
 */
public class DrawingTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext*.xml");
        final IndustryTimeSeriesChart industryTimeSeriesChart = applicationContext.getBean(IndustryTimeSeriesChart.class);
        final IndustryRiseChart industryRiseChart = applicationContext.getBean(IndustryRiseChart.class);
        JFrame frame = new JFrame("数据统计图");
        frame.setLayout(new GridLayout(2, 2, 10, 10));
        List<String> industrys = new ArrayList<String>();
        industrys.add("房地产");
        industrys.add("酿酒行业");
        industrys.add("有色金属");
        industrys.add("软件服务");
        industrys.add("航天航空");
        industrys.add("保险");
        frame.add(industryTimeSeriesChart.getChartPanel(industrys));
        frame.add(industryRiseChart.getChartPanel(industrys));
        frame.setBounds(50, 50, 800, 600);
        frame.setVisible(true);
    }
}

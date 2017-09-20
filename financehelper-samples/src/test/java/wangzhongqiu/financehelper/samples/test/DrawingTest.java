package wangzhongqiu.financehelper.samples.test;

import org.junit.Test;
import org.springframework.stereotype.*;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import wangzhongqiu.financehelper.samples.drawing.BarChart;
import wangzhongqiu.financehelper.samples.drawing.PieChart;
import wangzhongqiu.financehelper.samples.drawing.TimeSeriesChart;
import wangzhongqiu.financehelper.samples.drawing.eastmoney.IndustryTimeSeriesChart;

import javax.annotation.Resource;
import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by wangzhongqiu on 2017/9/19.
 */
@ContextConfiguration(locations = "classpath:/spring/applicationContextTest.xml")
public class DrawingTest {
    @Resource
    private IndustryTimeSeriesChart industryTimeSeriesChart;

    @Test
    public void testRun() {
        JFrame frame = new JFrame("数据统计图");
        frame.setLayout(new GridLayout(2, 2, 10, 10));
//        frame.add(new BarChart().getChartPanel());           //添加柱形图
//        frame.add(new PieChart().getChartPanel());           //添加饼状图
//        frame.add(new TimeSeriesChart().getChartPanel());    //添加折线图
        java.util.List<String> industrys = new ArrayList<String>();
        industrys.add("房地产");
        frame.add(industryTimeSeriesChart.getChartPanel(industrys));
        frame.setBounds(50, 50, 800, 600);
        frame.setVisible(true);
    }
}

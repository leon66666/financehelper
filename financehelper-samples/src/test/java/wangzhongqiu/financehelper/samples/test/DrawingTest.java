package wangzhongqiu.financehelper.samples.test;

import wangzhongqiu.financehelper.samples.drawing.BarChart;
import wangzhongqiu.financehelper.samples.drawing.PieChart;
import wangzhongqiu.financehelper.samples.drawing.TimeSeriesChart;

import javax.swing.*;
import java.awt.*;

/**
 * Created by wangzhongqiu on 2017/9/19.
 */
public class DrawingTest {
    public static void main(String args[]) {
        JFrame frame = new JFrame("数据统计图");
        frame.setLayout(new GridLayout(2, 2, 10, 10));
//        frame.add(new BarChart().getChartPanel());           //添加柱形图
//        frame.add(new PieChart().getChartPanel());           //添加饼状图
        frame.add(new TimeSeriesChart().getChartPanel());    //添加折线图
        frame.setBounds(50, 50, 800, 600);
        frame.setVisible(true);
    }
}

package wangzhongqiu.financehelper.samples.drawing.eastmoney;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.springframework.stereotype.Service;
import wangzhongqiu.financehelper.samples.dao.eastmoney.IndustryInfoDao;
import wangzhongqiu.financehelper.samples.model.eastmoney.IndustryInfo;
import zhongqiu.javautils.DateUtil;

import javax.annotation.Resource;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by wangzhongqiu on 2017/9/21.
 */
@Service
public class IndustryRiseChart {
    @Resource
    private IndustryInfoDao industryInfoDao;

    private XYDataset createDataset(List<String> industrys) {
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
        if (industrys != null) {
            for (String industry : industrys) {
                TimeSeries timeseries = new TimeSeries(industry + "涨跌统计",
                        org.jfree.data.time.Day.class);
                List<IndustryInfo> industryInfos = industryInfoDao.get(industry);
                if (industryInfos != null) {
                    for (IndustryInfo industryInfo : industryInfos) {
                        timeseries.add(new Day(DateUtil.parse(String.valueOf(industryInfo.getDate()), DateUtil.DATE_FORMAT_DAY_SHORT)), industryInfo.getRise());
                    }
                }
                timeseriescollection.addSeries(timeseries);
            }
        }
        return timeseriescollection;
    }

    public ChartPanel getChartPanel(List<String> industrys) {
        XYDataset xydataset = createDataset(industrys);
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("行业涨跌", "日期(日/单位)", "价格(%/单位)", xydataset, true, true, true);
        XYPlot xyplot = (XYPlot) jfreechart.getPlot();
        DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));
        dateaxis.setLabelFont(new Font("黑体", Font.BOLD, 14));         //水平底部标题
        dateaxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12));  //垂直标题
        ValueAxis rangeAxis = xyplot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
        jfreechart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        jfreechart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));//设置标题字体
        ChartPanel frame1 = new ChartPanel(jfreechart, true);
        return frame1;
    }
}

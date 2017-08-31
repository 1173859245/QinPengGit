package com.qp.inst_life.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.qp.inst_life.R;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class Fragment3 extends Fragment {

    @BindView(R.id.curru_month)
    TextView curruMonth;
    @BindView(R.id.month_details)
    TextView monthDetails;
    @BindView(R.id.linearChart)
    LineChart linearChart;
    Unbinder unbinder;
    @BindView(R.id.mypiechart)
    PieChart mypiechart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);
        unbinder = ButterKnife.bind(this, view);
        insertCurryMoth();
        initLinearChart();
        initPieChart();


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void insertCurryMoth() {
        String month = null;
        Calendar c = Calendar.getInstance();//
        int mYear = c.get(Calendar.YEAR); // 获取当前年份
        int mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        if (mMonth < 10) {
            month = "0" + mMonth;
        }

        curruMonth.setText(month + "/" + mYear);
    }

    /**
     * 绘制折线图
     */
    private void initLinearChart() {
        XAxis xAxis = linearChart.getXAxis();
        //设置X轴的文字在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        linearChart.setDescription("月份统计");
        linearChart.getAxisRight().setEnabled(false); // 隐藏右边 的坐标轴
        linearChart.setDragEnabled(true);// 是否可以拖拽
        linearChart.setScaleEnabled(true);// 是否可以缩放
        //隐藏左边坐标轴横网格线
        linearChart.getAxisLeft().setDrawGridLines(false);
        //隐藏右边坐标轴横网格线
        linearChart.getAxisRight().setDrawGridLines(false);
        //隐藏X轴竖网格线
        linearChart.getXAxis().setDrawGridLines(false);

        ArrayList<String> xValues = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            xValues.add(i + "月");
        }
        //模拟一组y轴数据(存放y轴数据的是一个Entry的ArrayList) 他是构建LineDataSet的参数之一


        ArrayList<Entry> yValue1 = new ArrayList<>();

        yValue1.add(new Entry(0, 0));
        yValue1.add(new Entry(25297, 1));
        yValue1.add(new Entry(11338, 2));
        yValue1.add(new Entry(4284, 3));
        yValue1.add(new Entry(6957, 4));
        yValue1.add(new Entry(0, 5));
        yValue1.add(new Entry(0, 6));


        //构建一个LineDataSet 代表一组Y轴数据 （比如不同的彩票： 七星彩  双色球）

        LineDataSet dataSet1 = new LineDataSet(yValue1, "星座指数");
        dataSet1.setColor(Color.BLACK);
        dataSet1.setValueTextSize(12);
        dataSet1.setHighlightEnabled(true);
        dataSet1.setDrawCircleHole(false);


        //构建一个类型为LineDataSet的ArrayList 用来存放所有 y的LineDataSet   他是构建最终加入LineChart数据集所需要的参数
        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(dataSet1);
        //构建一个LineData  将dataSets放入
        LineData lineData = new LineData(xValues, dataSets);
        //将数据插入
        linearChart.setData(lineData);
    }

    private void initPieChart() {
        PieData mPieData = getPieData(4);
        showpieChart(mypiechart,mPieData);
    }

    /**
     * 饼状图
     * @param piechart
     * @param piedata
     */
    private void showpieChart(PieChart piechart,PieData piedata) {
        mypiechart.setHoleRadius(60f);  //半径
        mypiechart.setTransparentCircleRadius(64f); // 半透明圈
        //pieChart.setHoleRadius(0)  //实心圆

        mypiechart.setDescription("星座指数图");

        // mChart.setDrawYValues(true);
        mypiechart.setDrawCenterText(true);  //饼状图中间可以添加文字

        mypiechart.setDrawHoleEnabled(true);

        mypiechart.setRotationAngle(90); // 初始旋转角度

        // draws the corresponding description value into the slice
        // mChart.setDrawXValues(true);

        // enable rotation of the chart by touch
        mypiechart.setRotationEnabled(true); // 可以手动旋转

        // display percentage values
       // mypiechart.setUsePercentValues(true);  //显示成百分比
        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
//      mChart.setOnChartValueSelectedListener(this);
        // mChart.setTouchEnabled(false);

//      mChart.setOnAnimationListener(this);

        mypiechart.setCenterText("星座指数");  //饼状图中间的文字

        //设置数据
        mypiechart.setData(piedata);

        // undo all highlights
//      pieChart.highlightValues(null);
//      pieChart.invalidate();

        Legend mLegend = mypiechart.getLegend();  //设置比例图
        mLegend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
//      mLegend.setForm(LegendForm.LINE);  //设置比例图的形状，默认是方形
        mLegend.setXEntrySpace(7f);
        mLegend.setYEntrySpace(5f);

        piechart.animateXY(1000, 1000);  //设置动画
    }

    /**
     *
     * @param count 分成几部分
     * @param
     */
    private PieData getPieData(int count ) {
       ArrayList<String> xValues=new ArrayList<String>();//xVals用来表示每个饼块上的内容

        xValues.add("爱情指数");
        xValues.add("幸运指数");
        xValues.add("花心指数");
        xValues.add("坏人指数");
        ArrayList<Entry> yValues=new ArrayList<Entry>();
        yValues.add(new Entry(150,0));
        yValues.add(new Entry(190,1));
        yValues.add(new Entry(200,2));
        yValues.add(new Entry(50,3));
        //Y轴的集合
        PieDataSet pieDataSet = new PieDataSet(yValues, "星座指数"/*显示在比例图上*/);
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离
        ArrayList<Integer> colors = new ArrayList<Integer>();

        // 饼图颜色
        colors.add(Color.rgb(205, 205, 205));
        colors.add(Color.rgb(114, 188, 223));
        colors.add(Color.rgb(255, 123, 124));
        colors.add(Color.rgb(57, 135, 200));

        pieDataSet.setColors(colors);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px); // 选中态多出的长度
        PieData pieData = new PieData(xValues, pieDataSet);




        return pieData;
    }
}

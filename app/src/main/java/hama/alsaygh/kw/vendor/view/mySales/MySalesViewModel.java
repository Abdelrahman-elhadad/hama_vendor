package hama.alsaygh.kw.vendor.view.mySales;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.model.mySales.MyMarkerView;
import hama.alsaygh.kw.vendor.model.mySales.MySalesData;
import hama.alsaygh.kw.vendor.model.mySales.MySalesResponse;
import hama.alsaygh.kw.vendor.repo.ProfileRepo;
import hama.alsaygh.kw.vendor.utils.Utils;

public class MySalesViewModel extends ViewModel {

    private final String TAG = "MySalesViewModel";

    private final ProfileRepo authRepo;


    private MutableLiveData<MySalesResponse> loginResponseMutableLiveData;


    public MySalesViewModel() {
        authRepo = new ProfileRepo();
    }


    public MutableLiveData<MySalesResponse> getObservable() {
        if (loginResponseMutableLiveData == null)
            loginResponseMutableLiveData = new MutableLiveData<>();

        return loginResponseMutableLiveData;
    }


    public void login(Context context) {
        authRepo.getMySales(context, loginResponseMutableLiveData);

    }

    public void setLineChartSetting(Context context, LineChart chart) {

        chart.setDrawGridBackground(false);

        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisLeft().setDrawAxisLine(false);
        chart.getXAxis().setDrawGridLines(false);
        chart.getXAxis().setDrawLabels(true);
        chart.getXAxis().setDrawAxisLine(true);
        //Part5
        chart.getXAxis().setLabelRotationAngle(0f);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getXAxis().setGranularity(1f);
        chart.getXAxis().setDrawLabels(true);
        chart.getXAxis().setDrawAxisLine(false);


        //Part7
        chart.getAxisRight().setEnabled(false);
        chart.getXAxis().setAxisMaximum(13f);

//Part8
        chart.setTouchEnabled(true);
        chart.setPinchZoom(true);

//Part9
        Description des = new Description();
        des.setText("");
        chart.setDescription(des);
        chart.setNoDataText(context.getString(R.string.no_data));

//Part10
        chart.animateX(1800, Easing.EaseInExpo);
        LinearGradient gradient = new LinearGradient(
                0f, 600f, 0f, 0f,
                ContextCompat.getColor(context, R.color.chart_green),
                ContextCompat.getColor(
                        context, R.color.chart_green
                ),
                Shader.TileMode.CLAMP
        );
        Paint paint = chart.getRenderer().getPaintRender();
        paint.setShader(gradient);

        chart.setMarker(new MyMarkerView(context, R.layout.chart_linear_item, context.getString(R.string.currency)));


        // don't forget to refresh the drawing
        chart.invalidate();

    }

    public String getSalesAvg(Context context, MySalesData mySalesData) {
        String avg = context.getString(R.string.bvbvb);
        double avgPrice = 0;

        if (mySalesData.getStatistics() != null) {
            avgPrice = (mySalesData.getStatistics().getTotal()) / 12;
        }

        avg = avg.replace("xx", Utils.formatNumber(avgPrice));
        return avg;
    }

    public void setPieChartSetting(Context context, PieChart chartAudiance) {
        chartAudiance.setUsePercentValues(true);
        chartAudiance.getDescription().setEnabled(false);
        chartAudiance.setExtraOffsets(5, 10, 5, 5);
        chartAudiance.setDragDecelerationFrictionCoef(0.95f);

        chartAudiance.setDrawHoleEnabled(true);
        chartAudiance.setHoleColor(Color.WHITE);

        chartAudiance.setTransparentCircleColor(Color.WHITE);
        chartAudiance.setTransparentCircleAlpha(110);

        chartAudiance.setHoleRadius(40f);
        chartAudiance.setTransparentCircleRadius(0f);

        chartAudiance.setDrawCenterText(false);

        chartAudiance.setRotationAngle(0);
        // enable rotation of the chart by touch
        chartAudiance.setRotationEnabled(true);
        chartAudiance.setHighlightPerTapEnabled(true);


        // entry label styling
        chartAudiance.setEntryLabelColor(Color.WHITE);
        chartAudiance.setEntryLabelTypeface(ResourcesCompat.getFont(context, R.font.cairobold));
        chartAudiance.setEntryLabelTextSize(12f);
        chartAudiance.setContentDescription(" ");
        chartAudiance.setNoDataText(context.getString(R.string.no_data));
        chartAudiance.setMarker(new MyMarkerView(context, R.layout.chart_pie_item, "%"));

    }

    public void setAudiansData(Context context, PieChart chart, MySalesData mySalesData) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        if (mySalesData.getGender_data() != null) {
            entries.add(new PieEntry((float) (mySalesData.getGender_data().getMales())));
            entries.add(new PieEntry((float) (mySalesData.getGender_data().getFemale())));
            entries.add(new PieEntry((float) (mySalesData.getGender_data().getOthers())));
        }

        PieDataSet dataSet = new PieDataSet(entries, context.getString(R.string.bestt));
        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<>();

        colors.add(ContextCompat.getColor(context, R.color.chart_green));
        colors.add(ContextCompat.getColor(context, R.color.dgdg));
        colors.add(ContextCompat.getColor(context, R.color.color_navigation));


        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(ResourcesCompat.getFont(context, R.font.cairobold));
        chart.setData(data);

        // undo all highlights
        chart.highlightValues(null);

        chart.invalidate();
    }

    public void setDeliveryData(Context context, PieChart chart, MySalesData mySalesData) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        if (mySalesData.getDelivery() != null) {
            entries.add(new PieEntry((float) mySalesData.getDelivery().getHama()));
            entries.add(new PieEntry((float) mySalesData.getDelivery().getHand_by_hand()));
        }

        PieDataSet dataSet = new PieDataSet(entries, context.getString(R.string.Delivery_method));
        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(4f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(ContextCompat.getColor(context, R.color.color_navigation));
        colors.add(ContextCompat.getColor(context, R.color.chart_green));
        dataSet.setColors(colors);


        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.WHITE);
        data.setValueTypeface(ResourcesCompat.getFont(context, R.font.cairobold));
        chart.setData(data);

        // undo all highlights
        chart.highlightValues(null);

        chart.invalidate();
    }

    public void setSalesData(Context context, LineChart chart, MySalesData mySalesData) {
        ArrayList<Entry> values = new ArrayList<>();
        ArrayList<String> months = new ArrayList<>();
        if (mySalesData.getStatistics() != null) {

            values.add(new Entry(0, 0f));
            values.add(new Entry(1, (float) mySalesData.getStatistics().getJan()));
            values.add(new Entry(2, (float) mySalesData.getStatistics().getFeb()));
            values.add(new Entry(3, (float) mySalesData.getStatistics().getMarch()));
            values.add(new Entry(4, (float) mySalesData.getStatistics().getApril()));
            values.add(new Entry(5, (float) mySalesData.getStatistics().getMay()));
            values.add(new Entry(6, (float) mySalesData.getStatistics().getJune()));
            values.add(new Entry(7, (float) mySalesData.getStatistics().getJuly()));
            values.add(new Entry(8, (float) mySalesData.getStatistics().getAugust()));
            values.add(new Entry(9, (float) mySalesData.getStatistics().getSeptember()));
            values.add(new Entry(10, (float) mySalesData.getStatistics().getOctober()));
            values.add(new Entry(11, (float) mySalesData.getStatistics().getNovember()));
            values.add(new Entry(12, (float) mySalesData.getStatistics().getDecember()));


            months.add("");
            months.add(context.getString(R.string.jan));
            months.add(context.getString(R.string.feb));
            months.add(context.getString(R.string.mar));
            months.add(context.getString(R.string.apr));
            months.add(context.getString(R.string.may));
            months.add(context.getString(R.string.jun));
            months.add(context.getString(R.string.jul));
            months.add(context.getString(R.string.aug));
            months.add(context.getString(R.string.sep));
            months.add(context.getString(R.string.oct));
            months.add(context.getString(R.string.nov));
            months.add(context.getString(R.string.dec));

        }

        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(months));
        LineDataSet set1;
        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, context.getString(R.string.fixed12));
            set1.setDrawFilled(true);
            set1.setDrawValues(true);
            set1.setLineWidth(2f);

            set1.setFillDrawable(ContextCompat.getDrawable(context, R.color.white));
            set1.setCircleColors(ContextCompat.getColor(context, R.color.chart_green));
            set1.setDrawCircles(true);
            set1.setDrawCircleHole(false);
            set1.setCircleRadius(4f);
            set1.setValueTextColor(ContextCompat.getColor(context, R.color.chart_green));
            set1.setColor(ContextCompat.getColor(context, R.color.chart_green));

            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);


            // create a data object with the data sets
            LineData data = new LineData(set1);
            data.setValueTypeface(ResourcesCompat.getFont(context, R.font.cairobold));
            data.setValueTextSize(9f);
            data.setDrawValues(true);

            // set data
            chart.setData(data);
        }


    }
}

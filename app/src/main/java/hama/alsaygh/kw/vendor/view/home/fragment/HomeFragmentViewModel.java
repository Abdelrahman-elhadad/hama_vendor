package hama.alsaygh.kw.vendor.view.home.fragment;

import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.Locale;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.model.mySales.MyMarkerView;
import hama.alsaygh.kw.vendor.model.mySales.MySalesData;
import hama.alsaygh.kw.vendor.model.mySales.MySalesResponse;
import hama.alsaygh.kw.vendor.repo.ProfileRepo;
import hama.alsaygh.kw.vendor.utils.Utils;

public class HomeFragmentViewModel extends ViewModel {

    private final String TAG = "LoginActivityViewModel";
    FragmentManager fragmentManager;
    private final ProfileRepo authRepo;
    private MutableLiveData<MySalesResponse> loginResponseMutableLiveData;

    private Context context;

    public HomeFragmentViewModel(Context context, FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        this.context = context;
        authRepo = new ProfileRepo();

    }

    public MutableLiveData<MySalesResponse> getObservable() {
        if (loginResponseMutableLiveData == null)
            loginResponseMutableLiveData = new MutableLiveData<>();

        return loginResponseMutableLiveData;
    }

    public void getMySales(Context context) {
        authRepo.getMySales(context, loginResponseMutableLiveData);

    }

    public void setLineChartSetting(Context context, LineChart chart) {

        chart.setDrawGridBackground(false);

        chart.getAxisLeft().setDrawGridLines(true);
        chart.getAxisLeft().setDrawAxisLine(true);
        chart.getXAxis().setDrawGridLines(false);
        chart.getXAxis().setDrawLabels(true);
        chart.getXAxis().setDrawAxisLine(false);
        //Part5
        chart.getXAxis().setLabelRotationAngle(0f);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getXAxis().setGranularity(1f);
        chart.getXAxis().setDrawLabels(true);
        chart.getXAxis().setDrawAxisLine(true);
        chart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return Utils.formatNumber(context, value);
            }
        });


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
        chart.getAxisLeft().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return Utils.formatNumber(context, value);
            }
        });
        // don't forget to refresh the drawing
        chart.invalidate();

    }

    public String getSalesAvg(Context context, MySalesData mySalesData) {
        String avg = context.getString(R.string.bvbvb);
        double avgPrice = 0;

        if (mySalesData.getStatistics() != null) {
            avgPrice = (mySalesData.getStatistics().getTotal()) / 12;
        }

        avg = avg.replace("xx", Utils.formatNumber(context, avgPrice));
        return avg;
    }

    public void setSalesData(Context context, LineChart chart, MySalesData mySalesData) {
        ArrayList<Entry> values = new ArrayList<>();
        ArrayList<String> months = new ArrayList<>();
        if (mySalesData.getStatistics() != null) {

            values.add(new Entry(0, 0f));
//            values.add(new Entry(1, (float) mySalesData.getStatistics().getJan()));
//            values.add(new Entry(2, (float) mySalesData.getStatistics().getFeb()));
//            values.add(new Entry(3, (float) mySalesData.getStatistics().getMarch()));
//            values.add(new Entry(4, (float) mySalesData.getStatistics().getApril()));
//            values.add(new Entry(5, (float) mySalesData.getStatistics().getMay()));
//            values.add(new Entry(6, (float) mySalesData.getStatistics().getJune()));
//            values.add(new Entry(7, (float) mySalesData.getStatistics().getJuly()));
//            values.add(new Entry(8, (float) mySalesData.getStatistics().getAugust()));
//            values.add(new Entry(9, (float) mySalesData.getStatistics().getSeptember()));
//            values.add(new Entry(10, (float) mySalesData.getStatistics().getOctober()));
//            values.add(new Entry(11, (float) mySalesData.getStatistics().getNovember()));
//            values.add(new Entry(12, (float) mySalesData.getStatistics().getDecember()));


            values.add(new Entry(1, 500f));
            values.add(new Entry(2, (float) 700.78898798798797));
            values.add(new Entry(3, (float) 600));
            values.add(new Entry(4, (float) 1000));
            values.add(new Entry(5, (float) 10000));
            values.add(new Entry(6, (float) 1500));
            values.add(new Entry(7, (float) 10000));
            values.add(new Entry(8, (float) 1000));
            values.add(new Entry(9, (float) 900));
            values.add(new Entry(10, (float) 10000));
            values.add(new Entry(11, (float) 1000500));
            values.add(new Entry(12, (float) 30000));


            months.add("");
            months.add(context.getString(R.string.jan).toUpperCase(Locale.ENGLISH));
            months.add(context.getString(R.string.feb).toUpperCase(Locale.ENGLISH));
            months.add(context.getString(R.string.mar).toUpperCase(Locale.ENGLISH));
            months.add(context.getString(R.string.apr).toUpperCase(Locale.ENGLISH));
            months.add(context.getString(R.string.may).toUpperCase(Locale.ENGLISH));
            months.add(context.getString(R.string.jun).toUpperCase(Locale.ENGLISH));
            months.add(context.getString(R.string.jul).toUpperCase(Locale.ENGLISH));
            months.add(context.getString(R.string.aug).toUpperCase(Locale.ENGLISH));
            months.add(context.getString(R.string.sep).toUpperCase(Locale.ENGLISH));
            months.add(context.getString(R.string.oct).toUpperCase(Locale.ENGLISH));
            months.add(context.getString(R.string.nov).toUpperCase(Locale.ENGLISH));
            months.add(context.getString(R.string.dec).toUpperCase(Locale.ENGLISH));

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
            set1.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return Utils.formatNumber(context, value);
                }
            });

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

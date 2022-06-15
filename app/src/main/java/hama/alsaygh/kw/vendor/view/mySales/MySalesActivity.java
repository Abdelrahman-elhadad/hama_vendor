package hama.alsaygh.kw.vendor.view.mySales;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.appcompat.widget.AppCompatTextView;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.ActivityMySalesBinding;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.listener.LoginListener;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;

public class MySalesActivity extends BaseActivity implements LoginListener, OnChartValueSelectedListener {

    ActivityMySalesBinding binding;
    MySalesViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMySalesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new MySalesViewModel();
        binding.setModel(model);
        binding.imgBack.setOnClickListener(v -> finish());

        model.setPieChartSetting(this, binding.chartAudiance);
        model.setPieChartSetting(this, binding.chartDelivery);
        model.setLineChartSetting(this, binding.chartSales);

        model.getObservable().observe(this, mySalesResponse -> {
            if (mySalesResponse.isStatus()) {

                model.setAudiansData(this, binding.chartAudiance, mySalesResponse.getData());
                model.setDeliveryData(this, binding.chartDelivery, mySalesResponse.getData());
                model.setSalesData(this, binding.chartSales, mySalesResponse.getData());
                binding.tvSales.setText(model.getSalesAvg(this, mySalesResponse.getData()));
            } else {
                if (mySalesResponse.getCode().equalsIgnoreCase("401"))
                    LoginDialog.newInstance().show(getSupportFragmentManager(), "login");
                else
                    Snackbar.make(binding.imageView14, mySalesResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });

        model.login(this);

    }


    public void setAudiansData(int count, float range) {
        ArrayList<PieEntry> entries = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count; i++) {
            entries.add(new PieEntry((float) ((Math.random() * range) + range / 5), "test"));
        }

        PieDataSet dataSet = new PieDataSet(entries, "Election Results");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        // data.setValueTypeface(tfLight);
        binding.chartAudiance.setData(data);

        // undo all highlights
        binding.chartAudiance.highlightValues(null);

        binding.chartAudiance.invalidate();
    }


    @Override
    public void validation() {

    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

        //  showPopupWindow(binding.chartSales,e.getX());


    }

    @Override
    public void onNothingSelected() {

    }

    private void showPopupWindow(View v, float value) {
        // inflate the layout of the popup window

        View popupView = View.inflate(v.getContext(), R.layout.popup_window, null);

        AppCompatTextView tvEdit = popupView.findViewById(R.id.tv_edit);
        AppCompatTextView tvDelete = popupView.findViewById(R.id.tv_delete);
        tvDelete.setVisibility(View.GONE);
        tvEdit.setText(value + " " + getString(R.string.currency));
        // create the popup window
        final PopupWindow popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);


        tvEdit.setOnClickListener(v1 -> {

            popupWindow.dismiss();
        });


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.setElevation(20);
        }
        popupView.measure(View.MeasureSpec.UNSPECIFIED,
                View.MeasureSpec.UNSPECIFIED);
        int height = popupView.getMeasuredHeight();
        int width = popupView.getMeasuredWidth();
        popupWindow.setWidth(width);
        popupWindow.setHeight(height);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAsDropDown(v);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
}
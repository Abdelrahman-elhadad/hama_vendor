package hama.alsaygh.kw.vendor.model.mySales;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.utils.Utils;

public class MyMarkerView extends MarkerView {

    private final TextView tvContent;
    private final String currency;

    public MyMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        currency = "";
        tvContent = findViewById(R.id.tv_content);
    }

    public MyMarkerView(Context context, int layoutResource, String currency) {
        super(context, layoutResource);
        this.currency = currency;
        tvContent = findViewById(R.id.tv_content);
    }

    // runs every time the MarkerView is redrawn, can be used to update the
// content (user-interface)
    @SuppressLint("SetTextI18n")
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        if (e instanceof CandleEntry) {

            CandleEntry ce = (CandleEntry) e;

            tvContent.setText(Utils.formatNumberDigital(ce.getHigh()) + " " + currency);
        } else {


            tvContent.setText(Utils.formatNumberDigital(e.getY()) + " " + currency);
        }

        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
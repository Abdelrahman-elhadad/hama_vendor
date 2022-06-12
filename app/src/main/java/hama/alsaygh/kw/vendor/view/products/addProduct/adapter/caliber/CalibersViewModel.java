package hama.alsaygh.kw.vendor.view.products.addProduct.adapter.caliber;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.model.product.caliber.Caliber;

public class CalibersViewModel extends ViewModel {


    private final Context context;

    protected Caliber addProduct;
    protected int position;

    public CalibersViewModel(Context context, Caliber addProduct) {
        this.context = context;
        this.addProduct = addProduct;
    }

    public String getValue() {
        return addProduct.getValue() + "";
    }
}

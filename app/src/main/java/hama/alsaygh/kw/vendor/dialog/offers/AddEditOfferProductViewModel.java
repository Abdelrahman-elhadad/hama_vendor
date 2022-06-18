package hama.alsaygh.kw.vendor.dialog.offers;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.model.addProduct.AddProduct;
import hama.alsaygh.kw.vendor.model.product.Product;

public class AddEditOfferProductViewModel extends ViewModel {


    protected AddProduct addProduct = new AddProduct();
    protected Product product = new Product();
    protected int position;


    public final MutableLiveData<String> weightObservable = new MutableLiveData<>();
    public final MutableLiveData<String> quantityObservable = new MutableLiveData<>();

    protected ObservableBoolean bindToMarket = new ObservableBoolean();
    protected ObservableBoolean fixedPrice = new ObservableBoolean();

    protected ObservableInt bindToMarketVisibility = new ObservableInt();
    protected ObservableInt fixedPriceVisibility = new ObservableInt();
    Context context;

    public AddEditOfferProductViewModel(Context context) {
        this.context = context;
//        weightObservable.setValue(addProduct.getTotal_weight());
//        quantityObservable.setValue(addProduct.getAvailable_quantity() + "");

        bindToMarket.set(false);
        fixedPrice.set(false);
        bindToMarketVisibility.set(View.GONE);
        fixedPriceVisibility.set(View.GONE);

    }

    public AddEditOfferProductViewModel(Context context, AddProduct option, Product product) {
        this.context = context;
        this.addProduct = option;
        this.product = product;

//        weightObservable.setValue(addProduct.getTotal_weight());
//        quantityObservable.setValue(addProduct.getAvailable_quantity() + "");

        bindToMarket.set(addProduct.isBind_to_market());
        fixedPrice.set(!addProduct.isBind_to_market());
        bindToMarketVisibility.set(View.GONE);
        fixedPriceVisibility.set(View.GONE);


    }

    public AddProduct getAddProduct() {
        return addProduct;
    }

    public Product getProduct() {
        return product;
    }

    public MutableLiveData<String> getWeightObserver() {
        return weightObservable;
    }

    public MutableLiveData<String> getQuantityObserver() {
        return quantityObservable;
    }


    public TextWatcher nameEnTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addProduct.setName(s.toString());
            }
        };
    }


    public MutableLiveData<String> getWeightObservable() {
        return weightObservable;
    }

    public MutableLiveData<String> getQuantityObservable() {
        return quantityObservable;
    }


    public ObservableInt getBindToMarketVisibility() {
        return bindToMarketVisibility;
    }

    public ObservableInt getFixedPriceVisibility() {
        return fixedPriceVisibility;
    }


    public ObservableBoolean getBindToMarket() {
        return bindToMarket;
    }

    public ObservableBoolean getFixedPrice() {
        return fixedPrice;
    }

    public void onFixedCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked) {
            addProduct.setBind_to_market(false);
            fixedPrice.set(true);
            bindToMarket.set(false);

            bindToMarketVisibility.set(View.GONE);
            fixedPriceVisibility.set(View.VISIBLE);
        } else
            fixedPriceVisibility.set(View.GONE);
    }

    public void onMarketCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            addProduct.setBind_to_market(true);
            fixedPrice.set(false);
            bindToMarket.set(true);

            bindToMarketVisibility.set(View.VISIBLE);
            fixedPriceVisibility.set(View.GONE);
        } else
            bindToMarketVisibility.set(View.VISIBLE);
    }

    public String getNameEn() {
        if (addProduct != null)
            return addProduct.getName();
        return "";
    }


    public String getColor() {
        if (addProduct != null)
            return addProduct.getColor();
        return "";
    }

    public boolean isFixedPrice() {
        if (addProduct != null)
            return !addProduct.isBind_to_market();
        return false;
    }

    public boolean isMarketPrice() {
        if (addProduct != null)
            return addProduct.isBind_to_market();
        return false;
    }

}

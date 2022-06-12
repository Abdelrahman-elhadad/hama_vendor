package hama.alsaygh.kw.vendor.dialog.addOptionDialog;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.model.product.Option;
import hama.alsaygh.kw.vendor.utils.Utils;

public class AddOptionProductViewModel extends ViewModel {


    protected Option addProduct = new Option();
    protected int position;


    public final MutableLiveData<String> weightObservable = new MutableLiveData<>();
    public final MutableLiveData<String> quantityObservable = new MutableLiveData<>();

    protected ObservableBoolean bindToMarket = new ObservableBoolean();
    protected ObservableBoolean fixedPrice = new ObservableBoolean();

    protected ObservableInt bindToMarketVisibility = new ObservableInt();
    protected ObservableInt fixedPriceVisibility = new ObservableInt();
    Context context;

    public AddOptionProductViewModel(Context context) {
        this.context = context;
        weightObservable.setValue(addProduct.getTotal_weight());
        quantityObservable.setValue(addProduct.getAvailable_quantity() + "");

        bindToMarket.set(false);
        fixedPrice.set(false);
        bindToMarketVisibility.set(View.GONE);
        fixedPriceVisibility.set(View.GONE);

    }

    public AddOptionProductViewModel(Context context, Option option) {
        this.context = context;
        this.addProduct = option;
        weightObservable.setValue(addProduct.getTotal_weight());
        quantityObservable.setValue(addProduct.getAvailable_quantity() + "");

        bindToMarket.set(addProduct.isBind_to_market());
        fixedPrice.set(!addProduct.isBind_to_market());
        bindToMarketVisibility.set(View.GONE);
        fixedPriceVisibility.set(View.GONE);


    }

    public Option getAddProduct() {
        return addProduct;
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

    public TextWatcher nameArTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addProduct.setNameAr(s.toString());
            }
        };
    }


    public TextWatcher weightTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addProduct.setTotal_weight(Utils.getInstance().convertArabic(s.toString()));
            }
        };
    }

    public TextWatcher quantityTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s == null || s.toString().isEmpty())
                    addProduct.setAvailable_quantity(0);
                else
                    addProduct.setAvailable_quantity(Integer.parseInt(Utils.getInstance().convertArabic(s.toString())));
            }
        };
    }

    public TextWatcher fixedPriceTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s == null || s.toString().isEmpty())
                    addProduct.setPrice(0);
                else
                    addProduct.setPrice(Double.parseDouble(Utils.getInstance().convertArabic(s.toString())));
            }
        };
    }


    public void onMinWeightClick(View v) {

        double weight = Double.parseDouble(addProduct.getTotal_weight());
        if (weight != 0)
            --weight;
        addProduct.setTotal_weight(weight + "");
        weightObservable.setValue(weight + "");

    }

    public void onMaxWeightClick(View v) {

        double weight = Double.parseDouble(addProduct.getTotal_weight());
        ++weight;
        addProduct.setTotal_weight(weight + "");
        weightObservable.setValue(weight + "");

    }

    public void onMinQuantityClick(View v) {

        int quantity = addProduct.getAvailable_quantity();
        if (quantity != 0)
            --quantity;
        addProduct.setAvailable_quantity(quantity);
        quantityObservable.setValue(quantity + "");

    }

    public void onMaxQuantityClick(View v) {

        int quantity = addProduct.getAvailable_quantity();
        ++quantity;
        addProduct.setAvailable_quantity(quantity);
        quantityObservable.setValue(quantity + "");

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

    public String getNameAr() {
        if (addProduct != null)
            return addProduct.getNameAr();
        return "";
    }

    public String getWeight() {
        if (addProduct != null)
            return addProduct.getTotal_weight();
        return "";
    }

    public String getPrice() {
        if (addProduct != null)
            return addProduct.getPrice() + "";
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

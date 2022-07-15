package hama.alsaygh.kw.vendor.dialog.offers;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.model.addProduct.AddProduct;
import hama.alsaygh.kw.vendor.model.general.GeneralResponse;
import hama.alsaygh.kw.vendor.model.product.Product;
import hama.alsaygh.kw.vendor.repo.ProductRepo;
import hama.alsaygh.kw.vendor.utils.LocalUtils;
import hama.alsaygh.kw.vendor.utils.Utils;

public class AddEditOfferProductViewModel extends ViewModel {


    protected AddProduct addProduct = new AddProduct();
    protected Product product = new Product();
    protected int position;
    public final MutableLiveData<String> newPriceObservable = new MutableLiveData<>();
    public final MutableLiveData<GeneralResponse> addProductMutableLiveData = new MutableLiveData<>();

    private Context context;
    int type;

    public AddEditOfferProductViewModel(Context context) {
        this.context = context;
    }

    public void setType(int type) {
        this.type = type;
    }

    public AddProduct getAddProduct() {
        return addProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;


        if (addProduct.getDiscount() != null && !addProduct.getDiscount().isEmpty() && addProduct.getFixed_price() != null && !addProduct.getFixed_price().isEmpty()) {
            double price = Double.parseDouble(addProduct.getFixed_price());
            double offers = Double.parseDouble(Utils.getInstance().convertArabic(addProduct.getDiscount()));
            double discount = price * (offers / 100);
            newPriceObservable.setValue(Utils.formatNumberDigital(Math.round(price - discount)));
            this.product.setDiscount_value(offers);
        } else {
            newPriceObservable.setValue(addProduct.getFixed_price());
            this.product.setDiscount_value(0.0);
        }

    }

    public void setAddProduct(AddProduct addProduct) {
        this.addProduct = addProduct;
    }

    public String getName() {
        if (addProduct != null)
            if (isEnglish())
                return addProduct.getName();
            else
                return addProduct.getName_ar();
        return "";
    }

    public String getPrice() {
        if (addProduct != null) {
            Log.i("getFixed_price", "getFixed_price : " + addProduct.getFixed_price());
            return addProduct.getFixed_price();
        }
        return "";
    }

    public String getDiscount() {
        if (addProduct != null)
            return addProduct.getDiscount();
        return "";
    }


    public TextWatcher ratioTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addProduct.setDiscount(Utils.getInstance().convertArabic(s.toString()));


                if (!s.toString().isEmpty() && addProduct.getFixed_price() != null && !addProduct.getFixed_price().isEmpty()) {
                    double price = Double.parseDouble(addProduct.getFixed_price());
                    double offers = Double.parseDouble(Utils.getInstance().convertArabic(s.toString()));
                    double discount = price * (offers / 100);
                    newPriceObservable.setValue(Utils.formatNumberDigital(Math.round(price - discount)));
                    product.setDiscount_value(offers);
                } else {
                    newPriceObservable.setValue(addProduct.getFixed_price());
                    product.setDiscount_value(0.0);
                }

            }
        };
    }

    public void addEditDiscount(Context context) {
        new ProductRepo().updateProduct(context, addProduct, addProductMutableLiveData);
    }

    public boolean isEnglish() {
        return LocalUtils.getInstance().getLanguageShort(context).equalsIgnoreCase("en");
    }
}

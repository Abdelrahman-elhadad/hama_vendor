package hama.alsaygh.kw.vendor.view.products.addProduct;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.model.addProduct.AddProduct;
import hama.alsaygh.kw.vendor.model.category.CategoriesResponse;
import hama.alsaygh.kw.vendor.model.category.Category;
import hama.alsaygh.kw.vendor.model.category.MainCategoriesResponse;
import hama.alsaygh.kw.vendor.model.category.MainCategory;
import hama.alsaygh.kw.vendor.model.general.GeneralResponse;
import hama.alsaygh.kw.vendor.model.product.Product;
import hama.alsaygh.kw.vendor.model.product.caliber.CalibersResponse;
import hama.alsaygh.kw.vendor.repo.GeneralRepo;
import hama.alsaygh.kw.vendor.repo.ProductRepo;
import hama.alsaygh.kw.vendor.utils.Utils;
import hama.alsaygh.kw.vendor.view.marketPrice.MarketPriceActivity;

public class AddEditProductViewModel extends ViewModel {

    FragmentManager fragmentManager;

    //  private Home home ;
    protected final int Step1 = 1;
    protected final int Step2 = 2;
    protected final int Add = 1;
    protected final int Edit = 2;
    protected final AddProduct addProduct = new AddProduct();
    protected int position;

    protected int type = Add;


    private final MutableLiveData<String> weightObservable = new MutableLiveData<>();
    private final MutableLiveData<String> quantityObservable = new MutableLiveData<>();
    private final MutableLiveData<CategoriesResponse> categoriesMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<MainCategoriesResponse> mainCategoriesMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<GeneralResponse> addProductMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<CalibersResponse> calibersMutableLiveData = new MutableLiveData<>();

    protected ObservableInt childSubVisibility = new ObservableInt();
    protected ObservableBoolean bindToMarket = new ObservableBoolean();
    protected ObservableBoolean fixedPrice = new ObservableBoolean();

    protected ObservableInt bindToMarketVisibility = new ObservableInt();
    protected ObservableInt fixedPriceVisibility = new ObservableInt();

    protected ObservableInt categoriesVisibility = new ObservableInt();
    protected ObservableInt nextVisibility = new ObservableInt();
    protected ObservableInt addProductVisibility = new ObservableInt();
    protected ObservableInt pbAddProductVisibility = new ObservableInt();
    protected ObservableInt discountVisibility = new ObservableInt();

    GeneralRepo generalRepo;
    ProductRepo productRepo;

    Fragment fragment;

    public AddEditProductViewModel(Context context, FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;

        generalRepo = new GeneralRepo();
        productRepo = new ProductRepo();
        weightObservable.setValue(addProduct.getWeight());
        quantityObservable.setValue(addProduct.getQuantity());
        childSubVisibility.set(View.GONE);
        bindToMarket.set(false);
        fixedPrice.set(false);
        bindToMarketVisibility.set(View.GONE);
        fixedPriceVisibility.set(View.GONE);
        categoriesVisibility.set(View.GONE);
        nextVisibility.set(View.VISIBLE);
        addProductVisibility.set(View.GONE);
        pbAddProductVisibility.set(View.GONE);
        discountVisibility.set(View.GONE);
        type = Add;
    }

    public AddEditProductViewModel(Context context, Product product, FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;

        generalRepo = new GeneralRepo();
        productRepo = new ProductRepo();
        type = Edit;
        addProduct.setId(product.getId());
        addProduct.setCode(product.getCate_code());
        if (product.getTranslations() != null) {
            if (product.getTranslations().getEn() != null) {
                addProduct.setName(product.getTranslations().getEn().getName());
                addProduct.setDescription(product.getTranslations().getEn().getDescription());
            }
            if (product.getTranslations().getEn() != null) {
                addProduct.setName_ar(product.getTranslations().getAr().getName());
                addProduct.setDescription_ar(product.getTranslations().getAr().getDescription());
            }
        }
        addProduct.setWeight(product.getWeight());
        addProduct.setCaliber(product.getCaliber());
        addProduct.setQuantity(product.getQuantity() + "");
        addProduct.setMain_category(product.getMain_category());

        if (product.getCategory() == null) {
            addProduct.setSub_category(product.getSub_category());
            addProduct.setChild_sub_category(null);
        } else {
            addProduct.setSub_category(product.getCategory());
            addProduct.setChild_sub_category(product.getSub_category());
        }

        addProduct.setBind_to_market(product.isBind_to_market());
        addProduct.setManufacture_price(product.getManufacture_price() + "");
        addProduct.setNetWeight(product.getMetal_weight() + "");
        addProduct.setStoneType(product.getSton_type() + "");
        addProduct.setStoneWeight(product.getGem_stone_weight() + "");
        addProduct.setDiamond(product.getDimond() + "");
        addProduct.setDiamondWeight(product.getDiamond_weight() + "");
        addProduct.setPurity(product.getPurity() + "");
        addProduct.setColor(product.getColor());
        addProduct.setFixed_price(product.getPrice() + "");
        addProduct.setDiscount(product.getDiscount_value() + "");
        addProduct.setGmPrice(product.getGram_price() + "");
        addProduct.setTotalWeightMetal(product.getTotal_metal_weight() + "");
        addProduct.setMedia(product.getMedia());
        addProduct.setOptions(product.getOptions());


        weightObservable.setValue(addProduct.getWeight());
        quantityObservable.setValue(addProduct.getQuantity());
        childSubVisibility.set(View.GONE);

        bindToMarketVisibility.set(View.GONE);
        fixedPriceVisibility.set(View.GONE);
        categoriesVisibility.set(View.GONE);
        nextVisibility.set(View.VISIBLE);
        addProductVisibility.set(View.GONE);
        pbAddProductVisibility.set(View.GONE);
        discountVisibility.set(View.GONE);
        bindToMarket.set(addProduct.isBind_to_market());
        fixedPrice.set(!addProduct.isBind_to_market());

        Log.i("json", "json : " + addProduct.toString());
    }


    public AddProduct getAddProduct() {
        return addProduct;
    }

    public MutableLiveData<String> getWeightObserver() {
        return weightObservable;
    }

    public MutableLiveData<String> getQuantityObserver() {
        return quantityObservable;
    }

    public MutableLiveData<CalibersResponse> getCalibersObserver() {
        return calibersMutableLiveData;
    }

    public MutableLiveData<CategoriesResponse> getCategoriesObserver() {
        return categoriesMutableLiveData;
    }

    public MutableLiveData<MainCategoriesResponse> getMainCategoriesObserver() {
        return mainCategoriesMutableLiveData;
    }

    public MutableLiveData<GeneralResponse> getAddProductObserver() {
        return addProductMutableLiveData;
    }

    public void getCategories(Context context) {
        generalRepo.getCategories(context, categoriesMutableLiveData);
    }

    public void getMainCategories(Context context) {
        generalRepo.getMainCategories(context, mainCategoriesMutableLiveData);
    }

    public void getCaliberss(Context context) {
        productRepo.getCalibers(context, calibersMutableLiveData);
    }

    public void commitFragment(Fragment fragment, int position) {
        this.position = position;
        this.fragment = fragment;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.liner1, fragment);
        fragmentTransaction.commit();

        if (position == Step1) {
            nextVisibility.set(View.VISIBLE);
            addProductVisibility.set(View.GONE);
        } else {
            nextVisibility.set(View.GONE);
            addProductVisibility.set(View.VISIBLE);
        }
    }


    public TextWatcher codeTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addProduct.setCode(Utils.getInstance().convertArabic(s.toString()));
            }
        };
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
                addProduct.setName_ar(s.toString());
            }
        };
    }

    public TextWatcher descEnTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addProduct.setDescription(s.toString());
            }
        };
    }

    public TextWatcher descArTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addProduct.setDescription_ar(s.toString());
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
                addProduct.setWeight(Utils.getInstance().convertArabic(s.toString()));
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
                addProduct.setQuantity(Utils.getInstance().convertArabic(s.toString()));
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

                addProduct.setFixed_price(Utils.getInstance().convertArabic(s.toString()));
            }
        };
    }

    public TextWatcher fixedPriceDiscountTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    addProduct.setDiscount("");
                    discountVisibility.set(View.GONE);
                } else {
                    double discount = Double.parseDouble(Utils.getInstance().convertArabic(s.toString()));
                    if (discount < 0) {
                        addProduct.setDiscount("0");
                        discountVisibility.set(View.GONE);
                    } else if (discount > 100) {
                        addProduct.setDiscount(discount + "");
                        discountVisibility.set(View.VISIBLE);
                    } else {
                        addProduct.setDiscount(discount + "");
                        discountVisibility.set(View.GONE);
                    }
                }
            }
        };
    }

    public TextWatcher manufacturePriceTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addProduct.setManufacture_price(Utils.getInstance().convertArabic(s.toString()));
            }
        };
    }

    public TextWatcher netWeightTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addProduct.setNetWeight(Utils.getInstance().convertArabic(s.toString()));
            }
        };
    }

    public TextWatcher stoneWeightTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addProduct.setStoneWeight(Utils.getInstance().convertArabic(s.toString()));
            }
        };
    }

    public TextWatcher totalMetalWeightTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addProduct.setTotalWeightMetal(Utils.getInstance().convertArabic(s.toString()));
            }
        };
    }

    public TextWatcher stoneTypeTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addProduct.setStoneType(Utils.getInstance().convertArabic(s.toString()));
            }
        };
    }

    public TextWatcher diamondTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addProduct.setDiamond(Utils.getInstance().convertArabic(s.toString()));
            }
        };
    }

    public TextWatcher diamondWeightTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addProduct.setDiamondWeight(Utils.getInstance().convertArabic(s.toString()));
            }
        };
    }

    public TextWatcher gmPriceTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addProduct.setGmPrice(Utils.getInstance().convertArabic(Utils.getInstance().convertArabic(s.toString())));
            }
        };
    }

    public TextWatcher colorTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addProduct.setColor(s.toString());
            }
        };
    }

    public TextWatcher purityTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                addProduct.setPurity(Utils.getInstance().convertArabic(s.toString()));
            }
        };
    }


    public void onMinWeightClick(View v) {

        double weight = Double.parseDouble(addProduct.getWeight());
        if (weight != 0)
            --weight;
        addProduct.setWeight(weight + "");
        weightObservable.setValue(weight + "");

    }

    public void onMaxWeightClick(View v) {

        double weight = Double.parseDouble(addProduct.getWeight());
        ++weight;
        addProduct.setWeight(weight + "");
        weightObservable.setValue(weight + "");

    }

    public void onMinQuantityClick(View v) {

        double quantity = Double.parseDouble(addProduct.getQuantity());
        if (quantity != 0)
            --quantity;
        addProduct.setQuantity(quantity + "");
        quantityObservable.setValue(quantity + "");

    }

    public void onMaxQuantityClick(View v) {

        double quantity = Double.parseDouble(addProduct.getQuantity());
        ++quantity;
        addProduct.setQuantity(quantity + "");
        quantityObservable.setValue(quantity + "");

    }


    public MutableLiveData<String> getWeightObservable() {
        return weightObservable;
    }

    public MutableLiveData<String> getQuantityObservable() {
        return quantityObservable;
    }

    public ObservableInt getChildSubVisibility() {
        return childSubVisibility;
    }

    public ObservableInt getBindToMarketVisibility() {
        return bindToMarketVisibility;
    }

    public ObservableInt getFixedPriceVisibility() {
        return fixedPriceVisibility;
    }

    public ObservableInt getCategoriesVisibility() {
        return categoriesVisibility;
    }

    public void setMainCategories(MainCategory category) {
        addProduct.setMain_category(category);
    }

    public void setCategories(Category category) {
        addProduct.setSub_category(category);
    }

    public void setChildCategories(Category category) {
        addProduct.setChild_sub_category(category);
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

    public void onNextClick(View v) {
        if (fragment != null) {
            if (((AddEditProductStep1Fragment) fragment).isValid()) {
                commitFragment(AddEditProductStep2Fragment.newInstance(this), Step2);
            }
        }
    }

    public void onAddProductClick(View v) {

        if (fragment != null) {
            if (((AddEditProductStep2Fragment) fragment).isValid()) {
                addProduct.setMedia(((AddEditProductStep2Fragment) fragment).getImages());
                addProduct.setOptions(((AddEditProductStep2Fragment) fragment).getOptions());
                addProductVisibility.set(View.GONE);
                pbAddProductVisibility.set(View.VISIBLE);

                if (type == Add)
                    productRepo.addProduct(v.getContext(), addProduct, addProductMutableLiveData);
                else if (type == Edit)
                    productRepo.updateProduct(v.getContext(), addProduct, addProductMutableLiveData);
            }
        }

    }

    public void onAddOptionClick(View v) {

    }


    public ObservableInt getNextVisibility() {
        return nextVisibility;
    }

    public ObservableInt getAddProductVisibility() {
        return addProductVisibility;
    }

    public ObservableInt getPbProductVisibility() {
        return pbAddProductVisibility;
    }

    public ObservableInt getDiscountVisibility() {
        return discountVisibility;
    }

    public void onMarketPriceClick(View view) {
        view.getContext().startActivity(new Intent(view.getContext(), MarketPriceActivity.class));
    }
}

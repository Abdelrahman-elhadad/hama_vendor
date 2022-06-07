package hama.alsaygh.kw.vendor.view.products.addProduct;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
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
import hama.alsaygh.kw.vendor.repo.GeneralRepo;

public class AddProductViewModel extends ViewModel {

    private final String TAG = "ProductsViewModel";
    FragmentManager fragmentManager;

    //  private Home home ;
    protected final int Step1 = 1;
    protected final int Step2 = 2;
    protected final AddProduct addProduct = new AddProduct();
    protected int position;

    private Context context;

    private final MutableLiveData<String> weightObservable = new MutableLiveData<>();
    private final MutableLiveData<String> quantityObservable = new MutableLiveData<>();
    private final MutableLiveData<CategoriesResponse> categoriesMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<MainCategoriesResponse> mainCategoriesMutableLiveData = new MutableLiveData<>();
    protected ObservableInt childSubVisibility = new ObservableInt();
    protected ObservableBoolean bindToMarket = new ObservableBoolean();
    protected ObservableBoolean fixedPrice = new ObservableBoolean();

    protected ObservableInt bindToMarketVisibility = new ObservableInt();
    protected ObservableInt fixedPriceVisibility = new ObservableInt();

    GeneralRepo generalRepo;

    Fragment fragment;

    public AddProductViewModel(Context context, FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        this.context = context;
        generalRepo = new GeneralRepo();
        weightObservable.setValue(addProduct.getWeight());
        quantityObservable.setValue(addProduct.getQuantity());
        childSubVisibility.set(View.GONE);
        bindToMarket.set(false);
        fixedPrice.set(false);
        bindToMarketVisibility.set(View.GONE);
        fixedPriceVisibility.set(View.GONE);
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

    public MutableLiveData<CategoriesResponse> getCategoriesObserver() {
        return categoriesMutableLiveData;
    }

    public MutableLiveData<MainCategoriesResponse> getMainCategoriesObserver() {
        return mainCategoriesMutableLiveData;
    }

    public void getCategories(Context context) {
        generalRepo.getCategories(context, categoriesMutableLiveData);
    }

    public void getMainCategories(Context context) {
        generalRepo.getMainCategories(context, mainCategoriesMutableLiveData);
    }


    public void commitFragment(Fragment fragment, int position) {
        this.position = position;
        this.fragment = fragment;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.liner1, fragment);
        fragmentTransaction.commit();
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
                addProduct.setCode(s.toString());
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
                addProduct.setWeight(s.toString());
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
                addProduct.setQuantity(s.toString());
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
                addProduct.setFixed_price(s.toString());
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
                addProduct.setManufacture_price(s.toString());
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
                addProduct.setNetWeight(s.toString());
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
                addProduct.setTotalWeightMetal(s.toString());
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
                addProduct.setStoneType(s.toString());
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
                addProduct.setDiamond(s.toString());
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
                addProduct.setDiamondWeight(s.toString());
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
                addProduct.setGmPrice(s.toString());
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
                addProduct.setPurity(s.toString());
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
        }
    }

    public void onMarketCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            addProduct.setBind_to_market(true);
            fixedPrice.set(false);
            bindToMarket.set(true);

            bindToMarketVisibility.set(View.VISIBLE);
            fixedPriceVisibility.set(View.GONE);
        }
    }

    public void onNextClick(View v) {
        if (position == Step1) {
            if (fragment != null) {
                if (((AddProductStep1Fragment) fragment).isValid()) {

                    commitFragment(AddProductStep2Fragment.newInstance(this), Step2);
                }
            }
        } else if (position == Step2) {

        }
    }


}

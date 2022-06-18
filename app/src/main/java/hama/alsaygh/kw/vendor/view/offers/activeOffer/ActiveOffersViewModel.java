package hama.alsaygh.kw.vendor.view.offers.activeOffer;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.model.addProduct.AddProduct;
import hama.alsaygh.kw.vendor.model.product.Product;
import hama.alsaygh.kw.vendor.model.product.ProductsResponse;
import hama.alsaygh.kw.vendor.repo.ProductRepo;
import hama.alsaygh.kw.vendor.view.products.addProduct.AddEditProductActivity;

public class ActiveOffersViewModel extends ViewModel {

    private final String TAG = "ProductsViewModel";

    private MutableLiveData<ProductsResponse> languageResponseMutableLiveData;

    final private ProductRepo productRepo;

    private String sort_key = "", type_of_price = "", range_price_from = "", range_price_to = "";
    private int category_level_1 = -1, category_level_2 = -1, category_level_3 = -1;


    public ActiveOffersViewModel() {
        productRepo = new ProductRepo();

    }

    public MutableLiveData<ProductsResponse> getObserver() {
        if (languageResponseMutableLiveData == null)
            languageResponseMutableLiveData = new MutableLiveData<>();
        return languageResponseMutableLiveData;
    }

    public void getProducts(Context context, int page) {
        productRepo.getProducts(context, page, sort_key, category_level_1, category_level_2, category_level_3, type_of_price, range_price_from, range_price_to, 1, languageResponseMutableLiveData);
    }


    public void onAddProductClick(View v) {
        Intent intent = new Intent(v.getContext(), AddEditProductActivity.class);
        v.getContext().startActivity(intent);
    }


    public String getSort_key() {
        return sort_key;
    }

    public void setSort_key(String sort_key) {
        this.sort_key = sort_key;
    }

    public String getType_of_price() {
        return type_of_price;
    }

    public void setType_of_price(String type_of_price) {
        this.type_of_price = type_of_price;
    }

    public String getRange_price_from() {
        return range_price_from;
    }

    public void setRange_price_from(String range_price_from) {
        this.range_price_from = range_price_from;
    }

    public String getRange_price_to() {
        return range_price_to;
    }

    public void setRange_price_to(String range_price_to) {
        this.range_price_to = range_price_to;
    }

    public int getCategory_level_1() {
        return category_level_1;
    }

    public void setCategory_level_1(int category_level_1) {
        this.category_level_1 = category_level_1;
    }

    public int getCategory_level_2() {
        return category_level_2;
    }

    public void setCategory_level_2(int category_level_2) {
        this.category_level_2 = category_level_2;
    }

    public int getCategory_level_3() {
        return category_level_3;
    }

    public void setCategory_level_3(int category_level_3) {
        this.category_level_3 = category_level_3;
    }

    public AddProduct setProductToSend(Product product) {
        AddProduct addProduct = new AddProduct();
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
        addProduct.setSub_category(product.getCategory());
        addProduct.setChild_sub_category(product.getSub_category());
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

        return addProduct;

    }

}

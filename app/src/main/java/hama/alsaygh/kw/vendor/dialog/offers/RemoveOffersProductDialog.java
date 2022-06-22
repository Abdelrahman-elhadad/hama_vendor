package hama.alsaygh.kw.vendor.dialog.offers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.app.MainApplication;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.addProduct.AddProduct;
import hama.alsaygh.kw.vendor.model.general.GeneralResponse;
import hama.alsaygh.kw.vendor.model.product.Product;
import hama.alsaygh.kw.vendor.repo.ProductRepo;


public class RemoveOffersProductDialog extends BottomSheetDialogFragment {


    LinearLayout parent_delete_p;
    ImageView icClose;
    TextView textView80, textView135;
    View view25;
    Button button3;
    ProgressBar pb_delete;
    AddProduct addProduct;

    OnGeneralClickListener onMyCartListener;
    int product_id;
    MutableLiveData<GeneralResponse> loginResponseMutableLiveData = new MutableLiveData<>();

    public void setOnMyCartListener(OnGeneralClickListener onMyCartListener) {
        this.onMyCartListener = onMyCartListener;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setAddProduct(Product product) {
        addProduct = new AddProduct();
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
        addProduct.setDiscount("0.0");
        addProduct.setGmPrice(product.getGram_price() + "");
        addProduct.setTotalWeightMetal(product.getTotal_metal_weight() + "");
        addProduct.setMedia(product.getMedia());
        addProduct.setOptions(product.getOptions());

    }

    public static RemoveOffersProductDialog newInstance(int product_id, Product addProduct, OnGeneralClickListener onMyCartListener) {

        RemoveOffersProductDialog fragment = new RemoveOffersProductDialog();
        fragment.setProduct_id(product_id);
        fragment.setAddProduct(addProduct);
        fragment.setOnMyCartListener(onMyCartListener);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.dialog_delete_product, container, false);
        icClose = view.findViewById(R.id.imageView28);
        textView80 = view.findViewById(R.id.textView80);
        textView135 = view.findViewById(R.id.textView135);
        view25 = view.findViewById(R.id.view25);
        button3 = view.findViewById(R.id.button3);
        pb_delete = view.findViewById(R.id.pb_delete);
        parent_delete_p = view.findViewById(R.id.parent_delete_p);

        icClose.setOnClickListener(v -> dismiss());

        textView80.setText(requireContext().getString(R.string.delete_offer));
        button3.setText(requireContext().getString(R.string.delete_offer));
        textView135.setText(requireContext().getString(R.string.delete_offer_msg));


        loginResponseMutableLiveData.observe(requireActivity(), productsResponse -> {
            pb_delete.setVisibility(View.GONE);
            button3.setVisibility(View.VISIBLE);
            if (productsResponse.isStatus()) {
                dismiss();
                if (onMyCartListener != null)
                    onMyCartListener.onDeleteClick(product_id, 0);
            } else {
                if (productsResponse.getCode().equalsIgnoreCase("401")) {
                    LoginDialog.newInstance().show(getChildFragmentManager(), "login");
                    dismiss();
                } else {
                    Snackbar.make(button3, productsResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        button3.setOnClickListener(v -> {

            if (MainApplication.isConnected) {

                pb_delete.setVisibility(View.VISIBLE);
                button3.setVisibility(View.GONE);
                new ProductRepo().updateProduct(v.getContext(), addProduct, loginResponseMutableLiveData);

            } else {
                Snackbar.make(v, v.getContext().getString(R.string.no_internet_connection), Snackbar.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}

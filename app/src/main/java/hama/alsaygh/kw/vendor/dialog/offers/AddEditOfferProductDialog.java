package hama.alsaygh.kw.vendor.dialog.offers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import eltos.simpledialogfragment.SimpleDialog;
import eltos.simpledialogfragment.color.SimpleColorDialog;
import hama.alsaygh.kw.vendor.databinding.DialogAddOptionProductBinding;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.addProduct.AddProduct;
import hama.alsaygh.kw.vendor.model.product.Product;
import hama.alsaygh.kw.vendor.utils.AppConstants;


public class AddEditOfferProductDialog extends BottomSheetDialogFragment implements SimpleDialog.OnDialogResultListener {

    DialogAddOptionProductBinding binding;
    AddEditOfferProductViewModel model;

    OnGeneralClickListener onMyCartListener;
    int type = 1;
    int position;
    AddProduct addProduct;
    Product product;

    public void setType(int type) {
        this.type = type;
    }

    public void setPosition(int position) {
        this.position = position;
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
        addProduct.setDiscount("0.0");
        addProduct.setGmPrice(product.getGram_price() + "");
        addProduct.setTotalWeightMetal(product.getTotal_metal_weight() + "");
        addProduct.setMedia(product.getMedia());
        addProduct.setOptions(product.getOptions());

    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setOnMyCartListener(OnGeneralClickListener onMyCartListener) {
        this.onMyCartListener = onMyCartListener;
    }

    public static AddEditOfferProductDialog newInstance(OnGeneralClickListener onMyCartListener) {

        AddEditOfferProductDialog fragment = new AddEditOfferProductDialog();
        fragment.setOnMyCartListener(onMyCartListener);
        fragment.setType(AppConstants.ADD);

        return fragment;
    }

    public static AddEditOfferProductDialog newInstance(Product product, int position, OnGeneralClickListener onMyCartListener) {

        AddEditOfferProductDialog fragment = new AddEditOfferProductDialog();
        fragment.setOnMyCartListener(onMyCartListener);
        fragment.setType(AppConstants.EDIT);
        fragment.setPosition(position);
        fragment.setAddProduct(product);
        fragment.setProduct(product);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DialogAddOptionProductBinding.inflate(inflater);
        //final View view = inflater.inflate(R.layout.dialog_delete_product, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (type == AppConstants.ADD)
            model = new AddEditOfferProductViewModel(requireContext());
        else
            model = new AddEditOfferProductViewModel(requireContext(), addProduct, product);
        //   binding.setModel(model);
        binding.ivClose.setOnClickListener(v -> dismiss());

        binding.button3.setOnClickListener(v -> {

            if (isValid()) {
                if (type == AppConstants.ADD) {
                    if (onMyCartListener != null)
                        onMyCartListener.onItemClick(model.addProduct, 0);
                } else {
                    if (onMyCartListener != null)
                        onMyCartListener.onEditClick(model.addProduct, position);
                }
                dismiss();
            }
        });


        model.getWeightObserver().observe(requireActivity(), weight -> binding.editWeight.setText(weight));

        model.getQuantityObserver().observe(requireActivity(), quantity -> binding.editQuantity.setText(quantity));

    }

    private boolean isValid() {
        return true;
    }

    @Override
    public boolean onResult(@NonNull String dialogTag, int which, @NonNull Bundle extras) {

        int color = extras.getInt(SimpleColorDialog.COLOR);  // The color chosen
        // int color = extras.getInt(SimpleColorWheelDialog.COLOR);  // The color chosen
        String hexColor = String.format("#%06X", (0xFFFFFF & color));
        binding.tvColor.setText(hexColor);
        model.addProduct.setColor(hexColor);
        return false;
    }
}

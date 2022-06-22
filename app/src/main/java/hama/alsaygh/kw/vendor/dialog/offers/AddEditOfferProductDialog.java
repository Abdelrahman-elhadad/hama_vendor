package hama.alsaygh.kw.vendor.dialog.offers;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.databinding.DialogAddOfferProductBinding;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.addProduct.AddProduct;
import hama.alsaygh.kw.vendor.model.product.Product;
import hama.alsaygh.kw.vendor.utils.AppConstants;
import hama.alsaygh.kw.vendor.view.gongrats.offers.GongratsOfferActivity;


public class AddEditOfferProductDialog extends BottomSheetDialogFragment {

    DialogAddOfferProductBinding binding;
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

    public void setAddProduct(Product product, int type) {
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
        if (type == AppConstants.ADD)
            addProduct.setDiscount("0.0");
        else
            addProduct.setDiscount(product.getDiscount_value() + "");
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

    public static AddEditOfferProductDialog newInstance(Product product, OnGeneralClickListener onMyCartListener) {

        AddEditOfferProductDialog fragment = new AddEditOfferProductDialog();
        fragment.setOnMyCartListener(onMyCartListener);
        fragment.setType(AppConstants.ADD);
        fragment.setProduct(product);
        fragment.setAddProduct(product, AppConstants.ADD);

        return fragment;
    }

    public static AddEditOfferProductDialog newInstance(Product product, int position, OnGeneralClickListener onMyCartListener) {

        AddEditOfferProductDialog fragment = new AddEditOfferProductDialog();
        fragment.setOnMyCartListener(onMyCartListener);
        fragment.setType(AppConstants.EDIT);
        fragment.setPosition(position);
        fragment.setAddProduct(product, AppConstants.EDIT);
        fragment.setProduct(product);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DialogAddOfferProductBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        model = new AddEditOfferProductViewModel(requireContext());
        model.setAddProduct(addProduct);
        model.setProduct(product);
        model.setType(type);
        binding.setModel(model);
        binding.ivClose.setOnClickListener(v -> dismiss());
        binding.tvBack.setOnClickListener(v -> dismiss());

        model.newPriceObservable.observe(requireActivity(), price -> binding.editProductPrice.setText(price));
        model.addProductMutableLiveData.observe(requireActivity(), generalResponse -> {

            binding.button3.setVisibility(View.VISIBLE);
            binding.pbDelete.setVisibility(View.GONE);

            if (generalResponse.isStatus()) {
                if (type == AppConstants.ADD) {

                    startActivity(new Intent(requireContext(), GongratsOfferActivity.class));

                } else {
                    if (onMyCartListener != null)
                        onMyCartListener.onEditClick(model.product, position);
                }
                dismiss();
            } else {
                if (generalResponse.getCode().equalsIgnoreCase("401")) {
                    LoginDialog.newInstance().show(getChildFragmentManager(), "login");
                    dismiss();
                } else
                    Snackbar.make(binding.button3, generalResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }

        });
        binding.button3.setOnClickListener(v ->
        {
            if (isValid()) {
                binding.button3.setVisibility(View.GONE);
                binding.pbDelete.setVisibility(View.VISIBLE);
                model.addEditDiscount(v.getContext());
            }
        });
    }

    private boolean isValid() {
        return true;
    }
}

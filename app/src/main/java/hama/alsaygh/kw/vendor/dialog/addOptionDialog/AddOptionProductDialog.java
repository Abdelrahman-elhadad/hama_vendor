package hama.alsaygh.kw.vendor.dialog.addOptionDialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import eltos.simpledialogfragment.SimpleDialog;
import eltos.simpledialogfragment.color.SimpleColorDialog;
import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.DialogAddOptionProductBinding;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.product.Option;
import hama.alsaygh.kw.vendor.utils.AppConstants;


public class AddOptionProductDialog extends BottomSheetDialogFragment implements SimpleDialog.OnDialogResultListener {

    DialogAddOptionProductBinding binding;
    AddOptionProductViewModel model;
    Option option;

    OnGeneralClickListener onMyCartListener;
    int type = 1;
    int position;

    public void setOption(Option option) {
        this.option = option;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setOnMyCartListener(OnGeneralClickListener onMyCartListener) {
        this.onMyCartListener = onMyCartListener;
    }

    public static AddOptionProductDialog newInstance(OnGeneralClickListener onMyCartListener) {

        AddOptionProductDialog fragment = new AddOptionProductDialog();
        fragment.setOnMyCartListener(onMyCartListener);
        fragment.setType(AppConstants.ADD);
        return fragment;
    }

    public static AddOptionProductDialog newInstance(Option option, int position, OnGeneralClickListener onMyCartListener) {

        AddOptionProductDialog fragment = new AddOptionProductDialog();
        fragment.setOnMyCartListener(onMyCartListener);
        fragment.setType(AppConstants.EDIT);
        fragment.setPosition(position);
        fragment.setOption(option);
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
            model = new AddOptionProductViewModel(requireContext());
        else
            model = new AddOptionProductViewModel(requireContext(), option);
        binding.setModel(model);
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

        binding.tvColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SimpleColorWheelDialog.build()
//                        .color(ContextCompat.getColor(requireContext(), R.color.white))
//                        .alpha(true)
//                        .show(AddOptionProductDialog.this, "COLOR_DIALOG");


                SimpleColorDialog.build()
                        .title(R.string.pick_a_color)
                        .allowCustom(true)
                        .setupColorWheelHideHex(false)
                        .colorPreset(ContextCompat.getColor(requireContext(), R.color.white))
                        .show(AddOptionProductDialog.this, "DIALOG_TAG");
            }
        });

        binding.ivWeightMax.setOnClickListener(v -> {
            model.onMaxWeightClick(v);
        });
        binding.ivWeightMin.setOnClickListener(v -> {
            model.onMinWeightClick(v);
        });

        binding.ivQuantityMax.setOnClickListener(v -> {
            model.onMaxQuantityClick(v);
        });
        binding.ivQuantityMin.setOnClickListener(v -> {
            model.onMinQuantityClick(v);
        });

        model.getWeightObserver().observe(requireActivity(), weight -> binding.editWeight.setText(weight));

        model.getQuantityObserver().observe(requireActivity(), quantity -> binding.editQuantity.setText(quantity));

    }

    public boolean isValid() {
        boolean isValid = true;


        if (model.getAddProduct().getName() == null || model.getAddProduct().getName().isEmpty()) {
            isValid = false;
            binding.editOptionNameEn.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else
            binding.editOptionNameEn.setBackgroundResource(R.drawable.back_spinner);

        if (model.getAddProduct().getNameAr() == null || model.getAddProduct().getNameAr().isEmpty()) {
            isValid = false;
            binding.editOptionNameAr.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else
            binding.editOptionNameAr.setBackgroundResource(R.drawable.back_spinner);


        if (!model.fixedPrice.get() && !model.bindToMarket.get()) {
            isValid = false;
            binding.tvPriceType.setTextColor(ContextCompat.getColor(requireContext(), R.color.back_canceld));
        } else
            binding.tvPriceType.setTextColor(ContextCompat.getColor(requireContext(), R.color.text));

        if (model.fixedPrice.get()) {

            if (model.getAddProduct().getPrice() == 0) {
                isValid = false;
                binding.editProductPrice.setBackgroundResource(R.drawable.back_edit_txt_red);
            } else
                binding.editProductPrice.setBackgroundResource(R.drawable.back_spinner);
        } else {
            binding.editProductPrice.setBackgroundResource(R.drawable.back_spinner);
        }

        if (model.getAddProduct().getAvailable_quantity() == 0) {
            isValid = false;
            binding.editQuantity.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else
            binding.editQuantity.setBackgroundResource(R.drawable.back_spinner);

        if (model.getAddProduct().getTotal_weight() == null || model.getAddProduct().getTotal_weight().isEmpty()
                || model.getAddProduct().getTotal_weight().equalsIgnoreCase("0.0")
                || model.getAddProduct().getTotal_weight().equalsIgnoreCase("0")) {
            isValid = false;
            binding.editWeight.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else
            binding.editWeight.setBackgroundResource(R.drawable.back_spinner);

        return isValid;
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

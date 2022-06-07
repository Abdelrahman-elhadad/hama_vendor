package hama.alsaygh.kw.vendor.view.products.addProduct;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.FragmentAddProductStep1Binding;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.category.Category;
import hama.alsaygh.kw.vendor.model.category.MainCategory;
import hama.alsaygh.kw.vendor.view.base.BaseFragment;

public class AddProductStep1Fragment extends BaseFragment implements OnGeneralClickListener {

    FragmentAddProductStep1Binding binding;
    AddProductViewModel model;

    public static AddProductStep1Fragment newInstance(AddProductViewModel model) {

        AddProductStep1Fragment fragment = new AddProductStep1Fragment();
        fragment.setModel(model);
        return fragment;
    }

    public void setModel(AddProductViewModel model) {
        this.model = model;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddProductStep1Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setModel(model);

        model.getWeightObserver().observe(requireActivity(), weight -> binding.editWeight.setText(weight));

        model.getQuantityObserver().observe(requireActivity(), quantity -> binding.editQuantity.setText(quantity));

        model.getCategoriesObserver().observe(requireActivity(), categoriesResponse -> {

            if (categoriesResponse.isStatus()) {

                ArrayAdapter<Category> arrayAdapter1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, categoriesResponse.getData());
                arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.spCatSub.setAdapter(arrayAdapter1);
                binding.spCatSub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        Category category = arrayAdapter1.getItem(position);
                        model.setCategories(category);
                        setChildCategories(category.getChilds());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            } else {
                if (categoriesResponse.getCode().equalsIgnoreCase("401"))
                    LoginDialog.newInstance().show(getChildFragmentManager(), "login");
                else
                    Snackbar.make(binding.textView2, categoriesResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }

        });

        model.getMainCategoriesObserver().observe(requireActivity(), mainCategoriesResponse -> {
            if (mainCategoriesResponse.isStatus()) {

                ArrayAdapter<MainCategory> arrayAdapter1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, mainCategoriesResponse.getData());
                arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.spCatMain.setAdapter(arrayAdapter1);
                binding.spCatMain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        MainCategory category = arrayAdapter1.getItem(position);
                        model.setMainCategories(category);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


            } else {
                if (mainCategoriesResponse.getCode().equalsIgnoreCase("401"))
                    LoginDialog.newInstance().show(getChildFragmentManager(), "login");
                else
                    Snackbar.make(binding.textView2, mainCategoriesResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });

        model.getMainCategories(requireContext());
        model.getCategories(requireContext());

    }

    @Override
    public void onItemClick(Object object, int position) {

    }

    @Override
    public void onEditClick(Object object, int position) {

    }

    @Override
    public void onDeleteClick(Object object, int position) {

    }

    public void setChildCategories(List<Category> childCategories) {
        model.setChildCategories(null);
        if (childCategories != null && !childCategories.isEmpty()) {
            model.childSubVisibility.set(View.VISIBLE);
            ArrayAdapter<Category> arrayAdapter1 = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, childCategories);
            arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spCatSubSub.setAdapter(arrayAdapter1);
            binding.spCatSubSub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Category category = arrayAdapter1.getItem(position);
                    model.setChildCategories(category);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else
            model.childSubVisibility.set(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        model.getMainCategoriesObserver().removeObservers(requireActivity());
        model.getCategoriesObserver().removeObservers(requireActivity());
        model.getQuantityObserver().removeObservers(requireActivity());
        model.getWeightObserver().removeObservers(requireActivity());
    }

    public boolean isValid() {
        boolean isValid = true;
        if (model.getAddProduct().getName() == null || model.getAddProduct().getName().isEmpty()) {
            isValid = false;
            binding.editProductNameEn.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else
            binding.editProductNameEn.setBackgroundResource(R.drawable.back_spinner);

        if (model.getAddProduct().getName_ar() == null || model.getAddProduct().getName_ar().isEmpty()) {
            isValid = false;
            binding.editProductNameAr.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else
            binding.editProductNameAr.setBackgroundResource(R.drawable.back_spinner);


        if (model.getAddProduct().getDescription() == null || model.getAddProduct().getDescription().isEmpty()) {
            isValid = false;
            binding.editProductDescEn.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else
            binding.editProductDescEn.setBackgroundResource(R.drawable.back_spinner);

        if (model.getAddProduct().getDescription_ar() == null || model.getAddProduct().getDescription_ar().isEmpty()) {
            isValid = false;
            binding.editProductDescAr.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else
            binding.editProductDescAr.setBackgroundResource(R.drawable.back_spinner);


        if (model.getAddProduct().getMain_category() == null) {
            isValid = false;
            binding.viewSpCatMain.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else {
            binding.viewSpCatMain.setBackgroundResource(R.drawable.back_spinner);

            if (model.getAddProduct().getMain_category().getId() == 1 || model.getAddProduct().getMain_category().getId() == 2) {
                if (model.getAddProduct().getManufacture_price() == null || model.getAddProduct().getManufacture_price().isEmpty()) {
                    isValid = false;
                    binding.editManufacturingPrice.setBackgroundResource(R.drawable.back_edit_txt_red);
                } else
                    binding.editManufacturingPrice.setBackgroundResource(R.drawable.back_spinner);

                if (model.getAddProduct().getCaliber() == null) {
                    isValid = false;
                    binding.tvCaliber.setTextColor(ContextCompat.getColor(requireContext(), R.color.back_canceld));
                } else
                    binding.tvCaliber.setTextColor(ContextCompat.getColor(requireContext(), R.color.text));

            } else {
                binding.tvCaliber.setTextColor(ContextCompat.getColor(requireContext(), R.color.text));
                binding.editManufacturingPrice.setBackgroundResource(R.drawable.back_spinner);
            }

        }

        if (model.getAddProduct().getSub_category() == null) {
            isValid = false;
            binding.viewSpCatSub.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else
            binding.viewSpCatSub.setBackgroundResource(R.drawable.back_spinner);


        if (!model.fixedPrice.get() && !model.bindToMarket.get()) {
            isValid = false;
            binding.tvPriceType.setTextColor(ContextCompat.getColor(requireContext(), R.color.back_canceld));
        } else
            binding.tvPriceType.setTextColor(ContextCompat.getColor(requireContext(), R.color.text));

        if (model.fixedPrice.get()) {
            if (model.getAddProduct().getGmPrice() == null || model.getAddProduct().getGmPrice().isEmpty()) {
                isValid = false;
                binding.editGmPrice.setBackgroundResource(R.drawable.back_edit_txt_red);
            } else
                binding.editGmPrice.setBackgroundResource(R.drawable.back_spinner);

            if (model.getAddProduct().getFixed_price() == null || model.getAddProduct().getFixed_price().isEmpty()) {
                isValid = false;
                binding.editProductPrice.setBackgroundResource(R.drawable.back_edit_txt_red);
            } else
                binding.editProductPrice.setBackgroundResource(R.drawable.back_spinner);
        } else {
            binding.editProductPrice.setBackgroundResource(R.drawable.back_spinner);
            binding.editGmPrice.setBackgroundResource(R.drawable.back_spinner);
        }

        return isValid;
    }
}
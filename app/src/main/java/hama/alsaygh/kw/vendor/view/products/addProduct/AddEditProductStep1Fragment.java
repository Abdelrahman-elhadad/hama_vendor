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
import androidx.fragment.app.FragmentManager;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.FragmentAddProductStep1Binding;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.category.Category;
import hama.alsaygh.kw.vendor.model.category.MainCategory;
import hama.alsaygh.kw.vendor.model.product.caliber.Caliber;
import hama.alsaygh.kw.vendor.view.base.BaseFragment;
import hama.alsaygh.kw.vendor.view.products.addProduct.adapter.caliber.CalibersRecycleViewAdapter;

public class AddEditProductStep1Fragment extends BaseFragment implements OnGeneralClickListener {

    FragmentAddProductStep1Binding binding;
    AddEditProductViewModel model;
    FragmentManager fragmentManager;
    public static AddEditProductStep1Fragment newInstance(AddEditProductViewModel model) {

        AddEditProductStep1Fragment fragment = new AddEditProductStep1Fragment();
        fragment.setModel(model);
        return fragment;
    }

    public void setModel(AddEditProductViewModel model) {
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
        fragmentManager = getChildFragmentManager();
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
                        if (category.getId() == 4 || category.getId() == 5) {
                            model.categoriesVisibility.set(View.VISIBLE);
                        } else {
                            model.categoriesVisibility.set(View.GONE);
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                if (model.addProduct.getSub_category() != null)
                    for (int i = 0; i < categoriesResponse.getData().size(); i++) {
                        if (categoriesResponse.getData().get(i).getId() == model.getAddProduct().getSub_category().getId()) {
                            binding.spCatSub.setSelection(i);
                            break;
                        }
                    }


            } else {
                if (categoriesResponse.getCode().equalsIgnoreCase("401"))
                    LoginDialog.newInstance().show(fragmentManager, "login");
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
                if (model.addProduct.getMain_category() != null)
                    for (int i = 0; i < mainCategoriesResponse.getData().size(); i++) {
                        if (mainCategoriesResponse.getData().get(i).getId() == model.getAddProduct().getMain_category().getId()) {
                            binding.spCatMain.setSelection(i);
                            break;
                        }
                    }

            } else {
                if (mainCategoriesResponse.getCode().equalsIgnoreCase("401"))
                    LoginDialog.newInstance().show(fragmentManager, "login");
                else
                    Snackbar.make(binding.textView2, mainCategoriesResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
        model.getCalibersObserver().observe(requireActivity(), mainCategoriesResponse -> {
            if (mainCategoriesResponse.isStatus()) {

                CalibersRecycleViewAdapter adapter = new CalibersRecycleViewAdapter(mainCategoriesResponse.getData(), AddEditProductStep1Fragment.this);
                binding.rvCaliber.setAdapter(adapter);

                if (model.getAddProduct().getCaliber() != null)
                    for (int i = 0; i < mainCategoriesResponse.getData().size(); i++) {
                        if (mainCategoriesResponse.getData().get(i).getId() == model.getAddProduct().getCaliber().getId()) {
                            adapter.setSelectedWithoutClick(i);
                            break;
                        }
                    }


            } else {
                if (mainCategoriesResponse.getCode().equalsIgnoreCase("401"))
                    LoginDialog.newInstance().show(fragmentManager, "login");
                else
                    Snackbar.make(binding.textView2, mainCategoriesResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });

        model.getMainCategories(requireContext());
        model.getCategories(requireContext());
        model.getCaliberss(requireContext());

    }

    @Override
    public void onItemClick(Object object, int position) {
        if (object instanceof Caliber) {
            model.getAddProduct().setCaliber((Caliber) object);
        }
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

            if (model.addProduct.getChild_sub_category() != null)
                for (int i = 0; i < childCategories.size(); i++) {
                    if (childCategories.get(i).getId() == model.getAddProduct().getChild_sub_category().getId()) {
                        binding.spCatSubSub.setSelection(i);
                        break;
                    }
                }
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

        if (model.getAddProduct().getCode() == null || model.getAddProduct().getCode().isEmpty()) {
            isValid = false;
            binding.editCode.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else
            binding.editCode.setBackgroundResource(R.drawable.back_spinner);

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
        }

        if (model.getAddProduct().getSub_category() == null) {
            isValid = false;
            binding.viewSpCatSub.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else {

            // if (model.getAddProduct().getSub_category().getId() == 4 || model.getAddProduct().getSub_category().getId() == 5) {
//                if (model.getAddProduct().getManufacture_price() == null || model.getAddProduct().getManufacture_price().isEmpty()) {
//                    isValid = false;
//                    binding.editManufacturingPrice.setBackgroundResource(R.drawable.back_edit_txt_red);
//                } else
//                    binding.editManufacturingPrice.setBackgroundResource(R.drawable.back_spinner);
//
//                if (model.getAddProduct().getCaliber() == null) {
//                    isValid = false;
//                    binding.tvCaliber.setTextColor(ContextCompat.getColor(requireContext(), R.color.back_canceld));
//                } else
//                    binding.tvCaliber.setTextColor(ContextCompat.getColor(requireContext(), R.color.text));

            //  } else
            {
                binding.tvCaliber.setTextColor(ContextCompat.getColor(requireContext(), R.color.text));
                binding.editManufacturingPrice.setBackgroundResource(R.drawable.back_spinner);
            }

            binding.viewSpCatSub.setBackgroundResource(R.drawable.back_spinner);
        }


        if (!model.fixedPrice.get() && !model.bindToMarket.get()) {
            isValid = false;
            binding.tvPriceType.setTextColor(ContextCompat.getColor(requireContext(), R.color.back_canceld));
        } else
            binding.tvPriceType.setTextColor(ContextCompat.getColor(requireContext(), R.color.text));

        if (model.fixedPrice.get()) {

            if (model.getAddProduct().getDiscount() == null || model.getAddProduct().getDiscount().isEmpty()) {
                isValid = false;
                binding.editProductPriceDiscount.setBackgroundResource(R.drawable.back_edit_txt_red);
                model.discountVisibility.set(View.VISIBLE);
            } else {
                double dic = Double.parseDouble(model.getAddProduct().getDiscount());
                if (dic > 100) {
                    isValid = false;
                    binding.editProductPriceDiscount.setBackgroundResource(R.drawable.back_edit_txt_red);
                    model.discountVisibility.set(View.VISIBLE);
                } else {
                    binding.editProductPriceDiscount.setBackgroundResource(R.drawable.back_spinner);
                    model.discountVisibility.set(View.GONE);
                }
            }
            if (model.getAddProduct().getFixed_price() == null || model.getAddProduct().getFixed_price().isEmpty()) {
                isValid = false;
                binding.editProductPrice.setBackgroundResource(R.drawable.back_edit_txt_red);
            } else
                binding.editProductPrice.setBackgroundResource(R.drawable.back_spinner);
        } else {
            binding.editProductPrice.setBackgroundResource(R.drawable.back_spinner);
            binding.editProductPriceDiscount.setBackgroundResource(R.drawable.back_spinner);
            model.discountVisibility.set(View.GONE);
        }

        if (model.getAddProduct().getQuantity() == null || model.getAddProduct().getQuantity().isEmpty()
                || model.getAddProduct().getQuantity().equalsIgnoreCase("0.0")
                || model.getAddProduct().getQuantity().equalsIgnoreCase("0")) {
            isValid = false;
            binding.editQuantity.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else
            binding.editQuantity.setBackgroundResource(R.drawable.back_spinner);

        if (model.getAddProduct().getWeight() == null || model.getAddProduct().getWeight().isEmpty()
                || model.getAddProduct().getWeight().equalsIgnoreCase("0.0")
                || model.getAddProduct().getWeight().equalsIgnoreCase("0")) {
            isValid = false;
            binding.editWeight.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else
            binding.editWeight.setBackgroundResource(R.drawable.back_spinner);


        if (model.getAddProduct().getTotalWeightMetal() == null || model.getAddProduct().getTotalWeightMetal().isEmpty()) {
            isValid = false;
            binding.editTotalMetalGm.setBackgroundResource(R.drawable.back_edit_txt_red);
        } else
            binding.editTotalMetalGm.setBackgroundResource(R.drawable.back_spinner);

        return isValid;
    }
}
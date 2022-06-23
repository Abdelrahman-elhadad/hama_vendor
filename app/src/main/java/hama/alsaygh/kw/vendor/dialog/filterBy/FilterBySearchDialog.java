package hama.alsaygh.kw.vendor.dialog.filterBy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.dialog.filterBy.adapter.AdapterProductFilter;
import hama.alsaygh.kw.vendor.listener.OnFilterDialogListener;
import hama.alsaygh.kw.vendor.model.category.CategoriesResponse;
import hama.alsaygh.kw.vendor.model.category.MainCategoriesResponse;
import hama.alsaygh.kw.vendor.model.category.MainCategory;
import hama.alsaygh.kw.vendor.model.category.SearchCategoriesResponse;
import hama.alsaygh.kw.vendor.repo.GeneralRepo;
import hama.alsaygh.kw.vendor.repo.ProfileRepo;
import hama.alsaygh.kw.vendor.utils.AppConstants;
import hama.alsaygh.kw.vendor.view.search.SearchCategoriesProductActivity;


public class FilterBySearchDialog extends BottomSheetDialogFragment implements OnFilterDialogListener {


    ImageView icClose;
    TextView tv_clear_all;
    RecyclerView rvFilter;
    AppCompatButton but_result;

    MutableLiveData<SearchCategoriesResponse> categoriesSearchMutableLiveData = new MutableLiveData<>();
    MutableLiveData<MainCategoriesResponse> mainCategoriesMutableLiveData = new MutableLiveData<>();
    MutableLiveData<CategoriesResponse> categoriesMutableLiveData = new MutableLiveData<>();

    String search;
    private final List<MainCategory> categories = new ArrayList<>();
    int categoryId = -1, subCategoryId = -1, mainCategoryId = -3;
    private AdapterProductFilter adapterProductFilter;

    public void setSearch(String search) {
        this.search = search;
    }


    public static FilterBySearchDialog newInstance(String search) {

        FilterBySearchDialog fragment = new FilterBySearchDialog();
        fragment.setSearch(search);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.dialog_filter_by, container, false);
        icClose = view.findViewById(R.id.iv_close);
        tv_clear_all = view.findViewById(R.id.tv_clear_all);
        rvFilter = view.findViewById(R.id.rv_filter);
        but_result = view.findViewById(R.id.but_result);
        icClose.setOnClickListener(v -> dismiss());


        categoriesSearchMutableLiveData.observe(requireActivity(), mainCategoriesResponse -> {

            if (mainCategoriesResponse.isStatus()) {
                adapterProductFilter.addCategories(mainCategoriesResponse.getCategories());
            } else {
                if (mainCategoriesResponse.getCode().equalsIgnoreCase("401")) {
                    LoginDialog.newInstance().show(getChildFragmentManager(), "login");
                } else
                    Snackbar.make(rvFilter, mainCategoriesResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }

        });

        categoriesMutableLiveData.observe(requireActivity(), mainCategoriesResponse -> {

            if (mainCategoriesResponse.isStatus()) {
                adapterProductFilter.addCategories(mainCategoriesResponse.getData());
            } else {
                if (mainCategoriesResponse.getCode().equalsIgnoreCase("401")) {
                    LoginDialog.newInstance().show(getChildFragmentManager(), "login");
                } else
                    Snackbar.make(rvFilter, mainCategoriesResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }

        });

        mainCategoriesMutableLiveData.observe(requireActivity(), mainCategoriesResponse -> {


            if (mainCategoriesResponse.isStatus()) {
                categories.addAll(0, mainCategoriesResponse.getData());
                adapterProductFilter.setMainCategories(categories);
            } else {
                if (mainCategoriesResponse.getCode().equalsIgnoreCase("401")) {
                    LoginDialog.newInstance().show(getChildFragmentManager(), "login");
                } else
                    Snackbar.make(rvFilter, mainCategoriesResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }

        });

        if (search != null && !search.isEmpty()) {
            new ProfileRepo().getSearchLogsCategories(requireContext(), search, categoriesSearchMutableLiveData);

            MainCategory category = new MainCategory();
            category.setId(-3);
            category.setName(getString(R.string.Categories));
            categories.add(category);

        } else {
            new GeneralRepo().getMainCategories(requireContext(), mainCategoriesMutableLiveData);
            new GeneralRepo().getCategories(requireContext(), categoriesMutableLiveData);
        }

        adapterProductFilter = new AdapterProductFilter(requireContext(), categories, new ArrayList<>(), "", mainCategoryId, categoryId, subCategoryId, "", "", this);
        rvFilter.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvFilter.setAdapter(adapterProductFilter);

        tv_clear_all.setVisibility(View.GONE);
        but_result.setOnClickListener(v -> {

            Intent intent = new Intent(v.getContext(), SearchCategoriesProductActivity.class);
            if (categoryId <= 3)
                intent.putExtra(AppConstants.CAT_LEVEL_1, categoryId);
            else if (categoryId < 9)
                intent.putExtra(AppConstants.CAT_LEVEL_2, categoryId);
            else
                intent.putExtra(AppConstants.CAT_LEVEL_3, categoryId);

            intent.putExtra(AppConstants.CAT_LEVEL_3, subCategoryId);
            startActivity(intent);

            dismiss();

        });

        return view;
    }

    @Override
    public void onTypeOfPriceClick(String type_of_price) {

    }

    @Override
    public void onCategoryLevel1Click(int category_level_1) {
        this.mainCategoryId = category_level_1;
        if (adapterProductFilter != null)
            adapterProductFilter.setCategory_level1(mainCategoryId);
    }

    @Override
    public void onCategoryLevel2Click(int category_level_2) {
        this.categoryId = category_level_2;
        if (adapterProductFilter != null)
            adapterProductFilter.setCategory_level2(categoryId);
    }

    @Override
    public void onCategoryLevel3Click(int category_level_3) {
        this.subCategoryId = category_level_3;
        if (adapterProductFilter != null)
            adapterProductFilter.setCategory_level3(subCategoryId);
    }

    @Override
    public void onRangePriceFromClick(String range_price_from) {

    }

    @Override
    public void onRangePriceToClick(String range_price_to) {
    }
}

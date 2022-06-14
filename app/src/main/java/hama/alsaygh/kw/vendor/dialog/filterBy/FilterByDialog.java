package hama.alsaygh.kw.vendor.dialog.filterBy;

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
import hama.alsaygh.kw.vendor.listener.OnFilterListener;
import hama.alsaygh.kw.vendor.model.category.CategoriesResponse;
import hama.alsaygh.kw.vendor.model.category.MainCategoriesResponse;
import hama.alsaygh.kw.vendor.model.category.MainCategory;
import hama.alsaygh.kw.vendor.repo.GeneralRepo;


public class FilterByDialog extends BottomSheetDialogFragment implements OnFilterDialogListener {


    ImageView icClose;
    TextView tv_clear_all;
    RecyclerView rvFilter;
    AppCompatButton but_result;

    OnFilterListener onMyCartListener;
    MutableLiveData<MainCategoriesResponse> mainCategoriesMutableLiveData = new MutableLiveData<>();
    MutableLiveData<CategoriesResponse> categoriesMutableLiveData = new MutableLiveData<>();

    private String type_of_price = "", rangeFrom = "", rangeTo = "";
    private int category_level1 = -1, category_level2 = -1, category_level3 = -1;
    private final List<MainCategory> categories = new ArrayList<>();
    private AdapterProductFilter adapterProductFilter;

    public void setOnMyCartListener(OnFilterListener onMyCartListener) {
        this.onMyCartListener = onMyCartListener;
    }


    public static FilterByDialog newInstance(String type_of_price, int category_level1, int category_level2, int category_level3, String rangeFrom, String rangeTo, OnFilterListener onMyCartListener) {

        FilterByDialog fragment = new FilterByDialog();
        fragment.setOnMyCartListener(onMyCartListener);
        fragment.setFilter(type_of_price, category_level1, category_level2, category_level3, rangeFrom, rangeTo);
        return fragment;
    }

    public void setFilter(String type_of_price, int category_level1, int category_level2, int category_level3, String rangeFrom, String rangeTo) {
        this.type_of_price = type_of_price;
        this.category_level1 = category_level1;
        this.category_level2 = category_level2;
        this.category_level3 = category_level3;
        this.rangeFrom = rangeFrom;
        this.rangeTo = rangeTo;
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

        MainCategory category = new MainCategory();
        category.setId(-1);
        category.setName(getString(R.string.price));
        categories.add(category);
        MainCategory category2 = new MainCategory();
        category2.setId(-2);
        category2.setName(getString(R.string.FILTER_BY4));
        categories.add(category2);

        adapterProductFilter = new AdapterProductFilter(requireContext(), categories, new ArrayList<>(), type_of_price, category_level1, category_level2, category_level3, rangeFrom, rangeTo, this);
        rvFilter.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvFilter.setAdapter(adapterProductFilter);
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

        new GeneralRepo().getMainCategories(requireContext(), mainCategoriesMutableLiveData);
        new GeneralRepo().getCategories(requireContext(), categoriesMutableLiveData);

        tv_clear_all.setOnClickListener(v -> {

            type_of_price = "";
            category_level1 = -1;
            category_level2 = -1;
            category_level3 = -1;
            rangeFrom = "";
            rangeTo = "";
            if (adapterProductFilter != null) {
                adapterProductFilter.setType_of_price("");
                adapterProductFilter.setCategory_level1(-1);
                adapterProductFilter.setCategory_level2(-1);
                adapterProductFilter.setCategory_level3(-1);
                adapterProductFilter.setRangeFrom("");
                adapterProductFilter.setRangeTo("");
                adapterProductFilter.notifyDataSetChanged();
            }

            if (onMyCartListener != null)
                onMyCartListener.onFilterClick("", -1, -1, -1, "", "");

        });
        but_result.setOnClickListener(v -> {
            if (onMyCartListener != null)
                onMyCartListener.onFilterClick(type_of_price, category_level1, category_level2, category_level3, rangeFrom, rangeTo);

            dismiss();

        });

        return view;
    }

    @Override
    public void onTypeOfPriceClick(String type_of_price) {

        this.type_of_price = type_of_price;
        if (adapterProductFilter != null)
            adapterProductFilter.setType_of_price(type_of_price);
    }

    @Override
    public void onCategoryLevel1Click(int category_level_1) {
        this.category_level1 = category_level_1;
        if (adapterProductFilter != null)
            adapterProductFilter.setCategory_level1(category_level1);
    }

    @Override
    public void onCategoryLevel2Click(int category_level_2) {
        this.category_level2 = category_level_2;
        if (adapterProductFilter != null)
            adapterProductFilter.setCategory_level2(category_level2);
    }

    @Override
    public void onCategoryLevel3Click(int category_level_3) {
        this.category_level3 = category_level_3;
        if (adapterProductFilter != null)
            adapterProductFilter.setCategory_level3(category_level3);
    }

    @Override
    public void onRangePriceFromClick(String range_price_from) {
        rangeFrom = range_price_from;
        if (adapterProductFilter != null)
            adapterProductFilter.setRangeFrom(rangeFrom);
    }

    @Override
    public void onRangePriceToClick(String range_price_to) {
        rangeTo = range_price_to;
        if (adapterProductFilter != null)
            adapterProductFilter.setRangeTo(rangeTo);
    }
}

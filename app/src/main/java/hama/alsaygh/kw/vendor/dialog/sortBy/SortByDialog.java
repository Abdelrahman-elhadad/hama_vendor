package hama.alsaygh.kw.vendor.dialog.sortBy;

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

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.dialog.sortBy.adapter.AdapterProductSort;
import hama.alsaygh.kw.vendor.listener.OnFilterListener;
import hama.alsaygh.kw.vendor.listener.OnSortDialogListener;
import hama.alsaygh.kw.vendor.model.sort.FilterProductResponse;
import hama.alsaygh.kw.vendor.repo.GeneralRepo;


public class SortByDialog extends BottomSheetDialogFragment implements OnSortDialogListener {


    ImageView icClose;
    TextView tv_clear_all, tv_title;
    RecyclerView rvFilter;
    AppCompatButton but_result;

    OnFilterListener onMyCartListener;
    MutableLiveData<FilterProductResponse> mainCategoriesMutableLiveData = new MutableLiveData<>();

    private String sortBy = "";
    private AdapterProductSort adapterProductFilter;

    public void setOnMyCartListener(OnFilterListener onMyCartListener) {
        this.onMyCartListener = onMyCartListener;
    }


    public static SortByDialog newInstance(String sortBy, OnFilterListener onMyCartListener) {

        SortByDialog fragment = new SortByDialog();
        fragment.setOnMyCartListener(onMyCartListener);
        fragment.setFilter(sortBy);
        return fragment;
    }

    public void setFilter(String type_of_price) {
        this.sortBy = type_of_price;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.dialog_filter_by, container, false);
        icClose = view.findViewById(R.id.iv_close);
        tv_clear_all = view.findViewById(R.id.tv_clear_all);
        tv_title = view.findViewById(R.id.textView9);
        rvFilter = view.findViewById(R.id.rv_filter);
        but_result = view.findViewById(R.id.but_result);
        icClose.setOnClickListener(v -> dismiss());
        tv_title.setText(getString(R.string.sort_by));

        adapterProductFilter = new AdapterProductSort(requireContext(), new ArrayList<>(), sortBy, this);
        rvFilter.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvFilter.setAdapter(adapterProductFilter);
        mainCategoriesMutableLiveData.observe(requireActivity(), mainCategoriesResponse -> {

            if (mainCategoriesResponse.isStatus()) {
                adapterProductFilter = new AdapterProductSort(requireContext(), mainCategoriesResponse.getData(), sortBy, this);
                rvFilter.setAdapter(adapterProductFilter);

            } else {
                if (mainCategoriesResponse.getCode().equalsIgnoreCase("401")) {
                    LoginDialog.newInstance().show(getChildFragmentManager(), "login");
                } else
                    Snackbar.make(rvFilter, mainCategoriesResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }

        });

        new GeneralRepo().getSort(requireContext(), mainCategoriesMutableLiveData);

        tv_clear_all.setOnClickListener(v -> {

            sortBy = "";

            if (adapterProductFilter != null) {
                adapterProductFilter.setSortBy("");
                adapterProductFilter.notifyDataSetChanged();
            }
            if (onMyCartListener != null)
                onMyCartListener.onSortClickClick("");

        });
        but_result.setOnClickListener(v -> {
            if (onMyCartListener != null)
                onMyCartListener.onSortClickClick(sortBy);

            dismiss();

        });

        return view;
    }


    @Override
    public void onSortByClick(String sortBy) {
        this.sortBy = sortBy;
    }
}

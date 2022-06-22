package hama.alsaygh.kw.vendor.view.search;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import hama.alsaygh.kw.vendor.databinding.ActivitySearchBinding;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.searchLog.SearchLog;
import hama.alsaygh.kw.vendor.utils.AppConstants;
import hama.alsaygh.kw.vendor.view.base.BaseActivity;
import hama.alsaygh.kw.vendor.view.search.adapter.AdapterSearchLog;

public class SearchActivity extends BaseActivity implements OnGeneralClickListener {
    ActivitySearchBinding binding;
    SearchViewModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new SearchViewModel(this);
        binding.setModel(model);
        binding.imgBack.setOnClickListener(v -> finish());
        binding.tvClearLog.setOnClickListener(v -> {
            AdapterSearchLog adapterSearchLog = new AdapterSearchLog(this, new ArrayList<>());
            binding.rvSearchLog.setAdapter(adapterSearchLog);
            model.deleteSearchLog(this);

        });
        binding.editText3.setOnCloseListener(() -> {
            binding.editText3.setQuery("", false);
            return false;
        });
        binding.editText3.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                int position = binding.spinner.getSelectedItemPosition();
                switch (position) {

                    case 0:
                        openProduct(query);
                        break;
                }


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        binding.clSearchProduct.setOnClickListener(v -> {

            openProduct("");
        });


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, model.arrayForSpinner);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(arrayAdapter);

        model.getSearchLogObserver().observe(this, searchLogsResponse -> {
            if (searchLogsResponse.isStatus()) {
                AdapterSearchLog adapterSearchLog;
                if (searchLogsResponse.getData() != null && !searchLogsResponse.getData().isEmpty())
                    adapterSearchLog = new AdapterSearchLog(this, searchLogsResponse.getData());
                else
                    adapterSearchLog = new AdapterSearchLog(this, new ArrayList<>());
                binding.rvSearchLog.setAdapter(adapterSearchLog);
                binding.rvSearchLog.setLayoutManager(new LinearLayoutManager(this));
            } else {
                if (searchLogsResponse.getCode().equalsIgnoreCase("401"))
                    LoginDialog.newInstance().show(getSupportFragmentManager(), "Login");
                else
                    Snackbar.make(binding.view, searchLogsResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        model.getSearchLog(this);
    }

    @Override
    public void onItemClick(Object object, int position) {

        if (object instanceof SearchLog) {
            SearchLog searchLog = (SearchLog) object;
            if (searchLog.getType().equalsIgnoreCase("products")) {
                openProduct(searchLog.getKey());
            }

        }
    }

    @Override
    public void onEditClick(Object object, int position) {

    }

    @Override
    public void onDeleteClick(Object object, int position) {

    }

    private void openProduct(String s) {

        Intent intent = new Intent(this, SearchResultProductActivity.class);
        intent.putExtra(AppConstants.SEARCH, s);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        model.getSearchLogObserver().removeObservers(this);
    }
}
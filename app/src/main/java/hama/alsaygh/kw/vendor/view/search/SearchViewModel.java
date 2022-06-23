package hama.alsaygh.kw.vendor.view.search;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.model.searchLog.ProductsSearchResponse;
import hama.alsaygh.kw.vendor.model.searchLog.SearchLogsResponse;
import hama.alsaygh.kw.vendor.repo.ProfileRepo;

public class SearchViewModel extends ViewModel {


    private final Context context;


    private final MutableLiveData<SearchLogsResponse> searchLogMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<ProductsSearchResponse> searchProductMutableLiveData = new MutableLiveData<>();

    String[] arrayForSpinner;
    ProfileRepo productRepo;


    public SearchViewModel(Context context) {
        this.context = context;
        productRepo = new ProfileRepo();

        arrayForSpinner = new String[]{context.getString(R.string.Categories), context.getString(R.string.products)};

    }

    public MutableLiveData<SearchLogsResponse> getSearchLogObserver() {
        return searchLogMutableLiveData;
    }

    public MutableLiveData<ProductsSearchResponse> getSearchProductObserver() {
        return searchProductMutableLiveData;
    }

    public void deleteSearchLog(Context context) {
        productRepo.deleteSearchLogs(context, null);
    }

    public void getSearchLog(Context context) {
        productRepo.getSearchLogs(context, searchLogMutableLiveData);
    }

    public void getSearchLogProduct(Context context, String search) {
        productRepo.getSearchLogsProduct(context, search, searchProductMutableLiveData);
    }


}

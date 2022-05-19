package hama.alsaygh.kw.vendor.view.terms;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.model.page.PageResponse;
import hama.alsaygh.kw.vendor.repo.GeneralRepo;

public class TermsViewModel extends ViewModel {

    private final String TAG = "TermsViewModel";

    MutableLiveData<PageResponse> languageResponseMutableLiveData;
    MutableLiveData<String> titleMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> descMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> UpdatedMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<PageResponse> getObserver() {
        if (languageResponseMutableLiveData == null)
            languageResponseMutableLiveData = new MutableLiveData<>();
        return languageResponseMutableLiveData;
    }

    public void getTerms(Context context) {
        new GeneralRepo().getTerms(context, languageResponseMutableLiveData);
    }

    public TermsViewModel() {

    }

    public MutableLiveData<String> getTitleMutableLiveData() {
        return titleMutableLiveData;
    }

    public MutableLiveData<String> getDescMutableLiveData() {
        return descMutableLiveData;
    }

    public MutableLiveData<String> getUpdatedMutableLiveData() {
        return UpdatedMutableLiveData;
    }

    public void setTitleMutableLiveData(String title) {
        this.titleMutableLiveData.setValue(title);
    }

    public void setDescMutableLiveData(String title) {
        this.descMutableLiveData.setValue(title);
    }

    public void setUpdatedMutableLiveData(String title) {
        UpdatedMutableLiveData.setValue(title);
    }
}

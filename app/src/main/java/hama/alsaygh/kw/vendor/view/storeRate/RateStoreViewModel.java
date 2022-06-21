package hama.alsaygh.kw.vendor.view.storeRate;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.model.review.ReviewsResponse;
import hama.alsaygh.kw.vendor.repo.ProfileRepo;

public class RateStoreViewModel extends ViewModel {

    private final String TAG = "RecommendationViewModel";

    private final ProfileRepo authRepo;

    private MutableLiveData<ReviewsResponse> loginResponseMutableLiveData;


    public RateStoreViewModel() {

        authRepo = new ProfileRepo();
    }

    public MutableLiveData<ReviewsResponse> getObservable() {
        if (loginResponseMutableLiveData == null)
            loginResponseMutableLiveData = new MutableLiveData<>();

        return loginResponseMutableLiveData;
    }


    public void getReviews(Context context, int page) {
        authRepo.getStoreRate(context, page, loginResponseMutableLiveData);

    }


}

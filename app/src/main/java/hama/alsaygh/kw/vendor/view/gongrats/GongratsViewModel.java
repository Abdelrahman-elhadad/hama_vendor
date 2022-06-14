package hama.alsaygh.kw.vendor.view.gongrats;

import android.content.Intent;
import android.view.View;

import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.view.home.HomeActivity;

public class GongratsViewModel extends ViewModel {

    private final String TAG = "LoginActivityViewModel";


    public GongratsViewModel() {
    }


    public void onHomeClickClick(View view) {
        Intent intent = new Intent(view.getContext(), HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        view.getContext().startActivity(intent);
    }
}

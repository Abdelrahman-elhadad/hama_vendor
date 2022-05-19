package hama.alsaygh.kw.vendor.view.more;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.utils.SharedPreferenceConstant;
import hama.alsaygh.kw.vendor.view.generalSettings.GeneralSettingsActivity;

public class MoreViewModel extends ViewModel {

    private final String TAG = "LoginActivityViewModel";
    private Context context;

    public MoreViewModel(Context context) {
        this.context = context;
    }

    public String getName() {
        return context.getString(R.string.store_name).replace("xxx", SharedPreferenceConstant.getSharedPreferenceUser(context).getName());
    }

    public String getEmail() {
        return  SharedPreferenceConstant.getSharedPreferenceUser(context).getEmail();
    }

    public String getLogo() {
        return  SharedPreferenceConstant.getSharedPreferenceUser(context).getLogo();
    }

    public void onMySalesClick(View view) {

    }

    public void onOrderTrackingClick(View view) {

    }

    public void onOrderRateClick(View view) {

    }
    public void onGeneralSettingsClick(View view) {

        Intent intent = new Intent(view.getContext(), GeneralSettingsActivity.class);
        view.getContext().startActivity(intent);
    }

    public void onHamaRateClick(View view) {

    }

    public void onTermsClick(View view) {

    }
     public void onContactHamaClick(View view) {

    }
     public void onRecommendationHamaClick(View view) {

    }
    public void onAppointmentHamaClick(View view) {

    }
    public void onLogoutClick(View view) {

    }
    public void onEditProfileClick(View view) {

    }

}

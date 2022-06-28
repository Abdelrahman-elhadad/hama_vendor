package hama.alsaygh.kw.vendor.view.more;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.utils.SharedPreferenceConstant;
import hama.alsaygh.kw.vendor.utils.Utils;
import hama.alsaygh.kw.vendor.view.appointment.AppointmentBookingActivity;
import hama.alsaygh.kw.vendor.view.contactUs.ContactUsActivity;
import hama.alsaygh.kw.vendor.view.generalSettings.GeneralSettingsActivity;
import hama.alsaygh.kw.vendor.view.mySales.MySalesActivity;
import hama.alsaygh.kw.vendor.view.profile.ProfileActivity;
import hama.alsaygh.kw.vendor.view.recommendation.RecommendationActivity;
import hama.alsaygh.kw.vendor.view.storeRate.StoreRateActivity;
import hama.alsaygh.kw.vendor.view.terms.TermsActivity;

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
        return SharedPreferenceConstant.getSharedPreferenceUser(context).getEmail();
    }

    public String getLogo() {
        return SharedPreferenceConstant.getSharedPreferenceUser(context).getLogo();
    }

    public void onMySalesClick(View view) {
        Intent intent = new Intent(view.getContext(), MySalesActivity.class);
        view.getContext().startActivity(intent);
    }

    public void onOrderTrackingClick(View view) {

    }

    public void onOrderRateClick(View view) {
        Intent intent = new Intent(view.getContext(), StoreRateActivity.class);
        view.getContext().startActivity(intent);
    }

    public void onGeneralSettingsClick(View view) {

        Intent intent = new Intent(view.getContext(), GeneralSettingsActivity.class);
        view.getContext().startActivity(intent);
    }

    public void onHamaRateClick(View view) {
//        Intent intent = new Intent(view.getContext(), RateHamaActivity.class);
//        view.getContext().startActivity(intent);

        final String appPackageName = view.getContext().getPackageName(); // getPackageName() from Context or Activity object
        try {
            view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    public void onTermsClick(View view) {
        Intent intent = new Intent(view.getContext(), TermsActivity.class);
        view.getContext().startActivity(intent);
    }

    public void onContactHamaClick(View view) {
        Intent intent = new Intent(view.getContext(), ContactUsActivity.class);
        view.getContext().startActivity(intent);
    }

    public void onRecommendationHamaClick(View view) {
        Intent intent = new Intent(view.getContext(), RecommendationActivity.class);
        view.getContext().startActivity(intent);

    }

    public void onAppointmentHamaClick(View view) {
        Intent intent = new Intent(view.getContext(), AppointmentBookingActivity.class);
        view.getContext().startActivity(intent);
    }

    public void onLogoutClick(View view) {

        Utils.getInstance().logOut(view.getContext());
    }

    public void onEditProfileClick(View view) {
        Intent intent = new Intent(view.getContext(), ProfileActivity.class);
        view.getContext().startActivity(intent);
    }

}

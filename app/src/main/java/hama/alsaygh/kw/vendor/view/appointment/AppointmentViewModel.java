package hama.alsaygh.kw.vendor.view.appointment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import hama.alsaygh.kw.vendor.model.appointment.Appointment;
import hama.alsaygh.kw.vendor.model.appointment.AppointmentResponse;
import hama.alsaygh.kw.vendor.model.contactUs.ContactUsResponse;
import hama.alsaygh.kw.vendor.model.user.User;
import hama.alsaygh.kw.vendor.repo.GeneralRepo;
import hama.alsaygh.kw.vendor.utils.SharedPreferenceConstant;

public class AppointmentViewModel extends ViewModel {

    private final String TAG = "AppointmentViewModel";
    private final ObservableInt loginVisibility = new ObservableInt();
    private final ObservableInt pbLoginVisibility = new ObservableInt();
    MutableLiveData<AppointmentResponse> languageResponseMutableLiveData;
    MutableLiveData<ContactUsResponse> contactUsResponseMutableLiveData;
    Appointment appointment = new Appointment();
    String msg = "";

    public AppointmentViewModel() {

        appointment.setAppointments_desc("");
        appointment.setAppointments_title("");
        appointment.setEmail("");
        appointment.setPhone("");
        appointment.setWorktime("");
        loginVisibility.set(View.VISIBLE);
        pbLoginVisibility.set(View.GONE);
    }

    public MutableLiveData<AppointmentResponse> getObserver() {
        if (languageResponseMutableLiveData == null)
            languageResponseMutableLiveData = new MutableLiveData<>();
        return languageResponseMutableLiveData;
    }

    public MutableLiveData<ContactUsResponse> getContactUsObserver() {
        if (contactUsResponseMutableLiveData == null)
            contactUsResponseMutableLiveData = new MutableLiveData<>();
        return contactUsResponseMutableLiveData;
    }

    public void getAppointment(Context context) {
        new GeneralRepo().getAppointment(context, languageResponseMutableLiveData);
    }

    public void makeAppointment(Context context) {
        User user = SharedPreferenceConstant.getSharedPreferenceUser(context);
        final String phone = user.getMobile();
        final String subject = appointment.getAppointments_title();
        final String name = user.getName();
        final String email = user.getEmail();
        new GeneralRepo().makeAppointment(context, name, email, phone, subject, msg, contactUsResponseMutableLiveData);
    }


    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public String getTitle() {
        return appointment.getAppointments_title();
    }

    public Spanned getDesc() {
        return Html.fromHtml(appointment.getAppointments_desc(), Html.FROM_HTML_MODE_LEGACY);
    }

    public String getPhone() {
        return appointment.getPhone();
    }

    public String getEmail() {
        return appointment.getEmail();
    }

    public String getWorkingTime() {
        return appointment.getWorktime();
    }

    public void onPhoneClick(View view) {
        try {
            String phone = appointment.getPhone();
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
            view.getContext().startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onEmailClick(View view) {
        try {
            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

            String[] aEmailList = {appointment.getEmail()};
            String[] aEmailCCList = {appointment.getEmail()};
            String[] aEmailBCCList = {appointment.getEmail()};

            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);
            emailIntent.putExtra(android.content.Intent.EXTRA_CC, aEmailCCList);
            emailIntent.putExtra(android.content.Intent.EXTRA_BCC, aEmailBCCList);

            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
            emailIntent.setType("plain/text");
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "");
            view.getContext().startActivity(emailIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onBookingClick(View view) {

        if (msg != null && !msg.isEmpty()) {
            makeAppointment(view.getContext());
        }
    }

    public TextWatcher msgTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                msg = s.toString();
            }
        };
    }

    public ObservableInt getLoginVisibility() {
        return loginVisibility;
    }

    public ObservableInt getPbLoginVisibility() {
        return pbLoginVisibility;
    }

    public void setLoginVisibility(int loginVisibility) {
        this.loginVisibility.set(loginVisibility);
    }

    public void setPbLoginVisibility(int loginVisibility) {
        this.pbLoginVisibility.set(loginVisibility);
    }
}

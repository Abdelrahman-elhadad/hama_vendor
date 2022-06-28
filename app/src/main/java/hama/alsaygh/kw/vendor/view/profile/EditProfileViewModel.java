package hama.alsaygh.kw.vendor.view.profile;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.databinding.ObservableInt;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.dialog.SelectImageDialog;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.user.LoginResponse;
import hama.alsaygh.kw.vendor.model.user.User;
import hama.alsaygh.kw.vendor.repo.ProfileRepo;
import hama.alsaygh.kw.vendor.utils.AppConstants;
import hama.alsaygh.kw.vendor.utils.SharedPreferenceConstant;

public class EditProfileViewModel extends ViewModel {


    private final Context context;


    protected User user = new User();
    protected int position;

    private final MutableLiveData<LoginResponse> profileMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<LoginResponse> editMutableLiveData = new MutableLiveData<>();

    String[] arrayForSpinner;
    ProfileRepo productRepo;

    int imageType;
    OnGeneralClickListener onGeneralClickListener;
    FragmentManager fragmentManager;

    public EditProfileViewModel(Context context, FragmentManager fragmentManager, OnGeneralClickListener onGeneralClickListener) {
        this.context = context;
        productRepo = new ProfileRepo();
        user = SharedPreferenceConstant.getSharedPreferenceUser(context);
        this.onGeneralClickListener = onGeneralClickListener;
        editVisibility.set(View.VISIBLE);
        pbEditVisibility.set(View.GONE);
        this.fragmentManager = fragmentManager;

        arrayForSpinner = new String[]{context.getString(R.string.male), context.getString(R.string.female)};
    }

    public void setImageType(int imageType) {
        this.imageType = imageType;
    }

    public int getImageType() {
        return imageType;
    }

    public MutableLiveData<LoginResponse> getProfileObserver() {
        return profileMutableLiveData;
    }

    public MutableLiveData<LoginResponse> getEditProfileObserver() {
        return editMutableLiveData;
    }

    public void getProfile(Context context) {
        productRepo.getProfile(context, profileMutableLiveData);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TextWatcher fullNameTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                user.setName(s.toString());
            }
        };
    }

    public TextWatcher storeNameArTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                user.getTranslations().getAr().setCompany_name(s.toString());
            }
        };
    }

    public TextWatcher storeNameEnTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                user.getTranslations().getEn().setCompany_name(s.toString());
            }
        };
    }

    public TextWatcher emailTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                user.setEmail(s.toString());
            }
        };
    }

    public TextWatcher phoneTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                user.setMobile(s.toString());
            }
        };
    }

    public TextWatcher descTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                user.getTranslations().getEn().setCompany_description(s.toString());
            }
        };
    }

    public TextWatcher descArTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                user.getTranslations().getAr().setCompany_description(s.toString());
            }
        };
    }

    public TextWatcher idNoTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                user.setId_no(s.toString());
            }
        };
    }


    public TextWatcher personalNoTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                user.setLandline(s.toString());
            }
        };
    }


    protected ObservableInt editVisibility = new ObservableInt();
    protected ObservableInt pbEditVisibility = new ObservableInt();

    public ObservableInt getEditVisibility() {
        return editVisibility;
    }

    public ObservableInt getPbEditVisibility() {
        return pbEditVisibility;
    }

    public void onExpiryDateClick(View v) {
        final DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = year + "-" + month + "-" + dayOfMonth;
                ((TextView) v).setText(date);
                user.setLicense_deadline(date);
            }
        };

        Calendar calendar = Calendar.getInstance();

        Date date = calendar.getTime();
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            date = sdf.parse(user.getLicense_deadline());
        } catch (Exception e) {
            e.printStackTrace();
        }

        int year = date.getYear() + 1900;
        int month = date.getMonth();
        int day = date.getDate();

        DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), android.R.style.Theme_Holo_Dialog_MinWidth
                , mDateSetListener, year, month, day);
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }


    public void onProfileClick(View v) {

        imageType = AppConstants.PROFILE;
        SelectImageDialog.newInstance(onGeneralClickListener).show(fragmentManager, "select_image");

    }

    public void onIdClick(View v) {

        imageType = AppConstants.ID;
        SelectImageDialog.newInstance(onGeneralClickListener).show(fragmentManager, "select_image");

    }

    public void onCommercialLicenseClick(View v) {

        imageType = AppConstants.COMMERCIAL_LICENSE;
        SelectImageDialog.newInstance(onGeneralClickListener).show(fragmentManager, "select_image");

    }

    public void onCommercialRecordClick(View v) {

        imageType = AppConstants.COMMERCIAL_RECORD;
        SelectImageDialog.newInstance(onGeneralClickListener).show(fragmentManager, "select_image");

    }

    public void onSignatureClick(View v) {

        imageType = AppConstants.SIGNATURE;
        SelectImageDialog.newInstance(onGeneralClickListener).show(fragmentManager, "select_image");

    }

    public void setLogo(String path) {
        user.setLogo(path);
    }

    public void setID(String path) {
        user.setNational_id(path);
    }

    public void setCommercialLicense(String path) {
        user.setCommercial_license(path);
    }

    public void setCommercialRecord(String path) {
        user.setCommercial_record(path);
    }

    public void setSignature(String path) {
        user.setSignature_approval(path);
    }

    public String getLogo() {
        return user.getLogo();
    }

    public String getId() {
        return user.getNational_id();
    }

    public String getCommercialLicense() {
        return user.getCommercial_license();
    }

    public String getCommercialRecord() {
        return user.getCommercial_record();
    }

    public String getSignature() {
        return user.getSignature_approval();
    }


    public void sendChanges(Context context) {
        productRepo.updateProfile(context, user, editMutableLiveData);
    }
}

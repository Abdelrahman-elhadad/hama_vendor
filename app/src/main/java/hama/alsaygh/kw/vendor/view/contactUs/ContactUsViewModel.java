package hama.alsaygh.kw.vendor.view.contactUs;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.app.MainApplication;
import hama.alsaygh.kw.vendor.listener.LoginListener;
import hama.alsaygh.kw.vendor.model.contactUs.ContactUsResponse;
import hama.alsaygh.kw.vendor.model.socialMedia.SocialMedia;
import hama.alsaygh.kw.vendor.model.socialMedia.SocialMediaResponse;
import hama.alsaygh.kw.vendor.repo.GeneralRepo;
import hama.alsaygh.kw.vendor.repo.ProfileRepo;

public class ContactUsViewModel extends ViewModel {

    private final String TAG = "LoginActivityViewModel";

    private final ProfileRepo authRepo;
    private String name, email, msg;

    private final ObservableInt loginVisibility = new ObservableInt();
    private final ObservableInt pbLoginVisibility = new ObservableInt();
    private final LoginListener listener;
    private MutableLiveData<ContactUsResponse> loginResponseMutableLiveData;
    private MutableLiveData<SocialMediaResponse> socialMediaMutableLiveData;

    SocialMedia socialMedia = null;

    public ContactUsViewModel(LoginListener listener) {
        this.listener = listener;
        authRepo = new ProfileRepo();
        loginVisibility.set(View.VISIBLE);
        pbLoginVisibility.set(View.GONE);
    }

    public void setSocialMedia(SocialMedia socialMedia) {
        this.socialMedia = socialMedia;
    }

    public MutableLiveData<ContactUsResponse> getForgetPasswordObservable() {
        if (loginResponseMutableLiveData == null)
            loginResponseMutableLiveData = new MutableLiveData<>();

        return loginResponseMutableLiveData;
    }

    public MutableLiveData<SocialMediaResponse> getSocialObservable() {
        if (socialMediaMutableLiveData == null)
            socialMediaMutableLiveData = new MutableLiveData<>();

        return socialMediaMutableLiveData;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMsg() {
        return msg;
    }

    public void setLoginVisibility(int loginVisibility) {
        this.loginVisibility.set(loginVisibility);
    }

    public void setPbLoginVisibility(int loginVisibility) {
        this.pbLoginVisibility.set(loginVisibility);
    }

    public void login(Context context) {
        authRepo.contactUs(context, name, email, msg, loginResponseMutableLiveData);

    }

    public void getSocialMedia(Context context) {
        new GeneralRepo().getSocialMedia(context, socialMediaMutableLiveData);
    }

    public void onFacebookClick(View view) {
        if (socialMedia != null)
            if (socialMedia.getFacebook() != null && !socialMedia.getFacebook().startsWith("http"))
                view.getContext().startActivity(newFacebookIntent(view.getContext().getPackageManager(), socialMedia.getFacebook()));
            else if (socialMedia.getFacebook() != null) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(socialMedia.getFacebook()));
                    view.getContext().startActivity(browserIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

    }

    public void onYoutubeClick(View view) {
        if (socialMedia != null) {
            if (socialMedia.getYoutube() != null) {

                String url = "";
                if (socialMedia.getYoutube().startsWith("http")) {
                    url = socialMedia.getYoutube();
                } else
                    url = "https://www.youtube.com/" + socialMedia.getYoutube();
                Intent intent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(url));
                intent.setComponent(new ComponentName("com.google.android.youtube", "com.google.android.youtube.PlayerActivity"));

                PackageManager manager = view.getContext().getPackageManager();
                List<ResolveInfo> infos = manager.queryIntentActivities(intent, 0);
                if (infos.size() > 0) {
                    view.getContext().startActivity(intent);
                } else {
                    Intent intent2 = new Intent(Intent.ACTION_VIEW);
                    intent2.setData(Uri.parse(socialMedia.getYoutube()));
                    view.getContext().startActivity(intent);
                }
            }
        }
    }

    public void onInstagramClick(View view) {
        if (socialMedia != null) {
            if (socialMedia.getInstagram() != null && !socialMedia.getInstagram().startsWith("http")) {
                Intent insta_intent = view.getContext().getPackageManager().getLaunchIntentForPackage("com.instagram.android");
                insta_intent.setComponent(new ComponentName("com.instagram.android", "com.instagram.android.activity.UrlHandlerActivity"));

                //use this if you want to open an image
//                    insta_intent.setData(Uri.parse("http://instagram.com/p/gjfLqSBQTJ/"));

                //And if you want to open a user's profile use this
                insta_intent.setData(Uri.parse("http://instagram.com/_u/" + socialMedia.getInstagram()));
                view.getContext().startActivity(insta_intent);
            } else if (socialMedia.getInstagram() != null) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(socialMedia.getInstagram()));
                    view.getContext().startActivity(browserIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void onLinkedinClick(View view) {
        if (socialMedia != null) {
            if (socialMedia.getLinkedin() != null && !socialMedia.getLinkedin().startsWith("http")) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://you"));
                final PackageManager packageManager = view.getContext().getPackageManager();
                final List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                if (list.isEmpty()) {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.linkedin.com/profile/view?id=" + socialMedia.getLinkedin()));
                }
                view.getContext().startActivity(intent);
            } else if (socialMedia.getLinkedin() != null) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(socialMedia.getLinkedin()));
                    view.getContext().startActivity(browserIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public void onTwitterClick(View view) {
        if (socialMedia != null) {
            if (socialMedia.getTwitter() != null && !socialMedia.getTwitter().startsWith("http")) {
                try {
                    view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + socialMedia.getTwitter())));
                } catch (Exception e) {
                    view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/#!/" + socialMedia.getTwitter())));
                }
            } else if (socialMedia.getTwitter() != null) {
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(socialMedia.getTwitter()));
                    view.getContext().startActivity(browserIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void onResetClick(View view) {

        if (MainApplication.isConnected) {
            if (listener != null)
                listener.validation();
            if (name != null && !name.isEmpty() && email != null && !email.isEmpty() && msg != null && !msg.isEmpty()) {
                setLoginVisibility(View.GONE);
                setPbLoginVisibility(View.VISIBLE);

                login(view.getContext());
            }
        } else
            Snackbar.make(view, view.getContext().getString(R.string.no_internet_connection), Snackbar.LENGTH_SHORT).show();

    }

    public TextWatcher nameTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                name = s.toString();
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
                email = s.toString();
            }
        };
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

    public int isHideOrShowAll() {
        return socialMedia == null ? View.GONE : View.VISIBLE;
    }

    public int isYoutube() {
        return socialMedia != null && !socialMedia.getYoutube().isEmpty() ? View.VISIBLE : View.GONE;
    }

    public int isTwitter() {
        return socialMedia != null && !socialMedia.getTwitter().isEmpty() ? View.VISIBLE : View.GONE;
    }

    public int isFacebook() {
        return socialMedia != null && !socialMedia.getFacebook().isEmpty() ? View.VISIBLE : View.GONE;
    }

    public int isInstagram() {
        return socialMedia != null && !socialMedia.getInstagram().isEmpty() ? View.VISIBLE : View.GONE;
    }

    public int isLinkedin() {
        return socialMedia != null && !socialMedia.getLinkedin().isEmpty() ? View.VISIBLE : View.GONE;
    }

    public Intent newFacebookIntent(PackageManager pm, String url) {
        Uri uri = Uri.parse(url);
        try {
            ApplicationInfo applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0);
            if (applicationInfo.enabled) {
                // http://stackoverflow.com/a/24547437/1048340
                uri = Uri.parse("fb://facewebmodal/f?href=" + url);
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }

}

package hama.alsaygh.kw.vendor.repo;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import hama.alsaygh.kw.vendor.model.contactUs.ContactUsResponse;
import hama.alsaygh.kw.vendor.model.user.LoginResponse;
import hama.alsaygh.kw.vendor.model.user.User;
import hama.alsaygh.kw.vendor.model.user.UserResponse;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;


public class ProfileRepo {

    private final String TAG = "RequestWrapper";

    ///////////////////// Profile ///////////////////////////////
    public void getProfile(final Context context, final MutableLiveData<LoginResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            LoginResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "profileData";
                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).get().build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG, "Response:profileData " + responseString);

                loginSocialResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, LoginResponse.class);


            } catch (Exception e) {
                e.printStackTrace();
                loginSocialResponse = new LoginResponse();
                loginSocialResponse.setStatus(false);
                loginSocialResponse.setMessage("server error");

            }

            if (loginResponseMutableLiveData != null) {
                final LoginResponse finalLoginSocialResponse = loginSocialResponse;
                new Handler(Looper.getMainLooper()).post(() -> loginResponseMutableLiveData.setValue(finalLoginSocialResponse));
            }

        }).start();

    }

    public void getUser(final Context context, final MutableLiveData<LoginResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            LoginResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "fetch-user";
                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).get().build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG, "Response:fetch-user " + responseString);

                loginSocialResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, LoginResponse.class);


            } catch (Exception e) {
                e.printStackTrace();
                loginSocialResponse = new LoginResponse();
                loginSocialResponse.setStatus(false);
                loginSocialResponse.setMessage("server error");

            }

            if (loginResponseMutableLiveData != null) {
                final LoginResponse finalLoginSocialResponse = loginSocialResponse;
                new Handler(Looper.getMainLooper()).post(() -> loginResponseMutableLiveData.setValue(finalLoginSocialResponse));
            }

        }).start();

    }

    public void updateProfile(final Context context, final User user, final String cites, final MutableLiveData<LoginResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            LoginResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "updateProfile";
                FormBody body = new FormBody.Builder()
                        .add("name", user.getName())
                        .add("birth_date", user.getBirth_date())
                        .add("address", user.getAddress())
                        .add("avatar", user.getImage())
                        .add("cities", cites)
                        .add("current_password", user.getPassword().isEmpty() ? "" : user.getPassword())
                        .add("password", user.getPassword().isEmpty() ? "" : user.getNewPassword())
                        .add("password_confirmation", user.getPassword().isEmpty() ? "" : user.getConfirmNewPassword())
                        .build();

                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).put(body).build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG, "Response:updateProfile " + responseString);
                responseString = responseString.replace("\"data\":[]", "\"data\":{}");
                loginSocialResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, LoginResponse.class);


            } catch (Exception e) {
                e.printStackTrace();
                loginSocialResponse = new LoginResponse();
                loginSocialResponse.setStatus(false);
                loginSocialResponse.setMessage("server error");

            }

            if (loginResponseMutableLiveData != null) {
                final LoginResponse finalLoginSocialResponse = loginSocialResponse;
                new Handler(Looper.getMainLooper()).post(() -> loginResponseMutableLiveData.setValue(finalLoginSocialResponse));
            }

        }).start();

    }

    public void updatePassword(final Context context, final String password, final String newPassword, final MutableLiveData<UserResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            UserResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "reset-password";
                FormBody body = new FormBody.Builder()
                        .add("current_password", password)
                        .add("old_password", password)
                        .add("password", newPassword)
                        .add("password_confirmation", newPassword)
                        .build();

                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).post(body).build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG, "Response:reset-password" + responseString);
                responseString=responseString.replace("\"data\":[]","\"data\":{}");
                loginSocialResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, UserResponse.class);


            } catch (Exception e) {
                e.printStackTrace();
                loginSocialResponse = new UserResponse();
                loginSocialResponse.setStatus(false);
                loginSocialResponse.setMessage("server error");

            }

            if (loginResponseMutableLiveData != null) {
                final UserResponse finalLoginSocialResponse = loginSocialResponse;
                new Handler(Looper.getMainLooper()).post(() -> loginResponseMutableLiveData.setValue(finalLoginSocialResponse));
            }

        }).start();

    }


    public void changeLanguage(final Context context,final String language, final MutableLiveData<UserResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            UserResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "settings/language";
                FormBody body = new FormBody.Builder()
                        .add("language", language)
                        .build();
                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).post(body).build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG, "Response:settings/language " + responseString);
                responseString=responseString.replace("\"data\":[]","\"data\":{}");
                loginSocialResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, UserResponse.class);


            } catch (Exception e) {
                e.printStackTrace();
                loginSocialResponse = new UserResponse();
                loginSocialResponse.setStatus(false);
                loginSocialResponse.setMessage("server error");

            }


            if (loginResponseMutableLiveData != null) {
                final UserResponse finalLoginSocialResponse = loginSocialResponse;
                new Handler(Looper.getMainLooper()).post(() -> loginResponseMutableLiveData.setValue(finalLoginSocialResponse));
            }

        }).start();

    }
    public void updateNotificationSettings(final Context context,final boolean general_notifications,final boolean order_notification,final boolean event_notification,final boolean adv_notification, final MutableLiveData<UserResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            UserResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "settings/notifications";
                FormBody body = new FormBody.Builder()
                        .add("general_notifications", general_notifications?"1":"0")
                        .add("order_notification", order_notification?"1":"0")
                        .add("event_notification", event_notification?"1":"0")
                        .add("adv_notification", adv_notification?"1":"0")
                        .build();
                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).post(body).build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                responseString=responseString.replace("\"data\":[]","\"data\":{}");
                Log.i(TAG, "Response:settings/notifications " + responseString);

                loginSocialResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, UserResponse.class);


            } catch (Exception e) {
                e.printStackTrace();
                loginSocialResponse = new UserResponse();
                loginSocialResponse.setStatus(false);
                loginSocialResponse.setMessage("server error");

            }


            if (loginResponseMutableLiveData != null) {
                final UserResponse finalLoginSocialResponse = loginSocialResponse;
                new Handler(Looper.getMainLooper()).post(() -> loginResponseMutableLiveData.setValue(finalLoginSocialResponse));
            }

        }).start();

    }
    public void contactUs(final Context context,final String name,final String email,final String message, final MutableLiveData<ContactUsResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            ContactUsResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "contact";
                FormBody body = new FormBody.Builder()
                        .add("name", name)
                        .add("email", email)
                        .add("message", message)

                        .build();
                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).post(body).build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                responseString=responseString.replace("\"data\":[]","\"data\":{}");
                Log.i(TAG, "Response:ContactUsResponse " + responseString);

                loginSocialResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, ContactUsResponse.class);


            } catch (Exception e) {
                e.printStackTrace();
                loginSocialResponse = new ContactUsResponse();
                loginSocialResponse.setStatus(false);
                loginSocialResponse.setMessage("server error");

            }


            if (loginResponseMutableLiveData != null) {
                final ContactUsResponse finalLoginSocialResponse = loginSocialResponse;
                new Handler(Looper.getMainLooper()).post(() -> loginResponseMutableLiveData.setValue(finalLoginSocialResponse));
            }

        }).start();

    }

}

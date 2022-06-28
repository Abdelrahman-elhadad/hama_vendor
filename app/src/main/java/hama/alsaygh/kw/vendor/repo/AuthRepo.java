package hama.alsaygh.kw.vendor.repo;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import hama.alsaygh.kw.vendor.model.check.CheckResponse;
import hama.alsaygh.kw.vendor.model.general.GeneralResponse;
import hama.alsaygh.kw.vendor.model.user.LoginResponse;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;


public class AuthRepo {

    private final String TAG = "RequestWrapper";

    ///////////////////// login ///////////////////////////////
    public void Login(final Context context, final String email, final String password,final String token,  final MutableLiveData<LoginResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            LoginResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "auth/login";

                FormBody body = new FormBody.Builder()
                        .add("password", password)
                        .add("email", email)
                        .add("fcm_token", token)
                        .build();

                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeaderPush(context, token);
                Request request = requestBuilder.url(url).post(body).build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG, "Response:auth/login " + responseString);

                responseString = responseString.replace("\"data\":[]", "\"data\":{}");
                responseString = responseString.replace("\"translations\":[]", "\"translations\":{}");

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

    //////////////////////// logout /////////////////////////
    public void logout(final Context context, final MutableLiveData<CheckResponse> signUpResponseMutableLiveData) {

        new Thread(() -> {
            CheckResponse signUpResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "auth/logout";

                FormBody body = new FormBody.Builder().build();

                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).post(body).build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();
                Log.i(TAG, "Response:auth/logout " + responseString);


                signUpResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, CheckResponse.class);

            } catch (Exception e) {
                e.printStackTrace();
                signUpResponse = new CheckResponse();
                signUpResponse.setStatus(false);
                signUpResponse.setMessage("server error");

            }
            if (signUpResponseMutableLiveData != null) {
                final CheckResponse finalLoginSocialResponse = signUpResponse;
                new Handler(Looper.getMainLooper()).post(() -> signUpResponseMutableLiveData.setValue(finalLoginSocialResponse));
            }
        }).start();
    }


    ///////////////////// forget password ///////////////////////////////
    public void forgetPassword(final Context context, final String email,  final MutableLiveData<GeneralResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            GeneralResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "auth/forget";

                FormBody body = new FormBody.Builder()
                        .add("email", email)
                        .build();

                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).post(body).build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG, "Response:auth/forget " + responseString);

                loginSocialResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, GeneralResponse.class);


            } catch (Exception e) {
                e.printStackTrace();
                loginSocialResponse = new GeneralResponse();
                loginSocialResponse.setStatus(false);
                loginSocialResponse.setMessage("server error");

            }

            if (loginResponseMutableLiveData != null) {
                final GeneralResponse finalLoginSocialResponse = loginSocialResponse;
                new Handler(Looper.getMainLooper()).post(() -> loginResponseMutableLiveData.setValue(finalLoginSocialResponse));
            }

        }).start();

    }

    ///////////////////// verify Code ///////////////////////////////
    public void verifyCode(final Context context, final String email,final String code,  final MutableLiveData<LoginResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            LoginResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "auth/verify";

                FormBody body = new FormBody.Builder()
                        .add("verify", email)
                        .add("code", code)
                        .build();

                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).post(body).build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG, "Response:auth/verify " + responseString);
                responseString = responseString.replace("\"data\":[]", "\"data\":{}");
                responseString = responseString.replace("\"translations\":[]", "\"translations\":{}");

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

    ///////////////////// Rest Password ///////////////////////////////
    public void resetPassword(final Context context, final String token,final String password,  final MutableLiveData<GeneralResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            GeneralResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "auth/reset";

                FormBody body = new FormBody.Builder()
                        .add("token", token)
                        .add("password", password)
                        .add("password_confirmation", password)
                        .build();

                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).post(body).build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG, "Response:auth/reset " + responseString);

                loginSocialResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, GeneralResponse.class);


            } catch (Exception e) {
                e.printStackTrace();
                loginSocialResponse = new GeneralResponse();
                loginSocialResponse.setStatus(false);
                loginSocialResponse.setMessage("server error");

            }

            if (loginResponseMutableLiveData != null) {
                final GeneralResponse finalLoginSocialResponse = loginSocialResponse;
                new Handler(Looper.getMainLooper()).post(() -> loginResponseMutableLiveData.setValue(finalLoginSocialResponse));
            }

        }).start();

    }


    ///////////////////// Register ///////////////////////////////
    public void register(final Context context, final String token,final String name,final String email,final String phone,final String password,  final MutableLiveData<LoginResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            LoginResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "auth/register";

                FormBody body = new FormBody.Builder()
                        .add("token", token)
                        .add("name", name)
                        .add("email", email)
                        .add("phone", phone)
                        .add("password", password)
                        .add("password_confirmation", password)
                        .build();

                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).post(body).build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG, "Response:auth/register " + responseString);
                responseString = responseString.replace("\"data\":[]", "\"data\":{}");
                responseString = responseString.replace("\"translations\":[]", "\"translations\":{}");

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

}

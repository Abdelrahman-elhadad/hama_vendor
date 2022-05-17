package hama.alsaygh.kw.vendor.repo;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.File;

import hama.alsaygh.kw.vendor.model.appointment.AppointmentResponse;
import hama.alsaygh.kw.vendor.model.image.ImageResponse;
import hama.alsaygh.kw.vendor.model.onBoarding.OnBoardResponse;
import hama.alsaygh.kw.vendor.model.page.PageResponse;
import hama.alsaygh.kw.vendor.model.socialMedia.SocialMediaResponse;
import hama.alsaygh.kw.vendor.utils.Utils;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class GeneralRepo {

    private final String TAG = "RequestWrapper";

    ///////////////////// OnBoarding ///////////////////////////////
    public void getOnBoarding(final Context context, final MutableLiveData<OnBoardResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            OnBoardResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPathConstants() + "on-bording";

                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).get().build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG, "Response:auth/on-bording " + responseString);

                loginSocialResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, OnBoardResponse.class);


            } catch (Exception e) {
                e.printStackTrace();
                loginSocialResponse = new OnBoardResponse();
                loginSocialResponse.setStatus(false);
                loginSocialResponse.setMessage("server error");

            }

            if (loginResponseMutableLiveData != null) {
                final OnBoardResponse finalLoginSocialResponse = loginSocialResponse;
                new Handler(Looper.getMainLooper()).post(() -> loginResponseMutableLiveData.setValue(finalLoginSocialResponse));
            }

        }).start();

    }

    //////////////////////// pages /////////////////////////
    public void getAboutUs(final Context context, final MutableLiveData<PageResponse> signUpResponseMutableLiveData) {

        new Thread(() -> {
            PageResponse signUpResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPathConstants() + "about";


                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).get().build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();
                Log.i(TAG, "Response:about " + responseString);


                signUpResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, PageResponse.class);

            } catch (Exception e) {
                e.printStackTrace();
                signUpResponse = new PageResponse();
                signUpResponse.setStatus(false);
                signUpResponse.setMessage("server error");

            }
            if (signUpResponseMutableLiveData != null) {
                final PageResponse finalLoginSocialResponse = signUpResponse;
                new Handler(Looper.getMainLooper()).post(() -> signUpResponseMutableLiveData.setValue(finalLoginSocialResponse));
            }
        }).start();
    }

    public void getTerms(final Context context, final MutableLiveData<PageResponse> signUpResponseMutableLiveData) {

        new Thread(() -> {
            PageResponse signUpResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPathConstants() + "terms-conditions";


                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).get().build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();
                Log.i(TAG, "Response:terms-conditions " + responseString);


                signUpResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, PageResponse.class);

            } catch (Exception e) {
                e.printStackTrace();
                signUpResponse = new PageResponse();
                signUpResponse.setStatus(false);
                signUpResponse.setMessage("server error");

            }
            if (signUpResponseMutableLiveData != null) {
                final PageResponse finalLoginSocialResponse = signUpResponse;
                new Handler(Looper.getMainLooper()).post(() -> signUpResponseMutableLiveData.setValue(finalLoginSocialResponse));
            }
        }).start();
    }


    ///////////////////// Social media ///////////////////////////////
    public void getSocialMedia(final Context context, final MutableLiveData<SocialMediaResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            SocialMediaResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "social-media";

                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).get().build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG, "Response:social-media " + responseString);

                loginSocialResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, SocialMediaResponse.class);


            } catch (Exception e) {
                e.printStackTrace();
                loginSocialResponse = new SocialMediaResponse();
                loginSocialResponse.setStatus(false);
                loginSocialResponse.setMessage("server error");

            }

            if (loginResponseMutableLiveData != null) {
                final SocialMediaResponse finalLoginSocialResponse = loginSocialResponse;
                new Handler(Looper.getMainLooper()).post(() -> loginResponseMutableLiveData.setValue(finalLoginSocialResponse));
            }

        }).start();

    }


    ///////////////////// Appointment ///////////////////////////////
    public void getAppointment(final Context context, final MutableLiveData<AppointmentResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            AppointmentResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "appointment";

                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).get().build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG, "Response:appointment " + responseString);

                loginSocialResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, AppointmentResponse.class);


            } catch (Exception e) {
                e.printStackTrace();
                loginSocialResponse = new AppointmentResponse();
                loginSocialResponse.setStatus(false);
                loginSocialResponse.setMessage("server error");

            }

            if (loginResponseMutableLiveData != null) {
                final AppointmentResponse finalLoginSocialResponse = loginSocialResponse;
                new Handler(Looper.getMainLooper()).post(() -> loginResponseMutableLiveData.setValue(finalLoginSocialResponse));
            }

        }).start();

    }


    ///////////////////// upload file or image ///////////////////////////////
    public void uploadImage(final Context context, final String image, final MutableLiveData<ImageResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            ImageResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPathConstants() + "image-upload";
                MultipartBody.Builder builder = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM);
                String IMG_Name = Utils.getInstance().encodeString(image.substring(image.lastIndexOf("/") + 1));
                builder.addFormDataPart("image", IMG_Name, RequestBody.create(RequestWrapper.getInstance().getMEDIA_TYPE(), new File(image)));
                MultipartBody requestBody = builder.build();

                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeaderMedia(context);
                Request request = requestBuilder.url(url).post(requestBody).build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG, "Response:image-upload " + responseString);

                loginSocialResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, ImageResponse.class);


            } catch (Exception e) {
                e.printStackTrace();
                loginSocialResponse = new ImageResponse();
                loginSocialResponse.setStatus(false);
                loginSocialResponse.setMessage("server error");

            }

            if (loginResponseMutableLiveData != null) {
                final ImageResponse finalLoginSocialResponse = loginSocialResponse;
                new Handler(Looper.getMainLooper()).post(() -> loginResponseMutableLiveData.setValue(finalLoginSocialResponse));
            }

        }).start();

    }

    public void uploadFile(final Context context, final String image, final MutableLiveData<ImageResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            ImageResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPathConstants() + "file-upload";
                MultipartBody.Builder builder = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM);
                String IMG_Name = Utils.getInstance().encodeString(image.substring(image.lastIndexOf("/") + 1));
                builder.addFormDataPart("file", IMG_Name, RequestBody.create(RequestWrapper.getInstance().getFILE_TYPE(), new File(image)));
                MultipartBody requestBody = builder.build();

                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeaderMedia(context);
                Request request = requestBuilder.url(url).post(requestBody).build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG, "Response:image-upload " + responseString);

                loginSocialResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, ImageResponse.class);


            } catch (Exception e) {
                e.printStackTrace();
                loginSocialResponse = new ImageResponse();
                loginSocialResponse.setStatus(false);
                loginSocialResponse.setMessage("server error");

            }

            if (loginResponseMutableLiveData != null) {
                final ImageResponse finalLoginSocialResponse = loginSocialResponse;
                new Handler(Looper.getMainLooper()).post(() -> loginResponseMutableLiveData.setValue(finalLoginSocialResponse));
            }

        }).start();

    }

}

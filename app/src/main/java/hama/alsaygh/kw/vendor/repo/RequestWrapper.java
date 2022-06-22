package hama.alsaygh.kw.vendor.repo;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import hama.alsaygh.kw.vendor.utils.LocalUtils;
import hama.alsaygh.kw.vendor.utils.SharedPreferenceConstant;
import hama.alsaygh.kw.vendor.utils.Utils;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okio.Buffer;


public class RequestWrapper {

    private final Gson gson;
    private final OkHttpClient client;
    private static RequestWrapper requestWrapper;

    private final String PATH = "https://hamakw.com/api";
    private final String VERSION = "v1";
    private final String MODULE_CONSTANTS = "constants";
    private final String MODULE_VENDOR = "vendor";
    private final String FULL_PATH_VENDOR = PATH + "/" + VERSION + "/" + MODULE_VENDOR + "/";
    private final String FULL_PATH_CONSTANTS = PATH + "/" + VERSION + "/" + MODULE_CONSTANTS + "/";
    private final String FULL_PATH_USER = PATH + "/" + VERSION + "/" + "user" + "/";


    private final MediaType MEDIA_TYPE = MediaType.parse("image/*");
    private final MediaType FILE_TYPE = MediaType.parse("*/*");

    private final static String CONTENT_TYPE = "application/json";
    private final static String CONTENT_TYPE_DATA = "application/x-www-form-urlencoded";


    private String APP_VERSION = "1.0";
    public final static String SOCIAL_PROVIDER_GOOGLE = "google";
    public final static String SOCIAL_PROVIDER_FACEBOOK = "facebook";
    public final static String SOCIAL_PROVIDER_GMAIL = "GMAIL";


    private RequestWrapper() {

        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        int timeout = 3;
        client = new OkHttpClient.Builder()
                .connectTimeout(timeout, TimeUnit.MINUTES)
                .writeTimeout(timeout, TimeUnit.MINUTES)
                .readTimeout(timeout, TimeUnit.MINUTES)
                .build();
        //MEDIA_TYPE = MediaType.parse("image/*");
    }

    public static RequestWrapper getInstance() {
        if (requestWrapper == null)
            requestWrapper = new RequestWrapper();
        return requestWrapper;
    }


    public Gson getGson() {
        return gson;
    }

    public OkHttpClient getClient() {
        return client;
    }

    public MediaType getMEDIA_TYPE() {
        return MEDIA_TYPE;
    }

    public MediaType getFILE_TYPE() {
        return FILE_TYPE;
    }

    public String getUserType() {
        return "user";
    }

    public String getContentType() {
        return "application/json";
    }

    public String getDeviceType() {
        return "android";
    }

    public String getFullPathUser() {
        return FULL_PATH_USER;
    }

    public String getAppVersion(Context context) {

        PackageManager pm = context.getPackageManager();
        PackageInfo pInfo = null;

        try {
            pInfo = pm.getPackageInfo(context.getPackageName(), 0);

        } catch (PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();
        }
        if (pInfo != null) {
            APP_VERSION = pInfo.versionName;

        }

        return APP_VERSION;
    }

    public String getContentTypeData() {
        return "application/x-www-form-urlencoded";
    }

    public String getFullPath() {
        return FULL_PATH_VENDOR;
    }

    public String getFullPathConstants() {
        return FULL_PATH_CONSTANTS;
    }

    public Request.Builder getRequestHeader(Context context)
    {
        return  new Request.Builder()
                .addHeader("Accept", RequestWrapper.getInstance().getContentType())
                .addHeader("Content-Type", RequestWrapper.getInstance().getContentType())
                .addHeader("app-version", RequestWrapper.getInstance().getAppVersion(context))
                .addHeader("os-version", RequestWrapper.getInstance().getDeviceType())
                .addHeader("mobile-version", Build.VERSION.RELEASE)
                .addHeader("Accept-Language", LocalUtils.getInstance().getLanguageShort(context) )
                .addHeader("UUID", Utils.getInstance().getUUID(context))
                .addHeader("Authorization", "Bearer " + SharedPreferenceConstant.getSharedPreferenceUserToken(context));
    }

    public Request.Builder getRequestHeaderMedia(Context context)
    {
        return  new Request.Builder()
                .addHeader("Accept", RequestWrapper.getInstance().getContentType())
                .addHeader("Content-Type", RequestWrapper.getInstance().getContentTypeData())
                .addHeader("app-version", RequestWrapper.getInstance().getAppVersion(context))
                .addHeader("os-version", RequestWrapper.getInstance().getDeviceType())
                .addHeader("mobile-version", Build.VERSION.RELEASE)
                .addHeader("Accept-Language", LocalUtils.getInstance().getLanguageShort(context) )
                .addHeader("UUID", Utils.getInstance().getUUID(context))
                .addHeader("Authorization", "Bearer " + SharedPreferenceConstant.getSharedPreferenceUserToken(context));
    }

    public Request.Builder getRequestHeader(Context context,String token)
    {
        return  new Request.Builder()
                .addHeader("Accept", RequestWrapper.getInstance().getContentType())
                .addHeader("Content-Type", RequestWrapper.getInstance().getContentType())
                .addHeader("app-version", RequestWrapper.getInstance().getAppVersion(context))
                .addHeader("os-version", RequestWrapper.getInstance().getDeviceType())
                .addHeader("mobile-version", Build.VERSION.RELEASE)
                .addHeader("Accept-Language", LocalUtils.getInstance().getLanguageShort(context) )
                .addHeader("UUID", Utils.getInstance().getUUID(context))
                .addHeader("Authorization", "Bearer " + token);

    }

    public Request.Builder getRequestHeaderPush(Context context,String token)
    {
        return  new Request.Builder()
                .addHeader("Accept", RequestWrapper.getInstance().getContentType())
                .addHeader("Content-Type", RequestWrapper.getInstance().getContentType())
                .addHeader("app-version", RequestWrapper.getInstance().getAppVersion(context))
                .addHeader("os-version", RequestWrapper.getInstance().getDeviceType())
                .addHeader("mobile-version", Build.VERSION.RELEASE)
                .addHeader("Accept-Language", LocalUtils.getInstance().getLanguageShort(context) )
                .addHeader("UUID", Utils.getInstance().getUUID(context))
                .addHeader("Authorization", "Bearer " + SharedPreferenceConstant.getSharedPreferenceUserToken(context))
                .addHeader("fcm_token", token);

    }
    public String requestBodyToString(final Request request) {

        try {
            final Buffer buffer = new Buffer();
            if (request.body() != null) {
                request.body().writeTo(buffer);
            }
            return request.headers().toString() + "\n" + buffer.readUtf8();
        } catch (final IOException e) {
            return request.headers().toString() + "\n" + "Failed to read request body";
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return request.headers().toString() + "\n" + "Failed to read request body";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return request.headers().toString() + "\n" + "Exception";
    }

}

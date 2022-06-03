package hama.alsaygh.kw.vendor.repo;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import hama.alsaygh.kw.vendor.model.product.ProductsResponse;
import okhttp3.Request;
import okhttp3.Response;


public class ProductRepo {

    private final String TAG = "RequestWrapper";

    ///////////////////// Products ///////////////////////////////
    public void getProducts(final Context context, final int page, final MutableLiveData<ProductsResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            ProductsResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "products/index?page=" + page;
                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).get().build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG, "products/index ?page=" + page + " : " + responseString);

                loginSocialResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, ProductsResponse.class);


            } catch (Exception e) {
                e.printStackTrace();
                loginSocialResponse = new ProductsResponse();
                loginSocialResponse.setStatus(false);
                loginSocialResponse.setMessage("server error");

            }

            if (loginResponseMutableLiveData != null) {
                final ProductsResponse finalLoginSocialResponse = loginSocialResponse;
                new Handler(Looper.getMainLooper()).post(() -> loginResponseMutableLiveData.setValue(finalLoginSocialResponse));
            }

        }).start();

    }

    public void deleteProduct(final Context context, final int id, final MutableLiveData<ProductsResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            ProductsResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "products/" + id + "/delete";
                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).delete().build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG, "products/" + id + "/delete : " + responseString);

                loginSocialResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, ProductsResponse.class);


            } catch (Exception e) {
                e.printStackTrace();
                loginSocialResponse = new ProductsResponse();
                loginSocialResponse.setStatus(false);
                loginSocialResponse.setMessage("server error");

            }

            if (loginResponseMutableLiveData != null) {
                final ProductsResponse finalLoginSocialResponse = loginSocialResponse;
                new Handler(Looper.getMainLooper()).post(() -> loginResponseMutableLiveData.setValue(finalLoginSocialResponse));
            }

        }).start();

    }

}

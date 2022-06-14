package hama.alsaygh.kw.vendor.repo;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import hama.alsaygh.kw.vendor.model.check.CheckResponse;
import hama.alsaygh.kw.vendor.model.order.OrderResponse;
import hama.alsaygh.kw.vendor.model.order.OrdersResponse;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;


public class OrderRepo {

    private final String TAG = "RequestWrapper";

    ///////////////////// order ///////////////////////////////
    public void getOrders(final Context context,final String status,  final MutableLiveData<OrdersResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            OrdersResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "order/index/"+status;
                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).get().build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG,  "order/index/"+status+" : " + responseString);

                loginSocialResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, OrdersResponse.class);


            } catch (Exception e) {
                e.printStackTrace();
                loginSocialResponse = new OrdersResponse();
                loginSocialResponse.setStatus(false);
                loginSocialResponse.setMessage("server error");

            }

            if (loginResponseMutableLiveData != null) {
                final OrdersResponse finalLoginSocialResponse = loginSocialResponse;
                new Handler(Looper.getMainLooper()).post(() -> loginResponseMutableLiveData.setValue(finalLoginSocialResponse));
            }

        }).start();

    }
    public void acceptOrder(final Context context, final int order_id, final MutableLiveData<CheckResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            CheckResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "order/" + order_id + "/operation?status=accepted";
                FormBody body = new FormBody.Builder().build();

                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).post(body).build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG, "Response:order/"+order_id+"/accept " + responseString);
                loginSocialResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, CheckResponse.class);


            } catch (Exception e) {
                e.printStackTrace();
                loginSocialResponse = new CheckResponse();
                loginSocialResponse.setStatus(false);
                loginSocialResponse.setMessage("server error");

            }

            if (loginResponseMutableLiveData != null) {
                final CheckResponse finalLoginSocialResponse = loginSocialResponse;
                new Handler(Looper.getMainLooper()).post(() -> loginResponseMutableLiveData.setValue(finalLoginSocialResponse));
            }

        }).start();

    }
    public void getOrder(final Context context,final int order_id,final String status,  final MutableLiveData<OrderResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            OrderResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "order/"+status+"/"+order_id+"/show";
                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).get().build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG,  "order/"+status+"/"+order_id+"/show"+" : " + responseString);

                loginSocialResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, OrderResponse.class);


            } catch (Exception e) {
                e.printStackTrace();
                loginSocialResponse = new OrderResponse();
                loginSocialResponse.setStatus(false);
                loginSocialResponse.setMessage("server error");

            }

            if (loginResponseMutableLiveData != null) {
                final OrderResponse finalLoginSocialResponse = loginSocialResponse;
                new Handler(Looper.getMainLooper()).post(() -> loginResponseMutableLiveData.setValue(finalLoginSocialResponse));
            }

        }).start();
    }

    public void discardOrder(final Context context, final int order_id, final MutableLiveData<CheckResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            CheckResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "order/" + order_id + "/operation?status=rejected";
                FormBody body = new FormBody.Builder().build();

                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).post(body).build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG, "Response:order/" + order_id + "/rejected " + responseString);
                loginSocialResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, CheckResponse.class);


            } catch (Exception e) {
                e.printStackTrace();
                loginSocialResponse = new CheckResponse();
                loginSocialResponse.setStatus(false);
                loginSocialResponse.setMessage("server error");

            }

            if (loginResponseMutableLiveData != null) {
                final CheckResponse finalLoginSocialResponse = loginSocialResponse;
                new Handler(Looper.getMainLooper()).post(() -> loginResponseMutableLiveData.setValue(finalLoginSocialResponse));
            }

        }).start();

    }

}

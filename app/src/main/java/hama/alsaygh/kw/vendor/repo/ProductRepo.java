package hama.alsaygh.kw.vendor.repo;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import hama.alsaygh.kw.vendor.model.addProduct.AddProduct;
import hama.alsaygh.kw.vendor.model.general.GeneralResponse;
import hama.alsaygh.kw.vendor.model.product.ProductsResponse;
import okhttp3.FormBody;
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

    public void addProduct(final Context context, final AddProduct product, final MutableLiveData<GeneralResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            GeneralResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "products/store";
                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                FormBody.Builder builder = new FormBody.Builder()
                        .add("title_ar", product.getName_ar() + "")
                        .add("description_ar", product.getDescription_ar() + "")
                        .add("title_en", product.getName() + "")
                        .add("description_en", product.getDescription() + "")
                        .add("ston_type", product.getStoneType() + "")
                        .add("color", product.getColor() + "")
                        .add("purity", product.getPurity() + "")
                        .add("diamond_weight", product.getDiamondWeight() + "")
                        .add("gem_stone_weight", product.getStoneWeight() + "")
                        .add("metal_weight", product.getNetWeight() + "")
                        .add("total_metal_weight", product.getTotalWeightMetal() + "")
                        .add("cate_code", product.getCode() + "")
                        .add("weight", product.getWeight() + "")
                        .add("quantity", product.getQuantity() + "")
                        .add("discount_value", product.getDiscount() + "")
                        .add("bind_to_market", product.isBind_to_market() ? "1" : "0")
                        .add("gram_price", product.getGmPrice() == null || product.getGmPrice().isEmpty() ? "0.0" : product.getGmPrice() + "")
                        .add("manufacture_price", product.getManufacture_price() == null || product.getManufacture_price().isEmpty() ? "0.0" : product.getManufacture_price() + "");

                if (product.getMain_category() != null) {
                    builder.add("main_category_id", product.getMain_category().getId() + "");
                }
                if (product.getCaliber() != null)
                    builder.add("caliber_id", product.getCaliber().getId() + "");

                if (product.getSub_category() != null) {
                    builder.add("category_id", product.getSub_category().getId() + "");
                }

                if (product.getChild_sub_category() != null) {
                    builder.add("sub_category_id", product.getChild_sub_category().getId() + "");
                }

                if (!product.isBind_to_market()) {
                    builder.add("fixed_price", product.getFixed_price() + "");
                }

                if (product.getMedia() != null && !product.getMedia().isEmpty()) {
                    for (String path : product.getMedia()) {
                        builder.add("images[]", path + "");
                    }
                }

                builder.add("options", "[]");

                FormBody body = builder.build();
                Request request = requestBuilder.url(url).post(body).build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG, "products/store : " + responseString);

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

}

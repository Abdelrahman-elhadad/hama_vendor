package hama.alsaygh.kw.vendor.repo;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONObject;

import hama.alsaygh.kw.vendor.model.addProduct.AddProduct;
import hama.alsaygh.kw.vendor.model.general.GeneralResponse;
import hama.alsaygh.kw.vendor.model.product.Media;
import hama.alsaygh.kw.vendor.model.product.ProductsResponse;
import hama.alsaygh.kw.vendor.model.product.caliber.CalibersResponse;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;


public class ProductRepo {

    private final String TAG = "RequestWrapper";

    ///////////////////// Products ///////////////////////////////
    public void getProducts(final Context context, final int page, final String sort_key, final int category_level_1, final int category_level_2, final int category_level_3, final String type_of_price, final String range_price_from, final String range_price_to, final MutableLiveData<ProductsResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            ProductsResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "products/index?page=" + page;


                if (!sort_key.isEmpty()) {
                    url = url + "sort_key=" + sort_key;
                }

                if (category_level_1 != -1) {
                    url = url + "&category_level_1=" + category_level_1;
                }

                if (category_level_2 != -1) {
                    url = url + "&category_level_2=" + category_level_2;
                }

                if (category_level_3 != -1) {
                    url = url + "&category_level_3=" + category_level_3;
                }

                if (!type_of_price.isEmpty()) {
                    url = url + "&type_of_price=" + type_of_price;
                }

                if (!range_price_from.isEmpty() && !range_price_to.isEmpty()) {
                    url = url + "&range_price_from=" + range_price_from;
                    url = url + "&range_price_to=" + range_price_to;
                }

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
                        .add("dimond", product.getDiamond() + "")
                        .add("gem_stone_weight", product.getStoneWeight() + "")
                        .add("metal_weight", product.getNetWeight() + "")
                        .add("total_metal_weight", product.getTotalWeightMetal() + "")
                        .add("cate_code", product.getCode() + "")
                        .add("weight", product.getWeight() + "")
                        .add("quantity", product.getQuantity() + "")
                        .add("discount_value", product.isBind_to_market() ? "0" : product.getDiscount() + "")
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
                    for (Media path : product.getMedia()) {
                        builder.add("images[]", path.getId() + "");
                    }
                }

                if (product.getOptions() != null && !product.getOptions().isEmpty()) {

                    JSONArray jsonArray = new JSONArray();
                    for (int i = 0; i < product.getOptions().size(); i++) {

                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("name_ar", product.getOptions().get(i).getNameAr() + "");
                        jsonObject.put("name_en", product.getOptions().get(i).getName());
                        jsonObject.put("available_quantity", product.getOptions().get(i).getAvailable_quantity() + "");
                        jsonObject.put("total_weight", product.getOptions().get(i).getTotal_weight());
                        jsonObject.put("bind_to_market", product.getOptions().get(i).isBind_to_market() ? "1" : "0");
                        jsonObject.put("price", product.getOptions().get(i).getPrice());
                        jsonObject.put("color", product.getOptions().get(i).getColor());

                        jsonArray.put(jsonObject);
                    }
                    builder.add("options", jsonArray.toString());
                } else
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

    public void updateProduct(final Context context, final AddProduct product, final MutableLiveData<GeneralResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            GeneralResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "products/" + product.getId() + "/update";
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
                        .add("dimond", product.getDiamond() + "")
                        .add("gem_stone_weight", product.getStoneWeight() + "")
                        .add("metal_weight", product.getNetWeight() + "")
                        .add("total_metal_weight", product.getTotalWeightMetal() + "")
                        .add("cate_code", product.getCode() + "")
                        .add("weight", product.getWeight() + "")
                        .add("quantity", product.getQuantity() + "")
                        .add("discount_value", product.isBind_to_market() ? "0" : product.getDiscount() + "")
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
                    for (Media path : product.getMedia()) {
                        builder.add("images[]", path.getId() + "");
                    }
                }

                if (product.getOptions() != null && !product.getOptions().isEmpty()) {

                    JSONArray jsonArray = new JSONArray();
                    for (int i = 0; i < product.getOptions().size(); i++) {

                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("name_ar", product.getOptions().get(i).getNameAr() + "");
                        jsonObject.put("name_en", product.getOptions().get(i).getName());
                        jsonObject.put("available_quantity", product.getOptions().get(i).getAvailable_quantity() + "");
                        jsonObject.put("total_weight", product.getOptions().get(i).getTotal_weight());
                        jsonObject.put("bind_to_market", product.getOptions().get(i).isBind_to_market() ? "1" : "0");
                        jsonObject.put("price", product.getOptions().get(i).getPrice());
                        jsonObject.put("color", product.getOptions().get(i).getColor());

                        jsonArray.put(jsonObject);
                    }
                    builder.add("options", jsonArray.toString());
                } else
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

    ////////////////// Calibers ////////////////////////////
    public void getCalibers(final Context context, final MutableLiveData<CalibersResponse> loginResponseMutableLiveData) {

        new Thread(() -> {
            CalibersResponse loginSocialResponse;
            try {
                String url = RequestWrapper.getInstance().getFullPath() + "calibers/index";
                Request.Builder requestBuilder = RequestWrapper.getInstance().getRequestHeader(context);
                Request request = requestBuilder.url(url).get().build();

                Log.i(TAG, "Request: " + request + "\n " + RequestWrapper.getInstance().requestBodyToString(request));
                Response response = RequestWrapper.getInstance().getClient().newCall(request).execute();
                String responseString = response.body().string();

                Log.i(TAG, "calibers/index" + responseString);

                loginSocialResponse = RequestWrapper.getInstance().getGson().fromJson(responseString, CalibersResponse.class);


            } catch (Exception e) {
                e.printStackTrace();
                loginSocialResponse = new CalibersResponse();
                loginSocialResponse.setStatus(false);
                loginSocialResponse.setMessage("server error");

            }

            if (loginResponseMutableLiveData != null) {
                final CalibersResponse finalLoginSocialResponse = loginSocialResponse;
                new Handler(Looper.getMainLooper()).post(() -> loginResponseMutableLiveData.setValue(finalLoginSocialResponse));
            }

        }).start();

    }

}

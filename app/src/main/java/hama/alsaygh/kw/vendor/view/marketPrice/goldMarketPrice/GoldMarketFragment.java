package hama.alsaygh.kw.vendor.view.marketPrice.goldMarketPrice;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.FragmentGoldMarketBinding;
import hama.alsaygh.kw.vendor.listener.OnCurrencyListener;
import hama.alsaygh.kw.vendor.model.marketPrice.CaliberItem;
import hama.alsaygh.kw.vendor.model.marketPrice.MainCaliberItem;
import hama.alsaygh.kw.vendor.view.base.BaseFragment;
import hama.alsaygh.kw.vendor.view.marketPrice.goldMarketPrice.adapter.AdapterGoldMarketCaliber;
import hama.alsaygh.kw.vendor.view.marketPrice.goldMarketPrice.adapter.AdapterGoldMarketCurrency;

public class GoldMarketFragment extends BaseFragment implements OnCurrencyListener {


    FragmentGoldMarketBinding binding;
    GoldMarketViewModel model;

    List<String> currency = new ArrayList<>();
    FirebaseFirestore db;
    int count = 0;
    CountDownTimer countDownTimer;
    AdapterGoldMarketCurrency currencyAdapter;

    public GoldMarketFragment() {
    }

    public static GoldMarketFragment newInstance() {
        return new GoldMarketFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentGoldMarketBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        model = new GoldMarketViewModel();
        binding.setModel(model);
        db = FirebaseFirestore.getInstance();

        DocumentReference docRef = db.collection("Prices").document("Gold");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        currency.addAll(document.getData().keySet());
                        currencyAdapter = new AdapterGoldMarketCurrency(currency, GoldMarketFragment.this);
                        binding.rvCurrency.setAdapter(currencyAdapter);
                        for (int i = 0; i < currency.size(); i++) {
                            if (currency.get(i).equalsIgnoreCase("KWD")) {
                                currencyAdapter.setSelected(i);
                                break;
                            }
                        }
                        countDownTimer = new CountDownTimer(10000, 1000) {

                            @Override
                            public void onTick(long millisUntilFinished) {
                                String timer = getString(R.string.timer_price).replace("xx", 10 - (millisUntilFinished / 1000) + "");
                                binding.tvTimer.setText(timer);
                            }

                            @Override
                            public void onFinish() {

                                getCurrencyPrice(currencyAdapter.getSelected());
                                countDownTimer.start();
                            }
                        };
                        countDownTimer.start();

                        Log.d("TAG", "DocumentSnapshot data: " + document.getData() + " " + document.getId());
                    } else {
                        Log.d("TAG", "No such document");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });

    }

    @Override
    public void onCurrencyClick(String currency) {

        getCurrencyPrice(currency);
//        getGold(currency);
//        getOunca(currency);
    }

    private void getCurrencyPrice(String currency) {
        CollectionReference docRef = db.collection("Prices");

        docRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<MainCaliberItem> mainCaliberItems = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("TAG", document.getId() + " => " + document.getData());
                        if (document.getId().equalsIgnoreCase("ounca")) {
                            getOunca(currency, document);
                        } else {
                            getMainCaliberItem(currency, document, mainCaliberItems);
                        }
                    }
                    AdapterGoldMarketCaliber adapterGoldMarketCaliber = new AdapterGoldMarketCaliber(mainCaliberItems);
                    binding.rvGoldMarket.setAdapter(adapterGoldMarketCaliber);
                } else {
                    Log.w("TAG", "Error getting documents.", task.getException());
                }
            }
        });

    }

    private void getMainCaliberItem(String currency) {
        DocumentReference docRef = db.collection("Prices").document("Gold");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        MainCaliberItem mainCaliberItem = new MainCaliberItem();
                        List<CaliberItem> caliberItemList = new ArrayList<>();
                        for (String key : document.getData().keySet()) {
                            if (key.equalsIgnoreCase(currency)) {
                                String object = document.getData().get(key).toString();
                                try {
                                    JSONArray object1 = new JSONArray(object);
                                    for (int i = 0; i < object1.length(); i++) {
                                        JSONObject jsonObject = object1.getJSONObject(i);
                                        CaliberItem caliberItem = new CaliberItem();
                                        caliberItem.setCaliber(jsonObject.getString("caliber"));
                                        caliberItem.setIs_up(jsonObject.getString("is_up"));
                                        caliberItem.setPrice(jsonObject.getDouble("price"));

                                        caliberItemList.add(caliberItem);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                mainCaliberItem.setCaliberItems(caliberItemList);
                                List<MainCaliberItem> mainCaliberItems = new ArrayList<>();
                                mainCaliberItems.add(mainCaliberItem);
                                AdapterGoldMarketCaliber adapterGoldMarketCaliber = new AdapterGoldMarketCaliber(mainCaliberItems);
                                binding.rvGoldMarket.setAdapter(adapterGoldMarketCaliber);
                                break;
                            }
                        }

                        Log.d("TAG", "DocumentSnapshot data: " + document.getData() + " " + document.getId());
                    } else {
                        Log.d("TAG", "No such document");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });
    }

    private void getOunca(String currency) {
        DocumentReference docRef = db.collection("Prices").document("ounca");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        for (String key : document.getData().keySet()) {

                            if (key.equalsIgnoreCase(currency)) {
                                try {
                                    String object = document.getData().get(key).toString();
                                    JSONObject jsonObject = new JSONObject(object);
                                    CaliberItem caliberItem = new CaliberItem();
                                    caliberItem.setIs_up(jsonObject.getString("is_up"));
                                    caliberItem.setPrice(jsonObject.getDouble("price"));
                                    binding.textView73.setText(getString(R.string.oz_price).replace("xx", caliberItem.getPrice() + ""));
                                    binding.textView74.setText(caliberItem.getPrice() + "");
                                    if (!caliberItem.isUp())
                                        binding.imageView13.setImageResource(R.drawable.ic_ounca_down);
                                    else {

                                        binding.imageView13.setImageResource(R.drawable.path_gold);

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                break;
                            }
                        }

                        Log.d("TAG", "DocumentSnapshot data: " + document.getData() + " " + document.getId());
                    } else {
                        Log.d("TAG", "No such document");
                    }
                } else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });
    }

    private void getOunca(String currency, QueryDocumentSnapshot document) {
        for (String key : document.getData().keySet()) {

            if (key.equalsIgnoreCase(currency)) {
                try {
                    String object = document.getData().get(key).toString();
                    JSONObject jsonObject = new JSONObject(object);
                    CaliberItem caliberItem = new CaliberItem();
                    caliberItem.setIs_up(jsonObject.getString("is_up"));
                    caliberItem.setPrice(jsonObject.getDouble("price"));
                    String priceFormatted = String.format(Locale.ENGLISH, "%.3f", caliberItem.getPrice());
                    binding.textView73.setText(requireContext().getString(R.string.oz_price).replace("xx", priceFormatted));
                    binding.textView74.setText(priceFormatted);
                    if (!caliberItem.isUp())
                        binding.imageView13.setImageResource(R.drawable.ic_ounca_down);
                    else {
                        binding.imageView13.setImageResource(R.drawable.path_gold);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
            }
        }
    }

    private void getMainCaliberItem(String currency, QueryDocumentSnapshot document, List<MainCaliberItem> mainCaliberItems) {
        MainCaliberItem mainCaliberItem = new MainCaliberItem();
        List<CaliberItem> caliberItemList = new ArrayList<>();
        for (String key : document.getData().keySet()) {
            if (key.equalsIgnoreCase(currency)) {
                String object = document.getData().get(key).toString();
                try {
                    JSONArray object1 = new JSONArray(object);
                    for (int i = 0; i < object1.length(); i++) {
                        JSONObject jsonObject = object1.getJSONObject(i);
                        CaliberItem caliberItem = new CaliberItem();
                        caliberItem.setCaliber(jsonObject.getString("caliber"));
                        caliberItem.setIs_up(jsonObject.getString("is_up"));
                        caliberItem.setPrice(jsonObject.getDouble("price"));

                        caliberItemList.add(caliberItem);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mainCaliberItem.setCaliberItems(caliberItemList);

                mainCaliberItems.add(mainCaliberItem);

                break;
            }

        }
    }

    @Override
    public void onStop() {
        super.onStop();
        count = 10;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }
}

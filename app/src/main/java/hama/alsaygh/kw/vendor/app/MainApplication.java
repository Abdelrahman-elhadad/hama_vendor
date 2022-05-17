package hama.alsaygh.kw.vendor.app;

import android.net.NetworkInfo;
import android.os.StrictMode;

import androidx.multidex.MultiDexApplication;

import com.github.pwittchen.reactivenetwork.library.Connectivity;
import com.github.pwittchen.reactivenetwork.library.ReactiveNetwork;

import hama.alsaygh.kw.vendor.utils.LocalUtils;
import hama.alsaygh.kw.vendor.utils.Utils;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainApplication extends MultiDexApplication {


    public static boolean isConnected = true;
    public static boolean networkAvailable;


    @Override
    public void onCreate() {

        super.onCreate();

        Utils.getInstance().setContext(this);
        LocalUtils.getInstance().updateResources(this, LocalUtils.getInstance().getLanguageShort(this));

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        try {
            ReactiveNetwork.observeNetworkConnectivity(this)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Connectivity>() {
                        @Override
                        public void call(Connectivity connectivity) {
                            networkAvailable = connectivity.getState() == NetworkInfo.State.CONNECTED;
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ReactiveNetwork.observeInternetConnectivity()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean isConnectedToInternet) {
                            if (networkAvailable) {
                                isConnected = true;
                            } else
                                isConnected = isConnectedToInternet;
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }



    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}

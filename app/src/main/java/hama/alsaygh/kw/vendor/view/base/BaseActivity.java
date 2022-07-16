package hama.alsaygh.kw.vendor.view.base;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.utils.LocalUtils;

public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setLightStatusBar();
        getSupportActionBar().hide(); //<< this
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalUtils.getInstance().updateResources(this, LocalUtils.getInstance().getLanguageShort(this));

    }

    private void setStatusBarColorDark() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.sign_in));

    }

//    private void setLightStatusBar() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            int flags = getWindow().getDecorView().getSystemUiVisibility(); // get current flag
//            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;   // add LIGHT_STATUS_BAR to flag
//            getWindow().getDecorView().setSystemUiVisibility(flags);
//            getWindow().setStatusBarColor(R.c); // optional
//        }
//    }
private void setLightStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        int flags = getWindow().getDecorView().getSystemUiVisibility(); // get current flag
        flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;   // add LIGHT_STATUS_BAR to flag
        getWindow().getDecorView().setSystemUiVisibility(flags);
        getWindow().setStatusBarColor(Color.TRANSPARENT); // optional
    }
}
}

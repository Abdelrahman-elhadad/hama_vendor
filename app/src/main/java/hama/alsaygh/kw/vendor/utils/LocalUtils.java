package hama.alsaygh.kw.vendor.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;

import java.util.Locale;


public class LocalUtils {

    private static LocalUtils localUtils;

    private LocalUtils() {
    }

    public static LocalUtils getInstance() {
        if (localUtils == null)
            localUtils = new LocalUtils();
        return localUtils;
    }


    public boolean updateResources(Context context, String lan) {


        Locale locale = new Locale(lan);
        Locale.setDefault(locale);

        Resources resources = context.getResources();

        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
        configuration.setLayoutDirection(locale);

        resources.updateConfiguration(configuration, resources.getDisplayMetrics());


        return true;
    }

    public void setLanguage(Context context, String language) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lan", language);
        editor.apply();
    }

    private String getLanguage(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
        // return sharedPreferences.getString("lan", "default");
        return sharedPreferences.getString("lan", "en");
    }


    public String getLanguageShort(Context context) {

        String language = getLanguage(context);


        String lan;
        if (language.equalsIgnoreCase("default")) {

            try {
                PackageManager packageManager = context.getPackageManager();
                Resources resources = packageManager.getResourcesForApplication("android");
                lan = resources.getConfiguration().locale.getLanguage();
                Log.e("language", " " + lan);
            } catch (Exception e) {
                e.printStackTrace();
                lan = Locale.getDefault().getLanguage();
            }

        } else
            lan = language;

        return lan;
    }


    public String getLanguageDisplay(Context context) {

        String language = getLanguage(context);
        String lan;
        if (language.equalsIgnoreCase("default")) {

            try {
                PackageManager packageManager = context.getPackageManager();
                Resources resources = packageManager.getResourcesForApplication("android");
                lan = resources.getConfiguration().locale.getDisplayLanguage(resources.getConfiguration().locale);
                Log.e("language", " " + lan);
            } catch (Exception e) {
                e.printStackTrace();
                lan = Locale.getDefault().getDisplayLanguage(Locale.getDefault());
            }

        } else {

            Locale locale = new Locale(language);
            lan = locale.getDisplayLanguage(locale);
        }

        return lan;
    }


    public boolean isSelected(Context context, Locale locale) {

        String language = getLanguage(context);
        String lan;
        if (language.equalsIgnoreCase("default")) {

            try {
                PackageManager packageManager = context.getPackageManager();
                Resources resources = packageManager.getResourcesForApplication("android");
                lan = resources.getConfiguration().locale.getLanguage();
                Log.e("language", " " + lan);
            } catch (Exception e) {
                e.printStackTrace();
                lan = Locale.getDefault().getLanguage();
            }

        } else {

            lan = language;
        }

        return lan.equalsIgnoreCase(locale.getLanguage());
    }
}
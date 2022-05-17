package hama.alsaygh.kw.vendor.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

public class CheckAndRequestPermission {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    public static final int MY_PERMISSIONS_REQUEST_PHONE_CALL = 2;
    public static final int MY_PERMISSIONS_REQUEST_STORAGE = 3;


    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void requestLocation(AppCompatActivity context) {
        ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
    }

    public static void requestLocation(Fragment context) {
        context.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
    }

    public static void requestPhoneCall(AppCompatActivity context) {
        ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_PHONE_CALL);
    }

    public static void requestStorage(AppCompatActivity context) {
        ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_STORAGE);
    }

    public static void requestStorage(Fragment context) {
        context.requestPermissions( new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_STORAGE);
    }

    /* Description: A method used to check if the user selected "never ask again" button when system request
     permissions dialog was appeared asking a user give a permission to access system resources.
     Developer: Hanady Irheem*/
    public static boolean isNeverAskSelected(@NonNull String[] permissions, Activity activity) {

        boolean showRationale = true;
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                showRationale = showRationale && ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
            }
        }
        return !showRationale;
    }

    public static boolean isFoundPermissionDenied(@NonNull int[] grantResults) {
        boolean isFoundPermissionDenied = false;
        for (int grantResult : grantResults) {
            if (grantResult != PackageManager.PERMISSION_GRANTED) {
                isFoundPermissionDenied = true;
            }
        }
        return isFoundPermissionDenied;
    }

}

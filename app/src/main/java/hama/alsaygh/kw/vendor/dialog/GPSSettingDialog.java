package hama.alsaygh.kw.vendor.dialog;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import hama.alsaygh.kw.vendor.R;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;



public class GPSSettingDialog extends DialogFragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = View.inflate(getActivity(), R.layout.dialog_gps_setting, null);

        TextView tvCancel = view.findViewById(R.id.tv_cancel);
        TextView tvOk = view.findViewById(R.id.tv_setting);

        final Dialog dialog = new Dialog(requireActivity(), android.R.style.Theme_NoTitleBar_OverlayActionModes);
//        final Dialog dialog = new Dialog(getActivity());
        dialog.setCanceledOnTouchOutside(false);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(0));

        tvOk.setOnClickListener(arg0 -> {

            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
            dialog.dismiss();
        });
        tvCancel.setOnClickListener(v -> dialog.dismiss());

        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.dimAmount = 0.7f;
        lp.y = -200;
        lp.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setContentView(view);
        dialog.show();
        return dialog;
    }

    @Override
    public void show(@NotNull FragmentManager manager, String tag) {
        try {
            FragmentTransaction ft = manager.beginTransaction();

            if (isAdded()) {
                ft.remove(this);
                ft.commitAllowingStateLoss();
            }
            ft.add(this, tag);
            ft.commitAllowingStateLoss();
        } catch (Exception e) {
            Log.d("ABSDIALOGFRAG", "Exception", e);
        }
    }
}
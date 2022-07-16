package hama.alsaygh.kw.vendor.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.view.auth.login.LoginActivity;
//import hama.alsaygh.kw.vendor.view.auth.LoginActivity;


public class LoginDialog extends BottomSheetDialogFragment implements View.OnClickListener {
    public static final String TAG = "ActionBottomDialog";
    private ItemClickListener mListener;

    public static LoginDialog newInstance() {
        return new LoginDialog();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ItemClickListener) {
            mListener = (ItemClickListener) context;
        } else {
            //  throw new RuntimeException(context.toString()
            //        + " must implement ItemClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface ItemClickListener {
        void onItemClick(String item);
    }

    @Override
    public void onClick(View v) {
        TextView tvSelected = (TextView) v;
        mListener.onItemClick(tvSelected.getText().toString());
        dismiss();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
        this.setCancelable(false);

    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(@NonNull final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View view = View.inflate(getContext(), R.layout.dialog_login, null);


        ImageView imageView20 = view.findViewById(R.id.imageView20);
        AppCompatButton button5 = view.findViewById(R.id.button5);


        button5.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), LoginActivity.class);
            startActivity(intent);
            dismiss();
        });

        imageView20.setOnClickListener(v -> dismiss());


        dialog.setContentView(view);
        this.setCancelable(false);

    }

}

package hama.alsaygh.kw.vendor.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;


public class SelectImageDialog extends BottomSheetDialogFragment {


    LinearLayout ll_gallery, ll_camera;
    ImageView icClose;
    TextView textView80;
    View view25;


    OnGeneralClickListener onMyCartListener;

    public void setOnMyCartListener(OnGeneralClickListener onMyCartListener) {
        this.onMyCartListener = onMyCartListener;
    }


    public static SelectImageDialog newInstance(OnGeneralClickListener onMyCartListener) {

        SelectImageDialog fragment = new SelectImageDialog();
        fragment.setOnMyCartListener(onMyCartListener);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.dialog_select_image, container, false);
        icClose = view.findViewById(R.id.imageView28);
        textView80 = view.findViewById(R.id.textView80);
        view25 = view.findViewById(R.id.view25);
        ll_gallery = view.findViewById(R.id.ll_gallery);
        ll_camera = view.findViewById(R.id.ll_camera);

        icClose.setOnClickListener(v -> dismiss());

        ll_gallery.setOnClickListener(v -> {
            if (onMyCartListener != null)
                onMyCartListener.onItemClick("gallery", 0);
            dismiss();
        });

        ll_camera.setOnClickListener(v -> {
            if (onMyCartListener != null)
                onMyCartListener.onItemClick("camera", 1);
            dismiss();
        });


        return view;
    }
}

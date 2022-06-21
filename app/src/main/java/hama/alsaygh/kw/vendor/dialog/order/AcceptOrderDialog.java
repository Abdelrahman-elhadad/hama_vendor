package hama.alsaygh.kw.vendor.dialog.order;

import static hama.alsaygh.kw.vendor.view.order.OrdersViewModel.IN_PROGRESS;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.app.MainApplication;
import hama.alsaygh.kw.vendor.dialog.LoginDialog;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.check.CheckResponse;
import hama.alsaygh.kw.vendor.repo.OrderRepo;
import hama.alsaygh.kw.vendor.utils.AppConstants;
import hama.alsaygh.kw.vendor.view.gongrats.GongratsActivity;


public class AcceptOrderDialog extends BottomSheetDialogFragment {


    LinearLayout parent_delete_p;
    TextView textView80, textView135, tv_back;
    View view25;
    Button button3;
    ProgressBar pb_delete;

    OnGeneralClickListener onMyCartListener;
    int product_id;
    MutableLiveData<CheckResponse> loginResponseMutableLiveData = new MutableLiveData<>();

    public void setOnMyCartListener(OnGeneralClickListener onMyCartListener) {
        this.onMyCartListener = onMyCartListener;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public static AcceptOrderDialog newInstance(int product_id, OnGeneralClickListener onMyCartListener) {

        AcceptOrderDialog fragment = new AcceptOrderDialog();
        fragment.setProduct_id(product_id);
        fragment.setOnMyCartListener(onMyCartListener);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.dialog_accept_order, container, false);
        tv_back = view.findViewById(R.id.tv_back);
        textView80 = view.findViewById(R.id.textView80);
        textView135 = view.findViewById(R.id.textView135);
        view25 = view.findViewById(R.id.view25);
        button3 = view.findViewById(R.id.button3);
        pb_delete = view.findViewById(R.id.pb_delete);
        parent_delete_p = view.findViewById(R.id.parent_delete_p);


        tv_back.setOnClickListener(v -> dismiss());

        loginResponseMutableLiveData.observe(requireActivity(), productsResponse -> {
            pb_delete.setVisibility(View.GONE);
            button3.setVisibility(View.VISIBLE);
            if (productsResponse.isStatus()) {
                dismiss();
                requireActivity().finish();
                Intent intent = new Intent(requireContext(), GongratsActivity.class);
                intent.putExtra(AppConstants.ORDER_ID, product_id);
                intent.putExtra(AppConstants.ORDER_STATUS, IN_PROGRESS);
                startActivity(intent);

            } else {
                if (productsResponse.getCode().equalsIgnoreCase("401")) {
                    LoginDialog.newInstance().show(getChildFragmentManager(), "login");
                    dismiss();
                } else {
                    Snackbar.make(button3, productsResponse.getMessage(), Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        button3.setOnClickListener(v -> {

            if (MainApplication.isConnected) {

                pb_delete.setVisibility(View.VISIBLE);
                button3.setVisibility(View.GONE);
                new OrderRepo().acceptOrder(v.getContext(), product_id, loginResponseMutableLiveData);

            } else {
                Snackbar.make(v, v.getContext().getString(R.string.no_internet_connection), Snackbar.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}

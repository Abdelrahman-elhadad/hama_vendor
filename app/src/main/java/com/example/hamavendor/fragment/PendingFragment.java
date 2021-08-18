package com.example.hamavendor.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hamavendor.R;

import java.io.Serializable;
import java.util.ArrayList;

public class PendingFragment  extends BaseFragment {
    RecyclerView recyclerView1;
    ArrayList<Serializable> best_stores;
    ImageView imagetoolbar;
    FragmentTransaction fragmentTransaction;
    static FragmentManager fragmentManager;
    boolean isReverse = false;
    ImageView img_toolbar, img_notification, img_love;
    TextView homeText;
    LinearLayout liner_all_stores;


    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pending_fragment, container, false);
        recyclerView1 = (RecyclerView) view.findViewById(R.id.rv_pending);
        //  imagetoolbar = (ImageView) view.findViewById(R.id.img_toolbar);

        fragmentManager = getFragmentManager();
        imagetoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = fragmentManager.beginTransaction();
                //   fragmentTransaction.replace(R.id.liner1, new MarketPrice());
                fragmentTransaction.commit();
            }
        });

        return view;

    }
}

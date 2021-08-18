package com.example.hamavendor.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hamavendor.R;
import com.example.hamavendor.adapter.AdapterActiveOffers;
import com.example.hamavendor.array.activeOffers;

import java.util.ArrayList;

public class ActiveOffers extends BaseFragment {
    FragmentTransaction fragmentTransaction;
    static FragmentManager fragmentManager;
    RecyclerView active_offer_rv;
    ArrayList<activeOffers> activeOffers;
    AdapterActiveOffers adapterActiveOffers;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentManager=getChildFragmentManager();
        View view = inflater.inflate(R.layout.active_offers_fragment, container, false);
        active_offer_rv=(RecyclerView)view.findViewById(R.id.active_offer);

        activeOffers = new ArrayList<>();
        activeOffers bs = new activeOffers(R.drawable.activeOffers_img, "Gold Ring");
        activeOffers bs2 = new activeOffers(R.drawable.activeOffers_img, "Gold Ring");
        activeOffers bs3 = new activeOffers(R.drawable.activeOffers_img, "Gold Ring");
        activeOffers bs4 = new activeOffers(R.drawable.activeOffers_img, "Gold Ring");
        activeOffers.add(bs);
        activeOffers.add(bs2);
        activeOffers.add(bs3);
        activeOffers.add(bs4);
        adapterActiveOffers = new AdapterActiveOffers(activeOffers);
        active_offer_rv.setAdapter(adapterActiveOffers);

       active_offer_rv.setLayoutManager(new GridLayoutManager(getActivity(), 2));



return view;

    }
}

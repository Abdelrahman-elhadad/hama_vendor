package hama.alsaygh.kw.vendor.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.view.base.BaseFragment;

public class AddNewOffers extends BaseFragment {
    FragmentTransaction fragmentTransaction;
    static FragmentManager fragmentManager;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentManager=getChildFragmentManager();
        View view = inflater.inflate(R.layout.add_new_offer_fragment, container, false);
        return view;

    }
}

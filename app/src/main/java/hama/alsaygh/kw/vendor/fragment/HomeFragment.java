package hama.alsaygh.kw.vendor.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.view.base.BaseFragment;

public class HomeFragment extends BaseFragment {


    FragmentTransaction fragmentTransaction;
    static FragmentManager fragmentManager;
    ImageView imageback;
    Button orderTracking;
    ViewPager viewPager;
    //ArrayList<MyorderArray> myordersArray;
    TextView txt_toolbar;
    LinearLayout parent_my_order;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragmentManager = getFragmentManager();


    }
}
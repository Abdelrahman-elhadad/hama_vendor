package hama.alsaygh.kw.vendor.view.offers.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.view.offers.MainOffersViewModel;
import hama.alsaygh.kw.vendor.view.offers.activeOffer.ActiveOffersFragment;
import hama.alsaygh.kw.vendor.view.offers.addOffers.AddOffersFragment;


public class AdapterPagerOffers extends FragmentPagerAdapter {

    private final List<OfferStatus> orderStatusList = new ArrayList<>();

    public AdapterPagerOffers(Context context, @NonNull FragmentManager fm) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        orderStatusList.add(new OfferStatus(MainOffersViewModel.ACTIVE_OFFER, context.getString(R.string.Active_Offers)));
        orderStatusList.add(new OfferStatus(MainOffersViewModel.ADD_OFFER, context.getString(R.string.Add_New_Offer)));

    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == MainOffersViewModel.ADD_OFFER) {
            return AddOffersFragment.newInstance();
        }
        return ActiveOffersFragment.newInstance();
    }


    @Override
    public int getCount() {
        return orderStatusList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return orderStatusList.get(position).getName();
    }
}

class OfferStatus {
    private int type;
    private String name;

    public OfferStatus() {

    }

    public OfferStatus(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}


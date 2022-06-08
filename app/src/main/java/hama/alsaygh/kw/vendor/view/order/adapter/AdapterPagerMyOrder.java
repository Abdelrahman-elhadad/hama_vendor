package hama.alsaygh.kw.vendor.view.order.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.view.order.OrdersViewModel;
import hama.alsaygh.kw.vendor.view.order.statusOrder.OrdersStatusFragment;


public class AdapterPagerMyOrder extends FragmentPagerAdapter {

    private final List<OrderStatus> orderStatusList = new ArrayList<>();

    public AdapterPagerMyOrder(Context context, @NonNull FragmentManager fm) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        orderStatusList.add(new OrderStatus(OrdersViewModel.PENDING, context.getString(R.string.Pending)));
        orderStatusList.add(new OrderStatus(OrdersViewModel.COMPLETED, context.getString(R.string.Completed)));
        orderStatusList.add(new OrderStatus(OrdersViewModel.CANCELED, context.getString(R.string.Canceled)));

    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return OrdersStatusFragment.newInstance(orderStatusList.get(position).getType());
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

class OrderStatus {
    private int type;
    private String name;

    public OrderStatus() {

    }

    public OrderStatus(int type, String name) {
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


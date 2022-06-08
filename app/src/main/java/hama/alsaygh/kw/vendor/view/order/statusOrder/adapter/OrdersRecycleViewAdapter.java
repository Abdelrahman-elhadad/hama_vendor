package hama.alsaygh.kw.vendor.view.order.statusOrder.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.ItemRvOrdersBinding;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.order.Order;


public class OrdersRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int type;
    private List<Order> countries;
    private final OnGeneralClickListener onGeneralClickListener;

    public OrdersRecycleViewAdapter(List<Order> countries, int type, OnGeneralClickListener onGeneralClickListener) {
        this.countries = countries;
        this.onGeneralClickListener = onGeneralClickListener;
        this.type = type;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemRvOrdersBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_rv_orders, parent, false);
        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CategoryViewHolder viewHolder = (CategoryViewHolder) holder;
        viewHolder.bind(new OrdersViewModel(viewHolder.binding.rlImages.getContext(), countries.get(position), type, onGeneralClickListener, position));
    }

    @Override
    public int getItemCount() {
        return countries == null ? 0 : countries.size();
    }


    public void addItems(List<Order> items) {
        if (countries == null)
            countries = new ArrayList<>();
        countries.addAll(items);
        notifyDataSetChanged();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        final ItemRvOrdersBinding binding;

        CategoryViewHolder(ItemRvOrdersBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(OrdersViewModel viewModel) {
            binding.setModel(viewModel);
            binding.executePendingBindings();
        }
    }
}
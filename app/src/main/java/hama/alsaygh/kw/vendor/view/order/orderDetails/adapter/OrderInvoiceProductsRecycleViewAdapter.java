package hama.alsaygh.kw.vendor.view.order.orderDetails.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.ItemRvOrderInvoiceProductBinding;
import hama.alsaygh.kw.vendor.model.cart.CartItem;


public class OrderInvoiceProductsRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int type;
    private List<CartItem> countries;

    public OrderInvoiceProductsRecycleViewAdapter(List<CartItem> countries, int type) {
        this.countries = countries;
        this.type = type;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemRvOrderInvoiceProductBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_rv_order_invoice_product, parent, false);
        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CategoryViewHolder viewHolder = (CategoryViewHolder) holder;
        viewHolder.bind(new OrderProductViewModel(viewHolder.binding.tvName.getContext(), countries.get(position), type, null, position));
    }

    @Override
    public int getItemCount() {
        return countries == null ? 0 : countries.size();
    }


    public void addItems(List<CartItem> items) {
        if (countries == null)
            countries = new ArrayList<>();
        countries.addAll(items);
        notifyDataSetChanged();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        final ItemRvOrderInvoiceProductBinding binding;

        CategoryViewHolder(ItemRvOrderInvoiceProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(OrderProductViewModel viewModel) {
            binding.setModel(viewModel);
            binding.executePendingBindings();
        }
    }
}
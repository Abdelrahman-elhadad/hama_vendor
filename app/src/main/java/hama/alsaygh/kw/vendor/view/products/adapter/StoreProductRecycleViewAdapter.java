package hama.alsaygh.kw.vendor.view.products.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.ItemRvMyProuductBinding;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.product.Product;


public class StoreProductRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Product> countries;
    private final OnGeneralClickListener onGeneralClickListener;

    public StoreProductRecycleViewAdapter(List<Product> countries, OnGeneralClickListener onGeneralClickListener) {
        this.countries = countries;
        this.onGeneralClickListener = onGeneralClickListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemRvMyProuductBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_rv_my_prouduct, parent, false);
        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CategoryViewHolder viewHolder = (CategoryViewHolder) holder;
        viewHolder.bind(new StoreProductViewModel(viewHolder.binding.loveImg.getContext(), countries.get(position), onGeneralClickListener, position));
    }

    @Override
    public int getItemCount() {
        return countries == null ? 0 : countries.size();
    }


    public void addItems(List<Product> items) {
        if (countries == null)
            countries = new ArrayList<>();
        countries.addAll(items);
        notifyDataSetChanged();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        final ItemRvMyProuductBinding binding;

        CategoryViewHolder(ItemRvMyProuductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(StoreProductViewModel viewModel) {
            binding.setModel(viewModel);
            binding.executePendingBindings();
        }
    }
}
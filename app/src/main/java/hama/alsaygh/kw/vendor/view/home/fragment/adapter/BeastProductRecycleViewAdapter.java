package hama.alsaygh.kw.vendor.view.home.fragment.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.ItemRv1BeastProductHomeBinding;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.product.Product;


public class BeastProductRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Product> countries;
    private final OnGeneralClickListener onGeneralClickListener;


    public BeastProductRecycleViewAdapter(List<Product> countries, OnGeneralClickListener onGeneralClickListener) {
        this.countries = countries;
        this.onGeneralClickListener = onGeneralClickListener;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemRv1BeastProductHomeBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_rv1_beast_product_home, parent, false);
        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CategoryViewHolder viewHolder = (CategoryViewHolder) holder;
        viewHolder.bind(new BeastProductViewModel(viewHolder.binding.pro.getContext(), countries.get(position), onGeneralClickListener, position));
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

    public void removeItem(int id) {
        if (countries == null)
            countries = new ArrayList<>();
        for (int i = 0; i < countries.size(); i++) {
            if (countries.get(i).getId() == id) {
                countries.remove(i);
                break;
            }
        }
        notifyDataSetChanged();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        final ItemRv1BeastProductHomeBinding binding;

        CategoryViewHolder(ItemRv1BeastProductHomeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(BeastProductViewModel viewModel) {
            binding.setModel(viewModel);
            binding.executePendingBindings();
        }
    }
}
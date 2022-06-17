package hama.alsaygh.kw.vendor.view.offers.activeOffer.adapter;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.ItemRvActiveOfferBinding;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.product.Product;


public class ActiveOffersRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Product> countries;
    private final OnGeneralClickListener onGeneralClickListener;
    private final FragmentManager fragmentManager;

    public ActiveOffersRecycleViewAdapter(List<Product> countries, FragmentManager fragmentManager, OnGeneralClickListener onGeneralClickListener) {
        this.countries = countries;
        this.onGeneralClickListener = onGeneralClickListener;
        this.fragmentManager = fragmentManager;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemRvActiveOfferBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_rv_active_offer, parent, false);
        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CategoryViewHolder viewHolder = (CategoryViewHolder) holder;
        ((CategoryViewHolder) holder).binding.tvPriceBefore.setPaintFlags(((CategoryViewHolder) holder).binding.tvPriceBefore.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        viewHolder.bind(new ActiveOffersViewModel(viewHolder.binding.loveImg.getContext(), countries.get(position), fragmentManager, onGeneralClickListener, position));
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
        final ItemRvActiveOfferBinding binding;

        CategoryViewHolder(ItemRvActiveOfferBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ActiveOffersViewModel viewModel) {
            binding.setModel(viewModel);
            binding.executePendingBindings();
        }
    }
}
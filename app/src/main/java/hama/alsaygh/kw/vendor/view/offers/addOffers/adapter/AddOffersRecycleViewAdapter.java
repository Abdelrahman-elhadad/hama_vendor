package hama.alsaygh.kw.vendor.view.offers.addOffers.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.ItemAddNewOfferBinding;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.product.Product;


public class AddOffersRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Product> countries;
    private final OnGeneralClickListener onGeneralClickListener;
    private final FragmentManager fragmentManager;

    int selectedPosition = -1;

    public AddOffersRecycleViewAdapter(List<Product> countries, FragmentManager fragmentManager, OnGeneralClickListener onGeneralClickListener) {
        this.countries = countries;
        this.onGeneralClickListener = onGeneralClickListener;
        this.fragmentManager = fragmentManager;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemAddNewOfferBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_add_new_offer, parent, false);
        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CategoryViewHolder viewHolder = (CategoryViewHolder) holder;
        viewHolder.bind(new AddOffersViewModel(viewHolder.binding.ivSelect.getContext(), countries.get(position), fragmentManager, onGeneralClickListener, position));

        if (selectedPosition == position) {
            viewHolder.binding.ivSelect.setImageDrawable(ContextCompat.getDrawable(viewHolder.binding.ivSelect.getContext(), R.drawable.ic_check));
        } else
            viewHolder.binding.ivSelect.setImageDrawable(ContextCompat.getDrawable(viewHolder.binding.ivSelect.getContext(), R.drawable.ic_uncheck));
        viewHolder.itemView.setOnClickListener(v -> setSelected(position));

    }

    public void setSelected(int position) {
        selectedPosition = position;
        if (onGeneralClickListener != null)
            onGeneralClickListener.onItemClick(countries.get(position), position);
        notifyDataSetChanged();

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
        final ItemAddNewOfferBinding binding;

        CategoryViewHolder(ItemAddNewOfferBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(AddOffersViewModel viewModel) {
            binding.setModel(viewModel);
            binding.executePendingBindings();
        }
    }
}
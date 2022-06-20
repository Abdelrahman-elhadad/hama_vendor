package hama.alsaygh.kw.vendor.view.home.fragment.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.ItemRvUserHomeBinding;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.home.HomeUserData;


public class UsersRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HomeUserData> countries;
    private final OnGeneralClickListener onGeneralClickListener;


    public UsersRecycleViewAdapter(List<HomeUserData> countries, OnGeneralClickListener onGeneralClickListener) {
        this.countries = countries;
        this.onGeneralClickListener = onGeneralClickListener;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemRvUserHomeBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_rv_user_home, parent, false);
        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CategoryViewHolder viewHolder = (CategoryViewHolder) holder;
        viewHolder.bind(new UsersViewModel(viewHolder.binding.goldRin.getContext(), countries.get(position), onGeneralClickListener, position));
    }

    @Override
    public int getItemCount() {
        return countries == null ? 0 : countries.size();
    }


    public void addItems(List<HomeUserData> items) {
        if (countries == null)
            countries = new ArrayList<>();
        countries.addAll(items);
        notifyDataSetChanged();
    }


    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        final ItemRvUserHomeBinding binding;

        CategoryViewHolder(ItemRvUserHomeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(UsersViewModel viewModel) {
            binding.setModel(viewModel);
            binding.executePendingBindings();
        }
    }
}
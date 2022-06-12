package hama.alsaygh.kw.vendor.view.products.addProduct.adapter.caliber;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.ItemRvCaliberBinding;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.product.caliber.Caliber;


public class CalibersRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Caliber> countries;
    private final OnGeneralClickListener onGeneralClickListener;
    int selected = -1;

    public CalibersRecycleViewAdapter(List<Caliber> countries, OnGeneralClickListener onGeneralClickListener) {
        this.countries = countries;
        this.onGeneralClickListener = onGeneralClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemRvCaliberBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_rv_caliber, parent, false);
        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CategoryViewHolder viewHolder = (CategoryViewHolder) holder;
        viewHolder.bind(new CalibersViewModel(viewHolder.binding.tvItem.getContext(), countries.get(position)));

        viewHolder.binding.llMain.setOnClickListener(v -> setSelected(position));
    }


    public void setSelected(int position) {
        Caliber caliber = countries.get(position);
        countries.remove(position);
        countries.add(position, caliber);

        selected = position;
        if (onGeneralClickListener != null)
            onGeneralClickListener.onItemClick(countries.get(position), position);
        notifyDataSetChanged();

    }

    public void setSelectedWithoutClick(int position) {
        Caliber caliber = countries.get(position);
        countries.remove(position);
        countries.add(position, caliber);

        selected = position;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return countries == null ? 0 : countries.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        final ItemRvCaliberBinding binding;

        CategoryViewHolder(ItemRvCaliberBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(CalibersViewModel viewModel) {
            binding.setModel(viewModel);
            if (getAdapterPosition() == selected) {
                binding.tvItem.setTextColor(ContextCompat.getColor(binding.tvItem.getContext(), R.color.white));
                binding.llMain.setBackgroundResource(R.drawable.back_item_rv);
            } else {
                binding.tvItem.setTextColor(ContextCompat.getColor(binding.tvItem.getContext(), R.color.text_not_selected));
                binding.llMain.setBackgroundResource(R.drawable.back_item_rv_not_selected);
            }

            binding.executePendingBindings();
        }
    }
}
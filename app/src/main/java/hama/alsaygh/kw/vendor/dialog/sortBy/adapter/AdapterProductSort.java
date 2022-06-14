package hama.alsaygh.kw.vendor.dialog.sortBy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.listener.OnSortDialogListener;
import hama.alsaygh.kw.vendor.model.sort.FilterProduct;

public class AdapterProductSort extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<FilterProduct> best_stores;
    Context context;
    OnSortDialogListener onSortDialogListener;
    String sortBy;

    public AdapterProductSort(Context context, List<FilterProduct> Best_stores, String sortBy, OnSortDialogListener onSortDialogListener) {
        this.best_stores = Best_stores;
        this.context = context;
        this.onSortDialogListener = onSortDialogListener;
        this.sortBy = sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sorting, parent, false);
        return new AdapterProductSort.Holder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int index) {
        Holder holder = (Holder) viewHolder;
        final int position = index;
        final FilterProduct filterProduct = best_stores.get(position);

        holder.view.setBackgroundColor(ContextCompat.getColor(context, R.color.my_cart2));
        holder.radioButton.setButtonTintList(ContextCompat.getColorStateList(context, R.color.color_navigation));
        holder.radioButton.setHighlightColor(ContextCompat.getColor(context, R.color.color_navigation));
        holder.radioButton.setTextColor(ContextCompat.getColor(context, R.color.text));


        holder.radioButton.setText(filterProduct.getTrans());
        holder.radioButton.setClickable(false);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sortBy = filterProduct.getKey();
                if (onSortDialogListener != null) {
                    onSortDialogListener.onSortByClick(sortBy);
                }
                best_stores.remove(position);
                best_stores.add(position, filterProduct);
                notifyDataSetChanged();
            }
        });

        if (sortBy.equalsIgnoreCase(filterProduct.getKey())) {
            holder.radioButton.setChecked(true);
        } else
            holder.radioButton.setChecked(false);
    }

    @Override
    public int getItemCount() {
        return best_stores.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        RadioButton radioButton;
        View view;


        public Holder(@NonNull View itemView) {
            super(itemView);
            radioButton = itemView.findViewById(R.id.radioButton1);
            view = itemView.findViewById(R.id.view8);

        }


    }

}

package hama.alsaygh.kw.vendor.dialog.filterBy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.listener.OnFilterDialogListener;
import hama.alsaygh.kw.vendor.model.category.Category;

public class AdapterProductSubChildFilter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<Category> categories;
    private final Context context;
    private final int mainCategoryId;
    private final int categoryId2;
    private final AdapterProductFilter adpter;

    private final OnFilterDialogListener onFilterListener;
    private int category_level1 = -1, category_level2 = -1, category_level3 = -1;

    public AdapterProductSubChildFilter(Context context, List<Category> categories, int mainCategoryId, int CategoryId2, int category_level1, int category_level2, int category_level3, AdapterProductFilter adpter, OnFilterDialogListener onFilterDialogListener) {
        this.context = context;
        this.categories = categories;
        this.mainCategoryId = mainCategoryId;
        this.categoryId2 = CategoryId2;
        this.adpter = adpter;
        this.onFilterListener = onFilterDialogListener;
        this.category_level1 = category_level1;
        this.category_level2 = category_level2;
        this.category_level3 = category_level3;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_filter, parent, false);
        return new Holder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        Holder holder = (Holder) viewHolder;
        final Category filterProduct = (Category) categories.get(position);

        holder.tv_item.setText(filterProduct.getName());
        holder.itemView.setOnClickListener(v -> {

            if (onFilterListener != null) {
                category_level1 = mainCategoryId;
                category_level2 = categoryId2;
                category_level3 = filterProduct.getId();
                onFilterListener.onCategoryLevel1Click(category_level1);
                onFilterListener.onCategoryLevel2Click(category_level2);
                onFilterListener.onCategoryLevel3Click(category_level3);
            }
            notifyDataSetChanged();
            adpter.notifyDataSetChanged();
        });

        if (category_level3 == filterProduct.getId() && category_level1 == mainCategoryId && category_level2 == categoryId2) {

            holder.tv_item.setTextColor(ContextCompat.getColor(context, R.color.whiteColor));
            holder.ll_item.setBackgroundResource(R.drawable.back_ground_rv_filter);

        } else {

            holder.tv_item.setTextColor(ContextCompat.getColor(context, R.color.blackcolor));
            holder.ll_item.setBackgroundResource(R.drawable.back_ground_rv_filter_unselect);

        }


    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView tv_item;
        LinearLayout ll_item;

        public Holder(@NonNull View itemView) {
            super(itemView);
            ll_item = itemView.findViewById(R.id.liner_filter);
            tv_item = itemView.findViewById(R.id.tv_item);

        }


    }

}

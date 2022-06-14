package hama.alsaygh.kw.vendor.dialog.filterBy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.listener.OnFilterDialogListener;
import hama.alsaygh.kw.vendor.model.category.Category;

public class AdapterProductSubFilter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<Category> categories;
    private final Context context;
    private final int mainCategoryId;
    private final RecyclerView rc_item_child;
    private final AdapterProductFilter adpter;
    private final OnFilterDialogListener onFilterListener;
    private int category_level1 = -1, category_level2 = -1, category_level3 = -1;

    public AdapterProductSubFilter(Context context, List<Category> categories, int mainCategoryId, int category_level1, int category_level2, int category_level3, RecyclerView rc_item_child, AdapterProductFilter adpter, OnFilterDialogListener onFilterListener) {
        this.context = context;
        this.categories = categories;
        this.mainCategoryId = mainCategoryId;
        this.rc_item_child = rc_item_child;
        this.category_level1 = category_level1;
        this.category_level2 = category_level2;
        this.category_level3 = category_level3;
        this.adpter = adpter;
        this.onFilterListener = onFilterListener;

        for (Category filterProduct : categories) {
            AdapterProductSubChildFilter adapter2 = new AdapterProductSubChildFilter(context, new ArrayList<>(), mainCategoryId, filterProduct.getId(), category_level1, category_level2, category_level3, adpter, onFilterListener);
            rc_item_child.setAdapter(adapter2);
            rc_item_child.setLayoutManager(new GridLayoutManager(context, 3));

        }

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

                category_level1 = (mainCategoryId);
                category_level2 = (filterProduct.getId());
                category_level3 = (-1);
                onFilterListener.onCategoryLevel1Click(category_level1);
                onFilterListener.onCategoryLevel2Click(category_level2);
                onFilterListener.onCategoryLevel3Click(category_level3);
            }
            adpter.notifyDataSetChanged();
            notifyDataSetChanged();

        });


        if (category_level2 == filterProduct.getId() && category_level1 == mainCategoryId) {

            holder.tv_item.setTextColor(ContextCompat.getColor(context, R.color.whiteColor));
            holder.ll_item.setBackgroundResource(R.drawable.back_ground_rv_filter);

            if (filterProduct.getChilds() != null && !filterProduct.getChilds().isEmpty()) {
                AdapterProductSubChildFilter adapter = new AdapterProductSubChildFilter(context, filterProduct.getChilds(), mainCategoryId, filterProduct.getId(), category_level1, category_level2, category_level3, adpter, onFilterListener);
                rc_item_child.setAdapter(adapter);
                rc_item_child.setLayoutManager(new GridLayoutManager(context, 3));
            } else {
                AdapterProductSubChildFilter adapter = new AdapterProductSubChildFilter(context, new ArrayList<>(), mainCategoryId, filterProduct.getId(), category_level1, category_level2, category_level3, adpter, onFilterListener);
                rc_item_child.setAdapter(adapter);
                rc_item_child.setLayoutManager(new GridLayoutManager(context, 3));
            }
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

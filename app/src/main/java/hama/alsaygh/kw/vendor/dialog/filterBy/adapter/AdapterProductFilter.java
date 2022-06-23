package hama.alsaygh.kw.vendor.dialog.filterBy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appyvet.materialrangebar.RangeBar;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.listener.OnFilterDialogListener;
import hama.alsaygh.kw.vendor.model.category.Category;
import hama.alsaygh.kw.vendor.model.category.MainCategory;


public class AdapterProductFilter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<MainCategory> best_stores;
    List<Category> categories;
    Context context;
    OnFilterDialogListener onFilterDialogListener;

    private String type_of_price = "", rangeFrom = "", rangeTo = "";
    private int category_level1 = -1, category_level2 = -1, category_level3 = -1;

    public AdapterProductFilter(Context context, List<MainCategory> Best_stores, List<Category> categories, String type_of_price, int category_level1, int category_level2, int category_level3, String rangeFrom, String rangeTo, OnFilterDialogListener onFilterDialogListener) {
        this.best_stores = Best_stores;
        this.context = context;
        this.categories = categories;
        this.onFilterDialogListener = onFilterDialogListener;
        this.type_of_price = type_of_price;
        this.category_level1 = category_level1;
        this.category_level2 = category_level2;
        this.category_level3 = category_level3;
        this.rangeFrom = rangeFrom;
        this.rangeTo = rangeTo;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void addCategories(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    public void setMainCategories(List<MainCategory> best_stores) {
        this.best_stores = best_stores;
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_filter_main, parent, false);
        return new Holder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        Holder holder = (Holder) viewHolder;
        final MainCategory filterProduct = (MainCategory) best_stores.get(position);

        holder.tv_filter_name.setText(filterProduct.getName());

        holder.itemView.setOnClickListener(v -> {

            if (filterProduct.getId() == -1) {
                if (holder.rangebar1.getVisibility() == View.VISIBLE) {
                    holder.rangebar1.setVisibility(View.GONE);
                    holder.iv_close.setImageResource(R.drawable.ic_icon_upp);

                } else {
                    holder.rangebar1.setVisibility(View.VISIBLE);
                    holder.iv_close.setImageResource(R.drawable.ic_dawnicon);

                }
            } else if (filterProduct.getId() == -2) {
                if (holder.ll_price_radio.getVisibility() == View.VISIBLE) {
                    holder.ll_price_radio.setVisibility(View.GONE);
                    holder.iv_close.setImageResource(R.drawable.ic_icon_upp);

                } else {
                    holder.ll_price_radio.setVisibility(View.VISIBLE);
                    holder.iv_close.setImageResource(R.drawable.ic_dawnicon);

                }
            } else {
                if (holder.rc_item.getVisibility() == View.VISIBLE) {
                    holder.rc_item.setVisibility(View.GONE);
                    holder.rc_item_child.setVisibility(View.GONE);
                    holder.iv_close.setImageResource(R.drawable.ic_icon_upp);

                } else {
                    holder.rc_item.setVisibility(View.VISIBLE);
                    holder.rc_item_child.setVisibility(View.VISIBLE);
                    holder.iv_close.setImageResource(R.drawable.ic_dawnicon);

                }
            }
            best_stores.remove(position);
            best_stores.add(position, filterProduct);
            notifyDataSetChanged();
        });

        if (filterProduct.getId() == -3) {
            holder.rc_item.setVisibility(View.VISIBLE);
            holder.rc_item_child.setVisibility(View.VISIBLE);
            holder.iv_close.setImageResource(R.drawable.ic_dawnicon);

            if (categories == null || categories.isEmpty()) {
                holder.tv_no_data.setVisibility(View.VISIBLE);
            } else
                holder.tv_no_data.setVisibility(View.GONE);

        } else {
            holder.rc_item.setVisibility(View.GONE);
            holder.rc_item_child.setVisibility(View.GONE);
            holder.iv_close.setImageResource(R.drawable.ic_icon_upp);
            holder.tv_no_data.setVisibility(View.GONE);
        }

        holder.rangebar1.setDrawTicks(true);
        holder.rangebar1.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {

                rangeFrom = (leftPinValue) + "";
                rangeTo = (rightPinValue) + "";
                if (onFilterDialogListener != null) {
                    onFilterDialogListener.onRangePriceToClick(rangeTo);
                    onFilterDialogListener.onRangePriceFromClick(rangeFrom);
                }
            }

            @Override
            public void onTouchStarted(RangeBar rangeBar) {

            }

            @Override
            public void onTouchEnded(RangeBar rangeBar) {

            }
        });
        holder.rb_market_price.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    type_of_price = "market";
                    if (onFilterDialogListener != null)
                        onFilterDialogListener.onTypeOfPriceClick(type_of_price);
                }
            }
        });

        holder.rb_fixed_price.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    type_of_price = "fixed";
                    if (onFilterDialogListener != null)
                        onFilterDialogListener.onTypeOfPriceClick(type_of_price);
                }
            }
        });


        if (type_of_price.equalsIgnoreCase("fixed")) {
            holder.rb_fixed_price.setChecked(true);
        } else if (type_of_price.equalsIgnoreCase("market")) {
            holder.rb_market_price.setChecked(true);
        } else {
            holder.rb_fixed_price.setChecked(false);
            holder.rb_market_price.setChecked(false);
        }

        if (!rangeFrom.isEmpty() && !rangeTo.isEmpty())
            holder.rangebar1.setRangePinsByValue(Float.parseFloat(rangeFrom), Float.parseFloat(rangeTo));


        AdapterProductSubFilter adapter = new AdapterProductSubFilter(context, categories, filterProduct.getId(), category_level1, category_level2, category_level3, holder.rc_item_child, this, onFilterDialogListener);
        holder.rc_item.setAdapter(adapter);
        holder.rc_item.setLayoutManager(new GridLayoutManager(context, 3));
    }

    @Override
    public int getItemCount() {
        return best_stores.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        RadioButton rb_market_price, rb_fixed_price;
        View view;
        TextView tv_filter_name, tv_no_data;
        ImageView iv_close;
        RecyclerView rc_item, rc_item_child;
        LinearLayout ll_price_radio;
        RangeBar rangebar1;

        public Holder(@NonNull View itemView) {
            super(itemView);
            rangebar1 = itemView.findViewById(R.id.rangebar1);
            rb_fixed_price = itemView.findViewById(R.id.rb_fixed_price);
            rb_market_price = itemView.findViewById(R.id.rb_market_price);
            ll_price_radio = itemView.findViewById(R.id.ll_price_radio);
            tv_filter_name = itemView.findViewById(R.id.tv_filter_name);
            iv_close = itemView.findViewById(R.id.iv_close);
            rc_item = itemView.findViewById(R.id.rc_item);
            rc_item_child = itemView.findViewById(R.id.rc_item_child);
            tv_no_data = itemView.findViewById(R.id.tv_no_categories);
            view = itemView.findViewById(R.id.view88);

        }


    }

    public String getType_of_price() {
        return type_of_price;
    }

    public void setType_of_price(String type_of_price) {
        this.type_of_price = type_of_price;
    }

    public String getRangeFrom() {
        return rangeFrom;
    }

    public void setRangeFrom(String rangeFrom) {
        this.rangeFrom = rangeFrom;
    }

    public String getRangeTo() {
        return rangeTo;
    }

    public void setRangeTo(String rangeTo) {
        this.rangeTo = rangeTo;
    }

    public int getCategory_level1() {
        return category_level1;
    }

    public void setCategory_level1(int category_level1) {
        this.category_level1 = category_level1;
    }

    public int getCategory_level2() {
        return category_level2;
    }

    public void setCategory_level2(int category_level2) {
        this.category_level2 = category_level2;
    }

    public int getCategory_level3() {
        return category_level3;
    }

    public void setCategory_level3(int category_level3) {
        this.category_level3 = category_level3;
    }
}

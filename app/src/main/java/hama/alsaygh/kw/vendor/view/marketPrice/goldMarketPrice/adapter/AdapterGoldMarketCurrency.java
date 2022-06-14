package hama.alsaygh.kw.vendor.view.marketPrice.goldMarketPrice.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.listener.OnCurrencyListener;

public class AdapterGoldMarketCurrency extends RecyclerView.Adapter<AdapterGoldMarketCurrency.Holder> {
    List<String> mainCaliberItems;
    OnCurrencyListener onCurrencyListener;
    int selected = -1;

    public AdapterGoldMarketCurrency(List<String> mainCaliberItems, OnCurrencyListener onCurrencyListener) {
        this.mainCaliberItems = mainCaliberItems;
        this.onCurrencyListener = onCurrencyListener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_market_price_currency_list, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.tv_item.setText(mainCaliberItems.get(position));

        holder.ll_item.setOnClickListener(v -> {
            setSelected(position);
        });

        if (selected == position) {

            holder.tv_item.setTextColor(ContextCompat.getColor(holder.tv_item.getContext(), R.color.my_profile));
            holder.ll_item.setBackgroundResource(R.drawable.back_liner_kwd);

        } else {
            holder.ll_item.setBackground(null);

            holder.tv_item.setTextColor(ContextCompat.getColor(holder.tv_item.getContext(), R.color.my_profile));

        }

    }

    @Override
    public int getItemCount() {
        return mainCaliberItems.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tv_item;
        LinearLayout ll_item;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tv_item = itemView.findViewById(R.id.tv_item);
            ll_item = itemView.findViewById(R.id.ll_main);

        }
    }


    public void setSelected(int position) {
        selected = position;
        String currency = mainCaliberItems.get(selected);
        mainCaliberItems.remove(position);
        mainCaliberItems.add(position, currency);
        notifyDataSetChanged();
        if (onCurrencyListener != null)
            onCurrencyListener.onCurrencyClick(mainCaliberItems.get(selected));
    }

    public String getSelected() {
        return mainCaliberItems.get(selected);
    }
}

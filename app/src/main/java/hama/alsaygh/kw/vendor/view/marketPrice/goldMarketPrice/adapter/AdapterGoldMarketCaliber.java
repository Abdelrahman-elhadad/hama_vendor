package hama.alsaygh.kw.vendor.view.marketPrice.goldMarketPrice.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.model.marketPrice.MainCaliberItem;

public class AdapterGoldMarketCaliber extends RecyclerView.Adapter<AdapterGoldMarketCaliber.Holder> {
    List<MainCaliberItem> mainCaliberItems;

    public AdapterGoldMarketCaliber(List<MainCaliberItem> mainCaliberItems) {
        this.mainCaliberItems = mainCaliberItems;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_market_price_gold_list, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {


        holder.view_line.setBackgroundColor(ContextCompat.getColor(holder.iv_close.getContext(), R.color.my_cart2));
        holder.tv_item.setTextColor(ContextCompat.getColor(holder.iv_close.getContext(), R.color.blackcolor));
        holder.iv_close.setImageResource(R.drawable.ic_dawon);
        AdapterGoldMarket dark = new AdapterGoldMarket(mainCaliberItems.get(position).getCaliberItems());
        holder.rv_gold_market.setAdapter(dark);


        holder.ll_item.setOnClickListener(v -> {
            if (holder.rv_gold_market.getVisibility() == View.VISIBLE) {
                holder.rv_gold_market.setVisibility(View.GONE);
            } else {
                holder.rv_gold_market.setVisibility(View.VISIBLE);
            }
        });

        if (position == 0)
            holder.rv_gold_market.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return mainCaliberItems.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tv_item;
        View view_line;
        ImageView iv_close;
        RecyclerView rv_gold_market;
        LinearLayout ll_item;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tv_item = itemView.findViewById(R.id.tv_item);
            iv_close = itemView.findViewById(R.id.iv_close);
            view_line = itemView.findViewById(R.id.view_line);
            rv_gold_market = itemView.findViewById(R.id.rv_gold_market);
            ll_item = itemView.findViewById(R.id.ll_item);

        }
    }
}

package hama.alsaygh.kw.vendor.view.marketPrice.goldMarketPrice.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.model.marketPrice.CaliberItem;

public class AdapterGoldMarket extends RecyclerView.Adapter<AdapterGoldMarket.Holder> {
    List<CaliberItem> myordersArray;

    public AdapterGoldMarket(List<CaliberItem> myordersArray) {
        this.myordersArray = myordersArray;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_market_price, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        CaliberItem caliberItem = myordersArray.get(position);
        if (caliberItem.isUp())
            holder.imageView.setImageResource(R.drawable.ic_solid12);
        else
            holder.imageView.setImageResource(R.drawable.ic_solid);

        holder.tv1.setText(holder.tv1.getContext().getString(R.string.Caliber_A).replace("xx", caliberItem.getCaliber()));

        holder.tv2.setText(String.format(Locale.ENGLISH, "%.3f", caliberItem.getPrice()));

    }

    @Override
    public int getItemCount() {
        return myordersArray.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tv1, tv2;
        ImageView imageView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView19);
            tv1 = (TextView) itemView.findViewById(R.id.textView112);
            tv2 = (TextView) itemView.findViewById(R.id.textView134);
        }
    }
}

package hama.alsaygh.kw.vendor.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.array.activeOffers;

import java.util.ArrayList;

public class AdapterActiveOffers extends  RecyclerView.Adapter<AdapterActiveOffers.Holder> {
    ArrayList<hama.alsaygh.kw.vendor.array.activeOffers> activeOffers;

    public AdapterActiveOffers(ArrayList<activeOffers> myCartArrays) {
        this.activeOffers = myCartArrays;
    }

    @NonNull
    @Override
    public AdapterActiveOffers.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_active_offer,parent,false);
        return new  AdapterActiveOffers.Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterActiveOffers.Holder holder, int position) {
        holder.imageView.setImageResource(activeOffers.get(position).getImg());
        holder.tv1.setText("Gold Ring");
        holder.tv2.setText("150KWD");

    }

    @Override
    public int getItemCount() {
        return activeOffers.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tv1 , tv2;
        View view;
        ImageView imageView ,imageView1,imageView2,imageView3;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView =(ImageView)itemView.findViewById(R.id.twenz_store_page);
            tv1=(TextView)itemView.findViewById(R.id.storepage_text);
            tv2=(TextView)itemView.findViewById(R.id.ttt);




        }
    }
}

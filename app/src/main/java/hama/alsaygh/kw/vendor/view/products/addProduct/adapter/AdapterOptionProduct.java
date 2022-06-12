package hama.alsaygh.kw.vendor.view.products.addProduct.adapter;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.dialog.addOptionDialog.AddOptionProductDialog;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.product.Option;

public class AdapterOptionProduct extends RecyclerView.Adapter<AdapterOptionProduct.Holder> {
    ArrayList<Option> imageUploads;
    FragmentManager activity;
    OnGeneralClickListener onGeneralClickListener;

    public AdapterOptionProduct(FragmentManager activity, ArrayList<Option> imageUploads, OnGeneralClickListener onGeneralClickListener) {
        this.imageUploads = imageUploads;
        this.activity = activity;
        this.onGeneralClickListener = onGeneralClickListener;
    }

    @NonNull
    @Override
    public AdapterOptionProduct.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_options, parent, false);
        return new AdapterOptionProduct.Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOptionProduct.Holder holder, int index) {
        final int position = index;


        holder.tv_name.setText(imageUploads.get(position).getName());
        holder.tv_name_ar.setText(imageUploads.get(position).getNameAr());
        holder.tv_count.setText(imageUploads.get(position).getAvailable_quantity() + "");
        holder.tv_price.setText(imageUploads.get(position).getPrice() + " " + holder.tv_price.getContext().getString(R.string.currency));
        holder.tv_market.setText(imageUploads.get(position).isBind_to_market() ? holder.tv_price.getContext().getString(R.string.save) : holder.tv_price.getContext().getString(R.string.find));

        if (imageUploads.get(position).isBind_to_market()) {
            holder.tv_price.setVisibility(View.INVISIBLE);
        } else
            holder.tv_price.setVisibility(View.VISIBLE);

        if (imageUploads.get(position).getColor() != null && !imageUploads.get(position).getColor().isEmpty()) {
            int color = ContextCompat.getColor(holder.tv_color.getContext(), R.color.whiteColor);
            try {
                String hexa = imageUploads.get(position).getColor();
                if (hexa.length() == 4) {
                    hexa = hexa.substring(1);
                    hexa = "#" + hexa + hexa;
                }

                color = Color.parseColor(hexa);
            } catch (Exception e) {
                e.printStackTrace();
            }
            holder.tv_color.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_IN);
            holder.ll_color.setVisibility(View.VISIBLE);
        } else {
            holder.ll_color.setVisibility(View.INVISIBLE);
        }

        holder.iv_delete.setOnClickListener(v -> {

            imageUploads.remove(position);
            notifyItemRemoved(position);
            notifyDataSetChanged();
        });

        holder.iv_edit.setOnClickListener(v -> {

            AddOptionProductDialog.newInstance(imageUploads.get(index), index, onGeneralClickListener).show(activity, "edit");

        });

    }


    @Override
    public int getItemCount() {
        return imageUploads.size();
    }

    public void addItem(Option imageUpload) {
        imageUploads.add(imageUpload);
        notifyDataSetChanged();
    }

    public void editItem(Option imageUpload, int index) {
        imageUploads.remove(index);
        imageUploads.add(index, imageUpload);
        notifyDataSetChanged();
    }

    public JSONArray getJson() {

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < imageUploads.size(); i++) {

            // jsonArray.put(imageUploads.get(i).getImage().getFile_name());

        }

        return jsonArray;
    }

    public List<Option> getItems() {

        return imageUploads;
    }


    public class Holder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_name_ar, tv_count, tv_market, tv_price;
        ImageView iv_edit, iv_delete;
        View tv_color;
        LinearLayout ll_color;


        public Holder(@NonNull View itemView) {
            super(itemView);
            iv_edit = itemView.findViewById(R.id.iv_edit);
            iv_delete = itemView.findViewById(R.id.iv_delete);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_name_ar = itemView.findViewById(R.id.tv_name_ar);
            tv_market = itemView.findViewById(R.id.tv_market);
            tv_count = itemView.findViewById(R.id.tv_count);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_color = itemView.findViewById(R.id.tv_color);
            ll_color = itemView.findViewById(R.id.ll_color);
        }
    }
}

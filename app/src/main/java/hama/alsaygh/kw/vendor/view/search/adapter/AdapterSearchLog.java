package hama.alsaygh.kw.vendor.view.search.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.listener.OnGeneralClickListener;
import hama.alsaygh.kw.vendor.model.searchLog.SearchLog;

public class AdapterSearchLog extends RecyclerView.Adapter<AdapterSearchLog.Holder> {
    List<SearchLog> addresses;
    private final OnGeneralClickListener onNoteListener;
    public static String TAG = "AdapterSearchLog";

    public AdapterSearchLog(OnGeneralClickListener onNoteListener, List<SearchLog> products) {
        this.onNoteListener = onNoteListener;
        this.addresses = products;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_search_log, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, final int position) {


        final SearchLog searchLog = addresses.get(position);
        holder.tv_item.setText(searchLog.getKey());
        holder.itemView.setOnClickListener(v -> {
            if (onNoteListener != null)
                onNoteListener.onItemClick(searchLog, position);
        });

    }

    @Override
    public int getItemCount() {
        return addresses.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tv_item;
        View viewLine;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tv_item = itemView.findViewById(R.id.tv_item);
            viewLine = itemView.findViewById(R.id.viewLine);
        }
    }
}

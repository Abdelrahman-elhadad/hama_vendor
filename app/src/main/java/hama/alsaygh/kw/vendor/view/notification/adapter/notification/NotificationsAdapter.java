package hama.alsaygh.kw.vendor.view.notification.adapter.notification;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.CardNotification1Binding;
import hama.alsaygh.kw.vendor.model.notifications.Notifications;

public class NotificationsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Notifications> mValues;

    public NotificationsAdapter(List<Notifications> items) {
        mValues = items;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CardNotification1Binding binding = DataBindingUtil.inflate(layoutInflater, R.layout.card_notification1, parent, false);
        return new ViewItemHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int index) {

        final int position = index;
        ViewItemHolder viewHolder = (ViewItemHolder) holder;
        viewHolder.bind(new NotificationViewModel(viewHolder.itemView.getContext(), mValues.get(position)));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void addItem(List<Notifications> data) {
        mValues.addAll(data);
    }

    private class ViewItemHolder extends RecyclerView.ViewHolder {
        final CardNotification1Binding binding;

        ViewItemHolder(CardNotification1Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(NotificationViewModel viewModel) {
            binding.setModel(viewModel);
            binding.executePendingBindings();
        }


    }
}

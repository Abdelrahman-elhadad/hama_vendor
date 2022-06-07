package hama.alsaygh.kw.vendor.view.products.addProduct.adapter;

import android.app.Activity;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;

import java.util.ArrayList;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.model.image.ImageResponse;
import hama.alsaygh.kw.vendor.model.image.ImageUpload;
import hama.alsaygh.kw.vendor.repo.GeneralRepo;

public class AdapterImageProduct extends RecyclerView.Adapter<AdapterImageProduct.Holder> {
    ArrayList<ImageUpload> imageUploads;
    SparseArray<MutableLiveData<ImageResponse>> threadSparseArray = new SparseArray<>();
    Activity activity;

    public AdapterImageProduct(Activity activity, ArrayList<ImageUpload> imageUploads) {
        this.imageUploads = imageUploads;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AdapterImageProduct.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_request_image, parent, false);
        return new AdapterImageProduct.Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterImageProduct.Holder holder, int index) {
        final int position = index;
        holder.iv_item.setImageURI(imageUploads.get(position).getUri());
        if (imageUploads.get(position).getImage() == null) {
            holder.tv_name.setText("");
            holder.pb_load.setVisibility(View.VISIBLE);
            holder.iv_delete.setVisibility(View.GONE);
        } else {
            holder.tv_name.setText(imageUploads.get(position).getImage().getFile_name());
            holder.pb_load.setVisibility(View.GONE);
            holder.iv_delete.setVisibility(View.VISIBLE);
        }

        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageUploads.remove(position);
                notifyItemRemoved(position);
                notifyDataSetChanged();
            }
        });


        if (threadSparseArray.get(position) == null && imageUploads.get(position).getImage() == null) {

            startUploadImage(position, holder);

        } else {
            Log.i("nnnn", "jjjjj");
        }

    }

    private void startUploadImage(final int position, final Holder holder) {

        MutableLiveData<ImageResponse> imageMutableLiveData = new MutableLiveData<>();
        imageMutableLiveData.observe((LifecycleOwner) activity, imageResponse -> {
            if (imageResponse.isStatus()) {
                ImageUpload imageUpload = imageUploads.get(position);
                imageUpload.setImage(imageResponse.getData());
                imageUploads.remove(position);
                imageUploads.add(position, imageUpload);

                notifyItemChanged(position);
                notifyDataSetChanged();
            }
        });
        threadSparseArray.put(position, imageMutableLiveData);
        new GeneralRepo().uploadImage(holder.iv_delete.getContext(), imageUploads.get(position).getPath(), imageMutableLiveData);
    }

    @Override
    public int getItemCount() {
        return imageUploads.size();
    }

    public void addItem(ImageUpload imageUpload) {
        imageUploads.add(imageUpload);
        notifyDataSetChanged();
    }

    public void removeAll() {
        imageUploads.clear();
        notifyDataSetChanged();
    }

    public boolean isValid() {
        boolean isValid = true;

        for (int i = 0; i < imageUploads.size(); i++) {
            if (imageUploads.get(i).getImage() == null) {
                isValid = false;
                break;
            }
        }

        return isValid;
    }

    public JSONArray getImageName() {

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < imageUploads.size(); i++) {
            if (imageUploads.get(i).getImage() != null) {
                jsonArray.put(imageUploads.get(i).getImage().getFile_name());
            }
        }

        return jsonArray;
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_size;
        ImageView iv_item, iv_delete;
        ProgressBar pb_load;

        public Holder(@NonNull View itemView) {
            super(itemView);
            iv_item = itemView.findViewById(R.id.iv_item);
            iv_delete = itemView.findViewById(R.id.iv_delete);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_size = itemView.findViewById(R.id.tv_size);
            pb_load = itemView.findViewById(R.id.pb_load);
        }
    }
}

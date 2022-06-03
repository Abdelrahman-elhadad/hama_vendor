package hama.alsaygh.kw.vendor.listener;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import hama.alsaygh.kw.vendor.R;

public interface ImagesBinding {

    @BindingAdapter({"imageProductUrl"})
    public static void loadProductImage(final ImageView view, String imageUrl) {

        if (imageUrl != null && !imageUrl.isEmpty())
            Picasso.get().load(imageUrl).into(view, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {

                    Picasso.get().load(R.drawable.image_not_foundpng).into(view);
                }
            });
        else
            Picasso.get().load(R.drawable.image_not_foundpng).into(view);
    }
}

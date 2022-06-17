package hama.alsaygh.kw.vendor.listener;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.utils.Utils;

public interface ImagesBinding {

    @BindingAdapter({"imageProductUrl"})
    public static void loadProductImage(final ImageView view, String imageUrl) {

        if (imageUrl != null && !imageUrl.isEmpty())
            Picasso.get().load(imageUrl).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                    view.setBackgroundColor(Utils.getInstance().getDominantColor(bitmap));
                    view.setImageBitmap(bitmap);
                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                    Picasso.get().load(R.drawable.image_not_foundpng).into(view);
                    view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.color_image_not_found));
                }


                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                    view.setImageDrawable(placeHolderDrawable);
                }
            });
        else {
            Picasso.get().load(R.drawable.image_not_foundpng).into(view);
            view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.color_image_not_found));
        }
    }
}

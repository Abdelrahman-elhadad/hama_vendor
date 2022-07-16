package hama.alsaygh.kw.vendor.view.storeRate.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.model.review.Review;
import hama.alsaygh.kw.vendor.utils.image.CircleTransform;

public class StoreReviewAdapter extends RecyclerView.Adapter<StoreReviewAdapter.Holder_details2> {

    List<Review> Reviews;

    public StoreReviewAdapter(List<Review> Reviews) {
        this.Reviews = Reviews;
    }

    @NonNull
    @Override
    public Holder_details2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_review, parent, false);
        return new Holder_details2(v);
    }

    @Override
    public int getItemCount() {
        return Reviews.size();
    }

    @Override
    public void onBindViewHolder(@NonNull Holder_details2 holder, int position) {
        holder.tv1.setText(Reviews.get(position).getUser().getName());
        holder.rb_review.setRating(Reviews.get(position).getRate());
        holder.tv2.setText(Reviews.get(position).getReview());
        holder.tv3.setText(Reviews.get(position).getCreated_at());

        if (Reviews.get(position).getUser().getImage() != null && !Reviews.get(position).getUser().getImage().isEmpty())
            Picasso.get().load(Reviews.get(position).getUser().getImage()).fit().transform(new CircleTransform()).into(holder.imageView, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {
                    Picasso.get().load(R.drawable.image_not_foundpng).transform(new CircleTransform()).into(holder.imageView);
                }
            });
        else
            Picasso.get().load(R.drawable.image_not_foundpng).transform(new CircleTransform()).into(holder.imageView);

    }


    public void addItems(List<Review> reviews) {
        if (Reviews == null)
            Reviews = new ArrayList<>();

        Reviews.addAll(reviews);
    }

    public class Holder_details2 extends RecyclerView.ViewHolder {
        TextView tv1, tv2, tv3;
        View view;
        ImageView imageView;
        RatingBar rb_review;

        public Holder_details2(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.person);
            rb_review = itemView.findViewById(R.id.rb_review);
            tv1 = itemView.findViewById(R.id.text_person);
            tv2 = itemView.findViewById(R.id.text_person1);
            tv3 = itemView.findViewById(R.id.text_person2);


        }
    }
}

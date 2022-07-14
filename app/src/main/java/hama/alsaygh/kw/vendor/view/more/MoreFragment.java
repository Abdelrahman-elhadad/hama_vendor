package hama.alsaygh.kw.vendor.view.more;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import hama.alsaygh.kw.vendor.R;
import hama.alsaygh.kw.vendor.databinding.FragmentMoreBinding;
import hama.alsaygh.kw.vendor.utils.SharedPreferenceConstant;
import hama.alsaygh.kw.vendor.utils.image.CircleTransform;
import hama.alsaygh.kw.vendor.view.base.BaseFragment;

public class MoreFragment extends BaseFragment {

    FragmentMoreBinding binding;
    MoreViewModel model;

    public static MoreFragment newInstance() {
        return new MoreFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMoreBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        model = new MoreViewModel(requireContext());
        binding.setModel(model);

        String imageUrl = SharedPreferenceConstant.getSharedPreferenceUser(requireContext()).getLogo();
        if (imageUrl != null && !imageUrl.isEmpty())
            Picasso.get().load(imageUrl).transform(new CircleTransform()).into(binding.ivProfile, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {

                    Picasso.get().load(R.drawable.image_not_foundpng).transform(new CircleTransform()).into(binding.ivProfile);
                }
            });
        else
            Picasso.get().load(R.drawable.image_not_foundpng).transform(new CircleTransform()).into(binding.ivProfile);


    }
}
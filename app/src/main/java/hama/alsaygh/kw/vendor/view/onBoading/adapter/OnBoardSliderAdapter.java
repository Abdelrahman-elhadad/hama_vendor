package hama.alsaygh.kw.vendor.view.onBoading.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;

import java.util.List;

import hama.alsaygh.kw.vendor.databinding.CardSplashScreenBinding;
import hama.alsaygh.kw.vendor.model.onBoarding.OnBoard;

public class OnBoardSliderAdapter extends PagerAdapter {
    LayoutInflater layoutInflater;
    Context context;
    List<OnBoard> onBoards;

    public OnBoardSliderAdapter(LayoutInflater layoutInflater, Context context, List<OnBoard> onBoards) {
        this.layoutInflater = layoutInflater;
        this.context = context;
        this.onBoards = onBoards;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container, position, object);
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public int getCount() {
        return onBoards.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        CardSplashScreenBinding binding = CardSplashScreenBinding.inflate(layoutInflater);
        OnBoardingAdapterViewModel model = new OnBoardingAdapterViewModel(onBoards.get(position));
        binding.setModel(model);

        Picasso.get().load(onBoards.get(position).getImage()).into(binding.ivBg);

        container.addView(binding.getRoot());

        return binding.getRoot();
    }
}

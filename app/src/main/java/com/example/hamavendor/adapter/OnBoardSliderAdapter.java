package com.example.hamavendor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.hamavendor.R;

import java.util.List;

public class OnBoardSliderAdapter extends PagerAdapter {
    LayoutInflater layoutInflater;
    TextView tvTitle, tvDesc,button2,btn_login;
    Context context;
    ImageView ivImage, ivBg;
   // List<OnBoard> onBoards;

    public int [] iv_bg ={
            R.drawable.splash,
            R.drawable.splash,
            R.drawable.splash,
    };
   public int[] slide_image = {
           R.drawable.hama_logo,
           R.drawable.hama_logo,
           R.drawable.hama_logo,
   };
    public int [] slide_heading ={
           // context.getString(R.string.wellcome),
            R.string.wellcome,R.string.wellcome,R.string.wellcome
//            context.getString(R.string.wellcome),
//            context.getString(R.string.wellcome)
    };
    public int [] slide_heading2 ={
           R.string.splash,
            R.string.splash,
            R.string.splash
//            context.getString(R.string.splash),
//            context.getString(R.string.splash)
    };
    public OnBoardSliderAdapter(LayoutInflater layoutInflater, Context context) {
        this.layoutInflater = layoutInflater;
        this.context = context;
      //  this.onBoards = onBoards;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container, position, object);
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public int getCount() {
        return iv_bg.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = LayoutInflater.from(context).inflate(R.layout.card_splash_screen, container, false);
//        if (SharedPreferenceConstant.getSharedPreferenceDarkMode(context)) {
//            view = LayoutInflater.from(context).inflate(R.layout.card_home_dark, container, false);
//        }

        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvDesc = (TextView) view.findViewById(R.id.tv_desc);
        ivImage = (ImageView) view.findViewById(R.id.iv_image);
        ivBg = (ImageView) view.findViewById(R.id.iv_bg);
        btn_login=(TextView)view.findViewById(R.id.btn_login);
        button2=(TextView)view.findViewById(R.id.button2);
        tvTitle.setText(slide_heading[position]);
        tvDesc.setText(slide_heading2[position]);
        ivBg.setBackgroundResource(iv_bg[position]);
//        ivImage.setBackgroundResource(img_logo[position]);
//        imageView43.setBackgroundResource(slider_img_lady[position]);
       // Picasso.get().load(onBoards.get(position).getImage()).into(ivBg);

        container.addView(view);

        return view;
    }
}

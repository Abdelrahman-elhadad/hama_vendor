package com.example.hamavendor.circleindicator;

import android.view.Gravity;
import android.widget.LinearLayout;

import androidx.annotation.AnimatorRes;
import androidx.annotation.DrawableRes;

import com.example.hamavendor.R;

public class Config {

    int width = -1;
    int height = -1;
    int margin = -1;
    @AnimatorRes
    int animatorResId = R.animator.scale_with_alpha;
    @AnimatorRes
    int animatorReverseResId = 0;
    @DrawableRes
    int backgroundResId = R.drawable.white_radius;
    @DrawableRes
    int unselectedBackgroundId;
    int orientation = LinearLayout.HORIZONTAL;
    int gravity = Gravity.CENTER;

    Config() {
    }

    public static class Builder {

        private final com.example.hamavendor.circleindicator.Config mConfig;

        public Builder() {
            mConfig = new com.example.hamavendor.circleindicator.Config();
        }

        public com.example.hamavendor.circleindicator.Config.Builder width(int width) {
            mConfig.width = width;
            return this;
        }

        public com.example.hamavendor.circleindicator.Config.Builder height(int height) {
            mConfig.height = height;
            return this;
        }

        public com.example.hamavendor.circleindicator.Config.Builder margin(int margin) {
            mConfig.margin = margin;
            return this;
        }

        public com.example.hamavendor.circleindicator.Config.Builder animator(@AnimatorRes int animatorResId) {
            mConfig.animatorResId = animatorResId;
            return this;
        }

        public com.example.hamavendor.circleindicator.Config.Builder animatorReverse(@AnimatorRes int animatorReverseResId) {
            mConfig.animatorReverseResId = animatorReverseResId;
            return this;
        }

        public com.example.hamavendor.circleindicator.Config.Builder drawable(@DrawableRes int backgroundResId) {
            mConfig.backgroundResId = backgroundResId;
            return this;
        }

        public com.example.hamavendor.circleindicator.Config.Builder drawableUnselected(@DrawableRes int unselectedBackgroundId) {
            mConfig.unselectedBackgroundId = unselectedBackgroundId;
            return this;
        }

        public com.example.hamavendor.circleindicator.Config.Builder orientation(int orientation) {
            mConfig.orientation = orientation;
            return this;
        }

        public com.example.hamavendor.circleindicator.Config.Builder gravity(int gravity) {
            mConfig.gravity = gravity;
            return this;
        }

        public com.example.hamavendor.circleindicator.Config build() {
            return mConfig;
        }
    }
}


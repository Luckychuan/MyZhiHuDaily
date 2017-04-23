package com.example.luckychuan.myzhihudaily.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.luckychuan.myzhihudaily.R;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.util.List;

/**
 * Created by Luckychuan on 2017/4/23.
 */
public class BannerAdapter extends StaticPagerAdapter {

    private static final String TAG = "BannerAdapter";
    private List<String> mImageUrlList;

    public BannerAdapter(List<String> list){
        mImageUrlList = list;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        Context context = container.getContext();
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Glide.with(context)
                .load(mImageUrlList.get(position))
                .placeholder(R.color.white)
                .error(R.color.white)
                .into(imageView);

        return imageView;
    }

    @Override
    public int getCount() {
        return mImageUrlList.size();
    }
}

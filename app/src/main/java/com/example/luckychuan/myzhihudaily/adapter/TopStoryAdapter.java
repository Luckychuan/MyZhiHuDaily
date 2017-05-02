package com.example.luckychuan.myzhihudaily.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.bean.LatestData;

import java.util.List;

/**
 * Created by Luckychuan on 2017/5/2.
 */
public class TopStoryAdapter extends PagerAdapter {

    private List<LatestData.TopStory> mTopStoryList;

    public TopStoryAdapter(List<LatestData.TopStory> list) {
        mTopStoryList = list;
    }

    @Override
    public int getCount() {
        return mTopStoryList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.pager_adapter_item, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.top_story_imageView);
        TextView textView = (TextView) view.findViewById(R.id.top_story_textView);

        LatestData.TopStory topStory = mTopStoryList.get(position);

        Glide.with(container.getContext())
                .load(topStory.getImageUrl())
                .placeholder(R.color.white)
                .error(R.color.white)
                .into(imageView);

        textView.setText(topStory.getTitle());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

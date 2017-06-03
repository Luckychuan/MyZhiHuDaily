package com.example.luckychuan.myzhihudaily.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.bean.LatestData;
import com.example.luckychuan.myzhihudaily.bean.Story;
import com.example.luckychuan.myzhihudaily.ui.StoryActivity;

import java.io.Serializable;
import java.util.ArrayList;
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
    public Object instantiateItem(final ViewGroup container, final int position) {
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
                Context context = container.getContext();
                Intent intent = new Intent(context, StoryActivity.class);

                List<Story> storyList = new ArrayList<Story>();
                for(LatestData.TopStory topStory:mTopStoryList){
                    Story story = new Story();
                    story.setId(topStory.getId());
                    story.setImageUrl(new String[]{topStory.getImageUrl()});
                    story.setTitle(topStory.getTitle());
                    story.setMultiPic(topStory.isMultiPic());

                    storyList.add(story);
                }

                intent.putExtra("storyList", (Serializable) storyList);
                intent.putExtra("position",position);
                context.startActivity(intent);
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

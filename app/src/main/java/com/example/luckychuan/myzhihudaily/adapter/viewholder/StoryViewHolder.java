package com.example.luckychuan.myzhihudaily.adapter.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.bean.Story;

/**
 * Created by Luckychuan on 2017/5/2.
 */
public class StoryViewHolder extends BaseViewHolder<Story> {

    private ImageView imageView;
    private TextView textView;
    private Context context;

    public StoryViewHolder(View itemView) {
        super(itemView);

        context = itemView.getContext();

        imageView = (ImageView) itemView.findViewById(R.id.new_image);
        textView = (TextView) itemView.findViewById(R.id.new_title);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });

    }

    @Override
    public void bindViewHolder(Story bean) {
        textView.setText(bean.getTitle());

        Glide.with(context)
                .load(bean.getImageUrl())
                .placeholder(R.color.white)
                .error(R.color.white)
                .into(imageView);
    }
}

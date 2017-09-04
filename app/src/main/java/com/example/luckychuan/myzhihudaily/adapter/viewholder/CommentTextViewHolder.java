package com.example.luckychuan.myzhihudaily.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.example.luckychuan.myzhihudaily.R;

/**
 * Created by Luckychuan on 2017/9/2.
 */


public class CommentTextViewHolder extends BaseViewHolder<String> {

    private TextView textView;

    public CommentTextViewHolder(View itemView) {
        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.comment_textView);

    }

    @Override
    public void bindViewHolder(String bean) {
        textView.setText(bean);
    }

    @Override
    public void bindViewHolder(String bean, int position) {

    }

}

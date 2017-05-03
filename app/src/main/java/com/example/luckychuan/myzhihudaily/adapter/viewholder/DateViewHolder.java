package com.example.luckychuan.myzhihudaily.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.example.luckychuan.myzhihudaily.R;


/**
 * Created by Luckychuan on 2017/5/2.
 */
public class DateViewHolder extends BaseViewHolder<String> {

    private TextView textView;

    public DateViewHolder(View itemView) {
        super(itemView);

        textView = (TextView) itemView.findViewById(R.id.date_text);

    }

    @Override
    public void bindViewHolder(String bean) {
        textView.setText(format(bean));
    }

    public String format(String date){

        return "今日新闻";
    }

}

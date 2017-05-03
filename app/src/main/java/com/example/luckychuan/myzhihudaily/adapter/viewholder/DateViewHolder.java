package com.example.luckychuan.myzhihudaily.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import com.example.luckychuan.myzhihudaily.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


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

    public static String format(String dateString){
        String formatDate;
        //获得今天的日期
       String todayDate  = new SimpleDateFormat("yyyyMMdd").format(new Date());

        if(dateString.equals(todayDate)){
            formatDate = "今日新闻";
        }else{
            Date date = null;
            try {
                 date = new SimpleDateFormat("yyyyMMdd").parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            formatDate = new SimpleDateFormat("MM月dd日 EEEE").format(date);
        }

        return formatDate;
    }

}

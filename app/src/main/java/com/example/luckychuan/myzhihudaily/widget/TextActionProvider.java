package com.example.luckychuan.myzhihudaily.widget;

import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luckychuan.myzhihudaily.R;

/**
 * Created by Luckychuan on 2017/5/11.
 */

public class TextActionProvider extends ActionProvider{

    private View mView;


    /**
     * Creates a new instance.
     *
     * @param context Context for accessing resources.
     */
    public TextActionProvider(Context context) {
        super(context);
    }

    @Override
    public View onCreateActionView() {
        mView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item_view,null);
        return mView;
    }

    public void setResource(int id,int number){
        ImageView imageView = (ImageView) mView.findViewById(R.id.menu_icon);
        TextView textView = (TextView) mView.findViewById(R.id.menu_number);

        imageView.setImageResource(id);
        textView.setText(number+"");

    }

    public void setOnClickListener(View.OnClickListener listener){
        mView.setOnClickListener(listener);
    }

}

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
    private TextView mTextView;


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
        mTextView = (TextView) mView.findViewById(R.id.menu_number);
        return mView;
    }

    public void setDrawable(int id){
        ImageView imageView = (ImageView) mView.findViewById(R.id.menu_icon);

        imageView.setImageResource(id);

    }


    public void setNumber(int number){
        mTextView.setText(number+"");
    }

    public void setLoadingText(){
        mTextView.setText("...");
    }

    public void setOnClickListener(View.OnClickListener listener){
        mView.setOnClickListener(listener);
    }

}

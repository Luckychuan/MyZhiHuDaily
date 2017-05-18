package com.example.luckychuan.myzhihudaily.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luckychuan.myzhihudaily.R;

/**
 * Created by Luckychuan on 2017/5/17.
 */

public class FavoriteFragment extends BaseFragment  {

    private OnTitleChangeListener mListener;
    


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite,container,false);
    }


    @Override
    public void setTitleChangeListener(OnTitleChangeListener listener) {
        mListener = listener;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            if(mListener !=null){
                // TODO: 2017/5/18  
                mListener.changeToolbarTitle("条收藏");
            }
        }
        
        
    }
}

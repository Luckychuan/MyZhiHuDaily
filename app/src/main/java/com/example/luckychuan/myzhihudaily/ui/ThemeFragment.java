package com.example.luckychuan.myzhihudaily.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luckychuan.myzhihudaily.R;

/**
 * Created by Luckychuan on 2017/5/5.
 */
public class ThemeFragment extends Fragment {

    private static final String TAG = "ThemeFragment";
    private int mId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_theme, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void refreshData(int id) {
        Log.d(TAG, "refreshData: "+id);
        if (mId != id) {
            mId=id;
            //// TODO: 2017/5/5 请求数据
        }
    }

}

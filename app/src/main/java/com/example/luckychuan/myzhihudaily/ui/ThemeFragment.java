package com.example.luckychuan.myzhihudaily.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luckychuan.myzhihudaily.R;
import com.example.luckychuan.myzhihudaily.bean.ThemeContent;
import com.example.luckychuan.myzhihudaily.presenter.GetThemeContentPresenter;
import com.example.luckychuan.myzhihudaily.view.ThemeContentView;

/**
 * Created by Luckychuan on 2017/5/5.
 */
public class ThemeFragment extends Fragment implements ThemeContentView {

    private GetThemeContentPresenter mPresenter;

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

        mPresenter = new GetThemeContentPresenter(this);
        mPresenter.attach(this);
        mPresenter.requestData((Integer) getArguments().get("id"));

    }

    public void refreshData(int id) {
        if (mId != id) {
            mId = id;
            mPresenter.requestData(id);
        }
    }

    @Override
    public void showErrorMsg(String error) {

    }

    @Override
    public void updateUI(ThemeContent content) {
        Log.d(TAG, "updateUI: " + content.toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detach();
    }
}

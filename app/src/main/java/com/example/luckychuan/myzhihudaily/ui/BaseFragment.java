package com.example.luckychuan.myzhihudaily.ui;

import android.support.v4.app.Fragment;

/**
 * Created by Luckychuan on 2017/5/17.
 */

public abstract class BaseFragment extends Fragment {


    public abstract void setTitleChangeListener(OnTitleChangeListener listener);


    /**
     * 当Fragment的标题改变时，让Activity修改Toolbar的标题
     */
    public interface OnTitleChangeListener {

        void changeToolbarTitle(String title);
    }

}

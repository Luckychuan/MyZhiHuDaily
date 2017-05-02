package com.example.luckychuan.myzhihudaily.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * 自动滑动的viewpager
 */
public class AutoSlideViewPager extends ViewPager {

    public AutoSlideViewPager(Context context) {
        super(context);
    }

    public AutoSlideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    private void startGlide() {
        Observable.interval(5, 5, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        if (getCurrentItem() == getAdapter().getCount() - 1) {
                            setCurrentItem(0);
                        } else {
                            setCurrentItem(getCurrentItem() + 1);
                        }

                    }
                });
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startGlide();
    }
}

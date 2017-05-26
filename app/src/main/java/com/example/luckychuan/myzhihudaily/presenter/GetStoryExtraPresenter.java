package com.example.luckychuan.myzhihudaily.presenter;

import com.example.luckychuan.myzhihudaily.bean.StoryExtra;
import com.example.luckychuan.myzhihudaily.model.Callback;
import com.example.luckychuan.myzhihudaily.model.GetStoryExtraModel;
import com.example.luckychuan.myzhihudaily.model.GetStoryExtraModelImpl;
import com.example.luckychuan.myzhihudaily.view.StoryExtraView;

/**
 * Created by Luckychuan on 2017/5/26.
 */

public class GetStoryExtraPresenter extends BasePresenter {

    private GetStoryExtraModel mModel;
    private StoryExtraView mView;

    public GetStoryExtraPresenter(StoryExtraView view) {
        mModel = new GetStoryExtraModelImpl();
        mView = view;
    }

    public void requestStoryExtra(int id) {
        mModel.getStoryExtra(id, new Callback<StoryExtra>() {
            @Override
            public void onSuccess(StoryExtra bean) {
                mView.updateToolbar(bean);
            }

            @Override
            public void onFail(String errorMsg) {

            }
        });
    }

}

package com.example.luckychuan.myzhihudaily.presenter;

import com.example.luckychuan.myzhihudaily.bean.StoryContent;
import com.example.luckychuan.myzhihudaily.bean.StoryExtra;
import com.example.luckychuan.myzhihudaily.model.Callback;
import com.example.luckychuan.myzhihudaily.model.GetStoryContentModel;
import com.example.luckychuan.myzhihudaily.model.GetStoryContentModelImpl;
import com.example.luckychuan.myzhihudaily.view.StoryContentView;

/**
 * Created by Luckychuan on 2017/5/8.
 */
public class GetStoryContentPresenter extends BasePresenter {

    private GetStoryContentModel mModel;
    private StoryContentView mView;

    public GetStoryContentPresenter(StoryContentView view){
        mView = view;
        mModel = new GetStoryContentModelImpl();
    }

    public void requestStoryContent(int id){
        mModel.getStoryContent(id, new Callback<StoryContent>() {
            @Override
            public void onSuccess(StoryContent bean) {
                mView.updateUI(bean);
            }

            @Override
            public void onFail(String errorMsg) {
                mView.showErrorMsg(errorMsg);
            }
        });
    }

    public void requestStoryExtra(int id){
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

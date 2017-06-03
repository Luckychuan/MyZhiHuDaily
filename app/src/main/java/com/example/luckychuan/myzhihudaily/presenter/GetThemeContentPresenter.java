package com.example.luckychuan.myzhihudaily.presenter;

import com.example.luckychuan.myzhihudaily.bean.ThemeContent;
import com.example.luckychuan.myzhihudaily.model.Callback;
import com.example.luckychuan.myzhihudaily.model.GetThemeContentModel;
import com.example.luckychuan.myzhihudaily.model.GetThemeContentModelImpl;
import com.example.luckychuan.myzhihudaily.view.ThemeContentView;

/**
 * Created by Luckychuan on 2017/5/5.
 */
public class GetThemeContentPresenter extends BasePresenter {

    private GetThemeContentModel mModel;
    private ThemeContentView mView;

    public GetThemeContentPresenter(ThemeContentView view){
        mView = view;
        mModel = new GetThemeContentModelImpl();
    }

    public void requestData(String id){
        mModel.getThemeContent(id,new Callback<ThemeContent>() {
            @Override
            public void onSuccess(ThemeContent bean) {
                mView.updateUI(bean);
            }

            @Override
            public void onFail(String errorMsg) {
                mView.showErrorMsg(errorMsg);
            }
        });
    }



}

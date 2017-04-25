package com.example.luckychuan.myzhihudaily.presenter;

import com.example.luckychuan.myzhihudaily.bean.News;
import com.example.luckychuan.myzhihudaily.model.Callback;
import com.example.luckychuan.myzhihudaily.model.GetOldDataModel;
import com.example.luckychuan.myzhihudaily.model.GetOldDataModelImpl;
import com.example.luckychuan.myzhihudaily.view.BaseView;
import com.example.luckychuan.myzhihudaily.view.OldDataView;

/**
 * Created by Luckychuan on 2017/4/25.
 */
public class GetOldDataPresenter extends BasePresenter {

    OldDataView mView;
    GetOldDataModel mModel;

    public GetOldDataPresenter(OldDataView view) {
        mView = view;
        mModel = new GetOldDataModelImpl();
    }

    public void requestData(String date) {
        mModel.getOldData(date, new Callback<News>() {
            @Override
            public void onSuccess(News bean) {
                mView.updateUI(bean);
            }

            @Override
            public void onFail(String errorMsg) {
                mView.showErrorMsg(errorMsg);
            }
        });
    }


}

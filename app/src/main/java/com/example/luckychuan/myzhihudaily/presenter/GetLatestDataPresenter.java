package com.example.luckychuan.myzhihudaily.presenter;

import com.example.luckychuan.myzhihudaily.bean.LatestData;
import com.example.luckychuan.myzhihudaily.model.Callback;
import com.example.luckychuan.myzhihudaily.model.GetLatestDataModel;
import com.example.luckychuan.myzhihudaily.model.GetLatestDataModelImpl;
import com.example.luckychuan.myzhihudaily.view.BaseView;

/**
 * Created by Luckychuan on 2017/4/20.
 */
public class GetLatestDataPresenter extends BasePresenter {

    BaseView<LatestData> mView;
    GetLatestDataModel mModel;

    public GetLatestDataPresenter(BaseView<LatestData> view) {
        mView = view;
        mModel = new GetLatestDataModelImpl();
    }

    public void requestData(){
        mModel.getLatestData(new Callback<LatestData>() {
            @Override
            public void onSuccess(LatestData bean) {
                mView.updateUI(bean);
            }

            @Override
            public void onFail(String errorMsg) {
                mView.showErrorMsg(errorMsg);
            }
        });
    }

}
